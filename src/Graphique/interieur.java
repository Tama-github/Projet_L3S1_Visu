package Graphique;

public class interieur {

    protected String batiment;
    protected int etage;
    protected int salle;

    public interieur (String building, int floor, int room) {
        this.batiment = building;
        this.etage = floor;
        this.salle = room;
    }
}
