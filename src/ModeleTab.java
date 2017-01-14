
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by jb on 21/12/16.
 */
public class ModeleTab extends AbstractTableModel {
    private Object[][] donnees;
    private String[] title;
    private Alerte alerte;
    private JTable tableau;

    public ModeleTab(Object[][] donnees, String[] title, Alerte alerte)
    {
        this.donnees = donnees;
        this.title = title;
        this.alerte = alerte;
    }

    @Override
    public String getColumnName(int colonne)
    {
        return this.title[colonne];
    }

    public int getColumnCount()
    {
        return this.title.length;
    }


    public int getRowCount()
    {
        return this.donnees.length;
    }


    public Object getValueAt(int ligne, int colonne)
    {
        return this.donnees[ligne][colonne];
    }

    @Override
    public void setValueAt(Object val, int ligne, int colonne)
    {
        if(!this.getColumnName(colonne).equals("Age") && !this.getColumnName(colonne).equals("Suppression")) {
            this.donnees[ligne][colonne] = val;
        }
    }

    @Override
    public Class getColumnClass(int colonne)
    {
        return this.donnees[0][colonne].getClass();
    }


    public void removeRow(int position){

        int indice = 0, indice2 = 0;
        int nbLigne = this.getRowCount()-1, nbColonne = this.getColumnCount();
        Object temp[][] = new Object[nbLigne][nbColonne];
        for(Object[] value : this.donnees){
            if(indice != position){
                temp[indice2++] = value;
             }
            indice++;
        }
        this.donnees = temp;
        temp = null;
        this.fireTableDataChanged();
    }

    public void addRow(Object[] donnees){
        int indice = 0, nbLigne = this.getRowCount(), nbColonne = this.getColumnCount();
        Object temp[][] = this.donnees;
        this.donnees = new Object[nbLigne + 1][nbColonne];

        for(Object[] value : temp)
            this.donnees[indice++] = value;

        this.donnees[indice] = donnees;
        temp = null;
        this.fireTableDataChanged();
    }

    public Color getRowColor(int row)
    {
        ArrayList<AlerteData> listeAlertes = alerte.getListeAlerte();
        int i;
        try {
            for (i = 0; i < listeAlertes.size(); i++) {
                AlerteData courant = listeAlertes.get(i);

                if (courant.getType().equals(tableau.getValueAt(row, 1))) {
                    if (!courant.getInferieurA().equals("") && !courant.getSuperieurA().equals("")) {
                        if (Double.parseDouble(tableau.getValueAt(row, 3).toString()) < Double.parseDouble(courant.getInferieurA()) || Double.parseDouble(tableau.getValueAt(row, 3).toString()) > Double.parseDouble(courant.getSuperieurA())) {
                            return Color.red;
                        }
                    } else if (!courant.getInferieurA().equals("") && courant.getSuperieurA().equals("")) {
                        if (Double.parseDouble(tableau.getValueAt(row, 3).toString()) < Double.parseDouble(courant.getInferieurA())) {
                            return Color.red;
                        }
                    } else if (courant.getInferieurA().equals("") && !courant.getSuperieurA().equals("")) {
                        if (Double.parseDouble(tableau.getValueAt(row, 3).toString()) > Double.parseDouble(courant.getSuperieurA())) {
                            return Color.red;
                        }
                    }
                }
            }
        }catch(NumberFormatException nfe)
        {
            return Color.white;
        }
        return Color.white;
    }

    public boolean isCellEditable(int ligne, int colonne){
        return false;
    }

    public void setTableau(JTable tableau) {
        this.tableau = tableau;
    }
}
