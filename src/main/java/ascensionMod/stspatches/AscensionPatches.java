package ascensionMod.stspatches;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.ByRef;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.controller.CInputActionSet;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.screens.DeathScreen;
import com.megacrit.cardcrawl.screens.charSelect.CharacterOption;
import com.megacrit.cardcrawl.screens.custom.CustomModeScreen;
import com.megacrit.cardcrawl.ui.panels.TopPanel;

import basemod.BaseMod;
import basemod.ReflectionHacks;

import ascensionMod.AscensionMod;


public class AscensionPatches 
{
	public static final Logger logger = LogManager.getLogger(AscensionPatches.class.getName());
	
	  
	
	/*@SuppressWarnings("unchecked")
	private static Map<String, UIStrings> UiString = (Map<String, UIStrings>)BaseMod.gson.fromJson(loadJson(AscensionMod.getLocalizationPath() + "AscensionDesc.json"), getTrueType(UIStrings.class));
	@SuppressWarnings("unchecked")
	private static Map<String, UIStrings> UiMinusString = (Map<String, UIStrings>)BaseMod.gson.fromJson(loadJson(AscensionMod.getLocalizationPath() + "AscensionMinusDesc.json"), getTrueType(UIStrings.class));
	*/
	
	private static UIStrings AscensionLevelStrings = CardCrawlGame.languagePack.getUIString("AscensionModeDescriptions"); //UiString.get("AscensionModeDescriptions");
	private static UIStrings AscensionMinusLevelStrings = CardCrawlGame.languagePack.getUIString("AscensionMinusDescriptions"); //UiMinusString.get("AscensionMinusDescriptions");
	
	private static String[] AscensionLevels = AscensionLevelStrings.TEXT;
	private static String[] AscensionMinusLevels = AscensionMinusLevelStrings.TEXT;
	
	
	/*private static String loadJson(String jsonPath) {
        return Gdx.files.internal(jsonPath).readString(String.valueOf(StandardCharsets.UTF_8));
    }
	
	private static Type getTrueType(Type type)
	{
		 @SuppressWarnings("rawtypes")
		HashMap TypeTokens = (HashMap)ReflectionHacks.getPrivateStatic(BaseMod.class, "typeTokens");
		 return (Type)TypeTokens.get(type);
	}*/
	
	@SpirePatch(
		cls = "com.megacrit.cardcrawl.screens.charSelect.CharacterOption",
		method = "updateHitbox",
		paramtypes = {}
	)
	public static class HitboxPatch {

