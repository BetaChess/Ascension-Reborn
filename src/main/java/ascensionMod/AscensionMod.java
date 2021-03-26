package ascensionMod;
  
import java.nio.charset.StandardCharsets;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.blights.AbstractBlight;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.curses.AscendersBane;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.*;
import com.megacrit.cardcrawl.helpers.*;
import com.megacrit.cardcrawl.localization.*;

import basemod.*;
import basemod.interfaces.*;

import java.io.IOException;
import java.lang.reflect.*;

import java.util.*;

import ascensionMod.UI.CharSelectScreenUI;
import ascensionMod.blights.*;

@SpireInitializer
public class AscensionMod implements PostInitializeSubscriber,
EditStringsSubscriber, 
EditRelicsSubscriber,
PostDungeonInitializeSubscriber,
EditKeywordsSubscriber,
EditCardsSubscriber
{
	public static final Logger logger = LogManager.getLogger(AscensionMod.class.getName());
	
	
	private static final String MODNAME = "Ascension Reborn";
    private static final String AUTHOR = "Beta Chess and Yhrcyt";
    private static final String DESCRIPTION = "Adds additional levels of ascension";
    
    public static ArrayList<String> blightIds = new ArrayList<String>();

    
    public static final int MAXMODASCENSIONLEVEL = 25;
    public static final int MINMODASCENSIONLEVEL = -20;
    
    public static boolean customAscensionRun = true;
    
    
    public static int AbsoluteAscensionLevel = 20;
    
    public static boolean BlightedRun = false;
    
    public static SpireConfig config;
    
    
    public static Boolean ascScaling;
    
    
    
    // !!! creating constructer 
	public AscensionMod() {
    	BaseMod.subscribe(this);
    }
    
    
    // !!! Initialize mod
    public static void initialize() {
    	logger.info("------------------------- AscensionPlus initiation -------------------------");
    	
    	
    	@SuppressWarnings("unused")
		AscensionMod AscMod = new AscensionMod();
    	
    	
    	logger.info("----------------------------------------------------------------------------");
    }
     
    
    // !!! creating mod badge and settings
    @Override
    public void receivePostInitialize() {

    	Prefs Ipref = SaveHelper.getPrefs("STSDataVagabond");;
    	Prefs Spref = SaveHelper.getPrefs("STSDataTheSilent");
    	Prefs Dpref = SaveHelper.getPrefs("STSDataDefect");

    	
    	Properties defaults = new Properties();
    	defaults.setProperty("Ascension_SCALING", "true");
    	defaults.setProperty("MaxAscLvl_SILENT", "" + Spref.getInteger("ASCENSION_LEVEL", 1));
    	defaults.setProperty("MaxAscLvl_IRONCLAD", "" + Ipref.getInteger("ASCENSION_LEVEL", 1));
    	defaults.setProperty("MaxAscLvl_DEFECT", "" + Dpref.getInteger("ASCENSION_LEVEL", 1));
    	defaults.setProperty("MaxAscLvl", "21");
    	defaults.setProperty("Presets", "Def/1/2");
    	try {
			config = new SpireConfig("ascensionMod", "config", defaults);
		} catch (IOException e) {
			e.printStackTrace();
		}
    	
    	ascScaling = Boolean.parseBoolean(config.getString("Ascension_SCALING"));
    	
    	// Mod badge
    	logger.info("Creating mod badge");
    	
    	ModPanel settingsPanel = new ModPanel();
        
    	Texture badgeTexture = new Texture("img/AscensionBadge.png");
    	
		ModLabeledToggleButton ascScalingButton = new ModLabeledToggleButton("Turn on normal ascension progression",
        		350.0f, 400.0f, Settings.CREAM_COLOR, FontHelper.charDescFont,
       		ascScaling, settingsPanel, (label) -> {}, (button) -> {
        			ascScaling = button.enabled;
        			//BaseMod.maybeSetBoolean("ascScale", ascScaling);
        			config.setString("Ascension_SCALING", ("" + ascScaling));
					try {
						AscensionMod.config.save();
					} catch (IOException e) {
						e.printStackTrace();
					}
        		});
    	settingsPanel.addUIElement(ascScalingButton);
    	BaseMod.registerModBadge(badgeTexture, MODNAME, AUTHOR, DESCRIPTION, settingsPanel);
        
    	logger.info("Mod badge created"); 
    }
    
    
    // !!! adding cards
    @Override
    public void receiveEditCards() {
        logger.info("begin editing cards");
        try {
        	BaseMod.addCard(new ascensionMod.cards.AscendersBane());
        } 
        catch (Exception e) {
            logger.error("Error while adding cards",e);
        }
        logger.info("done editing cards");
    }
    
    

    // !!! adding relics
    public void receiveEditRelics() {
    	logger.info("Editing relics");
    	
    	logger.info("Done editing relics");
    }
    
    
    // !!! creating keywords
    @Override
    public void receiveEditKeywords() {
    	/*logger.info("Creating Keywords");

        Type typeToken = new TypeToken<Map<String, Keyword>>(){}.getType();
        Gson gson = new Gson();
        String strings = loadJson("localization/eng/AscensionKeywordStrings.json");
        
        @SuppressWarnings("unchecked")
		Map<String,Keyword> keywords = (Map<String,Keyword>)gson.fromJson(strings, typeToken);
        for (Keyword kw : keywords.values()) {
            BaseMod.addKeyword(kw.NAMES, kw.DESCRIPTION);
        }
        
        logger.info("Done Creating Keywords");*/
    }

    
    // !!! loading strings for cards and relics
    @Override
    public void receiveEditStrings() {
    	logger.info("Editing strings");
    	
    	BaseMod.loadCustomStrings(RelicStrings.class, loadJson(getLocalizationPath() + "AscensionRelicStrings.json"));
    	BaseMod.loadCustomStrings(CardStrings.class, loadJson(getLocalizationPath() + "AscensionCardStrings.json"));
    	BaseMod.loadCustomStrings(BlightStrings.class, loadJson(getLocalizationPath() + "AscensionBlightStrings.json"));
    	
    	BaseMod.loadCustomStrings(UIStrings.class, loadJson(getLocalizationPath() + "AscensionUiStrings.json"));
    	BaseMod.loadCustomStrings(UIStrings.class, loadJson(getLocalizationPath() + "AscensionDesc.json"));
    	BaseMod.loadCustomStrings(UIStrings.class, loadJson(getLocalizationPath() + "AscensionMinusDesc.json"));
    	BaseMod.loadCustomStrings(UIStrings.class, loadJson(getLocalizationPath() + "RenameBoxStrings.json"));
    	BaseMod.loadCustomStrings(UIStrings.class, loadJson(getLocalizationPath() + "AscModScreenStrings.json"));
    	
    	logger.info("Done editing strings");
    }
    
    
    // !!! json loader
    private static String loadJson(String jsonPath) {
        return Gdx.files.internal(jsonPath).readString(String.valueOf(StandardCharsets.UTF_8));
    }
    
    // !!! Giving player proper relics (and possibly cards) for the asc lvl chosen
    public void receivePostDungeonInitialize() {
    	AbsoluteAscensionLevel = AbstractDungeon.ascensionLevel;
    	
    	if (customAscensionRun)
    		AbsoluteAscensionLevel = 0;
    	
    	if(AbstractDungeon.floorNum == 0)
    	{
	
    		AscensionMod.BlightedRun = false;
    		
	    	if(AbsoluteAscensionLevel >= 25  || (AscensionMod.customAscensionRun && CharSelectScreenUI.ascScreen.posAscButtons.get(24).toggledOn))
	    	{
	    		logger.info(AbstractPlayer.customMods.contains("Blight Chests"));
	    		AbstractPlayer.customMods.add("Blight Chests");
	    		AbstractDungeon.actNum = 4;
	    		logger.info(AbstractDungeon.actNum);
	    	}
	    	
	    	//ArrayList<String> relicsToAdd = new ArrayList<>();
	    	ArrayList<AbstractBlight> blightsToAdd = new ArrayList<>();
	    	
	    	//ArrayList<String> cardsToAdd = new ArrayList<>();
	    	if(AbsoluteAscensionLevel > 20) {
	    		if(AbsoluteAscensionLevel < 25) {
	    			blightsToAdd.add(new StarOfAscension());
	    		}
	    		
	    		if(AbsoluteAscensionLevel >= 22) {
	    			blightsToAdd.add(new CursedBank());
	    		}
	    		
	    		if(AbsoluteAscensionLevel >= 24) {
	    			AbstractDungeon.player.masterDeck.removeCard(AbstractDungeon.player.masterDeck.getBottomCard()); 
	    			AbstractDungeon.player.masterDeck.addToTop(new ascensionMod.cards.AscendersBane());
	    		}
	    		
	    		if(AbsoluteAscensionLevel >= 25) {
	    			blightsToAdd.add(new MegaStarOfAscension());
	    			blightsToAdd.add(new CursedFlame());
	    		}
	    	}
	    	
	    	if (AscensionMod.customAscensionRun)
	    	{
	    		int count = 0;
	    		for (int i = 0; i < 5; i++)
	    		{
	    			if (CharSelectScreenUI.ascScreen.posAscButtons.get(19+i).toggledOn)
	    			{
	    				count++;
	    			}
	    		}
	    		
	    		if (count < 5 && count > 0)
	    		{
	    			blightsToAdd.add(new StarOfAscension());
	    		}
	    		else if (count == 5)
	    		{
	    			blightsToAdd.add(new MegaStarOfAscension());
	    		}
	    		
	    		if (CharSelectScreenUI.ascScreen.posAscButtons.get(21).toggledOn)
	    		{
	    			blightsToAdd.add(new CursedBank());
	    		}
	    		
	    		if (CharSelectScreenUI.ascScreen.posAscButtons.get(24).toggledOn)
	    		{
	    			blightsToAdd.add(new CursedFlame());
	    		}
	    		
	    		if(CharSelectScreenUI.ascScreen.posAscButtons.get(23).toggledOn) {
	    			AbstractDungeon.player.masterDeck.removeCard(AbstractDungeon.player.masterDeck.getBottomCard()); 
	    			AbstractDungeon.player.masterDeck.addToTop(new ascensionMod.cards.AscendersBane());
	    		}
	    		
	    	}
	    	
	    	// ascension minus levels
	    	if (AscensionMod.AbsoluteAscensionLevel <= -9 || (AscensionMod.customAscensionRun && CharSelectScreenUI.ascScreen.negAscButtons.get(8).toggledOn)) {
				AbstractDungeon.player.increaseMaxHp(AbstractDungeon.player.getAscensionMaxHPLoss(), false);
			}
			if (AscensionMod.AbsoluteAscensionLevel <= -18 || (AscensionMod.customAscensionRun && CharSelectScreenUI.ascScreen.negAscButtons.get(17).toggledOn)) {
				AbstractDungeon.player.energy.energyMaster++;
			}
			if (AscensionMod.AbsoluteAscensionLevel <= -19 || (AscensionMod.customAscensionRun && CharSelectScreenUI.ascScreen.negAscButtons.get(18).toggledOn)) {
				AbstractDungeon.player.masterHandSize++;
			}
	    	
	    	
	    	//add relics
			/*int relicIndex = AbstractDungeon.player.relics.size();
			int relicRemoveIndex = relicsToAdd.size() - 1;
			while (relicsToAdd.size() > 0) {
				logger.info("Attempting to add: " + relicsToAdd.get(relicRemoveIndex));
				AbstractRelic relic = RelicLibrary.getRelic(relicsToAdd.remove(relicRemoveIndex));
				logger.info("Found relic is: " + relic);
				AbstractRelic relicCopy = relic.makeCopy();
				relicCopy.instantObtain(AbstractDungeon.player, relicIndex, true);
				relicRemoveIndex--;
				relicIndex++;
			}*/
			
			//add blights
			for(AbstractBlight b : blightsToAdd)
			{
				b.instantObtain(AbstractDungeon.player, AbstractDungeon.player.blights.size(), true);
			}
    	}
	}
    
    public static String getLocalizationPath()
    {
    	switch(Settings.language)
    	{
    	case ENG:
    		return "localization/eng/";
    		
    	case KOR:
    		return "localization/kor/";
    		
    	case ZHS:
    		return "localization/zhs/";
    		
    	default:
    		return "localization/eng/";	
    	}
    }
    
    public static boolean getCustomToggleState(int level)
    {
    	if (CharSelectScreenUI.ascScreen == null)
    	{
    		return false;
    	}
    	
    	if (level == 0)
    	{
    		logger.error("0 was passed to method: 'getCustomToggleState', returning false");
    		return false;
    	}
    	if (level < 0)
    	{
    		return (AscensionMod.customAscensionRun && CharSelectScreenUI.ascScreen.negAscButtons.get(Math.abs(level) - 1).toggledOn);
    	}
    	else
    	{
    		return (AscensionMod.customAscensionRun && CharSelectScreenUI.ascScreen.posAscButtons.get(level - 1).toggledOn);
    	}
    	
    }
    
}