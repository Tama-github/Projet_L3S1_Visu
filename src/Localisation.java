/**
 * Created by msi on 23/11/2016.
 */
public abstract class Localisation {

    private String type;
    public Localisation (String type) throws Exception{
        if (type.equals("Exterieur") || type.equals("Interieur")) {
            this.type = type;
        } else {
            throw new Exception("LieuCapteur exception : LieuCapteur inconnu.");
        }
    }

    public String getType() {
        return type;
    }

    /**
     *
     * @return String : chaine de caratères a envoyer sur le reseau
     */
    public abstract String getString();
}
