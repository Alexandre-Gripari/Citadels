package fr.cotedazur.univ.polytech.startingpoint;


import fr.cotedazur.univ.polytech.startingpoint.cards.Character;
import fr.cotedazur.univ.polytech.startingpoint.cards.Constructions;
import fr.cotedazur.univ.polytech.startingpoint.players.City;
import fr.cotedazur.univ.polytech.startingpoint.players.Hand;

import java.util.List;

public class Player implements Comparable<Player> {
    private int number;
    private int gold;
    private Hand hand;
    private City city;
    private Character character;

    private boolean isDead=false;

    public void kill() {
        this.isDead = true;
    }

    public boolean isDead() {
        return isDead;
    }

    public Character getCharacter() {
        return character;
    }



    public Player(int number, Hand hand){
        this(number, 2, hand, new City());
    }

    public void setGold(int gold) {
        this.gold = gold;
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

    public void play(Draw draw) {
        System.out.println("Le joueur " + number + " est le " + character.getName());
        if (!isDead) {
            if (hand.isEmpty()) hand.add(takeConstruction(draw));
            else takeGold();
            buildConstruction();
        }
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

    public void chooseCharacter(List<Character> characters){
        character = characters.get(0);
        characters.remove(0);
        //System.out.println("Le joueur " + number + " a choisi le personnage " + character.getName());
    }
    //Pour les tests
    public void setCharacter(Character character){
        this.character=character;
    }

}

