package ascensionMod.stspatches;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.evacipated.cardcrawl.modthespire.lib.ByRef;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.blights.AbstractBlight;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.BlightHelper;
import com.megacrit.cardcrawl.saveAndContinue.SaveFile;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import ascensionMod.blights.*;

public class BlightPatches {
	public static final Logger logger = LogManager.getLogger(AscensionPatches.class.getName());
	
	
	@SpirePatch(
		clz = BlightHelper.class,
		method = "getBlight"
	)
	public static class getBlightPatch
	{
		public static SpireReturn<AbstractBlight> Prefix(final String id)
		{
			switch(id)
			{
			case "AscMod:CursedBank":
				return SpireReturn.Return(new CursedBank());
				
			case "AscMod:CursedFlame":
				return SpireReturn.Return(new CursedFlame());
				
			case "AscMod:StarOfAscension":
				return SpireReturn.Return(new StarOfAscension());
				
			case "AscMod:MegaStarOfAscension":
				return SpireReturn.Return(new MegaStarOfAscension());
				
			default:
				return SpireReturn.Continue();
			}
		}
	}
	
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
	
	@SpirePatch(
		clz = AbstractPlayer.class,
		method = "gainGold"
	)
	public static class OnGainGoldPatch
	{
		public static void Postfix(AbstractPlayer __instance, final int amount)
		{
			ArrayList<CustomBlight> cBlights = CustomBlight.getCustomBlights(AbstractDungeon.player.blights);
			
            for (final CustomBlight b : cBlights) {
                b.onGainGold();
            }
		}
	}
	
	@SpirePatch(
		clz = AbstractDungeon.class,
		method = "nextRoomTransition",
		paramtypez = {
						SaveFile.class
					}
	)
	public static class OnEnterRoomPatch
	{
		public static void Postfix(AbstractDungeon __instance, final SaveFile savefile)
		{
			if (AbstractDungeon.nextRoom != null) {
				ArrayList<CustomBlight> cBlights = CustomBlight.getCustomBlights(AbstractDungeon.player.blights);
				
	            for (final CustomBlight b : cBlights) {
	                b.onEnterRoom(AbstractDungeon.nextRoom.room);
	            }
	        }
		}
	}
	
	@SpirePatch(
		clz = AbstractCreature.class,
		method = "heal",
		paramtypez = { 
						int.class,
						boolean.class
					}
	)
	public static class OnPlayerHealPatch
	{
		public static void Prefix(AbstractCreature __instance, @ByRef int[] healAmount, final boolean showeffect)
		{
			ArrayList<CustomBlight> cBlights = CustomBlight.getCustomBlights(AbstractDungeon.player.blights);
			
            for (final CustomBlight b : cBlights) {
	            if (__instance.isPlayer) {
	                healAmount[0] = b.onPlayerHeal(healAmount[0]);
	            }
	        }
		}
	}
	
}
