package fr.cotedazur.univ.polytech.startingpoint.cards;

import fr.cotedazur.univ.polytech.startingpoint.Player;

public enum WondersPower {

    COUR_DES_MIRACLES {
        public void power(){return;}
    },

    DONJON {
        public void power(){return;}
    },

    LABORATOIRE {
        public void power(){return;}
    },

    MANUFACTURE {
        public void power(){return;}
    },

    OBSERVATOIRE {
        public void power(){return;}
    },

    CIMETIERE {
        @Override
        public void power(Constructions c, Player ... players){
            Wonder associatedWonder = this.getWonder();
            for (Player player : players) {
                if (player.getWonders().contains(associatedWonder)) {
                    player.useCimetiery(c);
                }
            }
        }
    },

    BIBLIOTHEQUE {
        public void power(){return;}
    },

    ECOLE_DE_MAGIE {
        public void power(){return;}
    },

    UNIVERSITE {
        public void power(){return;}
    },

    DRACOPORT {
        public void power(){return;}
    };

    public void power(){}
    public void power(Constructions c, Player ... players){}

    public Wonder getWonder(){
        return new Wonder(this.name(), this, 0);
    }


}
