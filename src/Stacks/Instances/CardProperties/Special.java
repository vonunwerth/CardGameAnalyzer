package Stacks.Instances.CardProperties;

public enum Special {
    JOKER("joker"),
    MISSTURN("missturn");

    private String special;

    Special(String special) {
        this.special = special;
    }

    public String getSpecial() {
        return special;
    }
}
