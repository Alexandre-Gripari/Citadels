package fr.cotedazur.univ.polytech.startingpoint.strategies;

import fr.cotedazur.univ.polytech.startingpoint.Draw;
import fr.cotedazur.univ.polytech.startingpoint.Player;
import fr.cotedazur.univ.polytech.startingpoint.cards.Character;
import fr.cotedazur.univ.polytech.startingpoint.cards.Constructions;
import fr.cotedazur.univ.polytech.startingpoint.cards.Wonder;

import java.util.ArrayList;
import java.util.List;


/*Le bot qui préfère économiser son or pour poser la carte au coût le plus élevé de sa main*/
public class StrategyEco extends Strategy{
    @Override
    public void useAbility(Draw draw, Player[] players) {

    }

    public StrategyEco(String description) {
        super(description);
    }

    @Override
    public List<Character> getCharacterPriority(Player[] players) {
        return null;
    }

    public Character chooseCharacter(Player player, List<Character> characters, Player[] players){
        int max=0;
        super.chooseCharacter(player, characters, players);
        // ne pas le mettre dans cette fonction
        for (Constructions c: player.getCity().getCity() ) {
            max = Math.max(max, c.getValue());
        }
        if(player.getGold()<max-2) {
            List<Character> characterList = new ArrayList<>(List.of(Character.VOLEUR));
        }
        return null;
    }

    public void useWonder(List<Wonder> wonders) {return;}

    @Override
    public Constructions chooseCard(List<Constructions> Constructions, Player player) {
        return null;
    }

    @Override
    public void play(Player[] players, Draw draw) { super.play(players, draw);}

    public void assassin(Player[] players, Draw draw) {return;}
    public void thief(Player[] players, Draw draw) {return;}
    public void magician(Player[] players, Draw draw) {return;}
    public void king(Player[] players, Draw draw) {return;}
    public void bishop(Player[] players, Draw draw) {return;}
    public void merchant(Player[] players, Draw draw) {
    }
    public void architect(Player[] players, Draw draw) {return;}
    public void condottiere(Player[] players, Draw draw) {return;}
}



