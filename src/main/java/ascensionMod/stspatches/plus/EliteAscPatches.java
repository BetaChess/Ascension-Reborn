package ascensionMod.stspatches.plus;

import java.util.ArrayList;

import com.evacipated.cardcrawl.modthespire.lib.LineFinder;
import com.evacipated.cardcrawl.modthespire.lib.Matcher;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertLocator;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.patcher.PatchingException;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.beyond.GiantHead;
import com.megacrit.cardcrawl.monsters.beyond.Nemesis;
import com.megacrit.cardcrawl.monsters.beyond.Reptomancer;
import com.megacrit.cardcrawl.monsters.city.BanditLeader;
import com.megacrit.cardcrawl.monsters.city.BookOfStabbing;
import com.megacrit.cardcrawl.monsters.city.Byrd;
import com.megacrit.cardcrawl.monsters.city.Centurion;
import com.megacrit.cardcrawl.monsters.city.GremlinLeader;
import com.megacrit.cardcrawl.monsters.city.Healer;
import com.megacrit.cardcrawl.monsters.city.Mugger;
import com.megacrit.cardcrawl.monsters.city.Taskmaster;
import com.megacrit.cardcrawl.monsters.ending.SpireShield;
import com.megacrit.cardcrawl.monsters.ending.SpireSpear;
import com.megacrit.cardcrawl.monsters.exordium.GremlinNob;
import com.megacrit.cardcrawl.monsters.exordium.Lagavulin;
import com.megacrit.cardcrawl.monsters.exordium.Looter;
import com.megacrit.cardcrawl.monsters.exordium.Sentry;

import ascensionMod.AscensionMod;
import javassist.CannotCompileException;
import javassist.CtBehavior;

public class EliteAscPatches {
	@SpirePatch(clz = GiantHead.class, method = SpirePatch.CONSTRUCTOR)
	@SpirePatch(clz = Nemesis.class, method = SpirePatch.CONSTRUCTOR)
	@SpirePatch(clz = BookOfStabbing.class, method = SpirePatch.CONSTRUCTOR)
	@SpirePatch(clz = SpireShield.class, method = SpirePatch.CONSTRUCTOR)
	@SpirePatch(clz = SpireSpear.class, method = SpirePatch.CONSTRUCTOR)
	public static class _8_3Patch {
		@SpireInsertPatch(locator = Locator.class)
		public static void Insert(AbstractMonster __instance) {
			if ((AbstractDungeon.ascensionLevel == 8 || AbstractDungeon.ascensionLevel == 0)
					&& AscensionMod.getCustomToggleState(8)) {
				AbstractDungeon.ascensionLevel = 9;
			} else if ((AbstractDungeon.ascensionLevel == 9 || AbstractDungeon.ascensionLevel == 0)
					&& AscensionMod.getCustomToggleState(3)) {
				AbstractDungeon.ascensionLevel = 3;
			}
		}

