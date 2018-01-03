package Stacks.Instances;

import Stacks.Instances.CardProperties.Special;
import Stacks.Instances.CardProperties.Suit;
import Stacks.Instances.CardProperties.Value;

public class PlayingCard {

    private Special special = null;
    private Suit suit = null;
    private Value value = null;
    
    public PlayingCard(Suit suit, Value value) {
        this.suit = suit;
        this.value = value;
        this.special = null;
    }

    public PlayingCard(String special) {
        this.suit = null;
        this.value = null;
        for (Special singleSpecial : Special.values()) {
            if (special.equals(singleSpecial.getSpecial())) {
                this.special = singleSpecial;
            }
        }
    }

    @Override
    public String toString() {
        if (special == null) {
            return "Karte: " + suit.getName() + " " + value.getValue() + "\n";
        } else {
            return "Karte: " + special.getSpecial() + "\n";
        }
    }

    public Suit getSuit() {
        return suit;
    }


    public int getValue() {
        if (special != null) {
            if (special.getSpecial().equals("joker"))
            return 0; //TODO Es kann auch 3 Ausrufezeichen geben!
            else return -1;
        }
        return this.value.getValue();
    }
}
