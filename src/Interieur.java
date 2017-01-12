public class Interieur {

    protected String batiment;
    protected int etage;
    protected int salle;

    public Interieur(String building, int floor, int room) {
        this.batiment = building;
        this.etage = floor;
        this.salle = room;
    }
}
