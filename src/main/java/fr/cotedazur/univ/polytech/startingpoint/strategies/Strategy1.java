package fr.cotedazur.univ.polytech.startingpoint.strategies;

import fr.cotedazur.univ.polytech.startingpoint.Draw;
import fr.cotedazur.univ.polytech.startingpoint.Player;
import fr.cotedazur.univ.polytech.startingpoint.cards.Character;
import fr.cotedazur.univ.polytech.startingpoint.cards.Color;
import fr.cotedazur.univ.polytech.startingpoint.cards.Constructions;
import fr.cotedazur.univ.polytech.startingpoint.cards.Wonder;
import fr.cotedazur.univ.polytech.startingpoint.players.Hand;

import java.util.ArrayList;
import java.util.List;

public class Strategy1 extends Strategy{

    public Strategy1(String description) {
        super(description);
    }

    public Character choiceOfCharacter(Player player, List<Character> characters){
        return null;
    }

    public void useWonder(List<Wonder> wonders) {return;}

    public Constructions chooseCard(ArrayList<Constructions> constructions) {
        Constructions c = new Constructions("null", Color.MERVEILLEUX, 10);

        for (Constructions construction : constructions)
            if (construction.getValue() <= c.getValue()) c = construction;

        constructions.remove(c);
        return c;
    }

    public Constructions constructionToBuild(Hand hand, int gold) {
        if (hand.min().getValue() <= gold) return hand.min();
        else return null;
    }

    public void play(Player[] players, Draw draw) {
        super.play(players, draw);
        if (players[0].getHand().isEmpty()) {
            players[0].drawConstruction(draw, 2);
            for (Wonder w : players[0].getWonders()) {
                if (w.getName().equals("Observatoire") || w.getName().equals("Bibliothèque")) players[0].useWonder(draw, w.getWondersPower());
            }
        }
        else players[0].takeGold();
        for (Wonder w : players[0].getWonders()) {
            if (w.getName().equals("Laboratoire") || w.getName().equals("Manufacture") || w.getName().equals("Ecole de magie")) players[0].useWonder(draw, w.getWondersPower());
        }
        players[0].buildConstruction(constructionToBuild(players[0].getHand(), players[0].getGold()));
        players[0].useAbility(draw, players);
    }

    public void assassin(Player[] players, Draw draw) {return;}
    public void thief(Player[] players, Draw draw) {return;}
    public void magician(Player[] players, Draw draw) {return;}
    public void king(Player[] players, Draw draw) {return;}
    public void bishop(Player[] players, Draw draw) {return;}
    public void merchant(Player[] players, Draw draw) {return;}
    public void architect(Player[] players, Draw draw) {return;}
    public void condottiere(Player[] players, Draw draw) {return;}
}
