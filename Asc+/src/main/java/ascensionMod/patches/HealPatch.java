package ascensionMod.patches;

import com.evacipated.cardcrawl.modthespire.lib.ByRef;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
//import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;


public class HealPatch 
{ 
	public static int AL;
	private static float lessHealPct = 0.25f;
	
	/*@SpirePatch(
		cls = "com.megacrit.cardcrawl.characters.AbstractPlayer",
		method = "heal"
	)
	public static class HealthGainPatch{
		public static void Prefix(AbstractPlayer __instance, @ByRef int[] healAmt) {
			if(AL >= 4) {
				healAmt[0] -= Math.round(healAmt[0]*lessHealPct);
			}
		}
	}*/
	
	@SpirePatch(
		cls = "com.megacrit.cardcrawl.core.AbstractCreature",
		method = "heal"
	)
	public static class HealthGainCreaturePatch{
		public static void Prefix(AbstractCreature __instance, @ByRef int[] healAmt) {
			if(AL >= 4) {
				healAmt[0] -= Math.round(healAmt[0]*lessHealPct);
			}
		}
	}
}