package ascensionMod.UI;

import java.lang.reflect.Field;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.LineFinder;
import com.evacipated.cardcrawl.modthespire.lib.Matcher;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertLocator;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.patcher.PatchingException;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.exordium.LouseNormal;
import com.megacrit.cardcrawl.screens.charSelect.CharacterSelectScreen;
import com.megacrit.cardcrawl.screens.mainMenu.MainMenuScreen;
import com.megacrit.cardcrawl.screens.mainMenu.MenuButton;

import ascensionMod.AscensionMod;
import ascensionMod.UI.buttons.AscButton;
import javassist.CannotCompileException;
import javassist.CtBehavior;

public class CharSelectScreenUI {
	
	public static final Logger logger = LogManager.getLogger(CharSelectScreenUI.class.getName());
	
	static public AscModScreen ascScreen = null;
	
	private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("CharSelectStrings");
	
	private static Texture toggleOverlay = ImageMaster.loadImage("ui/toggleButtonOverlay.png");
	private static Texture buttonTexture = ImageMaster.loadImage("ui/button2.png");
	
	private static Hitbox customAscensionModeHb;
	private static AscButton openAscMenuButton;
	
	
	
	@SpirePatch(
		clz=CharacterSelectScreen.class,
        method="initialize"
    )
    public static class InitPostfix
    {
        public static void Postfix(CharacterSelectScreen __instance)
        {
        	Field ASC_LEFT_WF = null;
			try {
				ASC_LEFT_WF = CharacterSelectScreen.class.getDeclaredField("ASC_LEFT_W");
			} catch (NoSuchFieldException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ASC_LEFT_WF.setAccessible(true);
			float ASC_LEFT_W = 0;
			try {
				ASC_LEFT_W = (float)ASC_LEFT_WF.get(__instance);
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
        	(customAscensionModeHb = new Hitbox(ASC_LEFT_W + 150.0f * Settings.scale, 60.0f * Settings.scale)).move(Settings.WIDTH / 2.0f - ASC_LEFT_W - 16.0f - 30.0f * Settings.scale - 210.0f * Settings.scale, 85.0f * Settings.scale - 16.0f);
        	openAscMenuButton = new AscButton(Settings.WIDTH / 2.0f - ASC_LEFT_W - 16.0f - 30.0f * Settings.scale - 445.0f * Settings.scale, 110.0f * Settings.scale - 16.0f, buttonTexture, uiStrings.TEXT[1]);
        	
        	
        	if (ascScreen == null) {
            	ascScreen = new AscModScreen();
            }
        }
    }
	
	
	@SpirePatch(
		clz=CharacterSelectScreen.class,
        method="updateButtons"
    )
	public static class ConfirmButtonPatch
    {
		@SpireInsertPatch(rloc = 3)
		public static void Insert(CharacterSelectScreen __instance) {
			if(__instance.confirmButton.hb.clicked)
			{
				if (AscensionMod.customAscensionRun)
				{
					__instance.isAscensionMode = true;
					AbstractDungeon.isAscensionMode = true;
					
				}
			}
		}
    }
	
	@SpirePatch(
		clz=CharacterSelectScreen.class,
        method="open"
    )
	public static class openPatch
    {
		public static void Prefix(CharacterSelectScreen __instance, final boolean isEndless)
        {
			if (AscensionMod.customAscensionRun)
			{
				__instance.isAscensionMode = false;
				AbstractDungeon.isAscensionMode = false;
			}
        }
    }
	
	@SpirePatch(
		clz=CharacterSelectScreen.class,
        method="renderAscensionMode"
        //paramtypes= {"final SpriteBatch"}
    )
	public static class renderPatch
    {
        public static void Postfix(CharacterSelectScreen __instance, final SpriteBatch sb)
        {
        	/*if (customAscensionModeHb.justHovered)
        	{
        		AscensionMod.logger.info(Settings.scale);
        	}*/
        	
        	if (customAscensionModeHb.clicked)
        	{
        		customAscensionModeHb.clicked = false;
        		
        		if (AscensionMod.customAscensionRun)
        			AscensionMod.customAscensionRun = false;
        		
        		else
        		{
        			if (__instance.isAscensionMode)
        			{
        				__instance.isAscensionMode = false;
        				AbstractDungeon.isAscensionMode = false;
        				logger.info(__instance.isAscensionMode);
        			}
        			
        			AscensionMod.customAscensionRun = true;
        		}
        		
        		
        	}
        	
        	if (openAscMenuButton.pressed)
        	{
        		openAscMenuButton.pressed = false;
        		
        		CardCrawlGame.mainMenuScreen.darken();
        		CardCrawlGame.mainMenuScreen.screen = AscModScreen.Enum.ASC_MOD;
        		
        		if (ascScreen == null) {
                	ascScreen = new AscModScreen();
                }
        		ascScreen.open();
        	}
        	
        	Field anySelectedF = null;
			try {
				anySelectedF = CharacterSelectScreen.class.getDeclaredField("anySelected");
			} catch (NoSuchFieldException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			anySelectedF.setAccessible(true);
			boolean anySelected = false;
			try {
				anySelected = (boolean)anySelectedF.get(__instance);
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(anySelected)
			{
	        	Field ASC_LEFT_WF = null;
				try {
					ASC_LEFT_WF = CharacterSelectScreen.class.getDeclaredField("ASC_LEFT_W");
				} catch (NoSuchFieldException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				ASC_LEFT_WF.setAccessible(true);
				float ASC_LEFT_W = 0;
				try {
					ASC_LEFT_W = (float)ASC_LEFT_WF.get(__instance);
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        	
				sb.setColor(Color.WHITE);
	        	sb.draw(ImageMaster.OPTION_TOGGLE, Settings.WIDTH / 2.0f - ASC_LEFT_W - 16.0f - 30.0f * Settings.scale - 360.0f * Settings.scale, 70.0f * Settings.scale - 16.0f, 16.0f, 16.0f, 32.0f, 32.0f, Settings.scale, Settings.scale, 0.0f, 0, 0, 32, 32, false, false);
	        	if (AscensionMod.customAscensionRun)
	        		sb.draw(toggleOverlay, Settings.WIDTH / 2.0f - ASC_LEFT_W - 16.0f - 30.0f * Settings.scale - 360.0f * Settings.scale, 70.0f * Settings.scale - 16.0f, 16.0f, 16.0f, 32.0f, 32.0f, Settings.scale, Settings.scale, 0.0f, 0, 0, 32, 32, false, false);
	        	FontHelper.renderFontCentered(sb, FontHelper.cardTitleFont, uiStrings.TEXT[0], Settings.WIDTH / 2.0f - ASC_LEFT_W / 2.0f - 305.0f * Settings.scale, 70.0f * Settings.scale, Settings.GOLD_COLOR);
	        	//FontHelper.renderFontCentered(sb, FontHelper.cardDescFont_N, uiStrings.TEXT[0], Settings.WIDTH / 2.0f, 35.0f * Settings.scale, Settings.CREAM_COLOR);
	        	openAscMenuButton.render(sb);
	        	customAscensionModeHb.render(sb);
			}
        }
    }
	
	@SpirePatch(
		clz=CharacterSelectScreen.class,
		method="updateAscensionToggle"
	)
	public static class updateAscensionTogglePatch
    {
        public static void Postfix(CharacterSelectScreen __instance)
        {
        	Field anySelectedF = null;
			try {
				anySelectedF = CharacterSelectScreen.class.getDeclaredField("anySelected");
			} catch (NoSuchFieldException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			anySelectedF.setAccessible(true);
			boolean anySelected = false;
			try {
				anySelected = (boolean)anySelectedF.get(__instance);
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	
        	if (anySelected) {
                customAscensionModeHb.update();
                openAscMenuButton.update();

            }
            if (InputHelper.justClickedLeft) {
                if (customAscensionModeHb.hovered) {
                    customAscensionModeHb.clickStarted = true;
                }
            }
        }
    }
	
	@SpirePatch(
		clz=CharacterSelectScreen.class,
		method="updateAscensionToggle"
	)
	public static class UpdateCustomTogglePatch
    {
		@SpireInsertPatch(rloc = 17)
		public static void Insert(CharacterSelectScreen __instance) {
			Field ascensionModeHbF = null;
			try {
				ascensionModeHbF = CharacterSelectScreen.class.getDeclaredField("ascensionModeHb");
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
			
			if (!__instance.isAscensionMode && ascensionModeHb.clicked)
				AscensionMod.customAscensionRun = false;
		}
    }
	
	
}
