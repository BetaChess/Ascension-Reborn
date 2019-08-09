package ascensionMod.stspatches.plus;

import java.lang.reflect.Field;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.ByRef;
import com.evacipated.cardcrawl.modthespire.lib.LineFinder;
import com.evacipated.cardcrawl.modthespire.lib.Matcher;
import com.evacipated.cardcrawl.modthespire.lib.SpireInitializer;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertLocator;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.patcher.PatchingException;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ModHelper;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.rooms.AbstractRoom;
import com.megacrit.cardcrawl.rooms.MonsterRoom;

import com.megacrit.cardcrawl.monsters.beyond.*;
import com.megacrit.cardcrawl.monsters.city.*;
import com.megacrit.cardcrawl.monsters.ending.*;
import com.megacrit.cardcrawl.monsters.exordium.*;

import ascensionMod.AscensionMod;
import javassist.CannotCompileException;
import javassist.CtBehavior;


public class PosMonsterPatch {
	public static final Logger logger = LogManager.getLogger(PosMonsterPatch.class.getName());
	
	
	@SpirePatch(
		clz = Darkling.class, 
		method = SpirePatch.CONSTRUCTOR,
		paramtypez = {float.class, float.class}
	)
	
	@SpirePatch(
		clz = OrbWalker.class, 
		method = SpirePatch.CONSTRUCTOR,
		paramtypez = {float.class, float.class}
	)
	public static class NormalMonsterPatches_2
	{
		@SpireInsertPatch(
			locator = Locator.class
		)
		public static void Insert(AbstractMonster __instance, final float x, final float y) 
		{
			logger.info("HEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEELP");
		}
		
		private static class Locator extends SpireInsertLocator 
		{
			public int[] Locate(CtBehavior ctMethodToPatch) throws CannotCompileException, PatchingException 
			{
				//Matcher finalMatcher = new Matcher.MethodCallMatcher(AbstractMonster.class, "setHp");
				Matcher finalMatcher = new Matcher.FieldAccessMatcher(AbstractDungeon.class, "ascensionLevel");
			    
				
				return LineFinder.findInOrder(ctMethodToPatch, new ArrayList<Matcher>(), finalMatcher);
			}
		}
	}
	
	
	
	
	
	
	@SpirePatch(
		clz = AbstractDungeon.class, 
		method = "setCurrMapNode"
	)
	public static class MosterPatch 
	{
		
		public static void Prefix()	
		{
			logger.info("Getting next room");
			logger.info("Room is: " + AbstractDungeon.nextRoom.room.toString());
			
			
		}
	}
	
	@SpirePatch(
		clz = AbstractMonster.class, 
		method = SpirePatch.CONSTRUCTOR,
		paramtypez = {String.class, String.class, int.class, float.class, float.class, float.class, float.class, String.class, float.class, float.class, boolean.class}
	)
	public static class test 
	{
		
		public static void Prefix()
		{
			logger.info("FFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFFF");
		}
	}
}
