package ascensionMod.UI.panels;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.MathHelper;
import com.megacrit.cardcrawl.helpers.SaveHelper;
import com.megacrit.cardcrawl.helpers.TypeHelper;
import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
import com.megacrit.cardcrawl.helpers.input.InputActionSet;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.helpers.input.ScrollInputProcessor;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.ui.panels.RenamePopup;

import ascensionMod.UI.CharSelectScreenUI;


/*
Most of the code is taken from RenamePopup,
it has simply been refurbished to fit more with my needs.
*/


public class RenameBox {
	
	private static final UIStrings LocalStrings = CardCrawlGame.languagePack.getUIString("RenameBoxStrings"); 
	public static final String[] LocalStringsText = LocalStrings.TEXT; 
	
    private static final UIStrings uiStrings;
    public static final String[] TEXT;
    public boolean shown;
    public static String textField;
    public Hitbox yesHb;
    public Hitbox noHb;
    private Color faderColor;
    private Color uiColor;
    private float waitTimer;
    
    public RenameBox()
    {
        this.shown = false;
        this.yesHb = new Hitbox(160.0f * Settings.scale, 70.0f * Settings.scale);
        this.noHb = new Hitbox(160.0f * Settings.scale, 70.0f * Settings.scale);
        this.faderColor = new Color(0.0f, 0.0f, 0.0f, 0.0f);
        this.uiColor = new Color(1.0f, 0.965f, 0.886f, 0.0f);
        this.waitTimer = 0.0f;
        this.yesHb.move(854.0f * Settings.scale, Settings.OPTION_Y - 120.0f * Settings.scale);
        this.noHb.move(1066.0f * Settings.scale, Settings.OPTION_Y - 120.0f * Settings.scale);
    }
    
    public void update() {
        if (Gdx.input.isKeyPressed(67) && !RenameBox.textField.equals("") && this.waitTimer <= 0.0f) {
            RenameBox.textField = RenameBox.textField.substring(0, RenameBox.textField.length() - 1);
            
            this.waitTimer = 0.09f;
        }
        if (this.waitTimer > 0.0f) {
            this.waitTimer -= Gdx.graphics.getDeltaTime();
        }
        if (this.shown) {
            this.faderColor.a = MathHelper.fadeLerpSnap(this.faderColor.a, 0.75f);
            this.uiColor.a = MathHelper.fadeLerpSnap(this.uiColor.a, 1.0f);
            this.updateButtons();
            if (Gdx.input.isKeyJustPressed(66)) {
                this.confirm();
            }
            else if (InputHelper.pressedEscape) {
                InputHelper.pressedEscape = false;
                this.cancel();
            }
        }
        else {
            this.faderColor.a = MathHelper.fadeLerpSnap(this.faderColor.a, 0.0f);
            this.uiColor.a = MathHelper.fadeLerpSnap(this.uiColor.a, 0.0f);
        }
    }
    
    private void updateButtons() {
        this.yesHb.update();
        if (this.yesHb.justHovered) {
            CardCrawlGame.sound.play("UI_HOVER");
        }
        else if (this.yesHb.hovered && InputHelper.justClickedLeft) {
            CardCrawlGame.sound.play("UI_CLICK_1");
            this.yesHb.clickStarted = true;
        }
        else if (this.yesHb.clicked) {
            this.confirm();
            this.yesHb.clicked = false;
        }
        this.noHb.update();
        if (this.noHb.justHovered) {
            CardCrawlGame.sound.play("UI_HOVER");
        }
        else if (this.noHb.hovered && InputHelper.justClickedLeft) {
            CardCrawlGame.sound.play("UI_CLICK_1");
            this.noHb.clickStarted = true;
        }
        else if (this.noHb.clicked) {
            this.cancel();
            this.noHb.clicked = false;
        }
        if (CInputActionSet.proceed.isJustPressed()) {
            CInputActionSet.proceed.unpress();
            this.confirm();
        }
        else if (CInputActionSet.cancel.isJustPressed() || InputActionSet.cancel.isJustPressed()) {
            CInputActionSet.cancel.unpress();
            this.cancel();
        }
    }
    
