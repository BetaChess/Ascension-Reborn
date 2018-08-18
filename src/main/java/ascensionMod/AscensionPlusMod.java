package ascensionMod;
  
import java.nio.charset.StandardCharsets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.helpers.*;
import com.megacrit.cardcrawl.localization.*;

import basemod.*;
import basemod.helpers.*;
import basemod.interfaces.*;

import java.io.IOException;
import java.lang.reflect.*;

import com.megacrit.cardcrawl.relics.*;
import java.util.*;

import ascensionMod.relics.*;

@SpireInitializer
public class AscensionPlusMod implements PostInitializeSubscriber,
EditStringsSubscriber, 
EditRelicsSubscriber,
PostDungeonInitializeSubscriber,
EditKeywordsSubscriber
{ 
	public static final Logger logger = LogManager.getLogger(AscensionPlusMod.class.getName());
	
	private static final String MODNAME = "Ascension Plus";
    private static final String AUTHOR = "Beta Chess";
    private static final String DESCRIPTION = "Adds additional levels of ascension";
    
    
    public static int AbsoluteAscensionLevel = 15;
    
    public static SpireConfig config;
    
    
    private static Boolean EasterEgg = true;
    
    
    
    
    
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
    @Override
    public void receivePostInitialize() {

    	Prefs Ipref = SaveHelper.getPrefs("STSDataVagabond");;
    	Prefs Spref = SaveHelper.getPrefs("STSDataTheSilent");
    	Prefs Dpref = SaveHelper.getPrefs("STSDataDefect");

    	
    	Properties defaults = new Properties();
    	defaults.setProperty("MaxAscLvl_SILENT", "" + Spref.getInteger("ASCENSION_LEVEL", 1));
    	defaults.setProperty("MaxAscLvl_IRONCLAD", "" + Ipref.getInteger("ASCENSION_LEVEL", 1));
    	defaults.setProperty("MaxAscLvl_DEFECT", "" + Dpref.getInteger("ASCENSION_LEVEL", 1));
    	defaults.setProperty("MaxAscLvl", "21");
    	try {
			config = new SpireConfig("ascensionMod", "config", defaults);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    	// Mod badge
    	logger.info("Creating mod badge");
    	
    	ModPanel settingsPanel = new ModPanel();
        
    	Texture badgeTexture = new Texture("img/AscensionBadge.png");
    	@SuppressWarnings("deprecation")
		ModLabeledToggleButton EasterEggButton = new ModLabeledToggleButton("Turn on easter egg relics",
        		350.0f, 600.0f, Settings.CREAM_COLOR, FontHelper.charDescFont,
       		EasterEgg, settingsPanel, (label) -> {}, (button) -> {
        			EasterEgg = button.enabled;
        			BaseMod.maybeSetBoolean("Easter", EasterEgg);
        		});
    	settingsPanel.addUIElement(EasterEggButton);
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
    	BaseMod.addRelic(new JSpecialRelic(), RelicType.SHARED);
    	BaseMod.addRelic(new CursedBank(), RelicType.SHARED);
    	BaseMod.addRelic(new MegaStarOfAscension(), RelicType.SHARED);
    	BaseMod.addRelic(new CursedFlame(), RelicType.SHARED);
    	
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
    	if(AbstractDungeon.player.name.equals("Jrmiah") && (EasterEgg)) {
			relicsToAdd.add("AscMod:JSpecialRelic");
		}
    	if(AbsoluteAscensionLevel > 20) {
    		if(AbsoluteAscensionLevel < 25) {
    			relicsToAdd.add("AscMod:StarOfAscension");
    		}
    		
    		if(AbsoluteAscensionLevel >= 22) {
    			relicsToAdd.add("AscMod:CursedBank");
    		}
    		
    		if(AbsoluteAscensionLevel >= 25) {
    			relicsToAdd.add("AscMod:MegaStarOfAscension");
    			relicsToAdd.add("AscMod:CursedFlame");
    		}
    	}
    	
    	
    	//add relics
		int relicIndex = AbstractDungeon.player.relics.size();
		int relicRemoveIndex = relicsToAdd.size() - 1;
		while (relicsToAdd.size() > 0) {
			logger.info("Attempting to add: " + relicsToAdd.get(relicRemoveIndex));
			AbstractRelic relic = RelicLibrary.getRelic(relicsToAdd.remove(relicRemoveIndex));
			logger.info("Found relic is: " + relic);
			AbstractRelic relicCopy = relic.makeCopy();
			relicCopy.instantObtain(AbstractDungeon.player, relicIndex, true);
			relicRemoveIndex--;
			relicIndex++;
		}
	}
}