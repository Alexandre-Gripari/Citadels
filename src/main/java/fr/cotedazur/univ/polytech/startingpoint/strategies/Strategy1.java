package fr.cotedazur.univ.polytech.startingpoint.strategies;

import fr.cotedazur.univ.polytech.startingpoint.Draw;
import fr.cotedazur.univ.polytech.startingpoint.Player;
import fr.cotedazur.univ.polytech.startingpoint.cards.Character;
import fr.cotedazur.univ.polytech.startingpoint.cards.Wonder;

import java.util.List;

public class Strategy1 extends Strategy{

    public Strategy1(String description) {
        super(description);
    }

    public Character choiceOfCharacter(Player player, List<Character> characters){
        return null;
    }

    public void useWonder(List<Wonder> wonders) {return;}

    public void play(Player[] players, Draw draw) {
        super.play(players, draw);
    }

    public void assassin(Player[] players, Draw draw) {return;}
    public void thief(Player[] players, Draw draw) {return;}
    public void magician(Player[] players, Draw draw) {return;}
    public void king(Player[] players, Draw draw) {return;}
    public void bishop(Player[] players, Draw draw) {return;}
    public void merchant(Player[] players, Draw draw) {return;}
    public void architect(Player[] players, Draw draw) {return;}
    public void condottiere(Player[] players, Draw draw) {return;}
}
