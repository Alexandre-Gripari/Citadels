package fr.cotedazur.univ.polytech.startingpoint.cards;

public class Construction extends Card {

    private int value;

    public Construction(CardsName name, Color color, int value) {
        super(name, color);
        this.value = value;
    }

    public int getValue(){
        return this.value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (!(o instanceof Wonder)) return false;
        return this.getName().equals(((Construction)o).getName());
    }
    @Override
    public int hashCode(){return super.hashCode();}
    @Override
    public String toString() {
        return super.toString() + "(" + value + ")";
    }

}
