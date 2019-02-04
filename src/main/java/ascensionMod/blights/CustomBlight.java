package ascensionMod.blights;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.blights.AbstractBlight;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

public abstract class CustomBlight extends AbstractBlight {
	
    public CustomBlight(final String ID, final String name, final String desc, final Texture imgTexture, final Texture imgOutline, final boolean unique) {
        super(ID, name, desc, null, unique);
        
        this.img = imgTexture;
        this.outlineImg = imgOutline;
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
