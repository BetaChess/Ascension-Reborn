package ascensionMod.blights;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.blights.*;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.BlightHelper;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

public class CursedBank extends CustomBlight {
	public static final String ID = "AscMod:CursedBank";
	public static final String NAME = "Cursed Bank";
    private static final String IMG = "blights/CursedBank.png";
    private static final String OUTLINE = "blights/outline/CursedBank.png";
    public static final String[] DESC = new String[] {"This hungry bank eats some of all the gold you pick up"};
    
    private int prevGoldCount = 99;
    private int CurrentGoldCount;
    private float GoldLossPct = 0.15f;
    
    public CursedBank() {
        super(ID, CursedBank.NAME, CursedBank.DESC[0], new Texture(IMG), new Texture(OUTLINE), true);
    }
    
    
    @Override
    public void updateDescription() {
        this.description = CursedBank.DESC[0];
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
        this.initializeTips();
    }
    
    
    public void onEnterRoom(AbstractRoom room)
    {
    	prevGoldCount = AbstractDungeon.player.gold;
    }
    
    public void onGainGold() {
    	flash();
    	CurrentGoldCount = AbstractDungeon.player.gold;
    	if(prevGoldCount < CurrentGoldCount) {
    		AbstractDungeon.player.loseGold(Math.round((CurrentGoldCount - prevGoldCount) * GoldLossPct));
    		prevGoldCount = AbstractDungeon.player.gold;
    	}
    }
    
    @Override
    public void postBossCombat() {
        this.flash();
        
        ArrayList<AbstractBlight> exclusion = new ArrayList<AbstractBlight>();
        
        exclusion.add(new AncientAugmentation());
        exclusion.add(new GrotesqueTrophy());
        exclusion.add(new Spear());
        exclusion.add(new VoidEssence());
        exclusion.add(new MimicInfestation());
        exclusion.add(new Muzzle());
        
        
        BlightHelper.getRandomBlight().instantObtain(AbstractDungeon.player, AbstractDungeon.player.blights.size(), true);
    }

    
}
