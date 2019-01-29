package ascensionMod.stspatches.minus;

import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import ascensionMod.AscensionMod;

public class PlayerInitPatch {
	@SpirePatch(clz = AbstractDungeon.class, method = "dungeonTransitionSetup")
	public static class PlayerInit {
		@SpireInsertPatch(rloc = 46)
		public static void Insert() {
			if (AscensionMod.AbsoluteAscensionLevel <= -9) {
				AbstractDungeon.player.increaseMaxHp(AbstractDungeon.player.getAscensionMaxHPLoss(), false);
			}
			if (AscensionMod.AbsoluteAscensionLevel <= -18) {
				AbstractDungeon.player.energy.energyMaster++;
			}
			if (AscensionMod.AbsoluteAscensionLevel <= -19) {
				AbstractDungeon.player.masterHandSize++;
			}
			if (AscensionMod.AbsoluteAscensionLevel <= -20) {
				for (AbstractCard c : AbstractDungeon.player.masterDeck.group)
					c.upgrade();
			}
		}
	}
}
