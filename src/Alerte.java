/**
 * Created by jb on 21/12/16.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Alerte extends JFrame {

    private JFormattedTextField min = new JFormattedTextField();
    private JFormattedTextField max = new JFormattedTextField();
    private JComboBox typeDonnees = new JComboBox();
    private JButton appliquer = new JButton("Appliquer");
    private JLabel erreur = new JLabel();
    private ArrayList<String> comboListe = new ArrayList<>();
    private JPanel pGlobal = new JPanel();

    public Alerte() {

        JLabel lType = new JLabel("Type de données : ");
        JLabel lMin = new JLabel("Min : ");
        JLabel lMax = new JLabel("Max : ");

        JPanel pType = new JPanel();
        JPanel pMin = new JPanel();
        JPanel pMax = new JPanel();
        JPanel pAppliquer = new JPanel();
        JPanel pErreur = new JPanel();

        min.setColumns(10);
        max.setColumns(10);

        pType.add(lType);
        pType.add(typeDonnees);

        pMin.add(lMin);
        pMin.add(min);

        pMax.add(lMax);
        pMax.add(max);

        pAppliquer.add(appliquer);

        pErreur.add(erreur);

        pGlobal.add(pType);
        pGlobal.add(pMin);
        pGlobal.add(pMax);
        pGlobal.add(pAppliquer);
        pGlobal.add(pErreur);

        this.add(pGlobal);

        remplirListeType();
        setVisible(true);


        appliquer.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent appliMouse) {
                super.mouseClicked(appliMouse);
                int minVal;
                int maxVal;
                minVal = Integer.valueOf(min.getText());
                maxVal = Integer.valueOf(max.getText());
                if (verifInterval(minVal, maxVal))
                {
                    envoieDonneesAlerte(minVal, maxVal, typeDonnees.toString());
                }

            }
        });

    }

    public JPanel getPanGlobal()
    {
        return pGlobal;
    }

    private void setErreur(boolean isErreur)
    {
        if (isErreur) {
            erreur.setText("Erreur : Min doit être inferieur que max");
            erreur.setForeground(Color.red);
        }
        else
        {
            erreur.setText("");
            erreur.setForeground(Color.black);
        }
    }

    private void remplirListeType()
    {
        int i;
        comboListe.add("Température");
        comboListe.add("Humidité");
        comboListe.add("Luminosité");
        comboListe.add("Volume Sonore");
        comboListe.add("Consomation éclairage");
        comboListe.add("Eau froide");
        comboListe.add("Eau Chaude");
        comboListe.add("Vitesse vent");
        comboListe.add("Pression atmosphérique");

        for (i = 0; i < comboListe.size(); i++)
        {
            typeDonnees.addItem(comboListe.get(i));
        }

    }


    public void envoieDonneesAlerte(int min, int max, String typeDonnees)
    {

    }

     private boolean verifInterval(int min, int max)
     {
         if (max < min)
         {
             setErreur(true);
             return false;
         }
         else
         {
             setErreur(false);
             return true;
         }
     }

     public JPanel getpGlobal()
     {
         return pGlobal;
     }

}
