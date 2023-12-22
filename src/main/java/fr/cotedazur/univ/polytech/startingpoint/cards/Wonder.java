package fr.cotedazur.univ.polytech.startingpoint.cards;

public class Wonder extends Constructions {

    private WondersPower power;

    public Wonder (String name, WondersPower power, int value) {
        super(name, Color.MERVEILLEUX, value);
        this.power = power;
    }

    public void power(){
        this.power.power();
    }

    public WondersPower getWondersPower(){
        return this.power;
    }

}
