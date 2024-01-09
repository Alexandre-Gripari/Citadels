package fr.cotedazur.univ.polytech.startingpoint.cards;

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