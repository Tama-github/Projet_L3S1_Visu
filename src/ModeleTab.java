import javax.swing.table.AbstractTableModel;
import java.awt.*;

/**
 * Created by jb on 21/12/16.
 */
public class ModeleTab extends AbstractTableModel {
    private Object[][] donnees;
    private String[] title;

    public ModeleTab(Object[][] donnees, String[] title)
    {
        this.donnees = donnees;
        this.title = title;
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
        return Color.white;
    }

    public boolean isCellEditable(int ligne, int colonne){
        return false;
    }
}
