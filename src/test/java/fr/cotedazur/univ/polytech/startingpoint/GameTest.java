package fr.cotedazur.univ.polytech.startingpoint;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    void init() {

        Game game = new Game(new Player[]{new Player(), new Player()});
        game.init();
        assertEquals(2, game.getPlayers().length);
        assertEquals(54, game.getDraw().size());
    }
}