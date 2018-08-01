package ascensionMod.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.relics.AbstractRelic;
   
 
public class MegaStarOfAscension extends CustomRelic {
	public static final String ID = "AscMod:MegaStarOfAscension";
    private static final String IMG = "relics/MegaStarOfAscension.png";
    private static final String OutlineIMG = "relics/outline/MegaStarOfAscension.png";
    
    

    public void onEquip() {
    	if(CardCrawlGame.mainMenuScreen.charSelectScreen.ascensionLevel > 15) {
    		com.megacrit.cardcrawl.shop.ShopScreen.purgeCost = 100;
        	com.megacrit.cardcrawl.shop.ShopScreen.actualPurgeCost = 100;
    	}
    }
    
    
    public int onPlayerHeal(int healAmount) {
    	if(CardCrawlGame.mainMenuScreen.charSelectScreen.ascensionLevel >= 19) {
    		flash();
    	}
    	
    	return healAmount;
    }
    
    public MegaStarOfAscension() {
        super(ID, new Texture(IMG), new Texture(OutlineIMG), RelicTier.SPECIAL, LandingSound.FLAT);
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