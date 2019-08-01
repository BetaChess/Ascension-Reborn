package ascensionMod.stspatches.minus;

import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.modthespire.lib.ByRef;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.characters.AbstractPlayer;

import ascensionMod.AscensionMod;
import ascensionMod.UI.CharSelectScreenUI;

public class GainMoreGoldPatch {
	@SpirePatch(clz = AbstractPlayer.class, method = "gainGold")
	public static class GainMoreGold {
		public static void Prefix(AbstractPlayer __instance, @ByRef int[] amount) {
			if (AscensionMod.AbsoluteAscensionLevel <= -12  || (AscensionMod.customAscensionRun && CharSelectScreenUI.ascScreen.negAscButtons.get(11).toggledOn))
				amount[0] = MathUtils.round(amount[0] * 1.2F);
		}
	}
}
