/**
 * Created by jb on 21/12/16.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Alerte{

    private JFormattedTextField min = new JFormattedTextField();
    private JFormattedTextField max = new JFormattedTextField();
    private JComboBox typeDonnees = new JComboBox();
    private JButton appliquer = new JButton("Appliquer");
    private JLabel erreur = new JLabel();
    private ArrayList<String> comboListe = new ArrayList<>();
    private JPanel pGlobal = new JPanel();
    private Boolean isOkay = false;
    private JButton annuler = new JButton("Annuler");

    public Alerte() {

        JLabel lType = new JLabel("Type de données : ");
        JLabel lMin = new JLabel("Min : ");
        JLabel lMax = new JLabel("Max : ");

        JPanel pType = new JPanel();
        JPanel pMin = new JPanel();
        JPanel pMax = new JPanel();
        JPanel pAppliquer = new JPanel();
        JPanel pErreur = new JPanel();
        JPanel pAnnuler = new JPanel();

        min.setColumns(10);
        max.setColumns(10);

        pAnnuler.add(annuler);

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
        pGlobal.add(pAnnuler);
        pGlobal.add(pErreur);


        remplirListeType();


        annuler.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent annulerEvent) {
                super.mouseClicked(annulerEvent);
                isOkay = false;
            }
        });


        appliquer.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent appliMouse) {
                super.mouseClicked(appliMouse);
                int minVal;
                int maxVal;
                if ((min.getText().equals("") && (max.getText().equals(""))))
                {
                    isOkay = false;
                }
                else
                {
                    try {
                        minVal = Integer.valueOf(min.getText());
                        maxVal = Integer.valueOf(max.getText());

                        isOkay = verifInterval(minVal, maxVal);
                    }catch(java.lang.NumberFormatException nFe)
                    {
                        setErreur(true, "Erreur : Les valeurs doivent être des chiffres");
                    }
                }
            }
        });

    }

    public JPanel getPanGlobal()
    {
        return pGlobal;
    }

    private void setErreur(boolean isErreur, String msg)
    {
        if (isErreur) {
            erreur.setText(msg);
            erreur.setForeground(Color.red);
        }
        else
        {
            erreur.setText("");
            erreur.setForeground(Color.black);
        }
    }

    public boolean isOkay()
    {
        return isOkay;
    }

    private void remplirListeType()
    {
        int i;
        comboListe.add("Température");
        comboListe.add("Humidité");
        comboListe.add("Luminosité");
        comboListe.add("Volume Sonore");
        comboListe.add("Consommation éclairage");
        comboListe.add("Eau froide");
        comboListe.add("Eau Chaude");
        comboListe.add("Vitesse vent");
        comboListe.add("Pression atmosphérique");

        for (i = 0; i < comboListe.size(); i++)
        {
            typeDonnees.addItem(comboListe.get(i));
        }

    }



    public Double getMin() {
        return Double.valueOf(min.getText());
    }

    public Double getMax() {
        return Double.valueOf(max.getText());
    }

    public String getTypeDonnees() {
        return typeDonnees.getSelectedItem().toString();
    }

    public JButton getAppliquer() {
        return appliquer;
    }

    private boolean verifInterval(int min, int max)
     {
         if (max < min)
         {
             setErreur(true, "Erreur : La valeur max doit être supérieur à min");
             return false;
         }
         else
         {
             setErreur(false, "");
             return true;
         }
     }

    public JButton getAnnuler() {
        return annuler;
    }

    public JPanel getpGlobal()
     {
         return pGlobal;
     }

}
