package ascensionMod.blights;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.helpers.PowerTip;

import ascensionMod.AscensionMod;

public class StarOfAscension extends CustomBlight {
	public static final String ID = "AscMod:StarOfAscension";
    private static final String IMG = "blights/StarOfAscension.png";
    private static final String OUTLINE = "blights/outline/StarOfAscension.png";

    private static final int newPurgecost = 100;
    
    
    public StarOfAscension() {
        super(ID, new Texture(IMG), new Texture(OUTLINE), true);   
    }
    
    @Override
    public void updateDescription() {
        this.description = this.blightstrings.DESCRIPTION[0];
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
        this.initializeTips();
    }
    
    @Override
    public void onEquip() {
    	if(AscensionMod.AbsoluteAscensionLevel > 20) {
    		com.megacrit.cardcrawl.shop.ShopScreen.purgeCost = newPurgecost;
        	com.megacrit.cardcrawl.shop.ShopScreen.actualPurgeCost = newPurgecost;
    	}
    }
    
    @Override
    public int onPlayerHeal(int healAmount) {
    	if(AscensionMod.AbsoluteAscensionLevel >= 23) {
    		flash();
    	}
    	
    	return healAmount;
    }
}
