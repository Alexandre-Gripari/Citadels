package fr.cotedazur.univ.polytech.startingpoint.players;
import fr.cotedazur.univ.polytech.startingpoint.cards.Constructions;
import java.util.ArrayList;

public class City{

    private ArrayList<Constructions> city;

    public City() {
        city = new ArrayList<>();
    }

    public void add(Constructions c) { city.add(c); }

    public Constructions get(int i) { return city.get(i); }

    public Integer size() { return city.size(); }
    public int cityValue(){
        int value=0;
        for(int i=0;i<this.size();i++){
            value+=this.get(i).getValue();
        }
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
}
