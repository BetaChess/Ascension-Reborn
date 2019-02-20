package ascensionMod.blights;


import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.helpers.PowerTip;


public class CursedFlame extends CustomBlight {
	public static final String ID = "AscMod:CursedFlame";
    private static final String IMG = "blights/CursedFlame.png";
    private static final String OUTLINE = "blights/outline/CursedFlame.png";
    
    public static int count = 0;
    
    public CursedFlame() {
        super(ID, new Texture(IMG), new Texture(OUTLINE), true);
    }

    @Override
    public void postBossCombat() {
        this.flash();
        CursedFlame.count = 0;
    }

}
