package fr.cotedazur.univ.polytech.startingpoint.cards;


import fr.cotedazur.univ.polytech.startingpoint.Player;

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
        public void ability(Player thief,Player victim){return;}
    },

    ROI("Roi", Color.NEUTRE, 4){
        @Override
        public void ability(Player thief,Player victim){return;}
    },

    EVEQUE("Évêque", Color.RELIGIEUX, 5){
        @Override
        public void ability(Player thief,Player victim){return;}
    },

    MARCHAND("Marchand", Color.COMMERCIAL, 6){
        @Override
        public void ability(Player thief,Player victim){return;}
    },
    ARCHITECTE("Architecte", Color.NEUTRE, 7){
        @Override
        public void ability(Player thief,Player victim){return;}
    },

    CONDOTIERE("Condotière", Color.SOLDATESQUE, 8){
        @Override
        public void ability(Player thief,Player victim){return;}
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







