package fr.cotedazur.univ.polytech.startingpoint.players;

import fr.cotedazur.univ.polytech.startingpoint.cards.Color;
import fr.cotedazur.univ.polytech.startingpoint.cards.Construction;
import fr.cotedazur.univ.polytech.startingpoint.cards.Wonder;

import java.util.ArrayList;
import java.util.List;

public class City{

    private List<Construction> city;

    public City() {
        city = new ArrayList<>();
    }

    public List<Construction> getCity() { return city; }

    public void add(Construction c) { city.add(c); }

    public void remove(int i) { city.remove(i); }

    public void remove(Construction c) { city.remove(c); }

    public Construction get(int i) { return city.get(i); }

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
        for (Construction construction : city)
            ville += construction + ", ";
        return ville;
    }

    public Color missingColor() {
        boolean color = false;
        for (Color c : Color.values()) {
            for (Construction con : city) {
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

    public int getNumberOfColor(Color color){
        int count = 0;
        for (Construction c : city) {
            if (c.getColor() == color) count++;
        }
        return count;
    }
}
