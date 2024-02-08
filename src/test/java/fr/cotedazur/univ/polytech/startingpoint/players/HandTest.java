package fr.cotedazur.univ.polytech.startingpoint.players;

import fr.cotedazur.univ.polytech.startingpoint.cards.Color;
import fr.cotedazur.univ.polytech.startingpoint.cards.Constructions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HandTest {

    @Test
    void testMin() {
        Hand hand = new Hand();
        Constructions c1 = new Constructions("c1", Color.RELIGIEUX, 5);
        Constructions c2 = new Constructions("c2", Color.MERVEILLEUX, 3);
        Constructions c3 = new Constructions("c3", Color.COMMERCIAL, 1);

        hand.add(c1);
        hand.add(c2);
        hand.add(c3);

        assertEquals(c3, hand.min());
    }
}
