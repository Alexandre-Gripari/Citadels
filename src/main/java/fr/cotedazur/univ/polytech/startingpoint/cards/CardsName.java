package fr.cotedazur.univ.polytech.startingpoint.cards;

public enum CardsName {

    TEMPLE("Temple"),
    EGLISE("Eglise"),
    MONASTERE("Monastère"),
    CATHEDRALE("Cathédrale"),

    MANOIR("Manoir"),
    CHATEAU("Château"),
    PALAIS("Palais"),

    TOUR_DE_GUET("Tour de guet"),
    FORTERESSE("Forteresse"),
    PRISON("Prison"),
    BASTION("Bastion"),

    TAVERNE("Taverne"),
    ECHOPPE("Echoppe"),
    MARCHE("Marché"),
    COMPTOIR("Comptoir"),
    PORT("Port"),
    HOTEL_DE_VILLE("Hôtel de ville"),

    COUR_DES_MIRACLES("Cour des miracles"),
    DONJON("Donjon"),
    LABORATOIRE("Laboratoire"),
    MANUFACTURE("Manufacture"),
    OBSERVATOIRE("Observatoire"),
    CIMETIERE("Cimetière"),
    BIBLIOTHEQUE("Bibliothèque"),
    ECOLE_DE_MAGIE("Ecole de magie"),
    UNIVERSITE("Université"),
    DRACOPORT("Dracoport"),

    EMPTY_DRAW("empty draw"),
    NO_NAME("no name");

    private final String name;

    private CardsName(String name) {
        this.name = name;
    }

    public String getCardName() {return name;}

}
