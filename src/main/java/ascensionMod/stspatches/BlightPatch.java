package ascensionMod.stspatches;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.badlogic.gdx.tools.texturepacker.TexturePacker.Settings;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.blights.AbstractBlight;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.BlightHelper;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.relics.AbstractRelic;
import com.megacrit.cardcrawl.relics.Matryoshka;
import com.megacrit.cardcrawl.rewards.chests.BossChest;
import com.megacrit.cardcrawl.screens.select.BossRelicSelectScreen;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import ascensionMod.AscensionPlusMod;
import ascensionMod.blights.CustomBlight;

public class BlightPatch {
	public static final Logger logger = LogManager.getLogger(AscensionPatches.class.getName());
	
	
	@SpirePatch(
		clz = AbstractMonster.class,
		method = "onBossVictoryLogic"
	)
	public static class PostBossPatch
	{
		public static void Postfix(AbstractMonster __instance)
		{        
			ArrayList<CustomBlight> temp = new ArrayList<CustomBlight>();
			
			for(final AbstractBlight b : AbstractDungeon.player.blights)
			{
				if(b instanceof CustomBlight)
				{
					temp.add((CustomBlight) b);
				}
			}
			
			for(final CustomBlight b : temp)
			{
				b.postBossCombat();
			}
		}
	}
	
	
}
