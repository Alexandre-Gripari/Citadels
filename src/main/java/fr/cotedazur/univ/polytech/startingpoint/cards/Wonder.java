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






}
