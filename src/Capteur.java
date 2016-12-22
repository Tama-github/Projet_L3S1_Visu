/**
 * Created by jb on 19/12/16.
 */
public class Capteur{
    private String nom;
    private double valeur;
    private String type;
    private Localisation localisation;
    private double latitude;
    private double longitude;
    private String batiment;
    private String etage;
    private String salle;

    Capteur(String nom, double val, String type, double lat, double lon)
    {
        this.nom = nom;
        this.valeur = val;
        this.type = type;
        this.longitude = lon;
        this.latitude = lat;
        this.batiment = null;
        this.etage = null;
        this.salle = null;
        if (type == "exterieur")
        {
            try {
                localisation = new LocalisationExterieur("exterieur", lat, lon);
            } catch (Exception jLang1) {
                jLang1.printStackTrace();
            }
        }
    }

    Capteur(String nom, double val, String type, String batiment, String etage, String salle)
    {
        this.nom = nom;
        this.valeur = val;
        this.type = type;
        this.batiment = batiment;
        this.etage = etage;
        this.salle = salle;
        if (this.type.equals("interieur"))
        {
            try {
                localisation = new LocalisationInterieur("interieur", batiment, etage, salle, null);
            } catch (Exception jLang1) {
                jLang1.printStackTrace();
            }
        }
    }

    public String getNom() {
        return nom;
    }

    public double getValeur() {
        return valeur;
    }

    public String getLocalisation() {
        return localisation.getStringForConnexion();
    }

    public String getType()
    {
        return localisation.getType();
    }
}
