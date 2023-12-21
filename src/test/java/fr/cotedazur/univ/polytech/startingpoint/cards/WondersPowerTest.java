package fr.cotedazur.univ.polytech.startingpoint.cards;

import fr.cotedazur.univ.polytech.startingpoint.Player;
import fr.cotedazur.univ.polytech.startingpoint.players.City;
import fr.cotedazur.univ.polytech.startingpoint.players.Hand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WondersPowerTest {


    City playerWithEcoleDeMagieCity = new City();
    Hand playerWithEcoleDeMagieHand = new Hand();
    Player playerWithEcoleDeMagie = new Player(1,  2, playerWithEcoleDeMagieHand, playerWithEcoleDeMagieCity);
    Wonder edm = new Wonder("Ecole de magie", 6, WondersPower.ECOLE_DE_MAGIE);

    @Test
    void ecoleDeMagieTest() {
        assertEquals(2, playerWithEcoleDeMagie.getGold());
        playerWithEcoleDeMagie.setCharacter(Character.ROI);
        edm.getPower().power(playerWithEcoleDeMagie);
        assertEquals(3, playerWithEcoleDeMagie.getGold());
        playerWithEcoleDeMagie.setCharacter(Character.EVEQUE);
        edm.getPower().power(playerWithEcoleDeMagie);
        assertEquals(4, playerWithEcoleDeMagie.getGold());
        playerWithEcoleDeMagie.setCharacter(Character.MARCHAND);
        edm.getPower().power(playerWithEcoleDeMagie);
        assertEquals(5, playerWithEcoleDeMagie.getGold());
        playerWithEcoleDeMagie.setCharacter(Character.CONDOTTIERE);
        edm.getPower().power(playerWithEcoleDeMagie);
        assertEquals(6, playerWithEcoleDeMagie.getGold());
        playerWithEcoleDeMagie.setCharacter(Character.VOLEUR);
        edm.getPower().power(playerWithEcoleDeMagie);
        assertEquals(6, playerWithEcoleDeMagie.getGold());
        playerWithEcoleDeMagie.setCharacter(Character.MAGICIEN);
        edm.getPower().power(playerWithEcoleDeMagie);
        assertEquals(6, playerWithEcoleDeMagie.getGold());
    }


}