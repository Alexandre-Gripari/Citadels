package fr.cotedazur.univ.polytech.startingpoint.strategies;

import fr.cotedazur.univ.polytech.startingpoint.Draw;
import fr.cotedazur.univ.polytech.startingpoint.Player;
import fr.cotedazur.univ.polytech.startingpoint.cards.CardsName;
import fr.cotedazur.univ.polytech.startingpoint.cards.Character;
import fr.cotedazur.univ.polytech.startingpoint.cards.Construction;
import fr.cotedazur.univ.polytech.startingpoint.players.City;
import fr.cotedazur.univ.polytech.startingpoint.players.Hand;
import java.util.*;

public abstract class Strategy {

    private String description;

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
        // rajouter une condition pour l'or moyen des joueurs
        if (player.getGold() < 2 && characters.contains(Character.VOLEUR)) {
            return Character.VOLEUR;
        }
        Player[] playersCopy = players.clone();
        Arrays.sort(playersCopy);
        if (playersCopy[playersCopy.length-1].equals(player)
                && (player.getCity().cityValue() - playersCopy[players.length-2].getCity().cityValue() >8)
                && characters.contains(Character.ASSASSIN)) {
            return Character.ASSASSIN;
        }
        return null;
    }

    public abstract void playDefault(Player[] players, Draw draw);

    public abstract Construction constructionToBuild(Player player);

    public abstract List<Character> getCharacterPriority(Player[] players);
    public abstract Construction chooseCard(List<Construction> constructions, Player player);
    public void play(Player[] players, Draw draw) {
        switch (players[0].getCharacter()) {
            case ASSASSIN:
                assassin(players, draw);
                break;
            case VOLEUR:
                thief(players, draw);
                break;
            case MAGICIEN:
                magician(players, draw);
                break;
            case ROI:
                king(players, draw);
                break;
            case EVEQUE:
                bishop(players, draw);
                break;
            case MARCHAND:
                merchant(players, draw);
                break;
            case ARCHITECTE:
                architect(players, draw);
                break;
            case CONDOTTIERE:
                condottiere(players, draw);
                break;
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
        for (Construction c : hand.getHand()) avCost += c.getValue();
        avCost/=handSize;
        return avCost;
    }

    public List<Character> mostProfitableCharacters(Player p) {
        int commercialValue = 1;
        int nobleValue = 0;
        int religieuxValue = 0;
        int soldatesqueValue = 0;
        for (int i = 0; i < p.getCity().size(); i++) {
            switch (p.getCity().get(i).getColor()) {
                case COMMERCIAL:
                    commercialValue += 1;
                    break;
                case NOBLE:
                    nobleValue += 1;
                    break;
                case RELIGIEUX:
                    religieuxValue += 1;
                    break;
                case SOLDATESQUE:
                    soldatesqueValue += 1;
                    break;
                default:
                    break;
            }
        }
        int[] cityColorsValue={nobleValue,religieuxValue,soldatesqueValue,commercialValue};
        List<Character> charactersRank = new ArrayList<>();
        for (int j = 0; j < cityColorsValue.length; j++) {
            OptionalInt maxValue = Arrays.stream(cityColorsValue).max();
            int max = maxValue.getAsInt();
            if (max == nobleValue && nobleValue!=0) {
                charactersRank.add(Character.ROI);
                cityColorsValue[0]=-1;
            }
            if (max == religieuxValue && religieuxValue!=0) {
                charactersRank.add(Character.EVEQUE);
                cityColorsValue[1]=-1;
            }
            if (max == soldatesqueValue && soldatesqueValue!=0) {
                charactersRank.add(Character.CONDOTTIERE);
                cityColorsValue[2]=-1;
            }
            if (max == commercialValue && commercialValue!=0) {
                charactersRank.add(Character.MARCHAND);
                cityColorsValue[3] = -1;
            }
        }
        return charactersRank;
    }

    public boolean isMaybeLastTurn(Player[] players){
        for (Player p : players) {
            if (p.getCity().size() >= 7) return true;
        }
        return false;

    }

    public boolean isWinning(Player[] players){
        Player[] playersCopy = players.clone();
        Arrays.sort(playersCopy);
        return playersCopy[playersCopy.length-1].equals(players[0]);
    }

    public boolean gotCitySize(int size, Player[] players){
        for (Player p : players) {
            if (p.getCity().size().equals(size)) return true;
        }
        return false;
    }

    public Player canArchiRush(Player[] players){
        for (Player p : players) {
            if (p.getCity().size() >= 5 && p.getGold()>=4 && !p.getHand().isEmpty()) return p;
        }
        return null;
    }

    public int maxCitySizeExcept8(Player[] players) {
        int size = 0;
        for (Player player : players) {
            if (player.getCity().size() >= size && player.getCity().size() != 8 && player != players[0]) size = player.getCity().size();
        }
        return size;
    }

    public int minCostInCity(City city) {
        int minCost = Integer.MAX_VALUE;
        for (Construction c : city.getCity()) {
            if (c.getValue() < minCost && !Objects.equals(c.getName(), CardsName.DONJON)) minCost = c.getValue();
        }
        return minCost;
    }

    public int minCostInCityIndex(City city) {
        int minCost = Integer.MAX_VALUE;
        int index = -1;
        for (int i = 0; i < city.size(); i++) {
            int cityValue = city.get(i).getValue();
            if (cityValue < minCost && !Objects.equals(city.get(i).getName(), CardsName.DONJON)) {
                index = i;
                minCost = cityValue;
            }
        }
        return index;
    }
}


