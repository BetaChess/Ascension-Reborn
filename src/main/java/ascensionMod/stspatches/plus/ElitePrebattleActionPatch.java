package ascensionMod.stspatches.plus;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.ending.SpireShield;
import com.megacrit.cardcrawl.monsters.ending.SpireSpear;
import com.megacrit.cardcrawl.monsters.exordium.GremlinWarrior;

import ascensionMod.AscensionMod;

public class ElitePrebattleActionPatch {
	@SpirePatch(clz = SpireShield.class, method = "usePreBattleAction")
	@SpirePatch(clz = SpireSpear.class, method = "usePreBattleAction")
	public static class PreBattlePatch {
		public static void Prefix(AbstractMonster __instance) {
			if (AscensionMod.getCustomToggleState(18)) {
				AbstractDungeon.ascensionLevel = 18;
			}
		}

		public static void Postfix(AbstractMonster __instance) {
			if (AscensionMod.customAscensionRun) {
				AbstractDungeon.ascensionLevel = 0;
			}
		}
	}
}
