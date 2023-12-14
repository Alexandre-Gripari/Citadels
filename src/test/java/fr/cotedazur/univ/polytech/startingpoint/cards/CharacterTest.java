package fr.cotedazur.univ.polytech.startingpoint.cards;

import fr.cotedazur.univ.polytech.startingpoint.Player;
import fr.cotedazur.univ.polytech.startingpoint.players.City;
import fr.cotedazur.univ.polytech.startingpoint.players.Hand;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;



class CharacterTest {

    Player assassin;
    Hand h1;
    City c1;
    Player king;
    Hand h2;
    City c2;
    Player bishop;
    Hand h3;
    City c3;
    Player merchant;
    Hand h4;
    City c4;
    Player architect;
    Hand h5;
    City c5;
    Player thief;
    Hand h6;
    City c6;
    Player magician;
    Hand h7;
    City c7;
    Player condottiere;
    Hand h8;
    City c8;

    void init() {

        h1 = new Hand();
        c1 = new City();
        assassin = new Player(1, 2, h1, c1);
        assassin.getCity().add(new Constructions("Temple", Color.RELIGIEUX, 1));

        h2 = new Hand();
        c2 = new City();
        king = new Player(2, 2, h2, c2);
        king.getCity().add(new Constructions("Manoir", Color.NOBLE, 1));
        king.getCity().add(new Constructions("Forteresse", Color.SOLDATESQUE, 2));

        h3 = new Hand();
        c3 = new City();
        bishop = new Player(3, 2, h3, c3);
        bishop.getCity().add(new Constructions("Temple", Color.RELIGIEUX, 1));
        bishop.getCity().add(new Constructions("Eglise", Color.RELIGIEUX, 2));

        h4 = new Hand();
        c4 = new City();
        merchant = new Player(4, 2, h4, c4);
        merchant.getCity().add(new Constructions("Taverne", Color.COMMERCIAL, 1));
        merchant.getCity().add(new Constructions("Forteresse", Color.SOLDATESQUE, 2));
        merchant.getCity().add(new Constructions("Marché", Color.COMMERCIAL, 2));

        h5 = new Hand();
        c5 = new City();
        architect = new Player(5, 2, h5, c5);
        architect.getCity().add(new Constructions("Taverne", Color.COMMERCIAL, 1));
        architect.getCity().add(new Constructions("Forteresse", Color.SOLDATESQUE, 2));

        h6 = new Hand();
        c6 = new City();
        thief = new Player(6, 2, h6, c6);
        thief.getCity().add(new Constructions("Taverne", Color.COMMERCIAL, 1));


        h7 = new Hand();
        c7 = new City();
        magician = new Player(7, 2, h7, c7);
        magician.getCity().add(new Constructions("Port", Color.COMMERCIAL, 4));
        magician.getCity().add(new Constructions("Prison", Color.SOLDATESQUE, 3));
        magician.getCity().add(new Constructions("Palais", Color.NOBLE, 5));

        h8 = new Hand();
        c8 = new City();
        condottiere = new Player(8, 3, h8, c8);
        condottiere.getCity().add(new Constructions("Forteresse", Color.SOLDATESQUE, 2));

        List<Character> CharacterList = new ArrayList<>(List.of(Character.values()));
        assassin.chooseCharacter(CharacterList);
        king.chooseCharacter(CharacterList);
        bishop.chooseCharacter(CharacterList);
        merchant.chooseCharacter(CharacterList);
        architect.chooseCharacter(CharacterList);
        thief.chooseCharacter(CharacterList);
        magician.chooseCharacter(CharacterList);
        condottiere.chooseCharacter(CharacterList);

    }

    @Test
    void testAssassinAbility() {
        return;
    }

    @Test
    void testKingAbility() {
        return;
    }

    @Test
    void testBishopAbility() { //EVEQUE
        init();
        assertEquals(2, bishop.getGold());
        bishop.useAbility(null);
        assertEquals(4, bishop.getGold());
    }

    @Test
    void testMerchantAbility() {
        init();
        assertEquals(2, merchant.getGold());
        merchant.useAbility(null);
        assertEquals(5, merchant.getGold());
    }

    @Test
    void testArchitectAbility() {
        return;
    }

    @Test
    void testThiefAbility() {
        return;
    }

    @Test
    void testMagicianAbility() {
        return;
    }

    @Test
    void testCondottiereAbility() {
        init();
        assertEquals(3, condottiere.getGold());
        assertEquals(3, magician.getCity().size());
        condottiere.useAbility(new Player[]{magician});
        assertEquals(2, condottiere.getGold());
        assertEquals(2, magician.getCity().size());
    }
}