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
    }

    public void setProtocolManager(ProtocolManager protocolManager) {
        this.protocolManager = protocolManager;
        this.receptionThread = new ReceptionThread(protocolManager, this.localisationArbrePanel);
        this.receptionThread.start();
    }
}
