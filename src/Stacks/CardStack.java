package Stacks;

import Stacks.Instances.CardProperties.Suit;
import Stacks.Instances.CardProperties.Value;
import Stacks.Instances.PlayingCard;

import java.util.ArrayList;

public class CardStack {

    ArrayList<PlayingCard> stack = new ArrayList<>();
    boolean doubleSet; //Doppeltes Kartenspiel, wie bei Rommee
    int cards; //Anzahl Aller Karten
    int joker; //Anzahl der Joker
    int specials; //Spezialkarten, wie Aussetzen

    public CardStack() {
        doubleSet = true;
        specials = 0;
        joker = 6;
        int standardCards = doubleSet ? Suit.values().length * Value.values().length * 2 : Suit.values().length * Value.values().length;
        cards = standardCards + specials + joker;
        //System.out.println(cards);
    }

    public ArrayList<PlayingCard> getStack() {
        return stack;
    }
}
