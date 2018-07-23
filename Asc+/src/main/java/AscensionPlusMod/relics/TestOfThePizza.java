package ascensionMod.relics;

import basemod.abstracts.CustomRelic;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import ascensionMod.AscensionPlusMod;

public class TestOfThePizza extends CustomRelic {
	public static final String ID = "TestOfThePizza";
    private static final String IMG = "relics/PorontoPepperPizza.png";
    private static final String OutlineIMG = "relics/outline/PorontoPepperPizza.png";
    
    private static final int HP_AMT = 10;
    private static final int DMG_AMT = 7;
    
    
    public void onEquip() {
    	com.megacrit.cardcrawl.dungeons.AbstractDungeon.player.increaseMaxHp(HP_AMT, true);
    	com.megacrit.cardcrawl.dungeons.AbstractDungeon.player.currentHealth = com.megacrit.cardcrawl.dungeons.AbstractDungeon.player.currentHealth - 7;
    }
    
    public TestOfThePizza() {
        super(ID, new Texture(IMG), new Texture(OutlineIMG), RelicTier.COMMON, LandingSound.FLAT);
    }
    
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + HP_AMT + DESCRIPTIONS[1] + DMG_AMT + DESCRIPTIONS[2];
    }
    
    @Override
    public AbstractRelic makeCopy() {
        return new TestOfThePizza();
    }
}