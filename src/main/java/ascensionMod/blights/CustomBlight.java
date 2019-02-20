package ascensionMod.blights;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.blights.AbstractBlight;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.localization.BlightStrings;
import com.megacrit.cardcrawl.rooms.AbstractRoom;


public abstract class CustomBlight extends AbstractBlight {
	public static final Logger logger = LogManager.getLogger(CustomBlight.class.getName());
	
	protected BlightStrings blightstrings; 
	
    public CustomBlight(final String ID, final Texture imgTexture, final Texture imgOutline, final boolean unique) {
    	super(ID, CardCrawlGame.languagePack.getBlightString(ID).NAME, CardCrawlGame.languagePack.getBlightString(ID).DESCRIPTION[0], null, unique);
        
        this.img = imgTexture;
        this.outlineImg = imgOutline;
    }
    
    @Override
    public void updateDescription() {
        this.description = this.blightstrings.DESCRIPTION[0];
        this.tips.clear();
        this.tips.add(new PowerTip(this.name, this.description));
        this.initializeTips();
    }
    
    public void postBossCombat()
    {
    }
    
    public void onGainGold()
    {
    }
    
    public void onEnterRoom(final AbstractRoom room)
    {
    }

	public int onPlayerHeal(int healAmount) {
		return healAmount;
	}
	
	static public ArrayList<CustomBlight> getCustomBlights(ArrayList<AbstractBlight> blightList)
	{
		ArrayList<CustomBlight> retvals = new ArrayList<CustomBlight>();
		
		for (AbstractBlight b : blightList)
		{
			if(b instanceof CustomBlight)
			{
				retvals.add((CustomBlight) b);
			}
		}
		
		return retvals;
	}
}
