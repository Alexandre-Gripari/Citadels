package fr.cotedazur.univ.polytech.startingpoint;


import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import fr.cotedazur.univ.polytech.startingpoint.players.City;
import fr.cotedazur.univ.polytech.startingpoint.players.Hand;

import fr.cotedazur.univ.polytech.startingpoint.strategies.Strategy1;
import fr.cotedazur.univ.polytech.startingpoint.strategies.StrategyEco;
import fr.cotedazur.univ.polytech.startingpoint.strategies.StrategyRichard;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.logging.Level;
import java.util.ArrayList;
import java.util.List;


public class Main {
    @Parameter(names = {"--2thousands"})
    boolean twoThousands = false;
    @Parameter(names = {"--demo"})
    boolean demo = false;
    @Parameter(names = {"--csv"})
    boolean csv = false;

    public static void main(String... args) throws IOException, CsvException {
        Main main = new Main();
        JCommander.newBuilder()
                .addObject(main)
                .build()
                .parse(args);
        main.run();
    }

    public void run() throws IOException, CsvException {
        Player p1 = new Player(1, 2, new Hand(), new City(), new StrategyEco("Lamiri1"));
        Player p2 = new Player(2, 2, new Hand(), new City(), new StrategyEco("Lamiri2"));
        Player p3 = new Player(3, 2, new Hand(), new City(), new StrategyEco("Lamiri3"));
        Player p4 = new Player(4, 2, new Hand(), new City(), new StrategyEco("Lamiri4"));
        Player[] players = new Player[]{p1, p2, p3, p4};
        Player p5 = new Player(5, 2, new Hand(), new City(), new StrategyRichard("Richard"));
        Player p6 = new Player(6, 2, new Hand(), new City(), new StrategyRichard("Richard2"));
        Player p7 = new Player(7, 2, new Hand(), new City(), new Strategy1("Agro"));
        Player p8 = new Player(8, 2, new Hand(), new City(), new StrategyEco("Lamiri"));
        Player[] playersRichard = new Player[]{p5, p6, p7, p8};
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
                MyLogger.log(Level.INFO, "Player " + player.getNumber() + " V:" + player.getNumberOfVictory()
                        + "(" + player.getNumberOfVictory() / 10 + "%)" + " D:" + player.getNumberOfDefeat() + "(" + player.getNumberOfDefeat() / 10 + "%)" +
                        " E:" + player.getNumberOfDraw() + "(" + player.getNumberOfDraw() / 10 + "%)" + " SM:" + player.getAverageScore());
            }
            MyLogger.setLogLevel(Level.OFF);
            for (int i = 0; i < 1000; i++) {
                Game game = new Game(playersRichard);
                game.init();
                game.play();
                game.calculateStats();
                game.resetGame();
            }
            MyLogger.setLogLevel(Level.INFO);
            for (Player player : playersRichard) {
                MyLogger.log(Level.INFO, "Player " + player.getNumber() + " V:" + player.getNumberOfVictory()
                        +"("+player.getNumberOfVictory()/10+"%)" + " D:" + player.getNumberOfDefeat() + "(" + player.getNumberOfDefeat()/10 + "%)"+
                        " E:" + player.getNumberOfDraw() + "(" + player.getNumberOfDraw()/10 + "%)" + " SM:" + player.getAverageScore());
            }
        } else if (demo) {
            Game game = new Game(playersRichard);
            game.init();
            game.play();
        } else if (csv) {
            File fichier = new File(Csv.getFilePath());
            if (fichier.exists()) {
                CSVReader reader = new CSVReader(new FileReader(Csv.getFilePath()));
                List<String[]> oldData = reader.readAll();
                reader.close();
                for (int a = 0; a < playersRichard.length; a++) {
                    playersRichard[a].setNumberOfVictory(Integer.parseInt(oldData.get(a + 1)[1]));
                    playersRichard[a].setWinRate(Float.parseFloat(oldData.get(a + 1)[2]));
                    playersRichard[a].setNumberOfDefeat(Integer.parseInt(oldData.get(a + 1)[3]));
                    playersRichard[a].setLossRate(Float.parseFloat(oldData.get(a + 1)[4]));
                    playersRichard[a].setNumberOfDraw(Integer.parseInt(oldData.get(a + 1)[5]));
                    playersRichard[a].setDrawRate(Float.parseFloat(oldData.get(a + 1)[6]));
                    playersRichard[a].setCumulatedScore(Integer.parseInt(oldData.get(a + 1)[7]));
                    playersRichard[a].setAverageScore(Float.parseFloat(oldData.get(a + 1)[8]));
                }
            }
            MyLogger.setLogLevel(Level.OFF);
            for (int i = 0; i < 2000; i++) {
                Game game = new Game(playersRichard);
                game.init();
                game.play();
                game.calculateStats();
                game.rearrangePlayer();
                game.resetGame();
            }
            List<String[]> newStats = new ArrayList<>();
            newStats.add(new String[]{"Player", "Wins", "Win Rate", "Losses", "Loss Rate", "Draws", "Draw Rate", "Cumulated Score", "Average Score"});
            for (Player p : playersRichard) {
                newStats.add(p.getStats());
            }
            Csv.appendIntoCsvFile(Csv.getFilePath(), newStats);
        }
    }


}
