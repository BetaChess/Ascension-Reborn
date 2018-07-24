package ascensionMod.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.relics.AbstractRelic;
   
 
public class MegaStarOfAscension extends CustomRelic {
	public static final String ID = "MegaStarOfAscension";
    private static final String IMG = "relics/MegaStarOfAscension.png";
    private static final String OutlineIMG = "relics/outline/MegaStarOfAscension.png";
    
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
    
    public MegaStarOfAscension() {
        super(ID, new Texture(IMG), new Texture(OutlineIMG), RelicTier.STARTER, LandingSound.FLAT);
    }
    
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
    
    @Override
    public AbstractRelic makeCopy() {
        return new MegaStarOfAscension();
    }
}