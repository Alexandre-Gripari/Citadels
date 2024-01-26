package fr.cotedazur.univ.polytech.startingpoint.strategies;

import fr.cotedazur.univ.polytech.startingpoint.Draw;
import fr.cotedazur.univ.polytech.startingpoint.Player;
import fr.cotedazur.univ.polytech.startingpoint.cards.*;
import fr.cotedazur.univ.polytech.startingpoint.cards.Character;
import fr.cotedazur.univ.polytech.startingpoint.players.City;
import fr.cotedazur.univ.polytech.startingpoint.players.Hand;

import java.util.ArrayList;
import java.util.List;

public class Strategy1 extends Strategy{

    @Override
    public void useAbility(Draw draw, Player[] players){return;}
    public Strategy1(String description) {
        super(description);
    }

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




    public void useWonder(List<Wonder> wonders) {return;}

    @Override
    public Constructions chooseCard(ArrayList<Constructions> constructions, Player player) {
        Constructions c = new Constructions("null", Color.NEUTRE, 10);

        for (Constructions construction : constructions) {
            if (construction.getValue() < c.getValue() && !player.getHand().contains(construction)
                    && !player.getCity().getCity().contains(construction)) c = construction;
            if (construction.getValue() == c.getValue() && construction.getColor() == Color.MERVEILLEUX) c = construction;
        }

        if (c.getName() == "null") c = constructions.get(0);
        constructions.remove(c);
        return c;
    }

    public Constructions constructionToBuild(Hand hand, int gold) {
        if (hand.min().getValue() <= gold) return hand.min();
        else return null;
    }

    public void play(Player[] players, Draw draw) {
        super.play(players, draw);
        players[0].pick(draw, goldOrCard(players, draw));
        for (Wonder w : players[0].getWonders()) {
            if (w.getName().equals("Laboratoire") || w.getName().equals("Manufacture") || w.getName().equals("Ecole de magie")) useWonder(players[0].getWonders());
        }
        players[0].buildConstruction(constructionToBuild(players[0].getHand(), players[0].getGold()));
        useAbility(draw, players);
    }

    // Ajouter une méthode qui gère le début de tour : firstChoice(String s) s pouvant être "gold" pour prendre de l'or ou "pick" pour piocher.
    // Elle sera utilisée dans les méthodes de caractères.
    public int goldOrCard(Player[] players, Draw draw) {
        if (players[0].getHand().isEmpty()) {
            for (Wonder w : players[0].getWonders()) {
                if (w.getName().equals("Observatoire"))
                    return 3;
                if (w.getName().equals("Bibliothèque")) {
                    w.power(players[0], draw);
                    return -1;
                }
            }
            return 2;
        }
        else return 0;
    }

    // Le joueur cible l'architecte en tant qu'assassin
    public void assassin(Player[] players, Draw draw) {
        int size = players.length;
        for (int i = 1; i < size; i++) {
            if (players[i].getCharacter().equals(Character.ARCHITECTE)) {
                Character.ASSASSIN.ability(players[i]);
                break;
            }
        }
    }

    // Le joueur cible l'architecte en tant que voleur
    public void thief(Player[] players, Draw draw) {
        int size = players.length;
        for (int i = 1; i < size; i++) {
            if (players[i].getCharacter().equals(Character.ARCHITECTE) && !players[i].isDead()) {
                Character.VOLEUR.ability(players[0], players[i]);
                break;
            }
        }
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
        int biggestCitySize = players[1].getCity().size();
        for (int i = 2; i < players.length; i++) {
            int citySize = players[i].getCity().size();
            if (citySize != 0) {
                if ((citySize > biggestCitySize) || (citySize == biggestCitySize && minCostInCity(players[i].getCity()) < minCostInCity(players[biggestCityIndex].getCity()))) {
                    biggestCityIndex = i;
                    biggestCitySize = citySize;
                }
            }
        }
        if (biggestCitySize == 0) Character.CONDOTTIERE.ability(null, players[0], null); // On récupère juste l'or
        else {
            int consToDestructIndex = minCostInCityIndex(players[biggestCityIndex].getCity());
            if (players[0].getGold()-1 >= players[biggestCityIndex].getCity().get(consToDestructIndex).getValue())
                Character.CONDOTTIERE.ability(players[biggestCityIndex].getCity().get(consToDestructIndex), players[0], players[biggestCityIndex]);
        }
    }

    public int minCostInCity(City city) {
        int minCost = Integer.MAX_VALUE;
        for (Constructions c : city.getCity()) {
            if (c.getValue() < minCost) minCost = c.getValue();
        }
        return minCost;
    }

    public int minCostInCityIndex(City city) {
        int minCost = Integer.MAX_VALUE;
        int index = -1;
        for (int i = 0; i < city.size(); i++) {
            int cityValue = city.get(i).getValue();
            if (cityValue < minCost) {
                index = i;
                minCost = cityValue;
            }
        }
        return index;
    }
}