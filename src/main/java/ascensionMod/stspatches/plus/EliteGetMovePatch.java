package ascensionMod.stspatches.plus;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.city.BookOfStabbing;
import com.megacrit.cardcrawl.monsters.exordium.GremlinNob;
import com.megacrit.cardcrawl.monsters.exordium.SpikeSlime_L;

import ascensionMod.AscensionMod;

public class EliteGetMovePatch {
	@SpirePatch(clz = BookOfStabbing.class, method = "getMove", paramtypez = { int.class })
	@SpirePatch(clz = GremlinNob.class, method = "getMove", paramtypez = { int.class })
	public static class GetMovePatches {
		public static void Prefix(AbstractMonster __instance, final int num) {
			if (AscensionMod.getCustomToggleState(18)) {
				AbstractDungeon.ascensionLevel = 18;
			}
		}

		public static void Postfix(AbstractMonster __instance, final int num) {
			if (AscensionMod.customAscensionRun) {
				AbstractDungeon.ascensionLevel = 0;
			}
		}
	}
}
