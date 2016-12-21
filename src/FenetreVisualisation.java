import javax.swing.*;

/**
 * Created by msi on 20/12/2016.
 */
public class FenetreVisualisation extends JFrame{

    private LocalisationArbrePanel localisationArbrePanel;

    public FenetreVisualisation () {
        super("Visualisation");
        this.localisationArbrePanel = new LocalisationArbrePanel();
        this.setSize(500, 500);

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.add(localisationArbrePanel.getArbrePanel());
        localisationArbrePanel.addCapteurInt("U3", "2eme", "209", "zae");
        localisationArbrePanel.addCapteurInt("U3", "2eme", "209", "azertyuiop");
        localisationArbrePanel.addCapteurInt("U3", "3eme", "308", "qsd");
        localisationArbrePanel.addCapteurInt("U2", "1er", "101", "nhj");
    }
}
