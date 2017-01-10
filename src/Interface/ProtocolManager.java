package Interface;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by msi on 19/12/2016.
 */
public class ProtocolManager {
    private ServicesReseau servicesReseau;
    private String ip;
    private int port;
    private String idVisu;

    public ProtocolManager (String ip, int port) {
        this.servicesReseau = new ServicesReseau();
        this.ip = ip;
        this.port = port;
        this.idVisu = "Visu_L3_Groupe3_ljbr";
    }

    public ProtocolManager () {
        this.servicesReseau = new ServicesReseau();
        this.idVisu = "Visu_L3_Groupe3_ljbr";
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
            /*res = this.servicesReseau.recevoir();
            if (res.equals("DeconnexionOK")) {
                this.servicesReseau.deconnexion();
            } else {
                this.servicesReseau.deconnexion();
            }*/
        }
        return res;
    }

    public String receptionVisu () throws IOException {
        return this.servicesReseau.recevoir();
    }

    public String getTypeOfReceivedMessage (String message) {
        int i = 0;
        String type = "";
        char c = message.charAt(0);
        while (c != ';' && i < message.length()) {
            type += c;
            i++;
            if (i < message.length())
                c = message.charAt(i);
        }

        return type;
    }

    public String getFieldFromReceivedMessage (int index, String message) {
        String res = ";erreur;";
        int i = 0;
        int j = 0;
        char c = 'a';
        if (index >= 0) {
            while (i < index) {
                if (j < message.length())
                    c = message.charAt(j);
                if (c == ';') {
                    i++;
                } else if (j >= message.length()) {
                    i = -1;
                    break;
                }
                j++;
            }
            if (i != -1) {
                res = "";
                c = message.charAt(j);
                while (c != ';' && j < message.length()) {
                    res += c;
                    j++;
                    if (j < message.length())
                        c = message.charAt(j);
                }
            }
        }
        return res;
    }

    public void inscriptionCapteurs (ArrayList<Capteur> capteurs) throws IOException {
        String envoie = "InscriptionCapteur";
        for (int i = 0; i < capteurs.size(); i++) {
            envoie += ";"+capteurs.get(i).getNom();
        }
        if (capteurs.size() > 0)
            servicesReseau.envoyer(envoie+"\n");
    }

    public void desinscriptionCapteurs (ArrayList<Capteur> capteurs) throws IOException {
        String envoie = "DesinscriptionCapteur";
        for (int i = 0; i < capteurs.size(); i++) {
            envoie += ";"+capteurs.get(i).getNom();
        }
        if (capteurs.size() > 0)
            servicesReseau.envoyer(envoie+"\n");
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public ServicesReseau getServicesReseau() {
        return servicesReseau;
    }
}
