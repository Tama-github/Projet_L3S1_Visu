import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by msi on 19/12/2016.
 */
public class LocalisationArbrePanel {
    private LocalisationArbre localisationArbre;
    private JPanel arbrePanel;
    private JTree arbre;
    private HashMap<String, Capteur> capteurs;
    private ArrayList<Capteur> selectedItem = new ArrayList<>();
    private ArrayList<Capteur> capteurInscrit = new ArrayList<>();
    private boolean ctrlPressed = false;
    private  JScrollPane arbreScroll;

    public LocalisationArbrePanel () {

        this.capteurs = new HashMap<>();
        this.localisationArbre = new LocalisationArbre();
        this.arbrePanel = new JPanel();
        this.arbre = new JTree (this.localisationArbre.getArbre());
        this.arbre.getSelectionModel().setSelectionMode(TreeSelectionModel.DISCONTIGUOUS_TREE_SELECTION);

        arbreScroll = new JScrollPane(this.arbre);

        this.arbreScroll.setMinimumSize(new Dimension(1000, 150));
        this.arbre.setMinimumSize(new Dimension(1000, 150));

        this.arbreScroll.setMaximumSize(new Dimension(1000, 200));
        this.arbre.setMaximumSize(new Dimension(1000, 200));

        this.arbrePanel.setMinimumSize(new Dimension(200, 400));
        this.arbrePanel.setMaximumSize(new Dimension(300, 500));


        arbreScroll.setLayout(new ScrollPaneLayout());

        this.arbrePanel.add(arbreScroll, BorderLayout.CENTER);

        this.arbre.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                if (ctrlPressed) {
                    TreePath[] tp = e.getPaths();
                    for (int i = 0; i < tp.length; i++) {
                        getAllSelectedLeafs((DefaultMutableTreeNode) tp[i].getLastPathComponent());

                    }
                } else {
                    selectedItem.removeAll(selectedItem);
                    DefaultMutableTreeNode tmp = (DefaultMutableTreeNode) arbre.getLastSelectedPathComponent();
                    getAllSelectedLeafs(tmp);
                }
            }
        });

        this.arbre.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {
                if ((e.getModifiers() & ActionEvent.CTRL_MASK) == ActionEvent.CTRL_MASK && !ctrlPressed) {
                    ctrlPressed = true;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                ctrlPressed = false;
            }
        });
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

    private Capteur getCapteurForString (String infoCapteur) {
        boolean stop = false;
        Capteur res = null;
        for (int i = 0; i < infoCapteur.length() && !stop; i++) {
            if (infoCapteur.charAt(i) == ';') {
                stop = true;
            }
        }
        if (!stop) {
            res = this.capteurs.get(infoCapteur);
        } else {
            for (Map.Entry<String, Capteur> c : this.capteurs.entrySet()) {
                if (c.getValue().getLocalisation().equals(infoCapteur)) {
                    res = c.getValue();
                }
            }
        }
        return res;
    }

    private void getAllSelectedLeafs (DefaultMutableTreeNode node) {
        if (node == null) {
            return;
        }
        int n = node.getChildCount();
        if (n == 0) {
            if (!node.toString().equals("Interieur") && !node.toString().equals("Exterieur")) {
                this.selectedItem.add(this.getCapteurForString(node.toString()));
            }
        } else {
            for (int i = 0; i < n; i++) {
                this.getAllSelectedLeafs((DefaultMutableTreeNode) node.getChildAt(i));
            }
        }
    }

    public HashMap<String, Capteur> getCapteurs () {
        return this.capteurs;
    }

    public ArrayList<Capteur> getSelectedItem() {
        return selectedItem;
    }

    public ArrayList<Capteur> getCapteurInscrit() {
        return capteurInscrit;
    }
}
