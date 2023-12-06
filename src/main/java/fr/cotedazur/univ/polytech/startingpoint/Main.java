package fr.cotedazur.univ.polytech.startingpoint;


import fr.cotedazur.univ.polytech.startingpoint.Game;
import fr.cotedazur.univ.polytech.startingpoint.Player;
import fr.cotedazur.univ.polytech.startingpoint.players.Hand;

public class Main {


    public static void main(String... args) {
        Game game = new Game(new Player[]{new Player(1, new Hand()), new Player(2, new Hand())});
        game.init();
        for (Player player : game.getPlayers()) {
            for (int i = 0; i < 4; i++)
                player.getHand().add(game.getDraw().draw());
        }

        game.play();
    }

}
