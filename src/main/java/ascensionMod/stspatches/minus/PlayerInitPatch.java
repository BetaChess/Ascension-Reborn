package ascensionMod.stspatches.minus;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import ascensionMod.AscensionMod;
import ascensionMod.UI.CharSelectScreenUI;

public class PlayerInitPatch {
	/*@SpirePatch(clz = AbstractDungeon.class, method = "dungeonTransitionSetup")
	public static class PlayerInit {
		public static void Postfix() {
			
		}
	}*/
	
	@SpirePatch(clz = AbstractPlayer.class, method = "initializeStarterDeck")
	public static class postInit
	{
		public static void Postfix(AbstractPlayer __instance) 
		{
			if (AscensionMod.AbsoluteAscensionLevel <= -20 || AscensionMod.getCustomToggleState(-20)) {
				for (AbstractCard c : __instance.masterDeck.group)
					c.upgrade();
			}
		}
	}
}
