package fr.cotedazur.univ.polytech.startingpoint.strategies;

import fr.cotedazur.univ.polytech.startingpoint.Draw;
import fr.cotedazur.univ.polytech.startingpoint.MyLogger;
import fr.cotedazur.univ.polytech.startingpoint.Player;
import fr.cotedazur.univ.polytech.startingpoint.cards.Character;
import fr.cotedazur.univ.polytech.startingpoint.cards.Color;
import fr.cotedazur.univ.polytech.startingpoint.cards.Construction;
import fr.cotedazur.univ.polytech.startingpoint.players.City;
import fr.cotedazur.univ.polytech.startingpoint.players.Hand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import static org.junit.jupiter.api.Assertions.*;

class StrategyTest {

    Player p1 = new Player(1,0, new Hand());
    Player p2 = new Player(2, new Hand());

    Strategy strategy = new Strategy1("Agressif");
    Construction cathédrale = new Construction("Cathédrale", Color.RELIGIEUX, 5);
    Construction chateau = new Construction("Château", Color.NOBLE, 4);
    Construction monastère = new Construction("Monastère", Color.RELIGIEUX, 3);
    Construction marché = new Construction("Marché", Color.COMMERCIAL, 2);
    Construction comptoir = new Construction("Comptoir", Color.COMMERCIAL, 3);
    Construction temple = new Construction("Temple", Color.RELIGIEUX, 1);

    @Test
    void testAverageCostInHand() {
        Hand hand = new Hand();
        hand.add(cathédrale);
        hand.add(chateau);
        assertEquals(4.5, strategy.averageCostInHand(hand, hand.size()));
        hand.add(monastère);
        assertEquals(4, strategy.averageCostInHand(hand, hand.size()));
        hand.add(marché);
        hand.add(comptoir);
        hand.add(temple);
        assertEquals(3, strategy.averageCostInHand(hand, hand.size()));
    }

    @Test
    void getDescriptionTest() {
        Strategy s = new Strategy1("la description");
        assertEquals("la description", s.getDescription());
    }

    @Test
    void testChoiceCharacter(){
        List<Character> characters = new ArrayList<>();

        characters.add(Character.VOLEUR);
        characters.add(Character.ASSASSIN);
        characters.add(Character.MAGICIEN);
        assertEquals(Character.MAGICIEN, strategy.chooseCharacter(p1, characters, new Player[]{p1, p2}));
        p1.getHand().add(cathédrale);
        assertEquals(Character.VOLEUR, strategy.chooseCharacter(p1, characters, new Player[]{p1, p2}));
        characters.remove(Character.VOLEUR);
        p1.getCity().add(cathédrale);
        p1.getCity().add(chateau);
        assertEquals(Character.ASSASSIN, strategy.chooseCharacter(p1, characters, new Player[]{p1, p2}));
        p2.getCity().add(chateau);
        p2.getCity().add(monastère);
        assertEquals(Character.MAGICIEN, strategy.chooseCharacter(p1, characters, new Player[]{p1, p2}));
        characters.add(Character.ROI);
        assertEquals(Character.ROI, strategy.chooseCharacter(p1, characters, new Player[]{p1, p2}));
        characters.add(Character.ARCHITECTE);
        assertEquals(Character.ARCHITECTE, strategy.chooseCharacter(p1, characters, new Player[]{p1, p2}));
        p1.getHand().remove(cathédrale);
        assertEquals(Character.MAGICIEN, strategy.chooseCharacter(p1, characters, new Player[]{p1, p2}));
        characters.remove(Character.MAGICIEN);
        assertEquals(Character.ARCHITECTE, strategy.chooseCharacter(p1, characters, new Player[]{p1, p2}));
    }

    Hand hand1;
    Hand hand2;
    Player p11;
    Player p22;

    Draw draw;

    Strategy strat = new StrategyEco("oui");

    @BeforeEach
    void init() {

        MyLogger.setLogLevel(Level.OFF);

        hand1 = new Hand();

        p11 = new Player(1, hand1);
        p11.getHand().add(new Construction("Temple", Color.RELIGIEUX, 1));
        p11.getHand().add(new Construction("Forteresse", Color.SOLDATESQUE, 2));
        p11.getCity().add(new Construction("Gros château", Color.NOBLE, 284));
        p11.getHand().add(new Construction("Temple", Color.RELIGIEUX, 1));
        p11.getHand().add(new Construction("Forteresse", Color.SOLDATESQUE, 2));
        p11.getCity().add(new Construction("Big château", Color.NOBLE, 284));
        p11.getCity().add(new Construction("Big église", Color.RELIGIEUX, 284));
        p11.getCity().add(new Construction("Giga base militaire", Color.SOLDATESQUE, 284));
        p11.getCity().add(new Construction("Hypermarché", Color.COMMERCIAL, 284));

        draw = new Draw();
        draw.addXConstructions(new Construction("Cathédrale", Color.RELIGIEUX, 5), 1);
        draw.addXConstructions(new Construction("Château", Color.NOBLE, 4), 1);
        draw.addXConstructions(new Construction("Monastère", Color.RELIGIEUX, 3), 1);
        draw.addXConstructions(new Construction("Marché", Color.COMMERCIAL, 2), 1);
        draw.addXConstructions(new Construction("Comptoir", Color.COMMERCIAL, 3), 1);

        hand2 = new Hand();

        p22 = new Player(2, 1, hand2, new City());
        p22.getCity().add(new Construction("Temple", Color.RELIGIEUX, 1));
        p22.getCity().add(new Construction("Forteresse", Color.SOLDATESQUE, 2));
        p22.getCity().add(new Construction("Big château", Color.NOBLE, 284));
        p22.getCity().add(new Construction("Big église", Color.COMMERCIAL, 284));



    }

