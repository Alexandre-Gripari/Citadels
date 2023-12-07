package fr.cotedazur.univ.polytech.startingpoint;


import fr.cotedazur.univ.polytech.startingpoint.cards.Color;
import fr.cotedazur.univ.polytech.startingpoint.cards.Constructions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

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

        draw.shuffle();

    }

    public Draw getDraw() {
        return draw;
    }

    public boolean isFinished() {
        for (Player player : players)
            if (player.getCity().size() >= 3) return true;
        return false;
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
            System.out.println("Tour " + (int) nbTurn + " : ");
            for (Player player : players) {
                player.play(draw);
                System.out.println("Le joueur " + player.getNumber() + " a dans sa ville : " + player.getCity() + player.getGold() + " d'or et " + player.getHand().size() + " cartes dans sa main.\n");
            }
        }
        switch(getPlayers()[0].getCity().compareTo(getPlayers()[1].getCity())){
            case 1:
                System.out.println("Le joueur 1 gagne avec "+getPlayers()[0].getCity().cityValue()+" points.\nLe joueur 2 finit lamentablement avec "+getPlayers()[1].getCity().cityValue()+ " points.");
                break;
            case -1:
                System.out.println("Le joueur 2 gagne avec "+getPlayers()[1].getCity().cityValue()+" points.\nLe joueur 1 finit misérablement avec "+getPlayers()[0].getCity().cityValue()+ " points.");
                break;
            case 0:
                System.out.println("Il y a égalité avec "+getPlayers()[0].getCity().cityValue()+" points.");
                break;
        }
    }




}
