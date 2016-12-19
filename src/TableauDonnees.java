import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;

/**
 * Created by jb on 19/12/16.
 */
public class TableauDonnees extends AbstractTableModel{

    private JTable tableau = new JTable();
    private int nbCapteurs;
    private ArrayList listeCapteurs = new ArrayList();

    public TableauDonnees()
    {
        String ligne1[] = {"Nom", "Type de données", "Localisation", "Valeur"};
        JScrollPane panelTableau = new JScrollPane();


        panelTableau.add(tableau);
    }

    public Object getValueAt(int idL, int idC)
    {
        return null;
    }


    public int getnbCapteurs()
    {
        return nbCapteurs;
    }

    public String getColumnName(int colonne)
    {
        switch (colonne)
        {
            case 0: return new String("Nom");
            case 1: return new String("Type de donnees");
            case 2: return new String("Localisation");
            case 3: return new String("Valeur");
            default: return null;
        }
    }

    public int getRowCount()
    {
        return this.getnbCapteurs();
    }

    public int getColumnCount()
    {
        return 4;
    }

}
