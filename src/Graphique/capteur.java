package Graphique;

import java.util.ArrayList;

public class capteur {

    private String type;
    private localisation loc;
    /*protected String typeUnite;
    protected String unite;
    protected int valeur;*/
    private ArrayList<mesure> listeMesures;

    public capteur (String nom, ArrayList<mesure> mes, localisation where) {
        this.type = nom;
        this.loc = where;
        this.listeMesures = mes;
    }

    public String getType() {
        return type;
    }


    public localisation getLocalisation() {
        return loc;
    }


    public ArrayList<mesure> getListeMesures() {
        return listeMesures;
    }
}
