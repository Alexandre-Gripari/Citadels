package fr.cotedazur.univ.polytech.startingpoint;

import fr.cotedazur.univ.polytech.startingpoint.cards.Color;
import fr.cotedazur.univ.polytech.startingpoint.cards.Construction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DrawTest {

    boolean equalsTest(Draw d1, Draw d2){
        while(!d1.getDeck().isEmpty()){
            if(!d1.getDeck().poll().equals(d2.getDeck().poll())){
                return false;
            }
        }
        return true;
    }

    @Test
    void addXConstructions() {
        Construction c = new Construction("test", Color.COMMERCIAL, 1);
        Draw draw = new Draw();
        assertEquals(0, draw.getDeck().size());
        draw.addXConstructions(c, 3);
        assertEquals(3, draw.getDeck().size());
        assertEquals(c, draw.getDeck().poll());
    }

    @Test
    void shuffle() {
        Construction c1 = new Construction("test", Color.COMMERCIAL, 1);
        Construction c2 = new Construction("test2", Color.COMMERCIAL, 2);
        Construction c3 = new Construction("test3", Color.COMMERCIAL, 3);
        Draw draw = new Draw();
        Draw draw2 = new Draw();
        draw.addXConstructions(c1, 3);
        draw.addXConstructions(c2, 3);
        draw.addXConstructions(c3, 5);
        draw2.addXConstructions(c1, 3);
        draw2.addXConstructions(c2, 3);
        draw2.addXConstructions(c3, 5);
        draw2.shuffle();
        assertFalse(equalsTest(draw, draw2));

    }

    @Test
    void draw() {
        Construction c1 = new Construction("test", Color.COMMERCIAL, 1);
        Construction c2 = new Construction("test2", Color.COMMERCIAL, 2);
        Draw draw = new Draw();
        draw.addXConstructions(c1, 1);
        draw.addXConstructions(c2, 3);
        assertEquals(4, draw.getDeck().size());
        assertEquals(c1, draw.draw());
        assertEquals(3, draw.getDeck().size());
        assertEquals(c2, draw.draw());
        assertEquals(2, draw.getDeck().size());
    }


}