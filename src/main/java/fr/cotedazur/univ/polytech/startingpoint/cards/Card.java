package fr.cotedazur.univ.polytech.startingpoint.cards;

public abstract class Card {
    protected CardsName name;
    protected Color color;

    protected Card(CardsName name, Color color){
        this.name=name;
        this.color=color;
    }

    public CardsName getName(){
        return this.name;
    }

    public Color getColor(){
        return this.color;
    }
    @Override
    public boolean equals(Object o){
        if (this == o) return true;
        if (!(o instanceof Card)) return false;
        return this.getName().equals(((Card)o).getName());
    }

    @Override
    public String toString(){
        return this.name.getCardName();
    }

}
