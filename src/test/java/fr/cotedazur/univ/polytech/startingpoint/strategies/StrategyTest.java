package fr.cotedazur.univ.polytech.startingpoint.strategies;

import fr.cotedazur.univ.polytech.startingpoint.Draw;
import fr.cotedazur.univ.polytech.startingpoint.Player;
import fr.cotedazur.univ.polytech.startingpoint.cards.Character;
import fr.cotedazur.univ.polytech.startingpoint.cards.Color;
import fr.cotedazur.univ.polytech.startingpoint.cards.Constructions;
import fr.cotedazur.univ.polytech.startingpoint.cards.Wonder;
import fr.cotedazur.univ.polytech.startingpoint.players.City;
import fr.cotedazur.univ.polytech.startingpoint.players.Hand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StrategyTest {
    Hand hand1;
    Hand hand2;
    Player p1;
    Player p2;
    Draw draw;

    Strategy strat = new Strategy("oui") {
        @Override
        Character choiceOfCharacter(Player player, List<Character> characters) {
            return null;
        }

        @Override
        void useWonder(List<Wonder> wonders) {

        }

        @Override
        void assassin(Player[] players, Draw draw) {

        }

        @Override
        void thief(Player[] players, Draw draw) {

        }

        @Override
        void magician(Player[] players, Draw draw) {

        }

        @Override
        void king(Player[] players, Draw draw) {

        }

        @Override
        void bishop(Player[] players, Draw draw) {

        }

        @Override
        void merchant(Player[] players, Draw draw) {

        }

        @Override
        void architect(Player[] players, Draw draw) {

        }

        @Override
        void condottiere(Player[] players, Draw draw) {

        }
    };

    @BeforeEach
    void init() {

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
        p2.chooseCharacter(new ArrayList<>(Arrays.asList(Character.values())));
    }

    @Test
    void mostProfitableCharactersTest() {
        assertEquals(Character.ROI,strat.mostProfitableCharacters(p1).get(0));
        assertEquals(Character.MARCHAND,strat.mostProfitableCharacters(p1).get(1));
        assertEquals(Character.EVEQUE,strat.mostProfitableCharacters(p1).get(2));
        assertEquals(Character.CONDOTTIERE,strat.mostProfitableCharacters(p1).get(3));
    }
}