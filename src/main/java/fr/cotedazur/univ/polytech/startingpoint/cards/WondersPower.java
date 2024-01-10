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
            wonder.setColor(missingColor);
        }
    },

    DONJON {
        @Override
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
        @Override
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
        @Override
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
        @Override
        public void power(Constructions c, Player ... players){
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
            p.getHand().add(d.draw());
            p.getHand().add(d.draw());
        }
    },

    ECOLE_DE_MAGIE {
        @Override
        public void power(Player player) {
            int numberOfCharacter = player.getCharacter().getNumber();
            if (numberOfCharacter == 4 || numberOfCharacter == 5 || numberOfCharacter == 6 || numberOfCharacter == 8) {
                player.addGold(1);
                System.out.println("Le joueur " + player +" à gagné une pièce grâce à l'école de magie");
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

  

    public Wonder getWonder(){
        return new Wonder(this.name(), 0, this);
    }
   
}


