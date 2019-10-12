package ascensionMod.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.utils.ScissorStack;
import com.evacipated.cardcrawl.modthespire.Loader;
import com.evacipated.cardcrawl.modthespire.ModInfo;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.*;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.screens.charSelect.CharacterSelectScreen;
import com.megacrit.cardcrawl.screens.mainMenu.MainMenuScreen;
import com.megacrit.cardcrawl.screens.mainMenu.MenuCancelButton;
import com.megacrit.cardcrawl.screens.mainMenu.PatchNotesScreen;
import com.megacrit.cardcrawl.ui.buttons.Button;

import ascensionMod.AscensionMod;
import ascensionMod.UI.buttons.AscButton;
import ascensionMod.UI.buttons.AscToggleButton;
import ascensionMod.UI.buttons.CancelButton;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;

public class AscModScreen {
	
    private MenuCancelButton button = new MenuCancelButton();
    
    private boolean grabbedScreen;
    private float grabStartY;
    private float targetY;
    private float scrollY;
    private float scrollLowerBound;
    private float scrollUpperBound;
    
    // UI Variables
    private static boolean dropDownActive = false;
    
    // UI Hitboxes
    private Hitbox dropDownHitbox;
    private Hitbox presetHitbox;
    
    // UI Textures
    private static Texture foreground = ImageMaster.loadImage("ui/cover.png");
    private static Texture presetBanner = ImageMaster.loadImage("ui/selectBanner.png");
    private static Texture presetMenu = ImageMaster.loadImage("ui/rewardScreenSheet.png");
    private static Texture dropDownArrow = ImageMaster.loadImage("ui/arrowSmall.png");
    private static Texture buttonTexture = ImageMaster.loadImage("ui/button2.png");
    private static Texture ascensionContainer = ImageMaster.loadImage("ui/box.png");
    private static Texture scrollArrowRight = ImageMaster.loadImage("ui/arrowBigRight.png");
    private static Texture scrollArrowLeft = ImageMaster.loadImage("ui/arrowBigLeft.png");
    private static Texture containerTop = ImageMaster.loadImage("ui/topBox.png");
    private static Texture containerMiddle = ImageMaster.loadImage("ui/middleBox.png");
    private static Texture containerBottom = ImageMaster.loadImage("ui/bottomBox.png");
    private static Texture highlightBox = ImageMaster.loadImage("ui/highlightBox.png");
    private static Texture presetBox = ImageMaster.loadImage("ui/prebox.png");
    
    // UI Buttons
    private AscButton applyButton;
    private AscButton copyToPlaceHolderButton;
    private AscButton arrowRight;
    private AscButton arrowLeft;
    
    public ArrayList<AscToggleButton> negAscButtons = new ArrayList<>(); 
    public ArrayList<AscToggleButton> posAscButtons = new ArrayList<>();
    
    public ArrayList<AscButton> presetButtons = new ArrayList<>();
	
