package fr.cotedazur.univ.polytech.startingpoint.strategies;

import fr.cotedazur.univ.polytech.startingpoint.Draw;
import fr.cotedazur.univ.polytech.startingpoint.Player;
import fr.cotedazur.univ.polytech.startingpoint.cards.Character;
import fr.cotedazur.univ.polytech.startingpoint.cards.Color;
import fr.cotedazur.univ.polytech.startingpoint.cards.Constructions;
import fr.cotedazur.univ.polytech.startingpoint.players.Hand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Strategy1Test {

    Strategy1 strat;
    Player p1;
    Hand h1;
    Player p2;
    Hand h2;
    Player p3;
    Hand h3;
    Player p4;
    Hand h4;
    Player[] players;
    Draw draw;

    @BeforeEach
    void init() {
        strat = new Strategy1("rush");
        h1 = new Hand();
        p1 = new Player(1, h1);
        h2 = new Hand();
        p2 = new Player(2, h2);
        h3 = new Hand();
        p3 = new Player(3, h3);
        h4 = new Hand();
        p4 = new Player(4, h4);
        players = new Player[4];
        players[0] = p1; players[1] = p2; players[2] = p3; players[3] = p4;
        draw = new Draw();

    }

    @Test
    void assassinTest() {
        p1.setCharacter(Character.ASSASSIN);
        p2.setCharacter(Character.ARCHITECTE);
        p3.setCharacter(Character.ROI);
        p4.setCharacter(Character.CONDOTTIERE);

        strat.assassin(players, draw);

        assertTrue(p2.isDead());
        assertFalse(p1.isDead());
        assertFalse(p3.isDead());
        assertFalse(p4.isDead());
        p2.resurrect();

        p2.setCharacter(Character.MAGICIEN);
        strat.assassin(players, draw);
        assertFalse(p1.isDead());
        assertFalse(p2.isDead());
        assertFalse(p3.isDead());
        assertFalse(p4.isDead());
    }

    @Test
    void thiefTest() {
        p1.setCharacter(Character.VOLEUR);
        p2.setCharacter(Character.ARCHITECTE);
        p3.setCharacter(Character.ROI);
        p4.setCharacter(Character.CONDOTTIERE);

        assertEquals(2, p1.getGold());
        assertEquals(2, p2.getGold());
        strat.thief(players, draw);
        assertEquals(4, p1.getGold());
        assertEquals(0, p2.getGold());

        p1.setGold(2);
        p2.setGold(2);

        Character.ASSASSIN.ability(p2);

        assertEquals(2, p1.getGold());
        assertEquals(2, p2.getGold());
        strat.thief(players, draw);
        assertEquals(2, p1.getGold());
        assertEquals(2, p2.getGold());
    }

    @Test
    void magicianTestNoSwapHand() {
        p1.setCharacter(Character.MAGICIEN);
        p2.setCharacter(Character.ARCHITECTE);
        p3.setCharacter(Character.ROI);
        p4.setCharacter(Character.CONDOTTIERE);

        p1.getHand().add(new Constructions("Temple", Color.RELIGIEUX, 1));
        p1.getHand().add(new Constructions("Eglise", Color.RELIGIEUX, 2));
        p1.getHand().add(new Constructions("Monastère", Color.RELIGIEUX, 3));

        p2.getHand().add(new Constructions("Tour de guet", Color.SOLDATESQUE, 1));
        p3.getHand().add(new Constructions("Taverne", Color.COMMERCIAL, 1));
        p4.getHand().add(new Constructions("Comptoir", Color.COMMERCIAL, 3));

        draw.add(new Constructions("Prison", Color.SOLDATESQUE, 3));
        draw.add(new Constructions("Marché", Color.COMMERCIAL, 2));
        draw.add(new Constructions("Port", Color.COMMERCIAL, 4));

        strat.magician(players, draw);

        assertEquals(3, p1.getHand().size());
        assertEquals(1, p1.getHand().get(0).getValue());
    }

    @Test
    void magicianTestSwapWithDraw() {
        p1.setCharacter(Character.MAGICIEN);
        p2.setCharacter(Character.ARCHITECTE);
        p3.setCharacter(Character.ROI);
        p4.setCharacter(Character.CONDOTTIERE);

        p1.getHand().add(new Constructions("Temple", Color.RELIGIEUX, 1));
        p1.getHand().add(new Constructions("Eglise", Color.RELIGIEUX, 2));
        p1.getHand().add(new Constructions("Monastère", Color.RELIGIEUX, 3));
        p1.getHand().add(new Constructions("Hôtel de ville", Color.COMMERCIAL, 10));

        p2.getHand().add(new Constructions("Tour de guet", Color.SOLDATESQUE, 1));
        p3.getHand().add(new Constructions("Taverne", Color.COMMERCIAL, 1));
        p4.getHand().add(new Constructions("Comptoir", Color.COMMERCIAL, 3));

        draw.add(new Constructions("Prison", Color.SOLDATESQUE, 3));
        draw.add(new Constructions("Marché", Color.COMMERCIAL, 2));
        draw.add(new Constructions("Port", Color.COMMERCIAL, 4));
        draw.add(new Constructions("Bastion", Color.SOLDATESQUE, 5));

        strat.magician(players, draw);

        assertEquals(4, p1.getHand().size());
        assertEquals(3, p1.getHand().get(0).getValue());
    }

    @Test
    void magicianTestSwapWithAPlayer() {
        p1.setCharacter(Character.MAGICIEN);
        p2.setCharacter(Character.ARCHITECTE);
        p3.setCharacter(Character.ROI);
        p4.setCharacter(Character.CONDOTTIERE);

        p1.getHand().add(new Constructions("Echoppe", Color.COMMERCIAL, 2));
        p1.getHand().add(new Constructions("Eglise", Color.RELIGIEUX, 2));


        p2.getHand().add(new Constructions("Tour de guet", Color.SOLDATESQUE, 1));
        p2.getHand().add(new Constructions("Monastère", Color.RELIGIEUX, 3));
        p2.getHand().add(new Constructions("Hôtel de ville", Color.COMMERCIAL, 5));

        p3.getHand().add(new Constructions("Taverne", Color.COMMERCIAL, 1));
        p4.getHand().add(new Constructions("Comptoir", Color.COMMERCIAL, 3));

        draw.add(new Constructions("Prison", Color.SOLDATESQUE, 3));
        draw.add(new Constructions("Marché", Color.COMMERCIAL, 2));
        draw.add(new Constructions("Port", Color.COMMERCIAL, 4));
        draw.add(new Constructions("Bastion", Color.SOLDATESQUE, 5));

        assertEquals(2, p1.getHand().size());
        assertEquals(2, p1.getHand().get(0).getValue());

        strat.magician(players, draw);

        assertEquals(3, p1.getHand().size());
        assertEquals(1, p1.getHand().get(0).getValue());
    }

    @Test
    void condottiereTest() {
        p1.setCharacter(Character.CONDOTTIERE);
        p2.setCharacter(Character.ARCHITECTE);
        p3.setCharacter(Character.ROI);
        p4.setCharacter(Character.MAGICIEN);

        p1.getHand().add(new Constructions("Echoppe", Color.COMMERCIAL, 2));
        p1.getHand().add(new Constructions("Eglise", Color.RELIGIEUX, 2));


        p2.getCity().add(new Constructions("Tour de guet", Color.SOLDATESQUE, 1));
        p2.getCity().add(new Constructions("Hôtel de ville", Color.COMMERCIAL, 5));
        p2.getCity().add(new Constructions("Monastère", Color.RELIGIEUX, 3));

        p3.getCity().add(new Constructions("Taverne", Color.COMMERCIAL, 1));
        p4.getCity().add(new Constructions("Temple", Color.RELIGIEUX, 1));

        assertEquals(2, p1.getGold());
        assertEquals(3, p2.getCity().size());
        assertEquals(1, p2.getCity().get(0).getValue());
        strat.condottiere(players, draw);
        assertEquals(2, p1.getGold());
        assertEquals(2, p2.getCity().size());
        assertEquals(5, p2.getCity().get(0).getValue());

        p1.setGold(10);
        strat.condottiere(players, draw);
        assertEquals(8, p1.getGold());
        assertEquals(1, p2.getCity().size());
        assertEquals(5, p2.getCity().get(0).getValue());

        assertEquals(1, p3.getCity().size());
        strat.condottiere(players, draw);
        // p2 n'est plus celui ciblé
        assertEquals(1, p2.getCity().size());
        assertEquals(5, p2.getCity().get(0).getValue());
        // p3 est désormais ciblé
        assertEquals(0, p3.getCity().size());
        assertEquals(8, p1.getGold());

    }

    @Test
    void playWithBiggestHandIndexTest() {
        p1.setCharacter(Character.MAGICIEN);
        p2.setCharacter(Character.ARCHITECTE);
        p3.setCharacter(Character.ROI);
        p4.setCharacter(Character.CONDOTTIERE);

        p1.getHand().add(new Constructions("Echoppe", Color.COMMERCIAL, 2));
        p1.getHand().add(new Constructions("Eglise", Color.RELIGIEUX, 2));


        p2.getHand().add(new Constructions("Tour de guet", Color.SOLDATESQUE, 1));
        p2.getHand().add(new Constructions("Monastère", Color.RELIGIEUX, 3));
        p2.getHand().add(new Constructions("Hôtel de ville", Color.COMMERCIAL, 5));

        p3.getHand().add(new Constructions("Taverne", Color.COMMERCIAL, 1));
        p4.getHand().add(new Constructions("Comptoir", Color.COMMERCIAL, 3));

        assertEquals(1, strat.playerWithBiggestHandIndex(players));

        p1.getHand().add(new Constructions("Prison", Color.SOLDATESQUE, 3));
        p1.getHand().add(new Constructions("Marché", Color.COMMERCIAL, 2));

        assertEquals(0, strat.playerWithBiggestHandIndex(players));
    }

    @Test
    void minCostInCityTest() {
        p1.buildConstruction(new Constructions("Echoppe", Color.COMMERCIAL, 2));
        p1.buildConstruction(new Constructions("Eglise", Color.RELIGIEUX, 2));
        p1.buildConstruction(new Constructions("Comptoir", Color.COMMERCIAL, 3));

        assertEquals(2, strat.minCostInCity(p1.getCity()));

        p1.buildConstruction(new Constructions("Taverne", Color.COMMERCIAL, 1));

        assertEquals(1, strat.minCostInCity(p1.getCity()));
    }

    @Test
    void minCostInCityIndexTest() {
        p1.buildConstruction(new Constructions("Echoppe", Color.COMMERCIAL, 2));
        p1.buildConstruction(new Constructions("Eglise", Color.RELIGIEUX, 2));
        p1.buildConstruction(new Constructions("Comptoir", Color.COMMERCIAL, 3));

        assertEquals(0, strat.minCostInCityIndex(p1.getCity()));

        p1.buildConstruction(new Constructions("Taverne", Color.COMMERCIAL, 1));

        assertEquals(3, strat.minCostInCityIndex(p1.getCity()));
    }

}