package fr.cotedazur.univ.polytech.startingpoint.players;

import fr.cotedazur.univ.polytech.startingpoint.cards.Color;
import fr.cotedazur.univ.polytech.startingpoint.cards.Construction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class HandTest {

    @Test
    void testMin() {
        Hand hand = new Hand();
        Construction c1 = new Construction("c1", Color.RELIGIEUX, 5);
        Construction c2 = new Construction("c2", Color.MERVEILLEUX, 3);
        Construction c3 = new Construction("c3", Color.COMMERCIAL, 1);

        hand.add(c1);
        hand.add(c2);
        hand.add(c3);

        assertEquals(c3, hand.min());
    }
}
