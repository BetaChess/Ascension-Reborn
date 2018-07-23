package ascensionMod.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.relics.AbstractRelic;


public class StarOfAscension2 extends CustomRelic {
	public static final String ID = "StarOfAscension2";
    private static final String IMG = "relics/StarOfAscension.png";
    private static final String OutlineIMG = "relics/outline/StarOfAscension.png";
    
    public static void relicAction() {
    	ascensionMod.relics.StarOfAscension1.relicAction();
    }
    
    public void onEquip() {
    	relicAction();
    }
    
    public StarOfAscension2() {
        super(ID, new Texture(IMG), new Texture(OutlineIMG), RelicTier.STARTER, LandingSound.FLAT);
    }
    
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
    
    @Override
    public AbstractRelic makeCopy() {
        return new StarOfAscension2();
    }
}