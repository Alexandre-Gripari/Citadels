package fr.cotedazur.univ.polytech.startingpoint.cards;

import fr.cotedazur.univ.polytech.startingpoint.Draw;
import fr.cotedazur.univ.polytech.startingpoint.Player;

public class Wonder extends Constructions {

    private final WondersPower power;

    public Wonder (String name, int value, WondersPower power) {

        super(name, Color.MERVEILLEUX, value);
        this.power = power;
    }

    public void power(){
        this.power.power();
    }
    public void power(Player player, Draw d) {
        this.power.power(player, d);
    }

    public void power(Constructions c){
        this.power.power(c);
    }

    public void power(Player p, Draw d) {this.power.power(p,d);}
    public WondersPower getWondersPower(){
        return this.power;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Wonder)) return false;
        return this.getWondersPower().equals(((Wonder) o).getWondersPower());
    }

    @Override
    public int hashCode() {
        return this.getWondersPower().hashCode();
    }

    public void setColor(Color color) {
        this.color = color;
    }

}
