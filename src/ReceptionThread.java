

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Thread qui gere l'envoi des donnees au serveur
 * @autor Ludovic BURG
 */
public class ReceptionThread extends Thread implements Runnable {
    private ProtocolManager protocolManager;
    private LocalisationArbrePanel localisationArbrePanel;
    private boolean running = true;

    public ReceptionThread(ProtocolManager protocolManager, LocalisationArbrePanel localisationArbrePanel) {
        this.protocolManager = protocolManager;
        this.localisationArbrePanel = localisationArbrePanel;
    }

    @Override
    public void run() {
        String recu;
        String type;
        Capteur tmp;
        try {
            while (this.running) {
                recu = this.protocolManager.receptionVisu();
                type = this.protocolManager.getTypeOfReceivedMessage(recu);
                System.out.println("message recu : " + recu);
                if (!recu.equals("pas connecte")) {
                    if (type.equals("CapteurPresent")) { /* Reception d'un message destiner a mettre a jour l'arbre */
                        if (!this.protocolManager.getFieldFromReceivedMessage(6, recu).equals(";erreur;")) {
                            tmp = new Capteur(
                                        this.protocolManager.getFieldFromReceivedMessage(1, recu),
                                        "interieur",
                                        this.protocolManager.getFieldFromReceivedMessage(3, recu),
                                        this.protocolManager.getFieldFromReceivedMessage(4, recu),
                                        this.protocolManager.getFieldFromReceivedMessage(5, recu),
                                        this.protocolManager.getFieldFromReceivedMessage(6, recu)
                                    );
                            this.localisationArbrePanel.getCapteurs().put(tmp.getNom(), tmp);
                            this.localisationArbrePanel.addCapteurInt(
                                    this.protocolManager.getFieldFromReceivedMessage(3, recu),
                                    this.protocolManager.getFieldFromReceivedMessage(4, recu),
                                    this.protocolManager.getFieldFromReceivedMessage(5, recu),
                                    this.protocolManager.getFieldFromReceivedMessage(1, recu)
                            );
                        } else {
                            tmp = new Capteur (
                                        this.protocolManager.getFieldFromReceivedMessage(1, recu),
                                        "exterieur",
                                        Double.parseDouble(this.protocolManager.getFieldFromReceivedMessage(3, recu)),
                                        Double.parseDouble(this.protocolManager.getFieldFromReceivedMessage(4, recu))
                                    );
                            this.localisationArbrePanel.getCapteurs().put(tmp.getNom(), tmp);
                            this.localisationArbrePanel.addCapteurExt(
                                    Double.parseDouble(this.protocolManager.getFieldFromReceivedMessage(3, recu)),
                                    Double.parseDouble(this.protocolManager.getFieldFromReceivedMessage(4, recu))
                            );
                        }
                    } else if (type.equals("InscriptionCapteurKO")) {
                        String id = "";
                        int i = 1;
                        boolean stop = false;
                        while (!id.equals(";erreur;")) {
                            this.protocolManager.getFieldFromReceivedMessage(i, recu);
                            for (int j = 0; i < this.localisationArbrePanel.getCapteurs().size() && !stop; j++) {
                                if (this.localisationArbrePanel.getCapteurs().get(j).getNom().equals(id)) {
                                    this.localisationArbrePanel.getCapteurs().remove(j);
                                    stop = true;
                                }
                            }
                            stop = true;
                            i++;
                        }
                        //remplir le tableau
                    } else if (type.equals("DesinscriptionCapteurKO")) {

                    } else if (type.equals("ValeurCapteur")) { /* Reception d'un message destiné a mettre à jour le tableau */

                    } else if (type.equals("CapteurDeco")) { /* reception d'un message destiné à mettre à jour l'arbre  */
                        String idCapteur = this.protocolManager.getFieldFromReceivedMessage(1, recu);
                        tmp = this.localisationArbrePanel.getCapteurs().get(idCapteur);
                        if (tmp.getType().equals("interieur")) {
                            this.localisationArbrePanel.removeCapteurInt(idCapteur);
                        } else if (tmp.getType().equals("exterieur")) {
                            this.localisationArbrePanel.removeCapteurExt(Double.parseDouble(this.protocolManager.getFieldFromReceivedMessage(0, tmp.getLocalisation())), Double.parseDouble(this.protocolManager.getFieldFromReceivedMessage(1, tmp.getLocalisation())));
                        }
                    }
                }
            }
            Thread.currentThread().interrupt();
        } catch (IOException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public boolean isRunning() {
        return running;
    }
}

