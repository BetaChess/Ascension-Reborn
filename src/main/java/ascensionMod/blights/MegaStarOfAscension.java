package ascensionMod.blights;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.helpers.PowerTip;

import ascensionMod.AscensionMod;
import ascensionMod.UI.CharSelectScreenUI;

public class MegaStarOfAscension extends CustomBlight {
	public static final String ID = "AscMod:MegaStarOfAscension";
    private static final String IMG = "blights/MegaStarOfAscension.png";
    private static final String OUTLINE = "blights/outline/MegaStarOfAscension.png";
    private static final int newPurgecost = 100;
    
    
    public MegaStarOfAscension() {
        super(ID, new Texture(IMG), new Texture(OUTLINE), true);   
    }
    
    @Override
    public void onEquip() {
    	if(AscensionMod.AbsoluteAscensionLevel >= 21 || (AscensionMod.customAscensionRun && CharSelectScreenUI.ascScreen.posAscButtons.get(20).toggledOn)) {
    		com.megacrit.cardcrawl.shop.ShopScreen.purgeCost = newPurgecost;
        	com.megacrit.cardcrawl.shop.ShopScreen.actualPurgeCost = newPurgecost;
    	}
    }
    
    @Override
    public int onPlayerHeal(int healAmount) {
    	if(AscensionMod.AbsoluteAscensionLevel >= 23 || (AscensionMod.customAscensionRun && CharSelectScreenUI.ascScreen.posAscButtons.get(22).toggledOn)) {
    		flash();
    	}
    	
    	return healAmount;
    }
}
