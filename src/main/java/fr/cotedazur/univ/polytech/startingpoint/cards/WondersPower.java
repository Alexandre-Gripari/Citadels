package fr.cotedazur.univ.polytech.startingpoint.cards;

import fr.cotedazur.univ.polytech.startingpoint.Draw;
import fr.cotedazur.univ.polytech.startingpoint.Player;
import fr.cotedazur.univ.polytech.startingpoint.players.City;


public enum WondersPower {

    COUR_DES_MIRACLES {
        @Override
        public void power(City city, Wonder wonder){
            // la couleur devient celle qui n'est pas dans la cité
            Color missingColor = city.missingColor();
            if (missingColor != Color.NEUTRE) wonder.setColor(missingColor);
        }
    },

    DONJON {
        @Override
        public void power(){return;} // dans l'abilité de la condotière
    },

    LABORATOIRE {
        @Override
        public void power(Constructions c, Player player, Draw d) {
            player.discardConstruction(c,d);
            player.addGold(1);
        }
    },

    MANUFACTURE {
        @Override
        public void power(Player player, Draw d){
            if (player.getGold() >= 3) {
                player.draw(d,3);
                player.addGold(-3);
            }
        }
    },

    OBSERVATOIRE {
        @Override
        public void power(Player player, Draw d){
            player.drawConstruction(d, 3);
        }
    },

    CIMETIERE {
        @Override
        public void power(Constructions c, Player ... players){
            if (c == null) return;
            Wonder associatedWonder = this.getWonder();
            for (Player player : players) {
                if (player.getWonders().contains(associatedWonder) && player.getCharacter() != Character.CONDOTTIERE) {
                    player.useCimetiery(c);
                }
            }
        }
    },

    BIBLIOTHEQUE {
        @Override
        public void power(Player p, Draw d){
            p.draw(d,2);
        }
    },

    ECOLE_DE_MAGIE {
        @Override
        public void power(Player player, Color color) {
            for (Wonder wonder : player.getWonders()) {
                if (wonder.getName().equals("Ecole de magie")) wonder.setColor(color);
            }
        }
    },

    UNIVERSITE {
        @Override
        public void power(){return;} // effet de la merveille effectif dans le calcul de la valeur d'une cité
    },

    DRACOPORT {
        public void power(){return;} // effet de la merveille effectif dans le calcul de la valeur d'une cité
    };

    public void power(){}
    public void power(Player p, Draw d) {}
    public void power(Constructions c, Player ... players){}
    public void power(Player player){}
    public void power(Constructions c, Player player, Draw d){}
    public void power(City city, Wonder wonder){}
    public void power(Player player, Color color){}

  

    public Wonder getWonder(){
        return new Wonder(this.name(), 0, this);
    }

}