		public static void Prefix(AbstractMonster __instance) {
			if (AscensionMod.getCustomToggleState(8)) {
				AbstractDungeon.ascensionLevel = 8;
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
	
	@SpirePatch(clz = GremlinNob.class, method = SpirePatch.CONSTRUCTOR, paramtypez = {float.class, float.class, boolean.class})
	public static class GremlinPatch {
		@SpireInsertPatch(locator = Locator.class)
		public static void Insert(AbstractMonster __instance, final float x, final float y, final boolean setVuln) {
			if ((AbstractDungeon.ascensionLevel == 8 || AbstractDungeon.ascensionLevel == 0)
					&& AscensionMod.getCustomToggleState(8)) {
				AbstractDungeon.ascensionLevel = 9;
			} else if ((AbstractDungeon.ascensionLevel == 9 || AbstractDungeon.ascensionLevel == 0)
					&& AscensionMod.getCustomToggleState(3)) {
				AbstractDungeon.ascensionLevel = 3;
			}
		}

		public static void Prefix(AbstractMonster __instance, final float x, final float y, final boolean setVuln) {
			if (AscensionMod.getCustomToggleState(8)) {
				AbstractDungeon.ascensionLevel = 8;
			} else {
				if (AscensionMod.customAscensionRun)
				AbstractDungeon.ascensionLevel = 0;
			}
		}

		public static void Postfix(AbstractMonster __instance, final float x, final float y, final boolean setVuln) {
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

	@SpirePatch(clz = Reptomancer.class, method = SpirePatch.CONSTRUCTOR)
	public static class _18_8_3Patch {
		@SpireInsertPatch(locator = Locator.class)
		public static void Insert(AbstractMonster __instance) {
			// logger.info("HEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEELP");
			if (AbstractDungeon.ascensionLevel == 18 && AscensionMod.getCustomToggleState(18)) {
				AbstractDungeon.ascensionLevel = 19;
			} else if ((AbstractDungeon.ascensionLevel == 19 || AbstractDungeon.ascensionLevel == 0)
					&& AscensionMod.getCustomToggleState(8)) {
				AbstractDungeon.ascensionLevel = 8;
			} else if ((AbstractDungeon.ascensionLevel == 8 || AbstractDungeon.ascensionLevel == 0)
					&& AscensionMod.getCustomToggleState(3)) {
				AbstractDungeon.ascensionLevel = 3;
			}
		}

		public static void Prefix(AbstractMonster __instance) {
			if (AscensionMod.getCustomToggleState(18)) {
				AbstractDungeon.ascensionLevel = 18;
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
	
	@SpirePatch(clz = Lagavulin.class, method = SpirePatch.CONSTRUCTOR, paramtypez = {boolean.class})
	public static class _8_3_18Patch {
		@SpireInsertPatch(locator = Locator.class)
		public static void Insert(AbstractMonster __instance, boolean setAsleep) {
			if (AbstractDungeon.ascensionLevel == 8 && AscensionMod.getCustomToggleState(8)) {
				AbstractDungeon.ascensionLevel = 9;
			} else if ((AbstractDungeon.ascensionLevel == 9 || AbstractDungeon.ascensionLevel == 0)
					&& AscensionMod.getCustomToggleState(3)) {
				AbstractDungeon.ascensionLevel = 3;
			} else if ((AbstractDungeon.ascensionLevel == 3 || AbstractDungeon.ascensionLevel == 0)
					&& AscensionMod.getCustomToggleState(18)) {
				AbstractDungeon.ascensionLevel = 18;
			}
		}

		public static void Prefix(AbstractMonster __instance, boolean setAsleep) {
			if (AscensionMod.getCustomToggleState(8)) {
				AbstractDungeon.ascensionLevel = 8;
			} else {
				if (AscensionMod.customAscensionRun)
				AbstractDungeon.ascensionLevel = 0;
			}
		}

		public static void Postfix(AbstractMonster __instance, boolean setAsleep) {
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
	
	@SpirePatch(clz = Sentry.class, method = SpirePatch.CONSTRUCTOR, paramtypez = {float.class, float.class})
	public static class _8_3_18FFPatch {
		@SpireInsertPatch(locator = Locator.class)
		public static void Insert(AbstractMonster __instance, final float x, final float y) {
			if (AbstractDungeon.ascensionLevel == 8 && AscensionMod.getCustomToggleState(8)) {
				AbstractDungeon.ascensionLevel = 9;
			} else if ((AbstractDungeon.ascensionLevel == 9 || AbstractDungeon.ascensionLevel == 0)
					&& AscensionMod.getCustomToggleState(3)) {
				AbstractDungeon.ascensionLevel = 3;
			} else if ((AbstractDungeon.ascensionLevel == 3 || AbstractDungeon.ascensionLevel == 0)
					&& AscensionMod.getCustomToggleState(18)) {
				AbstractDungeon.ascensionLevel = 18;
			}
		}

		public static void Prefix(AbstractMonster __instance, final float x, final float y) {
			if (AscensionMod.getCustomToggleState(8)) {
				AbstractDungeon.ascensionLevel = 8;
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
	
	@SpirePatch(clz = GremlinLeader.class, method = SpirePatch.CONSTRUCTOR)
	public static class _8_18_3Patch {
		@SpireInsertPatch(locator = Locator.class)
		public static void Insert(AbstractMonster __instance) {
			if (AbstractDungeon.ascensionLevel == 8 && AscensionMod.getCustomToggleState(8)) {
				AbstractDungeon.ascensionLevel = 9;
			} else if (AbstractDungeon.ascensionLevel == 9 || AbstractDungeon.ascensionLevel == 0) {
				if (AscensionMod.getCustomToggleState(18)) {
					AbstractDungeon.ascensionLevel = 18;
				} else if (AscensionMod.getCustomToggleState(3)) {
					AbstractDungeon.ascensionLevel = 3;
				}
			}
		}

		public static void Prefix(AbstractMonster __instance) {
			if (AscensionMod.getCustomToggleState(8)) {
				AbstractDungeon.ascensionLevel = 8;
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
	
	@SpirePatch(clz = Taskmaster.class, method = SpirePatch.CONSTRUCTOR, paramtypez = {float.class, float.class})
	public static class _8_18_3FFPatch {
		@SpireInsertPatch(locator = Locator.class)
		public static void Insert(AbstractMonster __instance, final float x, final float y) {
			if (AbstractDungeon.ascensionLevel == 8 && AscensionMod.getCustomToggleState(8)) {
				AbstractDungeon.ascensionLevel = 9;
			} else if (AbstractDungeon.ascensionLevel == 9 || AbstractDungeon.ascensionLevel == 0) {
				if (AscensionMod.getCustomToggleState(18)) {
					AbstractDungeon.ascensionLevel = 18;
				} else if (AscensionMod.getCustomToggleState(3)) {
					AbstractDungeon.ascensionLevel = 3;
				}
			}
		}

		public static void Prefix(AbstractMonster __instance, final float x, final float y) {
			if (AscensionMod.getCustomToggleState(8)) {
				AbstractDungeon.ascensionLevel = 8;
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
}