	//private Hitbox customAscensionModeHb;
	
	
	private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("CustomAscensionScreen");
	private static final UIStrings posShortAscensionDesc = CardCrawlGame.languagePack.getUIString("ShortAscensionModeDescriptions");
	private static final UIStrings negShortAscensionDesc = CardCrawlGame.languagePack.getUIString("ShortAscensionMinusDescriptions");
	
	
	AscModScreen() {
		
		this.grabbedScreen = false;
        this.grabStartY = 0.0f;
        this.scrollY = 0.0f;
        this.calculateScrollBounds();
		
        
		//(customAscensionModeHb = new Hitbox(100.0f * Settings.scale, 50.0f * Settings.scale)).move(Settings.WIDTH / 2.0f - 50.0f * Settings.scale, 70.0f * Settings.scale);
		(dropDownHitbox = new Hitbox(500.0f * Settings.scale, 60.0f * Settings.scale)).move(Settings.WIDTH / 2.0f, Settings.HEIGHT - 175.0f * Settings.scale);
		(presetHitbox = new Hitbox(Settings.scale * 570, Settings.scale * 802)).move(Settings.WIDTH / 2.0f - Settings.scale * 612 / 2, Settings.HEIGHT - 980.0f * Settings.scale + Settings.scale * scrollY);
		
		applyButton = new AscButton(
				Settings.WIDTH / 2.0f - Settings.scale * 201 / 2 - Settings.scale * 410.0f, 
				Settings.HEIGHT - 253.0f * Settings.scale,
				buttonTexture,
				"Set"
				);
		
		copyToPlaceHolderButton = new AscButton(
				Settings.WIDTH / 2.0f - Settings.scale * 201 / 2 + Settings.scale * 410.0f, 
				Settings.HEIGHT - 253.0f * Settings.scale,
				buttonTexture,
				"Copy"
				);
		
		arrowRight = new AscButton(
				Settings.WIDTH / 2.0f + Settings.scale * 50.0f, 
				Settings.HEIGHT - 900.0f * Settings.scale,
				scrollArrowRight
				);
		
		arrowLeft = new AscButton(
				Settings.WIDTH / 2.0f - scrollArrowLeft.getWidth() * Settings.scale - Settings.scale * 50.0f, 
				Settings.HEIGHT - 900.0f * Settings.scale,
				scrollArrowLeft
				);
		
		// Ascension Minus Buttons
		for (int i = 0; i < Math.abs(AscensionMod.MINMODASCENSIONLEVEL) - 1; i ++)
		{
			negAscButtons.add(
				new AscToggleButton(
						Settings.WIDTH / 2.0f - Settings.scale * 480 * 1.5f / 2 - 340 * Settings.scale, 
						0, // TEMP, updated in rendering
						620.0f * Settings.scale,
						80.0f * Settings.scale,
						highlightBox,
						""
						)
			);
		}
		
		negAscButtons.add(
			new AscToggleButton(
					Settings.WIDTH / 2.0f - Settings.scale * 480 * 1.5f / 2 - 340 * Settings.scale, 
					0, // TEMP, updated in rendering
					620.0f * Settings.scale,
					1.80f * 80.0f * Settings.scale,
					highlightBox,
					""
					)
		);
		
		// Ascension Plus Buttons
		for (int i = 0; i < Math.abs(AscensionMod.MAXMODASCENSIONLEVEL); i ++)
		{
			posAscButtons.add(
				new AscToggleButton(
						Settings.WIDTH / 2.0f + Settings.scale * 510 * 1.5f / 2 - 300 * Settings.scale, 
						0, // TEMP, updated in rendering
						620.0f * Settings.scale,
						80.0f * Settings.scale,
						highlightBox,
						""
						)
			);
		}
		
		// Preset boxes
		for (int i = 0; i < 4; i++)
		{
			presetButtons.add(
					new AscButton(
							Settings.WIDTH / 2.0f - presetBox.getWidth()/2.0f,
							Settings.HEIGHT - 300.0f * Settings.scale + Settings.scale * scrollY,
							presetBox,
							"FFFFFFFFFFFF"
						)
			);
		}

        //configHb = new Hitbox(100 * Settings.scale, 40 * Settings.scale);
		prepareContradictors();
	}

	
	public static class Enum
    {
        @SpireEnum
        public static MainMenuScreen.CurScreen ASC_MOD;
    }

	
	
	public void open()
    {
		targetY = 0.0f;
		
		button.show(uiStrings.TEXT[0]);
        
        CardCrawlGame.mainMenuScreen.darken();
        CardCrawlGame.mainMenuScreen.screen = Enum.ASC_MOD;
    }
	
