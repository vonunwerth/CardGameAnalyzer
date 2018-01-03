import DataObjects.NLing;
import DataObjects.Stacks.Phase;
import Stacks.CardStack;
import Stacks.Instances.PlayingCard;
import Stacks.Phase10Stack;

import java.util.*;

public class Phase10Simulator {

    private static final int NUMBEROFCARDS = 10;

    private static final int ROUNDSTOPLAY = 1000000;

    public static void main(String[] args) {
        Map<Phase, Integer> phases = new HashMap<>();
        phases.put(new Phase(1, 1, 5, 0, 0), 0);
        phases.put(new Phase(7, 2, 4, 0, 0), 0);
        phases.put(new Phase(9, 1, 5, 1, 2, 0, 0), 0);
        phases.put(new Phase(10, 1, 5, 1, 3, 0, 0), 0);

        for (int round = 1; round <= ROUNDSTOPLAY; round++) {
            CardStack stack = new Phase10Stack();
            ArrayList<PlayingCard> deck = drawCards(stack);
            int joker = 0;
            for (PlayingCard playingCard : deck) {
                if (playingCard.getValue() == 0) joker++;
            }
            for (Phase phase : phases.keySet()) {
                if (phase.checkCondition(joker, countNLings(deck), phase.getMaxRow(), phase.getMaxSuit())) {
                    phases.put(phase, phases.get(phase) + 1);
                }
            }
        }
        //TODO Map sortieren

        //Ausgabe aller Phasen mit ihren Anzahlen
        for (Phase phase : phases.keySet()) {
            System.out.println("Phase " + phase.getNumber() + " wird mit einer Wahrscheinlichkeit von " + ((double) (phases.get(phase)) / (double) (ROUNDSTOPLAY)) * 100 + " Prozent gezogen.");
        }
        //System.out.println("Zu " + ((double)(ROUNDSTOPLAY -  numberOfAllPhasesFound)/(double)(ROUNDSTOPLAY))*100 + " Prozent wird keine Phase gezogen.");
    }

    /**
     * @param deck Your hand cards
     * @return Count of n-lings found
     */
    private static ArrayList<NLing> countNLings(ArrayList<PlayingCard> deck) {
        //Calculate doubles, maxrows and cards of color
        int missturn = 0;
        int joker = 0;

        //Found the real n-Lings in your hand cards without joker
        ArrayList<NLing> nLings = new ArrayList(); //Speichert alle Paare
        for (int number1 = 0; number1 < deck.size(); number1++) {
            if (deck.get(number1).getValue() == 0) {
                joker++;
                continue;
            } else if (deck.get(number1).getValue() == -1) {
                missturn++;
                continue;
            }
            int proofNumber = deck.get(number1).getValue();
            NLing nLing = new NLing(proofNumber, 0);
            for (int number2 = number1; number2 < deck.size(); number2++) {
                if (deck.get(number2).getValue() == proofNumber) {
                    nLing.increaseN();
                }
            }
            nLings.add(nLing);
        }

        //Sorts the found n-lings by n-ling size and adds joker to the n-lings, which need the fewest cards
        Collections.sort(nLings, Comparator.comparingInt(NLing::getN));
        Collections.reverse(nLings);

        return nLings;
    }

    /**
     * Draw your hand cards
     * @param stack From this card stack
     * @return Your new Handcards
     */
    private static ArrayList<PlayingCard> drawCards(CardStack stack) {
        Collections.shuffle(stack.getStack());
        ArrayList<PlayingCard> deck = new ArrayList<>();
        for (int cards = 0; cards < NUMBEROFCARDS; cards++) {
            deck.add(stack.getStack().get(cards));
            //System.out.println(stack.getStack().get(cards));
        }
        return deck;
    }
}