		@SuppressWarnings("rawtypes")
		@SpireInsertPatch(
			rloc=62
		)
		public static SpireReturn Insert(CharacterOption __instance)
		{
			return SpireReturn.Return(null);
		}
		
		
		public static void Postfix(CharacterOption __instance) {
			if(AscensionMod.ascScaling) {
				if(CardCrawlGame.chosenCharacter == AbstractPlayer.PlayerClass.THE_SILENT) {
					if(AscensionMod.AbsoluteAscensionLevel > Integer.parseInt(AscensionMod.config.getString("MaxAscLvl_SILENT"))) {
						AscensionMod.AbsoluteAscensionLevel = Integer.parseInt(AscensionMod.config.getString("MaxAscLvl_SILENT"));
					}
				}
				else if(CardCrawlGame.chosenCharacter == AbstractPlayer.PlayerClass.IRONCLAD) {
					if(AscensionMod.AbsoluteAscensionLevel > Integer.parseInt(AscensionMod.config.getString("MaxAscLvl_IRONCLAD"))) {
						AscensionMod.AbsoluteAscensionLevel = Integer.parseInt(AscensionMod.config.getString("MaxAscLvl_IRONCLAD"));
					}
				}
				else if(CardCrawlGame.chosenCharacter == AbstractPlayer.PlayerClass.DEFECT) {
					if(AscensionMod.AbsoluteAscensionLevel > Integer.parseInt(AscensionMod.config.getString("MaxAscLvl_DEFECT"))) {
						AscensionMod.AbsoluteAscensionLevel = Integer.parseInt(AscensionMod.config.getString("MaxAscLvl_DEFECT"));
					}
				}
				else {
					if(AscensionMod.AbsoluteAscensionLevel > Integer.parseInt(AscensionMod.config.getString("MaxAscLvl"))) {
						AscensionMod.AbsoluteAscensionLevel = Integer.parseInt(AscensionMod.config.getString("MaxAscLvl"));
					}
				}
				
	            CardCrawlGame.mainMenuScreen.charSelectScreen.ascensionLevel = AscensionMod.AbsoluteAscensionLevel;
	           	if(AscensionMod.AbsoluteAscensionLevel <= 20) {
	           		if (21 < AscensionMod.AbsoluteAscensionLevel) {
	           			if(__instance.c.chosenClass == AbstractPlayer.PlayerClass.THE_SILENT) {
	           				CardCrawlGame.mainMenuScreen.charSelectScreen.ascensionLevel = Integer.parseInt(AscensionMod.config.getString("MaxAscLvl_SILENT"));
	           				AscensionMod.AbsoluteAscensionLevel = CardCrawlGame.mainMenuScreen.charSelectScreen.ascensionLevel;
	           			}
	           			else if(__instance.c.chosenClass == AbstractPlayer.PlayerClass.IRONCLAD) {
	           				CardCrawlGame.mainMenuScreen.charSelectScreen.ascensionLevel = Integer.parseInt(AscensionMod.config.getString("MaxAscLvl_IRONCLAD"));
	           				AscensionMod.AbsoluteAscensionLevel = CardCrawlGame.mainMenuScreen.charSelectScreen.ascensionLevel;
	           			}
	           			else if(__instance.c.chosenClass == AbstractPlayer.PlayerClass.DEFECT) {
	           				CardCrawlGame.mainMenuScreen.charSelectScreen.ascensionLevel = Integer.parseInt(AscensionMod.config.getString("MaxAscLvl_DEFECT"));
	           				AscensionMod.AbsoluteAscensionLevel = CardCrawlGame.mainMenuScreen.charSelectScreen.ascensionLevel;
	           			}
	           			else {
	           				CardCrawlGame.mainMenuScreen.charSelectScreen.ascensionLevel = Integer.parseInt(AscensionMod.config.getString("MaxAscLvl"));
	           				AscensionMod.AbsoluteAscensionLevel = CardCrawlGame.mainMenuScreen.charSelectScreen.ascensionLevel;
	           			}
	           		}
	           	}
			}
			
			CardCrawlGame.mainMenuScreen.charSelectScreen.ascensionLevel = AscensionMod.AbsoluteAscensionLevel;
			
			if(AscensionMod.AbsoluteAscensionLevel > AscensionMod.MAXMODASCENSIONLEVEL) {
				AscensionMod.AbsoluteAscensionLevel = AscensionMod.MAXMODASCENSIONLEVEL;
				CardCrawlGame.mainMenuScreen.charSelectScreen.ascensionLevel = AscensionMod.AbsoluteAscensionLevel;
			}
			
           	if (AscensionMod.MINMODASCENSIONLEVEL > CardCrawlGame.mainMenuScreen.charSelectScreen.ascensionLevel) {
           		CardCrawlGame.mainMenuScreen.charSelectScreen.ascensionLevel = AscensionMod.MINMODASCENSIONLEVEL;
           		AscensionMod.AbsoluteAscensionLevel = CardCrawlGame.mainMenuScreen.charSelectScreen.ascensionLevel;
           	}
		
           	
           	if (AscensionMod.AbsoluteAscensionLevel > 0) {
           		CardCrawlGame.mainMenuScreen.charSelectScreen.ascLevelInfoString = AscensionLevels[(AscensionMod.AbsoluteAscensionLevel - 1)];
           	}
           	else if(AscensionMod.AbsoluteAscensionLevel < 0)
           	{
           		CardCrawlGame.mainMenuScreen.charSelectScreen.ascLevelInfoString = AscensionMinusLevels[(Math.abs(AscensionMod.AbsoluteAscensionLevel) - 1)];
           	}
           	else {
           		CardCrawlGame.mainMenuScreen.charSelectScreen.ascLevelInfoString = "";	
           	}
        }
	}
	
