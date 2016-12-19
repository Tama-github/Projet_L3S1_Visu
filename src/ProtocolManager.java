import java.io.IOException;

/**
 * Created by msi on 19/12/2016.
 */
public class ProtocolManager {
    private ServicesReseau servicesReseau;
    private String ip;
    private int port;
    private String idVisu;

    public ProtocolManager (String ip, int port, String idVisu) {
        this.servicesReseau = new ServicesReseau();
        this.ip = ip;
        this.port = port;
        this.idVisu = idVisu;
    }

    public String connexionVisu () throws IOException {
        String res = "";
        if (!this.servicesReseau.estConnecte()) {
            this.servicesReseau.connexion(this.ip, this.port);
            this.servicesReseau.envoyer("ConnexionVisu;" + this.idVisu + "\n");
        } else {
            throw new IOException();
        }
        res = this.servicesReseau.recevoir();
        if (res.equals("ConnexionKO")) {
            this.servicesReseau.deconnexion();
        }
        return res;
    }

    public String deconnexionVisu () throws IOException {
        String res = "DeconnexionKO";
        if (this.servicesReseau.estConnecte()) {
            this.servicesReseau.envoyer("DeconnexionVisu\n");
            res = this.servicesReseau.recevoir();
            if (res.equals("DeconnexionOK")) {
                this.servicesReseau.deconnexion();
            } else {
                this.servicesReseau.deconnexion();
            }
        }
        return res;
    }

    /*reception de donnee a gèrer*/

}
