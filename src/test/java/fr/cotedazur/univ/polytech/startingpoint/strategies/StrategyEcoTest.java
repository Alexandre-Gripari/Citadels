package fr.cotedazur.univ.polytech.startingpoint.strategies;

import fr.cotedazur.univ.polytech.startingpoint.Draw;
import fr.cotedazur.univ.polytech.startingpoint.MyLogger;
import fr.cotedazur.univ.polytech.startingpoint.Player;
import fr.cotedazur.univ.polytech.startingpoint.cards.*;
import fr.cotedazur.univ.polytech.startingpoint.cards.Character;
import fr.cotedazur.univ.polytech.startingpoint.players.City;
import fr.cotedazur.univ.polytech.startingpoint.players.Hand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;

import static org.junit.jupiter.api.Assertions.*;

class StrategyEcoTest {
    Player p1 = new Player(1,0, new Hand());
    Player p2 = new Player(2, new Hand());
    Hand hand1;
    Hand hand2;
    Draw draw;

    StrategyEco strat = new StrategyEco("oui");

    List<Character> charactersList = new ArrayList<Character>();
    Constructions temple = new Constructions("Temple", Color.RELIGIEUX, 1);
    Constructions eglise = new Constructions("Eglise", Color.RELIGIEUX, 2);
    Constructions monastere = new Constructions("Monastère", Color.RELIGIEUX, 3);
    Constructions cathedrale = new Constructions("Cathédrale", Color.RELIGIEUX, 5);

    Constructions manoir = new Constructions("Manoir", Color.NOBLE, 3);
    Constructions chateau = new Constructions("Château", Color.NOBLE, 4);
    Constructions palais = new Constructions("Palais", Color.NOBLE, 5);
    Constructions lidl = new Constructions("Lidl",Color.NEUTRE,99);

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

    @BeforeEach
    void init() {

        MyLogger.setLogLevel(Level.OFF);

        hand1 = new Hand();

        p1 = new Player(1, hand1);
        p1.getHand().add(new Constructions("Temple", Color.RELIGIEUX, 1));
        p1.getHand().add(new Constructions("Forteresse", Color.SOLDATESQUE, 2));
        p1.getCity().add(new Constructions("Gros château", Color.NOBLE, 284));

        draw = new Draw();
        draw.addXConstructions(new Constructions("Cathédrale", Color.RELIGIEUX, 5), 1);
        draw.addXConstructions(new Constructions("Château", Color.NOBLE, 4), 1);
        draw.addXConstructions(new Constructions("Monastère", Color.RELIGIEUX, 3), 1);
        draw.addXConstructions(new Constructions("Marché", Color.COMMERCIAL, 2), 1);
        draw.addXConstructions(new Constructions("Comptoir", Color.COMMERCIAL, 3), 1);

        hand2 = new Hand();

        p2 = new Player(2, 1, hand2, new City());
        p2.getHand().add(new Constructions("Temple", Color.RELIGIEUX, 1));
        p2.getHand().add(new Constructions("Forteresse", Color.SOLDATESQUE, 2));
        charactersList.addAll(List.of(Character.ROI,Character.MARCHAND));
    }
    @Test
    void choiceOfCharacter(){
        assertEquals(Character.ROI,strat.chooseCharacter(p1,charactersList,new Player[]{p1,p2}));
    }
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
        List<Constructions> c = new ArrayList<>();
        c.add(universite);
        c.add(dracoport);
        c.add(ecole);
        c.add(cour);
        c.add(lidl);
        Player ptest = new Player(1,10,hand);
        ptest.getHand().setHand(c);
        StrategyEco s = new StrategyEco("Test");
        assertEquals(ecole, s.constructionToBuild(ptest));
        ptest.setGold(0);
        assertNull(s.constructionToBuild(ptest));
    }
}