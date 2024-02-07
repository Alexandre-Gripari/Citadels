package fr.cotedazur.univ.polytech.startingpoint;

import fr.cotedazur.univ.polytech.startingpoint.cards.Construction;

import java.util.*;

public class Draw {

    private Queue<Construction> drawDeck;

    public Draw() {
        drawDeck = new LinkedList<>();
    }

    public void addXConstructions(Construction c, int x) {
        for (int i = 0; i < x; i++) {
            drawDeck.add(c);
        }
    }

    public void shuffle() {
        Collections.shuffle((List<Construction>) drawDeck);
    }


    public Queue<Construction> getDeck() {
        return drawDeck;
    }

    public String toString() {
        String s = "";
        for (Construction c : drawDeck) {
            s += c + "\n";
        }
        return s;
    }

    public Construction draw() {
        return drawDeck.poll();
    }

    public void add(Construction c) {
        drawDeck.add(c);
    }

    public int size() {
    	return drawDeck.size();
    }

    public boolean contains(Construction x) {
        return drawDeck.contains(x);
    }

    public Construction peek() {
        return drawDeck.peek();
    }
}
