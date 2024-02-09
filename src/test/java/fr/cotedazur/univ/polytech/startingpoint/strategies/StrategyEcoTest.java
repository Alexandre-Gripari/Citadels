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
    Construction temple = new Construction(CardsName.TEMPLE, Color.RELIGIEUX, 1);
    Construction eglise = new Construction(CardsName.EGLISE, Color.RELIGIEUX, 2);
    Construction monastere = new Construction(CardsName.MONASTERE, Color.RELIGIEUX, 3);
    Construction cathedrale = new Construction(CardsName.CATHEDRALE, Color.RELIGIEUX, 5);

    Construction manoir = new Construction(CardsName.MANOIR, Color.NOBLE, 3);
    Construction chateau = new Construction(CardsName.CHATEAU, Color.NOBLE, 4);
    Construction palais = new Construction(CardsName.PALAIS, Color.NOBLE, 5);
    Construction lidl = new Construction(CardsName.NO_NAME, Color.NEUTRE, 99);
    Construction tour = new Construction(CardsName.TOUR_DE_GUET, Color.SOLDATESQUE, 1);
    Construction forteresse = new Construction(CardsName.FORTERESSE, Color.SOLDATESQUE, 2);
    Construction prison = new Construction(CardsName.PRISON, Color.SOLDATESQUE, 3);
    Construction bastion = new Construction(CardsName.BASTION, Color.SOLDATESQUE, 5);

    Construction taverne = new Construction(CardsName.TAVERNE, Color.COMMERCIAL, 1);
    Construction echoppe = new Construction(CardsName.ECHOPPE, Color.COMMERCIAL, 2);
    Construction marche = new Construction(CardsName.MARCHE, Color.COMMERCIAL, 2);
    Construction comptoir =new Construction(CardsName.COMPTOIR, Color.COMMERCIAL, 3);
    Construction port = new Construction(CardsName.PORT, Color.COMMERCIAL, 4);
    Construction hotel = new Construction(CardsName.HOTEL_DE_VILLE, Color.COMMERCIAL, 5);

    Wonder cour = new Wonder(CardsName.COUR_DES_MIRACLES, 2, WondersPower.COUR_DES_MIRACLES);
    Wonder donjon = new Wonder(CardsName.DONJON, 3, WondersPower.DONJON);
    Wonder laboratoire = new Wonder(CardsName.LABORATOIRE, 5, WondersPower.LABORATOIRE);
    Wonder manufacure = new Wonder(CardsName.MANUFACTURE, 5, WondersPower.MANUFACTURE);
    Wonder observatoire = new Wonder(CardsName.OBSERVATOIRE, 5, WondersPower.OBSERVATOIRE);
    Wonder cimetiere = new Wonder(CardsName.CIMETIERE, 5, WondersPower.CIMETIERE);
    Wonder bibliotheque = new Wonder(CardsName.BIBLIOTHEQUE, 6, WondersPower.BIBLIOTHEQUE);
    Wonder ecole = new Wonder(CardsName.ECOLE_DE_MAGIE, 6, WondersPower.ECOLE_DE_MAGIE);
    Wonder universite = new Wonder(CardsName.UNIVERSITE, 6, WondersPower.UNIVERSITE);
    Wonder dracoport = new Wonder(CardsName.DRACOPORT, 6, WondersPower.DRACOPORT);

    @BeforeEach
    void init() {

        MyLogger.setLogLevel(Level.OFF);

        hand1 = new Hand();

        p1 = new Player(1, hand1);
        p1.getHand().add(new Construction(CardsName.TEMPLE, Color.RELIGIEUX, 1));
        p1.getHand().add(new Construction(CardsName.FORTERESSE, Color.SOLDATESQUE, 2));
        p1.getCity().add(new Construction(CardsName.NO_NAME, Color.NOBLE, 284));

        draw = new Draw();
        draw.addXConstructions(new Construction(CardsName.CATHEDRALE, Color.RELIGIEUX, 5), 1);
        draw.addXConstructions(new Construction(CardsName.CHATEAU, Color.NOBLE, 4), 1);
        draw.addXConstructions(new Construction(CardsName.MONASTERE, Color.RELIGIEUX, 3), 1);
        draw.addXConstructions(new Construction(CardsName.MARCHE, Color.COMMERCIAL, 2), 1);
        draw.addXConstructions(new Construction(CardsName.COMPTOIR, Color.COMMERCIAL, 3), 1);

        hand2 = new Hand();

        p2 = new Player(2, 1, hand2, new City());
        p2.getHand().add(new Construction(CardsName.TEMPLE, Color.RELIGIEUX, 1));
        p2.getHand().add(new Construction(CardsName.FORTERESSE, Color.SOLDATESQUE, 2));
        charactersList.addAll(List.of(Character.ROI,Character.MARCHAND));
    }
    @Test
    void choiceOfCharacter(){
        assertEquals(Character.ROI,strat.chooseCharacter(p1,charactersList,new Player[]{p1,p2}));
    }
    @Test
    void testChooseCard() {
        StrategyAggro s = new StrategyAggro("Test");
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