package fr.cotedazur.univ.polytech.startingpoint;


import fr.cotedazur.univ.polytech.startingpoint.cards.Character;
import fr.cotedazur.univ.polytech.startingpoint.cards.Color;
import fr.cotedazur.univ.polytech.startingpoint.cards.Constructions;
import fr.cotedazur.univ.polytech.startingpoint.cards.Wonder;
import fr.cotedazur.univ.polytech.startingpoint.players.City;
import fr.cotedazur.univ.polytech.startingpoint.players.Hand;
import fr.cotedazur.univ.polytech.startingpoint.strategies.Strategy;
import fr.cotedazur.univ.polytech.startingpoint.strategies.Strategy1;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Player implements Comparable<Player> {
    private int numberOfVictory = 0;
    private int numberOfDefeat = 0;
    private int numberOfDraw = 0;
    private int cumulatedScore = 0;
    private int number;
    private int gold;
    private Hand hand;
    private City city;
    private Character character;
    private List<Wonder> wonders = new ArrayList<>();
    private Strategy strategy;

    private Integer score = 0;

    private boolean isDead=false;


    private final static Logger LOGGER = Logger.getLogger(Player.class.getName());

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

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public void play(Draw draw, Player[] players) {
        if(isDead) {
            resurrect();
            return;
        }
        MyLogger.log(Level.INFO, "Le joueur " + number + " est le " + character.getName());
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
        if (c.getColor().equals(Color.MERVEILLEUX)) getWonders().add((Wonder) c);
        getHand().remove(c);
        gold -= c.getValue();
        MyLogger.log(Level.INFO, "Le joueur " + getNumber() + " construit " + c);
    }

    public void pick(Draw d, int n) {
        if (n <= 0) wonders.get(-n).power(this, d);
        else if (n == 1) takeGold();
        else drawConstruction(d, n);
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
        MyLogger.log(Level.INFO, "Le joueur " + number + " a pris 2 pièces d'or");
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
            MyLogger.log(Level.INFO, "Le joueur " + number + " a utilisé le cimetière pour récupérer " + c);
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

    public int getScore() {return score;}

    public void setScore(Integer newScore) {score = newScore;}

    public int getNumberOfVictory() {
        return numberOfVictory;
    }

    public void setNumberOfVictory(int numberOfVictory) {
        this.numberOfVictory = numberOfVictory;
    }

    public int getNumberOfDefeat() {
        return numberOfDefeat;
    }

    public void setNumberOfDefeat(int numberOfDefeat) {
        this.numberOfDefeat = numberOfDefeat;
    }

    public int getNumberOfDraw() {
        return numberOfDraw;
    }

    public void setNumberOfDraw(int numberOfDraw) {
        this.numberOfDraw = numberOfDraw;
    }

    public int getCumulatedScore() {
        return cumulatedScore;
    }

    public void setCumulatedScore(int cumulatedScore) {
        this.cumulatedScore = cumulatedScore;
    }

    public double getAverageScore() {
        return (double) getCumulatedScore() /1000;
    }

    public void reset(){
        this.hand = new Hand();
        this.city = new City();
        this.character = null;
        this.wonders = new ArrayList<>();
        this.isDead = false;
        this.gold = 2;
        this.score = 0;
    }
    public String[] getStats() {
        String strat = this.getStrategy().getDescription();
        String wins = String.valueOf(getNumberOfVictory());
        String winRate = String.valueOf(this.getNumberOfVictory() / 20);
        String losses = String.valueOf(this.getNumberOfDefeat());
        String lossRate = String.valueOf(this.getNumberOfDefeat() / 20);
        String draws = String.valueOf(this.getNumberOfDraw());
        String drawRate = String.valueOf(this.getNumberOfDraw() / 20);
        return new String[]{strat, wins, winRate+"%", losses, lossRate+"%", draws, drawRate+"%"};
    }
}

