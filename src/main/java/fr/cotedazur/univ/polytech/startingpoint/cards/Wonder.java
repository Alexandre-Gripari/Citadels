package fr.cotedazur.univ.polytech.startingpoint.cards;

public class Wonder extends Constructions {

    private final WondersPower power;

    public Wonder (String name, int value, WondersPower power) {
        super(name, Color.MERVEILLEUX, value);
        this.power = power;
    }

    public WondersPower getPower() {return this.power;}
}
