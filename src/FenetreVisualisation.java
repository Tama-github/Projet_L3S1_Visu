import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.io.FileNotFoundException;
import java.lang.InterruptedException;

import static javax.swing.Box.createHorizontalGlue;
import static javax.swing.Box.createRigidArea;
import static javax.swing.Box.createVerticalGlue;

/**
 * Created by msi on 20/12/2016.
 *
 * Credit icone Maxim Basinski
 */
public class FenetreVisualisation extends JFrame {

    private LocalisationArbrePanel localisationArbrePanel;
    private TableauDonnees tableauDonnees;
    private Alerte alerte;
    private ReceptionThread receptionThread;
    private ProtocolManager protocolManager;
    private FenetreConnexionIP fenetreConnexionIP;

    public FenetreVisualisation () {
        super("Visualisation");
        this.setIconImage(new ImageIcon("Icon.png").getImage());
        this.localisationArbrePanel = new LocalisationArbrePanel();
        this.alerte = new Alerte();
        this.tableauDonnees = new TableauDonnees(this.alerte);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(900, 650);
        this.setMinimumSize(new Dimension(900, 650));

        JButton inscription = new JButton("Inscrire InformationsCapteur(s)");
        JButton graphique = new JButton("Afficher le Graphique");
        JButton deconnection = new JButton("Déconnexion");

        JPanel panGeneral = new JPanel();
        JPanel hTreeTab = new JPanel();
        JPanel vTreeBouton = new JPanel();
        JPanel hAlerteBoutons = new JPanel();
        JPanel vBoutons = new JPanel();

        JPanel pGraphique = new JPanel();
        JPanel pDeconnexion = new JPanel();

        graphique.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                try {
                    if(e.getSource() == graphique){
                        Graphique nouveauGraphe = new Graphique();
                        nouveauGraphe.creationGraphique();
                    }
                }

                catch (FileNotFoundException err) {
                    System.out.println("2!");
                }

                catch(InterruptedException err) {
                    System.out.println("3!");
                }

            }
        });

        pGraphique.add(graphique);
        pDeconnexion.add(deconnection);

        panGeneral.setLayout(new BoxLayout(panGeneral, BoxLayout.Y_AXIS));
        hTreeTab.setLayout(new BoxLayout(hTreeTab, BoxLayout.X_AXIS));
        hAlerteBoutons.setLayout(new BoxLayout(hAlerteBoutons, BoxLayout.X_AXIS));
        vTreeBouton.setLayout(new BoxLayout(vTreeBouton, BoxLayout.Y_AXIS));
        vBoutons.setLayout(new BoxLayout(vBoutons, BoxLayout.Y_AXIS));


        tableauDonnees.getPanGlobal().setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(5), "Capteurs inscrits"));
        localisationArbrePanel.getArbrePanel().setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(5), "Capteurs connectés"));
        alerte.getPanGlobal().setBorder(BorderFactory.createTitledBorder(BorderFactory.createBevelBorder(5), "Alertes"));


        vTreeBouton.add(localisationArbrePanel.getArbrePanel());
        vTreeBouton.add(createRigidArea(new Dimension(5, 0)));
        vTreeBouton.add(inscription);
        vTreeBouton.add(createHorizontalGlue());

        hTreeTab.add(vTreeBouton);
        hTreeTab.add(createVerticalGlue());
        hTreeTab.add(tableauDonnees.getPanGlobal());

        vBoutons.add(pGraphique);
        vBoutons.add(pDeconnexion);

        hAlerteBoutons.add(alerte.getPanGlobal());
        hAlerteBoutons.add(createHorizontalGlue());
        hAlerteBoutons.add(vBoutons);

        panGeneral.add(hTreeTab);
        panGeneral.add(createHorizontalGlue());
        panGeneral.add(hAlerteBoutons);

        //this.fenetreConnexionIP.setIconImage(new ImageIcon("Icon.png").getImage());

        this.add(panGeneral);
        this.setVisible(false);

        inscription.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                try {
                    int j = 0;
                    System.out.println("**************inscription button deb*******************");
                    ArrayList<Capteur> aInscrire = new ArrayList<>();
                    ArrayList<Capteur> aDesinscrire = new ArrayList<>();
                    for (int i = 0; i < localisationArbrePanel.getSelectedItem().size(); i++) {
                        System.out.println(localisationArbrePanel.getSelectedItem().get(i).getNom());
                        j++;
                    }
                    if (j == 0) {
                        System.out.println("aucun item selectionné.");
                    }
                    for (int i = 0; i < localisationArbrePanel.getCapteurInscrit().size(); i++) {
                        System.out.println(localisationArbrePanel.getCapteurInscrit().get(i).getNom() + "------ taille tableau :" + localisationArbrePanel.getCapteurInscrit().size());
                        if (!localisationArbrePanel.getSelectedItem().contains(localisationArbrePanel.getCapteurInscrit().get(i))) {
                            aDesinscrire.add(localisationArbrePanel.getCapteurInscrit().get(i));
                            localisationArbrePanel.getCapteurInscrit().remove(localisationArbrePanel.getCapteurInscrit().get(i));
                            System.out.println("removed");
                            i--;
                        }
                    }
                    for (int i = 0; i < localisationArbrePanel.getSelectedItem().size(); i++) {
                        if (!localisationArbrePanel.getCapteurInscrit().contains(localisationArbrePanel.getSelectedItem().get(i))) {
                            aInscrire.add(localisationArbrePanel.getSelectedItem().get(i));
                            localisationArbrePanel.getCapteurInscrit().add(localisationArbrePanel.getSelectedItem().get(i));
                            System.out.println("addeda  zaqezfq");
                        }
                    }
                    protocolManager.desinscriptionCapteurs(aDesinscrire);
                    protocolManager.inscriptionCapteurs(aInscrire);
                } catch (IOException e1) {
                    //----
                }
                System.out.println("**************inscription button fin*******************");
            }
        });

        deconnection.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                try {
                    protocolManager.deconnexionVisu();
                    protocolManager.getServicesReseau().deconnexion();
                    setVisible(false);
                    fenetreConnexionIP.setVisible(true);
                    receptionThread.setRunning(false);
                    receptionThread.interrupt();
                    clearArbre();
                    tableauDonnees.removeAll();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });

        inscription.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                if ((e.getModifiers() & ActionEvent.CTRL_MASK) == ActionEvent.CTRL_MASK && !localisationArbrePanel.isCtrlPressed()) {
                    localisationArbrePanel.setCtrlPressed(true);
                }
            }

            @Override
            public void keyPressed(KeyEvent e) {
                if ((e.getModifiers() & ActionEvent.CTRL_MASK) == ActionEvent.CTRL_MASK && !localisationArbrePanel.isCtrlPressed()) {
                    localisationArbrePanel.setCtrlPressed(true);
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                localisationArbrePanel.setCtrlPressed(false);
            }
        });
    }

    public void setFenetreConnexionIP(FenetreConnexionIP fenetreConnexionIP) {
        this.fenetreConnexionIP = fenetreConnexionIP;
    }

    public void setProtocolManager(ProtocolManager protocolManager) {
        this.protocolManager = protocolManager;
        this.receptionThread = new ReceptionThread(protocolManager, this.localisationArbrePanel, tableauDonnees);
        this.receptionThread.start();
    }

    private void clearArbre () {
        System.out.println("bonjour");
        for (Map.Entry<String, Capteur> entry : localisationArbrePanel.getCapteurs().entrySet()) {
            System.out.println(entry.getValue().getType());
            if (entry.getValue().getLoc().getType().equals("Interieur")) {
                System.out.println("loli");
                this.localisationArbrePanel.removeCapteurInt(entry.getKey());
            } else if (entry.getValue().getLoc().getType().equals("Exterieur")) {
                System.out.println("lole");
                this.localisationArbrePanel.removeCapteurExt(Double.parseDouble(this.protocolManager.getFieldFromReceivedMessage(0, entry.getValue().getLocalisation())), Double.parseDouble(this.protocolManager.getFieldFromReceivedMessage(1, entry.getValue().getLocalisation())));
            }
        }
        this.localisationArbrePanel.getCapteurInscrit().removeAll(this.localisationArbrePanel.getCapteurInscrit());
        this.localisationArbrePanel.getSelectedItem().removeAll(this.localisationArbrePanel.getSelectedItem());
        this.localisationArbrePanel.getCapteurs().clear();
    }
}
