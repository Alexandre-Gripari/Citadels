package fr.cotedazur.univ.polytech.startingpoint;


import fr.cotedazur.univ.polytech.startingpoint.cards.Color;
import fr.cotedazur.univ.polytech.startingpoint.cards.Constructions;

public class Game {

    private Player[] players;
    private double nbTurn;
    private Draw draw;

    public Game(Player[] players) {
        this.players = players;
        this.nbTurn = 0;
        this.draw = new Draw();

    }

    public void init() {

        draw.addXConstructions(new Constructions("Temple", Color.RELIGIEUX, 1), 3);
        draw.addXConstructions(new Constructions("Eglise", Color.RELIGIEUX, 2), 4);
        draw.addXConstructions(new Constructions("Monastère", Color.RELIGIEUX, 3), 3);
        draw.addXConstructions(new Constructions("Cathédrale", Color.RELIGIEUX, 5), 2);

        draw.addXConstructions(new Constructions("Manoir", Color.NOBLE, 3), 5);
        draw.addXConstructions(new Constructions("Château", Color.NOBLE, 4), 4);
        draw.addXConstructions(new Constructions("Palais", Color.NOBLE, 5), 2);

        draw.addXConstructions(new Constructions("Tour de guet", Color.SOLDATESQUE, 1), 3);
        draw.addXConstructions(new Constructions("Forteresse", Color.SOLDATESQUE, 2), 3);
        draw.addXConstructions(new Constructions("Prison", Color.SOLDATESQUE, 3), 3);
        draw.addXConstructions(new Constructions("Bastion", Color.SOLDATESQUE, 5), 2);

        draw.addXConstructions(new Constructions("Taverne", Color.COMMERCIAL, 1), 5);
        draw.addXConstructions(new Constructions("Echoppe", Color.COMMERCIAL, 2), 3);
        draw.addXConstructions(new Constructions("Marché", Color.COMMERCIAL, 2), 4);
        draw.addXConstructions(new Constructions("Comptoir", Color.COMMERCIAL, 3), 3);
        draw.addXConstructions(new Constructions("Port", Color.COMMERCIAL, 4), 3);
        draw.addXConstructions(new Constructions("Hôtel de ville", Color.COMMERCIAL, 5), 2);

        //draw.shuffle();

    }

    public Draw getDraw() {
        return draw;
    }

    public boolean isFinished() {
        return nbTurn == 3;
    }

    public Player[] getPlayers() {
        return players;
    }

    public double getNbTurn() {
        return nbTurn;
    }

    public void play() {
        while(!isFinished()) {
            nbTurn++;
            for(Player player : players) {
                //player.play();
            }
        }
    }





}