	public void update()
    {
        // Update negative buttons
        for (int i = 0; i < Math.abs(AscensionMod.MINMODASCENSIONLEVEL); i++)
        {
        	negAscButtons.get(i).update();
        }
        
        // Update positive button
        for (int i = 0; i < Math.abs(AscensionMod.MAXMODASCENSIONLEVEL); i++)
        {
        	posAscButtons.get(i).update();
        }
        
        if(dropDownActive)	
        {
        	arrowRight.update(); // Code needs to be written
        	arrowLeft.update(); // Code needs to be written
        	
        	for (int i = 0; i < presetButtons.size(); i++)
        	{
        		presetButtons.get(i).update();
        	}
        }
        
        copyToPlaceHolderButton.update(); // Code needs to be written
        applyButton.update(); // Code needs to be written
        
        button.update();
        if (button.hb.clicked || InputHelper.pressedEscape) 
        {
            button.hb.clicked = false;
            InputHelper.pressedEscape = false;

            CardCrawlGame.mainMenuScreen.screen = MainMenuScreen.CurScreen.CHAR_SELECT;
            button.hide();
//            CardCrawlGame.mainMenuScreen.lighten();
        }
        
        dropDownHitbox.update();
        if (InputHelper.justClickedLeft && dropDownHitbox.hovered || dropDownHitbox.clicked)
        {
        	dropDownActive = !dropDownActive;
        }
        
        presetHitbox.update();
        if (presetHitbox.hovered && dropDownActive)
        {
        	for(int i = 0; i < 10; i++)
    		{
        		negAscButtons.get(i).hb.hovered = false;
        		posAscButtons.get(i).hb.hovered = false;
    		}
    	
        }
        
     // Update negative toggles
        for (int i = 0; i < Math.abs(AscensionMod.MINMODASCENSIONLEVEL); i++)
        {
        	negAscButtons.get(i).updateToggle();
        }
        
        // Update positive toggles
        for (int i = 0; i < Math.abs(AscensionMod.MAXMODASCENSIONLEVEL); i++)
        {
        	posAscButtons.get(i).updateToggle();
        }
        
        updateScrolling();

        /*if (baseModBadges != null && selectedMod >= 0 && baseModBadges.get(Loader.MODINFOS[selectedMod].jarURL) != null) {
            configHb.update();
            if (configHb.hovered && InputHelper.justClickedLeft) {
                modBadge_onClick(baseModBadges.get(Loader.MODINFOS[selectedMod].jarURL));
            }
        }*/
        
        InputHelper.justClickedLeft = false;
    }