    public void confirm() {
        RenameBox.textField = RenameBox.textField.trim();
        if (RenameBox.textField.equals("")) {
            return;
        }
        this.shown = false;
        
        Gdx.input.setInputProcessor(new ScrollInputProcessor());
        CharSelectScreenUI.ascScreen.nameConfiguration(RenameBox.textField);
    }
    
    public void cancel() {
        this.shown = false;
        Gdx.input.setInputProcessor(new ScrollInputProcessor());
    }
    
    public void render(final SpriteBatch sb) {
        sb.setColor(this.faderColor);
        sb.draw(ImageMaster.WHITE_SQUARE_IMG, 0.0f, 0.0f, Settings.WIDTH, Settings.HEIGHT);
        FontHelper.cardTitleFont_small.getData().setScale(1.0f);
        FontHelper.cardTitleFont.getData().setScale(1.0f);
        this.renderPopupBg(sb);
        this.renderTextbox(sb);
        this.renderHeader(sb);
        this.renderButtons(sb);
    }
    
    private void renderHeader(final SpriteBatch sb) {
        final Color c = Settings.GOLD_COLOR.cpy();
        c.a = this.uiColor.a;
        FontHelper.renderFontCentered(sb, FontHelper.cardTitleFont, RenameBox.LocalStringsText[0], Settings.WIDTH / 2.0f, Settings.OPTION_Y + 150.0f * Settings.scale, c);
    }
    
    private void renderPopupBg(final SpriteBatch sb) {
        sb.setColor(this.uiColor);
        sb.draw(ImageMaster.OPTION_CONFIRM, Settings.WIDTH / 2.0f - 180.0f, Settings.OPTION_Y - 207.0f, 180.0f, 207.0f, 360.0f, 414.0f, Settings.scale, Settings.scale, 0.0f, 0, 0, 360, 414, false, false);
    }
    
    private void renderTextbox(final SpriteBatch sb) {
        sb.draw(ImageMaster.RENAME_BOX, Settings.WIDTH / 2.0f - 160.0f, Settings.OPTION_Y + 20.0f * Settings.scale - 160.0f, 160.0f, 160.0f, 320.0f, 320.0f, Settings.scale, Settings.scale, 0.0f, 0, 0, 320, 320, false, false);
        FontHelper.renderSmartText(sb, FontHelper.cardTitleFont_small, RenameBox.textField, Settings.WIDTH / 2.0f - 120.0f * Settings.scale, Settings.OPTION_Y + 24.0f * Settings.scale, 100000.0f, 0.0f, this.uiColor);
        final float tmpAlpha = (MathUtils.cosDeg(System.currentTimeMillis() / 3L % 360L) + 1.25f) / 3.0f * this.uiColor.a;
        FontHelper.renderSmartText(sb, FontHelper.cardTitleFont_small, "_", Settings.WIDTH / 2.0f - 122.0f * Settings.scale + FontHelper.getSmartWidth(FontHelper.cardTitleFont_small, RenameBox.textField, 1000000.0f, 0.0f), Settings.OPTION_Y + 24.0f * Settings.scale, 100000.0f, 0.0f, new Color(1.0f, 1.0f, 1.0f, tmpAlpha));
    }
    
