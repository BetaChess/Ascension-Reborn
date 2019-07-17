package ascensionMod.cards;

 
import basemod.abstracts.CustomCard;

public abstract class AbstractAscensionCard extends CustomCard {
	public AbstractAscensionCard(String id, String name, String imgPath, int cost, String description, CardType type, CardColor color, CardRarity rarity, CardTarget target) {
        super(id, name, imgPath, cost, description, type, color, rarity, target);
    } 
}