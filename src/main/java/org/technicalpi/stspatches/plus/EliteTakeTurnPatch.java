package org.technicalpi.stspatches.plus;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.beyond.Nemesis;
import com.megacrit.cardcrawl.monsters.city.Taskmaster;
import com.megacrit.cardcrawl.monsters.ending.SpireShield;
import com.megacrit.cardcrawl.monsters.ending.SpireSpear;
import com.megacrit.cardcrawl.monsters.exordium.GremlinNob;

import org.technicalpi.AscensionMod;

public class EliteTakeTurnPatch {
	@SpirePatch(clz = Nemesis.class, method = "takeTurn")
	@SpirePatch(clz = Taskmaster.class, method = "takeTurn")
	@SpirePatch(clz = GremlinNob.class, method = "takeTurn")
	@SpirePatch(clz = SpireShield.class, method = "takeTurn")
	@SpirePatch(clz = SpireSpear.class, method = "takeTurn")
	public static class TurnPatch {
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
