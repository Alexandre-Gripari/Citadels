package fr.cotedazur.univ.polytech.startingpoint;


import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import fr.cotedazur.univ.polytech.startingpoint.players.Hand;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
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
                MyLogger.log(Level.INFO, "Player " + player.getNumber() + " V:" + player.getNumberOfVictory()
                        +"("+player.getNumberOfVictory()/10+"%)" + " D:" + player.getNumberOfDefeat() + "(" + player.getNumberOfDefeat()/10 + "%)"+
                        " E:" + player.getNumberOfDraw() + "(" + player.getNumberOfDraw()/10 + "%)" + " SM:" + player.getAverageScore());
            }
        } else if (demo) {
            Game game = new Game(players);
            game.init();
            game.play();
        }else if (csv) {
            File fichier = new File(Csv.getFilePath());
            if (fichier.exists()) {
                CSVReader reader = new CSVReader(new FileReader(Csv.getFilePath()));
                List<String[]> oldData = reader.readAll();
                for (int a = 0; a < players.length; a++) {
                    players[a].setNumberOfVictory(Integer.parseInt(oldData.get(a + 1)[1]));
                    players[a].setWinRate(Integer.parseInt(oldData.get(a + 1)[2]));
                    players[a].setNumberOfDefeat(Integer.parseInt(oldData.get(a + 1)[3]));
                    players[a].setLossRate(Integer.parseInt(oldData.get(a + 1)[4]));
                    players[a].setNumberOfDraw(Integer.parseInt(oldData.get(a + 1)[5]));
                    players[a].setDrawRate(Integer.parseInt(oldData.get(a + 1)[6]));
                }
        } else if (csv) {
            String[] legende = {"Player", "Wins", "Win Rate", "Losses", "Loss Rate", "Draws", "Draw Rate"};
            if (!new File(Csv.getFilePath()).exists()) {
                Csv.appendIntoCsvFile(Csv.getFilePath(), legende);
            } else {
                CSVReader reader = new CSVReader(new FileReader(Csv.getFilePath()));
                if (!Arrays.equals(reader.readNext(), legende)) {
                    Csv.appendIntoCsvFile(Csv.getFilePath(), legende);
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
            List<String[]> newStats = new ArrayList<>();
            for (Player p : players) {
                newStats.add(p.getStats());
            }
            Csv.appendIntoCsvFile(Csv.getFilePath(), newStats);
        }
            for (Player p : players) {
                Csv.appendIntoCsvFile(Csv.getFilePath(),p.getStats());
            }
        }
    }
}
