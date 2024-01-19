package fr.cotedazur.univ.polytech.startingpoint.strategies;

import fr.cotedazur.univ.polytech.startingpoint.Draw;
import fr.cotedazur.univ.polytech.startingpoint.Player;
import fr.cotedazur.univ.polytech.startingpoint.cards.Character;
import fr.cotedazur.univ.polytech.startingpoint.cards.Constructions;
import fr.cotedazur.univ.polytech.startingpoint.cards.Wonder;
import fr.cotedazur.univ.polytech.startingpoint.players.Hand;

import java.util.Arrays;
import java.util.List;

public abstract class Strategy {

    private String description;

    public abstract void useAbility(Draw draw, Player[] players);
    public Strategy(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public Character chooseCharacter(Player player, List<Character> characters, Player[] players){
        if (player.getHand().isEmpty() && characters.contains(Character.MAGICIEN)){
            return Character.MAGICIEN;
        }
        if (player.getGold() < 2 && characters.contains(Character.VOLEUR)) {
            return Character.VOLEUR;
        }
        Player[] playersCopy = players.clone();
        Arrays.sort(playersCopy);
        if (playersCopy[playersCopy.length-1].equals(player) && characters.contains(Character.ASSASSIN)) {
            return Character.ASSASSIN;
        }
        return null;
    }

    public abstract List<Character> getCharacterPriority(Player[] players);
    abstract void useWonder(List<Wonder> wonders);
    public abstract Constructions chooseCard(List<Constructions> constructions);

    public void play(Player[] players, Draw draw) {
        switch (players[0].getCharacter()){
            case ASSASSIN : assassin(players, draw); break;
            case VOLEUR : thief(players, draw); break;
            case MAGICIEN : magician(players, draw); break;
            case ROI : king(players, draw); break;
            case EVEQUE : bishop(players, draw); break;
            case MARCHAND : merchant(players, draw); break;
            case ARCHITECTE : architect(players, draw); break;
            case CONDOTTIERE: condottiere(players, draw); break;
        }
    }

    abstract void assassin(Player[] players, Draw draw);
    abstract void thief(Player[] players, Draw draw);
    abstract void magician(Player[] players, Draw draw);
    abstract void king(Player[] players, Draw draw);
    abstract void bishop(Player[] players, Draw draw);
    abstract void merchant(Player[] players, Draw draw);
    abstract void architect(Player[] players, Draw draw);
    abstract void condottiere(Player[] players, Draw draw);


    protected double averageCostInHand(Hand hand, int handSize) {
        double avCost = 0;
        for (Constructions c : hand.getHand()) avCost += c.getValue();
        avCost/=handSize;
        return avCost;
    }

}


