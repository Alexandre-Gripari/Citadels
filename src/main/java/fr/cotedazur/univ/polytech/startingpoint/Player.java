package fr.cotedazur.univ.polytech.startingpoint;


import fr.cotedazur.univ.polytech.startingpoint.cards.Character;
import fr.cotedazur.univ.polytech.startingpoint.cards.Constructions;
import fr.cotedazur.univ.polytech.startingpoint.cards.Wonder;
import fr.cotedazur.univ.polytech.startingpoint.players.City;
import fr.cotedazur.univ.polytech.startingpoint.players.Hand;
import fr.cotedazur.univ.polytech.startingpoint.strategies.Strategy;
import fr.cotedazur.univ.polytech.startingpoint.strategies.Strategy1;

import java.util.ArrayList;
import java.util.List;

public class Player implements Comparable<Player> {
    private int number;
    private int gold;
    private Hand hand;
    private City city;
    private Character character;
    private List<Wonder> wonders = new ArrayList<>();
    private Strategy strategy;

    private boolean isDead=false;

    public Player(int i, int gold, Hand hand, City city) {
        this(i,gold,hand,city,new Strategy1("Agressif"));
    }

    public void kill() {
        this.isDead = true;
    }

    public boolean isDead() {
        return isDead;
    }

    public Character getCharacter() {
        return character;
    }

    public void resurrect(){this.isDead = false;}

    public Player(int number, int gold, Hand hand){
        this(number, gold, hand, new City(), new Strategy1("Agressif"));
    }

    public Player(int number, Hand hand){
        this(number, 2, hand, new City(), new Strategy1("Agressif"));
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    /* Constructeur utile aux tests */
    public Player(int number, int gold, Hand hand, City city, Strategy strategy){
        this.number = number;
        this.gold = gold;
        this.hand = hand;
        this.city = city;
        this.strategy = strategy;
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

    public List<Wonder> getWonders() {
        return wonders;
    }

    public void setWonders(List<Wonder> wonders) {
        this.wonders = wonders;
    }

    public void play(Draw draw, Player[] players) {
        if(isDead) {
            resurrect();
            return;
        }
        System.out.println("Le joueur " + number + " est le " + character.getName());
        strategy.play(players, draw);

        /*if (hand.isEmpty()) {
            hand.add(takeConstruction(draw));
            for (Wonder w : getWonders()) {
                if (w.getName().equals("Observatoire") || w.getName().equals("Bibliothèque")) useWonder(draw, w.getWondersPower());
            }
        }
        else takeGold();
        for (Wonder w : getWonders()) {
            if (w.getName().equals("Laboratoire") || w.getName().equals("Manufacture") || w.getName().equals("Ecole de magie")) useWonder(draw, w.getWondersPower());
        }
        buildConstruction();
        useAbility(draw, players);*/
    }

    public void drawConstruction(Draw d, int n) {
        ArrayList<Constructions> temp = takeConstructions(d, n);
        hand.add(strategy.chooseCard(temp, this));
        putBack(d, temp);
    }

    /* Renvoie la construction (quartier) la moins chère entre les 2 en haut de la pioche */
    public ArrayList<Constructions> takeConstructions(Draw d, int n) {
        ArrayList<Constructions> tab = new ArrayList<>();
        for (int i = 0; i < n; i++)
            tab.add(d.draw());
        return tab;
    }

    public void putBack(Draw d, ArrayList<Constructions> constructions) {
        for (Constructions construction : constructions)
            d.add(construction);
    }

    public void buildConstruction(Constructions c){
        if (c == null) return;
        getCity().add(c);
        if (c instanceof Wonder) getWonders().add((Wonder) c);
        getHand().remove(c);
        gold -= c.getValue();
        System.out.println("Le joueur " + getNumber() + " construit " + c);
    }

    public void pick(Draw d, int n) {
        switch (n){
            case -1 : break;
            case 0 :
                takeGold();
                break;
            default: drawConstruction(d, n);
        }
    }

    /*public void useAbility(Draw draw, Player self, Player opponent, Constructions c, Player[] players){
        switch (character.getNumber()){
            case 1:
                character.ability(opponent);
                break;
            case 2:
                character.ability(self, opponent);
                break;
            case 3:
                if (opponent == null) character.ability(self);
                else character.ability(self, opponent);
                break;
            case 4, 5, 6:
                character.ability(this);
                break;
            case 7:
                character.ability(draw, self);
                break;
            case 8:
                Constructions destroyedCons = character.ability(c ,self, opponent);
                if (destroyedCons != null) WondersPower.CIMETIERE.power(destroyedCons, players);
                break;
        }
    }

    public void useWonder(Wonder wonder, Player player, Draw d, Constructions c ) {
        switch (wonder.getName()){
            case "Laboratoire":
                WondersPower.LABORATOIRE.power(c, player, d);
                break;
            case "Manufacture":
                WondersPower.MANUFACTURE.power(player, d);
                break;
            case "Observatoire":
                WondersPower.OBSERVATOIRE.power(player, d);
                break;
            case "Bibliothèque":
                WondersPower.BIBLIOTHEQUE.power(player, d);
                break;
            case "Ecole de magie":
                WondersPower.ECOLE_DE_MAGIE.power(player);
                break;
        }
    }*/

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

    public void draw(Draw d, int nb){
        for(int i=0;i<nb;i++){
            this.hand.add(d.draw());
        }
    }

    public void chooseCharacter(List<Character> characters, Player[] players){
        Character chosenCharacter = strategy.chooseCharacter(this, characters, players);
        this.setCharacter(chosenCharacter);
        characters.remove(chosenCharacter);
    }
  
    public void setCharacter(Character character){
        this.character=character;
    }


    public void useCimetiery(Constructions c) {
        if (c.getValue() <= gold) {
            gold -= c.getValue();
            System.out.println("Le joueur " + number + " a utilisé le cimetière pour récupérer " + c);
            hand.add(c);
        }
    }
public void discardConstruction(Constructions c, Draw d){
        d.add(c);
        hand.remove(c);
    }

    public Strategy getStrategy() {
        return strategy;
    }

    public void destroyConstruction(Constructions c) {
        city.remove(c);
    }
}

