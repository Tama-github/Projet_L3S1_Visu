import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by jb on 19/12/16.
 */
public class TableauDonnees{

    private JTable tableau;
    private Object[][] donnees = new Object[0][4];
    private JComboBox<String> comboType = new JComboBox<>();
    private JComboBox<String> comboLoc = new JComboBox<>();
    private JPanel pGlobal = new JPanel();
    private JScrollPane pTableau = new JScrollPane();
    private int minAlerte;
    private int maxAlerte;

    public TableauDonnees()
    {
        JLabel lType = new JLabel("Filtrer par type");
        JLabel lLoc = new JLabel("Filtrer par localisation");
        JPanel pType = new JPanel();
        JPanel pLoc = new JPanel();
        String[] titre = {"Nom","Type de donnees","Localisation","Valeur"};
        ModeleTab model = new ModeleTab(donnees, titre);
        tableau = new JTable(model);

        tableau.setCellSelectionEnabled(false);
        tableau.setDefaultRenderer(Object.class, new RenduCell());
        pTableau.setLayout(new ScrollPaneLayout());
        pTableau.setViewportView(tableau);
        tableau.setFillsViewportHeight(true);
        tableau.setEnabled(false);
        pTableau.setSize(new Dimension(100,100));
        pType.add(lType);
        pType.add(comboType);

        pLoc.add(lLoc);
        pLoc.add(comboLoc);
        pGlobal.setLayout(new BoxLayout(pGlobal, BoxLayout.Y_AXIS));
        pGlobal.add(pTableau);
        pGlobal.add(pType);
        pGlobal.add(pLoc);
        pGlobal.setVisible(true);

        remplirComboType();
        remplirComboLoc();
        ajoutTest();
        //setAlert(true, 3);

        comboType.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent itemEvent) {
                comboLoc.setSelectedItem("Tout");
            }
        });

        comboLoc.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent itemEventa) {
                comboType.setSelectedItem("Tout");
            }
        });


    }

    public JPanel getPanGlobal()
    {
        return pGlobal;
    }

    private void remplirComboType()
    {
        int i;
        String type[] = {"Tout","Température","Humidité","Luminosité", "Volume Sonore", "Consommation éclairage", "Eau froide", "Eau chaude", "Vitesse vent", "Pression Atmosphérique"};
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
        tableau.getCellRenderer(numLigne, 0).getTableCellRendererComponent(tableau, numLigne, false, false, numLigne, 0);
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
        ajoutLigne(new Object[] {"Lumiere", "Consommation eclairage", "Interieur", "25"});
        ajoutLigne(new Object[] {"Vent","Vitesse vent","Exterieur", "50"});
        ajoutLigne(new Object[] {"Pression","Pression atmospherique","Exterieur", "15"});
        ((ModeleTab) tableau.getModel()).fireTableDataChanged();
    }


    public void removeAll()
    {
        int nbLignes = tableau.getRowCount() - 1;
        for (int i = nbLignes; i >= 0; i--)
        {
            ((ModeleTab) tableau.getModel()).removeRow(i);
        }
    }

    public int getMinAlerte() {
        return minAlerte;
    }

    public int getMaxAlerte() {
        return maxAlerte;
    }

    public void setMinAlerte(int minAlerte) {
        this.minAlerte = minAlerte;
    }

    public void setMaxAlerte(int maxAlerte) {
        this.maxAlerte = maxAlerte;
    }
}
