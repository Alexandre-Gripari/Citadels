package fr.cotedazur.univ.polytech.startingpoint.strategies;

import fr.cotedazur.univ.polytech.startingpoint.Draw;
import fr.cotedazur.univ.polytech.startingpoint.MyLogger;
import fr.cotedazur.univ.polytech.startingpoint.Player;
import fr.cotedazur.univ.polytech.startingpoint.cards.Character;
import fr.cotedazur.univ.polytech.startingpoint.cards.Color;
import fr.cotedazur.univ.polytech.startingpoint.cards.Constructions;
import fr.cotedazur.univ.polytech.startingpoint.players.City;
import fr.cotedazur.univ.polytech.startingpoint.players.Hand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StrategyTest {

    Player p1 = new Player(1,0, new Hand());
    Player p2 = new Player(2, new Hand());

    Strategy strategy = new Strategy1("Agressif");
    Strategy strategy2 = new StrategyRichard("Agressif");
    Constructions cathédrale = new Constructions("Cathédrale", Color.RELIGIEUX, 5);
    Constructions chateau = new Constructions("Château", Color.NOBLE, 4);
    Constructions monastère = new Constructions("Monastère", Color.RELIGIEUX, 3);
    Constructions marché = new Constructions("Marché", Color.COMMERCIAL, 2);
    Constructions comptoir = new Constructions("Comptoir", Color.COMMERCIAL, 3);
    Constructions temple = new Constructions("Temple", Color.RELIGIEUX, 1);

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
        p11.getHand().add(new Constructions("Temple", Color.RELIGIEUX, 1));
        p11.getHand().add(new Constructions("Forteresse", Color.SOLDATESQUE, 2));
        p11.getCity().add(new Constructions("Gros château", Color.NOBLE, 284));
        p11.getHand().add(new Constructions("Temple", Color.RELIGIEUX, 1));
        p11.getHand().add(new Constructions("Forteresse", Color.SOLDATESQUE, 2));
        p11.getCity().add(new Constructions("Big château", Color.NOBLE, 284));
        p11.getCity().add(new Constructions("Big église", Color.RELIGIEUX, 284));
        p11.getCity().add(new Constructions("Giga base militaire", Color.SOLDATESQUE, 284));
        p11.getCity().add(new Constructions("Hypermarché", Color.COMMERCIAL, 284));

        draw = new Draw();
        draw.addXConstructions(new Constructions("Cathédrale", Color.RELIGIEUX, 5), 1);
        draw.addXConstructions(new Constructions("Château", Color.NOBLE, 4), 1);
        draw.addXConstructions(new Constructions("Monastère", Color.RELIGIEUX, 3), 1);
        draw.addXConstructions(new Constructions("Marché", Color.COMMERCIAL, 2), 1);
        draw.addXConstructions(new Constructions("Comptoir", Color.COMMERCIAL, 3), 1);

        hand2 = new Hand();

        p22 = new Player(2, 1, hand2, new City());
        p22.getCity().add(new Constructions("Temple", Color.RELIGIEUX, 1));
        p22.getCity().add(new Constructions("Forteresse", Color.SOLDATESQUE, 2));
        p22.getCity().add(new Constructions("Big château", Color.NOBLE, 284));
        p22.getCity().add(new Constructions("Big église", Color.COMMERCIAL, 284));



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