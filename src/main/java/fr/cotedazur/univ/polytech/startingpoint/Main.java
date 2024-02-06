package fr.cotedazur.univ.polytech.startingpoint;


import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import fr.cotedazur.univ.polytech.startingpoint.players.Hand;

import java.util.logging.Level;


public class Main {
    @Parameter(names={"--2thousands"})
    boolean twoThousands;
    @Parameter(names={"--demo"})
    boolean demo;

    public static void main(String... args) {
        Main main = new Main();
        JCommander.newBuilder()
                .addObject(main)
                .build()
                .parse(args);
        main.run();
    }

    public void run() {
        Player p1 = new Player(1, new Hand());
        Player p2 = new Player(2, new Hand());
        Player p3 = new Player(3, new Hand());
        Player p4 = new Player(4, new Hand());
        Player[] players = new Player[]{p1, p2, p3, p4};
        if (twoThousands) {
            MyLogger.setLogLevel(Level.OFF);
            // que lui meme pour l'instant on a pas d'autres joueurs
            for (int i = 0; i < 1000; i++) {
                Game game = new Game(players);
                game.init();
                game.play();
                game.calculateStats();
                game.resetGame();
            }
            MyLogger.setLogLevel(Level.INFO);
            for (Player player : players) {
                MyLogger.log(Level.INFO, "Player " + player.getNumber() + " V:" + player.getNumberOfVictory() + " D:" + player.getNumberOfDefeat() + " E:" + player.getNumberOfDraw() + " SM:" + player.getAverageScore());
            }
        }
        else if(demo){
            Game game = new Game(players);
            game.init();
            game.play();
        }



    }

}
