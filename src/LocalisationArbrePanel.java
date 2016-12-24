import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;

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
        this.reload();
    }

    public void addCapteurExt (double lat, double lon) {
        this.localisationArbre.addCapteurExt(lat, lon);
        this.reload();
    }

    public void removeCapteurInt (String id) {
        this.localisationArbre.removeCapteurInt(id, null);
        this.reload();
    }

    public void removeCapteurExt (double lat, double lon) {
        this.localisationArbre.removeCapteurExt(lat, lon);
        this.reload();
    }

    private void reload () {
        ((DefaultTreeModel)this.arbre.getModel()).setRoot(this.localisationArbre.getArbre());
        ((DefaultTreeModel)this.arbre.getModel()).reload();
        this.ettendreArbre(this.arbre, 0, this.arbre.getRowCount());
    }

    private void ettendreArbre(JTree arbre, int intex, int nb) {
        for (int i=intex; i < nb; i++) {
            arbre.expandRow(i);
        }

        if (arbre.getRowCount()!= nb) {
            ettendreArbre(arbre, nb, arbre.getRowCount());
        }
    }
}
