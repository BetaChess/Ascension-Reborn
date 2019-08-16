package ascensionMod.stspatches.plus;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.beyond.Darkling;
import com.megacrit.cardcrawl.monsters.beyond.SpireGrowth;
import com.megacrit.cardcrawl.monsters.city.BanditLeader;
import com.megacrit.cardcrawl.monsters.city.Mugger;
import com.megacrit.cardcrawl.monsters.city.Snecko;
import com.megacrit.cardcrawl.monsters.city.SphericGuardian;
import com.megacrit.cardcrawl.monsters.exordium.Cultist;
import com.megacrit.cardcrawl.monsters.exordium.FungiBeast;
import com.megacrit.cardcrawl.monsters.exordium.GremlinFat;
import com.megacrit.cardcrawl.monsters.exordium.GremlinWizard;
import com.megacrit.cardcrawl.monsters.exordium.LouseNormal;
import com.megacrit.cardcrawl.monsters.exordium.SlaverBlue;
import com.megacrit.cardcrawl.monsters.exordium.SlaverRed;
import com.megacrit.cardcrawl.monsters.exordium.SpikeSlime_L;
import com.megacrit.cardcrawl.monsters.exordium.SpikeSlime_M;

import ascensionMod.AscensionMod;

public class TakeTurnPatch {
	@SpirePatch(clz = Darkling.class, method = "takeTurn")
	@SpirePatch(clz = SpireGrowth.class, method = "takeTurn")
	@SpirePatch(clz = BanditLeader.class, method = "takeTurn")
	@SpirePatch(clz = Mugger.class, method = "takeTurn")
	@SpirePatch(clz = Snecko.class, method = "takeTurn")
	@SpirePatch(clz = SphericGuardian.class, method = "takeTurn")
	@SpirePatch(clz = Cultist.class, method = "takeTurn")
	@SpirePatch(clz = FungiBeast.class, method = "takeTurn")
	@SpirePatch(clz = GremlinFat.class, method = "takeTurn")
	@SpirePatch(clz = GremlinWizard.class, method = "takeTurn")
	@SpirePatch(clz = LouseNormal.class, method = "takeTurn")
	@SpirePatch(clz = SlaverBlue.class, method = "takeTurn")
	@SpirePatch(clz = SlaverRed.class, method = "takeTurn")
	@SpirePatch(clz = SpikeSlime_L.class, method = "takeTurn")
	public static class TurnPatch {
		public static void Prefix(AbstractMonster __instance) {
			if (AscensionMod.getCustomToggleState(17)) {
				AbstractDungeon.ascensionLevel = 17;
			}
		}

		public static void Postfix(AbstractMonster __instance) {
			if (AscensionMod.customAscensionRun) {
				AbstractDungeon.ascensionLevel = 0;
			}
		}
	}
}
