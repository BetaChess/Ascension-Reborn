package ascensionMod.relics;

import basemod.abstracts.CustomRelic;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

  

public class CursedBank extends CustomRelic {
	public static final String ID = "AscMod:CursedBank";
    private static final String IMG = "relics/CursedBank.png";
    private static final String OutlineIMG = "relics/outline/CursedBank.png";
    
    public static final Logger logger = LogManager.getLogger(CursedBank.class.getName());
    
    private float GoldLossPct = 0.15f;
    
    private int prevGoldCount = 99;
    private int CurrentGoldCount;
    
    
    
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

    
    public CursedBank() {
        super(ID, new Texture(IMG), new Texture(OutlineIMG), RelicTier.SPECIAL, LandingSound.FLAT);
    }
    
    
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
    
    
    @Override
    public AbstractRelic makeCopy() {
        return new CursedBank();
    }
}