package fr.cotedazur.univ.polytech.startingpoint.strategies;

import fr.cotedazur.univ.polytech.startingpoint.Draw;
import fr.cotedazur.univ.polytech.startingpoint.Player;
import fr.cotedazur.univ.polytech.startingpoint.cards.Character;
import fr.cotedazur.univ.polytech.startingpoint.cards.Color;
import fr.cotedazur.univ.polytech.startingpoint.cards.Constructions;
import fr.cotedazur.univ.polytech.startingpoint.cards.Wonder;
import fr.cotedazur.univ.polytech.startingpoint.players.Hand;

import java.util.Arrays;
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
    public Constructions constructionToBuild(Player player) {
        return super.constructionToBuild(player);
    }

    @Override
    public List<Character> getCharacterPriority(Player[] players) {
        return super.getCharacterPriority(players);
    }

    public Character chooseCharacter(Player player, List<Character> characters, Player[] players){
        return super.chooseCharacter(player, characters, players);
    }

    public void useWonder(List<Wonder> wonders) {return;}

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
        Player self = players[0];
        Player[] playersCopy = players.clone();
        Arrays.sort(playersCopy);
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
        for (Player player : players) {
            if (player.getCity().size() >= 6) p0 = player;
        }

        if (super.maxCitySizeExcept8(players) >= 6 &&
                players[0].getHand().min().getValue() > p0.getGold()+2) {
            Character.MAGICIEN.ability(draw, players[0], p0);
            playDefault(players, draw);
            return;
        }

        super.magician(players, draw);
    }

    @Override
    public void king(Player[] players, Draw draw) {super.king(players, draw);}

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
