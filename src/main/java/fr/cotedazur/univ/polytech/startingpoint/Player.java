package fr.cotedazur.univ.polytech.startingpoint;


import fr.cotedazur.univ.polytech.startingpoint.cards.Constructions;
import fr.cotedazur.univ.polytech.startingpoint.players.City;
import fr.cotedazur.univ.polytech.startingpoint.players.Hand;

public class Player implements Comparable<Player> {
    private int number;
    private int gold;
    private Hand hand;
    private City city;

    public Player(int number, Hand hand){
        this(number, 2, hand, new City());
    }

    /* Constructeur utile aux tests */
    public Player(int number, int gold, Hand hand, City city){
        this.number = number;
        this.gold = gold;
        this.hand = hand;
        this.city = city;
    }

    public int getNumber() {
        return number;
    }

    public int getGold() {
        return gold;
    }

    public Hand getHand() {
        return hand;
    }

    public City getCity() {
        return city;
    }

    public void play(Draw draw){
        if (hand.isEmpty()) hand.add(takeConstruction(draw));
        else takeGold();
        buildConstruction();
    }


    /* Renvoie la construction (quartier) la moins chère entre les 2 en haut de la pioche */
    public Constructions takeConstruction(Draw d){
        Constructions c1 = d.draw();
        Constructions c2 = d.draw();
        if (c1.getValue() <= c2.getValue()){
            d.add(c2);
            System.out.println("Le joueur " + number + " a pioché " + c1);
            return c1;
        }
        else {
            d.add(c1);
            System.out.println("Le joueur " + number + " a pioché " + c2);
            return c2;
        }
    }

    public void takeGold(){
        gold += 2;
        System.out.println("Le joueur " + number + " a pris 2 pièces d'or");
    }

    public void addGold(int gold) {
        this.gold += gold;
    }

    public void buildConstruction(){
        for (int i=0; i<hand.size(); i++){
            int valueOfConstruction = hand.get(i).getValue();
            if (gold >= valueOfConstruction){
                System.out.println("Le joueur " + number + " construit " + hand.get(i));
                gold -= valueOfConstruction;
                city.add(hand.get(i));
                hand.remove(i);
                return;
            }
        }
    }

    @Override
    public String toString(){
        return "Player " + this.getNumber();
    }

    @Override
    public int compareTo(Player other){
        return this.getCity().compareTo(other.getCity());
    }

}

