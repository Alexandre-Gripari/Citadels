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

class Strategy1Test {

    Constructions cathédrale = new Constructions("Cathédrale", Color.RELIGIEUX, 5);
    Constructions chateau = new Constructions("Château", Color.NOBLE, 4);
    Constructions monastère = new Constructions("Monastère", Color.RELIGIEUX, 3);
    Constructions marché = new Constructions("Marché", Color.COMMERCIAL, 2);
    Constructions comptoir = new Constructions("Comptoir", Color.COMMERCIAL, 3);
    Constructions temple = new Constructions("Temple", Color.RELIGIEUX, 1);
    Player player = new Player(1,10, new Hand());
    Player player2 = new Player(2, new Hand());
    Player[] players = new Player[]{player, player2};
    List<Character> characters = new ArrayList<>(List.of(Character.values()));

    @Test
    void testChooseCharacter() {
        Strategy1 strategy1 = new Strategy1("Agressif");
        assertEquals(Character.MAGICIEN, strategy1.chooseCharacter(player, characters, players));
        player.getHand().add(cathédrale);
        player.getHand().add(chateau);
        player.getHand().add(monastère);
        assertEquals(Character.ARCHITECTE, strategy1.chooseCharacter(player, characters, players));
        characters.remove(Character.ARCHITECTE);
        assertEquals(Character.ROI, strategy1.chooseCharacter(player, characters, players));

    }

    @Test
    void testGetCharacterPriority() {
        Strategy1 strategy1 = new Strategy1("Agressif");
        List<Character> characterPriority = strategy1.getCharacterPriority(players);
        assertEquals(Character.ARCHITECTE, characterPriority.get(0));
        assertEquals(Character.ROI, characterPriority.get(1));
        assertEquals(Character.CONDOTTIERE, characterPriority.get(2));
        assertEquals(Character.EVEQUE, characterPriority.get(3));
        assertEquals(Character.MARCHAND, characterPriority.get(4));
        assertEquals(Character.ASSASSIN, characterPriority.get(5));
        assertEquals(Character.VOLEUR, characterPriority.get(6));
        assertEquals(Character.MAGICIEN, characterPriority.get(7));





    }

}