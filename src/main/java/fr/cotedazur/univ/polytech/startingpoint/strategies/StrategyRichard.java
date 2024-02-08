package fr.cotedazur.univ.polytech.startingpoint.strategies;

import fr.cotedazur.univ.polytech.startingpoint.Draw;
import fr.cotedazur.univ.polytech.startingpoint.Player;
import fr.cotedazur.univ.polytech.startingpoint.cards.Character;
import fr.cotedazur.univ.polytech.startingpoint.cards.Color;
import fr.cotedazur.univ.polytech.startingpoint.cards.Construction;
import fr.cotedazur.univ.polytech.startingpoint.players.Hand;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class StrategyRichard extends Strategy1{
    private List<Character> characters = new ArrayList<>();

    public void setCharacters(List<Character> characters) {
        this.characters = characters;
    }

    public StrategyRichard(String description) {
        super(description);
    }

    @Override
    public void playDefault(Player[] players, Draw draw) {
        super.playDefault(players, draw);
    }

    @Override
    public Construction constructionToBuild(Player player) {
        return super.constructionToBuild(player);
    }

    @Override
    public List<Character> getCharacterPriority(Player[] players) {
        return super.getCharacterPriority(players);
    }

    public List<Character> getCharacterPriorityRichard(Player[] players, List<Character> characters){
        List<Character> characterPriority = new ArrayList<>();
        if (isMaybeLastTurn(players)){ // dernier tour
            if (isWinning(players)){
                characterPriority.add(Character.ASSASSIN); // le cas c'est moi la cible
            }
            else if (new HashSet<>(characters).containsAll(Arrays.asList(Character.ASSASSIN, Character.EVEQUE, Character.CONDOTTIERE))){
                characterPriority.add(Character.CONDOTTIERE); // 1er cas
                characterPriority.add(Character.ASSASSIN);
            }
            else if (!characters.contains(Character.EVEQUE)) { // 2ème cas
                characterPriority.add(Character.ASSASSIN);
                characterPriority.add(Character.CONDOTTIERE);
            }
            else if (!characters.contains(Character.CONDOTTIERE)) { //3ème cas
                characterPriority.add(Character.ASSASSIN);
                characterPriority.add(Character.MAGICIEN);
            }
            else if (!characters.contains(Character.ASSASSIN)) { //4ème cas
                characterPriority.add(Character.CONDOTTIERE);
                characterPriority.add(Character.EVEQUE);
            }
        }
        else if (canArchiRush(players) != null){
            if (canArchiRush(players).equals(players[0])) {
                characterPriority.add(Character.ARCHITECTE);
            }
            else {
                characterPriority.add(Character.ASSASSIN);
                characterPriority.add(Character.ARCHITECTE);
            }
        }
        else if (gotCitySize(6, players)){
                characterPriority.add(Character.ROI);
                characterPriority.add(Character.ASSASSIN);
                characterPriority.add(Character.CONDOTTIERE);
                characterPriority.add(Character.EVEQUE);
        }
        List<Character> otherPriority = getCharacterPriority(players);
        for (Character c : otherPriority){
            if (!characterPriority.contains(c)) characterPriority.add(c);
        }
        return characterPriority;

    }

    @Override
    public Character chooseCharacter(Player player, List<Character> characters, Player[] players){
        this.characters = characters;
        List<Character> characterPriorityRichard = getCharacterPriorityRichard(players, characters);
        for (Character c : characterPriorityRichard){
            if (characters.contains(c)) return c;
        }
        return characters.get(0); // normalement on ne devrait jamais arriver ici
    }

    @Override
    public Construction chooseCard(List<Construction> constructions, Player player) {
        return super.chooseCard(constructions, player);
    }

    @Override
    public void assassin(Player[] players, Draw draw) {
        Player self = players[0];
        Player[] playersCopy = players.clone();
        Arrays.sort(playersCopy);
        if (isMaybeLastTurn(players) && new HashSet<>(characters).containsAll(Arrays.asList(Character.ASSASSIN, Character.EVEQUE, Character.CONDOTTIERE))) {
            playDefault(players, draw);
            Character.ASSASSIN.ability(Character.EVEQUE, players);
            return;
        }
        if (isMaybeLastTurn(players) && !characters.contains(Character.EVEQUE)) {
            playDefault(players, draw);
            Character c = characters.get(0);
            for (Character character : characters) {
                if (c.equals(Character.CONDOTTIERE)) {
                    c = character;
                    break;
                }
            }
            Character.ASSASSIN.ability(c, players);
            return;
        }
        if (isMaybeLastTurn(players) && !characters.contains(Character.CONDOTTIERE)) {
            playDefault(players, draw);
            Character.ASSASSIN.ability(Character.MAGICIEN, players);
            return;
        }
        if (canArchiRush(players) != players[0] && canArchiRush(players) != null) {
            playDefault(players, draw);
            Character.ASSASSIN.ability(Character.ARCHITECTE, players);
            return;
        }
        if (super.maxCitySizeExcept8(players) == 6) {
            playDefault(players, draw);
            Character.ASSASSIN.ability(Character.ROI, players);
            return;
        }
        if (playersCopy[playersCopy.length-1].equals(self)
                && (self.getCity().cityValue() - playersCopy[players.length-2].getCity().cityValue() >4)
               ){
            playDefault(players, draw);
            Character.ASSASSIN.ability(Character.CONDOTTIERE, players);
            return;
        }
        for (Player p : players) {
            if (p.getGold()>=6 && !p.equals(self)) {
                playDefault(players, draw);
                Character.ASSASSIN.ability(Character.VOLEUR, players);
                return;
            }
            if (p.getCity().getCity().size() == 7 && !p.equals(self) && p.getCity().cityValue() >= playersCopy[playersCopy.length-1].getCity().cityValue()-2
                    && p.getCity().getNumberOfColor(Color.SOLDATESQUE)>2) {
                playDefault(players, draw);
                Character.ASSASSIN.ability(Character.CONDOTTIERE, players);
                return;
            }
        }
        super.assassin(players, draw);
    }

    @Override
    public void thief(Player[] players, Draw draw) {
        if (super.maxCitySizeExcept8(players) >= 6) {
            playDefault(players, draw);
            Character.VOLEUR.ability(Character.ROI, players);
            return;
        }
        super.thief(players, draw);
    }

    @Override
    public void magician(Player[] players, Draw draw) {
        Player p0 = new Player(0, new Hand());

        if (isMaybeLastTurn(players) && !characters.contains(Character.CONDOTTIERE) && players[0].getHand().size() <= 3) {
            Player[] playersCopy = players.clone();
            Arrays.sort(playersCopy);
            Character.MAGICIEN.ability(draw, players[0], playersCopy[players.length - 1]);
            playDefault(players, draw);
            return;
        }

        for (Player player : players) {
            if (player.getCity().size() >= 6) p0 = player;
        }

        if (players[0].getHand().min() != null && super.maxCitySizeExcept8(players) >= 6 &&
                players[0].getHand().min().getValue() > p0.getGold()+2) {
            Character.MAGICIEN.ability(draw, players[0], p0);
            playDefault(players, draw);
            return;
        }

        super.magician(players, draw);
    }
}
