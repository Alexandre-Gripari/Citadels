package fr.cotedazur.univ.polytech.startingpoint.cards;


import fr.cotedazur.univ.polytech.startingpoint.Draw;
import fr.cotedazur.univ.polytech.startingpoint.MyLogger;
import fr.cotedazur.univ.polytech.startingpoint.Player;
import fr.cotedazur.univ.polytech.startingpoint.players.Hand;

import java.util.logging.Level;

public enum Character{

    ASSASSIN("Assassin", Color.NEUTRE, 1){
        @Override
        public void ability(Character character, Player ... players){
            MyLogger.log(Level.INFO, "Le joueur a assasiné " + character.getName());
            for (Player p : players) {
                if (p.getCharacter().equals(character)) {
                    p.kill();
                }
            }
        }
    },

    VOLEUR("Voleur", Color.NEUTRE, 2){
        @Override
        public void ability(Character character, Player ... players){
            Player cible = players[1];
            Player self = players[0];
            for (Player p : players){
                if (p.getCharacter().equals(character)) cible = p;
            }
            if(!cible.getCharacter().equals(ASSASSIN) && !cible.isDead()) {
                MyLogger.log(Level.INFO, "Le joueur a volé" + cible.getCharacter());
                int butin = cible.getGold();
                cible.setGold(0);
                self.addGold(butin);
            }
        }
    },
          
    MAGICIEN("Magicien", Color.NEUTRE, 3){
        @Override
        public void ability(Draw draw, Player ... players){
            //players = reorganizePlayers(MAGICIEN, players);
            Hand hand = players[0].getHand();
            int size = hand.size();
            if (players.length == 1) {
                for (int i = 0; i < size; i++) {
                    draw.add(hand.get(0));
                    hand.remove(0);
                }
                players[0].draw(draw, size);
                MyLogger.log(Level.INFO, "Le joueur " + players[0].getNumber() + " a échangé sa main avec la pioche");
            }
            else {
                Hand hand2 = players[1].getHand();
                Hand tmp = new Hand();
                tmp.setHand(hand);
                hand.setHand(hand2);
                hand2.setHand(tmp);
                MyLogger.log(Level.INFO, "Le joueur " + players[0].getNumber() + " a échangé sa main avec le joueur " + players[1].getNumber());
            }
       }
    },

    ROI("Roi", Color.NOBLE, 4){
        @Override
        public void ability(Player player){
            int nbOfNoblessConstructions = 0;
            for (int i = 0; i < player.getCity().size(); i++) {
                if (player.getCity().get(i).getColor() == this.getColor()) nbOfNoblessConstructions++;
            }
            player.addGold(nbOfNoblessConstructions);
            MyLogger.log(Level.INFO, "Le joueur " + player.getNumber() + " gagne " + nbOfNoblessConstructions +" d'or grâce à la capacité du Roi");
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
            MyLogger.log(Level.INFO, "Le joueur " + player.getNumber() + " gagne " + nbOfReligiousConstructions +" d'or grâce à la capacité de l'évêque");
        } 
    },

    MARCHAND("Marchand", Color.COMMERCIAL, 6){
        @Override
        public void ability(Player player){
            int nbOfCommercialConstructions = 1;
            for (int i = 0; i < player.getCity().size(); i++) {
                if (player.getCity().get(i).getColor() == Color.COMMERCIAL) nbOfCommercialConstructions++;
            }
            player.addGold(nbOfCommercialConstructions);
            MyLogger.log(Level.INFO, "Le joueur " + player.getNumber() + " gagne " + nbOfCommercialConstructions +" d'or grâce à la capacité du marchand");
        }
    },

    ARCHITECTE("Architecte", Color.NEUTRE, 7){
        @Override
        public void ability(Draw draw, Player ... players){
            //players = reorganizePlayers(ARCHITECTE, players);
            players[0].draw(draw,2);
            MyLogger.log(Level.INFO, "Le joueur " + players[0].getNumber() + " a pioché 2 cartes grace au pouvoir de l'architecte");
        }
    },

    CONDOTTIERE("Condotière", Color.SOLDATESQUE, 8){
        @Override
        public Constructions ability(Constructions c, Player self, Player opponent){
            int gold = 0;
            if (c != null && !c.getName().equals("Donjon")) {
                opponent.destroyConstruction(c);
                self.addGold(-c.getValue()+1);
                MyLogger.log(Level.INFO, "Le joueur " + self.getNumber() + " a détruit la construction " + c.getName() + " du joueur " + opponent.getNumber());
            }
            for (Constructions co : self.getCity().getCity()) {
                if (co.getColor() == Color.SOLDATESQUE) {
                    gold++;
                }
            }
            self.addGold(gold);
            MyLogger.log(Level.INFO, "Le joueur " + self.getNumber() + " gagne " + gold + " d'or grâce à la capacité du condotière");
            return c;
        }
    };

    private final String name;
    private final Color color;
    private final int number;

    Character(String n, Color c, int num) {
        this.name = n;
        this.color = c;
        this.number = num;
    }

    public void ability(Character character, Player[] players) {/* abilité du joueur sur les autres personages*/}
    public void ability(Player self) {/* abilité du joueur lui-même*/}
    public void ability(Player ... players) {/* abilité du joueur sur les autres joueurs*/}
    public Constructions ability(Constructions c, Player self, Player opponent) { return null;/* abilité du joueur sur les autres joueurs et sur une construction*/}
    public void ability(Draw d, Player ... players) {/* abilité du joueur sur les autres joueurs et sur la pioche*/}

    public String getName() { return this.name; }

    public Color getColor() { return this.color; }

    public int getNumber() { return this.number; }

    public boolean isDead(Player[] players) {
        for (Player p : players) {
            if (p.getCharacter().equals(this) && p.isDead()) return true;
        }
        return false;
    }

}







