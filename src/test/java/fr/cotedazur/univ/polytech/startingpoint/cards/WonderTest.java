package fr.cotedazur.univ.polytech.startingpoint.cards;

import fr.cotedazur.univ.polytech.startingpoint.Draw;
import fr.cotedazur.univ.polytech.startingpoint.MyLogger;
import fr.cotedazur.univ.polytech.startingpoint.Player;
import fr.cotedazur.univ.polytech.startingpoint.players.Hand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.logging.Level;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class WonderTest {

    Player player1 = new Player(1, new Hand());
    Wonder wonder = new Wonder(CardsName.COUR_DES_MIRACLES, 3, WondersPower.COUR_DES_MIRACLES);
    Wonder wonder2 = new Wonder(CardsName.DONJON, 3, WondersPower.DONJON);


    @BeforeEach
    void setUp() {
        MyLogger.setLogLevel(Level.OFF);
        player1.getHand().add(wonder);
        player1.getHand().add(wonder2);
        player1.addGold(10);
    }

    @Test
    void power() {
        wonder.power();
        assertEquals(0,player1.getWonders().size());
        player1.buildConstruction(wonder);
        assertEquals(1,player1.getWonders().size());
        player1.buildConstruction(wonder2);
        assertEquals(2,player1.getWonders().size());
        player1.getWonders().get(0).power();
    }

    @Test
    void getWondersPower() {
        assertEquals(WondersPower.COUR_DES_MIRACLES, wonder.getWondersPower());
    }


    @Test
    void libraryTest() {
        Player player = new Player(0, new Hand());
        Wonder library = new Wonder(CardsName.BIBLIOTHEQUE, 6, WondersPower.BIBLIOTHEQUE);
        player.getCity().add(library);

        Draw d = new Draw();
        d.addXConstructions(new Construction(CardsName.TEMPLE, Color.RELIGIEUX, 1), 1);
        d.addXConstructions(new Construction(CardsName.EGLISE, Color.RELIGIEUX, 2), 1);
        d.addXConstructions(new Construction(CardsName.MANOIR, Color.RELIGIEUX, 3), 1);
        d.addXConstructions(new Construction(CardsName.CATHEDRALE, Color.RELIGIEUX, 5), 1);

        assertEquals(0, player.getHand().size());
        assertEquals(4,d.size());
        library.power(player, d);
        assertEquals(2, player.getHand().size());
        assertEquals(2, d.size());
    }

    @Test
    void manufactureTest() {
        Player player = new Player(0, new Hand());
        Wonder manufacture = new Wonder(CardsName.MANUFACTURE, 5, WondersPower.MANUFACTURE);
        player.getCity().add(manufacture);

        Draw d = new Draw();
        d.addXConstructions(new Construction(CardsName.TEMPLE, Color.RELIGIEUX, 1), 3);
        d.addXConstructions(new Construction(CardsName.EGLISE, Color.RELIGIEUX, 2), 4);
        d.addXConstructions(new Construction(CardsName.MONASTERE, Color.RELIGIEUX, 3), 3);
        d.addXConstructions(new Construction(CardsName.CATHEDRALE, Color.RELIGIEUX, 5), 2);

        manufacture.power(player, d);
        assertEquals(0,player.getHand().size());
        assertEquals(2,player.getGold());
        player.addGold(1);
        manufacture.power(player, d);
        assertEquals(3,player.getHand().size());
        assertEquals(0,player.getGold());
    }

    /*@Test
    void observatoireTest() {
        Player player = new Player(0, new Hand());
        Wonder observatoire = new Wonder("Observatoire", 5, WondersPower.OBSERVATOIRE);
        player.getCity().add(observatoire);

        Draw d = new Draw();
        d.addXConstructions(new Constructions(CardsName.TEMPLE, Color.RELIGIEUX, 1), 1);
        d.addXConstructions(new Constructions("Eglise", Color.RELIGIEUX, 2), 1);
        d.addXConstructions(new Constructions("Monastère", Color.RELIGIEUX, 3), 1);
        d.addXConstructions(new Constructions("Cathédrale", Color.RELIGIEUX, 5), 1);
 
        player.getHand().add(player.takeConstruction(d,3).get(0));
        assertEquals(1,player.getHand().size());
        assertEquals("Cathédrale", d.peek().getName());
        assertEquals(3,d.size());
    }*/

    @Test
    void testEquals() {
        Wonder wonder3 = new Wonder(CardsName.COUR_DES_MIRACLES, 3, WondersPower.COUR_DES_MIRACLES);
        Wonder wonder4 = new Wonder(CardsName.NO_NAME, 3, WondersPower.COUR_DES_MIRACLES);
        assertEquals(wonder, wonder3);
        assertNotEquals(wonder, wonder2);
        assertEquals(wonder, wonder4);
    }

    @Test
    void testSetColor(){
        Wonder wonder5 = new Wonder(CardsName.COUR_DES_MIRACLES, 3, WondersPower.COUR_DES_MIRACLES);
        wonder5.setColor(Color.SOLDATESQUE);
        assertEquals(Color.SOLDATESQUE, wonder5.getColor());
    }

    @Test
    void testHashCode() {
        Wonder w = new Wonder(CardsName.NO_NAME, 3, WondersPower.MANUFACTURE);
        assertEquals(WondersPower.MANUFACTURE.hashCode(), w.hashCode());
    }
}