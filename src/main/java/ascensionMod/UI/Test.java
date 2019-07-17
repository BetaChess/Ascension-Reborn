package ascensionMod.UI;


import com.evacipated.cardcrawl.modthespire.lib.SpireEnum;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.screens.charSelect.CharacterSelectScreen;
import com.megacrit.cardcrawl.screens.mainMenu.MenuButton;

import java.lang.reflect.Field;

public class Test {
    

    /*@SpirePatch(
        clz=MenuButton.class,
        method="setLabel"
    )
    public static class SetLabel
    {
        public static void Postfix(MenuButton __instance)
        {
            try {
                if (__instance.result == ASCREBORN) {
                    Field f_label = MenuButton.class.getDeclaredField("label");
                    f_label.setAccessible(true);
                    f_label.set(__instance, "ASCENSION");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }*/

    /*@SpirePatch(
        clz=MenuButton.class,
        method="buttonEffect"
    )
    public static class ButtonEffect
    {
        public static void Postfix(MenuButton __instance)
        {
            if (__instance.result == ASCREBORN) {
                if (ascScreen == null) {
                	ascScreen = new AscModScreen();
                }
                ascScreen.open();
            }
        }
    }*/
	
	/*@SpirePatch(
		clz = CharacterSelectScreen.class,
		method = "open",
		paramtypes = {"boolean"}
	)
	public static class TestPatch {
		public static SpireReturn<?> Prefix(CharacterSelectScreen __instance, final boolean isEndless) {
			AscModScreen.open();
			
			return SpireReturn.Return(null);
			
		}
		
	}*/
	
}
