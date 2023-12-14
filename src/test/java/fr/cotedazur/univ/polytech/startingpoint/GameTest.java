package fr.cotedazur.univ.polytech.startingpoint;

import fr.cotedazur.univ.polytech.startingpoint.cards.Color;
import fr.cotedazur.univ.polytech.startingpoint.cards.Constructions;
import fr.cotedazur.univ.polytech.startingpoint.players.Hand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    void init() {

        Game game = new Game(new Player[]{new Player(1, new Hand()), new Player(2, new Hand())});
        game.init();
        assertEquals(2, game.getPlayers().length);
        assertEquals(46, game.getDraw().size());
    }

    @Test
    void sortPlayers() {
        Game game = new Game(new Player[]{new Player(1, new Hand()), new Player(2, new Hand())});
        game.init();
        game.getPlayers()[0].getCity().add(new Constructions("Temple", Color.RELIGIEUX, 1));
        game.getPlayers()[0].getCity().add(new Constructions("Forteresse", Color.SOLDATESQUE, 5));
        game.getPlayers()[1].getCity().add(new Constructions("Temple", Color.RELIGIEUX, 1));
        game.getPlayers()[1].getCity().add(new Constructions("Forteresse", Color.SOLDATESQUE, 2));
        game.sortPlayers();
        assertEquals(1, game.getPlayers()[1].getNumber());
        game.getPlayers()[0].getCity().add(new Constructions("Temple", Color.RELIGIEUX, 4));
        game.sortPlayers();
        assertEquals(2, game.getPlayers()[1].getNumber());
    }

    @Test
    void isFinished() {
        Game game = new Game(new Player[]{new Player(1, new Hand()), new Player(2, new Hand())});
        game.init();
        assertFalse(game.isFinished());
        game.getPlayers()[0].getCity().add(new Constructions("Temple", Color.RELIGIEUX, 1));
        game.getPlayers()[0].getCity().add(new Constructions("Forteresse", Color.SOLDATESQUE, 5));
        assertFalse(game.isFinished());
        game.getPlayers()[0].getCity().add(new Constructions("Forteresse", Color.SOLDATESQUE, 5));
        assertTrue(game.isFinished());
    }


}