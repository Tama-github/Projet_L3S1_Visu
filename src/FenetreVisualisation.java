import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by msi on 20/12/2016.
 */
public class FenetreVisualisation extends JFrame{

    private LocalisationArbrePanel localisationArbrePanel;
    private TableauDonnees tableauDonnees;
    private Alerte alerte;
    private ArrayList<Capteur> capteurs;
    private ReceptionThread receptionThread;
    private ProtocolManager protocolManager;

    public FenetreVisualisation () {
        super("Visualisation");
        this.localisationArbrePanel = new LocalisationArbrePanel();
        this.tableauDonnees = new TableauDonnees();
        this.alerte = new Alerte();
        this.capteurs = new ArrayList<>();

        this.setSize(700, 400);
        JPanel panGeneral = new JPanel();
        panGeneral.setLayout(new GridLayout(2,2));
        panGeneral.add(localisationArbrePanel.getArbrePanel());
        panGeneral.add(tableauDonnees.getPanGlobal());
        panGeneral.add(alerte.getPanGlobal());

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.add(panGeneral);
        localisationArbrePanel.addCapteurInt("U3", "2eme", "209", "zae");
        localisationArbrePanel.addCapteurInt("U3", "2eme", "209", "azertyuiop");
        localisationArbrePanel.addCapteurInt("U3", "3eme", "308", "qsd");
        localisationArbrePanel.addCapteurInt("U2", "1er", "101", "nhj");

        localisationArbrePanel.addCapteurExt(-12.123, 15.154);
        localisationArbrePanel.addCapteurExt(-13.25, 0);
        localisationArbrePanel.addCapteurExt(-12.123,21.0);

        localisationArbrePanel.removeCapteurInt("nhj");
        localisationArbrePanel.removeCapteurExt(-12.123, 21.0);
    }
}
