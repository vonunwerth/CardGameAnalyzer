import Stacks.CardStack;
import Stacks.Phase10Stack;
import Stacks.Instances.PlayingCard;

import java.util.ArrayList;
import java.util.Collections;

public class Draw10Simulator {

    private static final int NUMBEROFCARDS = 10;

    public static void main(String[] args) {
        CardStack stack = new Phase10Stack();
        System.out.println(stack);
        ArrayList<PlayingCard> deck = drawCards(stack);
        System.out.println(findNLings(deck,4));
        System.out.println("Fertig");
    }

    /**
     * @param deck Your hand cards
     * @param n Search n-lings
     * @return Count of n-lings found
     */
    private static int findNLings(ArrayList<PlayingCard> deck, int n) {
        //Calculate doubles, maxrows and cards of color
        int missturn = 0;
        int joker = 0;
        int drillings = 0;

        //Found the real n-Lings in your hand cards without joker
        ArrayList<Integer> pairs = new ArrayList(); //Speichert alle Paare
        for (int number1 = 0; number1 < deck.size(); number1++) {
            if (deck.get(number1).getValue() == 0) {
                joker++;
                continue;
            } else if (deck.get(number1).getValue() == -1) {
                missturn++;
                continue;
            }
            int proofNumber = deck.get(number1).getValue();
            int proofCounter = 0;
            for (int number2 = number1; number2 < deck.size(); number2++) {
                    if (deck.get(number2).getValue() == proofNumber) {
                        proofCounter++;
                    }
            }
            pairs.add(proofCounter);
        }

        //Sorts the found n-lings by n-ling size and adds joker to the n-lings, which need the fewest cards
        Collections.sort(pairs);
        Collections.reverse(pairs);

        int nLings = 0;
        for (Integer size : pairs) {
            if (size >= n) nLings++;
            while (size < n && joker > 0) {
                size++;
                joker--;
                if (size >= n) nLings++;
            }
        }
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
            System.out.println(stack.getStack().get(cards));
        }
        return deck;
    }
}
