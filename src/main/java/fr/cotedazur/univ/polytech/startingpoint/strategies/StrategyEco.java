package fr.cotedazur.univ.polytech.startingpoint.strategies;

import fr.cotedazur.univ.polytech.startingpoint.Draw;
import fr.cotedazur.univ.polytech.startingpoint.Player;
import fr.cotedazur.univ.polytech.startingpoint.cards.*;
import fr.cotedazur.univ.polytech.startingpoint.cards.Character;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*Le bot qui préfère économiser son or pour poser la carte au coût le plus élevé de sa main*/
public class StrategyEco extends Strategy{
    @Override
    public void useAbility(Draw draw, Player[] players) {

    }

    public StrategyEco(String description) {
        super(description);
    }

    @Override
    public List<Character> getCharacterPriority(Player[] players) {
        List<Character> characters = new ArrayList<>();
        characters.add(Character.VOLEUR);
        characters.addAll(mostProfitableCharacters(players[0]));
        characters.addAll(List.of(Character.ARCHITECTE, Character.ASSASSIN, Character.MAGICIEN));
        return  characters;
    }

    public Character chooseCharacter(Player player, List<Character> characters, Player[] players){
        List<Character> characterList = new ArrayList<>();
        characterList=getCharacterPriority(players);
        for (Character c : characterList) {
            if (characters.contains(c)) {
                return c;
            }
        }
        return Character.ROI;
    }

    public void useWonder(List<Wonder> wonders) {return;}

    @Override
    public Constructions chooseCard(List<Constructions> constructions) {
        return null;
    }

    @Override
    public void play(Player[] players, Draw draw) { super.play(players, draw);}

    public void assassin(Player[] players, Draw draw) {return;}
    public void thief(Player[] players, Draw draw) {return;}
    public void magician(Player[] players, Draw draw) {return;}
    public void king(Player[] players, Draw draw) {return;}
    public void bishop(Player[] players, Draw draw) {return;}
    public void merchant(Player[] players, Draw draw) {
    }
    public void architect(Player[] players, Draw draw) {return;}
    public void condottiere(Player[] players, Draw draw) {return;}
}

