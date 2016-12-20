import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeModel;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by msi on 19/12/2016.
 */
public class LocalisationArbre {
    private DefaultMutableTreeNode arbre;

    public LocalisationArbre () {
        this.arbre = new DefaultMutableTreeNode("Localisation");
        this.arbre.add(new DefaultMutableTreeNode("Interieur"));
        this.arbre.add(new DefaultMutableTreeNode("Exterieur"));
    }

    public DefaultMutableTreeNode getArbre() {
        return arbre;
    }

    public void addCapteurInt (String batiment, String etage, String salle, String id) {
        DefaultMutableTreeNode c = (DefaultMutableTreeNode)this.arbre.getChildAt(0);
        DefaultMutableTreeNode tmp = c;

        //Ajout du batiment dans l'arbre
        boolean stop = false;
        int n = c.getChildCount();
        for (int i = 0; i < n && !stop; i++) {
            tmp = (DefaultMutableTreeNode)c.getChildAt(i);
            if (tmp.toString().equals(batiment)){
                stop = true;
            }
        }
        if (stop) {
            c = tmp;
        } else {
            tmp = new DefaultMutableTreeNode(batiment);
            c.add(tmp);
            c = tmp;
        }

        //Ajout de l'etage dans l'arbre
        stop = false;
        n = c.getChildCount();
        for (int i = 0; i < n && !stop; i++) {
            tmp = (DefaultMutableTreeNode)c.getChildAt(i);
            if (tmp.toString().equals(etage)){
                stop = true;
            }
        }
        if (stop) {
            c = tmp;
        } else {
            tmp = new DefaultMutableTreeNode(etage);
            c.add(tmp);
            c = tmp;
        }

        //Ajout de la salle dans l'arbre
        stop = false;
        n = c.getChildCount();
        for (int i = 0; i < n && !stop; i++) {
            tmp = (DefaultMutableTreeNode)c.getChildAt(i);
            if (tmp.toString().equals(salle)){
                stop = true;
            }
        }
        if (stop) {
            c = tmp;
        } else {
            tmp = new DefaultMutableTreeNode(salle);
            c.add(tmp);
            c = tmp;
        }

        //Ajout du capteur dans l'arbre
        stop = false;
        n = c.getChildCount();
        ArrayList<DefaultMutableTreeNode> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            tmp = (DefaultMutableTreeNode)c.getChildAt(i);
            if (stop || id.compareTo(tmp.toString()) <= 0){
                stop = true;
                list.add(tmp);
                c.remove(i);
                n--;
                i--;
            }
        }

        c.add(new DefaultMutableTreeNode(id));
        for (int i = 0; i < list.size(); i++) {
            c.add(list.get(i));
        }
    }

    public void addCapteurExt (double lat, double lon) {

    }
}
