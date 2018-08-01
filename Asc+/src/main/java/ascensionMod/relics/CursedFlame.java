package ascensionMod.relics;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.*;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;

import basemod.abstracts.CustomRelic;


public class CursedFlame extends CustomRelic {
	public static final String ID = "AscMod:CursedFlame";
    private static final String IMG = "relics/CursedFire.png";
    private static final String OutlineIMG = "relics/outline/CursedFire.png";
    
	
	@Override
    public void onEnterRoom(final AbstractRoom room) {
        if (room instanceof TreasureRoomBoss) {
            this.flash();
            this.pulse = true;
        }
        else {
            this.pulse = false;
        }
    }
	
	@Override
    public void onChestOpen(final boolean bossChest) {
        if (bossChest) {
            AbstractDungeon.topLevelEffects.add(new ShowCardAndObtainEffect(AbstractDungeon.returnRandomCurse(), Settings.WIDTH / 2, Settings.HEIGHT / 2));
            AbstractDungeon.actionManager.addToTop(new RelicAboveCreatureAction(AbstractDungeon.player, this));
        }
    }
	
	
	public CursedFlame() {
        super(ID, new Texture(IMG), new Texture(OutlineIMG), RelicTier.SPECIAL, LandingSound.FLAT);
    }
	
	@Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
    
    
    @Override
    public AbstractRelic makeCopy() {
        return new CursedFlame();
    }
}