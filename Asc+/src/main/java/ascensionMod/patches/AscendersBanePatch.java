package ascensionMod.patches;


import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.*;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.curses.AscendersBane;
import com.megacrit.cardcrawl.localization.CardStrings;

import basemod.BaseMod;
import basemod.ReflectionHacks;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@SpirePatch(cls="com.megacrit.cardcrawl.cards.curses.AscendersBane", method="<ctor>")
public class AscendersBanePatch {
	public static final Logger logger = LogManager.getLogger(AscendersBanePatch.class.getName());
	
	
	@SuppressWarnings("unchecked")
	private static Map<String, CardStrings> ascensionCards = (Map<String, CardStrings>)BaseMod.gson.fromJson(loadJson("localization/eng/AscensionCardStrings.json"), getTrueType(CardStrings.class));

	
	private static CardStrings AscenderStrings = ascensionCards.get("AscendersBane");

	
	//gettrue type
	private static Type getTrueType(Type type)
	{
		 @SuppressWarnings("rawtypes")
		HashMap TypeTokens = (HashMap)ReflectionHacks.getPrivateStatic(BaseMod.class, "typeTokens");
		 return (Type)TypeTokens.get(type);
	}
	
	
	//json loader
	private static String loadJson(String jsonPath) {
        return Gdx.files.internal(jsonPath).readString(String.valueOf(StandardCharsets.UTF_8));
    }
	
	
	public static int AL;
	
	
	public static void Postfix(AscendersBane __instance)
	{	
		__instance.rawDescription = AscenderStrings.DESCRIPTION;
		__instance.initializeDescription();
		logger.info("Ascension+ level " + AL);
		if(AL >= 2) {
			__instance.isEthereal = false;
		}
		else {
			__instance.isEthereal = true;
		}
	}	
}