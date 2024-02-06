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

import static org.junit.jupiter.api.Assertions.*;

class StrategyEcoTest {
    Player p1 = new Player(1,0, new Hand());
    Player p2 = new Player(2, new Hand());
    Hand hand1;
    Hand hand2;
    Draw draw;

    StrategyEco strat = new StrategyEco("oui");

    List<Character> charactersList = new ArrayList<Character>();

    @BeforeEach
    void init() {

        MyLogger.setLogLevel(Level.OFF);

        hand1 = new Hand();

        p1 = new Player(1, hand1);
        p1.getHand().add(new Constructions("Temple", Color.RELIGIEUX, 1));
        p1.getHand().add(new Constructions("Forteresse", Color.SOLDATESQUE, 2));
        p1.getCity().add(new Constructions("Gros château", Color.NOBLE, 284));

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
        charactersList.addAll(List.of(Character.ROI,Character.MARCHAND));
    }
    @Test
    void choiceOfCharacter(){
        assertEquals(Character.ROI,strat.chooseCharacter(p1,charactersList,new Player[]{p1,p2}));
    }
}