package ascensionMod;

import java.nio.charset.StandardCharsets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.evacipated.cardcrawl.modthespire.lib.*;
//import com.megacrit.cardcrawl.core.Settings.GameLanguage;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.helpers.*;
import com.megacrit.cardcrawl.localization.*;

import basemod.*;
import basemod.helpers.*;
import basemod.interfaces.*;

import java.lang.reflect.*;

import com.megacrit.cardcrawl.relics.*;
import java.util.*;

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
    
    private static int AscLvl = 5;
    
    
    
    public static int getAscLvl() {
    	int retVal = AscLvl;
    	return retVal;
    }
    
    
    // !!! creating constructer 
	public AscensionPlusMod() {
    	BaseMod.subscribe(this);
    }
    
    
    // !!! Initialize mod
    public static void initialize() {
    	logger.info("------------------------- AscensionPlus initiation -------------------------");
    	
    	@SuppressWarnings("unused")
		AscensionPlusMod AscMod = new AscensionPlusMod();
    	
    	
    	logger.info("----------------------------------------------------------------------------");
    }
    
    
    // !!! creating mod badge and settings
    @SuppressWarnings("deprecation")
	@Override
    public void receivePostInitialize() {
    	ascensionMod.patches.AscendersBanePatch.AL = AscLvl;
    	ascensionMod.patches.getPurgeablePatch.AL = AscLvl;
    	ascensionMod.relics.StarOfAscension.AL = AscLvl;
    	ascensionMod.relics. MegaStarOfAscension.AL = AscLvl;
    	ascensionMod.patches.HealPatch.AL = AscLvl;
    	
    	// Mod badge
    	logger.info("Creating mod badge");
        
    	Texture badgeTexture = new Texture("img/AscensionBadge.png");
        ModPanel settingsPanel = new ModPanel();
        settingsPanel.addSlider("Asc+ Lvl", 0.0f, 650.0f, 15.0f, "", (me) -> {
        	float ascFloat = me.value;
        	AscLvl = Math.round(ascFloat*15);
        	ascensionMod.patches.AscendersBanePatch.AL = AscLvl;
        	ascensionMod.patches.getPurgeablePatch.AL = AscLvl;
        	ascensionMod.relics.StarOfAscension.AL = AscLvl;
        	ascensionMod.relics.MegaStarOfAscension.AL = AscLvl;
        	ascensionMod.patches.HealPatch.AL = AscLvl;
        	//logger.info(ascFloat); 
        	logger.info(Math.round(ascFloat*15));
        });
        BaseMod.registerModBadge(badgeTexture, MODNAME, AUTHOR, DESCRIPTION, settingsPanel);
        
        logger.info("Mod badge created"); 
    }
    
    
    // !!! adding cards
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
    
    
    // !!! adding relics
    public void receiveEditRelics() {
    	logger.info("Editing relics");
    	
    	BaseMod.addRelic(new StarOfAscension(), RelicType.SHARED);
    	BaseMod.addRelic(new TestOfThePizza(), RelicType.SHARED);
    	//if(AbstractDungeon.player.name.equals("Jrmiah")) {
    	BaseMod.addRelic(new JSpecialRelic(), RelicType.SHARED);
    	BaseMod.addRelic(new CursedBank(), RelicType.SHARED);
    	BaseMod.addRelic(new MegaStarOfAscension(), RelicType.SHARED);
    	//}

    	
    	logger.info("Done editing relics");
    }
    
    
    // !!! creating keywords
    @Override
    public void receiveEditKeywords() {
    	logger.info("Creating Keywords");

        Type typeToken = new TypeToken<Map<String, Keyword>>(){}.getType();
        Gson gson = new Gson();
        String strings = loadJson("localization/eng/AscensionKeywordStrings.json");
        
        @SuppressWarnings("unchecked")
		Map<String,Keyword> keywords = (Map<String,Keyword>)gson.fromJson(strings, typeToken);
        for (Keyword kw : keywords.values()) {
            BaseMod.addKeyword(kw.NAMES, kw.DESCRIPTION);
        }
        
        logger.info("Done Creating Keywords");
    }

    
    // !!! loading strings for cards and relics
    @Override
    public void receiveEditStrings() {
    	logger.info("Editing strings");
    	
    	//BaseMod.loadCustomStrings(CardStrings.class, loadJson("localization/eng/AscensionCardStrings.json"));
    	BaseMod.loadCustomStrings(RelicStrings.class, loadJson("localization/eng/AscensionRelicStrings.json"));
    	
    	logger.info("Done editing strings");
    }
    
    
    // !!! json loader
    private static String loadJson(String jsonPath) {
        return Gdx.files.internal(jsonPath).readString(String.valueOf(StandardCharsets.UTF_8));
    }
    
    
    // !!! Giving player proper relics (and possibly cards) for the asc+ lvl chosen
    public void receivePostDungeonInitialize() {
    	ArrayList<String> relicsToAdd = new ArrayList<>();
    	//ArrayList<String> cardsToAdd = new ArrayList<>();
    	logger.info("------------------------------------- Asc+ lvl " +  AscLvl + " -------------------------------------");
    	if(AscLvl > 0) {
    		if(AscLvl < 5) {
    			relicsToAdd.add("StarOfAscension");
    		}
    		
    		if(AbstractDungeon.player.name.equals("Jrmiah")) {
    			relicsToAdd.add("JSpecialRelic");
    		}
    		
    		if(AscLvl >= 3) {
    			relicsToAdd.add("CursedBank");
    		}
    		
    		if(AscLvl >= 5) {
    			relicsToAdd.add("MegaStarOfAscension");
    		}
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
}