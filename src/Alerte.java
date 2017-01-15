/**
 * Created by jb on 21/12/16.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import static javax.swing.Box.createHorizontalGlue;
import static javax.swing.Box.createVerticalGlue;

public class Alerte{

    private JFormattedTextField inferieurA = new JFormattedTextField();
    private JFormattedTextField superieurA = new JFormattedTextField();
    private JComboBox typeDonnees = new JComboBox();
    private JButton ajouter = new JButton("Ajouter");
    private JLabel erreur = new JLabel();
    private ArrayList<String> comboListe = new ArrayList<>();
    private JButton supprimer = new JButton("Supprimer");
    private JComboBox comboAlertes =  new JComboBox();
    private ArrayList<AlerteData> listeAlerte = new ArrayList<>();
    private JPanel pAjoutGen = new JPanel();
    private JPanel pSupprGen = new JPanel();

    public Alerte() {

        comboAlertes.addItem("Aucune Alerte");

        JLabel lType = new JLabel("Type de données : ");
        JLabel lMin = new JLabel("Inférieur à : ");
        JLabel lMax = new JLabel("Supérieur à : ");
        JLabel lAlertes = new JLabel("Alertes");

        JPanel pType = new JPanel();
        JPanel pMin = new JPanel();
        JPanel pMax = new JPanel();
        JPanel pAppliquer = new JPanel();
        JPanel pErreur = new JPanel();
        JPanel pAnnuler = new JPanel();
        JPanel pActionBouton = new JPanel();
        JPanel pAlertes = new JPanel();


        pActionBouton.setLayout(new BoxLayout(pActionBouton, BoxLayout.X_AXIS));

        pSupprGen.setLayout(new BoxLayout(pSupprGen, BoxLayout.X_AXIS));

        pAjoutGen.setLayout(new BoxLayout(pAjoutGen, BoxLayout.Y_AXIS));
        pAjoutGen.setAlignmentX(0);
        inferieurA.setColumns(10);
        superieurA.setColumns(10);

        pAlertes.add(lAlertes);
        pAlertes.add(comboAlertes);

        pAnnuler.add(supprimer);

        pType.add(lType);
        pType.add(typeDonnees);

        pMin.add(lMin);
        pMin.add(inferieurA);

        pMax.add(lMax);
        pMax.add(superieurA);

        pAppliquer.add(ajouter);

        pErreur.add(erreur);

        pActionBouton.add(createHorizontalGlue());
        pActionBouton.add(createHorizontalGlue());
        pActionBouton.add(createHorizontalGlue());
        pActionBouton.add(pAnnuler);
        pActionBouton.add(pAppliquer);
        pActionBouton.add(createHorizontalGlue());
        pActionBouton.add(createHorizontalGlue());
        pActionBouton.add(createHorizontalGlue());

        pSupprGen.add(pAlertes);
        pSupprGen.add(pAnnuler);

        pAjoutGen.add(createVerticalGlue());
        pAjoutGen.add(pType);
        pAjoutGen.add(pMin);
        pAjoutGen.add(pMax);
        pAjoutGen.add(pAppliquer);
        pAjoutGen.add(pErreur);
        pAjoutGen.add(createVerticalGlue());

        pAjoutGen.setMaximumSize(new Dimension(300, 500));

        remplirListeType();


        supprimer.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent annulerEvent) {
                super.mouseClicked(annulerEvent);
                if(listeAlerte.size() > 0) {
                    boolean supressionOk;
                    supressionOk = listeAlerte.remove(parseChaineComboBoxToChaineListe(comboAlertes.getSelectedItem().toString()));
                    comboAlertes.removeAllItems();
                    remplirComboListe();
                }
                if (listeAlerte.size() == 0)
                {
                    comboAlertes.removeAllItems();
                    comboAlertes.addItem("Aucune Alerte");
                }
            }
        });


        ajouter.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent appliMouse) {
                super.mouseClicked(appliMouse);
                if (!inferieurA.getText().equals("") || !superieurA.getText().equals("")) {
                    AlerteData nouveau = new AlerteData(typeDonnees.getSelectedItem().toString(), inferieurA.getText(), superieurA.getText());
                    if (!listeAlerte.contains(nouveau)) {
                        if (coherenceAjout(nouveau)) {
                            if (!inferieurA.getText().equals("") && !superieurA.getText().equals(""))
                            {
                                if (verifInterval(Double.parseDouble(inferieurA.getText()), Double.parseDouble(superieurA.getText())))
                                {
                                    setErreur(false, "");
                                    listeAlerte.add(nouveau);
                                }
                                else
                                {
                                    setErreur(true, "Inferieur à doit être plus petit que supérieur à !");
                                    comboAlertes.removeAllItems();
                                    comboAlertes.addItem("Aucune Alerte");
                                }
                            }
                            else
                            {
                                listeAlerte.add(nouveau);
                            }
                        }
                        else
                        {
                            JDialog erreurAlertes = new JDialog();
                            Object[] options = {"Oui", "Non"};
                            int res = JOptionPane.showOptionDialog(erreurAlertes, "Il est impossible de definir cette alerte car cette borne est déjà utilisée dans une autre alerte.\n " + "Voulez vous remplacer l'ancienne par la nouvelle ?", "Erreur alertes", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE, null, options, options[0]);
                            if (res == 0)
                            {
                                //supprimer ancienne alerte
                                if (!inferieurA.getText().equals("") && !superieurA.getText().equals("")) {
                                    if (verifInterval(Double.parseDouble(inferieurA.getText()), Double.parseDouble(superieurA.getText()))) {
                                        supprimerIncoherence(nouveau);
                                        listeAlerte.add(nouveau);
                                        comboAlertes.removeAllItems();
                                        remplirComboListe();
                                    }
                                    else
                                    {
                                        setErreur(true, "Inferieur à doit être plus petit que supérieur à !");
                                        comboAlertes.removeAllItems();
                                        comboAlertes.addItem("Aucune Alerte");
                                    }
                                }
                                else
                                {
                                    supprimerIncoherence(nouveau);
                                    listeAlerte.add(nouveau);
                                    comboAlertes.removeAllItems();
                                    remplirComboListe();
                                }
                            }
                        }
                    }
                }
                comboAlertes.removeAllItems();
                remplirComboListe();
                if (listeAlerte.size() == 0)
                {
                    comboAlertes.addItem("Aucune Alerte");
                }
            }
        });

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


    public boolean coherenceAjout(AlerteData nouveau)
    {
        int i;
        ArrayList<AlerteData> sousListe = new ArrayList<>();
        AlerteData courant;
        //construction d'une sous liste contenant toutes les alertes du même type que nouveau
        for (i = 0; i < listeAlerte.size(); i++)
        {
            courant = listeAlerte.get(i);
            if (courant.getType().equals(nouveau.getType()))
            {
                sousListe.add(courant);
            }
        }

        //si ma sous liste est vide -> coherent car le type de nouveau est inédit dans ma liste d'alerte
        if (sousListe.size() == 0)
        {
            return true;
        }
        else
        {
            for (i = 0; i < sousListe.size(); i++)
            {
                courant = sousListe.get(i);
                if ((!courant.getInferieurA().equals("") && !nouveau.getInferieurA().equals("")) || (!courant.getSuperieurA().equals("") && !nouveau.getSuperieurA().equals("")))
                {
                    return false;
                }
            }
        }
        //Verifier la complétude de cette fonction
        return true;
    }

    private void supprimerIncoherence(AlerteData nouveau)
    {
        int i;
        ArrayList<AlerteData> sousListe = new ArrayList<>();
        AlerteData courant;
        //construction d'une sous liste contenant toutes les alertes du même type que nouveau
        for (i = 0; i < listeAlerte.size(); i++)
        {
            courant = listeAlerte.get(i);
            System.out.println(courant.getType());
            System.out.println(nouveau.getType());
            if (courant.getType().equals(nouveau.getType()))
            {
                sousListe.add(courant);
            }
        }

        if (sousListe.size() != 0)
        {
            for (i = 0; i < sousListe.size(); i++)
            {
                System.out.println("inf de nouveau" + nouveau.getInferieurA());
                System.out.println("sup de nouveaus" + nouveau.getSuperieurA());
                courant = sousListe.get(i);
                if ((!courant.getInferieurA().equals("") && !nouveau.getInferieurA().equals("")) || (!courant.getSuperieurA().equals("") && !nouveau.getSuperieurA().equals("")) || ((!courant.getInferieurA().equals("") && !nouveau.getInferieurA().equals("")) && (!courant.getSuperieurA().equals("") && !nouveau.getSuperieurA().equals(""))))
                {
                    listeAlerte.remove(sousListe.get(i));
                }
            }
        }
    }

    private void remplirComboListe()
    {
        AlerteData courant;
        int i;
        for (i = 0; i < listeAlerte.size(); i++)
        {
            courant = listeAlerte.get(i);
            comboAlertes.addItem(creerChaineComboBox(courant.getInferieurA(), courant.getSuperieurA(), courant.getType()));
        }
    }


    private AlerteData parseChaineComboBoxToChaineListe(String chaine)
    {
        char courant;
        int i;
        int cptInf = 0;
        int cptSup = 0;
        int cptSymbole = 0;
        boolean borneDouble = false;
        boolean borneSup = false;
        boolean borneInf = false;
        String type = "";
        String inf = "";
        String sup = "";

        for (i = 0; i < chaine.length(); i++)
        {
            courant = chaine.charAt(i);
            if (courant == '<')
            {
                cptInf++;
            }
            else if (courant == '>')
            {
                cptSup++;
            }
        }

        if (cptInf == 2 && cptSup == 0)
        {
            borneDouble = true;
        }
        else if(cptInf == 1)
        {
            borneInf = true;
        }
        else
        {
            borneSup = true;
        }

        for (i = 0; i < chaine.length(); i++)
        {
            courant = chaine.charAt(i);
            if (borneDouble)
            {
                if (courant != '<' && courant != '>')
                {
                    if (cptSymbole == 0)
                    {
                        inf += courant;
                    }
                    if (cptSymbole == 1)
                    {
                        type += courant;
                    }
                    if (cptSymbole == 2)
                    {
                        sup += courant;
                    }
                }
                else if (courant == '<' || courant == '>')
                {
                    cptSymbole++;
                }
            }
            else if (borneInf)
            {
                sup = "";
                if (courant != '<' && courant != '>')
                {
                    if (cptSymbole == 0)
                    {
                        type += courant;
                    }
                    if (cptSymbole == 1)
                    {
                        inf += courant;
                    }
                }
                else if (courant == '<' || courant == '>')
                {
                    cptSymbole++;
                }

            }
            else if (borneSup)
            {
                inf = "";
                if (courant != '<' && courant != '>')
                {
                    if (cptSymbole == 0)
                    {
                        type += courant;
                    }
                    if (cptSymbole == 1)
                    {
                        sup += courant;
                    }
                }
                else if (courant == '<' || courant == '>')
                {
                    cptSymbole++;
                }
            }
        }
        if ((type.length() > 0) && (type.charAt(type.length()-1) == ' ' || type.charAt(0) == ' '))
        {
            String aux = type;
            //type = new String("");
            if (aux.charAt(aux.length()-1) == ' ') {
                String aux1 = type;
                type = new String("");
                for (i = 0; i < aux1.length() - 1; i++) {
                    type += aux1.charAt(i);
                }
            }
            if (aux.charAt(0) == ' ')
            {
                String aux2 = type;
                type = new String("");
                for (i = 1; i < aux2.length(); i++) {
                    type += aux2.charAt(i);
                }
            }
        }
        if ((inf.length() > 0) && (inf.charAt(inf.length()-1) == ' ' || inf.charAt(0) == ' '))
        {
            String aux = inf;
            //inf = new String("");
            if (aux.charAt(aux.length()-1) == ' ') {
                String aux1 = inf;
                inf = new String("");
                for (i = 0; i < aux1.length() - 1; i++) {
                    inf += aux1.charAt(i);
                }
            }
            if (aux.charAt(0) == ' ')
            {
                String aux2 = inf;
                inf = new String("");
                for (i = 1; i < aux2.length(); i++) {
                    inf += aux2.charAt(i);
                }
            }
        }
        if ((sup.length() > 0) && (sup.charAt(sup.length()-1) == ' '|| sup.charAt(0) == ' '))
        {
            String aux = sup;
            //sup = new String("");
            if (aux.charAt(aux.length()-1) == ' ') {
                String aux1 = sup;
                sup = new String("");
                for (i = 0; i < aux1.length() - 1; i++) {
                    sup += aux1.charAt(i);
                }
            }
            if (aux.charAt(0) == ' ')
            {
                String aux2 = sup;
                sup = new String("");
                for (i = 1; i < aux2.length(); i++) {
                    sup += aux2.charAt(i);
                }
            }
        }
        return (new AlerteData(type, inf, sup));
    }

    private String creerChaineComboBox(String inf, String sup, String type)
    {
         /* 10 < Température < 50  */
        /*  Température < 10    */
        /* Température > 50   */
        if (inf.equals("") && !sup.equals("") && !type.equals(""))
        {
            return type + " > " + sup;
        }
        else if (!inf.equals("") && sup.equals("") && !type.equals(""))
        {
            return type + " < " + inf;
        }
        else if (!inf.equals("") && !sup.equals("") && !type.equals(""))
        {
            return inf + " < " + type + " < " + sup;
        }
        else
        {
            setErreur(true, "Demande d'alerte non comprise");
            return "";
        }
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
        comboListe.add("Eau chaude");
        comboListe.add("Vitesse vent");
        comboListe.add("Pression atmosphérique");

        for (i = 0; i < comboListe.size(); i++)
        {
            typeDonnees.addItem(comboListe.get(i));
        }

    }


    public JButton getAjouter() {
        return ajouter;
    }


    private boolean verifInterval(double min, double max)
     {
         if (max < min)
         {
             setErreur(true, "Erreur : La valeur superieurA doit être supérieur à inferieurA");
             return false;
         }
         else
         {
             setErreur(false, "");
             return true;
         }
     }

    public JButton getSupprimer() {
        return supprimer;
    }


    public ArrayList<AlerteData> getListeAlerte() {
        return listeAlerte;
    }

    public JPanel getpAjoutGen() {
        return pAjoutGen;
    }

    public JPanel getpSupprGen() {
        return pSupprGen;
    }
}
