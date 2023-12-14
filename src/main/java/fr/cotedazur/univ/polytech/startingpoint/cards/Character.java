package fr.cotedazur.univ.polytech.startingpoint.cards;


import fr.cotedazur.univ.polytech.startingpoint.Player;

public enum Character {

    ASSASSIN("Assassin", Color.NEUTRE, 1){
        @Override
        public void ability(Player player){return;}
    },

    ROI("Roi", Color.NOBLE, 4){
        public void ability(Player player){
            for (int i = 0; i < player.getCity().size(); i++) {
                if (player.getCity().get(i).getColor() == Color.NOBLE) player.addGold(1);
            }

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
                if (player.getCity().get(i).getColor() == this.getColor()) nbOfCommercialConstructions++;
            }
            player.addGold(nbOfCommercialConstructions);
            System.out.println("Le joueur " + player.getNumber() + " gagne " + nbOfCommercialConstructions +" d'or grâce à la capacité du marchand");
        }
    },

    ARCHITECTE("Architecte", Color.NEUTRE, 7){
        @Override
        public void ability(Player player){return;}
    },

    VOLEUR("Voleur", Color.NEUTRE, 2){
        @Override
        public void ability(Player seflPlayer, Player targetedPlayer){return;}
    },

    MAGICIEN("Magicien", Color.NEUTRE, 3){
        @Override
        public void ability(Player seflPlayer, Player targetedPlayer){return;}
    },

    CONDOTTIERE("Condotière", Color.SOLDATESQUE, 8){
        @Override
        public void ability(int index, Player ... players){
            String res = "";
            int nbOfArmyConstructions = 0;
            Player selfPlayer = players[0];
            for (int i = 0; i < selfPlayer.getCity().size(); i++) {
                if (selfPlayer.getCity().get(i).getColor() == this.getColor()) nbOfArmyConstructions++;
            }
            selfPlayer.addGold(nbOfArmyConstructions);
            res += "Le joueur " + selfPlayer.getNumber() + " gagne " + nbOfArmyConstructions +" d'or grâce à la capacité du condottiere";
            if (players.length == 2) {
                Player targetedPlayer = players[1];
                int cost = targetedPlayer.getCity().get(index).getValue() - 1;
                res += " et il détruit la cité : " + targetedPlayer.getCity().get(index).toString() + " du joueur " + targetedPlayer.getNumber() +" et perd " + cost + " d'or";
                targetedPlayer.getCity().remove(index);
                selfPlayer.addGold(-cost);
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

}







