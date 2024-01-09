package fr.cotedazur.univ.polytech.startingpoint.cards;
import fr.cotedazur.univ.polytech.startingpoint.Draw;
import fr.cotedazur.univ.polytech.startingpoint.Player;
import fr.cotedazur.univ.polytech.startingpoint.players.City;
import fr.cotedazur.univ.polytech.startingpoint.players.Hand;
import org.junit.jupiter.api.*;

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
        player1.getHand().add(wonder);
        player1.getHand().add(construction);
        player1.addGold(10);
        player1.buildConstruction();
        player1.buildConstruction();
        player3.getCity().add(construction2);
    }


    @Test
    void testPowerCimetière() {
        Player[] players = {player2, player3, player1};
        player2.setCharacter(Character.CONDOTTIERE);
        player2.addGold(10);
        assertEquals(1, player3.getCity().size());
        assertEquals(construction2, player3.getCity().get(0));
        assertEquals(8, player1.getGold());
        player2.useAbility(draw, players);
        assertEquals(construction2,player1.getHand().get(0));
        assertEquals(7, player1.getGold());
    }

    @Test
    void testPowerDonjon(){
        Player player4 = new Player(4, new Hand());
        player4.getCity().add(new Wonder("Donjon", 3, WondersPower.DONJON));
        Player[] players = {player2, player4, player1};
        player2.setCharacter(Character.CONDOTTIERE);
        player2.addGold(10);
        player2.useAbility(draw, players);
        assertEquals(12, player2.getGold());
        assertEquals(1,player4.getCity().size());

    }

    City playerWithEcoleDeMagieCity = new City();
    Hand playerWithEcoleDeMagieHand = new Hand();
    Player playerWithEcoleDeMagie = new Player(1,  2, playerWithEcoleDeMagieHand, playerWithEcoleDeMagieCity);
    Wonder edm = new Wonder("Ecole de magie", 6, WondersPower.ECOLE_DE_MAGIE);

    @Test
    void ecoleDeMagieTest() {
        assertEquals(2, playerWithEcoleDeMagie.getGold());
        playerWithEcoleDeMagie.setCharacter(Character.ROI);
        edm.getWondersPower().power(playerWithEcoleDeMagie);
        assertEquals(3, playerWithEcoleDeMagie.getGold());
        playerWithEcoleDeMagie.setCharacter(Character.EVEQUE);
        edm.getWondersPower().power(playerWithEcoleDeMagie);
        assertEquals(4, playerWithEcoleDeMagie.getGold());
        playerWithEcoleDeMagie.setCharacter(Character.MARCHAND);
        edm.getWondersPower().power(playerWithEcoleDeMagie);
        assertEquals(5, playerWithEcoleDeMagie.getGold());
        playerWithEcoleDeMagie.setCharacter(Character.CONDOTTIERE);
        edm.getWondersPower().power(playerWithEcoleDeMagie);
        assertEquals(6, playerWithEcoleDeMagie.getGold());
        playerWithEcoleDeMagie.setCharacter(Character.VOLEUR);
        edm.getWondersPower().power(playerWithEcoleDeMagie);
        assertEquals(6, playerWithEcoleDeMagie.getGold());
        playerWithEcoleDeMagie.setCharacter(Character.MAGICIEN);
        edm.getWondersPower().power(playerWithEcoleDeMagie);
        assertEquals(6, playerWithEcoleDeMagie.getGold());
    }


}