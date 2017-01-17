import java.util.ArrayList;

public class InformationsCapteur {

    private String nom;
    private Localisation loc;
    private ArrayList<Mesure> listeMesures;

    public InformationsCapteur(String nom, ArrayList<Mesure> mes, Localisation where) {
        this.nom = nom;
        this.loc = where;
        this.listeMesures = mes;
    }

    public String getType() {
        return nom;
    }


    public Localisation getLocalisation() {
        return loc;
    }


    public ArrayList<Mesure> getListeMesures() {
        return listeMesures;
    }
}
