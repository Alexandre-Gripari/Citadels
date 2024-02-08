package fr.cotedazur.univ.polytech.startingpoint;

import fr.cotedazur.univ.polytech.startingpoint.cards.Color;
import fr.cotedazur.univ.polytech.startingpoint.cards.Constructions;
import fr.cotedazur.univ.polytech.startingpoint.players.Hand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.logging.Level;

import static fr.cotedazur.univ.polytech.startingpoint.cards.Character.ROI;
import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @BeforeEach
    void noLog(){
        MyLogger.setLogLevel(Level.OFF);
    }

    @Test
    void init() {
        Game game = new Game(new Player[]{new Player(1, new Hand()), new Player(2, new Hand())});
        game.init();
        assertEquals(2, game.getPlayers().length);
        assertEquals(56, game.getDraw().size());
    }

    @Test
    void sortPlayers() {
        Game game = new Game(new Player[]{new Player(1, new Hand()), new Player(2, new Hand())});
        game.init();
        game.getPlayers()[0].getCity().add(new Constructions("Temple", Color.RELIGIEUX, 1));
        game.getPlayers()[0].getCity().add(new Constructions("Forteresse", Color.SOLDATESQUE, 5));
        game.getPlayers()[1].getCity().add(new Constructions("Temple", Color.RELIGIEUX, 1));
        game.getPlayers()[1].getCity().add(new Constructions("Forteresse", Color.SOLDATESQUE, 2));
        game.getPlayers()[0].setScore(game.getPlayers()[0].getCity().cityValue());
        game.getPlayers()[1].setScore(game.getPlayers()[1].getCity().cityValue());
        game.sortPlayersByPoints();
        assertEquals(1, game.getPlayers()[1].getNumber());
        game.getPlayers()[0].getCity().add(new Constructions("Temple", Color.RELIGIEUX, 4));
        game.getPlayers()[0].setScore(game.getPlayers()[0].getCity().cityValue());
        game.getPlayers()[1].setScore(game.getPlayers()[1].getCity().cityValue());
        game.sortPlayersByPoints();
        assertEquals(2, game.getPlayers()[1].getNumber());
    }

    @Test
    void playerHasFinished() {
        Game game = new Game(new Player[]{new Player(1, new Hand()), new Player(2, new Hand())});
        game.init();
        game.playerHasFinished(game.getPlayers()[0]);
        assertEquals(0, game.getPlayers()[0].getScore());
        game.playerHasFinished(game.getPlayers()[1]);
        assertEquals(0, game.getPlayers()[1].getScore());
        game.getPlayers()[0].getCity().add(new Constructions("Temple", Color.RELIGIEUX, 1));
        game.getPlayers()[0].getCity().add(new Constructions("Temple", Color.RELIGIEUX, 1));
        game.getPlayers()[0].getCity().add(new Constructions("Temple", Color.RELIGIEUX, 1));
        game.getPlayers()[0].getCity().add(new Constructions("Temple", Color.RELIGIEUX, 1));
        game.getPlayers()[0].getCity().add(new Constructions("Temple", Color.RELIGIEUX, 1));
        game.getPlayers()[0].getCity().add(new Constructions("Temple", Color.RELIGIEUX, 1));
        game.getPlayers()[0].getCity().add(new Constructions("Temple", Color.RELIGIEUX, 1));
        game.getPlayers()[1].getCity().add(new Constructions("Forteresse", Color.SOLDATESQUE, 5));
        game.getPlayers()[1].getCity().add(new Constructions("Forteresse", Color.SOLDATESQUE, 5));
        game.getPlayers()[1].getCity().add(new Constructions("Forteresse", Color.SOLDATESQUE, 5));
        game.getPlayers()[1].getCity().add(new Constructions("Forteresse", Color.SOLDATESQUE, 5));
        game.getPlayers()[1].getCity().add(new Constructions("Forteresse", Color.SOLDATESQUE, 5));
        game.getPlayers()[1].getCity().add(new Constructions("Forteresse", Color.SOLDATESQUE, 5));
        game.getPlayers()[1].getCity().add(new Constructions("Forteresse", Color.SOLDATESQUE, 5));
        game.playerHasFinished(game.getPlayers()[0]);
        assertEquals(0, game.getPlayers()[0].getScore());
        game.playerHasFinished(game.getPlayers()[1]);
        assertEquals(0, game.getPlayers()[1].getScore());
        game.getPlayers()[0].getCity().add(new Constructions("Temple", Color.RELIGIEUX, 1));
        game.playerHasFinished(game.getPlayers()[0]);
        game.getPlayers()[1].getCity().add(new Constructions("Forteresse", Color.SOLDATESQUE, 5));
        game.getPlayers()[1].getCity().add(new Constructions("Forteresse", Color.SOLDATESQUE, 5));
        game.playerHasFinished(game.getPlayers()[1]);
        assertEquals(4, game.getPlayers()[0].getScore());
        assertEquals(2, game.getPlayers()[1].getScore());
        game.isFinished();
        assertEquals(12, game.getPlayers()[0].getScore());
        assertEquals(47, game.getPlayers()[1].getScore());
    }

    @Test
    void isFinished() {
        Game game = new Game(new Player[]{new Player(1, new Hand()), new Player(2, new Hand())});
        game.init();
        game.playerHasFinished(game.getPlayers()[0]);
        assertFalse(game.isFinished());
        game.getPlayers()[0].getCity().add(new Constructions("Temple", Color.RELIGIEUX, 1));
        game.getPlayers()[0].getCity().add(new Constructions("Forteresse", Color.SOLDATESQUE, 5));
        game.playerHasFinished(game.getPlayers()[0]);
        assertFalse(game.isFinished());
        game.getPlayers()[0].getCity().add(new Constructions("Forteresse", Color.SOLDATESQUE, 5));
        game.playerHasFinished(game.getPlayers()[0]);
        assertFalse(game.isFinished());
        game.getPlayers()[0].getCity().add(new Constructions("Forteresse", Color.SOLDATESQUE, 5));
        assertFalse(game.isFinished());
        game.getPlayers()[0].getCity().add(new Constructions("Forteresse", Color.SOLDATESQUE, 5));
        game.getPlayers()[0].getCity().add(new Constructions("Forteresse", Color.SOLDATESQUE, 5));
        game.getPlayers()[0].getCity().add(new Constructions("Forteresse", Color.SOLDATESQUE, 5));
        game.getPlayers()[0].getCity().add(new Constructions("Forteresse", Color.SOLDATESQUE, 5));
        game.getPlayers()[0].getCity().add(new Constructions("Forteresse", Color.SOLDATESQUE, 5));
        game.playerHasFinished(game.getPlayers()[0]);
        assertTrue(game.isFinished());
    }

    @Test
    void testChoiceOfCharacter() {
        Player player1 = new Player(1, new Hand());
        Player player2 = new Player(2, new Hand());
        Player[] players = {player1, player2};
        Game game = new Game(players);
        game.init();
        assertEquals(8, game.getCharacters().size());
        game.discardCharacter();
        assertEquals(5, game.getCharacters().size());
        game.choiceOfCharacter();
        assertEquals(8, game.getCharacters().size());
    }


    @Test
    void testSortPLayerByCharacter() {
        Player player1 = new Player(1, new Hand());
        Player player2 = new Player(2, new Hand());
        Player[] players = {player1, player2};
        Game game = new Game(players);
        game.init();
        for (int i = 0; i < 20; i++)  {
            game.discardCharacter();
            game.choiceOfCharacter();
            game.sortPlayersByCharacter();
            assertTrue(game.getPlayers()[0].getCharacter().getNumber() <= game.getPlayers()[1].getCharacter().getNumber());
        }
    }

    @Test
    void testReorganizePlayers() {
        Player p1 = new Player(1, new Hand());
        Player p2 = new Player(2, new Hand());
        Player p3 = new Player(3, new Hand());
        Player p4 = new Player(4, new Hand());
        Game game = new Game(new Player[]{p1, p2, p3, p4});
        game.init();
        p1.chooseCharacter(game.getCharacters(), game.getOpponents(p1));
        p2.chooseCharacter(game.getCharacters(), game.getOpponents(p2));
        p3.chooseCharacter(game.getCharacters(), game.getOpponents(p3));
        p4.chooseCharacter(game.getCharacters(), game.getOpponents(p4));
        game.reorganizePlayers();
        assertEquals(ROI, game.getPlayers()[0].getCharacter());
    }

    @Test
    void testgetOpponents() {
        Player p1 = new Player(1, new Hand());
        Player p2 = new Player(2, new Hand());
        Player p3 = new Player(3, new Hand());
        Player p4 = new Player(4, new Hand());
        Game game = new Game(new Player[]{p1, p2, p3, p4});
        game.init();
        Player[] opponents = game.getOpponents(p1);
        assertEquals(4, opponents.length);
        assertEquals(p1, opponents[0]);
        assertEquals(p2, opponents[1]);
        assertEquals(p3, opponents[2]);
        assertEquals(p4, opponents[3]);
        Player[] opponents2 = game.getOpponents(p2);
        assertEquals(4, opponents2.length);
        assertEquals(p2, opponents2[0]);
        assertEquals(p1, opponents2[1]);
        assertEquals(p3, opponents2[2]);
        assertEquals(p4, opponents2[3]);
    }

    @Test
    void testGameReset(){
        Player p1 = new Player(1, new Hand());
        Player p2 = new Player(2, new Hand());
        Player p3 = new Player(3, new Hand());
        Player p4 = new Player(4, new Hand());

        Game g = new Game(new Player[]{p1, p2});

        g.play();

        g.resetGame();

        assertEquals(0, g.getNbTurn());

        assertEquals(0, p1.getCity().size());
        assertEquals(0, p1.getHand().size());
        assertEquals(2, p1.getGold());

        assertEquals(0, p2.getCity().size());
        assertEquals(0, p2.getHand().size());
        assertEquals(2, p2.getGold());

        assertEquals(0, p3.getCity().size());
        assertEquals(0, p3.getHand().size());
        assertEquals(2, p3.getGold());

        assertEquals(0, p4.getCity().size());
        assertEquals(0, p4.getHand().size());
        assertEquals(2, p4.getGold());
    }
}