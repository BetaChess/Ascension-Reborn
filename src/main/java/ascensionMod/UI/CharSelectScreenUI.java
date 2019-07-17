package ascensionMod.UI;

import java.lang.reflect.Field;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.localization.UIStrings;
import com.megacrit.cardcrawl.screens.charSelect.CharacterSelectScreen;
import com.megacrit.cardcrawl.screens.mainMenu.MainMenuScreen;
import com.megacrit.cardcrawl.screens.mainMenu.MenuButton;

import ascensionMod.AscensionMod;

public class CharSelectScreenUI {
	
	static public AscModScreen ascScreen = null;
	
	private static final UIStrings uiStrings = CardCrawlGame.languagePack.getUIString("CharSelectStrings");
	
	private static Texture toggleOverlay = ImageMaster.loadImage("ui/toggleButtonOverlay.png");
	
	private static Hitbox customAscensionModeHb;
	
	
	
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
        	
        	(customAscensionModeHb = new Hitbox(ASC_LEFT_W + 100.0f * Settings.scale, 50.0f * Settings.scale)).move(Settings.WIDTH / 2.0f - ASC_LEFT_W / 2.0f - 50.0f * Settings.scale, 70.0f * Settings.scale);
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
	        	sb.draw(ImageMaster.OPTION_TOGGLE, Settings.WIDTH / 2.0f - ASC_LEFT_W - 16.0f - 30.0f * Settings.scale - 310.0f * Settings.scale, 70.0f * Settings.scale - 16.0f, 16.0f, 16.0f, 32.0f, 32.0f, Settings.scale, Settings.scale, 0.0f, 0, 0, 32, 32, false, false);
	        	sb.draw(toggleOverlay, Settings.WIDTH / 2.0f - ASC_LEFT_W - 16.0f - 30.0f * Settings.scale - 310.0f * Settings.scale, 70.0f * Settings.scale - 16.0f, 16.0f, 16.0f, 32.0f, 32.0f, Settings.scale, Settings.scale, 0.0f, 0, 0, 32, 32, false, false);
	        	FontHelper.renderFontCentered(sb, FontHelper.cardTitleFont, uiStrings.TEXT[0], Settings.WIDTH / 2.0f - ASC_LEFT_W / 2.0f - 265.0f * Settings.scale, 70.0f * Settings.scale, Settings.GOLD_COLOR);
	        	//FontHelper.renderFontCentered(sb, FontHelper.cardDescFont_N, uiStrings.TEXT[0], Settings.WIDTH / 2.0f, 35.0f * Settings.scale, Settings.CREAM_COLOR);
	        	
			}
        	
        	
        	customAscensionModeHb.render(sb);
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
            }
            if (InputHelper.justClickedLeft) {
                if (customAscensionModeHb.hovered) {
                    customAscensionModeHb.clickStarted = true;
                }
            }
        }
    }
}
