package ascensionMod.stspatches.minus;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import ascensionMod.AscensionMod;

public class PlayerInitPatch {
	@SpirePatch(clz = AbstractDungeon.class, method = "dungeonTransitionSetup")
	public static class PlayerInit {
		public static void Postfix() {
			if (AscensionMod.AbsoluteAscensionLevel <= -9) {
				AbstractDungeon.player.increaseMaxHp(AbstractDungeon.player.getAscensionMaxHPLoss(), false);
			}
			if (AscensionMod.AbsoluteAscensionLevel <= -18) {
				AbstractDungeon.player.energy.energyMaster++;
			}
			if (AscensionMod.AbsoluteAscensionLevel <= -19) {
				AbstractDungeon.player.masterHandSize++;
			}
		}
	}
	
	@SpirePatch(clz = AbstractPlayer.class, method = "initializeStarterDeck")
	public static class postInit
	{
		public static void Postfix(AbstractPlayer __instance) 
		{
			if (AscensionMod.AbsoluteAscensionLevel <= -20) {
				for (AbstractCard c : __instance.masterDeck.group)
					c.upgrade();
			}
		}
	}
}
