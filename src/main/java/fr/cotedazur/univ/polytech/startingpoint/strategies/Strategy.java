package fr.cotedazur.univ.polytech.startingpoint.strategies;

import fr.cotedazur.univ.polytech.startingpoint.Player;
import fr.cotedazur.univ.polytech.startingpoint.cards.Character;
import fr.cotedazur.univ.polytech.startingpoint.cards.Constructions;
import fr.cotedazur.univ.polytech.startingpoint.cards.Wonder;
import fr.cotedazur.univ.polytech.startingpoint.cards.WondersPower;

import java.util.List;

public abstract class Strategy {

    private String description;

    public Strategy(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    abstract Character choiceOfCharacter(Player player, List<Character> characters);

    abstract void useWonder(List<Wonder> wonders);

    public void play(Player[] players) {
        switch (players[0].getCharacter()){
            case ASSASSIN : assassin(players); break;
            case VOLEUR : thief(players); break;
            case MAGICIEN : magician(players); break;
            case ROI : king(players); break;
            case EVEQUE : bishop(players); break;
            case MARCHAND : merchant(players); break;
            case ARCHITECTE : architect(players); break;
            case CONDOTTIERE: condottiere(players); break;
        }
    }

    abstract void assassin(Player[] players);
    abstract void thief(Player[] players);
    abstract void magician(Player[] players);
    abstract void king(Player[] players);
    abstract void bishop(Player[] players);
    abstract void merchant(Player[] players);
    abstract void architect(Player[] players);
    abstract void condottiere(Player[] players);

}
