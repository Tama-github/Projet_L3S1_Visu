import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

/**
 * Created by jb on 19/12/16.
 */
public class TableauDonnees{

    private JTable tableau;
    private int nbLignes;
    private Object[][] donnees = new Object[0][4];
    private JComboBox<String> comboType = new JComboBox<>();
    private JComboBox<String> comboLoc = new JComboBox<>();
    private JPanel pGlobal = new JPanel();
    private JScrollPane pTableau = new JScrollPane();

    public TableauDonnees()
    {
        //super("Tableau");
     /*   this.setSize(400, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);*/

        JLabel lType = new JLabel("Filtrer par type");
        JLabel lLoc = new JLabel("Filtrer par localisation");
        JPanel pType = new JPanel();
        JPanel pLoc = new JPanel();
        JPanel panTableau = new JPanel();
        String[] titre = {"Nom","Type de donnees","Localisation","Valeur"};
        ModeleTab model = new ModeleTab(donnees, titre);
        tableau = new JTable(model);

        tableau.setCellSelectionEnabled(false);
        //tableau.setDefaultRenderer(Object.class, new RenduCell());
        /*tableau.setSize(400,50);
        pTableau.setSize(400, 100);*/
        tableau.setEnabled(false);
        pTableau.add(tableau);
        panTableau.add(pTableau);
        pType.add(lType);
        pType.add(comboType);

        pLoc.add(lLoc);
        pLoc.add(comboLoc);
        pGlobal.setLayout(new GridLayout(3,1));
        pGlobal.add(tableau);
        pGlobal.add(pType);
        pGlobal.add(pLoc);

        tableau.setVisible(true);
        pTableau.setVisible(true);
        panTableau.setVisible(true);
        pGlobal.setVisible(true);


        nbLignes = 0;
        remplirComboType();
        remplirComboLoc();
        ajoutTest();
    }

    public JPanel getPanGlobal()
    {
        return pGlobal;
    }

    private void remplirComboType()
    {
        int i;
        String type[] = {"Tout","Temperature","Humidite","Luminosite", "Volume Sonore", "Consommation éclairage", "Eau froide", "Eau chaude", "Vitesse vent", "Pression Atmosphérique"};
        for (i = 0; i < type.length; i++)
        {
            comboType.addItem(type[i]);
        }
    }

    public void remplirComboLoc()
    {
        int i;
        String[] loc = {"Tout", "Exterieur", "Interieur"};
        for (i = 0; i < loc.length; i++)
        {
            comboLoc.addItem(loc[i]);
        }
    }

    public void setAlert(boolean alert, int numLigne)
    {
        tableau.getCellRenderer(numLigne, 0).getTableCellRendererComponent(tableau, "a", false, false, numLigne, 0);
        ((ModeleTab) tableau.getModel()).fireTableDataChanged();
    }

    public void ajoutLigne(Object[] ligne)
    {
        ((ModeleTab) tableau.getModel()).addRow(ligne);
    }

    public void ajoutTest()
    {
        Object[] ligne = {"chauffage", "Temperature", "Exterieur", "5"};
        ajoutLigne(ligne);
        nbLignes++;
        ajoutLigne(new Object[] {"Lumiere", "Consommation eclairage", "Interieur", "25"});
        nbLignes++;
        ajoutLigne(new Object[] {"Vent","Vitesse vent","Exterieur", "50"});
        nbLignes++;
        ajoutLigne(new Object[] {"Pression","Pression atmospherique","Exterieur", "15"});
        nbLignes++;
        ((ModeleTab) tableau.getModel()).fireTableDataChanged();
    }


    public void removeAll()
    {
        for (int i = nbLignes; i >= 0; i--)
        {
            ((ModeleTab) tableau.getModel()).removeRow(i);
        }
        nbLignes = 0;
    }

}