	@SpirePatch(
		cls = "com.megacrit.cardcrawl.screens.charSelect.CharacterOption",
		method = "decrementAscensionLevel",
		paramtypes = {"int"}
	)
	public static class decrementPatch {
		public static SpireReturn<?> Prefix(CharacterOption __instance, @ByRef int[] level) {
			if(level[0] == 0) {
				level[0] = -1;
			}
			else if(level[0] < AscensionMod.MINMODASCENSIONLEVEL)
			{
				level[0] = AscensionMod.MINMODASCENSIONLEVEL;
			}
			
			CardCrawlGame.mainMenuScreen.charSelectScreen.ascensionLevel = level[0];
			AscensionMod.AbsoluteAscensionLevel = level[0];
			
			if(level[0] >= 20) {
				CardCrawlGame.mainMenuScreen.charSelectScreen.ascLevelInfoString = AscensionLevels[(level[0] - 1)];
				return SpireReturn.Return(null);
			}
			else if(level[0] == 0) {
				return SpireReturn.Return(null);
			}
			else if(level[0] < 0)
			{
				CardCrawlGame.mainMenuScreen.charSelectScreen.ascLevelInfoString = AscensionMinusLevels[(Math.abs(AscensionMod.AbsoluteAscensionLevel) - 1)];
				return SpireReturn.Return(null);
			}
			else {
				return SpireReturn.Continue();
			}
		}
	}
	
	@SpirePatch(
		cls = "com.megacrit.cardcrawl.screens.charSelect.CharacterOption",
		method = "incrementAscensionLevel",
		paramtypes = {"int"}
	)
	public static class incrementPatch {
		public static SpireReturn<?> Prefix(CharacterOption __instance, @ByRef int[] level) {	
			if(AscensionMod.ascScaling) {
				if(__instance.c.chosenClass == AbstractPlayer.PlayerClass.THE_SILENT) {
					if(level[0] > Integer.parseInt(AscensionMod.config.getString("MaxAscLvl_SILENT"))) {
						level[0] = Integer.parseInt(AscensionMod.config.getString("MaxAscLvl_SILENT"));
					}
				}
				else if(__instance.c.chosenClass == AbstractPlayer.PlayerClass.IRONCLAD) {
					if(level[0] > Integer.parseInt(AscensionMod.config.getString("MaxAscLvl_IRONCLAD"))) {
						level[0] = Integer.parseInt(AscensionMod.config.getString("MaxAscLvl_IRONCLAD"));
					}
				}
				else if(__instance.c.chosenClass == AbstractPlayer.PlayerClass.DEFECT) {
					if(level[0] > Integer.parseInt(AscensionMod.config.getString("MaxAscLvl_DEFECT"))) {
						level[0] = Integer.parseInt(AscensionMod.config.getString("MaxAscLvl_DEFECT"));
					}
				}
				else {
					if(level[0] > Integer.parseInt(AscensionMod.config.getString("MaxAscLvl"))) {
						level[0] = Integer.parseInt(AscensionMod.config.getString("MaxAscLvl"));
					}
				}
			}
			
			logger.info(level[0]);
			
			if(level[0] > AscensionMod.MAXMODASCENSIONLEVEL) {
				level[0] = AscensionMod.MAXMODASCENSIONLEVEL;
			}
			else if(level[0] == 0)
			{
				level[0] = 1;
			}
			
			logger.info(level[0]);
			
			CardCrawlGame.mainMenuScreen.charSelectScreen.ascensionLevel = level[0];
			AscensionMod.AbsoluteAscensionLevel = level[0];
			
			if(level[0] >= 20) {
				CardCrawlGame.mainMenuScreen.charSelectScreen.ascLevelInfoString = AscensionLevels[(level[0] - 1)];
				return SpireReturn.Return(null);
			}
			else if(level[0] < 0)
			{
				CardCrawlGame.mainMenuScreen.charSelectScreen.ascLevelInfoString = AscensionMinusLevels[(Math.abs(AscensionMod.AbsoluteAscensionLevel) - 1)];
				return SpireReturn.Return(null);
			}
			else {
				logger.info("WOT");
				return SpireReturn.Continue();
			}
		}
	}
	
	
	@SpirePatch(
		cls = "com.megacrit.cardcrawl.screens.custom.CustomModeScreen",
		method = "renderAscension"
	)
	public static class RenderPatch{

		@SpireInsertPatch(
			rloc=61
		)
		public static void Insert(CustomModeScreen __instance, SpriteBatch sb)
		{
			AscensionMod.AbsoluteAscensionLevel = __instance.ascensionLevel;
			__instance.ascensionLevel = 0;
		}
		
