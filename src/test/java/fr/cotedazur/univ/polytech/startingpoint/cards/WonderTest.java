package fr.cotedazur.univ.polytech.startingpoint.cards;

import fr.cotedazur.univ.polytech.startingpoint.Draw;
import fr.cotedazur.univ.polytech.startingpoint.Player;
import fr.cotedazur.univ.polytech.startingpoint.players.Hand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.text.PlainDocument;

import static org.junit.jupiter.api.Assertions.*;

class WonderTest {

    Player player1 = new Player(1, new Hand());
    Wonder wonder = new Wonder("Cour des miracles", 3, WondersPower.COUR_DES_MIRACLES);
    Wonder wonder2 = new Wonder("Donjon", 3, WondersPower.DONJON);


    @BeforeEach
    void setUp() {
        player1.getHand().add(wonder);
        player1.getHand().add(wonder2);
        player1.addGold(10);
    }

    @Test
    void power() {
        wonder.power();
        assertEquals(0,player1.getWonders().size());
        player1.buildConstruction();
        assertEquals(1,player1.getWonders().size());
        player1.buildConstruction();
        assertEquals(2,player1.getWonders().size());
        player1.getWonders().get(0).power();
    }

    @Test
    void getWondersPower() {
        assertEquals(WondersPower.COUR_DES_MIRACLES, wonder.getWondersPower());
    }

    @Test
    void universityTest() {
        Wonder university = new Wonder("Université",WondersPower.UNIVERSITE, 6);

        university.power(university);
        assertEquals(8, university.getValue());
    }

    @Test
    void libraryTest() {
        Player player = new Player(0,new Hand());
        Wonder library = new Wonder("Biblioothèque", WondersPower.BIBLIOTHEQUE, 6);
        player.getCity().add(library);

    @Test
    void manufactureTest() {
        Player player = new Player(0, new Hand());
        Wonder manufacture = new Wonder("Manufacture", WondersPower.MANUFACTURE,5);
        player.getCity().add(manufacture);

        Draw d = new Draw();
        d.addXConstructions(new Constructions("Temple", Color.RELIGIEUX, 1), 3);
        d.addXConstructions(new Constructions("Eglise", Color.RELIGIEUX, 2), 4);
        d.addXConstructions(new Constructions("Monastère", Color.RELIGIEUX, 3), 3);
        d.addXConstructions(new Constructions("Cathédrale", Color.RELIGIEUX, 5), 2);

        manufacture.power(player, d);
        assertEquals(0,player.getHand().size());
        assertEquals(2,player.getGold());
        player.addGold(1);
        manufacture.power(player, d);
        assertEquals(3,player.getHand().size());
        assertEquals(0,player.getGold());
    }

    @Test
    void observatoireTest() {
        Player player = new Player(0, new Hand());
        Wonder observatoire = new Wonder("Observatoire", WondersPower.OBSERVATOIRE,5);
        player.getCity().add(observatoire);

        Draw d = new Draw();
        d.addXConstructions(new Constructions("Temple", Color.RELIGIEUX, 1), 1);
        d.addXConstructions(new Constructions("Eglise", Color.RELIGIEUX, 2), 1);
        d.addXConstructions(new Constructions("Monastère", Color.RELIGIEUX, 3), 1);
        d.addXConstructions(new Constructions("Cathédrale", Color.RELIGIEUX, 5), 1);


        assertEquals(0, player.getHand().size());
        assertEquals(4,d.size());
        library.power(player, d);
        assertEquals(2, player.getHand().size());
        assertEquals(2, d.size());
 
        player.getHand().add(player.takeConstruction(d));
        observatoire.power(player, d);
        assertEquals(1,player.getHand().size());
        assertEquals("Cathédrale", d.peek().getName());
        assertEquals(3,d.size());
    }

    @Test
    void testEquals() {
        Wonder wonder3 = new Wonder("Cour des miracles", 3, WondersPower.COUR_DES_MIRACLES);
        Wonder wonder4 = new Wonder("JE TEST OK ?", 3, WondersPower.COUR_DES_MIRACLES);
        assertEquals(wonder, wonder3);
        assertNotEquals(wonder, wonder2);
        assertEquals(wonder, wonder4);
    }

    @Test
    void testSetColor(){
        Wonder wonder5 = new Wonder("Cour des miracles", 3, WondersPower.COUR_DES_MIRACLES);
        wonder5.setColor(Color.SOLDATESQUE);
        assertEquals(Color.SOLDATESQUE, wonder5.getColor());
    }


}