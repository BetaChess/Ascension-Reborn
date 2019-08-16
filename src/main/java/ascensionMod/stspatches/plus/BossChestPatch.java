	package ascensionMod.stspatches.plus;

import java.lang.reflect.Field;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.blights.AbstractBlight;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.rewards.chests.BossChest;
import com.megacrit.cardcrawl.rooms.TreasureRoomBoss;
import com.megacrit.cardcrawl.screens.select.BossRelicSelectScreen;

import ascensionMod.AscensionMod;
import ascensionMod.UI.CharSelectScreenUI;
import ascensionMod.blights.CursedFlame;
import ascensionMod.stspatches.AscensionPatches;

public class BossChestPatch {
	public static final Logger logger = LogManager.getLogger(AscensionPatches.class.getName());
	public static boolean hasRunOnce = false;
	
	
	@SpirePatch(
		clz = BossChest.class,
		method = "open"
	)
	public static class BossChestOpenPatch
	{
		@SuppressWarnings("rawtypes")
		public static SpireReturn Prefix(BossChest __instance, final boolean bossChest)
		{
			if((!(AscensionMod.AbsoluteAscensionLevel < 25) || AscensionMod.getCustomToggleState(25)) && AbstractDungeon.actNum < 7)
			{
				if(CursedFlame.count == 0)
				{
					__instance.relics.clear();
				}
				
				logger.info("Ascension Reborn debug information. (State: open)");
				logger.info(CursedFlame.count);
				logger.info(AbstractPlayer.customMods.contains("Blight Chests"));
				logger.info(AbstractDungeon.actNum);

				
				if(CursedFlame.count == 1)
				{
					
					logger.info("Getting relics");
					while(AbstractPlayer.customMods.contains("Blight Chests"))
					{
						AbstractPlayer.customMods.remove("Blight Chests");
					}
					
					if(__instance.relics.size() != 3)
					{
						__instance.blights.clear();
						__instance.relics.clear();
			            for (int i = 0; i < 3; ++i) {
			                __instance.relics.add(AbstractDungeon.returnRandomRelic(AbstractRelic.RelicTier.BOSS));
			            }
					}
		            
		            CursedFlame.count = 1;
					
		            return SpireReturn.Continue();
				}
				else
				{
					__instance.relics.clear();
					return SpireReturn.Continue();
				}
			}
			else
			{
				return SpireReturn.Continue();
			}
		}
	}
	
	@SpirePatch(
		clz = BossRelicSelectScreen.class,
		method = "blightObtainLogic"
	)
	public static class BlightObtainPatch
	{
		public static void Postfix(BossRelicSelectScreen __instance, final AbstractBlight b)
		{
			if(!(AscensionMod.AbsoluteAscensionLevel < 25)  || (AscensionMod.customAscensionRun && CharSelectScreenUI.ascScreen.posAscButtons.get(24).toggledOn) && AbstractDungeon.actNum < 7)
			{
				hasRunOnce = !hasRunOnce;
				
				logger.info("WELL THEN");
				logger.info(hasRunOnce);
				logger.info(CursedFlame.count);
				logger.info(AbstractPlayer.customMods.get(0));
				
				if(CursedFlame.count == 0)
				{
					Field isDoneF = null;
					try {
						isDoneF = BossRelicSelectScreen.class.getDeclaredField("isDone");
					} catch (NoSuchFieldException e) {
						e.printStackTrace();
					} catch (SecurityException e) {
						e.printStackTrace();
					}
	
					
					isDoneF.setAccessible(true);
					//String x = sb.toString();
					try {
						isDoneF.set(__instance, false);
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
					
					CursedFlame.count++;
					final TreasureRoomBoss r = (TreasureRoomBoss)AbstractDungeon.getCurrRoom();
		            r.chest.close();
				}
			}
		}
	}
	
	@SpirePatch(
		clz = BossRelicSelectScreen.class,
		method = "relicObtainLogic"
	)
	public static class relicObtainPatch
	{
		public static void Postfix(BossRelicSelectScreen __instance, final AbstractRelic r)
		{
			if(!(AscensionMod.AbsoluteAscensionLevel < 25) || (AscensionMod.customAscensionRun && CharSelectScreenUI.ascScreen.posAscButtons.get(24).toggledOn) && AbstractDungeon.actNum < 7)
			{
				AbstractPlayer.customMods.add("Blight Chests");
			}
		}
	}
}
