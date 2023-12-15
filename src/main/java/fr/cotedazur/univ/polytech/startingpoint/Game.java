package fr.cotedazur.univ.polytech.startingpoint;


import fr.cotedazur.univ.polytech.startingpoint.cards.Character;
import fr.cotedazur.univ.polytech.startingpoint.cards.Color;
import fr.cotedazur.univ.polytech.startingpoint.cards.Constructions;

import java.util.*;

public class Game {

    private Player[] players;
    private double nbTurn;
    private Draw draw;
    private List<Character> characters;
    private Character[] charactersDiscarded = new Character[3];

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
        for (Player player : players)
            if (player.getCity().size() >= 8) return true;
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
            System.out.println("\nTour " + (int) nbTurn + " : ");
            discardCharacter();
            choiceOfCharacter();
            sortPlayersByCharacter();
            for (Player player : players) {
                Player[] opponents = getOpponents(player);
                player.play(draw, opponents);
                System.out.println("Le joueur " + player.getNumber() + " a dans sa ville : " + player.getCity() + player.getGold() + " d'or et " + player.getHand().size() + " cartes dans sa main.\n");
            }
        }
        sortPlayersByPoints();
        if (players[0].getCity().cityValue() == players[1].getCity().cityValue()) System.out.println("Egalité ! Les deux joueurs ont " + players[0].getCity().cityValue() + " points !");
        else {
            System.out.println("Le joueur " + players[1].getNumber() + " a gagné avec " + players[1].getCity().cityValue() + " points !");
            System.out.println("Le joueur " + players[0].getNumber() + " a perdu lamentablement avec " + players[0].getCity().cityValue() + " points !");
        }

    }

    public void sortPlayersByPoints(){
        Arrays.sort(players);
    }

    public Player[] getOpponents(Player player) {
        Player[] opponents = new Player[players.length - 1];
        int j = 0;
        for (int i = 0; i < players.length - 1; i++) {
            if (players[i].getNumber() != player.getNumber()) {
                opponents[j] = players[i];
                j++;
            }
        }
        assert opponents.length == players.length - 1;
        return opponents;
    }

    public void choiceOfCharacter(){
        for (Player player : players) {
            player.chooseCharacter(characters);
        }
        for (Player player : players) {
            characters.add(player.getCharacter());
        }
        characters.addAll(Arrays.asList(charactersDiscarded));
        reorganizePlayers();
        charactersDiscarded = new Character[3];
    }

    public void discardCharacter(){
        Random random = new Random();
        for (int i = 0; i < 2; i++) {
            int randoms = random.nextInt(characters.size());
            Character characterDiscarded = characters.get(randoms);
            System.out.println("Le personnage " + characterDiscarded + " a été défaussé.");
            charactersDiscarded[i] = characterDiscarded;
            characters.remove(characterDiscarded);
        }

        int randoms = random.nextInt(characters.size());
        Character characterDiscarded = characters.get(randoms);
        charactersDiscarded[2] = characterDiscarded;
        characters.remove(characterDiscarded);
        System.out.println("Un personnage a été défaussé face cachée.\n");
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


}
