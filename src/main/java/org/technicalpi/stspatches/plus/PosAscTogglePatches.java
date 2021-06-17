package org.technicalpi.stspatches.plus;

import java.lang.reflect.Field;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.evacipated.cardcrawl.modthespire.lib.ByRef;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.dungeons.TheBeyond;
import com.megacrit.cardcrawl.dungeons.TheCity;
import com.megacrit.cardcrawl.dungeons.TheEnding;
import com.megacrit.cardcrawl.helpers.ModHelper;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.EventRoom;
import com.megacrit.cardcrawl.shop.ShopScreen;
import com.megacrit.cardcrawl.ui.buttons.ProceedButton;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.characters.AbstractPlayer.PlayerClass;

import org.technicalpi.AscensionMod;

public class PosAscTogglePatches {
	public static final Logger logger = LogManager.getLogger(PosAscTogglePatches.class.getName());

	@SpirePatch(clz = AbstractDungeon.class, method = "generateRoomTypes")
	public static class MoreElites {

		@SpireInsertPatch(rloc = 22, localvars = { "eliteCount" })
		public static void Insert(final ArrayList<AbstractRoom> roomList, final int availableRoomCount,
				@ByRef int[] eliteCount) {
			if (!ModHelper.isModEnabled("Elite Swarm") && AscensionMod.getCustomToggleState(1)) {
				// Field get, start
				Field eliteRoomChanceF = null;
				try {
					eliteRoomChanceF = AbstractDungeon.class.getDeclaredField("eliteRoomChance");
				} catch (NoSuchFieldException e) {
					e.printStackTrace();
				} catch (SecurityException e) {
					e.printStackTrace();
				}
				eliteRoomChanceF.setAccessible(true);
				// Field get, end

				float eliteRoomChance = 0;
				try {
					eliteRoomChance = (float) eliteRoomChanceF.get(AbstractDungeon.class);
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}

				eliteCount[0] = Math.round(availableRoomCount * eliteRoomChance * 1.6f);
			}
		}
	}

	@SpirePatch(clz = AbstractDungeon.class, method = "dungeonTransitionSetup")
	public static class LessHeal {
		@SpireInsertPatch(rloc = 29)
		public static void Insert() {
			if (AscensionMod.getCustomToggleState(5)) {
				AbstractDungeon.ascensionLevel = 5;
			}
		}

		public static void Postfix() {
			if (AscensionMod.customAscensionRun)
				AbstractDungeon.ascensionLevel = 0;
		}
	}

	@SpirePatch(clz = AbstractDungeon.class, method = "dungeonTransitionSetup")
	public static class LessMaxHP {
		@SpireInsertPatch(rloc = 41)
		public static void Insert() {
//			logger.info(AscensionMod.getCustomToggleState(14));
//			logger.info(AbstractDungeon.floorNum);
//			logger.info(CardCrawlGame.dungeon instanceof Exordium);
			if (AscensionMod.getCustomToggleState(14)) {
				AbstractDungeon.ascensionLevel = 14;
			}
		}
	}

	@SpirePatch(clz = AbstractDungeon.class, method = "dungeonTransitionSetup")
	public static class DamagedStart {
		@SpireInsertPatch(rloc = 44)
		public static void Insert() {
			if (AscensionMod.getCustomToggleState(6)) {
				AbstractDungeon.ascensionLevel = 6;
			}
		}
	}

	@SpirePatch(clz = AbstractDungeon.class, method = "dungeonTransitionSetup")
	public static class CursedStart {
		@SpireInsertPatch(rloc = 47)
		public static void Insert() {
			if (AscensionMod.getCustomToggleState(10)) {
				AbstractDungeon.ascensionLevel = 10;
			}
		}
	}

	@SpirePatch(clz = AbstractPlayer.class, method = SpirePatch.CONSTRUCTOR

	)
	public static class LessPotionSlots {
		public static void Prefix(AbstractPlayer __instance, final String name, final PlayerClass setClass) {
			if (AscensionMod.getCustomToggleState(11)) {
				AbstractDungeon.ascensionLevel = 11;
			}
		}

		public static void Postfix(AbstractPlayer __instance, final String name, final PlayerClass setClass) {
			if (AscensionMod.customAscensionRun)
				AbstractDungeon.ascensionLevel = 0;
		}
	}

	@SpirePatch(clz = TheBeyond.class, method = "initializeLevelSpecificChances")
	@SpirePatch(clz = TheCity.class, method = "initializeLevelSpecificChances")
	@SpirePatch(clz = TheEnding.class, method = "initializeLevelSpecificChances")
	public static class LessUpgradedCards {
		public static void Prefix(AbstractDungeon __instance) {
			if (AscensionMod.getCustomToggleState(12)) {
				AbstractDungeon.ascensionLevel = 12;
			}
		}

		public static void Postfix(AbstractDungeon __instance) {
			if (AscensionMod.customAscensionRun)
				AbstractDungeon.ascensionLevel = 0;
		}
	}

	@SpirePatch(clz = AbstractRoom.class, method = "update")
	public static class PoorBosses {
		public static void Prefix(AbstractRoom __instance) {
			if (AscensionMod.getCustomToggleState(13)) {
				AbstractDungeon.ascensionLevel = 13;
			}
		}

		public static void Postfix(AbstractRoom __instance) {
			if (AscensionMod.customAscensionRun)
				AbstractDungeon.ascensionLevel = 0;
		}
	}

	@SpirePatch(clz = AbstractDungeon.class, method = "setCurrMapNode")
	public static class EventPatch {

		public static void Prefix() {
			logger.info("Getting next room");
			logger.info("Room is: " + AbstractDungeon.nextRoom.room.toString());

			if ((AbstractDungeon.nextRoom.room instanceof EventRoom) && AscensionMod.getCustomToggleState(15)) {
				AbstractDungeon.ascensionLevel = 15;
			} else {
				if (AscensionMod.customAscensionRun)
					AbstractDungeon.ascensionLevel = 0;
			}

		}
	}

	@SpirePatch(clz = ShopScreen.class, method = "init")
	public static class ShopPatch {
		public static void Prefix(ShopScreen __instance, final ArrayList<AbstractCard> coloredCards,
				final ArrayList<AbstractCard> colorlessCards) {
			if (AscensionMod.getCustomToggleState(16)) {
				AbstractDungeon.ascensionLevel = 16;
			} else if (AscensionMod.customAscensionRun)
				AbstractDungeon.ascensionLevel = 0;
		}

		public static void Postfix(ShopScreen __instance, final ArrayList<AbstractCard> coloredCards,
				final ArrayList<AbstractCard> colorlessCards) {
			if (AscensionMod.customAscensionRun)
				AbstractDungeon.ascensionLevel = 0;
		}
	}

	@SpirePatch(clz = ProceedButton.class, method = "update")
	public static class DoubleBoss {
		public static void Prefix(ProceedButton __instance) {
			if (AscensionMod.getCustomToggleState(20)) {
				AbstractDungeon.ascensionLevel = 20;
			} else if (AscensionMod.customAscensionRun)
				AbstractDungeon.ascensionLevel = 0;

		}

		public static void Postfix(ProceedButton __instance) {
			if (AscensionMod.customAscensionRun)
				AbstractDungeon.ascensionLevel = 0;
		}
	}

}
