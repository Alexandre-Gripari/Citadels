package main.java.fr.cotedazur.univ.polytech.startingpoint.cards;

import main.java.fr.cotedazur.univ.polytech.startingpoint.Cards;

public class Character extends Cards {
    private int number;

    public Character(String name, Color color,int number) {
        super(name, color);
        this.number=number;
    }
}
