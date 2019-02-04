package ascensionMod.blights;



import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

public class CursedBank extends CustomBlight {
	public static final String ID = "AscMod:CursedBank";
	public static final String NAME = "Cursed Bank";
    private static final String IMG = "blights/CursedBank.png";
    private static final String OUTLINE = "blights/outline/CursedBank.png";
    public static final String[] DESC = new String[] {"This hungry bank eats some of the gold you pick up. "};
    
    private int prevGoldCount = 99;
    private int CurrentGoldCount;
    private float GoldLossPct = 0.20f;
    
    public CursedBank() {
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
    public void onEnterRoom(final AbstractRoom room)
    {
    	prevGoldCount = AbstractDungeon.player.gold;
    }
    
    @Override
    public void onGainGold() {
    	flash();
    	CurrentGoldCount = AbstractDungeon.player.gold;
    	if(prevGoldCount < CurrentGoldCount) {
    		AbstractDungeon.player.loseGold(Math.round((CurrentGoldCount - prevGoldCount) * GoldLossPct));
    		prevGoldCount = AbstractDungeon.player.gold;
    	}
    }
}
