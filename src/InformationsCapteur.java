import java.util.ArrayList;

public class InformationsCapteur {

    private String type;
    private Localisation loc;
    private ArrayList<Mesure> listeMesures;

    public InformationsCapteur(String nom, ArrayList<Mesure> mes, Localisation where) {
        this.type = nom;
        this.loc = where;
        this.listeMesures = mes;
    }

    public String getType() {
        return type;
    }


    public Localisation getLocalisation() {
        return loc;
    }


    public ArrayList<Mesure> getListeMesures() {
        return listeMesures;
    }
}
