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

    //permet de faire une sauvegarde des valeurs du tableau, utile pour filtrer le tableau par exemple
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

    //Permet de filtrer les valeurs du tableau en fonction du type ou de la localisation
    private void filtrageTableau(String loc, String type)
    {
        removeAll();
        int i;
        if (!loc.equals("Tout") && type.equals("Tout"))
        {
            removeAll();
            for (i = 0; i < backupDonnees.length; i++)
            {
                if ((backupDonnees[i][2].toString()).equalsIgnoreCase(loc))
                {
                    ajoutLigne(backupDonnees[i]);
                }
            }
        }
        else if(!type.equals("Tout") && loc.equals("Tout"))
        {
            removeAll();
            for (i = 0; i < backupDonnees.length; i++) {
                String typeCourant = backupDonnees[i][1].toString();
                if (typeCourant.equalsIgnoreCase("Consomation éclairage"))
                {
                    typeCourant = "Consommation éclairage";
                }
                if (typeCourant.equalsIgnoreCase(type)) {
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

    //Initialise la comboBox comboType
    private void remplirComboType()
    {
        int i;
        String type[] = {"Tout","Température","Humidité","Luminosité", "Volume Sonore", "Consommation éclairage", "Eau froide", "Eau Chaude", "Vitesse vent", "Pression atmosphérique"};
        for (i = 0; i < type.length; i++)
        {
            comboType.addItem(type[i]);
        }
    }

    //Initialise la comboBox comboLoc
    public void remplirComboLoc()
    {
        int i;
        String[] loc = {"Tout", "Exterieur", "Interieur"};
        for (i = 0; i < loc.length; i++)
        {
            comboLoc.addItem(loc[i]);
        }
    }

    //permet d'ajouter une ligne à mon tableau
    public void ajoutLigne(Object[] ligne)
    {
        ((ModeleTab) tableau.getModel()).addRow(ligne);
    }


    //permet de rafraichir mon taleau en cas de modification de celui-ci
    public void raffraichirDonnees()
    {
        ((ModeleTab) tableau.getModel()).fireTableDataChanged();
    }


    //Vide l'integralité du tableau
    public void removeAll()
    {
        int nbLignes = tableau.getRowCount() - 1;
        for (int i = nbLignes; i >= 0; i--)
        {
            ((ModeleTab) tableau.getModel()).removeRow(i);
        }
    }

    //Permet de supprimer une ligne
    public void supprimerLigne(int ligne)
    {
        ((ModeleTab) tableau.getModel()).removeRow(ligne);
        backup();
    }

    //Permet de changer la valeur d'un capteur déjà présent dans le tableau
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

    //Determine si un capteur se trouve dans ma liste de capteurs
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

    //Permet de supprimer les capteurs non présent dans la liste listeCapteurs
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

    //Determine si un capteur est présent dans le tableau
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

    //Permet d'ajouter tous les capteurs non déjà présent de la liste en parametre dans le tableau
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
