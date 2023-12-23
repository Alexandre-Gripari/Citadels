package fr.cotedazur.univ.polytech.startingpoint.players;

import fr.cotedazur.univ.polytech.startingpoint.cards.Color;
import fr.cotedazur.univ.polytech.startingpoint.cards.Constructions;
import fr.cotedazur.univ.polytech.startingpoint.*;
import fr.cotedazur.univ.polytech.startingpoint.cards.Wonder;
import fr.cotedazur.univ.polytech.startingpoint.cards.WondersPower;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CityTest {
    Constructions c1 = new Constructions("test", Color.COMMERCIAL, 1);
    Constructions c2 = new Constructions("test2", Color.COMMERCIAL, 2);
    Constructions c3 = new Constructions("test3", Color.COMMERCIAL, 3);
    Constructions c4 = new Constructions("test4", Color.COMMERCIAL, 4);
    Constructions c5 = new Constructions("test5", Color.COMMERCIAL, 5);
    Constructions c6 = new Constructions("test6", Color.COMMERCIAL, 6);
    Constructions r1 = new Constructions("testr1", Color.RELIGIEUX, 1);
    Constructions r2 = new Constructions("testr2", Color.RELIGIEUX, 2);
    Constructions r3 = new Constructions("testr3", Color.RELIGIEUX, 3);
    Constructions m1 = new Wonder("Dracoport", 6, WondersPower.DRACOPORT); // Pour tester le Dracoport

    City city1 = new City();
    City city2 = new City();
    City city3 = new City();

    @BeforeEach
    void init() {
        city1.add(c1);
        city1.add(c2);
        city1.add(c3);
        city2.add(c4);
        city2.add(c5);
        city2.add(c6);
        city3.add(r1);
        city3.add(r2);
        city3.add(r3);
        city3.add(m1);
    }



    @Test
    void cityValue(){
        assertEquals(6,city1.cityValue());
        assertEquals(15,city2.cityValue());
        assertEquals(14, city3.cityValue());
    }

    @Test
    void compareTo(){
        assertEquals(-9,city1.compareTo(city2));
        assertEquals(9,city2.compareTo(city1));

        assertEquals(0,city1.compareTo(city1));

        assertEquals(0,city2.compareTo(city2));
        assertEquals(0,city3.compareTo(city3));

        assertEquals(-8,city1.compareTo(city3));
        assertEquals(8,city3.compareTo(city1));
    }
}