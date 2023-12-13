package fr.cotedazur.univ.polytech.startingpoint.cards;


import fr.cotedazur.univ.polytech.startingpoint.Player;

public enum Character {

    ASSASSIN("Assassin", Color.NEUTRE, 1){
        public void ability(){return;}
    },

    VOLEUR("Voleur", Color.NEUTRE, 2){
        public void ability(){return;}
    },

    MAGICIEN("Magicien", Color.NEUTRE, 3){
        public void ability(){return;}
    },

    ROI("Roi", Color.NEUTRE, 4){
        public void ability(){return;}
    },

    EVEQUE("Évêque", Color.RELIGIEUX, 5){
        public void ability(Player player){
            int nbOfReligiousConstructions = 0;
            for (int i = 0; i < player.getCity().size(); i++) {
                if (player.getCity().get(i).getColor() == this.getColor()) nbOfReligiousConstructions++;
            }
            player.addGold(nbOfReligiousConstructions);
        }
    },

    MARCHAND("Marchand", Color.COMMERCIAL, 6){
        public void ability(Player player){
            int nbOfCommercialConstructions = 1;
            for (int i = 0; i < player.getCity().size(); i++) {
                if (player.getCity().get(i).getColor() == this.getColor()) nbOfCommercialConstructions++;
            }
            player.addGold(nbOfCommercialConstructions);
        }
    },
    ARCHITECTE("Architecte", Color.NEUTRE, 7){
        public void ability(){return;}
    },

    CONDOTIERE("Condotière", Color.SOLDATESQUE, 8){
        public void ability(Player selfPlayer, Player targetedPlayer, int index){
            int nbOfArmyConstructions = 0;
            for (int i = 0; i < selfPlayer.getCity().size(); i++) {
                if (selfPlayer.getCity().get(i).getColor() == this.getColor()) nbOfArmyConstructions++;
            }
            selfPlayer.addGold(nbOfArmyConstructions);
            if (targetedPlayer != null) {
                int cost = targetedPlayer.getCity().get(index).getValue() - 1;
                targetedPlayer.getCity().remove(index);
                selfPlayer.addGold(-cost);
            }
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

    public String getName() { return this.name; }

    public Color getColor() { return this.color; }

    public int getNumber() { return this.number; }
}







