import javax.swing.*;

/**
 * Created by msi on 19/12/2016.
 */
public class Main {
    public static void main (String args []) {
        LocalisationArbrePanel localisationArbrePanel = new LocalisationArbrePanel();
        JFrame main = new JFrame("Visualisation");
        main.setSize(500,500);

        main.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        main.add(localisationArbrePanel.getArbrePanel());
        main.setVisible(true);
        localisationArbrePanel.addCapteurInt("U3", "2eme", "209", "zae");
        localisationArbrePanel.addCapteurInt("U3", "2eme", "209", "azertyuiop");
        localisationArbrePanel.addCapteurInt("U3", "3eme", "308", "qsd");
        localisationArbrePanel.addCapteurInt("U2", "1er", "101", "nhj");
    }
}
