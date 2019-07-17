package ascensionMod.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class AscendersBane extends AbstractAscensionCard {
	static public String ID = "AscMod:ascendersbane";
	public static final String NAME;
    public static final String DESCRIPTION;
    static public String imgpath = "cards/ascendersbane.png";
    
	private static final CardStrings cardStrings;
    
	

	public AscendersBane() {
		super(ID, NAME, imgpath, -2, DESCRIPTION, CardType.CURSE, CardColor.CURSE, CardRarity.SPECIAL, CardTarget.NONE);
		
	}

	
	
	@Override
	public void upgrade() {}

	@Override
	public void use(AbstractPlayer arg0, AbstractMonster arg1) {}
	
	static {
        cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
        NAME = cardStrings.NAME;
        DESCRIPTION = cardStrings.DESCRIPTION;
    }

}
