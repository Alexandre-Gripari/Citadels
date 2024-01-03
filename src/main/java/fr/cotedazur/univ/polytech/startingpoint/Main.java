package fr.cotedazur.univ.polytech.startingpoint;


import fr.cotedazur.univ.polytech.startingpoint.players.Hand;

public class Main {


    public static void main(String... args) {
        Game game = new Game(new Player[]{new Player(1, new Hand()),
                new Player(2, new Hand()), new Player(3, new Hand()), new Player(4, new Hand())});
        game.init();
        game.play();
    }

}