    public void render(SpriteBatch sb)
    {
        // Getting the private orthographic camera from CardCrawlGame using reflection
        /*OrthographicCamera camera = null;
        try {
            Field f = CardCrawlGame.class.getDeclaredField("camera");
            f.setAccessible(true);
            camera = (OrthographicCamera)f.get(Gdx.app.getApplicationListener());
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }

        if (camera != null) {
            sb.flush();
            Rectangle scissors = new Rectangle();
            float y = Settings.HEIGHT - 110 * Settings.scale;
            Rectangle clipBounds = new Rectangle(50 * Settings.scale, y,
                500 * Settings.scale, button.hb.y - y + button.hb.height);
            ScissorStack.calculateScissors(camera, sb.getTransformMatrix(), clipBounds, scissors);
            ScissorStack.pushScissors(scissors);
        }*/

        
        sb.setColor(new Color(0, 0, 0, 1.0f));
        sb.draw(ImageMaster.WHITE_SQUARE_IMG, 0.0F, 0.0F, Settings.WIDTH, Settings.HEIGHT);
        sb.setColor(Color.WHITE);

        
        ////////
        // The real UI starts here
        
        // Ascension Minus Menu
        // container:
        
        // Top
        sb.draw(containerTop, 
        		Settings.WIDTH / 2.0f - Settings.scale * 510 * 1.5f / 2 - 390 * Settings.scale, Settings.HEIGHT - 920.0f * Settings.scale + Settings.scale * scrollY, // x and y
        		0.0f, 0.0f, // origin x and y
        		Settings.scale * 510 * 1.5f, Settings.scale * 516, // width and height 
        		1.0f, 1.0f, // scaling x and y
        		0.0f, // rotation
        		0, 0, // src x and y
        		510, 516, //src width and height
        		false, false // flip x and flip y
        		);
        
        // Bottom
        sb.draw(containerBottom, 
        		Settings.WIDTH / 2.0f - Settings.scale * 510 * 1.5f / 2 - 390 * Settings.scale, Settings.HEIGHT - AscensionMod.MAXMODASCENSIONLEVEL * 100.0f * Settings.scale - 500.0f * Settings.scale - 40.0f * Settings.scale + Settings.scale * scrollY, // x and y
        		0.0f, 0.0f, // origin x and y
        		Settings.scale * 510 * 1.5f, Settings.scale * 516, // width and height 
        		1.0f, 1.0f, // scaling x and y
        		0.0f, // rotation
        		0, 0, // src x and y
        		510, 516, //src width and height
        		false, false // flip x and flip y
        		);
        
        // Middle
        sb.draw(containerMiddle, 
        		Settings.WIDTH / 2.0f - Settings.scale * 510 * 1.5f / 2 - 390 * Settings.scale, Settings.HEIGHT - AscensionMod.MAXMODASCENSIONLEVEL * 100.0f * Settings.scale - 500.0f * Settings.scale - 150.0f * Settings.scale + Settings.scale * scrollY, // x and y
        		0.0f, 0.0f, // origin x and y
        		Settings.scale * 510 * 1.5f, AscensionMod.MAXMODASCENSIONLEVEL * 100 * Settings.scale + 260.0f * Settings.scale, // width and height 
        		1.0f, 1.0f, // scaling x and y
        		0.0f, // rotation
        		0, 0, // src x and y
        		510, 516, //src width and height
        		false, false // flip x and flip y
        		);
        
        // Ascension Levels
        // Highlight boxes
        for (int i = 0; i < Math.abs(AscensionMod.MINMODASCENSIONLEVEL) - 1; i++)
        {
        	negAscButtons.get(i).y = Settings.HEIGHT - 563.0f * Settings.scale + Settings.scale * scrollY - 100.0f * i * Settings.scale;
        	
        	negAscButtons.get(i).render(sb);
        }
        
        negAscButtons.get(19).y = Settings.HEIGHT - 525.0f * Settings.scale + Settings.scale * scrollY - 100.0f * 20 * Settings.scale;
        negAscButtons.get(19).render(sb);
        
        // TEXT
        for (int i = 0; i < Math.abs(AscensionMod.MINMODASCENSIONLEVEL); i++)
        {
        	FontHelper.renderFontLeft(sb, FontHelper.SCP_cardTitleFont_small, "-" + (i + 1) + ": " + negShortAscensionDesc.TEXT[i],
        			Settings.WIDTH / 2.0f - Settings.scale * 510 * 1.5f / 2 - 300 * Settings.scale, // x
        			Settings.HEIGHT - 520.0f * Settings.scale + Settings.scale * scrollY - 100.0f * i * Settings.scale, // y
                    Settings.GOLD_COLOR);
        }
        FontHelper.renderFontLeft(sb, FontHelper.SCP_cardTitleFont_small, negShortAscensionDesc.TEXT[20],
    			Settings.WIDTH / 2.0f - Settings.scale * 510 * 1.5f / 2 - 190 * Settings.scale, // x
    			Settings.HEIGHT - 520.0f * Settings.scale + Settings.scale * scrollY - 100.0f * 20 * Settings.scale + 45.0f * Settings.scale, // y
                Settings.GOLD_COLOR);
        
        
        // Ascension Plus Menu
        // container:
        
        // Top
        sb.draw(containerTop, 
        		Settings.WIDTH / 2.0f - Settings.scale * 510 * 1.5f / 2 + 390 * Settings.scale, Settings.HEIGHT - 920.0f * Settings.scale + Settings.scale * scrollY, // x and y
        		0.0f, 0.0f, // origin x and y
        		Settings.scale * 510.0f * 1.5f, Settings.scale * 516.0f, // width and height 
        		1.0f, 1.0f, // scaling x and y
        		0.0f, // rotation
        		0, 0, // src x and y
        		510, 516, //src width and height
        		false, false // flip x and flip y
        		);
        
        // Bottom
        sb.draw(containerBottom, 
        		Settings.WIDTH / 2.0f - Settings.scale * 510 * 1.5f / 2 + 390 * Settings.scale, Settings.HEIGHT - AscensionMod.MAXMODASCENSIONLEVEL * 100.0f * Settings.scale - 500.0f * Settings.scale - 40.0f * Settings.scale + Settings.scale * scrollY, // x and y
        		0.0f, 0.0f, // origin x and y
        		Settings.scale * 510 * 1.5f, Settings.scale * 516, // width and height 
        		1.0f, 1.0f, // scaling x and y
        		0.0f, // rotation
        		0, 0, // src x and y
        		510, 516, //src width and height
        		false, false // flip x and flip y
        		);
        
        // Middle
        sb.draw(containerMiddle, 
        		Settings.WIDTH / 2.0f - Settings.scale * 510 * 1.5f / 2 + 390 * Settings.scale, Settings.HEIGHT - AscensionMod.MAXMODASCENSIONLEVEL * 100.0f * Settings.scale - 500.0f * Settings.scale - 150.0f * Settings.scale + Settings.scale * scrollY, // x and y
        		0.0f, 0.0f, // origin x and y
        		Settings.scale * 510 * 1.5f, AscensionMod.MAXMODASCENSIONLEVEL * 100 * Settings.scale + 260.0f * Settings.scale, // width and height 
        		1.0f, 1.0f, // scaling x and y
        		0.0f, // rotation
        		0, 0, // src x and y
        		510, 516, //src width and height
        		false, false // flip x and flip y
        		);
        
        // Ascension Levels
        // Highlight boxes
        for (int i = 0; i < Math.abs(AscensionMod.MAXMODASCENSIONLEVEL); i++)
        {
        	posAscButtons.get(i).y = Settings.HEIGHT - 563.0f * Settings.scale + Settings.scale * scrollY - 100.0f * i * Settings.scale;
        	
        	posAscButtons.get(i).render(sb);
        }
        
        
        // TEXT
        for (int i = 0; i < AscensionMod.MAXMODASCENSIONLEVEL; i++)
        {
        	FontHelper.renderFontLeft(sb, FontHelper.SCP_cardTitleFont_small, (i + 1) + ": " + posShortAscensionDesc.TEXT[i],
        			Settings.WIDTH / 2.0f - Settings.scale * 510 * 1.5f / 2 + 490 * Settings.scale, // x
        			Settings.HEIGHT - 520.0f * Settings.scale + Settings.scale * scrollY - 100.0f * i * Settings.scale, // y
                    Settings.GOLD_COLOR);
        }
        
        
        // Dropdown menu for the presets:
        if(dropDownActive)
        {
	        sb.draw(presetMenu, 
	        		Settings.WIDTH / 2.0f - Settings.scale * 612 / 2, Settings.HEIGHT - 980.0f * Settings.scale + Settings.scale * scrollY, // x and y
	        		0.0f, 0.0f, // origin x and y
	        		Settings.scale * 612, Settings.scale * 802, // width and height 
	        		1.0f, 1.0f, // scaling x and y
	        		0.0f, // rotation
	        		0, 0, // src x and y
	        		612, 716, //src width and height
	        		false, false // flip x and flip y
	        		);
	        
	        // Arrows in dropdown menu:
	        // Right arrow
	        arrowRight.y = Settings.HEIGHT - 900.0f * Settings.scale + Settings.scale * scrollY;
        	arrowRight.render(sb);
        	
        	// Left arrow 
        	arrowLeft.y = Settings.HEIGHT - 900.0f * Settings.scale + Settings.scale * scrollY;
        	arrowLeft.render(sb);
        	
        	for (int i = 0; i < presetButtons.size(); i++)
        	{
        		presetButtons.get(i).render(sb);;
        	}
        	
        }
        
        presetHitbox.move(Settings.WIDTH / 2.0f, Settings.HEIGHT - 980.0f * Settings.scale / 2 - 70.0f * Settings.scale + Settings.scale * scrollY);
        
        // Banner to display preset:
        sb.draw(presetBanner, 
        		Settings.WIDTH / 2.0f - Settings.scale * 1112 / 2, Settings.HEIGHT - 345.0f * Settings.scale + Settings.scale * scrollY, // x and y
        		0.0f, 0.0f, // origin x and y
        		Settings.scale * 1112, Settings.scale * 238, // width and height 
        		1.0f, 1.0f, // scaling x and y
        		0.0f, // rotation
        		0, 0, // src x and y
        		1112, 238, //src width and height
        		false, false // flip x and flip y
        		);
        
       
        
        
        // Arrow next to text:
        sb.draw(dropDownArrow, 
        		Settings.WIDTH / 2.0f - Settings.scale * 57 / 2 + 200.0f * Settings.scale, Settings.HEIGHT - 228.0f * Settings.scale + Settings.scale * scrollY, // x and y
        		0.0f, 0.0f, // origin x and y
        		Settings.scale * 42.75f, Settings.scale * 40.5f, // width and height 
        		1.0f, 1.0f, // scaling x and y
        		0.0f, // rotation
        		0, 0, // src x and y
        		57, 54, //src width and height
        		false, dropDownActive // flip x and flip y
        		);
        
        // Text on the banner:
        FontHelper.renderFontCentered(sb, FontHelper.SCP_cardTitleFont_small, "None Selected",
                Settings.WIDTH / 2.0f, // x
                Settings.HEIGHT - 205.0f * Settings.scale + Settings.scale * scrollY, // y
                Settings.GOLD_COLOR);
        
        // Update hitbox position
        dropDownHitbox.move(Settings.WIDTH / 2.0f, Settings.HEIGHT - 205.0f * Settings.scale + Settings.scale * scrollY);
        
        // Button
        applyButton.y = Settings.HEIGHT - 283.0f * Settings.scale + Settings.scale * scrollY;
        applyButton.render(sb);
        
        copyToPlaceHolderButton.y = Settings.HEIGHT - 283.0f * Settings.scale + Settings.scale * scrollY;
        copyToPlaceHolderButton.render(sb);
        
        
        // Foreground
        sb.draw(foreground, 
        		0.0f, 0.0f, // x and y
        		0.0f, 0.0f, // origin x and y
        		Settings.WIDTH, Settings.HEIGHT, // width and height 
        		1.0f, 1.0f, // scaling x and y
        		0.0f, // rotation
        		0, 0, // src x and y
        		1920, 1200, //src height and width
        		false, false // flip x and flip y
        		);
        
        // Text at the top of the screen
        FontHelper.renderFontCentered(sb, FontHelper.SCP_cardTitleFont_small, "Custom Ascension Screen",
            Settings.WIDTH / 2.0f,
            Settings.HEIGHT - 70.0f * Settings.scale,
            Settings.GOLD_COLOR);
        
        
        // Ascension Minus container
        /*sb.draw(ascensionContainer, 
        		Settings.WIDTH / 2.0f - Settings.scale * 57 / 2 + 200.0f * Settings.scale, Settings.HEIGHT - 198.0f * Settings.scale, // x and y
        		0.0f, 0.0f, // origin x and y
        		Settings.scale * 42.75f, Settings.scale * 40.5f, // width and height 
        		1.0f, 1.0f, // scaling x and y
        		0.0f, // rotation
        		0, 0, // src x and y
        		510, 670, //src width and height
        		false, dropDownActive // flip x and flip y
        		);*/
        
        
        
        
        /*sb.draw(buttonTexture, 
        		Settings.WIDTH / 2.0f - Settings.scale * 201 / 2 - Settings.scale * 500.0f, Settings.HEIGHT - 335.0f * Settings.scale, // x and y
        		0.0f, 0.0f, // origin x and y
        		Settings.scale * 201, Settings.scale * 108, // width and height 
        		1.0f, 1.0f, // scaling x and y
        		0.0f, // rotation
        		0, 0, // src x and y
        		201, 108, //src width and height
        		false, false // flip x and flip y
        		);*/
        
     
        
        //float tmpY = 0;
        /*for (int i=0; i<Loader.MODINFOS.length; ++i) {
            if (ascHitboxes.get(i).hovered) {
                Color c = sb.getColor();
                sb.setColor(1, 1, 1, (ascHitboxes.get(i).clickStarted ? 0.8f : 0.4f));
                sb.draw(ImageMaster.WHITE_SQUARE_IMG, ascHitboxes.get(i).x, ascHitboxes.get(i).y, ascHitboxes.get(i).width, ascHitboxes.get(i).height);
                sb.setColor(c);
            }

            FontHelper.renderFontLeftTopAligned(sb, FontHelper.buttonLabelFont, Loader.MODINFOS[i].Name,
                95.0f * Settings.scale,
                tmpY + scrollY,
                Settings.CREAM_COLOR);
            if (i == selectedMod) {
                drawRect(sb,
                    hitboxes.get(i).x,
                    hitboxes.get(i).y,
                    hitboxes.get(i).width,
                    hitboxes.get(i).height,
                    2);
            }

            // Render BaseMod ModBadges
            if (baseModBadges != null) {
                for (Map.Entry<URL, Object> entry : baseModBadges.entrySet()) {
                    try {
                        if (entry.getKey().equals(modURL)) {
                            Object modBadge = entry.getValue();
                            ModBadge_x.set(modBadge, 55.0f * Settings.scale);
                            ModBadge_y.set(modBadge, tmpY + scrollY - 27.0f * Settings.scale);

                            boolean tmpModSettingsUp = (boolean)BaseMod_modSettingsUp.get(null);
                            BaseMod_modSettingsUp.set(null, false);
                            MainMenuScreen.CurScreen tmpScreen = CardCrawlGame.mainMenuScreen.screen;
                            CardCrawlGame.mainMenuScreen.screen = MainMenuScreen.CurScreen.MAIN_MENU;
                            ModBadge_render.invoke(modBadge, sb);
                            CardCrawlGame.mainMenuScreen.screen = tmpScreen;
                            BaseMod_modSettingsUp.set(null, tmpModSettingsUp);
                            break;
                        }
                    } catch (IllegalAccessException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
            
            

            tmpY -= 45.0f * Settings.scale;
        }*/
        
        /*if (camera != null) {
            sb.flush();
            ScissorStack.popScissors();
        }*/

        button.render(sb);
        
        
    }
    
