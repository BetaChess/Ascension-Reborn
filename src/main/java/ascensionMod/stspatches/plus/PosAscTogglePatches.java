package ascensionMod.stspatches.plus;

import java.lang.reflect.Field;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.modthespire.lib.ByRef;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ModHelper;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.screens.custom.CustomModeScreen;

import ascensionMod.AscensionMod;
import ascensionMod.UI.CharSelectScreenUI;

public class PosAscTogglePatches 
{
	public static final Logger logger = LogManager.getLogger(PosAscTogglePatches.class.getName());
	
	@SpirePatch(
		clz = AbstractDungeon.class, 
		method = "generateRoomTypes"
	)
	public static class MoreElites 
	{
		
		@SpireInsertPatch(
			rloc = 22,
			localvars = {"eliteCount"}
		)
		public static void Insert(final ArrayList<AbstractRoom> roomList, final int availableRoomCount, @ByRef int[] eliteCount) 
		{
			if (!ModHelper.isModEnabled("Elite Swarm") && AscensionMod.getCustomToggleState(1))
			{
				// Field get, start
				Field eliteRoomChanceF = null;
				try {
					eliteRoomChanceF = AbstractDungeon.class.getDeclaredField("eliteRoomChance");
				} catch (NoSuchFieldException e) {
					e.printStackTrace();
				} catch (SecurityException e) {
					e.printStackTrace();
				}
				eliteRoomChanceF.setAccessible(true);
				// Field get, end
				
				float eliteRoomChance = 0;
				try {
					eliteRoomChance = (float)eliteRoomChanceF.get(AbstractDungeon.class);
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
				
				eliteCount[0] = Math.round(availableRoomCount * eliteRoomChance * 1.6f);
			}
		}
	}
}
