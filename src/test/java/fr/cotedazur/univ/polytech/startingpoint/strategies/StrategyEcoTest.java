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
    Construction temple = new Construction("Temple", Color.RELIGIEUX, 1);
    Construction eglise = new Construction("Eglise", Color.RELIGIEUX, 2);
    Construction monastere = new Construction("Monastère", Color.RELIGIEUX, 3);
    Construction cathedrale = new Construction("Cathédrale", Color.RELIGIEUX, 5);

    Construction manoir = new Construction("Manoir", Color.NOBLE, 3);
    Construction chateau = new Construction("Château", Color.NOBLE, 4);
    Construction palais = new Construction("Palais", Color.NOBLE, 5);
    Construction lidl = new Construction("Lidl",Color.NEUTRE,99);

    Construction tour = new Construction("Tour de guet", Color.SOLDATESQUE, 1);
    Construction forteresse = new Construction("Forteresse", Color.SOLDATESQUE, 2);
    Construction prison = new Construction("Prison", Color.SOLDATESQUE, 3);
    Construction bastion = new Construction("Bastion", Color.SOLDATESQUE, 5);

    Construction taverne = new Construction("Taverne", Color.COMMERCIAL, 1);
    Construction echoppe = new Construction("Echoppe", Color.COMMERCIAL, 2);
    Construction marche = new Construction("Marché", Color.COMMERCIAL, 2);
    Construction comptoir =new Construction("Comptoir", Color.COMMERCIAL, 3);
    Construction port = new Construction("Port", Color.COMMERCIAL, 4);
    Construction hotel = new Construction("Hôtel de ville", Color.COMMERCIAL, 5);

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
        p1.getHand().add(new Construction("Temple", Color.RELIGIEUX, 1));
        p1.getHand().add(new Construction("Forteresse", Color.SOLDATESQUE, 2));
        p1.getCity().add(new Construction("Gros château", Color.NOBLE, 284));

        draw = new Draw();
        draw.addXConstructions(new Construction("Cathédrale", Color.RELIGIEUX, 5), 1);
        draw.addXConstructions(new Construction("Château", Color.NOBLE, 4), 1);
        draw.addXConstructions(new Construction("Monastère", Color.RELIGIEUX, 3), 1);
        draw.addXConstructions(new Construction("Marché", Color.COMMERCIAL, 2), 1);
        draw.addXConstructions(new Construction("Comptoir", Color.COMMERCIAL, 3), 1);

        hand2 = new Hand();

        p2 = new Player(2, 1, hand2, new City());
        p2.getHand().add(new Construction("Temple", Color.RELIGIEUX, 1));
        p2.getHand().add(new Construction("Forteresse", Color.SOLDATESQUE, 2));
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
        List<Construction> c = new ArrayList<>();
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