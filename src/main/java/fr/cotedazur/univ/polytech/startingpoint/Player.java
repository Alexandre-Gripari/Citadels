package fr.cotedazur.univ.polytech.startingpoint;


import fr.cotedazur.univ.polytech.startingpoint.cards.Card;
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

    public Character getCharacter() {
        return character;
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

    public void play(Draw draw, Player[] opponents) {
        System.out.println("Le joueur " + number + " est le " + character.getName());
        if (hand.isEmpty()) hand.add(takeConstruction(draw));
        else takeGold();
        buildConstruction();
        useAbility(opponents);
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

    public void useAbility(Player[] opponents) {
        if (character.compareTo(Character.VOLEUR) < 0) character.ability(this);
        else if (character.compareTo(Character.CONDOTTIERE) < 0) character.ability(this, opponents[0]); // choisie sans stratégie
        // Le condottiere détruit la première carte qu'il peut
        else {
            for (Player opponent : opponents) { // choix sans stratégie
                if (opponent.getCity().size() != 0) {
                    for (int i = 0; i < opponent.getCity().size(); i++) {
                        if (opponent.getCity().get(i).getValue() <= gold) {
                            character.ability(i, this, opponent);
                            return;
                        }
                    }
                }
            }
        }
    }

    public void takeGold(){
        gold += 2;
        System.out.println("Le joueur " + number + " a pris 2 pièces d'or");
    }

    public void addGold(int gold) {
        this.gold += gold;
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

        public void addGold(int n) {
            gold += n;
        }
}

