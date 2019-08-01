package ascensionMod.stspatches.minus;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rewards.chests.AbstractChest;

import ascensionMod.AscensionMod;
import ascensionMod.UI.CharSelectScreenUI;

public class OpenChestPatch {
	@SpirePatch(clz = AbstractChest.class, method = "open")
	public static class OpenChest {
		public static void Prefix(AbstractChest __instance, boolean bossChest) {
			if (AscensionMod.AbsoluteAscensionLevel <= -20  || (AscensionMod.customAscensionRun && CharSelectScreenUI.ascScreen.negAscButtons.get(19).toggledOn)) {
				if (!bossChest)
					AbstractDungeon.getCurrRoom().addRelicToRewards(AbstractDungeon.returnRandomRelicTier());
			}
		}
	}
}
