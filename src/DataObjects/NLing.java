package DataObjects;

public class NLing {
    private int n;
    private int value;

    public NLing(int value, int n) {
        this.n = n;
        this.value = value;
    }

    public void increaseN() {
        this.n++;
    }

    public int getN() {
        return n;
    }
}
