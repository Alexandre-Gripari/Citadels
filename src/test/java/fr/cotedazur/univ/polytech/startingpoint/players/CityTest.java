package fr.cotedazur.univ.polytech.startingpoint.players;

import fr.cotedazur.univ.polytech.startingpoint.cards.Color;
import fr.cotedazur.univ.polytech.startingpoint.cards.Construction;
import fr.cotedazur.univ.polytech.startingpoint.*;
import fr.cotedazur.univ.polytech.startingpoint.cards.Wonder;
import fr.cotedazur.univ.polytech.startingpoint.cards.WondersPower;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.logging.Level;

import static org.junit.jupiter.api.Assertions.*;

class CityTest {
    Construction c1 = new Construction("test", Color.COMMERCIAL, 1);
    Construction c2 = new Construction("test2", Color.COMMERCIAL, 2);
    Construction c3 = new Construction("test3", Color.COMMERCIAL, 3);
    Construction c4 = new Construction("test4", Color.COMMERCIAL, 4);
    Construction c5 = new Construction("test5", Color.COMMERCIAL, 5);
    Construction c6 = new Construction("test6", Color.COMMERCIAL, 6);
    Construction r1 = new Construction("testr1", Color.RELIGIEUX, 1);
    Construction r2 = new Construction("testr2", Color.RELIGIEUX, 2);
    Construction r3 = new Construction("testr3", Color.RELIGIEUX, 3);
    Construction m1 = new Wonder("Dracoport", 6, WondersPower.DRACOPORT); // Pour tester le Dracoport

    //Test cour des miracles
    Construction m2 = new Wonder("Cour des miracles", 2, WondersPower.COUR_DES_MIRACLES);
    Construction m3 = new Wonder("Cour des miracles", 2, WondersPower.COUR_DES_MIRACLES);
    Construction c7 = new Construction("religieux", Color.RELIGIEUX, 1);
    Construction c8 = new Construction("noble", Color.NOBLE, 1);
    Construction c9 = new Construction("soldatesque", Color.SOLDATESQUE, 1);

    City city1 = new City();
    City city2 = new City();
    City city3 = new City();
    City city4 = new City();
    City city5 = new City();

    @BeforeEach
    void init() {
        MyLogger.setLogLevel(Level.OFF);
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

        city4.add(m2);
        city4.add(c7);
        city4.add(c8);
        city4.add(c9);

        //test bonus fin de partie avec toutes les couleurs
        city5.add(c1);
        city5.add(r1);
        city5.add(c8);
        city5.add(c9);
        city5.add(m3);
    }



    @Test
    void cityValue(){
        assertEquals(6,city1.cityValue());
        assertEquals(15,city2.cityValue());
        assertEquals(14, city3.cityValue());
        city4.cityValue();
        assertEquals(Color.COMMERCIAL, city4.get(0).getColor());
        assertEquals(Color.MERVEILLEUX, city5.get(4).getColor());
        assertEquals(Color.NEUTRE, city5.missingColor());
        assertEquals(9, city5.cityValue());
        assertEquals(Color.MERVEILLEUX, city5.get(4).getColor());
        city5.remove(3);
        assertEquals(Color.MERVEILLEUX, city5.get(3).getColor());
        assertEquals(Color.SOLDATESQUE, city5.missingColor());
        assertEquals(5, city5.cityValue());
        assertEquals(Color.SOLDATESQUE, city5.get(3).getColor());
        city5.add(m1);
        assertEquals(16, city5.cityValue());
        assertEquals(Color.SOLDATESQUE, city5.get(3).getColor());
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

    @Test
    void missingColor(){
        assertEquals(Color.RELIGIEUX, city1.missingColor());
        assertEquals(Color.RELIGIEUX, city2.missingColor());
        assertEquals(Color.NOBLE, city3.missingColor());
        City city4 = new City();
        Construction c7 = new Construction("test7", Color.RELIGIEUX, 7);
        Construction c8 = new Construction("test8", Color.NOBLE, 8);
        Construction c9 = new Construction("test9", Color.SOLDATESQUE, 9);
        Construction c10 = new Construction("test10", Color.COMMERCIAL, 10);
        Construction c11 = new Construction("test11", Color.MERVEILLEUX, 11);
        Wonder w1 = new Wonder("Cour des miracles", 1, WondersPower.COUR_DES_MIRACLES);
        Wonder w2 = new Wonder("testw2", 2, WondersPower.DONJON);
        city4.add(c7);
        city4.add(c8);
        city4.add(c9);
        city4.add(c10);
        city4.add(c11);
        assertEquals(Color.NEUTRE, city4.missingColor());
        city4.remove(0);
        assertEquals(Color.RELIGIEUX, city4.missingColor());
        city4.add(c7);
        city4.remove(0);
        assertEquals(Color.NOBLE, city4.missingColor());
        city4.add(c8);
        city4.remove(0);
        assertEquals(Color.SOLDATESQUE, city4.missingColor());
        city4.add(c9);
        city4.remove(0);
        assertEquals(Color.COMMERCIAL, city4.missingColor());
        city4.add(c10);
        city4.remove(0);
        assertEquals(Color.MERVEILLEUX, city4.missingColor());
        city4.add(w2);
        assertEquals(Color.NEUTRE, city4.missingColor());
        city4.remove(4);
        city4.add(w1);
        assertEquals(Color.NEUTRE, city4.missingColor());
    }
}