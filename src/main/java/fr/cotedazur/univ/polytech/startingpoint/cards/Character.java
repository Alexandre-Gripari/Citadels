package fr.cotedazur.univ.polytech.startingpoint.cards;


public class Character extends Card {
    private int number;

    public Character(String name, Color color,int number) {
        super(name, color);
        this.number=number;
    }
}
