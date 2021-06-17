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
import com.megacrit.cardcrawl.monsters.beyond.Maw;
import com.megacrit.cardcrawl.monsters.beyond.SpireGrowth;
import com.megacrit.cardcrawl.monsters.beyond.Transient;
import com.megacrit.cardcrawl.monsters.beyond.WrithingMass;
import com.megacrit.cardcrawl.monsters.city.SphericGuardian;
import com.megacrit.cardcrawl.monsters.exordium.AcidSlime_S;
import com.megacrit.cardcrawl.monsters.exordium.SpikeSlime_S;

import org.technicalpi.AscensionMod;
import javassist.CannotCompileException;
import javassist.CtBehavior;

public class PosSpecialMonsterPatches {

	@SpirePatch(clz = SpireGrowth.class, method = SpirePatch.CONSTRUCTOR)
	@SpirePatch(clz = WrithingMass.class, method = SpirePatch.CONSTRUCTOR)
	public static class NoParamConstPatch {
		@SpireInsertPatch(locator = Locator.class)
		public static void Insert(AbstractMonster __instance) {
			// logger.info("HEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEELP");
			if ((AbstractDungeon.ascensionLevel == 2 || AbstractDungeon.ascensionLevel == 0)
					&& AscensionMod.getCustomToggleState(7)) {
				AbstractDungeon.ascensionLevel = 7;
			} else if ((AbstractDungeon.ascensionLevel == 7 || AbstractDungeon.ascensionLevel == 0)
					&& AscensionMod.getCustomToggleState(2)) {
				AbstractDungeon.ascensionLevel = 2;
			}
		}

		public static void Prefix(AbstractMonster __instance) {
			if (AscensionMod.getCustomToggleState(2)) {
				AbstractDungeon.ascensionLevel = 2;
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

				return LineFinder.findInOrder(ctMethodToPatch, new ArrayList<Matcher>(), finalMatcher);
			}
		}
	}

	@SpirePatch(clz = AcidSlime_S.class, method = SpirePatch.CONSTRUCTOR, paramtypez = { float.class, float.class,
			int.class })
	@SpirePatch(clz = SpikeSlime_S.class, method = SpirePatch.CONSTRUCTOR, paramtypez = { float.class, float.class,
			int.class })
	public static class PoisonConstPatch {
		@SpireInsertPatch(locator = Locator.class)
		public static void Insert(AbstractMonster __instance, final float x, final float y, int poisonAmount) {
			// logger.info("HEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEELP");
			if ((AbstractDungeon.ascensionLevel == 2 || AbstractDungeon.ascensionLevel == 0)
					&& AscensionMod.getCustomToggleState(7)) {
				AbstractDungeon.ascensionLevel = 7;
			} else if ((AbstractDungeon.ascensionLevel == 7 || AbstractDungeon.ascensionLevel == 0)
					&& AscensionMod.getCustomToggleState(2)) {
				AbstractDungeon.ascensionLevel = 2;
			}
		}

		public static void Prefix(AbstractMonster __instance, final float x, final float y, int poisonAmount) {
			if (AscensionMod.getCustomToggleState(2)) {
				AbstractDungeon.ascensionLevel = 2;
			} else {
				if (AscensionMod.customAscensionRun)
				AbstractDungeon.ascensionLevel = 0;
			}
		}

		public static void Postfix(AbstractMonster __instance, final float x, final float y, int poisonAmount) {
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

	@SpirePatch(clz = Maw.class, method = SpirePatch.CONSTRUCTOR, paramtypez = { float.class, float.class })
	public static class _17_2Patch {
		@SpireInsertPatch(locator = Locator.class)
		public static void Insert(AbstractMonster __instance, final float x, final float y) {
			// logger.info("HEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEELP");
			if ((AbstractDungeon.ascensionLevel == 2 || AbstractDungeon.ascensionLevel == 0)
					&& AscensionMod.getCustomToggleState(17)) {
				AbstractDungeon.ascensionLevel = 17;
			} else if ((AbstractDungeon.ascensionLevel == 17 || AbstractDungeon.ascensionLevel == 0)
					&& AscensionMod.getCustomToggleState(2)) {
				AbstractDungeon.ascensionLevel = 2;
			}
		}

		public static void Prefix(AbstractMonster __instance, final float x, final float y) {
			if (AscensionMod.getCustomToggleState(2)) {
				AbstractDungeon.ascensionLevel = 2;
			} else {
				if (AscensionMod.customAscensionRun)
				AbstractDungeon.ascensionLevel = 0;
			}
		}

		public static void Postfix(AbstractMonster __instance, final float x, final float y) {
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

	@SpirePatch(clz = Transient.class, method = SpirePatch.CONSTRUCTOR)
	public static class TransientPatch {
		public static void Prefix(AbstractMonster __instance) {
			if (AscensionMod.getCustomToggleState(2)) {
				AbstractDungeon.ascensionLevel = 2;
			}
		}

		public static void Postfix(AbstractMonster __instance) {
			if (AscensionMod.customAscensionRun) {
				AbstractDungeon.ascensionLevel = 0;
			}
		}
	}
	
	@SpirePatch(clz = SphericGuardian.class, method = SpirePatch.CONSTRUCTOR, paramtypez = { float.class, float.class })
	public static class SphericGuardianPatch {
		public static void Prefix(AbstractMonster __instance, final float x, final float y) {
			if (AscensionMod.getCustomToggleState(2)) {
				AbstractDungeon.ascensionLevel = 2;
			}
		}

		public static void Postfix(AbstractMonster __instance, final float x, final float y) {
			if (AscensionMod.customAscensionRun) {
				AbstractDungeon.ascensionLevel = 0;
			}
		}
	}

}
