package ascensionMod.stspatches.minus;

import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.modthespire.lib.ByRef;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.core.AbstractCreature;

import ascensionMod.AscensionMod;
import ascensionMod.UI.CharSelectScreenUI;

public class HealMorePatch {
	@SpirePatch(clz = AbstractCreature.class, method = "heal", paramtypez = { int.class, boolean.class })
	public static class HealMore {
		public static void Prefix(AbstractCreature __instance, @ByRef int[] healAmount, boolean showEffect) {
			if (AscensionMod.AbsoluteAscensionLevel <= -13  || AscensionMod.getCustomToggleState(-13)) {
				healAmount[0] = MathUtils.round(healAmount[0] * 1.4F);
			}
		}
	}
}
