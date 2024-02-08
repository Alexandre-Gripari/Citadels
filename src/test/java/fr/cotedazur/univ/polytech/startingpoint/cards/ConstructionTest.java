package fr.cotedazur.univ.polytech.startingpoint.cards;

import org.junit.jupiter.api.*;


import static org.junit.jupiter.api.Assertions.*;

class ConstructionTest {

    @Test
    void setValueTest() {
        Construction c = new Construction("test setter", Color.NOBLE, 4);
        c.setValue(8);
        assertEquals(8, c.getValue());
    }
}
