package fr.cotedazur.univ.polytech.startingpoint;


import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import fr.cotedazur.univ.polytech.startingpoint.players.City;
import fr.cotedazur.univ.polytech.startingpoint.players.Hand;
import fr.cotedazur.univ.polytech.startingpoint.strategies.Strategy1;
import fr.cotedazur.univ.polytech.startingpoint.strategies.StrategyRichard;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;


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
        Player p1 = new Player(1, new Hand());
        Player p2 = new Player(2, new Hand());
        Player p3 = new Player(3, new Hand());
        Player p4 = new Player(4, new Hand());
        Player[] players = new Player[]{p1, p2, p3, p4};
        Player p5 = new Player(5, 2, new Hand(), new City(), new StrategyRichard("Richard"));
        Player p6 = new Player(6, 2, new Hand(), new City(), new StrategyRichard("Richard"));
        Player p7 = new Player(7, 2, new Hand(), new City(), new StrategyRichard("Richard"));
        Player p8 = new Player(8, 2, new Hand(), new City(), new Strategy1("Agressif"));
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
                        +"("+player.getNumberOfVictory()/10+"%)" + " D:" + player.getNumberOfDefeat() + "(" + player.getNumberOfDefeat()/10 + "%)"+
                        " E:" + player.getNumberOfDraw() + "(" + player.getNumberOfDraw()/10 + "%)" + " SM:" + player.getAverageScore());
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
            Game game = new Game(players);
            game.init();
            game.play();
        } else if (csv) {
            if (!new File(Csv.getFilePath()).exists()) {
                Csv.appendIntoCsvFile(Csv.getFilePath(), new String[]{"Player", "Wins", "Win Rate", "Losses", "Loss Rate", "Draws", "Draw Rate"});
            } else {
                CSVReader reader = new CSVReader(new FileReader(Csv.getFilePath()));
                if (reader.readNext() == null) {
                    Csv.appendIntoCsvFile(Csv.getFilePath(), new String[]{"Player", "Wins", "Win Rate", "Losses", "Loss Rate", "Draws", "Draw Rate"});
                }
                reader.close();
            }
            MyLogger.setLogLevel(Level.OFF);
            for (int i = 0; i < 2000; i++) {
                Game game = new Game(players);
                game.init();
                game.play();
                game.calculateStats();
                game.resetGame();
            }
            for (Player p : players) {
                Csv.appendIntoCsvFile(Csv.getFilePath(),p.getStats());
            }
            Csv.readCsvFile(Csv.getFilePath());
        }

    }
}
