package ascensionMod.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.shop.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import ascensionMod.relics.*;

import ascensionMod.AscensionPlusMod;

public class StarOfAscension2 extends CustomRelic {
	public static final String ID = "StarOfAscension2";
    private static final String IMG = "relics/StarOfAscension.png";
    private static final String OutlineIMG = "relics/outline/StarOfAscension.png";
    
    public static void relicAction() {
    	
    }
    
    public void onEquip() {
    	ascensionMod.relics.StarOfAscension1.relicAction();
    }
    
    public StarOfAscension2() {
        super(ID, new Texture(IMG), new Texture(OutlineIMG), RelicTier.STARTER, LandingSound.FLAT);
    }
    
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
    
    @Override
    public AbstractRelic makeCopy() {
        return new StarOfAscension1();
    }
}