package fr.cotedazur.univ.polytech.startingpoint.strategies;

import fr.cotedazur.univ.polytech.startingpoint.Player;
import fr.cotedazur.univ.polytech.startingpoint.cards.Character;
import fr.cotedazur.univ.polytech.startingpoint.cards.Color;
import fr.cotedazur.univ.polytech.startingpoint.cards.Constructions;
import fr.cotedazur.univ.polytech.startingpoint.players.Hand;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StrategyTest {

    Player p1 = new Player(1,0, new Hand());
    Player p2 = new Player(2, new Hand());

    Strategy strategy = new Strategy1("Agressif");
    Strategy strategy2 = new Strategy2("Agressif");
    Constructions cathédrale = new Constructions("Cathédrale", Color.RELIGIEUX, 5);
    Constructions chateau = new Constructions("Château", Color.NOBLE, 4);
    Constructions monastère = new Constructions("Monastère", Color.RELIGIEUX, 3);
    Constructions marché = new Constructions("Marché", Color.COMMERCIAL, 2);
    Constructions comptoir = new Constructions("Comptoir", Color.COMMERCIAL, 3);
    Constructions temple = new Constructions("Temple", Color.RELIGIEUX, 1);

    @Test
    void testAverageCostInHand() {
        Hand hand = new Hand();
        hand.add(cathédrale);
        hand.add(chateau);
        assertEquals(4.5, strategy.averageCostInHand(hand, hand.size()));
        hand.add(monastère);
        assertEquals(4, strategy.averageCostInHand(hand, hand.size()));
        hand.add(marché);
        hand.add(comptoir);
        hand.add(temple);
        assertEquals(3, strategy.averageCostInHand(hand, hand.size()));
    }

    @Test
    void testChoiceCharacter(){
        List<Character> characters = new ArrayList<>();

        characters.add(Character.VOLEUR);
        characters.add(Character.ASSASSIN);
        characters.add(Character.MAGICIEN);
        assertEquals(Character.MAGICIEN, strategy.chooseCharacter(p1, characters, new Player[]{p1, p2}));
        p1.getHand().add(cathédrale);
        assertEquals(Character.VOLEUR, strategy.chooseCharacter(p1, characters, new Player[]{p1, p2}));
        characters.remove(Character.VOLEUR);
        p1.getCity().add(cathédrale);
        p1.getCity().add(chateau);
        assertEquals(Character.ASSASSIN, strategy.chooseCharacter(p1, characters, new Player[]{p1, p2}));
        p2.getCity().add(chateau);
        p2.getCity().add(monastère);
        assertEquals(Character.MAGICIEN, strategy.chooseCharacter(p1, characters, new Player[]{p1, p2}));
        characters.add(Character.ROI);
        assertEquals(Character.ROI, strategy.chooseCharacter(p1, characters, new Player[]{p1, p2}));
        characters.add(Character.ARCHITECTE);
        assertEquals(Character.ARCHITECTE, strategy.chooseCharacter(p1, characters, new Player[]{p1, p2}));
        p1.getHand().remove(cathédrale);
        assertEquals(Character.MAGICIEN, strategy.chooseCharacter(p1, characters, new Player[]{p1, p2}));
        characters.remove(Character.MAGICIEN);
        assertEquals(Character.ARCHITECTE, strategy.chooseCharacter(p1, characters, new Player[]{p1, p2}));
    }

}