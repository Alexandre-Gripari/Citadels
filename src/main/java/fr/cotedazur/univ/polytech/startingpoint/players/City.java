package fr.cotedazur.univ.polytech.startingpoint.players;

import fr.cotedazur.univ.polytech.startingpoint.cards.Color;
import fr.cotedazur.univ.polytech.startingpoint.cards.Constructions;
import fr.cotedazur.univ.polytech.startingpoint.cards.Wonder;

import java.util.ArrayList;
import java.util.List;

public class City{

    private List<Constructions> city;

    public City() {
        city = new ArrayList<>();
    }

    public List<Constructions> getCity() { return city; }

    public void add(Constructions c) { city.add(c); }

    public void remove(int i) { city.remove(i); }

    public void remove(Constructions c) { city.remove(c); }

    public Constructions get(int i) { return city.get(i); }

    public Integer size() { return city.size(); }

    public int cityValue(){
        int value=0;
        for(int i=0;i<this.size();i++){
            if (this.get(i).getName().equals("Cour des miracles")) {
                Wonder temp = (Wonder) this.get(i);
                temp.getWondersPower().power(this, temp);
            }
            if (this.get(i).getName().equals("Dracoport") || this.get(i).getName().equals("Université")) value+=2;
            value+=this.get(i).getValue();
        }
        if (missingColor() == Color.NEUTRE) value+=3; // Bonus si toutes les couleurs sont présentes dans la cité
        return value;
    }
    public int compareTo(City other){
        return this.cityValue()-other.cityValue();
    }

    @Override
    public String toString() {
        String ville = "";
        for (Constructions constructions : city)
            ville += constructions + ", ";
        return ville;
    }

    public Color missingColor() {
        boolean color = false;
        for (Color c : Color.values()) {
            for (Constructions con : city) {
                color = false;
                if (con.getColor() == c && !c.equals(Color.NEUTRE)) {
                    color = true;
                    break;
                }
            }
            if (!color) return c;
        }
        return Color.NEUTRE;
    }
}
