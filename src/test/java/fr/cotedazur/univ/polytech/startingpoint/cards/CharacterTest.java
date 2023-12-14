package fr.cotedazur.univ.polytech.startingpoint.cards;

import fr.cotedazur.univ.polytech.startingpoint.Draw;
import fr.cotedazur.univ.polytech.startingpoint.Player;
import fr.cotedazur.univ.polytech.startingpoint.players.Hand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.junit.jupiter.api.Assertions.*;

class CharacterTest {

    Draw d = new Draw();
    Player p = new Player(1, new Hand());
    Constructions c1 = new Constructions("Temple", Color.RELIGIEUX, 1);
    Constructions c2 = new Constructions("Forteresse", Color.SOLDATESQUE, 2);
    Constructions c3 = new Constructions("Cathédrale", Color.RELIGIEUX, 5);
    Constructions c4 = new Constructions("Château", Color.NOBLE, 4);
    Constructions c5 = new Constructions("Monastère", Color.RELIGIEUX, 3);
    Constructions c6 = new Constructions("Marché", Color.COMMERCIAL, 2);

    Player p2 = new Player(1, new Hand());
    Constructions c7 = new Constructions("Tata", Color.RELIGIEUX, 1);
    Constructions c8 = new Constructions("Toto", Color.SOLDATESQUE, 2);
    Constructions c9 = new Constructions("Titi", Color.RELIGIEUX, 5);


    @BeforeEach
    void setUp() {
        d.add(c1);
        d.add(c2);
        d.add(c3);
        d.add(c4);
        d.add(c5);
        d.add(c6);
        p.draw(d, 2);
        p2.getHand().add(c7);
        p2.getHand().add(c8);
        p2.getHand().add(c9);


    }

    @Test
    void testAbilityMag1() {
        assertEquals(2, p.getHand().size());
        assertEquals(4, d.size());
        assertFalse(d.contains(c1));
        assertFalse(d.contains(c2));
        assertTrue(p.getHand().contains(c1));
        assertTrue(p.getHand().contains(c2));
        Character.MAGICIEN.ability(d,p);
        assertEquals(2, p.getHand().size());
        assertEquals(4, d.size());
        assertTrue(d.contains(c1));
        assertTrue(d.contains(c2));

    }

    @Test
    void testAbilityMag2() {
        assertEquals(3, p2.getHand().size());
        assertEquals(2, p.getHand().size());
        assertTrue(p2.getHand().contains(c7));
        assertTrue(p2.getHand().contains(c8));
        assertTrue(p2.getHand().contains(c9));
        assertTrue(p.getHand().contains(c1));
        assertTrue(p.getHand().contains(c2));
        Character.MAGICIEN.ability(d,p,p2);
        assertEquals(2, p2.getHand().size());
        assertEquals(3, p.getHand().size());
        assertTrue(p2.getHand().contains(c1));
        assertTrue(p2.getHand().contains(c2));
        assertTrue(p.getHand().contains(c7));
        assertTrue(p.getHand().contains(c8));
        assertTrue(p.getHand().contains(c9));
    }

    @Test
    void testAbilityArchitecte() {
       assertTrue(p.getHand().contains(c1));
       assertTrue(p.getHand().contains(c2));
       assertEquals(2, p.getHand().size());
       Character.ARCHITECTE.ability(d,p);
       assertEquals(4, p.getHand().size());
       assertTrue(p.getHand().contains(c1));
       assertTrue(p.getHand().contains(c2));
       assertTrue(p.getHand().contains(c3));
       assertTrue(p.getHand().contains(c4));

    }


}