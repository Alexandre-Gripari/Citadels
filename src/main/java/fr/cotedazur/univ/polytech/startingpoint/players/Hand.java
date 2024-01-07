package fr.cotedazur.univ.polytech.startingpoint.players;

import fr.cotedazur.univ.polytech.startingpoint.cards.Constructions;
import java.util.ArrayList;
import java.util.List;

public class Hand{ // extends Arraylist ?

    private ArrayList<Constructions> hand;

    public Hand(){
        this.hand = new ArrayList<>();
    }

    public List<Constructions> getHand() { return hand; }

    public boolean isEmpty() {return hand.isEmpty();}

    public void add (Constructions c) { hand.add(c); }

    public Integer size() { return hand.size(); }

    public Constructions get(int i) { return  hand.get(i); }

    public void remove(int i) { hand.remove(i); }

    public  String toString() {
        String main = "";
        for (Constructions constructions : hand)
            main += constructions + " ";
        return main;
    }

    public boolean contains(Constructions x) {
        return hand.contains(x);
    }

    public void setHand(Hand hand) {
        this.hand = (ArrayList<Constructions>) hand.getHand();
    }

    public void set(int i, Constructions c) {
        hand.set(i, c);
    }
}