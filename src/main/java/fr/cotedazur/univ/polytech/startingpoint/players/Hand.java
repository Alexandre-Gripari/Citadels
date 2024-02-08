package fr.cotedazur.univ.polytech.startingpoint.players;

import fr.cotedazur.univ.polytech.startingpoint.Player;
import fr.cotedazur.univ.polytech.startingpoint.cards.Construction;
import java.util.ArrayList;
import java.util.List;

public class Hand{ // extends Arraylist ?

    public void setHand(List<Construction> hand) {
        this.hand = hand;
    }

    private List<Construction> hand;

    public Hand(){
        this.hand = new ArrayList<>();
    }

    public List<Construction> getHand() { return hand; }

    public boolean isEmpty() {return hand.isEmpty();}

    public void add (Construction c) { hand.add(c); }

    public Integer size() { return hand.size(); }

    public Construction get(int i) { return  hand.get(i); }

    public void remove(int i) { hand.remove(i); }

    public  String toString() {
        String main = "";
        for (Construction constructions : hand)
            main += constructions + " ";
        return main;
    }

    public boolean contains(Construction x) {
        return hand.contains(x);
    }

    public void setHand(Hand hand) {
        this.hand = (ArrayList<Construction>) hand.getHand();
    }

    public void remove(Construction c) {
        hand.remove(c);
    }

    public Construction min() {
        Construction c = new Construction("null", null, 110);
      
        for (Construction construction : hand)
            if (c.getValue() >= construction.getValue()) c = construction;
        
        if (c.getName().equals("null")) return null;
        return c;
    }

    public Construction minNotInCity(Player player) {
        Construction c = new Construction("null", null, 110);

        if (player.getHand().isEmpty()) return null;
        for (Construction construction : hand)
            if (construction != null && c.getValue() >= construction.getValue()
                    && !player.getCity().getCity().contains(construction)) c = construction;

        if (c.getName().equals("null")) return null;
        return c;
    }

    public Construction max() {
        Construction c = new Construction("null", null, -1);

        for (Construction construction : hand)
            if (c.getValue() <= construction.getValue()) c = construction;

        if (c.getName().equals("null")) return null;
      
        return c;
    }
}