    @Test
    void mostProfitableCharactersTest() {

        assertEquals(Character.ROI,strat.mostProfitableCharacters(p11).get(0));
        assertEquals(Character.MARCHAND,strat.mostProfitableCharacters(p11).get(1));
        assertEquals(Character.EVEQUE,strat.mostProfitableCharacters(p11).get(2));
        assertEquals(Character.CONDOTTIERE,strat.mostProfitableCharacters(p11).get(3));

        assertEquals(Character.ROI,strat.mostProfitableCharacters(p22).get(1));
        assertEquals(Character.MARCHAND,strat.mostProfitableCharacters(p22).get(0));
        assertEquals(Character.EVEQUE,strat.mostProfitableCharacters(p22).get(2));
        assertEquals(Character.CONDOTTIERE,strat.mostProfitableCharacters(p22).get(3));

    }

    @Test
    void isMaybeLastTurnTest() {
        Player p1 = new Player(0, new Hand());
        Player p2 = new Player(1, new Hand());

        assertFalse(p1.getStrategy().isMaybeLastTurn(new Player[]{p1, p2}));

        p1.getCity().add(marché);
        p1.getCity().add(marché);
        p1.getCity().add(marché);
        p1.getCity().add(marché);
        p1.getCity().add(marché);
        p1.getCity().add(marché);
        p1.getCity().add(marché);

        assertTrue(p1.getStrategy().isMaybeLastTurn(new Player[]{p1,p2}));
    }

    @Test
    void isWinningTest() {
        Player p1 = new Player(0, new Hand());
        Player p2 = new Player(1, new Hand());

        p1.getCity().add(chateau, cathédrale, monastère);
        p2.getCity().add(marché,temple);

        assertTrue(p1.getStrategy().isWinning(new Player[]{p1, p2}));
        assertFalse(p2.getStrategy().isWinning(new Player[]{p2, p1}));
    }

    @Test
    void citySizeTest() {
        Player p1 = new Player(0, new Hand());
        Player p2 = new Player(1, new Hand());
        Player p3 = new Player(3, new Hand());
        Player p4 = new Player(4, new Hand());

        p1.getCity().add(marché, marché, marché);
        p3.getCity().add(marché);

        Player[] players = new Player[]{p1, p2, p3, p4};

        assertTrue(p1.getStrategy().gotCitySize(3, players));
        assertTrue(p1.getStrategy().gotCitySize(1, players));
        assertFalse(p1.getStrategy().gotCitySize(-8, players));
        assertFalse(p1.getStrategy().gotCitySize(4, players));
    }

    @Test
    void canArchiRushTest() {
        Player p1 = new Player(0, new Hand());
        Player p2 = new Player(1, new Hand());
        Player p3 = new Player(3, new Hand());
        Player p4 = new Player(4, new Hand());

        Player[] players = new Player[]{p1, p2, p3, p4};

        assertNull(p1.getStrategy().canArchiRush(players));

        p3.getCity().add(marché, marché, marché, marché, marché);
        assertNull(p1.getStrategy().canArchiRush(players));

        p3.setGold(5);
        assertNull(p1.getStrategy().canArchiRush(players));

        p3.getHand().add(chateau);
        assertEquals(p3, p1.getStrategy().canArchiRush(players));
    }
  
    @Test
    void maxCitySizeExcept8Test() {
        Hand h1 = new Hand();
        City c1 = new City();
        Player p1 = new Player(1,0,h1,c1);

        Hand h2 = new Hand();
        City c2 = new City();
        Player p2 = new Player(2,0,h2,c2);

        Hand h3 = new Hand();
        City c3 = new City();
        Player p3 = new Player(3,0,h3,c3);

        Hand h4 = new Hand();
        City c4 = new City();
        Player p4 = new Player(4,0,h4,c4);

        Player[] players = new Player[]{p1,p2,p3,p4};

        assertEquals(0, p1.getStrategy().maxCitySizeExcept8(players));

        c1.add(marché);
        c1.add(marché);
        c1.add(marché);
        c1.add(marché);
        c1.add(marché);

        c2.add(marché);
        c2.add(marché);
        assertEquals(2, p1.getStrategy().maxCitySizeExcept8(players));

        c2.add(marché);
        c2.add(marché);
        c2.add(marché);
        c2.add(marché);
        c2.add(marché);
        c2.add(marché);

        c4.add(marché);
        assertEquals(1, p1.getStrategy().maxCitySizeExcept8(players));
    }
}