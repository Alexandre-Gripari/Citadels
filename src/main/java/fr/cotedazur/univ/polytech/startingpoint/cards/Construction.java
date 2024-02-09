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

    public boolean equals(Construction c){
        return this.name.equals(c.getName());
    }

    public String toString() {
        return super.toString() + "(" + value + ")";
    }

}
