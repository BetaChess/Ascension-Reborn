package ascensionMod.UI.buttons;

import java.lang.reflect.Field;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.screens.custom.CustomModeScreen;
import com.megacrit.cardcrawl.ui.buttons.Button;

public class AscButton {
	
	public float x;
    public float y;
    protected Texture img;
    public Hitbox hb;
    protected Color activeColor;
    protected Color inactiveColor;
    public boolean pressed;
    public float height;
    public float width;
    protected String text;
    protected float textSize;
	
	public AscButton(float x, float y, final Texture img) {
		this.activeColor = Color.WHITE;
        this.inactiveColor = new Color(0.6f, 0.6f, 0.6f, 1.0f);
        this.pressed = false;
        this.x = x;
        this.y = y;
        this.img = img;
        this.height = (int) (img.getHeight() * Settings.scale);
        this.width = (int) (img.getWidth() * Settings.scale);
        this.hb = new Hitbox(x, y, this.width, this.height);
        this.text = "";
    }
	
	public AscButton(float x, float y, final Texture img, String text) {
		this(x, y, img);
        this.text = text;
    }
	
	public AscButton(float x, float y, float width, float height, final Texture img, String text) {
		this(x, y, img, text);
        this.width = width;
        this.height = height;
        this.hb = new Hitbox(x, y, this.width, this.height);
    }
	
    public void update() {
        this.hb.update(this.x, this.y);
        if (this.hb.hovered && InputHelper.justClickedLeft) {
            this.pressed = true;
            InputHelper.justClickedLeft = false;
        }
    }
    
    public void render(final SpriteBatch sb) {
        if (this.hb.hovered) {
            sb.setColor(this.activeColor);
        }
        else {
            sb.setColor(this.inactiveColor);
        }
        
        sb.draw(img, 
        		x, y, // x and y
        		0.0f, 0.0f, // origin x and y
        		width, height, // width and height 
        		1.0f, 1.0f, // scaling x and y
        		0.0f, // rotation
        		0, 0, // src x and y
        		img.getWidth(), img.getHeight(), //src width and height
        		false, false // flip x and flip y
        		);
        
        FontHelper.renderFontCentered(sb, FontHelper.cardEnergyFont_L, text,
    			x + width / 2, // x
    			y + height / 2, // y
                Settings.GOLD_COLOR);
        sb.setColor(Color.WHITE);
        this.hb.render(sb);
    }
    
    public void setText(String newText)
    {
    	this.text = newText;
    }
    
    
}
