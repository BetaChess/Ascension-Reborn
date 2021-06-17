package org.technicalpi.stspatches.plus;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.evacipated.cardcrawl.modthespire.lib.LineFinder;
import com.evacipated.cardcrawl.modthespire.lib.Matcher;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertLocator;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.patcher.PatchingException;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.city.BanditBear;
import com.megacrit.cardcrawl.monsters.city.BanditLeader;
import com.megacrit.cardcrawl.monsters.city.Byrd;
import com.megacrit.cardcrawl.monsters.city.Centurion;
import com.megacrit.cardcrawl.monsters.city.Healer;
import com.megacrit.cardcrawl.monsters.city.Mugger;
import com.megacrit.cardcrawl.monsters.exordium.JawWorm;
import com.megacrit.cardcrawl.monsters.exordium.Looter;

import org.technicalpi.AscensionMod;
import javassist.CannotCompileException;
import javassist.CtBehavior;

public class TripleNormAscConstructorPatches {
	public static final Logger logger = LogManager.getLogger(TripleNormAscConstructorPatches.class.getName());

	// Why is there so little consistency? ;-;

	@SpirePatch(clz = BanditBear.class, method = SpirePatch.CONSTRUCTOR, paramtypez = { float.class, float.class })
	public static class BearPatch {
		@SpireInsertPatch(locator = Locator.class)
		public static void Insert(AbstractMonster __instance, final float x, final float y) {
			// logger.info("HEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEELP");
			if (AbstractDungeon.ascensionLevel == 7 && AscensionMod.getCustomToggleState(7)) {
				AbstractDungeon.ascensionLevel = 8;
			} else if ((AbstractDungeon.ascensionLevel == 8 || AbstractDungeon.ascensionLevel == 0)
					&& AscensionMod.getCustomToggleState(2)) {
				AbstractDungeon.ascensionLevel = 2;
			} else if ((AbstractDungeon.ascensionLevel == 2 || AbstractDungeon.ascensionLevel == 0)
					&& AscensionMod.getCustomToggleState(17)) {
				AbstractDungeon.ascensionLevel = 17;
			}
		}

		public static void Prefix(AbstractMonster __instance, final float x, final float y) {
			logger.info(AscensionMod.getCustomToggleState(7));
			if (AscensionMod.getCustomToggleState(7)) {
				AbstractDungeon.ascensionLevel = 7;
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

	@SpirePatch(clz = BanditLeader.class, method = SpirePatch.CONSTRUCTOR, paramtypez = { float.class, float.class })
	@SpirePatch(clz = Mugger.class, method = SpirePatch.CONSTRUCTOR, paramtypez = { float.class, float.class })
	@SpirePatch(clz = Looter.class, method = SpirePatch.CONSTRUCTOR, paramtypez = { float.class, float.class })
	public static class _17_7_2Patch {
		@SpireInsertPatch(locator = Locator.class)
		public static void Insert(AbstractMonster __instance, final float x, final float y) {
			// logger.info("HEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEELP");
			if (AbstractDungeon.ascensionLevel == 17 && AscensionMod.getCustomToggleState(17)) {
				AbstractDungeon.ascensionLevel = 18;
			} else if ((AbstractDungeon.ascensionLevel == 18 || AbstractDungeon.ascensionLevel == 0)
					&& AscensionMod.getCustomToggleState(7)) {
				AbstractDungeon.ascensionLevel = 7;
			} else if ((AbstractDungeon.ascensionLevel == 7 || AbstractDungeon.ascensionLevel == 0)
					&& AscensionMod.getCustomToggleState(2)) {
				AbstractDungeon.ascensionLevel = 2;
			}
		}

		public static void Prefix(AbstractMonster __instance, final float x, final float y) {
			if (AscensionMod.getCustomToggleState(17)) {
				AbstractDungeon.ascensionLevel = 17;
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

	@SpirePatch(clz = Byrd.class, method = SpirePatch.CONSTRUCTOR, paramtypez = { float.class, float.class })
	@SpirePatch(clz = Centurion.class, method = SpirePatch.CONSTRUCTOR, paramtypez = { float.class, float.class })
	@SpirePatch(clz = Healer.class, method = SpirePatch.CONSTRUCTOR, paramtypez = { float.class, float.class })
	public static class _7_17_2Patch {
		@SpireInsertPatch(locator = Locator.class)
		public static void Insert(AbstractMonster __instance, final float x, final float y) {
			if (AbstractDungeon.ascensionLevel == 7 && AscensionMod.getCustomToggleState(7)) {
				AbstractDungeon.ascensionLevel = 8;
			} else if (AbstractDungeon.ascensionLevel == 8 || AbstractDungeon.ascensionLevel == 0) {
				if (AscensionMod.getCustomToggleState(17)) {
					AbstractDungeon.ascensionLevel = 17;
				} else if (AscensionMod.getCustomToggleState(2)) {
					AbstractDungeon.ascensionLevel = 2;
				}
			}
		}

		public static void Prefix(AbstractMonster __instance, final float x, final float y) {
			if (AscensionMod.getCustomToggleState(7)) {
				AbstractDungeon.ascensionLevel = 7;
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

	@SpirePatch(clz = JawWorm.class, method = SpirePatch.CONSTRUCTOR, paramtypez = { float.class, float.class,
			boolean.class })
	public static class JawWormPatch {
		@SpireInsertPatch(locator = Locator.class)
		public static void Insert(AbstractMonster __instance, final float x, final float y, final boolean hard) {
			// logger.info("HEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEELP");
			logger.info("HEEEEEEEEEEEEEEEEEEEEELLLLLLLLOOOOOOOOO: " + AscensionMod.getCustomToggleState(2));
			logger.info("HEEEEEEEEEEEEEEEEEEEEELLLLLLLLOOOOOOOOO: " + AscensionMod.getCustomToggleState(7));
			logger.info("HEEEEEEEEEEEEEEEEEEEEELLLLLLLLOOOOOOOOO: " + AscensionMod.getCustomToggleState(17));
			logger.info("HEEEEEEEEEEEEEEEEEEEEELLLLLLLLOOOOOOOOO: " + AbstractDungeon.ascensionLevel);

			if (AbstractDungeon.ascensionLevel == 7 && AscensionMod.getCustomToggleState(7)) {
				AbstractDungeon.ascensionLevel = 8;
			} else if (AbstractDungeon.ascensionLevel == 8 || AbstractDungeon.ascensionLevel == 0) {
				logger.info("HEEEEEEEEEEEEEEEEEEEEELLLLLLLLOOOOOOOOO: " + AscensionMod.getCustomToggleState(17));

				if (AscensionMod.getCustomToggleState(17)) {
					AbstractDungeon.ascensionLevel = 17;
				} else if (AscensionMod.getCustomToggleState(2)) {
					AbstractDungeon.ascensionLevel = 2;
				}
			}
		}

		public static void Prefix(AbstractMonster __instance, final float x, final float y, final boolean hard) {
			if (AscensionMod.getCustomToggleState(7)) {
				AbstractDungeon.ascensionLevel = 7;
			} else {
				if (AscensionMod.customAscensionRun)
				AbstractDungeon.ascensionLevel = 0;
			}
		}

		public static void Postfix(AbstractMonster __instance, final float x, final float y, final boolean hard) {
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
