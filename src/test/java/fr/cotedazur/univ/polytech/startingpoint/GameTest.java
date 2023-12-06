package fr.cotedazur.univ.polytech.startingpoint;

import fr.cotedazur.univ.polytech.startingpoint.players.Hand;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    void init() {

        Game game = new Game(new Player[]{new Player(1, new Hand()), new Player(2, new Hand())});
        game.init();
        assertEquals(2, game.getPlayers().length);
        assertEquals(54, game.getDraw().size());
    }
}