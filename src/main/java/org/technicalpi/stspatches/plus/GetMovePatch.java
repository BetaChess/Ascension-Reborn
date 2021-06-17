package org.technicalpi.stspatches.plus;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.beyond.Darkling;
import com.megacrit.cardcrawl.monsters.beyond.SpireGrowth;
import com.megacrit.cardcrawl.monsters.city.Chosen;
import com.megacrit.cardcrawl.monsters.city.Healer;
import com.megacrit.cardcrawl.monsters.city.ShelledParasite;
import com.megacrit.cardcrawl.monsters.city.SnakePlant;
import com.megacrit.cardcrawl.monsters.exordium.AcidSlime_L;
import com.megacrit.cardcrawl.monsters.exordium.AcidSlime_M;
import com.megacrit.cardcrawl.monsters.exordium.AcidSlime_S;
import com.megacrit.cardcrawl.monsters.exordium.LouseDefensive;
import com.megacrit.cardcrawl.monsters.exordium.LouseNormal;
import com.megacrit.cardcrawl.monsters.exordium.SlaverBlue;
import com.megacrit.cardcrawl.monsters.exordium.SlaverRed;
import com.megacrit.cardcrawl.monsters.exordium.SpikeSlime_L;
import com.megacrit.cardcrawl.monsters.exordium.SpikeSlime_M;

import org.technicalpi.AscensionMod;

public class GetMovePatch {
	@SpirePatch(clz = SpikeSlime_M.class, method = "getMove", paramtypez = { int.class })
	@SpirePatch(clz = Darkling.class, method = "getMove", paramtypez = { int.class })
	@SpirePatch(clz = SpireGrowth.class, method = "getMove", paramtypez = { int.class })
	@SpirePatch(clz = Chosen.class, method = "getMove", paramtypez = { int.class })
	@SpirePatch(clz = ShelledParasite.class, method = "getMove", paramtypez = { int.class })
	@SpirePatch(clz = Healer.class, method = "getMove", paramtypez = { int.class })
	@SpirePatch(clz = SnakePlant.class, method = "getMove", paramtypez = { int.class })
	@SpirePatch(clz = AcidSlime_L.class, method = "getMove", paramtypez = { int.class })
	@SpirePatch(clz = AcidSlime_M.class, method = "getMove", paramtypez = { int.class })
	@SpirePatch(clz = AcidSlime_S.class, method = "getMove", paramtypez = { int.class })
	@SpirePatch(clz = LouseDefensive.class, method = "getMove", paramtypez = { int.class })
	@SpirePatch(clz = LouseNormal.class, method = "getMove", paramtypez = { int.class })
	@SpirePatch(clz = SlaverBlue.class, method = "getMove", paramtypez = { int.class })
	@SpirePatch(clz = SlaverRed.class, method = "getMove", paramtypez = { int.class })
	@SpirePatch(clz = SpikeSlime_L.class, method = "getMove", paramtypez = { int.class })
	public static class GetMovePatches {
		public static void Prefix(AbstractMonster __instance, final int num) {
			if (AscensionMod.getCustomToggleState(17)) {
				AbstractDungeon.ascensionLevel = 17;
			}
		}

		public static void Postfix(AbstractMonster __instance, final int num) {
			if (AscensionMod.customAscensionRun) {
				AbstractDungeon.ascensionLevel = 0;
			}
		}
	}
}
