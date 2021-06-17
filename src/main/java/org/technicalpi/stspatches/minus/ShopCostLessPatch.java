package org.technicalpi.stspatches.minus;

import java.util.ArrayList;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.shop.ShopScreen;

import org.technicalpi.AscensionMod;

public class ShopCostLessPatch {
	@SpirePatch(clz = ShopScreen.class, method = "init")
	public static class ShopCostLess {
		public static void Postfix(ShopScreen __instance, ArrayList<AbstractCard> coloredCards, ArrayList<AbstractCard> colorlessCards) {
			if (AscensionMod.AbsoluteAscensionLevel <= -10 || AscensionMod.getCustomToggleState(-10)) {
				__instance.applyDiscount(0.75F, true);
			}
		}
	}
}
