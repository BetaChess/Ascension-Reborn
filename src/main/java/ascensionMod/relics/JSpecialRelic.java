package ascensionMod.relics;

import basemod.abstracts.CustomRelic;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;
  

public class JSpecialRelic extends CustomRelic {
	public static final String ID = "AscMod:JSpecialRelic";
    private static final String IMG = "relics/Jrm1ah.png";
    private static final String OutlineIMG = "relics/outline/Jrm1ah.png";
    
    public static final Logger logger = LogManager.getLogger(JSpecialRelic.class.getName());
    
    private boolean firstTurn = true;
    private int prevRestCount;
    private int CurrentRestCount;
     
    
    public void onEnterRestRoom() {
    	flash();
    	//logger.info("----------------------------------------------------------------------------");
    	prevRestCount = CardCrawlGame.metricData.campfire_rested;
    	this.counter = -2;
    	//logger.info("------------------------------------Re " + prevRestCount + " st " + this.counter + " ----------------------------------------");
    }

    
    public void atPreBattle(){
    	//logger.info("---------------------------------- PreBattle ------------------------------------------");
    	this.firstTurn = true;
    }
    
    
    public void atTurnStart() {
    	CurrentRestCount = CardCrawlGame.metricData.campfire_rested;
    	//logger.info("------------- " + this.firstTurn + " " + this.counter + " " + CurrentRestCount + " " + prevRestCount + " -------------");
    	if (this.firstTurn) {
    		if ((this.counter == -2) && (CurrentRestCount == prevRestCount) && (com.megacrit.cardcrawl.dungeons.AbstractDungeon.player.currentHealth < 40)) {
    			//logger.info("----------------------------------------------------------------------------");
    			this.counter = -1;
    			flash();
    			AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction(AbstractDungeon.player, this));
    	    	AbstractDungeon.actionManager.addToBottom(new com.megacrit.cardcrawl.actions.animations.TalkAction(true, this.DESCRIPTIONS[1], 2.5F, 3.0F));
    		}
    		this.firstTurn = false;
    	}
    }

    
    public JSpecialRelic() {
        super(ID, new Texture(IMG), new Texture(OutlineIMG), RelicTier.SPECIAL, LandingSound.FLAT);
    }
    
    
    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }
    
    
    @Override
    public AbstractRelic makeCopy() {
        return new JSpecialRelic();
    }
}