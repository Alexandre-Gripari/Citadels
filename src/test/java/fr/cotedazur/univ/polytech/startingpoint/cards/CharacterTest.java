package fr.cotedazur.univ.polytech.startingpoint.cards;

import fr.cotedazur.univ.polytech.startingpoint.Draw;
import fr.cotedazur.univ.polytech.startingpoint.Player;
import fr.cotedazur.univ.polytech.startingpoint.Game;
import fr.cotedazur.univ.polytech.startingpoint.players.City;
import fr.cotedazur.univ.polytech.startingpoint.players.Hand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

class CharacterTest {

    Hand hand1;
    Hand hand2;

    Hand hand3;
    Player p1;
    Player p2;
    Player p3;
    @BeforeEach
    void init() {
        hand1 = new Hand();
        hand2 = new Hand();
        hand3 = new Hand();
        p1 = new Player(1, hand1);
        p2 = new Player(2, hand2);
        p3 = new Player(2, hand3);
        p1.setCharacter(Character.ASSASSIN);
        p2.setCharacter(Character.VOLEUR);
        p3.setCharacter(Character.MARCHAND);
    }
    @Test
    void assassinability(){
        Character.ASSASSIN.ability(p3,p2);
        assertEquals(true,p3.isDead());
    }
    @Test
    void voleurability(){
        p1.setGold(10);
        p2.setGold(10);
        p3.setGold(10);
        Character.VOLEUR.ability(p2,p3);
        assertEquals(0,p3.getGold());
        assertEquals(20,p2.getGold());
        Character.VOLEUR.ability(p2,p1);
        assertEquals(20,p2.getGold());
        assertEquals(10,p1.getGold());
    }

}