package ascensionMod.blights;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.helpers.PowerTip;

import ascensionMod.AscensionMod;

public class MegaStarOfAscension extends CustomBlight {
	public static final String ID = "AscMod:MegaStarOfAscension";
	public static final String NAME = "Star of Ascension";
    private static final String IMG = "blights/MegaStarOfAscension.png";
    private static final String OUTLINE = "blights/outline/MegaStarOfAscension.png";
    public static final String[] DESC = new String[] {"A star given to you by The Spire. It feels like it attracts evil. "};
    private static final int newPurgecost = 100;
    
    
    public MegaStarOfAscension() {
        super(ID, NAME, DESC[0], new Texture(IMG), new Texture(OUTLINE), true);   
    }
    
    @Override
    public void updateDescription() {
        this.description = DESC[0];
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
