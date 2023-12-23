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
        public void power(){return;}
    },

    BIBLIOTHEQUE {
        public void power(){return;}
    },

    ECOLE_DE_MAGIE {
        public void power(Player player) {
            int numberOfCharacter = player.getCharacter().getNumber();
            if (numberOfCharacter == 4 || numberOfCharacter == 5 || numberOfCharacter == 6 || numberOfCharacter == 8) player.addGold(1);
        }
    },

    UNIVERSITE {
        public void power(){return;}
    },

    DRACOPORT {
        public void power(){return;} // effet de la merveille effectif dans le calcul de la valeur d'une cité
    };

    public void power(){};
    public void power(Player player){return;};
}


