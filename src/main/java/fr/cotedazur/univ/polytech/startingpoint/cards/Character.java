package fr.cotedazur.univ.polytech.startingpoint.cards;


import fr.cotedazur.univ.polytech.startingpoint.Draw;
import fr.cotedazur.univ.polytech.startingpoint.Player;
import fr.cotedazur.univ.polytech.startingpoint.players.Hand;

public enum Character implements Ability{

    ASSASSIN("Assassin", Color.NEUTRE, 1){
        @Override
        public void ability(Player victim,Player useless){
            victim.kill();
        }
    },

    VOLEUR("Voleur", Color.NEUTRE, 2){
        @Override
        public void ability(Player thief,Player victim){
            if(!victim.getCharacter().equals(ASSASSIN) && !victim.isDead()) {
                int butin = victim.getGold();
                victim.setGold(0);
                thief.setGold(thief.getGold() + butin);
                return;
            }
        }
    },
          
    MAGICIEN("Magicien", Color.NEUTRE, 3){
        @Override
        public void ability(Draw draw, Player ... players){
            players = reorganizePlayers(MAGICIEN, players);
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

    ROI("Roi", Color.NOBLE, 4){
        @Override
        public void ability(Player player){
            int nbOfNoblessConstructions = 0;
            for (int i = 0; i < player.getCity().size(); i++) {
                if (player.getCity().get(i).getColor() == this.getColor()) nbOfNoblessConstructions++;
            }
            player.addGold(nbOfNoblessConstructions);
            System.out.println("Le joueur " + player.getNumber() + " gagne " + nbOfNoblessConstructions +" d'or grâce à la capacité du Roi");
        }
    },
  
    EVEQUE("Évêque", Color.RELIGIEUX, 5){
        @Override
        public void ability(Player player){
            int nbOfReligiousConstructions = 0;
            for (int i = 0; i < player.getCity().size(); i++) {
                if (player.getCity().get(i).getColor() == this.getColor()) nbOfReligiousConstructions++;
            }
            player.addGold(nbOfReligiousConstructions);
            System.out.println("Le joueur " + player.getNumber() + " gagne " + nbOfReligiousConstructions +" d'or grâce à la capacité de l'évêque");
        }
    },

    MARCHAND("Marchand", Color.COMMERCIAL, 6){
        @Override
        public void ability(Player player){
            int nbOfCommercialConstructions = 1;
            for (int i = 0; i < player.getCity().size(); i++) {
                if (player.getCity().get(i).getColor() == Color.COMMERCIAL) nbOfCommercialConstructions++;
            }
            player.addGold(nbOfCommercialConstructions);
            System.out.println("Le joueur " + player.getNumber() + " gagne " + nbOfCommercialConstructions +" d'or grâce à la capacité du marchand");
        }
    },

    ARCHITECTE("Architecte", Color.NEUTRE, 7){
        @Override
        public void ability(Draw draw,Player ... players){
            players = reorganizePlayers(ARCHITECTE, players);
            players[0].draw(draw,2);
        }
    },

    CONDOTTIERE("Condotière", Color.SOLDATESQUE, 8){
        @Override
        public void ability(int index, Player ... players){
            players = reorganizePlayers(CONDOTTIERE, players);
            String res = "";
            int nbOfArmyConstructions = 0;
            Player selfPlayer = players[0];
            for (int i = 0; i < selfPlayer.getCity().size(); i++) {
                if (selfPlayer.getCity().get(i).getColor() == this.getColor()) nbOfArmyConstructions++;
            }
            selfPlayer.addGold(nbOfArmyConstructions);
            res += "Le joueur " + selfPlayer.getNumber() + " gagne " + nbOfArmyConstructions +" d'or grâce à la capacité du condottiere";
            if (players.length >= 2) {
                Player targetedPlayer = players[1];
                int cost = targetedPlayer.getCity().get(index).getValue() - 1;
                if (selfPlayer.getGold() >= cost) {
                    res += " et il détruit la cité : " + targetedPlayer.getCity().get(index).toString() + " du joueur " + targetedPlayer.getNumber() + " et perd " + cost + " d'or";
                    targetedPlayer.getCity().remove(index);
                    selfPlayer.addGold(-cost);
                }
            }
            System.out.println(res);
        }
    };

    private String name;
    private Color color;
    private int number;

    private Character(String n, Color c, int num) {
        this.name = n;
        this.color = c;
        this.number = num;
    }

    public void ability(Player player) {return;}
    public void ability(Player selfPlayer, Player targetedPlayer){return;}
    public void ability(int index, Player ... players) {return;}

    public String getName() { return this.name; }

    public Color getColor() { return this.color; }

    public int getNumber() { return this.number; }

    public void ability(Draw d, Player ... players) {
    }

    public Player[] reorganizePlayers(Character c, Player ... players) {
        boolean isCharacter = false;
        for (Player player : players) {
            if (player.getCharacter() == Character.ROI) {
                isCharacter = true;
                break;
            }
        }
        if (players[0].getCharacter() != c && isCharacter) {
            Player p = players[0];
            for (int i = 0; i < players.length - 1; i++)
                players[i] = players[i + 1];
            players[players.length - 1] = p;
            players = reorganizePlayers(c, players);
        }
        return players;
    }
}







