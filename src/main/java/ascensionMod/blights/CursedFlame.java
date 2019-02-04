package ascensionMod.blights;


import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.helpers.PowerTip;


public class CursedFlame extends CustomBlight {
	public static final String ID = "AscMod:CursedFlame";
	public static final String NAME = "Cursed Flame";
    private static final String IMG = "blights/CursedFlame.png";
    private static final String OUTLINE = "blights/outline/CursedFlame.png";
    public static final String[] DESC = new String[] {"The eternal flame of the spire punishes you when you kill a boss. "};
    public static int count = 0;
    
    public CursedFlame() {
        super(ID, NAME, DESC[0], new Texture(IMG), new Texture(OUTLINE), true);
    }
    
    
    @Override
    public void updateDescription() {
        this.description = DESC[0];
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
        this.initializeTips();
    }
    
    @Override
    public void postBossCombat() {
        this.flash();
        CursedFlame.count = 0;
    }

}
