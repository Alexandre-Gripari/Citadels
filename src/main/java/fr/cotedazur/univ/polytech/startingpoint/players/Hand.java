package fr.cotedazur.univ.polytech.startingpoint.players;

import fr.cotedazur.univ.polytech.startingpoint.Player;
import fr.cotedazur.univ.polytech.startingpoint.cards.Constructions;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hand{ // extends Arraylist ?

    private List<Constructions> hand;

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

    public void remove(Constructions c) {
        hand.remove(c);

    }

    public Constructions min() {
        Constructions c = new Constructions("null", null, 110);

        for (Constructions construction : hand)
            if (c.getValue() >= construction.getValue()) c = construction;

        return c;
    }

    public Constructions minNotInCity(Player player) {
        Constructions c = new Constructions("null", null, 110);

        for (Constructions construction : hand)
            if (c.getValue() >= construction.getValue()
                    && !player.getCity().getCity().contains(construction)) c = construction;

        return c;
    }

    public Constructions max() {
        Constructions c = new Constructions("null", null, -1);

        for (Constructions construction : hand)
            if (c.getValue() <= construction.getValue()) c = construction;

        return c;
    }
}