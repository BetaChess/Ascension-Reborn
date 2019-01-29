package ascensionMod.stspatches.minus;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.badlogic.gdx.Gdx;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.localization.LocalizedStrings;
import com.megacrit.cardcrawl.localization.RelicStrings;
import com.megacrit.cardcrawl.localization.RunModStrings;

public class EditStrings {
	private static final Logger logger = LogManager.getLogger(EditStrings.class.getName());

	/*private static HashMap<Type, String> typeMaps;
	private static HashMap<Type, Type> typeTokens;
	private static Gson gson = new GsonBuilder().create();

	@SpirePatch(clz = LocalizedStrings.class, method = SpirePatch.CONSTRUCTOR)
	public static class Initialize {
		public static void Postfix(LocalizedStrings __instance) {
			// copied from BaseMod.initializeTypeMaps
			logger.info("AscensionMinus: initializeTypeMaps");

			typeMaps = new HashMap<>();
			typeTokens = new HashMap<>();

			for (Field f : LocalizedStrings.class.getDeclaredFields()) {
				Type type = f.getGenericType();
				if (type instanceof ParameterizedType) {
					ParameterizedType pType = (ParameterizedType) type;
					Type[] typeArgs = pType.getActualTypeArguments();
					if (typeArgs.length == 2 && typeArgs[0].equals(String.class)
							&& typeArgs[1].getTypeName().startsWith("com.megacrit.cardcrawl.localization.")
							&& typeArgs[1].getTypeName().endsWith("Strings")) {

						logger.info("Registered " + typeArgs[1].getTypeName().replace("com.megacrit.cardcrawl.localization.", ""));
						typeMaps.put(typeArgs[1], f.getName());
						ParameterizedType p = com.google.gson.internal.$Gson$Types.newParameterizedTypeWithOwner(null, Map.class,
								String.class, typeArgs[1]);
						typeTokens.put(typeArgs[1], p);
					}
				}
			}

			// initialize my own strings
			logger.info("AscensionMinus: begin editing localization strings");

			loadJsonStrings(RunModStrings.class, "localization/CustomModDescription.json");
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static void loadJsonStrings(Type stringType, String filepath) {
		// I really like to try catch the entire method :) I know I shouldn't do this but whatever
		try {
			// copied from BaseMod.loadJsonStrings
			logger.info("AscensionMinus: loadJsonStrings: " + stringType.getTypeName());

			String typeMap = typeMaps.get(stringType);
			Type typeToken = typeTokens.get(stringType);
			String jsonString = Gdx.files.internal(filepath).readString(String.valueOf(StandardCharsets.UTF_8));

			Field field = LocalizedStrings.class.getDeclaredField(typeMap);
			field.setAccessible(true);

			Map localizationStrings = (Map) field.get(null);
			Map map = new HashMap(gson.fromJson(jsonString, typeToken));
			if (stringType.equals(CardStrings.class) || stringType.equals(RelicStrings.class)) {
				Map map2 = new HashMap();
				for (Object k : map.keySet()) {
					map2.put(k, map.get(k));
				}
				localizationStrings.putAll(map2);
			} else {
				localizationStrings.putAll(map);
			}

			field = LocalizedStrings.class.getDeclaredField(typeMap);
			Field modifiersField = Field.class.getDeclaredField("modifiers");
			modifiersField.setAccessible(true);
			modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
			field.setAccessible(true);
			field.set(null, localizationStrings);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
}
