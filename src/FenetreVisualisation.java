import javax.swing.*;
import java.awt.*;

/**
 * Created by msi on 20/12/2016.
 */
public class FenetreVisualisation extends JFrame{

    private LocalisationArbrePanel localisationArbrePanel;
    private TableauDonnees tableauDonnees;
    private Alerte alerte;

    public FenetreVisualisation () {
        super("Visualisation");
        this.localisationArbrePanel = new LocalisationArbrePanel();
        this.tableauDonnees = new TableauDonnees();
        this.alerte = new Alerte();

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
    }
}
