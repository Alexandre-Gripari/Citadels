package fr.cotedazur.univ.polytech.startingpoint.strategies;

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

    public void assassin(Player[] players) {return;}
    public void thief(Player[] players) {return;}
    public void magician(Player[] players) {return;}
    public void king(Player[] players) {return;}
    public void bishop(Player[] players) {return;}
    public void merchant(Player[] players) {return;}
    public void architect(Player[] players) {return;}
    public void condottiere(Player[] players) {return;}
}
