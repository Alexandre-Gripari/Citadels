package fr.cotedazur.univ.polytech.startingpoint.cards;

public class Constructions extends Card {

    private int value;

    public Constructions(String name, Color color, int value) {
        super(name, color);
        this.value = value;
    }

    public int getValue(){
        return this.value;
    }

    public boolean equals(Constructions c){
        return this.name.equals(c.getName());
    }
}