		public static void Postfix(CustomModeScreen __instance, SpriteBatch sb) {
			if(AscensionMod.AbsoluteAscensionLevel > AscensionMod.MAXMODASCENSIONLEVEL) {
				AscensionMod.AbsoluteAscensionLevel = AscensionMod.MAXMODASCENSIONLEVEL;
			}
			if(AscensionMod.AbsoluteAscensionLevel < AscensionMod.MINMODASCENSIONLEVEL) {
				AscensionMod.AbsoluteAscensionLevel = AscensionMod.MINMODASCENSIONLEVEL;
			}
			
			__instance.ascensionLevel = AscensionMod.AbsoluteAscensionLevel;

			
			if(__instance.ascensionLevel != 0) {
				Field screenXF = null;
				try {
					screenXF = CustomModeScreen.class.getDeclaredField("screenX");
				} catch (NoSuchFieldException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				screenXF.setAccessible(true);
				float screenX = 0;
				try {
					screenX = (float)screenXF.get(__instance);
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Field ascensionModeHbF = null;
				try {
					ascensionModeHbF = CustomModeScreen.class.getDeclaredField("ascensionModeHb");
				} catch (NoSuchFieldException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ascensionModeHbF.setAccessible(true);
				Hitbox ascensionModeHb = null;
				try {
					ascensionModeHb = (Hitbox)ascensionModeHbF.get(__instance);
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				if(AscensionMod.AbsoluteAscensionLevel > 0)
					FontHelper.renderSmartText(sb, FontHelper.charDescFont, CardCrawlGame.mainMenuScreen.charSelectScreen.ascLevelInfoString = AscensionLevels[(__instance.ascensionLevel - 1)], screenX + 475.0F * com.megacrit.cardcrawl.core.Settings.scale, ascensionModeHb.cY + 10.0F * com.megacrit.cardcrawl.core.Settings.scale, 9999.0F, 32.0F * com.megacrit.cardcrawl.core.Settings.scale, com.megacrit.cardcrawl.core.Settings.CREAM_COLOR);
				else
					FontHelper.renderSmartText(sb, FontHelper.charDescFont, CardCrawlGame.mainMenuScreen.charSelectScreen.ascLevelInfoString = AscensionMinusLevels[(Math.abs(__instance.ascensionLevel) - 1)], screenX + 475.0F * com.megacrit.cardcrawl.core.Settings.scale, ascensionModeHb.cY + 10.0F * com.megacrit.cardcrawl.core.Settings.scale, 9999.0F, 32.0F * com.megacrit.cardcrawl.core.Settings.scale, com.megacrit.cardcrawl.core.Settings.CREAM_COLOR);
					
			}
		}
	}
	
	
	@SpirePatch(
		cls = "com.megacrit.cardcrawl.ui.panels.TopPanel",
		method = "setupAscensionMode",
		paramtypes = {}
	)
	public static class SetupPatch{
		@SuppressWarnings("rawtypes")
		@SpireInsertPatch(
			rloc=5
		)
		public static SpireReturn Insert(TopPanel __instance)
		{
		    return SpireReturn.Return(null);
		}
		public static void Postfix(TopPanel __instance) {
			if (AbstractDungeon.isAscensionMode) {
				final StringBuilder sb = new StringBuilder();
				if(AscensionMod.AbsoluteAscensionLevel < 0)
				{
					for (int i = 0; i > AscensionMod.AbsoluteAscensionLevel; i--) {
						sb.append(AscensionMinusLevels[Math.abs(i)]);
						System.out.println(AscensionMinusLevels[Math.abs(i)]);
						if (i != AscensionMod.AbsoluteAscensionLevel - 1) {
							sb.append(" NL ");
						}
					}
				}
				else if(AscensionMod.AbsoluteAscensionLevel > 0)
				{
					for (int i = 0; i < Math.abs(AscensionMod.AbsoluteAscensionLevel); i++) {
						sb.append(AscensionLevels[i]);
						System.out.println(AscensionLevels[i]);
						if (i != AscensionMod.AbsoluteAscensionLevel + 1) {
							sb.append(" NL ");
						}
					}
				}
				
				Field ascensionStringF = null;
				try {
					ascensionStringF = TopPanel.class.getDeclaredField("ascensionString");
				} catch (NoSuchFieldException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(sb.toString());
				ascensionStringF.setAccessible(true);
				//String x = sb.toString();
				try {
					ascensionStringF.set(__instance, sb.toString());
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
        }
	}
	
	@SpirePatch(
		cls = "com.megacrit.cardcrawl.screens.DeathScreen",
		method = "update",
		paramtypes = {}
	)
	public static class UpdatePatch {
		public static void Postfix(DeathScreen __instance) {
			if ((__instance.isVictory) && (AbstractDungeon.isAscensionMode) && (!Settings.isTrial) && (AbstractDungeon.ascensionLevel < 25)) {
				if(AbstractDungeon.player.chosenClass == AbstractPlayer.PlayerClass.THE_SILENT) {
					if(AbstractDungeon.ascensionLevel == Integer.parseInt(AscensionMod.config.getString("MaxAscLvl_SILENT"))) {
						AscensionMod.config.setString("MaxAscLvl_SILENT", ("" + (1 + Integer.parseInt(AscensionMod.config.getString("MaxAscLvl_SILENT")))));
						try {
							AscensionMod.config.save();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				else if(AbstractDungeon.player.chosenClass == AbstractPlayer.PlayerClass.IRONCLAD) {
					if(AbstractDungeon.ascensionLevel == Integer.parseInt(AscensionMod.config.getString("MaxAscLvl_IRONCLAD"))) {
						AscensionMod.config.setString("MaxAscLvl_IRONCLAD", ("" + (1 + Integer.parseInt(AscensionMod.config.getString("MaxAscLvl_IRONCLAD")))));
						try {
							AscensionMod.config.save();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				else if(AbstractDungeon.player.chosenClass == AbstractPlayer.PlayerClass.DEFECT) {
					if(AbstractDungeon.ascensionLevel == Integer.parseInt(AscensionMod.config.getString("MaxAscLvl_DEFECT"))) {
						AscensionMod.config.setString("MaxAscLvl_DEFECT", ("" + (1 + Integer.parseInt(AscensionMod.config.getString("MaxAscLvl_DEFECT")))));
						try {
							AscensionMod.config.save();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
				else {
					if(AbstractDungeon.ascensionLevel == Integer.parseInt(AscensionMod.config.getString("MaxAscLvl"))) {
						AscensionMod.config.setString("MaxAscLvl", ("" + (1 + Integer.parseInt(AscensionMod.config.getString("MaxAscLvl")))));
						try {
							AscensionMod.config.save();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		}
	}
	
	@SpirePatch(
		cls = "com.megacrit.cardcrawl.screens.custom.CustomModeScreen",
		method = "updateAscension",
		paramtypes = {}
	)
	
	public static class UpdateAscPatch {
		public static void Prefix(CustomModeScreen __instance) {
			
			Field ascRightHbF = null;
			try {
				ascRightHbF = CustomModeScreen.class.getDeclaredField("ascRightHb");
			} catch (NoSuchFieldException | SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ascRightHbF.setAccessible(true);
			Hitbox ascRightHb = null;
			try {
				ascRightHb = (Hitbox)ascRightHbF.get(__instance);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ascRightHb.update();
			
			Field ascLeftHbF = null;
			try {
				ascLeftHbF = CustomModeScreen.class.getDeclaredField("ascLeftHb");
			} catch (NoSuchFieldException | SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ascLeftHbF.setAccessible(true);
			Hitbox ascLeftHb = null;
			try {
				ascLeftHb = (Hitbox)ascLeftHbF.get(__instance);
			} catch (IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ascLeftHb.update();
			
			
			
			
			if ((ascRightHb.clicked) || (CInputActionSet.pageRightViewExhaust.isJustPressed())) {
				//playClickFinishSound();
				
				ascRightHb.clicked = false;
				__instance.ascensionLevel += 1;
				if (__instance.ascensionLevel > AscensionMod.MAXMODASCENSIONLEVEL) {
					__instance.ascensionLevel = AscensionMod.MINMODASCENSIONLEVEL;
				}
				
				__instance.isAscensionMode = true;
			}
			
			if ((ascLeftHb.clicked) || (CInputActionSet.pageRightViewExhaust.isJustPressed())) {
				//playClickFinishSound();
				
				ascLeftHb.clicked = false;
				__instance.ascensionLevel -= 1;
				if (__instance.ascensionLevel < AscensionMod.MINMODASCENSIONLEVEL) {
					__instance.ascensionLevel = AscensionMod.MAXMODASCENSIONLEVEL;
				}
				
				__instance.isAscensionMode = true;
			}
			
			AscensionMod.AbsoluteAscensionLevel = __instance.ascensionLevel;
		}
	}
}
