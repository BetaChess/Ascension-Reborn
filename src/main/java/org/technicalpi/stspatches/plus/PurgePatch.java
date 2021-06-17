package org.technicalpi.stspatches.plus;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.cards.CardGroup.CardGroupType;

@SpirePatch(
		clz = com.megacrit.cardcrawl.cards.CardGroup.class,
		method = "getPurgeableCards"
	)
public class PurgePatch 
{
	
	public static SpireReturn<CardGroup> Prefix(CardGroup __instance)
	{
		final CardGroup retVal = new CardGroup(CardGroupType.UNSPECIFIED);
        for (final AbstractCard c : __instance.group) {
            if (!c.cardID.equals("Necronomicurse") && !c.cardID.equals("AscendersBane") && !c.cardID.equals("AscMod:ascendersbane")) {
                retVal.group.add(c);
            }
        }
        return SpireReturn.Return(retVal);
	}	
	
}
