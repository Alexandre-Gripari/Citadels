package fr.cotedazur.univ.polytech.startingpoint.cards;

public abstract class Cards {
    protected String name;
    protected Color color;

    protected Cards(String name, Color color){
        this.name=name;
        this.color=color;
    }

    public String getName(){
        return this.name;
    }

    public Color getColor(){
        return this.color;
    }

    public String toString(){
        return this.name;
    }
}
