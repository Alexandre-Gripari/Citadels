package fr.cotedazur.univ.polytech.startingpoint.cards;

public class Wonder extends Card {

    private WondersPower power;
    private String name;
    private Color color;

    public Wonder (String name, WondersPower power) {
        super(name, Color.MERVEILLEUX);
        this.power = power;
    }

}
