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
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Player implements Comparable<Player> {
    private int numberOfVictory = 0;
    private int numberOfDefeat = 0;
    private int numberOfDraw = 0;


    public void setWinRate(float winRate) {
        this.winRate = winRate;
    }


    public void setLossRate(float lossRate) {
        this.lossRate = lossRate;
    }


    public void setDrawRate(float drawRate) {
        this.drawRate = drawRate;
    }

    private float winRate = 0;
    private float lossRate = 0;
    private float drawRate = 0;
    private int cumulatedScore = 0;

    public void setAverageScore(float averageScore) {
        this.averageScore = averageScore;
    }

    private float averageScore = 0;
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

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }

    public void play(Draw draw, Player[] players) {
        if (draw.getDeck().isEmpty()) {
            LOGGER.log(Level.INFO, "La pioche est vide");
            return;
        }
        if(isDead) {
            resurrect();
            return;
        }
        MyLogger.log(Level.INFO, "Le joueur " + number + " est le " + character.getName());
        strategy.play(players, draw);
    }

    public void drawConstruction(Draw d, int n) {
        ArrayList<Constructions> temp = takeConstructions(d, n);
        Constructions c = strategy.chooseCard(temp, this);
        if (c == null) return;
        hand.add(c);
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
        if (c == null || c.getColor()==null) return;
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
        float numberOfGames = (this.getNumberOfDefeat()+this.getNumberOfVictory()+this.getNumberOfDraw());
        String strat = this.getStrategy().getDescription();
        String wins = String.valueOf(getNumberOfVictory());
        String winRate1 = String.valueOf(this.getNumberOfVictory()*100/numberOfGames);
        String losses = String.valueOf(this.getNumberOfDefeat());
        String lossRate1 = String.valueOf(this.getNumberOfDefeat()*100/numberOfGames);
        String draws = String.valueOf(this.getNumberOfDraw());
        String drawRate1 = String.valueOf(this.getNumberOfDraw()*100/numberOfGames);
        String cumulatedScore1 = String.valueOf(this.getCumulatedScore());
        String averageScore1 = String.valueOf(this.getCumulatedScore()/numberOfGames);
        return new String[]{strat, wins, winRate1, losses, lossRate1, draws, drawRate1,cumulatedScore1, averageScore1};
    }
}

