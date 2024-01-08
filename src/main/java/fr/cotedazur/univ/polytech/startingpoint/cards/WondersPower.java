package fr.cotedazur.univ.polytech.startingpoint.cards;

import fr.cotedazur.univ.polytech.startingpoint.Draw;
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
        public void power(Player p, Draw d){
            p.getHand().add(d.draw());
            p.getHand().add(d.draw());
        }
    },

    ECOLE_DE_MAGIE {
        public void power(){return;}
    },

    UNIVERSITE {
        public void power(Constructions c){
            c.setValue(8);
        }
    },

    DRACOPORT {
        public void power(){return;}
    };

    public void power(){}

    public void power(Constructions c){}

    public void power(Player p, Draw d) {}
}
