package fr.cotedazur.univ.polytech.startingpoint.strategies;

import fr.cotedazur.univ.polytech.startingpoint.Draw;
import fr.cotedazur.univ.polytech.startingpoint.Player;
import fr.cotedazur.univ.polytech.startingpoint.cards.Character;
import fr.cotedazur.univ.polytech.startingpoint.cards.Constructions;
import fr.cotedazur.univ.polytech.startingpoint.cards.Wonder;
import fr.cotedazur.univ.polytech.startingpoint.players.City;
import fr.cotedazur.univ.polytech.startingpoint.players.Hand;

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

    // Ajouter une méthode qui gère le début de tour : firstChoice(String s) s pouvant être "gold" pour prendre de l'or ou "pick" pour piocher.
    // Elle sera utilisée dans les méthodes de caractères.

    // Le joueur cible l'architecte en tant qu'assassin
    public void assassin(Player[] players, Draw draw) {
        int size = players.length;
        for (int i = 1; i < size; i++) {
            if (players[i].getCharacter().equals(Character.ARCHITECTE)) {
                Character.ASSASSIN.ability(players[0], players[i]);
                break;
            }
        }
    }

    // Le joueur cible l'architecte en tant que voleur
    public void thief(Player[] players, Draw draw) {
        int size = players.length;
        for (int i = 1; i < size; i++) {
            if (players[i].getCharacter().equals(Character.ARCHITECTE)) {
                Character.VOLEUR.ability(players[i]);
                break;
            }
        }
    }

    // Le joueur échange sa main avec un joueur ayant plus de carte que lui sinon la pioche si sa main est trop vide et/ou trop coûteuse
    public void magician(Player[] players, Draw draw) {
        int handSize = players[0].getHand().size();
        int averageCost = 0;
        // Calcul du coût moyen de la main
        for (Constructions c : players[0].getHand().getHand()) averageCost += c.getValue();
        averageCost/=handSize;
        if (handSize <= 2 || averageCost >= 3) {
            int size = players.length;
            int maxHandIndex = 0;
            for (int i = 1; i < size; i++) {
                if (players[maxHandIndex].getHand().size().compareTo(players[i].getHand().size()) < 0)
                    maxHandIndex = i;
            }
            if (maxHandIndex == 0) Character.MAGICIEN.ability(draw, players[0]);
            else Character.MAGICIEN.ability(draw, players[0], players[maxHandIndex]);
        }
    }

    public void king(Player[] players, Draw draw) {
        Character.ROI.ability(players[0]);
    }
    public void bishop(Player[] players, Draw draw) {
        Character.EVEQUE.ability(players[0]);
    }
    public void merchant(Player[] players, Draw draw) {
        Character.MARCHAND.ability(players[0]);
    }
    public void architect(Player[] players, Draw draw) { Character.ARCHITECTE.ability(draw, players[0]); }

    public void condottiere(Player[] players, Draw draw) {
        int biggestCityIndex = 1;
        int biggestCitySize = players[1].getCity().getCity().size();
        for (int i = 2; i < players.length; i++) {
            if (players[i].getCity().getCity().size() != 0) {
                int citySize = players[i].getCity().getCity().size();
                if ((citySize > biggestCitySize) || (citySize == biggestCitySize && minCostInCity(players[i].getCity()) < minCostInCity(players[biggestCityIndex].getCity()))) {
                    biggestCityIndex = i;
                    biggestCitySize = citySize;
                }
            }
        }
        if (biggestCitySize == 0) Character.CONDOTTIERE.ability(null, players[0], null); // On récupère juste l'or
        else {
            int consToDestructIndex = indexConsWithMinCost(players[biggestCityIndex].getCity());
            Character.CONDOTTIERE.ability(players[biggestCityIndex].getCity().get(consToDestructIndex), players[0], players[biggestCityIndex]);
        }
    }

    private int minCostInCity(City city) {
        int minCost = Integer.MAX_VALUE;
        for (Constructions c : city.getCity()) {
            if (c.getValue() < minCost) minCost = c.getValue();
        }
        return minCost;
    }

    private int indexConsWithMinCost(City city) {
        int minCost = minCostInCity(city);
        for (int i = 0; i < city.size(); i++) {
            if (city.get(i).getValue() == minCost) return i;
        }
        return -1;
    }
}
