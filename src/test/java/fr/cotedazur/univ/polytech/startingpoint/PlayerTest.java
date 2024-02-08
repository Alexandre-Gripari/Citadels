package fr.cotedazur.univ.polytech.startingpoint;

import fr.cotedazur.univ.polytech.startingpoint.cards.Character;
import fr.cotedazur.univ.polytech.startingpoint.cards.Color;
import fr.cotedazur.univ.polytech.startingpoint.cards.Construction;
import fr.cotedazur.univ.polytech.startingpoint.cards.Wonder;
import fr.cotedazur.univ.polytech.startingpoint.cards.WondersPower;

import fr.cotedazur.univ.polytech.startingpoint.players.City;
import fr.cotedazur.univ.polytech.startingpoint.players.Hand;
import fr.cotedazur.univ.polytech.startingpoint.strategies.Strategy1;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class PlayerTest {

    Hand hand1;
    Hand hand2;
    Player p1;
    Player p2;
    Draw draw;
    Player[] opponentOfP1 = new Player[2];
    Player[] opponentOfP2 = new Player[2];


    Construction cathédrale = new Construction("Cathédrale", Color.RELIGIEUX, 5);
    Construction chateau = new Construction("Château", Color.NOBLE, 4);
    Construction monastère = new Construction("Monastère", Color.RELIGIEUX, 3);
    Construction marché = new Construction("Marché", Color.COMMERCIAL, 2);
    Construction comptoir = new Construction("Comptoir", Color.COMMERCIAL, 3);


    @BeforeEach
    void notLog(){
        MyLogger.setLogLevel(Level.OFF);
    }

    @BeforeEach
    void init() {
        hand1 = new Hand();

        p1 = new Player(1, hand1);
        p1.getHand().add(new Construction("Temple", Color.RELIGIEUX, 1));
        p1.getHand().add(new Construction("Forteresse", Color.SOLDATESQUE, 2));

        draw = new Draw();
        draw.addXConstructions(cathédrale, 1);
        draw.addXConstructions(chateau, 1);
        draw.addXConstructions(monastère, 1);
        draw.addXConstructions(marché, 1);
        draw.addXConstructions(comptoir, 1);

        hand2 = new Hand();

        p2 = new Player(2,1, hand2);
        p2.getHand().add(new Construction("Temple", Color.RELIGIEUX, 1));
        p2.getHand().add(new Construction("Forteresse", Color.SOLDATESQUE, 2));

        opponentOfP2[0] = p2;
        opponentOfP2[1] = p1;
    }

    @Test
    void getNumberOfVictoryTest() {
        p1.setNumberOfVictory(4);
        assertEquals(4, p1.getNumberOfVictory());
    }

    @Test
    void setNumberOfVictoryTest() {
        p1.setNumberOfVictory(2);
        assertEquals(2, p1.getNumberOfVictory());
    }

    @Test
    void getNumberOfDefeatTest() {
        p1.setNumberOfDefeat(4);
        assertEquals(4, p1.getNumberOfDefeat());
    }

    @Test
    void setNumberOfDefeatTest() {
        p1.setNumberOfDefeat(2);
        assertEquals(2, p1.getNumberOfDefeat());
    }

    @Test
    void getNumberOfDrawTest() {
        p1.setNumberOfDraw(4);
        assertEquals(4, p1.getNumberOfDraw());
    }

    @Test
    void setNumberOfDrawTest() {
        p1.setNumberOfDraw(2);
        assertEquals(2, p1.getNumberOfDraw());
    }

    @Test
    void getCumulatedScoreTest() {
        p1.setCumulatedScore(4);
        assertEquals(4, p1.getCumulatedScore());
    }

    @Test
    void setCumulatedScoreTest() {
        p1.setCumulatedScore(2);
        assertEquals(2, p1.getCumulatedScore());
    }

    @Test
    void getAverageScore() {
        p1.setCumulatedScore(20000);
        assertEquals(20, p1.getAverageScore());
    }

    @Test
    void takeConstruction() {
        init();
        assertEquals(new ArrayList<Construction>(Arrays.asList(cathédrale,chateau)), p1.takeConstructions(draw,2));
        assertEquals(new ArrayList<Construction>(Arrays.asList(monastère,marché, comptoir)), p1.takeConstructions(draw,3));
    }

    @Test
    void testPutBack() {
        init();
        ArrayList<Construction> constructions = p1.takeConstructions(draw, 2);
        assertEquals(3, draw.size());
        p1.putBack(draw, constructions);
        assertEquals(5, draw.size());
    }

    @Test
    void takeGold() {
        init();
        assertEquals(2, p1.getGold());
        p1.takeGold();
        assertEquals(4, p1.getGold());
    }

    @Test
    void addGold() {
        init();
        assertEquals(1, p2.getGold());
        p2.addGold(4);
        assertEquals(5, p2.getGold());
        p2.addGold(-5);
        assertEquals(0, p2.getGold());
    }

    @Test
    void buildConstruction() {
        Player p = new Player(1, new Hand());
        Construction temple = new Construction("Temple", Color.RELIGIEUX, 1);
        Construction forteresse = new Construction("Forteresse", Color.SOLDATESQUE, 2);
        p.getHand().add(temple);
        p.getHand().add(forteresse);

        assertEquals(2, p.getHand().size());
        assertEquals(0, p.getCity().size());
        p.buildConstruction(temple);
        assertEquals(1, p.getHand().size());
        assertEquals(1, p.getCity().size());
        assertEquals(temple, p.getCity().get(0));
    }

    @Test
    void testcompareTo(){
        init();
        p1.setScore(p1.getCity().cityValue());
        p2.setScore(p2.getCity().cityValue());
        assertEquals(0, p1.compareTo(p2));
        p1.getCity().add(new Construction("Temple", Color.RELIGIEUX, 1));
        p1.setScore(p1.getCity().cityValue());
        p2.setScore(p2.getCity().cityValue());
        assertEquals(1, p1.compareTo(p2));
        p2.getCity().add(new Construction("Temple", Color.RELIGIEUX, 1));
        p1.setScore(p1.getCity().cityValue());
        p2.setScore(p2.getCity().cityValue());
        assertEquals(0, p1.compareTo(p2));
        p1.getCity().add(new Construction("Temple", Color.RELIGIEUX, 1));
        p1.setScore(p1.getCity().cityValue());
        p2.setScore(p2.getCity().cityValue());
        assertEquals(1, p1.compareTo(p2));
        p2.getCity().add(new Construction("Monastère", Color.RELIGIEUX, 3));
        p1.setScore(p1.getCity().cityValue());
        p2.setScore(p2.getCity().cityValue());
        assertEquals(-2, p1.compareTo(p2));
    }

    @Test
    void testDraw() {
        init();
        p1.draw(draw, 1);
        assertEquals(3, p1.getHand().size());
        assertEquals(4, draw.size());
        p1.draw(draw, 2);
        assertEquals(5, p1.getHand().size());
        assertEquals(2, draw.size());
        p1.draw(draw, 3);
        assertEquals(8, p1.getHand().size());
        assertEquals(0, draw.size());
    }

    @Test
    void testChooseCharacter() {
        init();
        Game game = new Game(new Player[]{p1, p2});
        game.init();
        game.discardCharacter();
        p1.chooseCharacter(game.getCharacters(), game.getOpponents(p1));
        p2.chooseCharacter(game.getCharacters(), game.getOpponents(p2));
        assertEquals(3, game.getCharacters().size());

    }

    @Test
    void testDiscardConstruction(){
        init();
        p1.discardConstruction(p1.getHand().get(0), draw);
        assertEquals(1, p1.getHand().size());
        assertEquals(6, draw.size());
        assertEquals("Forteresse", p1.getHand().get(0).getName());
    }

    @Test
    void testPick() {
        init();
        Strategy1 s = new Strategy1("Test");
        Player p = new Player(0, 2, new Hand(), new City(), s);

        p.pick(draw, s.goldOrCard(new Player[]{p}));
        assertEquals(1, p.getHand().size() );
        assertEquals(2, p.getGold());

        p.pick(draw, s.goldOrCard(new Player[]{p}));
        assertEquals(1, p.getHand().size() );
        assertEquals(4, p.getGold());
    }

    @Test
    void testReset(){
        Player player3 = new Player(3, new Hand());
        player3.getHand().add(new Construction("Temple", Color.RELIGIEUX, 1));
        player3.getHand().add(new Construction("Forteresse", Color.SOLDATESQUE, 2));
        player3.getCity().add(new Construction("Temple", Color.RELIGIEUX, 1));
        player3.getCity().add(new Construction("Forteresse", Color.SOLDATESQUE, 2));
        player3.addGold(40);
        player3.setCharacter(Character.ASSASSIN);
        player3.buildConstruction(new Wonder("Cour des miracles", 2, WondersPower.COUR_DES_MIRACLES));
        player3.reset();
        assertEquals(2, player3.getGold());
        assertEquals(0, player3.getCity().size());
        assertEquals(0, player3.getHand().size());
        assertNull(player3.getCharacter());
        assertEquals(0, player3.getWonders().size());
    }
}
