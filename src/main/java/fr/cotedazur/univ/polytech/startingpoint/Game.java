package fr.cotedazur.univ.polytech.startingpoint;


import fr.cotedazur.univ.polytech.startingpoint.cards.Character;
import fr.cotedazur.univ.polytech.startingpoint.cards.*;

import java.util.*;
import java.util.logging.Level;


public class Game {

    private Player[] players;
    private double nbTurn;
    private Draw draw;
    private List<Character> characters;
    private Character[] charactersDiscarded = new Character[3];
    private boolean someoneFinished = false;

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

        draw.add(new Wonder("Cour des miracles", 2, WondersPower.COUR_DES_MIRACLES));
        draw.add(new Wonder("Donjon", 3, WondersPower.DONJON));
        draw.add(new Wonder("Laboratoire", 5, WondersPower.LABORATOIRE));
        draw.add(new Wonder("Manufacture", 5, WondersPower.MANUFACTURE));
        draw.add(new Wonder("Observatoire", 5, WondersPower.OBSERVATOIRE));
        draw.add(new Wonder("Cimetière", 5, WondersPower.CIMETIERE));
        draw.add(new Wonder("Bibliothèque", 6, WondersPower.BIBLIOTHEQUE));
        draw.add(new Wonder("Ecole de magie", 6, WondersPower.ECOLE_DE_MAGIE));
        draw.add(new Wonder("Université", 6, WondersPower.UNIVERSITE));
        draw.add(new Wonder("Dracoport", 6, WondersPower.DRACOPORT));

        draw.shuffle();

        for (int i = 0; i < 4; i++) {
            for (Player player : players) {
                player.getHand().add(draw.draw());
            }
        }

        characters = new ArrayList<>(List.of(Character.values()));
    }

    public Draw getDraw() {
        return draw;
    }

    public boolean isFinished() {
        if (someoneFinished) {
            for (Player player : players) {
                player.setScore(player.getScore()+player.getCity().cityValue());
            }
            return true;
        }
        return false;
    }

    public Player[] getPlayers() {
        return players;
    }

    public double getNbTurn() {
        return nbTurn;
    }

    public List<Character> getCharacters() {
        return characters;
    }

    public Character[] getCharactersDiscarded() {
        return charactersDiscarded;
    }


    public void play() {
        while(!isFinished()) {
            nbTurn++;
            MyLogger.log(Level.INFO, "\nTour " + (int) nbTurn + " : ");
            discardCharacter();
            choiceOfCharacter();
            sortPlayersByCharacter();
            for (Player player : players) {
                player.play(draw, getOpponents(player));
                MyLogger.log(Level.INFO, "Le joueur " + player.getNumber() + " a dans sa ville : " + player.getCity() + player.getGold() + " d'or. \nLe joueur " + player.getNumber() + " a dans sa main : " + player.getHand() + "\n"); //+ " cartes dans sa main.\n");
                playerHasFinished(player);
            }
        }
        sortPlayersByPoints();
        for (int i= players.length-1,j=1;i>=0;i--,j++ ){
            if(i<players.length-1 && players[i].getScore() == players[i+1].getScore()) j--;
            MyLogger.log(Level.INFO, "Le joueur " + players[i].getNumber() + " termine à la " + j + "ème place avec " + players[i].getScore() + " points.");
        }

    }

    public void playerHasFinished(Player player) {
        if (player.getCity().size() >= 8) {
            if (!someoneFinished) {
                someoneFinished = true;
                player.setScore(player.getScore()+4);
            }
            else player.setScore(player.getScore()+2);
        }
    }

    public void calculateStats(){
        int refValue = players[players.length-1].getScore();
        for (int i = 0; i <= players.length-2; i++) {
            players[i].setCumulatedScore(players[i].getCumulatedScore() + players[i].getScore());
            if (players[i].getScore() == refValue) players[i].setNumberOfDraw(players[i].getNumberOfDraw() + 1);
            else players[i].setNumberOfDefeat(players[i].getNumberOfDefeat() + 1);
        }
        if (refValue == players[players.length-2].getScore()) players[players.length-1].setNumberOfDraw(players[players.length-1].getNumberOfDraw() + 1);
        else players[players.length-1].setNumberOfVictory(players[players.length-1].getNumberOfVictory() + 1);
        players[players.length-1].setCumulatedScore(players[players.length-1].getCumulatedScore() + players[players.length-1].getScore());


    }

    public void sortPlayersByPoints(){
        Arrays.sort(players);
    }

    public Player[] getOpponents(Player player) {
        Player[] opponents = new Player[players.length];
        int j = 1;
        for (Player value : players) {
            if (value.getNumber() != player.getNumber()) {
                opponents[j] = value;
                j++;
            }
        }
        assert opponents.length == players.length;
        opponents[0] = player;
        return opponents;
    }

    public void choiceOfCharacter(){
        reorganizePlayers();
        for (Player player : players) {
            player.chooseCharacter(characters, getOpponents(player));
        }
        for (Player player : players) {
            characters.add(player.getCharacter());
        }
        characters.addAll(Arrays.asList(charactersDiscarded));
    }

    public void discardCharacter(){
        Random random = new Random();
        for (int i = 0; i < 2; i++) {
            int randoms = random.nextInt(characters.size());
            Character characterDiscarded = characters.get(randoms);
            MyLogger.log(Level.INFO, "Le personnage " + characterDiscarded + " a été défaussé.");
            charactersDiscarded[i] = characterDiscarded;
            characters.remove(characterDiscarded);
        }

        int randoms = random.nextInt(characters.size());
        Character characterDiscarded = characters.get(randoms);
        charactersDiscarded[2] = characterDiscarded;
        characters.remove(characterDiscarded);
        MyLogger.log(Level.INFO, "Un personnage a été défaussé face cachée.\n");
    }

    //tri selon le numéro du personnage du joueur
    public void sortPlayersByCharacter(){
        Arrays.sort(players, Comparator.comparingInt(p -> p.getCharacter().getNumber()));
    }

    public void reorganizePlayers() {
        boolean isKing = false;
        for (Player player : players) {
            if (player.getCharacter() == Character.ROI) {
                isKing = true;
                break;
            }
        }
        if (players[0].getCharacter() != Character.ROI && isKing) {
            Player p = players[0];
            for(int i = 0; i < players.length-1; i++)
                players[i] = players[i+1];
            players[players.length-1] = p;
            reorganizePlayers();
        }
    }

    public void resetGame(){
        for (Player player : players) {
            player.reset();
        }
        nbTurn = 0;
    }


}
