package fr.cotedazur.univ.polytech.startingpoint;

import fr.cotedazur.univ.polytech.startingpoint.cards.Color;
import fr.cotedazur.univ.polytech.startingpoint.cards.Constructions;
import fr.cotedazur.univ.polytech.startingpoint.players.City;
import fr.cotedazur.univ.polytech.startingpoint.players.Hand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    Hand hand1;
    Hand hand2;
    Player p1;
    Player p2;
    Draw draw;

    void init() {
        hand1 = new Hand();

        p1 = new Player(1, hand1);
        p1.getHand().add(new Constructions("Temple", Color.RELIGIEUX, 1));
        p1.getHand().add(new Constructions("Forteresse", Color.SOLDATESQUE, 2));

        draw = new Draw();
        draw.addXConstructions(new Constructions("Cathédrale", Color.RELIGIEUX, 5), 1);
        draw.addXConstructions(new Constructions("Château", Color.NOBLE, 4), 1);
        draw.addXConstructions(new Constructions("Monastère", Color.RELIGIEUX, 3), 1);
        draw.addXConstructions(new Constructions("Marché", Color.COMMERCIAL, 2), 1);
        draw.addXConstructions(new Constructions("Comptoir", Color.COMMERCIAL, 3), 1);

        hand2 = new Hand();

        p2 = new Player(2, 1, hand2, new City());
        p2.getHand().add(new Constructions("Temple", Color.RELIGIEUX, 1));
        p2.getHand().add(new Constructions("Forteresse", Color.SOLDATESQUE, 2));
    }

    @Test
    void play() {
        init();
        assertEquals(1, p2.getGold());
        p2.play(draw);
        assertEquals(2, p2.getGold());
        assertEquals("Temple", p2.getCity().get(0).getName());
        assertEquals("Forteresse", p2.getHand().get(0).getName());
        p2.play(draw);
        assertEquals(2, p2.getGold());
        assertEquals("Forteresse", p2.getCity().get(1).getName());
        assertTrue(p2.getHand().isEmpty());
        p2.play(draw);
        assertEquals(2, p2.getGold());
        assertEquals("Château", p2.getHand().get(0).getName());
        p2.play(draw);
        assertEquals(0, p2.getGold());
        assertEquals(0, p2.getHand().size());
        assertEquals(3, p2.getCity().size());
        p2.play(draw);
        assertEquals(0, p2.getGold());
        assertEquals("Marché", p2.getHand().get(0).getName());
        assertEquals("Château", p2.getCity().get(2).getName());
        assertEquals(1, p2.getHand().size());
        assertEquals(3, p2.getCity().size());
    }

    @Test
    void takeConstruction() {
        init();
        assertEquals("Château", p1.takeConstruction(draw).getName());
        assertEquals("Marché", p1.takeConstruction(draw).getName());
        assertEquals("Comptoir", p1.takeConstruction(draw).getName());
    }

    @Test
    void takeGold() {
        init();
        assertEquals(2, p1.getGold());
        p1.takeGold();
        assertEquals(4, p1.getGold());
    }

    @Test
    void buildConstruction() {
        init();
        p1.buildConstruction();
        assertEquals(2, p1.getHand().get(0).getValue());
        assertEquals(1, p1.getHand().size());

        assertEquals(1, p1.getCity().get(0).getValue());
        assertEquals(1, p1.getCity().size());

        assertEquals(1, p1.getGold());
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
    void testDraw(){
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
}
