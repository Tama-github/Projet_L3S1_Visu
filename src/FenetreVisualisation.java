import sun.awt.AWTIcon32_java_icon16_png;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import static javax.swing.Box.createHorizontalGlue;
import static javax.swing.Box.createRigidArea;
import static javax.swing.Box.createVerticalGlue;

/**
 * Created by msi on 20/12/2016.
 */
public class FenetreVisualisation extends JFrame{

    private LocalisationArbrePanel localisationArbrePanel;
    private TableauDonnees tableauDonnees;
    private Alerte alerte;
    private ReceptionThread receptionThread;
    private ProtocolManager protocolManager;

    public FenetreVisualisation () {
        super("Visualisation");
        this.localisationArbrePanel = new LocalisationArbrePanel();
        this.alerte = new Alerte();
        this.tableauDonnees = new TableauDonnees(this.alerte);

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(600, 450);
        this.setMinimumSize(new Dimension(600, 450));

        JButton inscription = new JButton("Inscrire capteur(s)");
        JButton graphique = new JButton("Afficher graphique");
        JButton deconnection = new JButton("Déconnexion");

        JPanel panGeneral = new JPanel();
        JPanel hTreeTab = new JPanel();
        JPanel vTreeBouton = new JPanel();
        JPanel hAlerteBoutons = new JPanel();
        JPanel vBoutons = new JPanel();

        panGeneral.setLayout(new BoxLayout(panGeneral, BoxLayout.Y_AXIS));
        hTreeTab.setLayout(new BoxLayout(hTreeTab, BoxLayout.X_AXIS));
        hAlerteBoutons.setLayout(new BoxLayout(hAlerteBoutons, BoxLayout.X_AXIS));
        vBoutons.setLayout(new BoxLayout(vBoutons, BoxLayout.Y_AXIS));
        vTreeBouton.setLayout(new BoxLayout(vTreeBouton, BoxLayout.Y_AXIS));

        vTreeBouton.add(localisationArbrePanel.getArbrePanel());
        vTreeBouton.add(createRigidArea(new Dimension(5,0)));
        vTreeBouton.add(inscription);
        vTreeBouton.add(createVerticalGlue());
        vTreeBouton.setAlignmentX(5);

        hTreeTab.add(vTreeBouton);
        hTreeTab.add(createVerticalGlue());
        hTreeTab.add(tableauDonnees.getPanGlobal());

        vBoutons.add(createVerticalGlue());
        vBoutons.add(createHorizontalGlue());
        vBoutons.add(graphique);
        vBoutons.add(deconnection);
        vBoutons.add(createHorizontalGlue());
        vBoutons.add(createVerticalGlue());

        hAlerteBoutons.add(alerte.getPanGlobal());
        hAlerteBoutons.add(createHorizontalGlue());
        hAlerteBoutons.add(vBoutons);

        panGeneral.add(hTreeTab);
        panGeneral.add(createHorizontalGlue());
        panGeneral.add(hAlerteBoutons);

        this.add(panGeneral);
        this.setVisible(true);

        inscription.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                try {
                    ArrayList<Capteur> aInscrire = new ArrayList<>();
                    ArrayList<Capteur> aDesinscrire = new ArrayList<>();
                    for (int i = 0; i < localisationArbrePanel.getCapteurInscrit().size(); i++) {
                        if (!localisationArbrePanel.getSelectedItem().contains(localisationArbrePanel.getCapteurInscrit().get(i))) {
                            aDesinscrire.add(localisationArbrePanel.getCapteurInscrit().get(i));
                            localisationArbrePanel.getCapteurInscrit().remove(localisationArbrePanel.getCapteurInscrit().get(i));
                        }
                    }
                    for (int i = 0; i < localisationArbrePanel.getSelectedItem().size(); i++) {
                        if (!localisationArbrePanel.getCapteurInscrit().contains(localisationArbrePanel.getSelectedItem().get(i))) {
                            aInscrire.add(localisationArbrePanel.getSelectedItem().get(i));
                            localisationArbrePanel.getCapteurInscrit().add(localisationArbrePanel.getSelectedItem().get(i));
                        }
                    }
                    protocolManager.desinscriptionCapteurs(aDesinscrire);
                    protocolManager.inscriptionCapteurs(aInscrire);
                } catch (IOException e1) {
                    //----
                }
            }
        });
    }

    public void setProtocolManager(ProtocolManager protocolManager) {
        this.protocolManager = protocolManager;
        this.receptionThread = new ReceptionThread(protocolManager, this.localisationArbrePanel, tableauDonnees);
        this.receptionThread.start();
    }
}
