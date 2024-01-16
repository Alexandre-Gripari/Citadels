package fr.cotedazur.univ.polytech.startingpoint;


import fr.cotedazur.univ.polytech.startingpoint.cards.Character;
import fr.cotedazur.univ.polytech.startingpoint.cards.Constructions;
import fr.cotedazur.univ.polytech.startingpoint.cards.Wonder;
import fr.cotedazur.univ.polytech.startingpoint.cards.WondersPower;
import fr.cotedazur.univ.polytech.startingpoint.players.City;
import fr.cotedazur.univ.polytech.startingpoint.players.Hand;
import fr.cotedazur.univ.polytech.startingpoint.strategies.Strategy;

import java.util.ArrayList;
import java.util.List;

public class Player implements Comparable<Player> {
    private int number;
    private int gold;
    private Hand hand;
    private City city;
    private Character character;
    private List<Wonder> wonders = new ArrayList<>();

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

    public void resurrect(){this.isDead = false;}

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
        strategy.play(draw, players);
    }

    public void drawConstruction(Draw d, int n) {
        ArrayList<Constructions> temp = takeConstructions(d, n);
        hand.add(strategy.chooseCard(temp));
        putBack(d, temp);
    }

    /* Renvoie la construction (quartier) la moins chère entre les 2 en haut de la pioche */
    public ArrayList<Constructions> takeConstructions(Draw d, int n){
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
        getCity().add(c);
        getHand().remove(c);
    }

    public void useAbility(Draw draw, Player ... players) {
        switch (character.getNumber()){
            case 1:
                character.ability(players);
                break;
            case 2:
                character.ability(players[0], players[1]);
                break;
            case 3, 7:
                character.ability(draw, players);
                break;
            case 4, 5, 6:
                character.ability(this);
                break;
            case 8:
                Constructions destroyedCons = character.ability(0, players);
                if (destroyedCons != null) WondersPower.CIMETIERE.power(destroyedCons, players);
                break;
        }
    }

    public void useWonder(Draw draw, WondersPower wonderPower) {
        switch (wonderPower.name()) {
            case "LABORATOIRE":
                if (!this.getHand().isEmpty()) wonderPower.power(this.getHand().get(0), this, draw);
                break;
            case "MANUFACTURE", "OBSERVATOIRE", "BIBLIOTHEQUE":
                wonderPower.power(this, draw);
                break;
            case "ECOLE_DE_MAGIE":
                wonderPower.power(this);
                break;
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

    public void draw(Draw d, int nb){
        for(int i=0;i<nb;i++){
            this.hand.add(d.draw());
        }
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
}

