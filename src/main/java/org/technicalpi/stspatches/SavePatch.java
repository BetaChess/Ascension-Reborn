package org.technicalpi.stspatches;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.saveAndContinue.SaveAndContinue;
import com.megacrit.cardcrawl.saveAndContinue.SaveFile;

import org.technicalpi.AscensionMod;
import org.technicalpi.blights.CursedFlame;

public class SavePatch {
	@SpirePatch(
		clz = SaveAndContinue.class,
		method = "save"
	)
	public static class BossChestOpenPatch
	{
		public static void Prefix(final SaveFile save)
		{
			if(AscensionMod.AbsoluteAscensionLevel >= 25)
			{
				if(!(AbstractPlayer.customMods.contains("Blight Chests")))
				{
					AbstractPlayer.customMods.add("Blight Chests");
				}
			}
		}
	}
	
	@SpirePatch(
		clz = CardCrawlGame.class,
		method = "loadPlayerSave"
	)
	public static class loadSavePatch
	{
		public static void Postfix(CardCrawlGame __instance, final AbstractPlayer p)
		{
			AscensionMod.AbsoluteAscensionLevel = AbstractDungeon.ascensionLevel;
			CursedFlame.count = 0;
		}
	}
}
