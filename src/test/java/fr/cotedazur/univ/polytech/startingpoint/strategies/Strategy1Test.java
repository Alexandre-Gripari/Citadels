package fr.cotedazur.univ.polytech.startingpoint.strategies;


import fr.cotedazur.univ.polytech.startingpoint.Draw;
import fr.cotedazur.univ.polytech.startingpoint.MyLogger;
import fr.cotedazur.univ.polytech.startingpoint.Player;
import fr.cotedazur.univ.polytech.startingpoint.cards.Color;
import fr.cotedazur.univ.polytech.startingpoint.cards.Construction;
import fr.cotedazur.univ.polytech.startingpoint.cards.Wonder;
import fr.cotedazur.univ.polytech.startingpoint.cards.WondersPower;
import fr.cotedazur.univ.polytech.startingpoint.players.City;
import fr.cotedazur.univ.polytech.startingpoint.players.Hand;
import fr.cotedazur.univ.polytech.startingpoint.cards.Character;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;

import static org.junit.jupiter.api.Assertions.*;

class Strategy1Test {

    Construction temple = new Construction("Temple", Color.RELIGIEUX, 1);
    Construction eglise = new Construction("Eglise", Color.RELIGIEUX, 2);
    Construction monastere = new Construction("Monastère", Color.RELIGIEUX, 3);
    Construction cathedrale = new Construction("Cathédrale", Color.RELIGIEUX, 5);

    Construction manoir = new Construction("Manoir", Color.NOBLE, 3);
    Construction chateau = new Construction("Château", Color.NOBLE, 4);
    Construction palais = new Construction("Palais", Color.NOBLE, 5);

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

        City city = new City();

        Strategy1 s = new Strategy1("Test");

        Player player = new Player(1, 2, hand, city,s);

        assertEquals(cour, s.constructionToBuild(player));