    private void updateScrolling() {
        final int y = InputHelper.mY;
        if (this.scrollUpperBound > 0.0f) {
            if (!this.grabbedScreen) {
                if (InputHelper.scrolledDown) {
                    this.targetY += Settings.SCROLL_SPEED;
                }
                else if (InputHelper.scrolledUp) {
                    this.targetY -= Settings.SCROLL_SPEED;
                }
                if (InputHelper.justClickedLeft) {
                    this.grabbedScreen = true;
                    this.grabStartY = y - this.targetY;
                }
            }
            else if (InputHelper.isMouseDown) {
                this.targetY = y - this.grabStartY;
            }
            else {
                this.grabbedScreen = false;
            }
        }
        this.scrollY = MathHelper.scrollSnapLerpSpeed(this.scrollY, this.targetY);
        if (this.targetY < this.scrollLowerBound) {
            this.targetY = MathHelper.scrollSnapLerpSpeed(this.targetY, this.scrollLowerBound);
        }
        else if (this.targetY > this.scrollUpperBound) {
            this.targetY = MathHelper.scrollSnapLerpSpeed(this.targetY, this.scrollUpperBound);
        }
    }
    
    private void calculateScrollBounds() {
    	AscensionMod.logger.info(Settings.scale);
        this.scrollUpperBound = Settings.HEIGHT * Settings.scale / 2 + AscensionMod.MAXMODASCENSIONLEVEL * 90.0f * Settings.scale - 200.0f * Settings.scale;
        this.scrollLowerBound = 50.0f * Settings.scale;
    }
    
