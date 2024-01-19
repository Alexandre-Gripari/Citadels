package fr.cotedazur.univ.polytech.startingpoint.strategies;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Strategy1Test {

    @Test
    void testChooseCharacter() {

    }

    @Test
    void testGetCharacterPriority() {
        Strategy1 strategy1 = new Strategy1("Agressif");
        assertEquals(1, strategy1.getCharacterPriority(null).size());


    }

}