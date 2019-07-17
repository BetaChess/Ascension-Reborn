package ascensionMod.UI;

import com.evacipated.cardcrawl.modthespire.lib.ByRef;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.screens.mainMenu.MainMenuScreen;
import com.megacrit.cardcrawl.screens.mainMenu.MenuButton;

import java.util.Arrays;


@SpirePatch(
    clz=MainMenuScreen.class,
    method="setMainMenuButtons"
)
public class Test2
{
    /*@SpireInsertPatch(
        rloc=4,
        localvars={"index"}
    )
    public static void Insert(MainMenuScreen __obj_instance, @ByRef int[] index)
    {
        MainMenuScreen __instance = (MainMenuScreen)__obj_instance;
        __instance.buttons.add(new MenuButton(Test.ASCREBORN, index[0]++));
    }*/
}

