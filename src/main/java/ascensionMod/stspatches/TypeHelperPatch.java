package ascensionMod.stspatches;

import java.util.ArrayList;

import com.evacipated.cardcrawl.modthespire.lib.LineFinder;
import com.evacipated.cardcrawl.modthespire.lib.Matcher;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertLocator;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.patcher.PatchingException;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.TypeHelper;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import ascensionMod.AscensionMod;
import ascensionMod.UI.panels.RenameBox;
import javassist.CannotCompileException;
import javassist.CtBehavior;

@SpirePatch(clz = TypeHelper.class, method = "keyTyped", paramtypez = {char.class})
public class TypeHelperPatch {
	@SpireInsertPatch(locator = Locator.class)
	public static void Insert(TypeHelper __instance, final char character) {
		final String charStr = String.valueOf(character);
		AscensionMod.logger.info("key typed");
		RenameBox.textField += charStr;
	}
	
	private static class Locator extends SpireInsertLocator {
		public int[] Locate(CtBehavior ctMethodToPatch) throws CannotCompileException, PatchingException {
			Matcher finalMatcher = new Matcher.MethodCallMatcher(Character.class, "isDigit");
			int[] ret = LineFinder.findInOrder(ctMethodToPatch, new ArrayList<Matcher>(), finalMatcher);
			ret[0]++;
			return ret;
		}
	}
}
