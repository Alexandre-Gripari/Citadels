package fr.cotedazur.univ.polytech.startingpoint;

import fr.cotedazur.univ.polytech.startingpoint.cards.Color;
import fr.cotedazur.univ.polytech.startingpoint.cards.Constructions;
import fr.cotedazur.univ.polytech.startingpoint.players.City;
import fr.cotedazur.univ.polytech.startingpoint.players.Hand;
import fr.cotedazur.univ.polytech.startingpoint.strategies.Strategy1;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlayerTest {

    Hand hand1;
    Hand hand2;
    Player p1;
    Player p2;
    Draw draw;
    Player[] opponentOfP1 = new Player[2];
    Player[] opponentOfP2 = new Player[2];


    Constructions cathédrale = new Constructions("Cathédrale", Color.RELIGIEUX, 5);
    Constructions chateau = new Constructions("Château", Color.NOBLE, 4);
    Constructions monastère = new Constructions("Monastère", Color.RELIGIEUX, 3);
    Constructions marché = new Constructions("Marché", Color.COMMERCIAL, 2);
    Constructions comptoir = new Constructions("Comptoir", Color.COMMERCIAL, 3);


    void init() {
        hand1 = new Hand();

        p1 = new Player(1, hand1);
        p1.getHand().add(new Constructions("Temple", Color.RELIGIEUX, 1));
        p1.getHand().add(new Constructions("Forteresse", Color.SOLDATESQUE, 2));

        draw = new Draw();
        draw.addXConstructions(cathédrale, 1);
        draw.addXConstructions(chateau, 1);
        draw.addXConstructions(monastère, 1);
        draw.addXConstructions(marché, 1);
        draw.addXConstructions(comptoir, 1);

        hand2 = new Hand();

        p2 = new Player(2,1, hand2);
        p2.getHand().add(new Constructions("Temple", Color.RELIGIEUX, 1));
        p2.getHand().add(new Constructions("Forteresse", Color.SOLDATESQUE, 2));

        opponentOfP2[0] = p2;
        opponentOfP2[1] = p1;
    }

    /*@Test
    void play() {
        init();
        assertEquals(2, p2.getGold());
        p2.play(draw, opponentOfP2);
        assertEquals(2, p2.getGold());
        assertEquals("Temple", p2.getCity().get(0).getName());
        assertEquals("Forteresse", p2.getHand().get(0).getName());
        p2.play(draw, opponentOfP2);
        assertEquals(2, p2.getGold());
        assertEquals("Forteresse", p2.getCity().get(1).getName());
        assertTrue(p2.getHand().isEmpty());
        p2.play(draw, opponentOfP2);
        assertEquals(2, p2.getGold());
        assertEquals("Château", p2.getHand().get(0).getName());
        p2.play(draw, opponentOfP2);
        assertEquals(0, p2.getGold());
        assertEquals(0, p2.getHand().size());
        assertEquals(3, p2.getCity().size());
        p2.play(draw, opponentOfP2);
        assertEquals(0, p2.getGold());
        assertEquals("Marché", p2.getHand().get(0).getName());
        assertEquals("Château", p2.getCity().get(2).getName());
        assertEquals(1, p2.getHand().size());
        assertEquals(3, p2.getCity().size());
    }*/

    @Test
    void takeConstruction() {
        init();
        assertEquals(new ArrayList<Constructions>(Arrays.asList(cathédrale,chateau)), p1.takeConstructions(draw,2));
        assertEquals(new ArrayList<Constructions>(Arrays.asList(monastère,marché, comptoir)), p1.takeConstructions(draw,3));
    }

    @Test
    void testPutBack() {
        init();
        ArrayList<Constructions> constructions = p1.takeConstructions(draw, 2);
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
        Constructions temple = new Constructions("Temple", Color.RELIGIEUX, 1);
        Constructions forteresse = new Constructions("Forteresse", Color.SOLDATESQUE, 2);
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
        assertEquals(0, p1.compareTo(p2));
        p1.getCity().add(new Constructions("Temple", Color.RELIGIEUX, 1));
        assertEquals(1, p1.compareTo(p2));
        p2.getCity().add(new Constructions("Temple", Color.RELIGIEUX, 1));
        assertEquals(0, p1.compareTo(p2));
        p1.getCity().add(new Constructions("Temple", Color.RELIGIEUX, 1));
        assertEquals(1, p1.compareTo(p2));
        p2.getCity().add(new Constructions("Monastère", Color.RELIGIEUX, 3));
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

        p.pick(draw, s.goldOrCard(new Player[]{p}, draw));
        assertEquals(1, p.getHand().size() );
        assertEquals(2, p.getGold());

        p.pick(draw, s.goldOrCard(new Player[]{p}, draw));
        assertEquals(1, p.getHand().size() );
        assertEquals(4, p.getGold());
    }
}
