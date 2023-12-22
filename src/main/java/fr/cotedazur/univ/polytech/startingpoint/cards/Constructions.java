package fr.cotedazur.univ.polytech.startingpoint.cards;

public class Constructions extends Card {

    private int value;
    private boolean constructed=false;

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

    public boolean isConstructed(){
        return this.constructed;
    }

    public void construct(){
        this.constructed=true;
    }

    public void destruct(){
        this.constructed=false;
    }

}
