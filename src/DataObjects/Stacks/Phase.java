package DataObjects.Stacks;

import DataObjects.NLing;

import java.util.ArrayList;
import java.util.Iterator;

public class Phase {
    private int number;
    private ArrayList<NLing> nLings = new ArrayList<>();
    private int maxRow;
    private int maxSuit;

    public Phase(int number, ArrayList<NLing> nLings, int maxRow, int maxSuit) {
        this.number = number;
        this.nLings = nLings;
        this.maxRow = maxRow;
        this.maxSuit = maxSuit;
    }

    public Phase(int number, int nLingCount, int nLingSize, int maxRow, int maxSuit) {
        this.number = number;
        this.maxRow = maxRow;
        this.maxSuit = maxSuit;
        for (int i = 1; i <= nLingCount; i++) {
            this.nLings.add(new NLing(0, nLingSize));
        }
    }

    public Phase(int number, int nLingCount, int nLingSize, int nLingCount2, int nLingSize2, int maxRow, int maxSuit) {
        this.number = number;
        this.maxRow = maxRow;
        this.maxSuit = maxSuit;
        for (int i = 1; i <= nLingCount; i++) {
            this.nLings.add(new NLing(0, nLingSize));
        }
        for (int i = 1; i <= nLingCount2; i++) {
            this.nLings.add(new NLing(0, nLingSize2));
        }
    }

    public boolean checkCondition(int joker, ArrayList<NLing> nLings, int maxRow, int maxSuit) {
        boolean ret = true;
        if (maxRow < this.maxRow) ret = false;
        if (maxSuit < this.maxSuit) ret = false;
        int nLingsFound = 0;
        boolean nLingFound;
        for (NLing nLing : this.nLings) {
            nLingFound = false;
            //Are all needed NLings in the given NLings
            Iterator<NLing> iter = nLings.iterator();
            while (iter.hasNext()) {
                NLing nl = iter.next();
                if (!nLingFound) {
                    while (joker > 0 && nLing.getN() > nl.getN()) {
                        nl.increaseN();
                        joker--;
                    }
                    if (nLing.getN() <= nl.getN()) {
                        nLingFound = true;
                        nLingsFound++;
                        iter.remove(); //Should work :D
                    }
                }
            }
        }
        if (nLingsFound < this.nLings.size()) ret = false;
        return ret;
    }

    public int getMaxRow() {
        return maxRow;
    }

    public int getMaxSuit() {
        return maxSuit;
    }

    public int getNumber() {
        return number;
    }
}
