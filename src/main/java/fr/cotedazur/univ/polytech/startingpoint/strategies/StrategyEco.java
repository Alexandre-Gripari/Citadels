package fr.cotedazur.univ.polytech.startingpoint.strategies;

import fr.cotedazur.univ.polytech.startingpoint.Draw;
import fr.cotedazur.univ.polytech.startingpoint.Player;
import fr.cotedazur.univ.polytech.startingpoint.cards.*;
import fr.cotedazur.univ.polytech.startingpoint.cards.Character;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*Le bot qui préfère économiser son or pour poser la carte au coût le plus élevé de sa main*/
public class StrategyEco extends Strategy{
    public StrategyEco(String description) {
        super(description);
    }

    public void choiceOfCharacter(Player player, List<Character> characters){
        int max=0;
        for (Constructions c: player.getCity()) {
            max = Math.max(max, c.getValue());
        }
        if(player.getGold()<max-2) {
            List<Character> characterList = {Character.VOLEUR};
        }
    }
}

    public void useWonder(List<Wonder> wonders) {return;}

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

}


