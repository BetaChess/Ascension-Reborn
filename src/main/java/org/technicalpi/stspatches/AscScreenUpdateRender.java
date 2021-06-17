package org.technicalpi.stspatches;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.screens.mainMenu.MainMenuScreen;
import org.technicalpi.UI.*;
import org.technicalpi.UI.AscModScreen;
import org.technicalpi.UI.CharSelectScreenUI;

public class AscScreenUpdateRender {
	@SpirePatch(
        clz=MainMenuScreen.class,
        method="update"
    )
    public static class Update
    {
        public static void Postfix(MainMenuScreen __instance)
        {
            if (__instance.screen == AscModScreen.Enum.ASC_MOD) {
                CharSelectScreenUI.ascScreen.update(); ////////////////////
            }
        }
    }

    @SpirePatch(
        clz=MainMenuScreen.class,
        method="render"
    )
    public static class Render
    {
        public static void Postfix(MainMenuScreen __instance, SpriteBatch sb)
        {
            if (__instance.screen == AscModScreen.Enum.ASC_MOD) {
            	CharSelectScreenUI.ascScreen.render(sb); ///////////////////////
            }
        }
    }
}
