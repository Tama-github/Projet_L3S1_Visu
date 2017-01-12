import java.util.ArrayList;

public class InformationsCapteur {

    private String type;
    private LieuCapteur loc;
    /*protected String typeUnite;
    protected String unite;
    protected int valeur;*/
    private ArrayList<Mesure> listeMesures;

    public InformationsCapteur(String nom, ArrayList<Mesure> mes, LieuCapteur where) {
        this.type = nom;
        this.loc = where;
        this.listeMesures = mes;
    }

    public String getType() {
        return type;
    }


    public LieuCapteur getLocalisation() {
        return loc;
    }


    public ArrayList<Mesure> getListeMesures() {
        return listeMesures;
    }
}
