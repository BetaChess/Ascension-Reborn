package ascensionMod.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.relics.AbstractRelic;


public class StarOfAscension1 extends CustomRelic {
	public static final String ID = "StarOfAscension1";
    private static final String IMG = "relics/StarOfAscension.png";
    private static final String OutlineIMG = "relics/outline/StarOfAscension.png";
    
    public static void relicAction() {
    	com.megacrit.cardcrawl.shop.ShopScreen.purgeCost = 100;
    	com.megacrit.cardcrawl.shop.ShopScreen.actualPurgeCost = 100;
    }
    
    public void onEquip() {
    	relicAction();
    }
    
    public StarOfAscension1() {
        super(ID, new Texture(IMG), new Texture(OutlineIMG), RelicTier.STARTER, LandingSound.FLAT);
    }
    
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
    
    @Override
    public AbstractRelic makeCopy() {
        return new StarOfAscension1();
    }
}