package ascensionMod.stspatches.minus;

import java.lang.reflect.Field;
import java.util.List;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.screens.custom.CustomMod;
import com.megacrit.cardcrawl.screens.custom.CustomModeScreen;

@SpirePatch(clz = CustomModeScreen.class, method = "initializeMods")
public class CustomModPatch {
	public static final String BlightlessID = "AscensionMinus:Blightless";
	
	@SuppressWarnings("unchecked")
	public static void Postfix(CustomModeScreen _instance) { // copied from BaseMod
		try {
			CustomMod mod = new CustomMod(BlightlessID, "p", true);
			Field targetField = CustomModeScreen.class.getDeclaredField("modList");
			targetField.setAccessible(true);
			List<CustomMod> modList = (List<CustomMod>) targetField.get(_instance);
			int lastIndex = modList.size();
			for (int i = 0; i < modList.size(); ++i) {
				if (modList.get(i).color.equals(mod.color)) {
					lastIndex = i + 1;
				}
			}
			modList.add(lastIndex, mod);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
