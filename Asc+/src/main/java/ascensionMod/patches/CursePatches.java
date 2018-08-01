//package ascensionMod.patches;
//
//import java.lang.reflect.Type;
//import java.nio.charset.StandardCharsets;
//import java.util.HashMap;
//import java.util.Map;
//
//
//import com.badlogic.gdx.Gdx;
//import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
//import com.megacrit.cardcrawl.cards.curses.*;
//import com.megacrit.cardcrawl.localization.CardStrings;
//
//import basemod.BaseMod;
//import basemod.ReflectionHacks;
//
//public class CursePatches 
//{
//	public static int AL;
//	
//	
//	@SuppressWarnings("serial")
//	public static HashMap<String, Boolean> SoulBound = new HashMap<String, Boolean>() {{
//		//put("Doubt", true);
//	}};
//	
//	private static CardStrings CardStrings;
//	
//	@SuppressWarnings("unchecked")
//	private static Map<String, CardStrings> ascensionCards = (Map<String, CardStrings>)BaseMod.gson.fromJson(loadJson("localization/eng/AscensionCardStrings.json"), getTrueType(CardStrings.class));
//	
//	
//	private static String loadJson(String jsonPath) {
//        return Gdx.files.internal(jsonPath).readString(String.valueOf(StandardCharsets.UTF_8));
//    }
//	
//	private static Type getTrueType(Type type)
//	{
//		 @SuppressWarnings("rawtypes")
//		HashMap TypeTokens = (HashMap)ReflectionHacks.getPrivateStatic(BaseMod.class, "typeTokens");
//		 return (Type)TypeTokens.get(type);
//	}
//	 
//	@SpirePatch(
//		cls = "com.megacrit.cardcrawl.cards.curses.Doubt",
//		method = "<ctor>"
//	)
//	public static class DoubtPatch{
//		public static void Postfix(Doubt __instance) {
//			CardStrings  = ascensionCards.get("Doubt");
//			
//			__instance.rawDescription = CardStrings.DESCRIPTION;
//			__instance.initializeDescription();
//		}
//	}
//	
//	
//}