package fr.cotedazur.univ.polytech.startingpoint.cards;

import fr.cotedazur.univ.polytech.startingpoint.Draw;
import fr.cotedazur.univ.polytech.startingpoint.Player;
import fr.cotedazur.univ.polytech.startingpoint.players.City;

public enum WondersPower {

    COUR_DES_MIRACLES {
        @Override
        public void power(Player player, Wonder wonder){
            // la couleur devient celle qui n'est pas dans la cité
            City city = player.getCity();
            Color missingColor = city.missingColor();
            wonder.setColor(missingColor);
        }
    },

    DONJON {
        public void power(){return;}
    },

    LABORATOIRE {
        @Override
        public void power(Constructions c, Player player, Draw d) {
            player.discardConstruction(c,d);
            player.addGold(1);
        }
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

    public void power(){}
    public void power(Constructions c, Player ... players){}
    public void power(Player player){}
    public void power(Constructions c, Player player, Draw d){}
    public void power(Player player, Wonder wonder){}

  
  
    public Wonder getWonder(){
        return new Wonder(this.name(), 0, this);
    }
   

}


