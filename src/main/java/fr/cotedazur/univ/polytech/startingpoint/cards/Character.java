package fr.cotedazur.univ.polytech.startingpoint.cards;


import fr.cotedazur.univ.polytech.startingpoint.Draw;
import fr.cotedazur.univ.polytech.startingpoint.Player;
import fr.cotedazur.univ.polytech.startingpoint.players.Hand;

public enum Character {

    ASSASSIN("Assassin", Color.NEUTRE, 1){
        public void ability(){return;}
    },

    VOLEUR("Voleur", Color.NEUTRE, 2){
        public void ability(){return;}
    },

    MAGICIEN("Magicien", Color.NEUTRE, 3){
        @Override
        public void ability(Draw draw, Player ... players){
            Hand hand = players[0].getHand();
            int size = hand.size();
            if (players.length == 1) {
                for (int i = 0; i < size; i++) {
                    draw.add(hand.get(0));
                    hand.remove(0);
                }
                players[0].draw(draw, size);

            }
            else {
                Hand hand2 = players[1].getHand();
                Hand tmp = new Hand();
                tmp.setHand(hand);
                hand.setHand(hand2);
                hand2.setHand(tmp);
            }


        }
    },

    ROI("Roi", Color.NEUTRE, 4){
        public void ability(){return;}
    },

    EVEQUE("Évêque", Color.RELIGIEUX, 5){
        public void ability(){return;}
    },

    MARCHAND("Marchand", Color.COMMERCIAL, 6){
        public void ability(){return;}
    },
    ARCHITECTE("Architecte", Color.NEUTRE, 7){
        public void ability(Draw draw,Player ... players){
            players[0].draw(draw,2);
        }
    },

    CONDOTIERE("Condotière", Color.SOLDATESQUE, 8){
        public void ability(){return;}
    };

    private String name;
    private Color color;
    private int number;

    private Character(String n, Color c, int num) {
        this.name = n;
        this.color = c;
        this.number = num;
    }

    public String getName() { return this.name; }

    public Color getColor() { return this.color; }

    public int getNumber() { return this.number; }


    public void ability(Draw d, Player ... players) {
    }

}







