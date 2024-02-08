package fr.cotedazur.univ.polytech.startingpoint.strategies;

import fr.cotedazur.univ.polytech.startingpoint.Draw;
import fr.cotedazur.univ.polytech.startingpoint.Player;
import fr.cotedazur.univ.polytech.startingpoint.cards.*;
import fr.cotedazur.univ.polytech.startingpoint.cards.Character;
import fr.cotedazur.univ.polytech.startingpoint.players.City;
import fr.cotedazur.univ.polytech.startingpoint.players.Hand;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Strategy1 extends Strategy{

    public Strategy1(String description) {
        super(description);
    }

    @Override
    public Character chooseCharacter(Player player,List<Character> characters, Player[] players){
        Character character = super.chooseCharacter(player, characters, players);
        if (character != null) return character;
        List<Character> characterPriority = getCharacterPriority(players);
        for (Character c : characterPriority){
            if (characters.contains(c)) return c;
        }
        return characters.get(0); // normalement on ne devrait jamais arriver ici
    }

    public List<Character> getCharacterPriority(Player[] players){
        List<Character> characterPriority = new ArrayList<>();
        Hand hand = players[0].getHand();
        characterPriority.add(Character.ARCHITECTE);
        characterPriority.add(Character.ROI);
        if (!hand.isEmpty() && averageCostInHand(hand, hand.size()) > 4) characterPriority.add(Character.MAGICIEN);
        // en attende de la méthode qui arrangera les persos avec une couleur;
        characterPriority.add(Character.CONDOTTIERE);
        characterPriority.add(Character.EVEQUE);
        characterPriority.add(Character.MARCHAND);
        characterPriority.add(Character.ASSASSIN);
        if (!characterPriority.contains(Character.VOLEUR)) characterPriority.add(Character.VOLEUR);
        if (!characterPriority.contains(Character.MAGICIEN)) characterPriority.add(Character.MAGICIEN);
        return characterPriority;
    }

    @Override
    public Construction chooseCard(List<Construction> constructions, Player player) {
        Construction c = new Construction("null", Color.NEUTRE, 10);

        for (Construction construction : constructions) {
            if (construction.getValue() < c.getValue() && !player.getHand().contains(construction)
                    && !player.getCity().getCity().contains(construction)) c = construction;
            if (construction.getValue() == c.getValue() && construction.getColor() == Color.MERVEILLEUX) c = construction;
        }

        if (c.getName().equals("null")) c = constructions.get(0);
        constructions.remove(c);
        return c;
    }

    public Construction constructionToBuild(Player player) {

        if (player.getHand().getHand().isEmpty()) return null;
        if (player.getHand().minNotInCity(player) != null && player.getHand().minNotInCity(player).getValue() <= player.getGold()) return player.getHand().minNotInCity(player);
        else return null;
    }

    public void play(Player[] players, Draw draw) {
        for (Wonder wonder : players[0].getWonders()) {
            switch (wonder.getName()) {
                case "Laboratoire":
                    capacityLaboratoire(players, draw);
                    break;
                case "Manufacture":
                    capacityManufacture(players, draw);
                    break;
                case "Ecole de magie":
                    capacityEcoleDeMagie(players);
                    break;
                default:
                    break;
            }
        }
        super.play(players, draw);
        for (Wonder wonder : players[0].getWonders()) {
            if (wonder.getName().equals("Ecole de magie")) wonder.setColor(Color.MERVEILLEUX);
        }
    }

    public void playDefault(Player[] players, Draw draw) {
        players[0].pick(draw, goldOrCard(players));
        players[0].buildConstruction(constructionToBuild(players[0]));
    }

    // Ajouter une méthode qui gère le début de tour : firstChoice(String s) s pouvant être "gold" pour prendre de l'or ou "pick" pour piocher.
    // Elle sera utilisée dans les méthodes de caractères.
    public int goldOrCard(Player[] players) {
        if (players[0].getHand().isEmpty()) {
            for (Wonder w : players[0].getWonders()) {
                if (w.getName().equals("Observatoire") || w.getName().equals("Bibliothèque")) {
                    return -players[0].getWonders().indexOf(w);
                }
            }
            return 2;
        }
        else return 1;
    }

    // Le joueur cible l'architecte en tant qu'assassin
    public void assassin(Player[] players, Draw draw) {
        playDefault(players, draw);
        Character.ASSASSIN.ability(Character.ARCHITECTE, players);
    }

    // Le joueur cible l'architecte en tant que voleur
    public void thief(Player[] players, Draw draw) {
        playDefault(players, draw);
        if (!Character.ARCHITECTE.isDead(players)) Character.VOLEUR.ability(Character.ARCHITECTE, players);
        else Character.VOLEUR.ability(Character.ROI, players);
    }

    // Le joueur échange sa main avec un joueur ayant plus de carte que lui sinon la pioche si sa main est trop vide et/ou trop coûteuse
    public void magician(Player[] players, Draw draw) {
        int handSize = players[0].getHand().size();
        int maxHandIndex = playerWithBiggestHandIndex(players);
        if (handSize == 0) {
            if (maxHandIndex != 0) Character.MAGICIEN.ability(draw, players[0], players[maxHandIndex]);
        }
        else {
            double averageCost = averageCostInHand(players[0].getHand(), handSize);
            if (handSize <= 2 || averageCost >= 3) {
                if (maxHandIndex != 0) Character.MAGICIEN.ability(draw, players[0], players[maxHandIndex]);
                else Character.MAGICIEN.ability(draw, players[0]);
            }
        }
        playDefault(players, draw);
    }

    public int playerWithBiggestHandIndex(Player[] players) {
        int maxHand = 0;
        int size = players.length;
        for (int i = 1; i < size; i++) {
            if (players[maxHand].getHand().size().compareTo(players[i].getHand().size()) < 0) maxHand = i;
        }
        return maxHand;
    }

    public void king(Player[] players, Draw draw) {
        playDefault(players, draw);
        Character.ROI.ability(players[0]);
    }
    public void bishop(Player[] players, Draw draw) {
        playDefault(players, draw);
        Character.EVEQUE.ability(players[0]);
    }
    public void merchant(Player[] players, Draw draw) {
        playDefault(players, draw);
        Character.MARCHAND.ability(players[0]);
    }
    public void architect(Player[] players, Draw draw) {
        Character.ARCHITECTE.ability(draw, players[0]);
        playDefault(players, draw);
        players[0].buildConstruction(constructionToBuild(players[0]));
        players[0].buildConstruction(constructionToBuild(players[0]));
    }

    public void condottiere(Player[] players, Draw draw) {
        playDefault(players, draw);
        int citySize;
        int biggestCityIndex = 0;
        int biggestCitySize = 0;
        for (int i = 1; i < players.length; i++) {
            citySize = players[i].getCity().size();
            if (citySize < 8 && citySize != 0) {
                if ((citySize > biggestCitySize) || (citySize == biggestCitySize && minCostInCity(players[i].getCity()) < minCostInCity(players[biggestCityIndex].getCity()))) {
                    biggestCityIndex = i;
                    biggestCitySize = citySize;
                }
            }
        }
        if (biggestCitySize == 0) Character.CONDOTTIERE.ability(null, players[0], null); // On récupère juste l'or
        else {
            int consToDestructIndex = minCostInCityIndex(players[biggestCityIndex].getCity());
            if ( consToDestructIndex != -1 && players[0].getGold()-1 >= players[biggestCityIndex].getCity().get(consToDestructIndex).getValue()) {
                WondersPower.CIMETIERE.power(players[biggestCityIndex].getCity().get(consToDestructIndex), players);
                Character.CONDOTTIERE.ability(players[biggestCityIndex].getCity().get(consToDestructIndex), players[0], players[biggestCityIndex]);
            }
        }
    }

    public int minCostInCity(City city) {
        int minCost = Integer.MAX_VALUE;
        for (Construction c : city.getCity()) {
            if (c.getValue() < minCost && !Objects.equals(c.getName(), "Donjon")) minCost = c.getValue();
        }
        return minCost;
    }

    public int minCostInCityIndex(City city) {
        int minCost = Integer.MAX_VALUE;
        int index = -1;
        for (int i = 0; i < city.size(); i++) {
            int cityValue = city.get(i).getValue();
            if (cityValue < minCost && !Objects.equals(city.get(i).getName(), "Donjon")) {
                index = i;
                minCost = cityValue;
            }
        }
        return index;
    }

    public void capacityLaboratoire(Player[] players, Draw draw) {
        Construction max = players[0].getHand().max();
        if (max != null && max.getValue() >= 4) {
            WondersPower.LABORATOIRE.power(max, players[0], draw);
        }
    }

    public void capacityManufacture(Player[] players, Draw draw) {
        if (players[0].getGold() >= 3 && players[0].getHand().isEmpty()) WondersPower.MANUFACTURE.power(players[0], draw);
    }

    public void capacityEcoleDeMagie(Player[] players) {
        if (players[0].getCharacter().getColor() != Color.NEUTRE) WondersPower.ECOLE_DE_MAGIE.power(players[0], players[0].getCharacter().getColor());
    }
}