import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import static javax.swing.Box.createVerticalBox;

/**
 * Created by jb on 19/12/16.
 */
public class TableauDonnees{

    private JTable tableau;
    private Object [][] backupDonnees = new Object[0][4];
    private Object[][] donnees = new Object[0][4];
    private JComboBox<String> comboType = new JComboBox<>();
    private JComboBox<String> comboLoc = new JComboBox<>();
    private JPanel pGlobal = new JPanel();
    private JScrollPane pTableau = new JScrollPane();
    private Alerte alerte;

    public TableauDonnees(Alerte alerte)
    {
        this.alerte = alerte;

        JLabel lType = new JLabel("Filtrer par type");
        JLabel lLoc = new JLabel("Filtrer par localisation");
        JPanel pType = new JPanel();
        JPanel pLoc = new JPanel();
        String[] titre = {"Nom","Type de donnees","Localisation","Valeur"};
        ModeleTab model = new ModeleTab(donnees, titre, alerte);
        tableau = new JTable(model);
        model.setTableau(tableau);

        tableau.setCellSelectionEnabled(false);
        tableau.setDefaultRenderer(Object.class, new RenduCell(model));
        pTableau.setLayout(new ScrollPaneLayout());
        pTableau.setViewportView(tableau);
        tableau.setFillsViewportHeight(true);
        tableau.setEnabled(false);

        pTableau.setMaximumSize(new Dimension(900, 250));
        pTableau.setMinimumSize(new Dimension(600, 150));
        pType.add(lType);
        pType.add(comboType);

        pLoc.add(lLoc);
        pLoc.add(comboLoc);
        pGlobal.setLayout(new BoxLayout(pGlobal, BoxLayout.Y_AXIS));
        pGlobal.add(pTableau);
        pGlobal.add(createVerticalBox());
        pGlobal.add(pType);
        pGlobal.add(pLoc);
        pGlobal.add(createVerticalBox());
        pGlobal.setVisible(true);

        remplirComboType();
        remplirComboLoc();

        comboType.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent comboTypeEvent) {
                comboLoc.setSelectedItem("Tout");
                filtrageTableau(comboLoc.getSelectedItem().toString(), comboType.getSelectedItem().toString());
            }
        });

        comboLoc.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent comboLocEvent) {
                comboType.setSelectedItem("Tout");
                filtrageTableau(comboLoc.getSelectedItem().toString(), comboType.getSelectedItem().toString());
            }
        });

        alerte.getAjouter().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent alerteAppliquerEvent) {
                super.mouseClicked(alerteAppliquerEvent);
                ((ModeleTab) tableau.getModel()).fireTableDataChanged();
            }
        });

        alerte.getSupprimer().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent alerteAnnulerEvent) {
                super.mouseClicked(alerteAnnulerEvent);
                ((ModeleTab) tableau.getModel()).fireTableDataChanged();
            }
        });

    }


    private void backup()
    {
        int i,j;
        backupDonnees = new Object[tableau.getRowCount()][4];
        for (i = 0; i < tableau.getRowCount(); i++)
        {
            for (j = 0; j < 4; j++) {
                backupDonnees[i][j] = tableau.getValueAt(i, j).toString();
            }
        }
    }


    private void filtrageTableau(String loc, String type)
    {
        removeAll();
        int i;
        if (!loc.equals("Tout") && type.equals("Tout"))
        {
            removeAll();
            for (i = 0; i < backupDonnees.length; i++)
            {
                if (backupDonnees[i][2].equals(loc))
                {
                    ajoutLigne(backupDonnees[i]);
                }
            }
        }
        else if(!type.equals("Tout") && loc.equals("Tout"))
        {
            removeAll();
            for (i = 0; i < backupDonnees.length; i++) {
                if (backupDonnees[i][1].equals(type)) {
                    ajoutLigne(backupDonnees[i]);
                }
            }
        }
        else
        {
            removeAll();
            for (i = 0; i < backupDonnees.length; i++) {
                ajoutLigne(backupDonnees[i]);
            }
        }
        ((ModeleTab) tableau.getModel()).fireTableDataChanged();
    }

    public JPanel getPanGlobal()
    {
        return pGlobal;
    }


    private void remplirComboType()
    {
        int i;
        String type[] = {"Tout","Température","Humidité","Luminosité", "Volume Sonore", "Consomation éclairage", "Eau froide", "Eau Chaude", "Vitesse vent", "Pression atmosphérique"};
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


    public void ajoutLigne(Object[] ligne)
    {
        ((ModeleTab) tableau.getModel()).addRow(ligne);
    }


    public void raffraichirDonnees()
    {
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

    public void supprimerLigne(int ligne)
    {
        ((ModeleTab) tableau.getModel()).removeRow(ligne);
        backup();
    }

    public void changerValeur(String idCapteur, String valeur)
    {
        int i;
        int nbLignes = tableau.getRowCount() - 1;
        //suppression de tous les capteurs non inscrit
        for (i = nbLignes; i >= 0; i--)
        {
            if (tableau.getValueAt(i, 0).toString().equals(idCapteur))
            {
                tableau.setValueAt(valeur, i, 3);
                raffraichirDonnees();
            }
        }
    }

    private boolean contient(ArrayList<Capteur> listeCapteurs, String valeur)
    {
        int i;
        for (i = 0; i < listeCapteurs.size(); i++)
        {
            if (listeCapteurs.get(i).getNom().equals(valeur))
            {
                return true;
            }
        }
        return false;
    }

    public void suppressionCapteursNonInscrits(ArrayList<Capteur> listeCapteurs)
    {
        int i;
        int nbLignes = tableau.getRowCount() - 1;
        //suppression de tous les capteurs non inscrit
        for (i = nbLignes; i >= 0; i--)
        {
            if (!contient(listeCapteurs, tableau.getValueAt(i, 0).toString()))
            {
                supprimerLigne(i);
            }
        }
    }

    public boolean existe(String capteur)
    {
        int i;
        int nbLignes = tableau.getRowCount() - 1;
        for (i = nbLignes; i >= 0; i--)
        {
            if (tableau.getValueAt(i, 0).toString().equals(capteur))
            {
                return true;
            }
        }
        return false;
    }

    public void ajoutListeCapteurs(ArrayList<Capteur> listeCapteurs)
    {
        Capteur capteurCourant;
        int i;
        for (i = 0; i < listeCapteurs.size(); i++) {
            if (!existe(listeCapteurs.get(i).getNom()))
            {
                Object[] ligne = new Object[4];
                capteurCourant = listeCapteurs.get(i);
                //{"Nom","Type de donnees","Interface.Localisation","Valeur"};
                ligne[0] = capteurCourant.getNom();
                ligne[1] = capteurCourant.getType();
                if (capteurCourant.getLoc().getType().equals("exterieur") || capteurCourant.getLoc().getType().equals("Exterieur")) {
                    ligne[2] = "Exterieur";
                } else {
                    ligne[2] = "Interieur";
                }
                ligne[3] = "";
                ajoutLigne(ligne);
            }
        }
        backup();
    }

}
