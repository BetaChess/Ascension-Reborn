package ascensionMod.cards;

 
import basemod.abstracts.CustomCard;

public abstract class AbstractAscensionCard extends CustomCard {
	public AbstractAscensionCard(String id, String name, int cost, String description, CardType type, CardColor color, CardRarity rarity, CardTarget target) {
        super(id, name, "img/test.png", cost, description, type, color, rarity, target);
    } 
}