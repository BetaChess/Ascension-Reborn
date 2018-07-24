package ascensionMod.relics;

import basemod.abstracts.CustomRelic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.relics.AbstractRelic;
 

public class StarOfAscension extends CustomRelic {
	public static final Logger logger = LogManager.getLogger(StarOfAscension.class.getName());
	
	public static final String ID = "StarOfAscension";
    private static final String IMG = "relics/StarOfAscension.png";
    private static final String OutlineIMG = "relics/outline/StarOfAscension.png";
    
    public static int AL;
    

    public void onEquip() {
    	if(AL > 0) {
    		com.megacrit.cardcrawl.shop.ShopScreen.purgeCost = 100;
        	com.megacrit.cardcrawl.shop.ShopScreen.actualPurgeCost = 100;
    	}
    }
    
    
    public int onPlayerHeal(int healAmount) {
    	if(AL >= 4) {
    		flash();
    	}
    	
    	return healAmount;
    }
    
    public StarOfAscension() {
        super(ID, new Texture(IMG), new Texture(OutlineIMG), RelicTier.STARTER, LandingSound.FLAT);
    }
    
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
    
    @Override
    public AbstractRelic makeCopy() {
        return new StarOfAscension();
    }
}