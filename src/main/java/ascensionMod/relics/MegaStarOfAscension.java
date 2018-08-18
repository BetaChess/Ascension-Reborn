package ascensionMod.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.relics.AbstractRelic;
   
 
public class MegaStarOfAscension extends CustomRelic {
	public static final String ID = "AscMod:MegaStarOfAscension";
    private static final String IMG = "relics/MegaStarOfAscension.png";
    private static final String OutlineIMG = "relics/outline/MegaStarOfAscension.png";
    private static final int newPurgecost = 100;
    

    public void onEquip() {
    	if(CardCrawlGame.mainMenuScreen.charSelectScreen.ascensionLevel > 20) {
    		com.megacrit.cardcrawl.shop.ShopScreen.purgeCost = newPurgecost;
        	com.megacrit.cardcrawl.shop.ShopScreen.actualPurgeCost = newPurgecost;
    	}
    }
    
    
    public int onPlayerHeal(int healAmount) {
    	if(CardCrawlGame.mainMenuScreen.charSelectScreen.ascensionLevel >= 23) {
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