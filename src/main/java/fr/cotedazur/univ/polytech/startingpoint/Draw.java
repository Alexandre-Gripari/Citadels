package fr.cotedazur.univ.polytech.startingpoint;

import fr.cotedazur.univ.polytech.startingpoint.cards.Constructions;

import java.util.*;

public class Draw {

    // deck de construction (queue)

    private Queue<Constructions> drawDeck;

    public Draw() {
        drawDeck = new LinkedList<>();
    }

    public void addXConstructions(Constructions c, int x) {
        for (int i = 0; i < x; i++) {
            drawDeck.add(c);
        }
    }

    public void shuffle() {
        Collections.shuffle((List<Constructions>) drawDeck);
    }


    public Queue<Constructions> getDeck() {
        return drawDeck;
    }

    public String toString() {
        String s = "";
        for (Constructions c : drawDeck) {
            s += c + "\n";
        }
        return s;
    }

    public Constructions draw() {
        return drawDeck.poll();
    }

    public void add(Constructions c) {
        drawDeck.add(c);
    }

    public int size() {
    	return drawDeck.size();
    }

    public boolean contains(Constructions x) {
        return drawDeck.contains(x);
    }
}
