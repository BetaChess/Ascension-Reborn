package ascensionMod.patches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.AbstractCard.CardType;
import com.megacrit.cardcrawl.cards.CardGroup;
 

public class getPurgeablePatch 
{
	public static int AL;
	 
	@SpirePatch(
		cls = "com.megacrit.cardcrawl.cards.CardGroup",
		method = "getPurgeableCards"
	)
	public static class PurgePatch{
		public static CardGroup Postfix(CardGroup __result, CardGroup __instance) {
			if(AL >= 5) {
				for(AbstractCard c : __instance.group) {
					if(c.type == CardType.CURSE) {
						__result.removeCard(c);
					}
				}
			}
			
			return __result;
		}
	}
}