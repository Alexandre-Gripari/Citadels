package fr.cotedazur.univ.polytech.startingpoint.cards;

import fr.cotedazur.univ.polytech.startingpoint.Draw;
import fr.cotedazur.univ.polytech.startingpoint.Player;
import fr.cotedazur.univ.polytech.startingpoint.players.City;
import fr.cotedazur.univ.polytech.startingpoint.players.Hand;
import fr.cotedazur.univ.polytech.startingpoint.strategies.Strategy;
import fr.cotedazur.univ.polytech.startingpoint.strategies.Strategy1;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CharacterTest {

    Hand hand1;
    Hand hand2;

    Hand hand3;
    Player p1;
    Player p2;
    Player p3;

    Strategy s1 = new Strategy1("Agressif");

    @BeforeEach
    void init1() {
        hand1 = new Hand();
        hand2 = new Hand();
        hand3 = new Hand();
        p1 = new Player(1, hand1);
        p2 = new Player(2, hand2);
        p3 = new Player(2, hand3);
        p1.setCharacter(Character.ASSASSIN);
        p2.setCharacter(Character.VOLEUR);
        p3.setCharacter(Character.MARCHAND);
    }
    @Test
    void assassinability(){
        p1.getCharacter().ability(p2);
        assertTrue(p2.isDead());
    }
    @Test
    void voleurability() {
        p1.setGold(10);
        p2.setGold(10);
        p3.setGold(10);
        Character.VOLEUR.ability(p2, p3);
        assertEquals(0, p3.getGold());
        assertEquals(20, p2.getGold());
        Character.VOLEUR.ability(p2, p1);
        assertEquals(20, p2.getGold());
        assertEquals(10, p1.getGold());
    }

    Draw d = new Draw();
    Player p = new Player(1, new Hand());
    Constructions c1 = new Constructions("Temple", Color.RELIGIEUX, 1);
    Constructions c2 = new Constructions("Forteresse", Color.SOLDATESQUE, 2);
    Constructions c3 = new Constructions("Cathédrale", Color.RELIGIEUX, 5);
    Constructions c4 = new Constructions("Château", Color.NOBLE, 4);
    Constructions c5 = new Constructions("Monastère", Color.RELIGIEUX, 3);
    Constructions c6 = new Constructions("Marché", Color.COMMERCIAL, 2);

    Player p2_1 = new Player(2, new Hand());
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
        p2_1.getHand().add(c7);
        p2_1.getHand().add(c8);
        p2_1.getHand().add(c9);
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
        assertEquals(3, p2_1.getHand().size());
        assertEquals(2, p.getHand().size());
        assertTrue(p2_1.getHand().contains(c7));
        assertTrue(p2_1.getHand().contains(c8));
        assertTrue(p2_1.getHand().contains(c9));
        assertTrue(p.getHand().contains(c1));
        assertTrue(p.getHand().contains(c2));
        Character.MAGICIEN.ability(d,p,p2_1);
        assertEquals(2, p2_1.getHand().size());
        assertEquals(3, p.getHand().size());
        assertTrue(p2_1.getHand().contains(c1));
        assertTrue(p2_1.getHand().contains(c2));
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


    Player assassin;
    Hand h1;
    City ct1;
    Player king;
    Hand h2;
    City ct2;
    Player bishop;
    Hand h3;
    City ct3;
    Player merchant;
    Hand h4;
    City ct4;
    Player architect;
    Hand h5;
    City ct5;
    Player thief;
    Hand h6;
    City ct6;
    Player magician;
    Hand h7;
    City ct7;
    Player condottiere;
    Hand h8;
    City ct8;
    Constructions prison = new Constructions("Prison", Color.SOLDATESQUE, 3);

    void init() {

        h1 = new Hand();
        ct1 = new City();
        assassin = new Player(1, 2, h1, ct1, s1);
        assassin.getCity().add(new Constructions("Temple", Color.RELIGIEUX, 1));

        h2 = new Hand();
        ct2 = new City();
        king = new Player(2, 2, h2, ct2, s1);
        king.getCity().add(new Constructions("Manoir", Color.NOBLE, 1));
        king.getCity().add(new Constructions("Forteresse", Color.SOLDATESQUE, 2));

        h3 = new Hand();
        ct3 = new City();
        bishop = new Player(3, 2, h3, ct3, s1);
        bishop.getCity().add(new Constructions("Temple", Color.RELIGIEUX, 1));
        bishop.getCity().add(new Constructions("Eglise", Color.RELIGIEUX, 2));

        h4 = new Hand();
        ct4 = new City();
        merchant = new Player(4, 2, h4, ct4, s1);
        merchant.getCity().add(new Constructions("Taverne", Color.COMMERCIAL, 1));
        merchant.getCity().add(new Constructions("Forteresse", Color.SOLDATESQUE, 2));
        merchant.getCity().add(new Constructions("Marché", Color.COMMERCIAL, 2));

        h5 = new Hand();
        ct5 = new City();
        architect = new Player(5, 2, h5, ct5, s1);
        architect.getCity().add(new Constructions("Taverne", Color.COMMERCIAL, 1));
        architect.getCity().add(new Constructions("Forteresse", Color.SOLDATESQUE, 2));

        h6 = new Hand();
        ct6 = new City();
        thief = new Player(6, 2, h6, ct6, s1);
        thief.getCity().add(new Constructions("Taverne", Color.COMMERCIAL, 1));


        h7 = new Hand();
        ct7 = new City();
        magician = new Player(7, 2, h7, ct7, s1);
        magician.getCity().add(new Constructions("Port", Color.COMMERCIAL, 4));
        magician.getCity().add(prison);
        magician.getCity().add(new Constructions("Palais", Color.NOBLE, 5));

        h8 = new Hand();
        ct8 = new City();
        condottiere = new Player(8, 3, h8, ct8, s1);
        condottiere.getCity().add(new Constructions("Forteresse", Color.SOLDATESQUE, 2));

        List<Character> CharacterList = new ArrayList<>(List.of(Character.values()));
        assassin.chooseCharacter(CharacterList);
        thief.chooseCharacter(CharacterList);
        magician.chooseCharacter(CharacterList);
        king.chooseCharacter(CharacterList);
        bishop.chooseCharacter(CharacterList);
        merchant.chooseCharacter(CharacterList);
        architect.chooseCharacter(CharacterList);
        condottiere.chooseCharacter(CharacterList);

    }

    @Test
    void testBishopAbility() { //EVEQUE
        init();
        assertEquals(2, bishop.getGold());
        Character.EVEQUE.ability(bishop);
        assertEquals(4, bishop.getGold());
    }

    @Test
    void testMerchantAbility() {
        init();
        assertEquals(2, merchant.getGold());
        Character.MARCHAND.ability(merchant);
        assertEquals(5, merchant.getGold());
    }

    @Test
    void testCondottiereAbility() {
        init();
        Constructions prison2 = new Constructions("Prison", Color.SOLDATESQUE, 2);
        assertEquals(3, condottiere.getGold());
        assertEquals(3, magician.getCity().size());
        assertEquals("Prison",Character.CONDOTTIERE.ability(prison, condottiere, magician).getName());
        assertEquals(2, condottiere.getGold());
        assertEquals(2, magician.getCity().size());

    }

}