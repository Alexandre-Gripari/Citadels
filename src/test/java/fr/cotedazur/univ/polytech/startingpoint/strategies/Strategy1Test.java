package fr.cotedazur.univ.polytech.startingpoint.strategies;

import fr.cotedazur.univ.polytech.startingpoint.Draw;
import fr.cotedazur.univ.polytech.startingpoint.Player;
import fr.cotedazur.univ.polytech.startingpoint.cards.Color;
import fr.cotedazur.univ.polytech.startingpoint.cards.Constructions;
import fr.cotedazur.univ.polytech.startingpoint.cards.Wonder;
import fr.cotedazur.univ.polytech.startingpoint.cards.WondersPower;
import fr.cotedazur.univ.polytech.startingpoint.players.City;
import fr.cotedazur.univ.polytech.startingpoint.players.Hand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Strategy1Test {

    Constructions temple = new Constructions("Temple", Color.RELIGIEUX, 1);
    Constructions eglise = new Constructions("Eglise", Color.RELIGIEUX, 2);
    Constructions monastere = new Constructions("Monastère", Color.RELIGIEUX, 3);
    Constructions cathedrale = new Constructions("Cathédrale", Color.RELIGIEUX, 5);

    Constructions manoir = new Constructions("Manoir", Color.NOBLE, 3);
    Constructions chateau = new Constructions("Château", Color.NOBLE, 4);
    Constructions palais = new Constructions("Palais", Color.NOBLE, 5);

    Constructions tour = new Constructions("Tour de guet", Color.SOLDATESQUE, 1);
    Constructions forteresse = new Constructions("Forteresse", Color.SOLDATESQUE, 2);
    Constructions prison = new Constructions("Prison", Color.SOLDATESQUE, 3);
    Constructions bastion = new Constructions("Bastion", Color.SOLDATESQUE, 5);

    Constructions taverne = new Constructions("Taverne", Color.COMMERCIAL, 1);
    Constructions echoppe = new Constructions("Echoppe", Color.COMMERCIAL, 2);
    Constructions marche = new Constructions("Marché", Color.COMMERCIAL, 2);
    Constructions comptoir =new Constructions("Comptoir", Color.COMMERCIAL, 3);
    Constructions port = new Constructions("Port", Color.COMMERCIAL, 4);
    Constructions hotel = new Constructions("Hôtel de ville", Color.COMMERCIAL, 5);

    Wonder cour = new Wonder("Cour des miracles", 2, WondersPower.COUR_DES_MIRACLES);
    Wonder donjon = new Wonder("Donjon", 3, WondersPower.DONJON);
    Wonder laboratoire = new Wonder("Laboratoire", 5, WondersPower.LABORATOIRE);
    Wonder manufacure = new Wonder("Manufacture", 5, WondersPower.MANUFACTURE);
    Wonder observatoire = new Wonder("Observatoire", 5, WondersPower.OBSERVATOIRE);
    Wonder cimetiere = new Wonder("Cimetière", 5, WondersPower.CIMETIERE);
    Wonder bibliotheque = new Wonder("Bibliothèque", 6, WondersPower.BIBLIOTHEQUE);
    Wonder ecole = new Wonder("Ecole de magie", 6, WondersPower.ECOLE_DE_MAGIE);
    Wonder universite = new Wonder("Université", 6, WondersPower.UNIVERSITE);
    Wonder dracoport = new Wonder("Dracoport", 6, WondersPower.DRACOPORT);

    @Test
    void testChooseCard() {
        Strategy1 s = new Strategy1("Test");
        Player p = new Player(0, 2, new Hand(), new City(), s);
        p.getCity().add(temple);
        p.getCity().add(palais);

        p.getHand().add(cimetiere);
        p.getHand().add(marche);

        assertEquals(taverne, s.chooseCard(new ArrayList(Arrays.asList(prison, taverne, port, eglise)), p));
        assertEquals(echoppe, s.chooseCard(new ArrayList(Arrays.asList(temple, palais, cathedrale, echoppe)), p));
        assertEquals(palais, s.chooseCard(new ArrayList(Arrays.asList(palais, temple, cimetiere)), p));
        assertEquals(donjon, s.chooseCard(new ArrayList(Arrays.asList(prison, comptoir, donjon, monastere)), p));
    }

    @Test
    void testConstructionToBuild() {
        Hand hand = new Hand();
        hand.add(ecole);
        hand.add(cour);
        hand.add(hotel);
        Strategy1 s = new Strategy1("Test");

        assertEquals(cour, s.constructionToBuild(hand, 5));
        assertEquals(null, s.constructionToBuild(hand, 0));
    }

    @Test
    void testGoldOrCard() {
        Strategy1 s = new Strategy1("Test");
        Player p = new Player(0, 2, new Hand(), new City(), s);
        Draw d = new Draw();

        d.add(tour);
        d.add(bastion);

        assertEquals(2, s.goldOrCard(new Player[]{p}, d));

        p.getWonders().add(observatoire);
        assertEquals(3, s.goldOrCard(new Player[]{p}, d));

        p.getWonders().add(bibliotheque);
        assertEquals(3, s.goldOrCard(new Player[]{p}, d));

        p.getWonders().remove(observatoire);
        assertEquals(-1, s.goldOrCard(new Player[]{p}, d));

        p.getHand().add(manoir);
        assertEquals(0, s.goldOrCard(new Player[]{p}, d));
    }
}
