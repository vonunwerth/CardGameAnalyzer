package Stacks;

import Stacks.Instances.CardProperties.Suit;
import Stacks.Instances.CardProperties.Value;
import Stacks.Instances.PlayingCard;

public class Phase10Stack extends CardStack { //TODO Stacks überarbeiten
    public Phase10Stack() {
        doubleSet = true;
        specials = 4;
        joker = 8;
        int standardCards = doubleSet ? Suit.values().length * Value.values().length * 2 : Suit.values().length * Value.values().length;
        cards = standardCards + specials + joker;
        //System.out.println(cards);
        for (int suits = 0; suits < Suit.values().length; suits++) {
            for (int values = 0; values < Value.values().length; values++) {
                stack.add(new PlayingCard(Suit.values()[suits], Value.values()[values]));
            }
        }
        for (int joker = 0; joker < this.joker; joker++) {
            stack.add(new PlayingCard("joker"));
        }
        for (int missturn = 0; missturn < specials; missturn++) {
            stack.add(new PlayingCard("missturn"));
        }

    }

    @Override
    public String toString() {
        StringBuilder ret = new StringBuilder();
        for (PlayingCard card : stack) {
            ret.append(card.toString());
        }
        return ret.toString();
    }
}
