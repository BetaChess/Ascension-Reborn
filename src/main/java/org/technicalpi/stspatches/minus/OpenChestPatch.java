package org.technicalpi.stspatches.minus;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rewards.chests.AbstractChest;

import org.technicalpi.AscensionMod;

public class OpenChestPatch {
	@SpirePatch(clz = AbstractChest.class, method = "open")
	public static class OpenChest {
		public static void Prefix(AbstractChest __instance, boolean bossChest) {
			if (AscensionMod.AbsoluteAscensionLevel <= -20  || AscensionMod.getCustomToggleState(-20)) {
				if (!bossChest)
					AbstractDungeon.getCurrRoom().addRelicToRewards(AbstractDungeon.returnRandomRelicTier());
			}
		}
	}
}
