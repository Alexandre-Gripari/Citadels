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
    Wonder wonder = new Wonder("Cour des miracles", WondersPower.COUR_DES_MIRACLES, 3);
    Wonder wonder2 = new Wonder("Donjon", WondersPower.DONJON, 3);


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
    }
}