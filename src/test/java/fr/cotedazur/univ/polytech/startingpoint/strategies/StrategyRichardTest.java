package fr.cotedazur.univ.polytech.startingpoint.strategies;


import fr.cotedazur.univ.polytech.startingpoint.Draw;
import fr.cotedazur.univ.polytech.startingpoint.MyLogger;
import fr.cotedazur.univ.polytech.startingpoint.Player;
import fr.cotedazur.univ.polytech.startingpoint.cards.Color;
import fr.cotedazur.univ.polytech.startingpoint.cards.Constructions;
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

class StrategyRichardTest {

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
        StrategyRichard s = new StrategyRichard("Test");
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
        StrategyRichard s = new StrategyRichard("Test");
        Player p = new Player(0, 2, new Hand(), new City(), s);
        Draw d = new Draw();

        d.add(tour);
        d.add(bastion);
        d.add(ecole);
        d.add(echoppe);

        assertEquals(2, s.goldOrCard(new Player[]{p}, d));

        p.getWonders().add(observatoire);
        assertEquals(0, s.goldOrCard(new Player[]{p}, d));

        p.getWonders().add(bibliotheque);
        assertEquals(0, s.goldOrCard(new Player[]{p}, d));

        p.getHand().add(manoir);
        assertEquals(1, s.goldOrCard(new Player[]{p}, d));
    }

    StrategyRichard strat;
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
        strat = new StrategyRichard("rush");
        h1 = new Hand();
        p1 = new Player(1, h1);
        p1.setStrategy(strat);
        h2 = new Hand();
        p2 = new Player(2, h2);
        p2.setStrategy(strat);
        h3 = new Hand();
        p3 = new Player(3, h3);
        p3.setStrategy(strat);
        h4 = new Hand();
        p4 = new Player(4, h4);
        p4.setStrategy(strat);
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
    void assassinTestRichard() {
        Strategy strategyRichard = new StrategyRichard("Richard");
        Player player10 = new Player(1,2,new Hand(), new City(), strategyRichard);
        Player player20 = new Player(2,2,new Hand(), new City(), strategyRichard);
        Player player30 = new Player(3,2,new Hand(), new City(), strategyRichard);
        Player player40 = new Player(4,2,new Hand(), new City(), strategyRichard);
        player10.setCharacter(Character.ASSASSIN);
        player20.setCharacter(Character.ARCHITECTE);
        player30.setCharacter(Character.VOLEUR);
        player40.setCharacter(Character.CONDOTTIERE);
        Player[] playersList = new Player[]{player10, player20, player30, player40};
        Draw draw = new Draw();
        draw.add(temple);
        draw.add(eglise);
        draw.add(marche);
        draw.add(prison);
        draw.add(tour);
        draw.add(forteresse);
        draw.add(prison);
        draw.add(bastion);
        strategyRichard.assassin(playersList, draw);
        assertFalse(player10.isDead());
        assertTrue(player20.isDead());
        assertFalse(player30.isDead());
        assertFalse(player40.isDead());
        player20.resurrect();
        player20.addGold(10);
        strategyRichard.assassin(playersList, draw);
        assertFalse(player10.isDead());
        assertFalse(player20.isDead());
        assertTrue(player30.isDead());
        assertFalse(player40.isDead());
        player30.resurrect();
        player10.getCity().add(temple); // p1 points = 3
        player10.getCity().add(eglise);
        player20.getCity().add(marche); // p2 points = 5
        player20.getCity().add(prison);
        player30.getCity().add(tour); // p3 points = 3
        player30.getCity().add(forteresse);
        strategyRichard.assassin(playersList, draw);
        assertFalse(player10.isDead());
        assertFalse(player20.isDead());
        assertTrue(player30.isDead());
        assertFalse(player40.isDead());
        player30.resurrect();
        player10.getCity().add(prison); // p1 points = 6
        player10.getCity().add(bastion); // p1 points = 11
        strategyRichard.assassin(playersList, draw);
        assertFalse(player10.isDead());
        assertFalse(player20.isDead());
        assertFalse(player30.isDead());
        assertTrue(player40.isDead());
        player40.resurrect();
        player40.getCity().add(prison);
        player40.getCity().add(forteresse);
        player40.getCity().add(bastion);
        strategyRichard.assassin(playersList, draw);
        assertFalse(player10.isDead());
        assertFalse(player20.isDead());
        assertFalse(player30.isDead());
        assertTrue(player40.isDead());
        player40.resurrect();

        player20.setCharacter(Character.ROI);
        player20.getCity().add(manoir);
        player20.getCity().add(palais);
        player20.getCity().add(prison);
        player20.getCity().add(bastion);
        strategyRichard.assassin(playersList, draw);
        assertFalse(player10.isDead());
        assertTrue(player20.isDead());
        assertFalse(player30.isDead());
        assertFalse(player40.isDead());

    }

    @Test
    void thiefTest() {
        p1.setCharacter(Character.VOLEUR);
        p2.setCharacter(Character.ARCHITECTE);
        p3.setCharacter(Character.ROI);
        p4.setCharacter(Character.CONDOTTIERE);

        p3.getCity().add(bastion);
        p3.getCity().add(bastion);
        p3.getCity().add(bastion);
        p3.getCity().add(bastion);
        p3.getCity().add(bastion);
        p3.getCity().add(bastion);
        p1.setGold(0);
        p1.getStrategy().thief(new Player[]{p1,p2,p3,p4}, draw);

        assertEquals(2, p1.getGold());
        assertEquals(0, p3.getGold());
    }

    @Test
    void magicianTestNoSwapHand() {
        p1.setCharacter(Character.MAGICIEN);
        p2.setCharacter(Character.ARCHITECTE);
        p3.setCharacter(Character.ROI);
        p4.setCharacter(Character.CONDOTTIERE);

        p1.getHand().add(new Constructions("Temple", Color.RELIGIEUX, 1));
        p1.getHand().add(new Constructions("Eglise", Color.RELIGIEUX, 2));
        p1.getHand().add(new Constructions("Monastère", Color.RELIGIEUX, 3));

        p2.getHand().add(new Constructions("Tour de guet", Color.SOLDATESQUE, 1));
        p3.getHand().add(new Constructions("Taverne", Color.COMMERCIAL, 1));
        p4.getHand().add(new Constructions("Comptoir", Color.COMMERCIAL, 3));

        draw.add(new Constructions("Prison", Color.SOLDATESQUE, 3));
        draw.add(new Constructions("Marché", Color.COMMERCIAL, 2));
        draw.add(new Constructions("Port", Color.COMMERCIAL, 4));

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

        p1.getHand().add(new Constructions("Temple", Color.RELIGIEUX, 1));
        p1.getHand().add(new Constructions("Eglise", Color.RELIGIEUX, 2));
        p1.getHand().add(new Constructions("Monastère", Color.RELIGIEUX, 3));
        p1.getHand().add(new Constructions("Hôtel de ville", Color.COMMERCIAL, 10));

        p2.getHand().add(new Constructions("Tour de guet", Color.SOLDATESQUE, 1));
        p3.getHand().add(new Constructions("Taverne", Color.COMMERCIAL, 1));
        p4.getHand().add(new Constructions("Comptoir", Color.COMMERCIAL, 3));

        draw.add(new Constructions("Prison", Color.SOLDATESQUE, 3));
        draw.add(new Constructions("Marché", Color.COMMERCIAL, 2));
        draw.add(new Constructions("Port", Color.COMMERCIAL, 4));
        draw.add(new Constructions("Bastion", Color.SOLDATESQUE, 5));

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

        p1.getHand().add(new Constructions("Echoppe", Color.COMMERCIAL, 2));
        p1.getHand().add(new Constructions("Eglise", Color.RELIGIEUX, 2));


        p2.getHand().add(new Constructions("Tour de guet", Color.SOLDATESQUE, 1));
        p2.getHand().add(new Constructions("Monastère", Color.RELIGIEUX, 3));
        p2.getHand().add(new Constructions("Hôtel de ville", Color.COMMERCIAL, 5));

        p3.getHand().add(new Constructions("Taverne", Color.COMMERCIAL, 1));
        p4.getHand().add(new Constructions("Comptoir", Color.COMMERCIAL, 3));

        draw.add(new Constructions("Prison", Color.SOLDATESQUE, 3));
        draw.add(new Constructions("Marché", Color.COMMERCIAL, 2));
        draw.add(new Constructions("Port", Color.COMMERCIAL, 4));
        draw.add(new Constructions("Bastion", Color.SOLDATESQUE, 5));

        assertEquals(2, p1.getHand().size());
        assertEquals(2, p1.getHand().get(0).getValue());

        strat.magician(players2, draw);

        assertEquals(2, p1.getHand().size());
        assertEquals(3, p1.getHand().get(0).getValue());
    }

    @Test
    void magicianTestSwapWithAPlayerSpecialKing() {
        p1.setCharacter(Character.MAGICIEN);
        p2.setCharacter(Character.ARCHITECTE);
        p3.setCharacter(Character.ROI);
        p4.setCharacter(Character.CONDOTTIERE);

        p3.getCity().add(marche);
        p3.getCity().add(marche);
        p3.getCity().add(marche);
        p3.getCity().add(marche);
        p3.getCity().add(marche);
        p3.getCity().add(marche);

        p1.setGold(0);
        p3.setGold(0);

        p1.getHand().add(chateau);
        p1.getHand().add(monastere);
        p1.getHand().add(manoir);

        Player[] players = new Player[]{p1, p2, p3, p4};

        p1.getStrategy().magician(players, draw);

        assertEquals(1, p1.getHand().size());
        assertEquals(3,p3.getHand().size());
    }

    @Test
    void condottiereTest() {
        p1.setCharacter(Character.CONDOTTIERE);
        p2.setCharacter(Character.ARCHITECTE);
        p3.setCharacter(Character.ROI);
        p4.setCharacter(Character.MAGICIEN);

        p1.getHand().add(new Constructions("Echoppe", Color.COMMERCIAL, 2));
        p1.getHand().add(new Constructions("Eglise", Color.RELIGIEUX, 2));


        p2.getCity().add(new Constructions("Tour de guet", Color.SOLDATESQUE, 1));
        p2.getCity().add(new Constructions("Hôtel de ville", Color.COMMERCIAL, 5));
        p2.getCity().add(new Constructions("Monastère", Color.RELIGIEUX, 3));

        p3.getCity().add(new Constructions("Taverne", Color.COMMERCIAL, 1));
        p4.getCity().add(new Constructions("Temple", Color.RELIGIEUX, 1));

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

    }

    @Test
    void playWithBiggestHandIndexTest() {
        p1.setCharacter(Character.MAGICIEN);
        p2.setCharacter(Character.ARCHITECTE);
        p3.setCharacter(Character.ROI);
        p4.setCharacter(Character.CONDOTTIERE);

        p1.getHand().add(new Constructions("Echoppe", Color.COMMERCIAL, 2));
        p1.getHand().add(new Constructions("Eglise", Color.RELIGIEUX, 2));


        p2.getHand().add(new Constructions("Tour de guet", Color.SOLDATESQUE, 1));
        p2.getHand().add(new Constructions("Monastère", Color.RELIGIEUX, 3));
        p2.getHand().add(new Constructions("Hôtel de ville", Color.COMMERCIAL, 5));

        p3.getHand().add(new Constructions("Taverne", Color.COMMERCIAL, 1));
        p4.getHand().add(new Constructions("Comptoir", Color.COMMERCIAL, 3));

        assertEquals(1, strat.playerWithBiggestHandIndex(players2));

        p1.getHand().add(new Constructions("Prison", Color.SOLDATESQUE, 3));
        p1.getHand().add(new Constructions("Marché", Color.COMMERCIAL, 2));

        assertEquals(0, strat.playerWithBiggestHandIndex(players2));
    }

    @Test
    void minCostInCityTest() {
        p1.buildConstruction(new Constructions("Echoppe", Color.COMMERCIAL, 2));
        p1.buildConstruction(new Constructions("Eglise", Color.RELIGIEUX, 2));
        p1.buildConstruction(new Constructions("Comptoir", Color.COMMERCIAL, 3));

        assertEquals(2, strat.minCostInCity(p1.getCity()));

        p1.buildConstruction(new Constructions("Taverne", Color.COMMERCIAL, 1));

        assertEquals(1, strat.minCostInCity(p1.getCity()));
    }

    @Test
    void minCostInCityIndexTest() {
        p1.buildConstruction(new Constructions("Echoppe", Color.COMMERCIAL, 2));
        p1.buildConstruction(new Constructions("Eglise", Color.RELIGIEUX, 2));
        p1.buildConstruction(new Constructions("Comptoir", Color.COMMERCIAL, 3));

        assertEquals(0, strat.minCostInCityIndex(p1.getCity()));

        p1.buildConstruction(new Constructions("Taverne", Color.COMMERCIAL, 1));

        assertEquals(3, strat.minCostInCityIndex(p1.getCity()));
    }

    Player player = new Player(1,10, new Hand());
    Player player2 = new Player(2, new Hand());
    Player[] players3 = new Player[]{player, player2};
    List<Character> characters = new ArrayList<>(List.of(Character.values()));


    @Test
    void testGetCharacterPriority() {
        StrategyRichard StrategyRichard = new StrategyRichard("Agressif");
        List<Character> characterPriority = StrategyRichard.getCharacterPriority(players3);
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
    void testGetCharacterPriorityRichard(){
        StrategyRichard strategyRichard = new StrategyRichard("Richard");
        Player player1 = new Player(1,2,new Hand(), new City(), strategyRichard);
        Player player2 = new Player(2,2,new Hand(), new City(), strategyRichard);
        Player player3 = new Player(3,2,new Hand(), new City(), strategyRichard);
        Player player4 = new Player(4,2,new Hand(), new City(), strategyRichard);
        player2.getCity().add(temple);
        player2.getCity().add(eglise);
        player2.getCity().add(marche);
        player2.getCity().add(prison);
        player2.getCity().add(tour);
        player2.getCity().add(forteresse);
        Player[] players = new Player[]{player1, player2, player3, player4};
        Player[] players2 = new Player[]{player2, player3, player1, player4};
        List<Character> listCharacters = new ArrayList<>(List.of(Character.values()));
        assertEquals(Character.ROI, strategyRichard.getCharacterPriorityRichard(players, listCharacters).get(0));
        assertEquals(Character.ASSASSIN, strategyRichard.getCharacterPriorityRichard(players, listCharacters).get(1));
        assertEquals(Character.CONDOTTIERE, strategyRichard.getCharacterPriorityRichard(players, listCharacters).get(2));
        assertEquals(Character.EVEQUE, strategyRichard.getCharacterPriorityRichard(players, listCharacters).get(3));
        assertEquals(8, strategyRichard.getCharacterPriorityRichard(players2, listCharacters).size());
        player2.setGold(5);
        assertEquals(Character.ROI, strategyRichard.getCharacterPriorityRichard(players, listCharacters).get(0));
        assertEquals(Character.ASSASSIN, strategyRichard.getCharacterPriorityRichard(players, listCharacters).get(1));
        assertEquals(Character.CONDOTTIERE, strategyRichard.getCharacterPriorityRichard(players, listCharacters).get(2));
        assertEquals(Character.EVEQUE, strategyRichard.getCharacterPriorityRichard(players, listCharacters).get(3));
        assertEquals(8, strategyRichard.getCharacterPriorityRichard(players2, listCharacters).size());
        player2.getHand().add(echoppe);
        assertEquals(Character.ASSASSIN, strategyRichard.getCharacterPriorityRichard(players, listCharacters).get(0));
        assertEquals(Character.ARCHITECTE, strategyRichard.getCharacterPriorityRichard(players, listCharacters).get(1));
        assertEquals(8, strategyRichard.getCharacterPriorityRichard(players2, listCharacters).size());
        assertEquals(Character.ARCHITECTE, strategyRichard.getCharacterPriorityRichard(players2, listCharacters).get(0));
        assertEquals(8, strategyRichard.getCharacterPriorityRichard(players2, listCharacters).size());
        player2.getCity().add(prison);
        assertEquals(Character.ASSASSIN, strategyRichard.getCharacterPriorityRichard(players2, listCharacters).get(0));
        assertEquals(Character.CONDOTTIERE, strategyRichard.getCharacterPriorityRichard(players, listCharacters).get(0));
        characters.remove(Character.EVEQUE);
        assertEquals(Character.ASSASSIN, strategyRichard.getCharacterPriorityRichard(players, characters).get(0));
        characters.add(Character.EVEQUE);
        characters.remove(Character.CONDOTTIERE);
        assertEquals(Character.ASSASSIN, strategyRichard.getCharacterPriorityRichard(players, characters).get(0));
        characters.add(Character.CONDOTTIERE);
        characters.remove(Character.ASSASSIN);
        assertEquals(Character.CONDOTTIERE, strategyRichard.getCharacterPriorityRichard(players, characters).get(0));
    }

    @Test
    void testChooseCharacterRichard(){
        StrategyRichard strategyRichard = new StrategyRichard("Richard");
        Player player1 = new Player(1,2,new Hand(), new City(), strategyRichard);
        Player player2 = new Player(2,2,new Hand(), new City(), strategyRichard);
        Player player3 = new Player(3,2,new Hand(), new City(), strategyRichard);
        Player player4 = new Player(4,2,new Hand(), new City(), strategyRichard);
        player2.getCity().add(temple);
        player2.getCity().add(eglise);
        player2.getCity().add(marche);
        player2.getCity().add(prison);
        player2.getCity().add(tour);
        player2.getCity().add(forteresse);
        player2.getHand().add(echoppe);
        player2.setGold(4);
        Player[] players = new Player[]{player2, player1, player3, player4};
        Player[] players2 = new Player[]{player1, player2, player3, player4};

        characters = new ArrayList<>(List.of(Character.values()));
        player2.chooseCharacter(characters, players);
        assertEquals(Character.ARCHITECTE, player2.getCharacter());
        player2.getCity().add(chateau);
        player1.chooseCharacter(characters, players2);
        player3.chooseCharacter(characters, players2);
        assertEquals(Character.CONDOTTIERE, player1.getCharacter());
        assertEquals(Character.ASSASSIN, player3.getCharacter());

        characters = new ArrayList<>(List.of(Character.values()));
        characters.remove(Character.EVEQUE);
        player1.chooseCharacter(characters, players2);
        player3.chooseCharacter(characters, players2);
        assertEquals(Character.ASSASSIN, player1.getCharacter());
        assertEquals(Character.CONDOTTIERE, player3.getCharacter());

        characters = new ArrayList<>(List.of(Character.values()));
        characters.remove(Character.CONDOTTIERE);
        player1.chooseCharacter(characters, players2);
        player3.chooseCharacter(characters, players2);
        assertEquals(Character.ASSASSIN, player1.getCharacter());
        assertEquals(Character.MAGICIEN, player3.getCharacter());

        characters = new ArrayList<>(List.of(Character.values()));
        characters.remove(Character.ASSASSIN);
        player1.chooseCharacter(characters, players2);
        player3.chooseCharacter(characters, players2);
        assertEquals(Character.CONDOTTIERE, player1.getCharacter());
        assertEquals(Character.MAGICIEN, player3.getCharacter());
    }

}
