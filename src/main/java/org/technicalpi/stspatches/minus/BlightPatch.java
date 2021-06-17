package org.technicalpi.stspatches.minus;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.blights.AbstractBlight;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;

public class BlightPatch {
	@SpirePatch(clz = AbstractBlight.class, method = "obtain")
	public static class Obtain {
		public static SpireReturn<Void> Prefix(AbstractBlight __instance) {
			if (CardCrawlGame.trial != null && CardCrawlGame.trial.dailyModIDs().contains(CustomModPatch.BlightlessID))
				return SpireReturn.Return(null);
			return SpireReturn.Continue();
		}
	}

	@SpirePatch(clz = AbstractBlight.class, method = "instantObtain")
	public static class InstantObtain {
		public static SpireReturn<Void> Prefix(AbstractBlight __instance, AbstractPlayer p, int slot, boolean callOnEquip) {
			if (CardCrawlGame.trial != null && CardCrawlGame.trial.dailyModIDs().contains(CustomModPatch.BlightlessID))
				return SpireReturn.Return(null);
			return SpireReturn.Continue();
		}
	}

	@SpirePatch(clz = AbstractBlight.class, method = "spawn")
	public static class Spawn {
		public static SpireReturn<Void> Prefix(AbstractBlight __instance, float x, float y) {
			if (CardCrawlGame.trial != null && CardCrawlGame.trial.dailyModIDs().contains(CustomModPatch.BlightlessID))
				return SpireReturn.Return(null);
			return SpireReturn.Continue();
		}
	}
}
