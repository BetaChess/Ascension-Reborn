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
import com.megacrit.cardcrawl.monsters.beyond.AwakenedOne;
import com.megacrit.cardcrawl.monsters.beyond.Deca;
import com.megacrit.cardcrawl.monsters.beyond.Donu;
import com.megacrit.cardcrawl.monsters.beyond.TimeEater;
import com.megacrit.cardcrawl.monsters.city.BronzeAutomaton;
import com.megacrit.cardcrawl.monsters.city.BronzeOrb;
import com.megacrit.cardcrawl.monsters.city.Champ;
import com.megacrit.cardcrawl.monsters.city.TheCollector;
import com.megacrit.cardcrawl.monsters.city.TorchHead;
import com.megacrit.cardcrawl.monsters.ending.CorruptHeart;
import com.megacrit.cardcrawl.monsters.exordium.Hexaghost;
import com.megacrit.cardcrawl.monsters.exordium.SlimeBoss;
import com.megacrit.cardcrawl.monsters.exordium.TheGuardian;

import org.technicalpi.AscensionMod;
import javassist.CannotCompileException;
import javassist.CtBehavior;

public class BossAscPatch {

	@SpirePatch(clz = AwakenedOne.class, method = SpirePatch.CONSTRUCTOR, paramtypez = {float.class, float.class})
	public static class AwakenedPatch {
		public static void Prefix(AbstractMonster __instance, final float x, final float y) {
			if (AscensionMod.getCustomToggleState(9)) {
				AbstractDungeon.ascensionLevel = 9;
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
	}

	@SpirePatch(clz = Deca.class, method = SpirePatch.CONSTRUCTOR)
	@SpirePatch(clz = Donu.class, method = SpirePatch.CONSTRUCTOR)
	@SpirePatch(clz = TimeEater.class, method = SpirePatch.CONSTRUCTOR)
	@SpirePatch(clz = BronzeAutomaton.class, method = SpirePatch.CONSTRUCTOR)
	@SpirePatch(clz = SlimeBoss.class, method = SpirePatch.CONSTRUCTOR)
	@SpirePatch(clz = CorruptHeart.class, method = SpirePatch.CONSTRUCTOR)
	public static class _9_4Patch {
		@SpireInsertPatch(locator = Locator.class)
		public static void Insert(AbstractMonster __instance) {
			if ((AbstractDungeon.ascensionLevel == 9 || AbstractDungeon.ascensionLevel == 0)
					&& AscensionMod.getCustomToggleState(9)) {
				AbstractDungeon.ascensionLevel = 10;
			} else if ((AbstractDungeon.ascensionLevel == 10 || AbstractDungeon.ascensionLevel == 0)
					&& AscensionMod.getCustomToggleState(4)) {
				AbstractDungeon.ascensionLevel = 4;
			}
		}

		public static void Prefix(AbstractMonster __instance) {
			if (AscensionMod.getCustomToggleState(9)) {
				AbstractDungeon.ascensionLevel = 9;
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

	@SpirePatch(clz = Champ.class, method = SpirePatch.CONSTRUCTOR)
	public static class ChampPatch {
		public static void Prefix(AbstractMonster __instance) {
			if (AscensionMod.getCustomToggleState(19)) {
				AbstractDungeon.ascensionLevel = 19;
			} else if (AscensionMod.getCustomToggleState(9)) {
				AbstractDungeon.ascensionLevel = 9;
			} else if (AscensionMod.getCustomToggleState(4)) {
				AbstractDungeon.ascensionLevel = 4;
			} else {
				if (AscensionMod.customAscensionRun)
				AbstractDungeon.ascensionLevel = 0;
			}
		}

		public static void Postfix(AbstractMonster __instance) {
			if (AscensionMod.customAscensionRun) {
				if (AscensionMod.customAscensionRun)
				AbstractDungeon.ascensionLevel = 0;
			}
		}
	}
	
	@SpirePatch(clz = TheCollector.class, method = SpirePatch.CONSTRUCTOR)
	@SpirePatch(clz = Hexaghost.class, method = SpirePatch.CONSTRUCTOR)
	public static class _9_19_4Patch {
		@SpireInsertPatch(locator = Locator.class)
		public static void Insert(AbstractMonster __instance) {
			if (AbstractDungeon.ascensionLevel == 9 && AscensionMod.getCustomToggleState(9)) {
				AbstractDungeon.ascensionLevel = 10;
			} else if (AbstractDungeon.ascensionLevel == 10 || AbstractDungeon.ascensionLevel == 0) {
				if (AscensionMod.getCustomToggleState(19)) {
					AbstractDungeon.ascensionLevel = 19;
				} else if (AscensionMod.getCustomToggleState(4)) {
					AbstractDungeon.ascensionLevel = 4;
				}
			}
		}

		public static void Prefix(AbstractMonster __instance) {
			if (AscensionMod.getCustomToggleState(9)) {
				AbstractDungeon.ascensionLevel = 9;
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
	
	
	@SpirePatch(clz = TheGuardian.class, method = SpirePatch.CONSTRUCTOR)
	public static class _19_9_4Patch {
		@SpireInsertPatch(locator = Locator.class)
		public static void Insert(AbstractMonster __instance) {
			if (AbstractDungeon.ascensionLevel == 19 && AscensionMod.getCustomToggleState(19)) {
				AbstractDungeon.ascensionLevel = 19;
			} else if ((AbstractDungeon.ascensionLevel == 19 || AbstractDungeon.ascensionLevel == 0)
					&& AscensionMod.getCustomToggleState(9)) {
				AbstractDungeon.ascensionLevel = 9;
			} else if ((AbstractDungeon.ascensionLevel == 9 || AbstractDungeon.ascensionLevel == 0)
					&& AscensionMod.getCustomToggleState(4)) {
				AbstractDungeon.ascensionLevel = 4;
			}
		}

		public static void Prefix(AbstractMonster __instance) {
			if (AscensionMod.getCustomToggleState(19)) {
				AbstractDungeon.ascensionLevel = 19;
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
	
	@SpirePatch(clz = BronzeOrb.class, method = SpirePatch.CONSTRUCTOR, paramtypez = { float.class, float.class, int.class })
	public static class BronzeOrbPatch {
		public static void Prefix(AbstractMonster __instance, final float x, final float y, final int count) {
			if (AscensionMod.getCustomToggleState(9)) {
				AbstractDungeon.ascensionLevel = 9;
			}
		}

		public static void Postfix(AbstractMonster __instance, final float x, final float y, final int count) {
			if (AscensionMod.customAscensionRun) {
				AbstractDungeon.ascensionLevel = 0;
			}
		}
	}
	
	@SpirePatch(clz = TorchHead.class, method = SpirePatch.CONSTRUCTOR, paramtypez = { float.class, float.class})
	public static class TorchHeadPatch {
		public static void Prefix(AbstractMonster __instance, final float x, final float y) {
			if (AscensionMod.getCustomToggleState(9)) {
				AbstractDungeon.ascensionLevel = 9;
			}
		}

		public static void Postfix(AbstractMonster __instance, final float x, final float y) {
			if (AscensionMod.customAscensionRun) {
				AbstractDungeon.ascensionLevel = 0;
			}
		}
	}
	
	// Method Patches
	@SpirePatch(clz = AwakenedOne.class, method = "changeState", paramtypez = { String.class })
	public static class ChangeStatePatch {
		public static void Prefix(AbstractMonster __instance, final String key) {
			if (AscensionMod.getCustomToggleState(9)) {
				AbstractDungeon.ascensionLevel = 9;
			}
		}

		public static void Postfix(AbstractMonster __instance, final String key) {
			if (AscensionMod.customAscensionRun) {
				AbstractDungeon.ascensionLevel = 0;
			}
		}
	}
	
	
	@SpirePatch(clz = Deca.class, method = "usePreBattleAction")
	@SpirePatch(clz = Donu.class, method = "usePreBattleAction")
	@SpirePatch(clz = CorruptHeart.class, method = "usePreBattleAction")
	public static class PreBattleActionPatch {
		public static void Prefix(AbstractMonster __instance) {
			if (AscensionMod.getCustomToggleState(19)) {
				AbstractDungeon.ascensionLevel = 19;
			}
		}

		public static void Postfix(AbstractMonster __instance) {
			if (AscensionMod.customAscensionRun) {
				AbstractDungeon.ascensionLevel = 0;
			}
		}
	}
	
	@SpirePatch(clz = Deca.class, method = "takeTurn")
	@SpirePatch(clz = TimeEater.class, method = "takeTurn")
	@SpirePatch(clz = TheCollector.class, method = "takeTurn")
	@SpirePatch(clz = SlimeBoss.class, method = "takeTurn")
	public static class TakeTurnPatch {
		public static void Prefix(AbstractMonster __instance) {
			if (AscensionMod.getCustomToggleState(19)) {
				AbstractDungeon.ascensionLevel = 19;
			}
		}

		public static void Postfix(AbstractMonster __instance) {
			if (AscensionMod.customAscensionRun) {
				AbstractDungeon.ascensionLevel = 0;
			}
		}
	}
	
	@SpirePatch(clz = Deca.class, method = "getMove", paramtypez = { int.class })
	@SpirePatch(clz = BronzeAutomaton.class, method = "getMove", paramtypez = { int.class })
	@SpirePatch(clz = Champ.class, method = "getMove", paramtypez = { int.class })
	public static class GetMovePatch {
		public static void Prefix(AbstractMonster __instance, final int num) {
			if (AscensionMod.getCustomToggleState(19)) {
				AbstractDungeon.ascensionLevel = 19;
			}
		}

		public static void Postfix(AbstractMonster __instance, final int num) {
			if (AscensionMod.customAscensionRun) {
				AbstractDungeon.ascensionLevel = 0;
			}
		}
	}
	
	@SpirePatch(clz = TheGuardian.class, method = "useCloseUp")
	public static class UseCloseUpPatch {
		public static void Prefix(AbstractMonster __instance) {
			if (AscensionMod.getCustomToggleState(19)) {
				AbstractDungeon.ascensionLevel = 19;
			}
		}

		public static void Postfix(AbstractMonster __instance) {
			if (AscensionMod.customAscensionRun) {
				AbstractDungeon.ascensionLevel = 0;
			}
		}
	}
	
	
}
