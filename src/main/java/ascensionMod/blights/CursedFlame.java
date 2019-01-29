package ascensionMod.blights;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.blights.AbstractBlight;
import com.megacrit.cardcrawl.blights.AncientAugmentation;
import com.megacrit.cardcrawl.blights.GrotesqueTrophy;
import com.megacrit.cardcrawl.blights.MimicInfestation;
import com.megacrit.cardcrawl.blights.Muzzle;
import com.megacrit.cardcrawl.blights.Spear;
import com.megacrit.cardcrawl.blights.VoidEssence;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.BlightHelper;
import com.megacrit.cardcrawl.helpers.PowerTip;

public class CursedFlame extends CustomBlight {
	public static final String ID = "AscMod:CursedFlame";
	public static final String NAME = "Cursed Flame";
    private static final String IMG = "blights/CursedFlame.png";
    private static final String OUTLINE = "blights/outline/CursedFlame.png";
    public static final String[] DESC = new String[] {"The eternal flame of the spire punishes you when you kill a boss. "};
    
    public CursedFlame() {
        super(ID, CursedFlame.NAME, CursedFlame.DESC[0], new Texture(IMG), new Texture(OUTLINE), true);
    }
    
    
    @Override
    public void updateDescription() {
        this.description = CursedFlame.DESC[0];
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
        this.initializeTips();
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
