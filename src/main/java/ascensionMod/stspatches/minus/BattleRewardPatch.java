package ascensionMod.stspatches.minus;

import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.modthespire.lib.ByRef;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic.RelicTier;
import com.megacrit.cardcrawl.rewards.RewardItem;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.MonsterRoomBoss;
import com.megacrit.cardcrawl.rooms.MonsterRoomElite;
import com.megacrit.cardcrawl.screens.charSelect.CharacterSelectScreen;
import ascensionMod.UI.CharSelectScreenUI;

import ascensionMod.AscensionMod;

public class BattleRewardPatch {
	@SpirePatch(clz = AbstractRoom.class, method = "update")
	public static class RicherBosses {
		@SpireInsertPatch(rloc = 115, localvars = { "tmp" })
		public static void Insert(AbstractRoom __instance, @ByRef int[] tmp) {
			if (AscensionMod.AbsoluteAscensionLevel <= -8 || (AscensionMod.customAscensionRun && CharSelectScreenUI.ascScreen.negAscButtons.get(7).toggledOn))
				tmp[0] = MathUtils.round(tmp[0] * 1.5F);
		}
	}

	@SpirePatch(clz = AbstractRoom.class, method = "update")
	public static class GainMaxHpAfterBoss {
		@SpireInsertPatch(rloc = 107)
		public static void Insert(AbstractRoom __instance) {
			if (__instance instanceof MonsterRoomBoss) {
				if (AscensionMod.AbsoluteAscensionLevel <= -11 || (AscensionMod.customAscensionRun && CharSelectScreenUI.ascScreen.negAscButtons.get(10).toggledOn))
					AbstractDungeon.player.increaseMaxHp(AbstractDungeon.player.getAscensionMaxHPLoss(), true);
			}
		}
	}

	@SpirePatch(clz = AbstractRoom.class, method = "update")
	public static class NormalEnemiesDropRelic {
		@SpireInsertPatch(rloc = 157)
		public static void Insert(AbstractRoom __instance) {
			if (AscensionMod.AbsoluteAscensionLevel <= -14 || (AscensionMod.customAscensionRun && CharSelectScreenUI.ascScreen.negAscButtons.get(13).toggledOn))
				if (AbstractDungeon.potionRng.random(0, 99) < 20)
					__instance.addRelicToRewards(AbstractDungeon.returnRandomRelicTier());
		}
	}

	@SpirePatch(clz = AbstractRoom.class, method = "update")
	public static class ElitesDropCard {
		@SpireInsertPatch(rloc = 134)
		public static void Insert(AbstractRoom __instance) {
			if (AscensionMod.AbsoluteAscensionLevel <= -15 && __instance instanceof MonsterRoomElite  || (AscensionMod.customAscensionRun && CharSelectScreenUI.ascScreen.negAscButtons.get(14).toggledOn) && __instance instanceof MonsterRoomElite)
				__instance.addCardToRewards();
		}
	}

	@SpirePatch(clz = AbstractRoom.class, method = "update")
	public static class BossDropRelic {
		@SpireInsertPatch(rloc = 107)
		public static void Insert(AbstractRoom __instance) {
			if (AscensionMod.AbsoluteAscensionLevel <= -16 && __instance instanceof MonsterRoomBoss  || (AscensionMod.customAscensionRun && CharSelectScreenUI.ascScreen.negAscButtons.get(15).toggledOn && __instance instanceof MonsterRoomBoss))
				__instance.addRelicToRewards(AbstractDungeon.returnRandomRelic(RelicTier.RARE));
		}
	}

	@SpirePatch(clz = AbstractRoom.class, method = "update")
	public static class RewardsContainPotion {
		@SpireInsertPatch(rloc = 170)
		public static void Insert(AbstractRoom __instance) {
			if (AscensionMod.AbsoluteAscensionLevel <= -17  || (AscensionMod.customAscensionRun && CharSelectScreenUI.ascScreen.negAscButtons.get(16).toggledOn))
				__instance.rewards.add(new RewardItem(AbstractDungeon.returnRandomPotion()));
		}
	}
}
