package fr.cotedazur.univ.polytech.startingpoint.cards;
import fr.cotedazur.univ.polytech.startingpoint.Draw;
import fr.cotedazur.univ.polytech.startingpoint.MyLogger;
import fr.cotedazur.univ.polytech.startingpoint.Player;
import fr.cotedazur.univ.polytech.startingpoint.players.City;
import fr.cotedazur.univ.polytech.startingpoint.players.Hand;
import org.junit.jupiter.api.*;

import java.util.logging.Level;

import static org.junit.jupiter.api.Assertions.*;


class WondersPowerTest {

    Draw draw = new Draw();
    Player player1 = new Player(1, new Hand());
    Wonder wonder = new Wonder("Cimetière", 3, WondersPower.CIMETIERE);
    Constructions construction = new Constructions("Test", Color.COMMERCIAL, 1);
    Constructions construction2 = new Constructions("Test2", Color.COMMERCIAL, 1);
    Player player2 = new Player(2, new Hand());
    Player player3 = new Player(3, new Hand());

    @BeforeEach
    void setUp() {
        MyLogger.setLogLevel(Level.OFF);
        player1.getHand().add(wonder);
        player1.getHand().add(construction);
        player1.addGold(10);
        player1.buildConstruction(wonder);
        player1.buildConstruction(construction);
        player3.getCity().add(construction2);
    }


    /*@Test
    void testPowerCimetière() {
        Player[] players = {player2, player3, player1};
        player2.setCharacter(Character.CONDOTTIERE);
        player2.addGold(10);
        assertEquals(1, player3.getCity().size());
        assertEquals(construction2, player3.getCity().get(0));
        assertEquals(8, player1.getGold());
        player2.getCharacter().ability(construction, player2, player3);
        assertEquals(construction2,player1.getHand().get(0));
        assertEquals(7, player1.getGold());
    }*/

    @Test
    void testPowerDonjon(){
        Player player4 = new Player(4, new Hand());
        player4.getCity().add(new Wonder("Donjon", 3, WondersPower.DONJON));
        Player[] players = {player2, player4, player1};
        player2.setCharacter(Character.CONDOTTIERE);
        player2.addGold(10);
        Constructions donjon = new Wonder("Donjon",3, WondersPower.DONJON);
        player2.getCharacter().ability(donjon, player2, player4);
        assertEquals(12, player2.getGold());
        assertEquals(1,player4.getCity().size());

    }


    @Test
    void ecoleDeMagieTest() {
        City playerWithEcoleDeMagieCity = new City();
        Hand playerWithEcoleDeMagieHand = new Hand();
        Wonder edm = new Wonder("Ecole de magie", 6, WondersPower.ECOLE_DE_MAGIE);
        playerWithEcoleDeMagieCity.add(edm);
        Player playerWithEcoleDeMagie = new Player(0,  2, playerWithEcoleDeMagieHand, playerWithEcoleDeMagieCity);
        playerWithEcoleDeMagie.getWonders().add(edm);

        Draw d = new Draw();
        d.addXConstructions(new Constructions("testEDM", Color.MERVEILLEUX, 100), 100);

        assertEquals(2, playerWithEcoleDeMagie.getGold());

        playerWithEcoleDeMagie.setCharacter(Character.ROI);
        playerWithEcoleDeMagie.getStrategy().play(new Player[]{playerWithEcoleDeMagie}, d);
        assertEquals(3, playerWithEcoleDeMagie.getGold());

        playerWithEcoleDeMagie.setCharacter(Character.EVEQUE);
        playerWithEcoleDeMagie.getStrategy().play(new Player[]{playerWithEcoleDeMagie}, d);
        assertEquals(6, playerWithEcoleDeMagie.getGold());

        playerWithEcoleDeMagie.setCharacter(Character.MARCHAND);
        playerWithEcoleDeMagie.getStrategy().play(new Player[]{playerWithEcoleDeMagie}, d);
        assertEquals(10, playerWithEcoleDeMagie.getGold());

        playerWithEcoleDeMagie.setCharacter(Character.CONDOTTIERE);
        playerWithEcoleDeMagie.getStrategy().play(new Player[]{playerWithEcoleDeMagie, player1}, d);
        assertEquals(13, playerWithEcoleDeMagie.getGold());

        playerWithEcoleDeMagie.setCharacter(Character.VOLEUR);
        playerWithEcoleDeMagie.getStrategy().play(new Player[]{playerWithEcoleDeMagie}, d);
        assertEquals(15, playerWithEcoleDeMagie.getGold());

        playerWithEcoleDeMagie.setCharacter(Character.MAGICIEN);
        playerWithEcoleDeMagie.getStrategy().play(new Player[]{playerWithEcoleDeMagie}, d);
        assertEquals(17, playerWithEcoleDeMagie.getGold());
    }

    @Test
    void testPowerCourDesMiracles(){
        Player player4 = new Player(4, new Hand());
        Wonder wonder = new Wonder("Cour des miracles", 3, WondersPower.COUR_DES_MIRACLES);
        Constructions c1 = new Constructions("test1", Color.NOBLE, 1);
        Constructions c2 = new Constructions("test2", Color.SOLDATESQUE, 2);
        Constructions c3 = new Constructions("test3", Color.COMMERCIAL, 3);
        Constructions c5 = new Constructions("test5", Color.RELIGIEUX, 5);
        player4.getCity().add(wonder);
        wonder.getWondersPower().power(player4.getCity(), wonder);
        assertEquals(Color.RELIGIEUX, wonder.getColor());
        player4.getCity().add(c1);
        wonder.getWondersPower().power(player4.getCity(), wonder);
        assertEquals(Color.SOLDATESQUE, wonder.getColor());
        player4.getCity().add(c5);
        wonder.getWondersPower().power(player4.getCity(), wonder);
        assertEquals(Color.COMMERCIAL, wonder.getColor());
        player4.getCity().add(c2);
        player4.getCity().add(c3);
        wonder.getWondersPower().power(player4.getCity() , wonder);
        assertEquals(Color.MERVEILLEUX, wonder.getColor());
    }

    @Test
    void testPowerLaboratoire(){
        Draw d = new Draw();
        Player player4 = new Player(4, new Hand());
        Wonder wonder = new Wonder("Laboratoire", 5, WondersPower.LABORATOIRE);
        Constructions c1 = new Constructions("test1", Color.NOBLE, 1);
        Constructions c2 = new Constructions("test2", Color.SOLDATESQUE, 2);
        Constructions c3 = new Constructions("test3", Color.COMMERCIAL, 3);
        Constructions c5 = new Constructions("test5", Color.RELIGIEUX, 5);
        player1.getHand().add(c1);
        player1.getHand().add(c2);
        player1.getHand().add(c3);
        player1.getHand().add(c5);
        player4.getCity().add(wonder);
        wonder.getWondersPower().power(c1, player4, d);
        assertEquals(3, player4.getGold());
        assertFalse(player4.getHand().contains(c1));
        wonder.getWondersPower().power(c2, player4, d);
        assertEquals(4, player4.getGold());
        assertFalse(player4.getHand().contains(c2));
        wonder.getWondersPower().power(c5, player4, d);
        assertEquals(5, player4.getGold());

        assertTrue(d.contains(c1));
        assertTrue(d.contains(c2));
        assertTrue(d.contains(c5));

    }


}