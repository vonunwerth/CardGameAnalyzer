package Stacks.Instances.CardProperties;

public enum Suit {
    YELLOW("yellow"), GREEN("green"), RED("red"), VIOLET("violet");

    private String name;

    Suit(String suit) {
        this.name = suit;
    }

    public String getName() {
        return name;
    }
}