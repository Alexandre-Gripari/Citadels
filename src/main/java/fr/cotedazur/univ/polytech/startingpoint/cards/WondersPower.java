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
        public void power(Player player, Draw d){
            if (player.getGold() >= 3) {
                player.getHand().add(d.draw());
                player.getHand().add(d.draw());
                player.getHand().add(d.draw());
                player.addGold(-3);
            }
        }
    },

    OBSERVATOIRE {
        public void power(Player player, Draw d){
            Constructions c1 = d.draw();
            Constructions c2 = player.getHand().get(player.getHand().size()-1);
            if (c1.getValue() <= c2.getValue()){
                d.add(c2);
                System.out.println("Le joueur " + player.getNumber() + " a pioché " + c1);
                player.getHand().set(player.getHand().size()-1, c1);
            }
            else {
                d.add(c1);
                System.out.println("Le joueur " + player.getNumber() + " a pioché " + c2);
            }
        }
    },

    CIMETIERE {
        public void power(){return;}
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
    public void power(Player p, Draw d){}
}
