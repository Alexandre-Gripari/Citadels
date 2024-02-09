package fr.cotedazur.univ.polytech.startingpoint.strategies;

import fr.cotedazur.univ.polytech.startingpoint.Draw;
import fr.cotedazur.univ.polytech.startingpoint.Game;
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
    StrategyEco strat = new StrategyEco("oui");
    Player p1 = new Player(1,2,new Hand(),new City(),strat);
    Player p2 = new Player(2,2,new Hand(),new City(),strat);
    Player p3 = new Player(3,2,new Hand(),new City(),strat);
    Player p4 = new Player(4,2,new Hand(),new City(),strat);
    Hand hand1;
    Hand hand2;
    Draw draw;

    Player[] players2 = new Player[]{p1, p2, p3, p4};

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

        p1 = new Player(1,2, hand1,new City(),strat);
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
        StrategyEco s = new StrategyEco("Test");
        Player p = new Player(0, 2, new Hand(), new City(), s);
        p.getCity().add(temple);
        p.getCity().add(palais);

        p.getHand().add(cimetiere);
        p.getHand().add(marche);

        assertEquals(prison, s.chooseCard(new ArrayList(Arrays.asList(prison, taverne, port, eglise)), p));
        assertEquals(palais, s.chooseCard(new ArrayList(Arrays.asList(temple, palais, cathedrale, echoppe)), p));
        assertEquals(cimetiere, s.chooseCard(new ArrayList(Arrays.asList(palais, temple, cimetiere)), p));
        assertEquals(prison, s.chooseCard(new ArrayList(Arrays.asList(prison, comptoir, donjon, monastere)), p));
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
        Player ptest = new Player(1,2, hand1,new City(),strat);
        ptest.getHand().setHand(c);
        StrategyEco s = new StrategyEco("Test");
        assertEquals(cour, s.constructionToBuild(ptest));
        ptest.setGold(0);
        assertNull(s.constructionToBuild(ptest));
    }
    @Test
    void testEcoleDeMagie() {
        City city = new City();
        city.add(port);
        city.add(temple);
        city.add(forteresse);
        city.add(ecole);

        Hand hand = new Hand();

        Player p = new Player(0, 0, hand, city,strat);
        p.getWonders().add(ecole);
        p.setCharacter(Character.ROI);

        Draw draw = new Draw();
        draw.add(dracoport);
        draw.add(hotel);

        p.play(draw, new Player[]{p});

        assertEquals(Color.MERVEILLEUX,cour.getColor());
        assertEquals(1, p.getGold());
    }

    @Test
    void testLaboratoire() {
        City city1 = new City();
        city1.add(port);
        city1.add(temple);
        city1.add(forteresse);
        city1.add(laboratoire);

        Hand hand1 = new Hand();
        hand1.add(manufacure);

        Player p1 = new Player(0, 0, hand1, city1,strat);
        p1.getWonders().add(laboratoire);
        p1.setCharacter(Character.ROI);

        Draw draw = new Draw();
        draw.add(dracoport);
        draw.add(hotel);
        draw.add(ecole);

        p1.play(draw, new Player[]{p1});

        assertEquals(2, p1.getGold());

        City city2 = new City();
        city2.add(port);
        city2.add(laboratoire);

        Hand hand2 = new Hand();
        hand2.add(monastere);

        Player p222 = new Player(0, 0, hand2, city2,strat);
        p222.getWonders().add(laboratoire);
        p222.setCharacter(Character.VOLEUR);
        p222.setGold(0);
        p1.setGold(0);
        p222.play(draw, new Player[]{p222,p1});
        assertEquals(1,p222.getHand().size());
        assertFalse(p222.getHand().contains(monastere));
        assertEquals(1, p222.getGold());
    }

    @Test
    void testManufacture() {
        City city = new City();
        city.add(port);
        city.add(manufacure);

        Hand hand = new Hand();

        Player p = new Player(0, 3, hand, city,strat);
        p.getWonders().add(manufacure);
        p.setCharacter(Character.ROI);

        Draw draw = new Draw();
        draw.add(dracoport);
        draw.add(hotel);
        draw.add(cathedrale);
        draw.add(palais);
        draw.add(bastion);

        p.play(draw, new Player[]{p});

        assertEquals(3, p.getHand().size());
        assertEquals(2, p.getGold());
    }
    @Test
    void assassinTest() {
        p1.setCharacter(Character.ASSASSIN);
        p2.setCharacter(Character.ARCHITECTE);
        p3.setCharacter(Character.ROI);
        p4.setCharacter(Character.CONDOTTIERE);

        players2 = new Player[]{p1, p2, p3, p4};

        strat.assassin(players2, draw);

        assertTrue(p2.isDead());
        assertFalse(p1.isDead());
        assertFalse(p3.isDead());
        assertFalse(p4.isDead());
        p2.resurrect();

        p2.setCharacter(Character.MAGICIEN);
        strat.assassin(players2, draw);
        assertFalse(p1.isDead());
        assertFalse(p2.isDead());
        assertFalse(p3.isDead());
        assertFalse(p4.isDead());
    }

    @Test
    void thiefTest() {
        p1.setCharacter(Character.VOLEUR);
        p2.setCharacter(Character.ARCHITECTE);
        p3.setCharacter(Character.ROI);
        p4.setCharacter(Character.CONDOTTIERE);

        assertEquals(2, p1.getGold());
        assertEquals(1, p2.getGold());

        players2 = new Player[]{p1, p2, p3, p4};

        strat.thief(players2, draw);
        assertEquals(3, p1.getGold());
        assertEquals(2, p1.getCity().size());
        assertEquals(0, p2.getGold());

        p1.setGold(2);
        p2.setGold(2);

        Character.ASSASSIN.ability(Character.ARCHITECTE, new Player[]{p1, p2,p3});

        assertEquals(2, p1.getGold());
        assertEquals(2, p2.getGold());
        strat.thief(players2, draw);
        assertEquals(5, p1.getGold());
        assertEquals(2, p2.getGold());
    }

    @Test
    void magicianTestNoSwapHand() {
        p1.setCharacter(Character.MAGICIEN);
        p2.setCharacter(Character.ARCHITECTE);
        p3.setCharacter(Character.ROI);
        p4.setCharacter(Character.CONDOTTIERE);

        p1.getHand().add(new Construction(CardsName.TEMPLE, Color.RELIGIEUX, 1));
        p1.getHand().add(new Construction(CardsName.EGLISE, Color.RELIGIEUX, 2));
        p1.getHand().add(new Construction(CardsName.MONASTERE, Color.RELIGIEUX, 3));

        p2.getHand().add(new Construction(CardsName.TOUR_DE_GUET, Color.SOLDATESQUE, 1));
        p3.getHand().add(new Construction(CardsName.TAVERNE, Color.COMMERCIAL, 1));
        p4.getHand().add(new Construction(CardsName.COMPTOIR, Color.COMMERCIAL, 3));

        draw.add(new Construction(CardsName.PRISON, Color.SOLDATESQUE, 3));
        draw.add(new Construction(CardsName.MARCHE, Color.COMMERCIAL, 2));
        draw.add(new Construction(CardsName.PORT, Color.COMMERCIAL, 4));

        strat.magician(players2, draw);

        assertEquals(5, p1.getHand().size());
        assertEquals(1, p1.getHand().get(0).getValue());
    }

    @Test
    void magicianTestSwapWithDraw() {
        p1.setCharacter(Character.MAGICIEN);
        p2.setCharacter(Character.ARCHITECTE);
        p3.setCharacter(Character.ROI);
        p4.setCharacter(Character.CONDOTTIERE);

        p1.getHand().add(new Construction(CardsName.TEMPLE, Color.RELIGIEUX, 1));
        p1.getHand().add(new Construction(CardsName.EGLISE, Color.RELIGIEUX, 2));
        p1.getHand().add(new Construction(CardsName.MONASTERE, Color.RELIGIEUX, 3));
        p1.getHand().add(new Construction(CardsName.HOTEL_DE_VILLE, Color.COMMERCIAL, 10));

        p2.getHand().add(new Construction(CardsName.TOUR_DE_GUET, Color.SOLDATESQUE, 1));
        p3.getHand().add(new Construction(CardsName.TAVERNE, Color.COMMERCIAL, 1));
        p4.getHand().add(new Construction(CardsName.COMPTOIR, Color.COMMERCIAL, 3));

        draw.add(new Construction(CardsName.PRISON, Color.SOLDATESQUE, 3));
        draw.add(new Construction(CardsName.MARCHE, Color.COMMERCIAL, 2));
        draw.add(new Construction(CardsName.PORT, Color.COMMERCIAL, 4));
        draw.add(new Construction(CardsName.BASTION, Color.SOLDATESQUE, 5));

        strat.magician(players2, draw);

        assertEquals(6, p1.getHand().size());
        assertEquals(1, p1.getHand().get(0).getValue());
    }

    @Test
    void magicianTestSwapWithAPlayer() {
        p1.setCharacter(Character.MAGICIEN);
        p2.setCharacter(Character.ARCHITECTE);
        p3.setCharacter(Character.ROI);
        p4.setCharacter(Character.CONDOTTIERE);

        p1.getHand().add(new Construction(CardsName.ECHOPPE, Color.COMMERCIAL, 2));
        p1.getHand().add(new Construction(CardsName.EGLISE, Color.RELIGIEUX, 2));


        p2.getHand().add(new Construction(CardsName.TOUR_DE_GUET, Color.SOLDATESQUE, 1));
        p2.getHand().add(new Construction(CardsName.MONASTERE, Color.RELIGIEUX, 3));
        p2.getHand().add(new Construction(CardsName.HOTEL_DE_VILLE, Color.COMMERCIAL, 5));

        p3.getHand().add(new Construction(CardsName.TAVERNE, Color.COMMERCIAL, 1));
        p4.getHand().add(new Construction(CardsName.COMPTOIR, Color.COMMERCIAL, 3));

        draw.add(new Construction(CardsName.PRISON, Color.SOLDATESQUE, 3));
        draw.add(new Construction(CardsName.MARCHE, Color.COMMERCIAL, 2));
        draw.add(new Construction(CardsName.PORT, Color.COMMERCIAL, 4));
        draw.add(new Construction(CardsName.BASTION, Color.SOLDATESQUE, 5));

        assertEquals(4, p1.getHand().size());
        assertEquals(1, p1.getHand().get(0).getValue());

        strat.magician(players2, draw);

        assertEquals(4, p1.getHand().size());
        assertEquals(1, p1.getHand().get(0).getValue());
    }

    @Test
    void architectTest() {
        Player p1 = new Player(1,2, hand1,new City(),strat);
        p1.setCharacter(Character.ARCHITECTE);

        Draw draw = new Draw();
        draw.add(cathedrale, bastion, palais);

        p1.getStrategy().architect(new Player[]{p1}, draw);

        assertEquals(1, p1.getGold());
        assertEquals(2, p1.getHand().size());
        assertEquals(2, p1.getCity().size());
    }

    @Test
    void condottiereTest() {
        p1.setCharacter(Character.CONDOTTIERE);
        p2.setCharacter(Character.ARCHITECTE);
        p3.setCharacter(Character.ROI);
        p4.setCharacter(Character.MAGICIEN);

        p1.getHand().add(new Construction(CardsName.ECHOPPE, Color.COMMERCIAL, 2));
        p1.getHand().add(new Construction(CardsName.EGLISE, Color.RELIGIEUX, 2));


        p2.getCity().add(new Construction(CardsName.TOUR_DE_GUET, Color.SOLDATESQUE, 1));
        p2.getCity().add(new Construction(CardsName.HOTEL_DE_VILLE, Color.COMMERCIAL, 5));
        p2.getCity().add(new Construction(CardsName.MONASTERE, Color.RELIGIEUX, 3));

        p3.getCity().add(new Construction(CardsName.TAVERNE, Color.COMMERCIAL, 1));
        p4.getCity().add(new Construction(CardsName.TEMPLE, Color.RELIGIEUX, 1));

        assertEquals(2, p1.getGold());
        assertEquals(3, p2.getCity().size());
        assertEquals(1, p2.getCity().get(0).getValue());
        strat.condottiere(players2, draw);
        assertEquals(2, p1.getGold());
        assertEquals(3, p2.getCity().size());
        assertEquals(1, p2.getCity().get(0).getValue());

        p1.setGold(10);
        strat.condottiere(players2, draw);
        assertEquals(10, p1.getGold());
        assertEquals(3, p2.getCity().size());
        assertEquals(1, p2.getCity().get(0).getValue());

        assertEquals(0, p3.getCity().size());
        strat.condottiere(players2, draw);
        // p2 n'est plus celui ciblé
        assertEquals(3, p2.getCity().size());
        assertEquals(1, p2.getCity().get(0).getValue());
        // p3 est désormais ciblé
        assertEquals(0, p3.getCity().size());
        assertEquals(10, p1.getGold());


        Hand h5 = new Hand();
        City c5 = new City();
        c5.add(temple);
        c5.add(donjon);
        c5.add(palais);
        c5.add(chateau);
        c5.add(taverne);
        c5.add(cathedrale);
        c5.add(monastere);
        c5.add(prison);

        Player p5 = new Player(5, 0, h5, c5, strat);
        p5.setCharacter(Character.EVEQUE);
        p1.setGold(0);
        p1.play(draw, new Player[]{p1, p5});
        assertEquals(2, p1.getCity().size());
        assertEquals(8, p5.getCity().size());
    }

    @Test
    void playWithBiggestHandIndexTest() {
        p1.setCharacter(Character.MAGICIEN);
        p2.setCharacter(Character.ARCHITECTE);
        p3.setCharacter(Character.ROI);
        p4.setCharacter(Character.CONDOTTIERE);

        p1.getHand().add(new Construction(CardsName.ECHOPPE, Color.COMMERCIAL, 2));
        p1.getHand().add(new Construction(CardsName.EGLISE, Color.RELIGIEUX, 2));


        p2.getHand().add(new Construction(CardsName.TOUR_DE_GUET, Color.SOLDATESQUE, 1));
        p2.getHand().add(new Construction(CardsName.MONASTERE, Color.RELIGIEUX, 3));
        p2.getHand().add(new Construction(CardsName.HOTEL_DE_VILLE, Color.COMMERCIAL, 5));

        p3.getHand().add(new Construction(CardsName.TAVERNE, Color.COMMERCIAL, 1));
        p4.getHand().add(new Construction(CardsName.COMPTOIR, Color.COMMERCIAL, 3));

        assertEquals(2, strat.playerWithBiggestHandIndex(players2));

        p1.getHand().add(new Construction(CardsName.PRISON, Color.SOLDATESQUE, 3));
        p1.getHand().add(new Construction(CardsName.MARCHE, Color.COMMERCIAL, 2));

        assertEquals(2, strat.playerWithBiggestHandIndex(players2));
    }

    @Test
    void minCostInCityTest() {
        p1.buildConstruction(new Construction(CardsName.ECHOPPE, Color.COMMERCIAL, 2));
        p1.buildConstruction(new Construction(CardsName.EGLISE, Color.RELIGIEUX, 2));
        p1.buildConstruction(new Construction(CardsName.COMPTOIR, Color.COMMERCIAL, 3));

        assertEquals(2, strat.minCostInCity(p1.getCity()));

        p1.buildConstruction(new Construction(CardsName.TAVERNE, Color.COMMERCIAL, 1));

        assertEquals(1, strat.minCostInCity(p1.getCity()));
    }

    @Test
    void minCostInCityIndexTest() {
        p1.buildConstruction(new Construction(CardsName.ECHOPPE, Color.COMMERCIAL, 2));
        p1.buildConstruction(new Construction(CardsName.EGLISE, Color.RELIGIEUX, 2));
        p1.buildConstruction(new Construction(CardsName.COMPTOIR, Color.COMMERCIAL, 3));

        assertEquals(1, strat.minCostInCityIndex(p1.getCity()));

        p1.buildConstruction(new Construction(CardsName.TAVERNE, Color.COMMERCIAL, 1));

        assertEquals(4, strat.minCostInCityIndex(p1.getCity()));
    }
    @Test
    public void testBishop() {
        Player[] players = {p1,p2};
        Draw draw = new Draw();
        p1.buildConstruction(cour);
        p1.setCharacter(Character.EVEQUE);
        p2.setCharacter(Character.CONDOTTIERE);
        p2.setGold(99);
        strat.bishop(players, draw);
        p2.getCharacter().ability(cour,p2,p1);
        assertFalse(p1.getCity().getCity().isEmpty());
    }

    @Test
    public void testMerchant() {

        Player p1 = new Player(1,2,new Hand(),new City(), strat);
        Player[] players = {p1};
        p1.setGold(2);
        p1.setCharacter(Character.MARCHAND);
        Draw draw = new Draw();
        draw.add(universite);
        draw.add(dracoport);
        p1.buildConstruction(echoppe);
        strat.merchant(players, draw);
        assertEquals(1, p1.getCity().size());
        assertEquals(2,p1.getGold());
    }

    Player player = new Player(1,2, hand1,new City(),strat);
    Player player2 = new Player(2,2, hand1,new City(),strat);
    Player[] players3 = new Player[]{player, player2};
    List<Character> characters = new ArrayList<>(List.of(Character.values()));
}