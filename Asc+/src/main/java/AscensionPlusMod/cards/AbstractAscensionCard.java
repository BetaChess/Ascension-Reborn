package ascensionMod.cards;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.relics.AbstractRelic;

import basemod.abstracts.CustomCard;
import ascensionMod.AscensionPlusMod;

public abstract class AbstractAscensionCard extends CustomCard {
	public AbstractAscensionCard(String id, String name, int cost, String description, CardType type, CardColor color, CardRarity rarity, CardTarget target) {
        super(id, name, "img/test.png", cost, description, type, color, rarity, target);
    }
}