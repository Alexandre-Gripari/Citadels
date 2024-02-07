package fr.cotedazur.univ.polytech.startingpoint.strategies;

import fr.cotedazur.univ.polytech.startingpoint.Draw;
import fr.cotedazur.univ.polytech.startingpoint.Player;
import fr.cotedazur.univ.polytech.startingpoint.cards.Character;
import fr.cotedazur.univ.polytech.startingpoint.cards.Color;
import fr.cotedazur.univ.polytech.startingpoint.cards.Constructions;
import fr.cotedazur.univ.polytech.startingpoint.cards.Wonder;
import fr.cotedazur.univ.polytech.startingpoint.players.Hand;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class StrategyRichard extends Strategy1{

    public StrategyRichard(String description) {
        super(description);
    }

    @Override
    public void playDefault(Player[] players, Draw draw) {
        super.playDefault(players, draw);
    }

    @Override
    public Constructions constructionToBuild(Hand hand, int gold) {
        return super.constructionToBuild(hand, gold);
    }

    @Override
    public List<Character> getCharacterPriority(Player[] players) {
        return super.getCharacterPriority(players);
    }

    public List<Character> getCharacterPriorityRichard(Player[] players, List<Character> characters){
        List<Character> characterPriority = new ArrayList<>();
        if (isMaybeLastTurn(players)){
            if (isWinning(players)){
                characterPriority.add(Character.ASSASSIN);
            }
            else if (new HashSet<>(characters).containsAll(Arrays.asList(Character.ASSASSIN, Character.EVEQUE, Character.CONDOTTIERE))){
                characterPriority.add(Character.CONDOTTIERE);
                characterPriority.add(Character.ASSASSIN);
            }
            else if (characters.contains(Character.EVEQUE)) {
                characterPriority.add(Character.ASSASSIN);
                characterPriority.add(Character.CONDOTTIERE);
            }
            else if (!characters.contains(Character.CONDOTTIERE)) {
                characterPriority.add(Character.ASSASSIN);
                characterPriority.add(Character.MAGICIEN);
            }
            else if (!characters.contains(Character.ASSASSIN)) {
                characterPriority.add(Character.CONDOTTIERE);
                characterPriority.add(Character.EVEQUE);
            }
        }
        if (gotCitySize(6, players)){
                characterPriority.add(Character.ROI);
                characterPriority.add(Character.ASSASSIN);
                characterPriority.add(Character.CONDOTTIERE);
                characterPriority.add(Character.EVEQUE);
            }
        if (canArchiRush(players) != null){
            if (canArchiRush(players).equals(players[0])) {
                characterPriority.add(Character.ARCHITECTE);
            }
            else {
                characterPriority.add(Character.ASSASSIN);
                characterPriority.add(Character.ARCHITECTE);
            }
        }
        List<Character> otherPriority = getCharacterPriority(players);
        for (Character c : otherPriority){
            if (!characterPriority.contains(c)) characterPriority.add(c);
        }
        return characterPriority;

    }

    @Override
    public Character chooseCharacter(Player player, List<Character> characters, Player[] players){
        List<Character> characterPriorityRichard = getCharacterPriorityRichard(players, characters);
        for (Character c : characterPriorityRichard){
            if (characters.contains(c)) return c;
        }
        return characters.get(0); // normalement on ne devrait jamais arriver ici
    }

    @Override
    public Constructions chooseCard(List<Constructions> constructions, Player player) {
        return super.chooseCard(constructions, player);
    }

    @Override
    public void play(Player[] players, Draw draw) {
        super.play(players, draw);
    }

    @Override
    public void assassin(Player[] players, Draw draw) {
        playDefault(players, draw);
        Character toAssassinate = Character.ARCHITECTE;
        Player self = players[0];
        playDefault(players, draw);
        Player[] playersCopy = players.clone();
        Arrays.sort(playersCopy);
        if (playersCopy[playersCopy.length-1].equals(self)
                && (self.getCity().cityValue() - playersCopy[players.length-2].getCity().cityValue() >4)
               ){ Character.ASSASSIN.ability(Character.CONDOTTIERE, players);
            return;
        }
        for (Player p : players) {
            if (p.getGold()>=6 && !p.equals(self)) Character.ASSASSIN.ability(Character.VOLEUR, players);
            if (p.getCity().getCity().size() == 7 && !p.equals(self) && p.getCity().cityValue() >= playersCopy[playersCopy.length-1].getCity().cityValue()-2
                    && p.getCity().getNumberOfColor(Color.SOLDATESQUE)>2) {
                Character.ASSASSIN.ability(Character.CONDOTTIERE, players);
                return;
            }
        }
        Character.ASSASSIN.ability(toAssassinate, players);
    }

    @Override
    public void thief(Player[] players, Draw draw) {
        super.thief(players, draw);
    }

    @Override
    public void magician(Player[] players, Draw draw) {
        super.magician(players, draw);
    }

    @Override
    public void king(Player[] players, Draw draw) {
        super.king(players, draw);
    }

    @Override
    public void bishop(Player[] players, Draw draw) {
        super.bishop(players, draw);
    }

    @Override
    public void merchant(Player[] players, Draw draw) {
        super.merchant(players, draw);
    }

    @Override
    public void architect(Player[] players, Draw draw) {
        super.architect(players, draw);
    }

    @Override
    public void condottiere(Player[] players, Draw draw) {
        super.condottiere(players, draw);
    }
}
