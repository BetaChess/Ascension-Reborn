package ascensionMod.blights;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.blights.AbstractBlight;

public abstract class CustomBlight extends AbstractBlight {
    public CustomBlight(final String ID, final String name, final String desc, final Texture imgTexture, final Texture imgOutline, final boolean unique) {
        super(ID, name, desc, null, unique);
        
        this.img = imgTexture;
        this.outlineImg = imgOutline;
    }
    
    public void postBossCombat()
    {
    }
}
