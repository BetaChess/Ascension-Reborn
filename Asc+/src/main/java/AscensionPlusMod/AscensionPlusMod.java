package ascensionMod;

import java.nio.charset.StandardCharsets;

import com.megacrit.cardcrawl.cards.colorless.*;
import com.megacrit.cardcrawl.cards.curses.*;
import com.megacrit.cardcrawl.cards.red.*;
import com.megacrit.cardcrawl.cards.green.*;
import com.megacrit.cardcrawl.cards.blue.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.*;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.actions.*;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.utility.*;
import com.megacrit.cardcrawl.cards.*;
import com.megacrit.cardcrawl.characters.*;
import com.megacrit.cardcrawl.core.*;
//import com.megacrit.cardcrawl.core.Settings.GameLanguage;
import com.megacrit.cardcrawl.potions.*;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.helpers.*;
import com.megacrit.cardcrawl.localization.*;
import com.megacrit.cardcrawl.rooms.*;
import com.megacrit.cardcrawl.unlock.*;
import com.megacrit.cardcrawl.vfx.*;
import com.megacrit.cardcrawl.vfx.cardManip.ShowCardAndObtainEffect;

import basemod.*;
import basemod.helpers.*;
import basemod.interfaces.*;

import java.lang.reflect.*;
import java.io.*;

import com.megacrit.cardcrawl.random.Random;
import com.megacrit.cardcrawl.relics.*;
import java.util.*;
import java.util.function.*;

import ascensionMod.cards.*;
import ascensionMod.relics.*;

