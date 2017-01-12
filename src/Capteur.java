/**
 * Created by jb on 19/12/16.
 */
public class Capteur{
    private String id;
    private String type;
    private Localisation localisation;

    Capteur(String id, String type, String typeLoc, double lat, double lon)
    {
        this.id = id;
        this.type = type;
        if (typeLoc.equals("Exterieur"))
        {
            try {
                localisation = new LocalisationExterieur("Exterieur", lat, lon);
            } catch (Exception jLang1) {
                jLang1.printStackTrace();
            }
        }
    }

    Capteur(String id, String type, String typeLoc, String batiment, String etage, String salle, String infoSup)
    {
        this.id = id;
        this.type = type;
        if (typeLoc.equals("Interieur"))
        {
            try {
                localisation = new LocalisationInterieur("Interieur", batiment, etage, salle, infoSup);
            } catch (Exception jLang1) {
                jLang1.printStackTrace();
            }
        }
    }

    public String getNom() {
        return id;
    }

    public String getLocalisation() {
        return localisation.getString();
    }

    public Localisation getLoc()
    {
        return localisation;
    }

    public String getType()
    {
        return this.type;
    }
}
