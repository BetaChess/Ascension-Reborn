package org.technicalpi.stspatches.plus;

import java.util.ArrayList;

import com.evacipated.cardcrawl.modthespire.lib.LineFinder;
import com.evacipated.cardcrawl.modthespire.lib.Matcher;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertLocator;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.patcher.PatchingException;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.beyond.OrbWalker;
import com.megacrit.cardcrawl.monsters.beyond.Spiker;
import com.megacrit.cardcrawl.monsters.beyond.Transient;
import com.megacrit.cardcrawl.monsters.exordium.GremlinWarrior;
import com.megacrit.cardcrawl.monsters.exordium.LouseDefensive;
import com.megacrit.cardcrawl.monsters.exordium.LouseNormal;

import org.technicalpi.AscensionMod;
import javassist.CannotCompileException;
import javassist.CtBehavior;

public class PreBattleActionPatch {
	@SpirePatch(clz = OrbWalker.class, method = "usePreBattleAction")
	@SpirePatch(clz = Spiker.class, method = "usePreBattleAction")
	@SpirePatch(clz = Transient.class, method = "usePreBattleAction")
	@SpirePatch(clz = GremlinWarrior.class, method = "usePreBattleAction")
	public static class PreBattlePatch {
		public static void Prefix(AbstractMonster __instance) {
			if (AscensionMod.getCustomToggleState(17)) {
				AbstractDungeon.ascensionLevel = 17;
			}
		}

		public static void Postfix(AbstractMonster __instance) {
			if (AscensionMod.customAscensionRun) {
				AbstractDungeon.ascensionLevel = 0;
			}
		}
	}

	@SpirePatch(clz = LouseDefensive.class, method = "usePreBattleAction")
	@SpirePatch(clz = LouseNormal.class, method = "usePreBattleAction")
	public static class PreBattle17_7Patch {
		@SpireInsertPatch(locator = Locator.class)
		public static void Insert(AbstractMonster __instance) {
			// logger.info("HEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEELP");
			if ((AbstractDungeon.ascensionLevel == 17 || AbstractDungeon.ascensionLevel == 0)
					&& AscensionMod.getCustomToggleState(7)) {
				AbstractDungeon.ascensionLevel = 7;
			}
		}

		public static void Prefix(AbstractMonster __instance) {
			if (AscensionMod.getCustomToggleState(17)) {
				AbstractDungeon.ascensionLevel = 17;
			} else {
				if (AscensionMod.customAscensionRun)
				AbstractDungeon.ascensionLevel = 0;
			}
		}

		public static void Postfix(AbstractMonster __instance) {
			if (AscensionMod.customAscensionRun) {
				AbstractDungeon.ascensionLevel = 0;
			}
		}

		private static class Locator extends SpireInsertLocator {
			public int[] Locate(CtBehavior ctMethodToPatch) throws CannotCompileException, PatchingException {
				// Matcher finalMatcher = new Matcher.MethodCallMatcher(AbstractMonster.class,
				// "setHp");
				Matcher finalMatcher = new Matcher.FieldAccessMatcher(AbstractDungeon.class, "ascensionLevel");
				// Matcher finalMatcher = new Matcher.InstanceOfMatcher("if
				// (AbstractDungeon.ascensionLevel >= 2) {");

				return LineFinder.findAllInOrder(ctMethodToPatch, new ArrayList<Matcher>(), finalMatcher);
			}
		}
	}
}