@SpireInitializer
public class AscensionPlusMod implements PostInitializeSubscriber,
//EditCardsSubscriber,
EditStringsSubscriber, 
EditRelicsSubscriber,
PostDungeonInitializeSubscriber,
EditKeywordsSubscriber//,
//PostCreateStartingRelicsSubscriber,
//PostCreateIroncladStartingRelicsSubscriber,
{
	public static final Logger logger = LogManager.getLogger(AscensionPlusMod.class.getName());
	
	private static final String MODNAME = "Ascension Plus";
    private static final String AUTHOR = "Beta Chess";
    private static final String DESCRIPTION = "Adds additional levels of ascension";
    
    private static int AscLvl;
    
    public static int getAscLvl() {
    	int retVal = AscLvl;
    	return retVal;
    }
    
    public AscensionPlusMod() {
    	BaseMod.subscribeToEditStrings(this);
    	BaseMod.subscribeToPostInitialize(this);
    	BaseMod.subscribeToEditRelics(this);
    	BaseMod.subscribeToPostDungeonInitialize(this);
    	BaseMod.subscribeToEditKeywords(this);
    	//BaseMod.subscribeToPostCreateStartingRelics(this);
    	//BaseMod.subscribeToEditCards(this);
    }
    
    public static void initialize() {
    	logger.info("------------------------- AscensionPlus initiation -------------------------");
    	
    	AscensionPlusMod AscMod = new AscensionPlusMod();
    	
    	logger.info("----------------------------------------------------------------------------");
    }
    
    @Override
    public void receivePostInitialize() {
    	
    	// Mod badge
    	logger.info("Creating mod badge");
        
    	Texture badgeTexture = new Texture("img/AscensionBadge.png");
        ModPanel settingsPanel = new ModPanel();
        //settingsPanel.addLabel("This mod does not have any settings.", 400.0f, 700.0f, (me) -> {});
        settingsPanel.addSlider("Asc+ Lvl", 0.0f, 650.0f, 15.0f, "", (me) -> {
        	float ascFloat = me.value;
        	AscLvl = Math.round(ascFloat*15);
        	ascensionMod.patches.AscendersBanePatch.AL = AscLvl;
        	//logger.info(ascFloat); 
        	logger.info(Math.round(ascFloat*15));
        });
        BaseMod.registerModBadge(badgeTexture, MODNAME, AUTHOR, DESCRIPTION, settingsPanel);
        
        logger.info("Mod badge created"); 
    }
    
    /*@Override
    public void receiveEditCards() {
        logger.info("begin editing cards");
        try {
        	BaseMod.addCard(new TestCard());
        } 
        catch (Exception e) {
            logger.error("Error while adding cards",e);
        }
        logger.info("done editing cards");
    }*/
    
    public void receiveEditRelics() {
    	logger.info("Editing relics");
    	
    	BaseMod.addRelic(new StarOfAscension1(), RelicType.SHARED);
    	BaseMod.addRelic(new StarOfAscension2(), RelicType.SHARED);
    	BaseMod.addRelic(new TestOfThePizza(), RelicType.SHARED);
    	
    	logger.info("Done editing relics");
    }
    
    @Override
    public void receiveEditKeywords() {
    	logger.info("Creating Keywords");

        Type typeToken = new TypeToken<Map<String, Keyword>>(){}.getType();
        Gson gson = new Gson();
        String strings = loadJson("localization/eng/AscensionKeywordStrings.json");
        
        Map<String,Keyword> keywords = (Map<String,Keyword>)gson.fromJson(strings, typeToken);
        for (Keyword kw : keywords.values()) {
            BaseMod.addKeyword(kw.NAMES, kw.DESCRIPTION);
        }
        
        logger.info("Done Creating Keywords");
    }
    
    @Override
    public void receiveEditStrings() {
    	logger.info("Editing strings");
    	
    	//BaseMod.loadCustomStrings(CardStrings.class, loadJson("localization/eng/AscensionCardStrings.json"));
    	BaseMod.loadCustomStrings(RelicStrings.class, loadJson("localization/eng/AscensionRelicStrings.json"));
    	
    	logger.info("Done editing strings");
    }
    
    private static String loadJson(String jsonPath) {
        return Gdx.files.internal(jsonPath).readString(String.valueOf(StandardCharsets.UTF_8));
    }
    
    /*@Override
	public void receiveEditKeywords() {
		logger.info("Creating Keyword Celestial");
		Keyword fetch = fetchLocalizer.getFetch();
	    BaseMod.addKeyword(fetch.NAMES, fetch.DESCRIPTION);
	}*/
    	
    public void receivePostDungeonInitialize() {
    	ArrayList<String> relicsToAdd = new ArrayList<>();
    	ArrayList<String> cardsToAdd = new ArrayList<>();
            
    	if(AscLvl > 0) {
    		relicsToAdd.add("StarOfAscension" + AscLvl);
    	}
    	if(AscLvl == 2) {
    		
    		AbstractDungeon.effectList.add(new ShowCardAndObtainEffect(new Madness(), Settings.WIDTH / 2.0F - 350.0F * Settings.scale, Settings.HEIGHT / 2.0F));
    	}
    	
    	
    	//add relics
		int relicIndex = AbstractDungeon.player.relics.size();
		int relicRemoveIndex = relicsToAdd.size() - 1;
		while (relicsToAdd.size() > 0) {
			System.out.println("Attempting to add: " + relicsToAdd.get(relicRemoveIndex));
			AbstractRelic relic = RelicLibrary.getRelic(relicsToAdd.remove(relicRemoveIndex));
			System.out.println("Found relic is: " + relic);
			AbstractRelic relicCopy = relic.makeCopy();
			relicCopy.instantObtain(AbstractDungeon.player, relicIndex, true);
			relicRemoveIndex--;
			relicIndex++;
		}
	}
    
    
    
    
    /*@Override
	public boolean receivePostCreateStartingRelics(ArrayList<String> relicsToAdd) {
    	logger.info("DOING THE THING ---------------------------------------------------------------------");
		relicsToAdd.add("Black Blood"); // here we simply give the player black blood in addition to their other starting relics
		UnlockTracker.markRelicAsSeen("Black Blood");
		logger.info("Done DOING THE THING ----------------------------------------------------------------");
		return true;
	}*/
}