    private void prepareContradictors()
    {
    	// Negative contradictions
    	negAscButtons.get(0).contradictor = posAscButtons.get(1);
    	negAscButtons.get(1).contradictor = posAscButtons.get(2);
    	negAscButtons.get(2).contradictor = posAscButtons.get(3);
    	negAscButtons.get(3).contradictor = posAscButtons.get(6);
    	negAscButtons.get(4).contradictor = posAscButtons.get(7);
    	negAscButtons.get(5).contradictor = posAscButtons.get(8);
    	negAscButtons.get(6).contradictor = posAscButtons.get(11);
    	negAscButtons.get(7).contradictor = posAscButtons.get(12);
    	negAscButtons.get(8).contradictor = posAscButtons.get(13);
    	negAscButtons.get(9).contradictor = posAscButtons.get(15);
    	negAscButtons.get(11).contradictor = posAscButtons.get(21);
    	negAscButtons.get(12).contradictor = posAscButtons.get(22);
    	
    	posAscButtons.get(1).contradictor = negAscButtons.get(0);
    	posAscButtons.get(2).contradictor = negAscButtons.get(1);
    	posAscButtons.get(3).contradictor = negAscButtons.get(2);
    	posAscButtons.get(6).contradictor = negAscButtons.get(3);
    	posAscButtons.get(7).contradictor = negAscButtons.get(4);
    	posAscButtons.get(8).contradictor = negAscButtons.get(5);
    	posAscButtons.get(11).contradictor = negAscButtons.get(6);
    	posAscButtons.get(12).contradictor = negAscButtons.get(7);
    	posAscButtons.get(13).contradictor = negAscButtons.get(8);
    	posAscButtons.get(15).contradictor = negAscButtons.get(9);
    	posAscButtons.get(21).contradictor = negAscButtons.get(11);
    	posAscButtons.get(22).contradictor = negAscButtons.get(12);
    }
    
}
