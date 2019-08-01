package ascensionMod.UI.buttons;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.input.InputHelper;

public class AscToggleButton extends AscButton {
	public boolean toggledOn = false; 
	protected Color toggledColor;
	public AscToggleButton contradictor = null;
	
	public AscToggleButton(float x, float y, final Texture img) {
		super(x, y, img);
		
		this.toggledColor = new Color(0.8f, 0.8f, 0.8f, 1.0f);
		this.inactiveColor = new Color(0.5f, 0.5f, 0.5f, 1.0f);
    }
	
	public AscToggleButton(float x, float y, final Texture img, String text) {
		super(x, y, img, text);
		
		this.toggledColor = new Color(0.8f, 0.8f, 0.8f, 1.0f);
		this.inactiveColor = new Color(0.5f, 0.5f, 0.5f, 1.0f);
    }
	
	public AscToggleButton(float x, float y, float width, float height, final Texture img, String text) {
		super(x, y, width, height, img, text);
		
		this.toggledColor = new Color(0.8f, 0.8f, 0.8f, 1.0f);
		this.inactiveColor = new Color(0.5f, 0.5f, 0.5f, 1.0f);
    }
	
	@Override
	public void update() {
        this.hb.update(this.x, this.y);
    }
	
	public void updateToggle()
	{
		if (this.hb.hovered && InputHelper.justClickedLeft) {
            this.pressed = true;
            if (contradictor != null)
			{
		        if (contradictor.toggledOn && !this.toggledOn)
		        {
		        	contradictor.toggledOn = false;
		        }
			}
            toggledOn = !toggledOn;
            InputHelper.justClickedLeft = false;
        }
	}
	
	@Override
	public void render(final SpriteBatch sb) {
        if (this.hb.hovered) 
        {
            sb.setColor(this.activeColor);
        }
        else if (toggledOn) 
        {
        	sb.setColor(this.toggledColor);
        }
        else
        {
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
}
