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
}