    private void renderButtons(final SpriteBatch sb) {
        sb.setColor(this.uiColor);
        Color c = Settings.GOLD_COLOR.cpy();
        c.a = this.uiColor.a;
        if (this.yesHb.clickStarted) {
            sb.setColor(new Color(1.0f, 1.0f, 1.0f, this.uiColor.a * 0.9f));
            sb.draw(ImageMaster.OPTION_YES, Settings.WIDTH / 2.0f - 86.5f - 100.0f * Settings.scale, Settings.OPTION_Y - 37.0f - 120.0f * Settings.scale, 86.5f, 37.0f, 173.0f, 74.0f, Settings.scale, Settings.scale, 0.0f, 0, 0, 173, 74, false, false);
            sb.setColor(new Color(this.uiColor));
        }
        else {
            sb.draw(ImageMaster.OPTION_YES, Settings.WIDTH / 2.0f - 86.5f - 100.0f * Settings.scale, Settings.OPTION_Y - 37.0f - 120.0f * Settings.scale, 86.5f, 37.0f, 173.0f, 74.0f, Settings.scale, Settings.scale, 0.0f, 0, 0, 173, 74, false, false);
        }
        if (!this.yesHb.clickStarted && this.yesHb.hovered) {
            sb.setColor(new Color(1.0f, 1.0f, 1.0f, this.uiColor.a * 0.25f));
            sb.setBlendFunction(770, 1);
            sb.draw(ImageMaster.OPTION_YES, Settings.WIDTH / 2.0f - 86.5f - 100.0f * Settings.scale, Settings.OPTION_Y - 37.0f - 120.0f * Settings.scale, 86.5f, 37.0f, 173.0f, 74.0f, Settings.scale, Settings.scale, 0.0f, 0, 0, 173, 74, false, false);
            sb.setBlendFunction(770, 771);
            sb.setColor(this.uiColor);
        }
        if (this.yesHb.clickStarted || RenameBox.textField.trim().equals("")) {
            c = Color.LIGHT_GRAY.cpy();
        }
        else if (this.yesHb.hovered) {
            c = Settings.CREAM_COLOR.cpy();
        }
        else {
            c = Settings.GOLD_COLOR.cpy();
        }
        c.a = this.uiColor.a;
        // TODO: LOOK AT TEXT
        FontHelper.renderFontCentered(sb, FontHelper.cardTitleFont_small, RenameBox.TEXT[2], Settings.WIDTH / 2.0f - 110.0f * Settings.scale, Settings.OPTION_Y - 118.0f * Settings.scale, c, 1.0f);
        sb.draw(ImageMaster.OPTION_NO, Settings.WIDTH / 2.0f - 80.5f + 106.0f * Settings.scale, Settings.OPTION_Y - 37.0f - 120.0f * Settings.scale, 80.5f, 37.0f, 161.0f, 74.0f, Settings.scale, Settings.scale, 0.0f, 0, 0, 161, 74, false, false);
        if (!this.noHb.clickStarted && this.noHb.hovered) {
            sb.setColor(new Color(1.0f, 1.0f, 1.0f, this.uiColor.a * 0.25f));
            sb.setBlendFunction(770, 1);
            sb.draw(ImageMaster.OPTION_NO, Settings.WIDTH / 2.0f - 80.5f + 106.0f * Settings.scale, Settings.OPTION_Y - 37.0f - 120.0f * Settings.scale, 80.5f, 37.0f, 161.0f, 74.0f, Settings.scale, Settings.scale, 0.0f, 0, 0, 161, 74, false, false);
            sb.setBlendFunction(770, 771);
            sb.setColor(this.uiColor);
        }
        if (this.noHb.clickStarted) {
            c = Color.LIGHT_GRAY.cpy();
        }
        else if (this.noHb.hovered) {
            c = Settings.CREAM_COLOR.cpy();
        }
        else {
            c = Settings.GOLD_COLOR.cpy();
        }
        c.a = this.uiColor.a;
        FontHelper.renderFontCentered(sb, FontHelper.cardTitleFont_small, RenameBox.TEXT[3], Settings.WIDTH / 2.0f + 110.0f * Settings.scale, Settings.OPTION_Y - 118.0f * Settings.scale, c, 1.0f);
        if (Settings.isControllerMode) {
            sb.draw(CInputActionSet.proceed.getKeyImg(), 770.0f * Settings.scale - 32.0f, Settings.OPTION_Y - 32.0f - 140.0f * Settings.scale, 32.0f, 32.0f, 64.0f, 64.0f, Settings.scale, Settings.scale, 0.0f, 0, 0, 64, 64, false, false);
            sb.draw(CInputActionSet.cancel.getKeyImg(), 1150.0f * Settings.scale - 32.0f, Settings.OPTION_Y - 32.0f - 140.0f * Settings.scale, 32.0f, 32.0f, 64.0f, 64.0f, Settings.scale, Settings.scale, 0.0f, 0, 0, 64, 64, false, false);
        }
        if (this.shown) {
            this.yesHb.render(sb);
            this.noHb.render(sb);
        }
    }
    
    public void open() {
        Gdx.input.setInputProcessor(new TypeHelper());
        this.shown = true;
        RenameBox.textField = "";
        RenamePopup.textField = "";
    }
    
    static {
        uiStrings = CardCrawlGame.languagePack.getUIString("RenamePanel");
        TEXT = RenameBox.uiStrings.TEXT;
        RenameBox.textField = "";
    }
    
}
