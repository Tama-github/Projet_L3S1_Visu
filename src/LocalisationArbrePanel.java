import javax.swing.*;

/**
 * Created by msi on 19/12/2016.
 */
public class LocalisationArbrePanel {
    private LocalisationArbre localisationArbre;
    private JPanel arbrePanel;
    private JTree arbre;

    public LocalisationArbrePanel () {
        this.localisationArbre = new LocalisationArbre();
        this.arbrePanel = new JPanel();
        //JScrollPane jScrollPane = new JScrollPane();
        this.arbre = new JTree (localisationArbre.getArbre());
        //jScrollPane.add(this.arbre);
        this.arbrePanel.add(this.arbre);
    }

    public JPanel getArbrePanel() {
        return arbrePanel;
    }

    public void addCapteurInt (String batiment, String etage, String salle, String id) {
        this.localisationArbre.addCapteurInt(batiment, etage, salle, id);
        this.arbre = new JTree(this.localisationArbre.getArbre());
    }

    public void addCapteurExt (double lat, double lon) {
        this.localisationArbre.addCapteurExt(lat, lon);
        this.arbre = new JTree(this.localisationArbre.getArbre());
    }

    public void removeCapteurInt (String id) {
        this.localisationArbre.removeCapteurInt(id, null);
        this.arbre = new JTree(this.localisationArbre.getArbre());
    }

    public void removeCapteurExt (double lat, double lon) {
        this.localisationArbre.removeCapteurExt(lat, lon);
        this.arbre = new JTree(this.localisationArbre.getArbre());
    }
}
