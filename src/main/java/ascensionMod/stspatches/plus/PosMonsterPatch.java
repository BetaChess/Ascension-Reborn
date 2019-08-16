package ascensionMod.stspatches.plus;

import java.lang.reflect.Field;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.ByRef;
import com.evacipated.cardcrawl.modthespire.lib.LineFinder;
import com.evacipated.cardcrawl.modthespire.lib.Matcher;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertLocator;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.patcher.PatchingException;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ModHelper;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.MonsterRoom;

import com.megacrit.cardcrawl.monsters.beyond.*;
import com.megacrit.cardcrawl.monsters.city.*;
import com.megacrit.cardcrawl.monsters.ending.*;
import com.megacrit.cardcrawl.monsters.exordium.*;

import ascensionMod.AscensionMod;
import javassist.CannotCompileException;
import javassist.CtBehavior;

public class PosMonsterPatch {
	public static final Logger logger = LogManager.getLogger(PosMonsterPatch.class.getName());

	@SpirePatch(clz = Darkling.class, method = SpirePatch.CONSTRUCTOR, paramtypez = { float.class, float.class })
	@SpirePatch(clz = OrbWalker.class, method = SpirePatch.CONSTRUCTOR, paramtypez = { float.class, float.class })
	@SpirePatch(clz = SpikeSlime_M.class, method = SpirePatch.CONSTRUCTOR, paramtypez = { float.class, float.class })
	@SpirePatch(clz = SpikeSlime_L.class, method = SpirePatch.CONSTRUCTOR, paramtypez = { float.class, float.class })
	@SpirePatch(clz = Exploder.class, method = SpirePatch.CONSTRUCTOR, paramtypez = { float.class, float.class })
	@SpirePatch(clz = Repulsor.class, method = SpirePatch.CONSTRUCTOR, paramtypez = { float.class, float.class })
	@SpirePatch(clz = Spiker.class, method = SpirePatch.CONSTRUCTOR, paramtypez = { float.class, float.class })
	@SpirePatch(clz = BanditPointy.class, method = SpirePatch.CONSTRUCTOR, paramtypez = { float.class, float.class })
	@SpirePatch(clz = Chosen.class, method = SpirePatch.CONSTRUCTOR, paramtypez = { float.class, float.class })
	@SpirePatch(clz = ShelledParasite.class, method = SpirePatch.CONSTRUCTOR, paramtypez = { float.class, float.class })
	@SpirePatch(clz = SnakePlant.class, method = SpirePatch.CONSTRUCTOR, paramtypez = { float.class, float.class })
	@SpirePatch(clz = Snecko.class, method = SpirePatch.CONSTRUCTOR, paramtypez = { float.class, float.class })
	@SpirePatch(clz = AcidSlime_L.class, method = SpirePatch.CONSTRUCTOR, paramtypez = { float.class, float.class })
	@SpirePatch(clz = AcidSlime_M.class, method = SpirePatch.CONSTRUCTOR, paramtypez = { float.class, float.class })
	@SpirePatch(clz = OrbWalker.class, method = SpirePatch.CONSTRUCTOR, paramtypez = { float.class, float.class })
	@SpirePatch(clz = FungiBeast.class, method = SpirePatch.CONSTRUCTOR, paramtypez = { float.class, float.class })
	@SpirePatch(clz = GremlinFat.class, method = SpirePatch.CONSTRUCTOR, paramtypez = { float.class, float.class })
	@SpirePatch(clz = GremlinThief.class, method = SpirePatch.CONSTRUCTOR, paramtypez = { float.class, float.class })
	@SpirePatch(clz = GremlinTsundere.class, method = SpirePatch.CONSTRUCTOR, paramtypez = { float.class, float.class })
	@SpirePatch(clz = GremlinWarrior.class, method = SpirePatch.CONSTRUCTOR, paramtypez = { float.class, float.class })
	@SpirePatch(clz = GremlinWizard.class, method = SpirePatch.CONSTRUCTOR, paramtypez = { float.class, float.class })
	@SpirePatch(clz = LouseDefensive.class, method = SpirePatch.CONSTRUCTOR, paramtypez = { float.class, float.class })
	@SpirePatch(clz = LouseNormal.class, method = SpirePatch.CONSTRUCTOR, paramtypez = { float.class, float.class })
	@SpirePatch(clz = SlaverBlue.class, method = SpirePatch.CONSTRUCTOR, paramtypez = { float.class, float.class })
	@SpirePatch(clz = SlaverRed.class, method = SpirePatch.CONSTRUCTOR, paramtypez = { float.class, float.class })
	public static class NormalMonsterPatches_2 {
		@SpireInsertPatch(locator = Locator.class)
		public static void Insert(AbstractMonster __instance, final float x, final float y) {
			// logger.info("HEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEELP");
			if ((AbstractDungeon.ascensionLevel == 2 || AbstractDungeon.ascensionLevel == 0)
					&& AscensionMod.getCustomToggleState(7)) {
				AbstractDungeon.ascensionLevel = 7;
			} else if ((AbstractDungeon.ascensionLevel == 7 || AbstractDungeon.ascensionLevel == 0)
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

	@SpirePatch(clz = Cultist.class, method = SpirePatch.CONSTRUCTOR, paramtypez = { float.class, float.class,
			boolean.class })
	public static class CultistPatch {
		@SpireInsertPatch(locator = Locator.class)
		public static void Insert(AbstractMonster __instance, final float x, final float y, final boolean talk) {
			// logger.info("HEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEELP");
			if ((AbstractDungeon.ascensionLevel == 2 || AbstractDungeon.ascensionLevel == 0)
					&& AscensionMod.getCustomToggleState(7)) {
				AbstractDungeon.ascensionLevel = 7;
			} else if ((AbstractDungeon.ascensionLevel == 7 || AbstractDungeon.ascensionLevel == 0)
					&& AscensionMod.getCustomToggleState(2)) {
				AbstractDungeon.ascensionLevel = 2;
			}
		}

		public static void Prefix(AbstractMonster __instance, final float x, final float y, final boolean talk) {
			if (AscensionMod.getCustomToggleState(2)) {
				AbstractDungeon.ascensionLevel = 2;
			} else {
				if (AscensionMod.customAscensionRun)
				AbstractDungeon.ascensionLevel = 0;
			}
		}

		public static void Postfix(AbstractMonster __instance, final float x, final float y, final boolean talk) {
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
