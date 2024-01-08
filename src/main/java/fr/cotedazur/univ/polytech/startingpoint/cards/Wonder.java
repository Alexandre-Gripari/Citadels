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

    public void power(Constructions c){
        this.power.power(c);
    }

    public void power(Player p, Draw d) {this.power.power(p,d);}
    public WondersPower getWondersPower(){
        return this.power;
    }

}