        player.setGold(0);
        assertNull(s.constructionToBuild(player));
    }

    @Test
    void testGoldOrCard() {
        Strategy1 s = new Strategy1("Test");
        Player p = new Player(0, 2, new Hand(), new City(), s);
        Draw d = new Draw();

        d.add(tour);
        d.add(bastion);
        d.add(ecole);
        d.add(echoppe);

        assertEquals(2, s.goldOrCard(new Player[]{p}));

        p.getWonders().add(observatoire);
        assertEquals(0, s.goldOrCard(new Player[]{p}));

        p.getWonders().add(bibliotheque);
        assertEquals(0, s.goldOrCard(new Player[]{p}));

        p.getHand().add(manoir);
        assertEquals(1, s.goldOrCard(new Player[]{p}));
    }

    Strategy1 strat;
    Player p1;
    Hand h1;
    Player p2;
    Hand h2;
    Player p3;
    Hand h3;
    Player p4;
    Hand h4;
    Player[] players2;
    Draw draw;

    @BeforeEach
    void init() {
        MyLogger.setLogLevel(Level.OFF);
        strat = new Strategy1("rush");
        h1 = new Hand();
        p1 = new Player(1, h1);
        h2 = new Hand();
        p2 = new Player(2, h2);
        h3 = new Hand();
        p3 = new Player(3, h3);
        h4 = new Hand();
        p4 = new Player(4, h4);
        players2 = new Player[4];
        players2[0] = p1; players2[1] = p2; players2[2] = p3; players2[3] = p4;
        draw = new Draw();
        draw.add(chateau);
        draw.add(marche);
        draw.add(prison);
        draw.add(port);
        draw.add(echoppe);
    }

    @Test
    void assassinTest() {
        p1.setCharacter(Character.ASSASSIN);
        p2.setCharacter(Character.ARCHITECTE);
        p3.setCharacter(Character.ROI);
        p4.setCharacter(Character.CONDOTTIERE);

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
        assertEquals(2, p2.getGold());

        strat.thief(players2, draw);
        assertEquals(2, p1.getGold());
        assertEquals(1, p1.getCity().size());
        assertEquals(0, p2.getGold());

        p1.setGold(2);
        p2.setGold(2);

        Character.ASSASSIN.ability(Character.ARCHITECTE, new Player[]{p1, p2,p3});

        assertEquals(2, p1.getGold());
        assertEquals(2, p2.getGold());
        strat.thief(players2, draw);
        assertEquals(4, p1.getGold());
        assertEquals(2, p2.getGold());
    }

    @Test
    void magicianTestNoSwapHand() {
        p1.setCharacter(Character.MAGICIEN);
        p2.setCharacter(Character.ARCHITECTE);
        p3.setCharacter(Character.ROI);
        p4.setCharacter(Character.CONDOTTIERE);

        p1.getHand().add(new Construction("Temple", Color.RELIGIEUX, 1));
        p1.getHand().add(new Construction("Eglise", Color.RELIGIEUX, 2));
        p1.getHand().add(new Construction("Monastère", Color.RELIGIEUX, 3));

        p2.getHand().add(new Construction("Tour de guet", Color.SOLDATESQUE, 1));
        p3.getHand().add(new Construction("Taverne", Color.COMMERCIAL, 1));
        p4.getHand().add(new Construction("Comptoir", Color.COMMERCIAL, 3));

        draw.add(new Construction("Prison", Color.SOLDATESQUE, 3));
        draw.add(new Construction("Marché", Color.COMMERCIAL, 2));
        draw.add(new Construction("Port", Color.COMMERCIAL, 4));

        strat.magician(players2, draw);

        assertEquals(2, p1.getHand().size());
        assertEquals(2, p1.getHand().get(0).getValue());
    }

    @Test
    void magicianTestSwapWithDraw() {
        p1.setCharacter(Character.MAGICIEN);
        p2.setCharacter(Character.ARCHITECTE);
        p3.setCharacter(Character.ROI);
        p4.setCharacter(Character.CONDOTTIERE);

        p1.getHand().add(new Construction("Temple", Color.RELIGIEUX, 1));
        p1.getHand().add(new Construction("Eglise", Color.RELIGIEUX, 2));
        p1.getHand().add(new Construction("Monastère", Color.RELIGIEUX, 3));
        p1.getHand().add(new Construction("Hôtel de ville", Color.COMMERCIAL, 10));

        p2.getHand().add(new Construction("Tour de guet", Color.SOLDATESQUE, 1));
        p3.getHand().add(new Construction("Taverne", Color.COMMERCIAL, 1));
        p4.getHand().add(new Construction("Comptoir", Color.COMMERCIAL, 3));

        draw.add(new Construction("Prison", Color.SOLDATESQUE, 3));
        draw.add(new Construction("Marché", Color.COMMERCIAL, 2));
        draw.add(new Construction("Port", Color.COMMERCIAL, 4));
        draw.add(new Construction("Bastion", Color.SOLDATESQUE, 5));

        strat.magician(players2, draw);

        assertEquals(3, p1.getHand().size());
        assertEquals(4, p1.getHand().get(0).getValue());
    }

    @Test
    void magicianTestSwapWithAPlayer() {
        p1.setCharacter(Character.MAGICIEN);
        p2.setCharacter(Character.ARCHITECTE);
        p3.setCharacter(Character.ROI);
        p4.setCharacter(Character.CONDOTTIERE);

        p1.getHand().add(new Construction("Echoppe", Color.COMMERCIAL, 2));
        p1.getHand().add(new Construction("Eglise", Color.RELIGIEUX, 2));


        p2.getHand().add(new Construction("Tour de guet", Color.SOLDATESQUE, 1));
        p2.getHand().add(new Construction("Monastère", Color.RELIGIEUX, 3));
        p2.getHand().add(new Construction("Hôtel de ville", Color.COMMERCIAL, 5));

        p3.getHand().add(new Construction("Taverne", Color.COMMERCIAL, 1));
        p4.getHand().add(new Construction("Comptoir", Color.COMMERCIAL, 3));

        draw.add(new Construction("Prison", Color.SOLDATESQUE, 3));
        draw.add(new Construction("Marché", Color.COMMERCIAL, 2));
        draw.add(new Construction("Port", Color.COMMERCIAL, 4));
        draw.add(new Construction("Bastion", Color.SOLDATESQUE, 5));

        assertEquals(2, p1.getHand().size());
        assertEquals(2, p1.getHand().get(0).getValue());

        strat.magician(players2, draw);

        assertEquals(2, p1.getHand().size());
        assertEquals(3, p1.getHand().get(0).getValue());
    }

    @Test
    void architectTest() {
        Player p1 = new Player(1, new Hand());
        p1.setCharacter(Character.ARCHITECTE);

        Draw draw = new Draw();
        draw.add(cathedrale, bastion, palais);

        p1.getStrategy().architect(new Player[]{p1}, draw);

        assertEquals(4, p1.getGold());
        assertEquals(2, p1.getHand().size());
        assertEquals(0, p1.getCity().size());
    }

    @Test
    void condottiereTest() {
        p1.setCharacter(Character.CONDOTTIERE);
        p2.setCharacter(Character.ARCHITECTE);
        p3.setCharacter(Character.ROI);
        p4.setCharacter(Character.MAGICIEN);

        p1.getHand().add(new Construction("Echoppe", Color.COMMERCIAL, 2));
        p1.getHand().add(new Construction("Eglise", Color.RELIGIEUX, 2));


        p2.getCity().add(new Construction("Tour de guet", Color.SOLDATESQUE, 1));
        p2.getCity().add(new Construction("Hôtel de ville", Color.COMMERCIAL, 5));
        p2.getCity().add(new Construction("Monastère", Color.RELIGIEUX, 3));

        p3.getCity().add(new Construction("Taverne", Color.COMMERCIAL, 1));
        p4.getCity().add(new Construction("Temple", Color.RELIGIEUX, 1));

        assertEquals(2, p1.getGold());
        assertEquals(3, p2.getCity().size());
        assertEquals(1, p2.getCity().get(0).getValue());
        strat.condottiere(players2, draw);
        assertEquals(2, p1.getGold());
        assertEquals(2, p2.getCity().size());
        assertEquals(5, p2.getCity().get(0).getValue());

        p1.setGold(10);
        strat.condottiere(players2, draw);
        assertEquals(8, p1.getGold());
        assertEquals(1, p2.getCity().size());
        assertEquals(5, p2.getCity().get(0).getValue());

        assertEquals(1, p3.getCity().size());
        strat.condottiere(players2, draw);
        // p2 n'est plus celui ciblé
        assertEquals(1, p2.getCity().size());
        assertEquals(5, p2.getCity().get(0).getValue());
        // p3 est désormais ciblé
        assertEquals(0, p3.getCity().size());
        assertEquals(6, p1.getGold());


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

        Player p5 = new Player(5, 0, h5, c5);
        p5.setCharacter(Character.EVEQUE);
        p1.setGold(0);
        p1.play(draw, new Player[]{p1, p5});
        assertEquals(3, p1.getCity().size());
        assertEquals(8, p5.getCity().size());
    }

    @Test
    void playWithBiggestHandIndexTest() {
        p1.setCharacter(Character.MAGICIEN);
        p2.setCharacter(Character.ARCHITECTE);
        p3.setCharacter(Character.ROI);
        p4.setCharacter(Character.CONDOTTIERE);

        p1.getHand().add(new Construction("Echoppe", Color.COMMERCIAL, 2));
        p1.getHand().add(new Construction("Eglise", Color.RELIGIEUX, 2));


        p2.getHand().add(new Construction("Tour de guet", Color.SOLDATESQUE, 1));
        p2.getHand().add(new Construction("Monastère", Color.RELIGIEUX, 3));
        p2.getHand().add(new Construction("Hôtel de ville", Color.COMMERCIAL, 5));

        p3.getHand().add(new Construction("Taverne", Color.COMMERCIAL, 1));
        p4.getHand().add(new Construction("Comptoir", Color.COMMERCIAL, 3));

        assertEquals(1, strat.playerWithBiggestHandIndex(players2));

        p1.getHand().add(new Construction("Prison", Color.SOLDATESQUE, 3));
        p1.getHand().add(new Construction("Marché", Color.COMMERCIAL, 2));

        assertEquals(0, strat.playerWithBiggestHandIndex(players2));
    }

    @Test
    void minCostInCityTest() {
        p1.buildConstruction(new Construction("Echoppe", Color.COMMERCIAL, 2));
        p1.buildConstruction(new Construction("Eglise", Color.RELIGIEUX, 2));
        p1.buildConstruction(new Construction("Comptoir", Color.COMMERCIAL, 3));

        assertEquals(2, strat.minCostInCity(p1.getCity()));

        p1.buildConstruction(new Construction("Taverne", Color.COMMERCIAL, 1));

        assertEquals(1, strat.minCostInCity(p1.getCity()));
    }

    @Test
    void minCostInCityIndexTest() {
        p1.buildConstruction(new Construction("Echoppe", Color.COMMERCIAL, 2));
        p1.buildConstruction(new Construction("Eglise", Color.RELIGIEUX, 2));
        p1.buildConstruction(new Construction("Comptoir", Color.COMMERCIAL, 3));

        assertEquals(0, strat.minCostInCityIndex(p1.getCity()));

        p1.buildConstruction(new Construction("Taverne", Color.COMMERCIAL, 1));

        assertEquals(3, strat.minCostInCityIndex(p1.getCity()));
    }

    Player player = new Player(1,10, new Hand());
    Player player2 = new Player(2, new Hand());
    Player[] players3 = new Player[]{player, player2};
    List<Character> characters = new ArrayList<>(List.of(Character.values()));

    @Test
    void testChooseCharacter() {
        Strategy1 strategy1 = new Strategy1("Agressif");
        assertEquals(Character.MAGICIEN, strategy1.chooseCharacter(player, characters, players3));
        player.getHand().add(cathedrale);
        player.getHand().add(chateau);
        player.getHand().add(monastere);
        assertEquals(Character.ARCHITECTE, strategy1.chooseCharacter(player, characters, players3));
        characters.remove(Character.ARCHITECTE);
        assertEquals(Character.ROI, strategy1.chooseCharacter(player, characters, players3));

    }

    @Test
    void testGetCharacterPriority() {
        Strategy1 strategy1 = new Strategy1("Agressif");
        List<Character> characterPriority = strategy1.getCharacterPriority(players3);
        assertEquals(Character.ARCHITECTE, characterPriority.get(0));
        assertEquals(Character.ROI, characterPriority.get(1));
        assertEquals(Character.CONDOTTIERE, characterPriority.get(2));
        assertEquals(Character.EVEQUE, characterPriority.get(3));
        assertEquals(Character.MARCHAND, characterPriority.get(4));
        assertEquals(Character.ASSASSIN, characterPriority.get(5));
        assertEquals(Character.VOLEUR, characterPriority.get(6));
        assertEquals(Character.MAGICIEN, characterPriority.get(7));
    }

    @Test
    void testEcoleDeMagie() {
        City city = new City();
        city.add(port);
        city.add(temple);
        city.add(forteresse);
        city.add(ecole);

        Hand hand = new Hand();

        Player p = new Player(0, 0, hand, city);
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

        Player p1 = new Player(0, 0, hand1, city1);
        p1.getWonders().add(laboratoire);
        p1.setCharacter(Character.ROI);

        Draw draw = new Draw();
        draw.add(dracoport);
        draw.add(hotel);
        draw.add(ecole);

        p1.play(draw, new Player[]{p1});

        assertEquals(1, p1.getGold());

        City city2 = new City();
        city2.add(port);
        city2.add(laboratoire);

        Hand hand2 = new Hand();
        hand2.add(monastere);

        Player p222 = new Player(0, 0, hand2, city2);
        p222.getWonders().add(laboratoire);
        p222.setCharacter(Character.VOLEUR);
        p222.setGold(0);
        p1.setGold(0);
        p222.play(draw, new Player[]{p222,p1});
        assertEquals(1,p222.getHand().size());
        assertTrue(p222.getHand().contains(monastere));
        assertEquals(2, p222.getGold());
    }

    @Test
    void testManufacture() {
        City city = new City();
        city.add(port);
        city.add(manufacure);

        Hand hand = new Hand();

        Player p = new Player(0, 3, hand, city);
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

}
