package fr.cotedazur.univ.polytech.startingpoint.cards;

import fr.cotedazur.univ.polytech.startingpoint.Draw;
import fr.cotedazur.univ.polytech.startingpoint.Player;

public class Wonder extends Constructions {

    private WondersPower power;

    public Wonder (String name, WondersPower power, int value) {
        super(name, Color.MERVEILLEUX, value);
        this.power = power;
    }

    public void power(){
        this.power.power();
    }
    public void power(Player player, Draw d) {
        this.power.power(player, d);
    }

    public WondersPower getWondersPower(){
        return this.power;
    }

}
