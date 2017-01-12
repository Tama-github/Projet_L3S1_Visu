import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.ListIterator;

public class Graphe extends JPanel {
    /*** Paramètre ***/
    private ArrayList<Mesure> listeMesures;	//Contient la liste des mesures faites par le InformationsCapteur
    private int xMax = 250;

    public int getxMax() {
        return xMax;
    }

    public void setxMax(int x) {
        this.xMax = x;
    }


    /*** Fonctions ***/

    /** Liste des fonctions :
     *
     * public void setValeur (ArrayList<Mesure> liste)
     *
     * public void traitHorizontal(Graphics g, int xDep_H, int yDep_H, int xArr_H, int yArr_H)
     *
     * public void traitVertical(Graphics g, int xDep_V, int yDep_V, int xArr_V, int yArr_V, int yDep_VNeg, int yArr_VNeg)
     *
     * public void legende(Graphics g, int xDep_V, int yDep_V, int xArr_V, int yArr_V, int xArr_H, int yArr_H)
     *
     * public void pointilles(Graphics g, int xDep_V, int xArr_H, int xArr_V, int yArr_V, int yArr_VNeg, int tailleMax)
     *
     * public void relierPoints(Graphics g, int xPoint1, int yPoint1, int xPoint2, int yPoint2)
     *
     * public void affichageMesures (Graphics g, int xDep_H, int yDep_H, int tailleMax)
     *
     * public void paint(Graphics g)
     *
     */


    /**
     * La fonction 'setValeur' enregistre la liste des mesures faites par le InformationsCapteur avant de commencer à dessiner
     * /!\ Important : il faut lancer cette fonction avant toute autre fonction de cette classe /!\
     *
     * @param liste		: contient la liste des mesures faites par le InformationsCapteur
     */
    public void setValeur (ArrayList<Mesure> liste) {
        this.listeMesures = liste;
    }


    /**
     * La fonction 'traitHorizontal' trace le trait des abscisses et sa flèche
     *
     * @param g : Graphics g est la base du dessin
     *
     * @param xDep_H	: coordonnée 'x' de départ (trait horizontal)
     * @param yDep_H	: coordonnée 'y' de départ (trait horizontal)
     * @param xArr_H	: coordonnée 'x' d'arrivée (trait horizontal)
     * @param yArr_H	: coordonnée 'y' d'arrivée (trait horizontal)
     */
    public void traitHorizontal(Graphics g, int xDep_H, int yDep_H, int xArr_H, int yArr_H) {
        //Ligne horizontale
        g.drawLine(xDep_H - 5, yDep_H + 0, xArr_H, yArr_H + 0);
        g.drawLine(xDep_H - 5, yDep_H + 1, xArr_H, yArr_H + 1);
        g.drawLine(xDep_H - 5, yDep_H + 2, xArr_H, yArr_H + 2);
        g.drawLine(xDep_H - 5, yDep_H + 3, xArr_H, yArr_H + 3);
        g.drawLine(xDep_H - 5, yDep_H + 4, xArr_H, yArr_H + 4);

        //Flèche horizontale
        g.drawLine(xArr_H, yDep_H - 9, xArr_H + 12, yArr_H + 2);
        g.drawLine(xArr_H, yDep_H - 8, xArr_H + 11, yArr_H + 2);
        g.drawLine(xArr_H, yDep_H - 7, xArr_H + 10, yArr_H + 2);
        g.drawLine(xArr_H, yDep_H - 6, xArr_H + 9, yArr_H + 2);
        g.drawLine(xArr_H, yDep_H - 5, xArr_H + 8, yArr_H + 2);
        g.drawLine(xArr_H, yDep_H + 9, xArr_H + 8, yArr_H + 2);
        g.drawLine(xArr_H, yDep_H + 10, xArr_H + 9, yArr_H + 2);
        g.drawLine(xArr_H, yDep_H + 11, xArr_H + 10, yArr_H + 2);
        g.drawLine(xArr_H, yDep_H + 12, xArr_H + 11, yArr_H + 2);
        g.drawLine(xArr_H, yDep_H + 13, xArr_H + 12, yArr_H + 2);
    }


    /**
     * La fonction 'traitVertical' trace le trait des ordonnées et sa flèche
     *
     * @param g : Graphics g est la base du dessin
     *
     * @param xDep_V	: coordonnée 'x' de départ (trait vertical positif)
     * @param yDep_V	: coordonnée 'y' de départ (trait vertical positif)
     * @param xArr_V	: coordonnée 'x' d'arrivée (trait vertical positif)
     * @param yArr_V	: coordonnée 'y' d'arrivée (trait vertical positif)
     *
     * @param yDep_VNeg : coordonnée 'y' de départ (trait vertical négatif)
     * @param yArr_VNeg : coordonnée 'y' d'arrivée (trait vertical négatif)
     */
    public void traitVertical(Graphics g, int xDep_V, int yDep_V, int xArr_V, int yArr_V, int yDep_VNeg, int yArr_VNeg) {
        //Ligne verticale
        g.drawLine(xDep_V + 0, yDep_V, xArr_V + 0, yArr_V);
        g.drawLine(xDep_V + 1, yDep_V, xArr_V + 1, yArr_V);
        g.drawLine(xDep_V + 2, yDep_V, xArr_V + 2, yArr_V);
        g.drawLine(xDep_V + 3, yDep_V, xArr_V + 3, yArr_V);
        g.drawLine(xDep_V + 4, yDep_V, xArr_V + 4, yArr_V);

        //Ligne verticale Négative
        g.drawLine(xDep_V + 0, yDep_VNeg, xArr_V + 0, yArr_VNeg);
        g.drawLine(xDep_V + 1, yDep_VNeg, xArr_V + 1, yArr_VNeg);
        g.drawLine(xDep_V + 2, yDep_VNeg, xArr_V + 2, yArr_VNeg);
        g.drawLine(xDep_V + 3, yDep_VNeg, xArr_V + 3, yArr_VNeg);
        g.drawLine(xDep_V + 4, yDep_VNeg, xArr_V + 4, yArr_VNeg);

        //Flèche verticale
        g.drawLine(xDep_V - 9, yArr_V, xArr_V + 2, yArr_V - 12);
        g.drawLine(xDep_V - 8, yArr_V, xArr_V + 2, yArr_V - 11);
        g.drawLine(xDep_V - 7, yArr_V, xArr_V + 2, yArr_V - 10);
        g.drawLine(xDep_V - 6, yArr_V, xArr_V + 2, yArr_V - 9);
        g.drawLine(xDep_V - 5, yArr_V, xArr_V + 2, yArr_V - 8);
        g.drawLine(xDep_V + 9, yArr_V, xArr_V + 2, yArr_V - 8);
        g.drawLine(xDep_V + 10, yArr_V, xArr_V + 2, yArr_V - 9);
        g.drawLine(xDep_V + 11, yArr_V, xArr_V + 2, yArr_V - 10);
        g.drawLine(xDep_V + 12, yArr_V, xArr_V + 2, yArr_V - 11);
        g.drawLine(xDep_V + 13, yArr_V, xArr_V + 2, yArr_V - 12);
    }


    /**
     * La fonction 'legende' écrit sur le graphe la légende des abscisses et des ordonnées
     *
     * @param g : Graphics g est la base du dessin
     *
     * @param xDep_V	: coordonnée 'x' de départ (trait vertical positif)
     * @param yDep_V	: coordonnée 'y' de départ (trait vertical positif)
     * @param xArr_V	: coordonnée 'x' d'arrivée (trait vertical positif)
     * @param yArr_V	: coordonnée 'y' d'arrivée (trait vertical positif)
     */
    public void legende(Graphics g, int xDep_V, int yDep_V, int xArr_V, int yArr_V, int xArr_H, int yArr_H) {
        Font saveFont = g.getFont();					//Cette ligne garde en mémoire les paramètres d'écriture du graphe.

        //Type
        g.setFont(new Font("Arial", Font.BOLD, 14));
        //A modifier : risque de dépassement du cadre en x :
        g.drawString(listeMesures.get(0).typeUnite, xDep_V - (listeMesures.get(0).typeUnite.length() * 10), yArr_V + 25);

        //Max
        int tailleVal = 0, i = listeMesures.get(0).max;
        while (i > 0) {
            tailleVal++;
            i = i/10;
        }
        g.drawString(Integer.toString(listeMesures.get(0).max), xDep_V - 14 * tailleVal, yArr_V + 58);

        //Origine
        g.setFont(new Font("Arial", Font.BOLD, 14));
        if (listeMesures.get(0).unite.equals("hPa"))
            g.drawString("1000", xDep_V - 56, yDep_V + 7);
        else
            g.drawString("0", xDep_V - 22, yDep_V + 7);
        g.setFont(saveFont);							//Cette ligne remet les paramètres d'écriture du graphe à la normale.

        g.drawLine(xDep_V - 5, yArr_V + 51, xArr_V, yArr_V + 51);
        g.drawLine(xDep_V - 5, yArr_V + 52, xArr_V, yArr_V + 52);
        g.drawLine(xDep_V - 5, yArr_V + 53, xArr_V, yArr_V + 53);
        g.drawLine(xDep_V - 5, yArr_V + 54, xArr_V, yArr_V + 54);
        g.drawLine(xDep_V - 5, yArr_V + 55, xArr_V, yArr_V + 55);

        //Légende axe abscisses
        g.setFont(new Font("Arial", Font.BOLD, 14));
        g.drawString("Temps (" + listeMesures.get(0).uniteTemps + ")", xArr_H - 15, yArr_H + 30);
        g.setFont(new Font("Arial", Font.PLAIN, 12));
    }


    /**
     * La fonction 'pointilles' trace les pointillés correspondants à la valeur max, à la moitié de la valeur max (positive ET négative),
     * 		ainsi que des pointillés sur la partie négative de l'axe des ordonnées
     *
     * @param g : Graphics g est la base du dessin
     *
     * @param xDep_V	: coordonnée 'x' de départ (trait vertical positif)
     * @param xArr_H	: coordonnée 'x' d'arrivée (trait horizontal)
     * @param xArr_V	: coordonnée 'x' d'arrivée (trait vertical positif)
     * @param yArr_V	: coordonnée 'y' d'arrivée (trait vertical positif)
     *
     * @param yArr_VNeg : coordonnée 'y' d'arrivée (trait vertical négatif)
     *
     * @param tailleMax	: distance [origine ; valeurMax]
     */
    public void pointilles(Graphics g, int xDep_V, int xArr_H, int xArr_V, int yArr_V, int yArr_VNeg, int tailleMax) {
        int i = 0;
        int trait = xDep_V - 10 + (i + 1) * 25;
        int nbTraits = yArr_V + 53;

        //Pointillés des valeurs
        for (int y = 0 ; y < 3 ; y++) {
            i = 0;
            trait = xDep_V - 10 + (i + 1) * 25;
            while (trait < xArr_H) {
                if ((y == 0)) {
                    g.drawLine(trait, nbTraits - 1, trait + 10, nbTraits - 1);
                    g.drawLine(trait, nbTraits + 1, trait + 10, nbTraits + 1);
                }
                g.drawLine(trait, nbTraits, trait + 10, nbTraits);
                i++;
                trait = xDep_V - 10 + (i + 1) * 25;
            }
            nbTraits = nbTraits + (tailleMax / 2);
            if (y == 1) {
                nbTraits = nbTraits + (tailleMax / 2);;
                nbTraits += 2;
            }
        }

        //Pointillés négatifs
        i = yArr_VNeg + 10;
        int y;
        for (y = 0 ; y < 2 ; y++) {
            g.drawLine(xDep_V + 0, i, xArr_V + 0, i + 10);
            g.drawLine(xDep_V + 1, i, xArr_V + 1, i + 10);
            g.drawLine(xDep_V + 2, i, xArr_V + 2, i + 10);
            g.drawLine(xDep_V + 3, i, xArr_V + 3, i + 10);
            g.drawLine(xDep_V + 4, i, xArr_V + 4, i + 10);
            i += 20;
        }
    }


    /**
     * La fonction 'relierPoints' trace les traits entre 2 points du graphes
     *
     * @param g : Graphics g est la base du dessin
     *
     * @param xPoint1	: coordonnée 'x' du premier  point
     * @param yPoint1	: coordonnée 'y' du premier  point
     * @param xPoint2	: coordonnée 'x' du deuxième point
     * @param yPoint2	: coordonnée 'y' du deuxième point
     */
    public void relierPoints(Graphics g, int xPoint1, int yPoint1, int xPoint2, int yPoint2) {
        //g.setColor(new Color(58, 137, 35));  //Couleur verte
        g.drawLine(xPoint1, yPoint1, xPoint2, yPoint2);
        g.drawLine(xPoint1 + 1, yPoint1, xPoint2 + 1, yPoint2);
        g.drawLine(xPoint1, yPoint1 + 1, xPoint2, yPoint2 + 1);
        g.drawLine(xPoint1 + 1, yPoint1 + 1, xPoint2 + 1, yPoint2 + 1);
        //g.setColor(Color.BLACK);
    }


    /**
     * La fonction 'affichageMesures' affiche toutes les mesures relevées dans la base de données (température, humidité, etc.)
     *
     * @param g : Graphics g est la base du dessin
     *
     * @param xDep_H	: coordonnée 'x' de départ (trait horizontal)
     * @param yDep_H	: coordonnée 'y' de départ (trait horizontal)
     *
     * @param tailleMax	: distance [origine ; valeurMax]
     */
    public void affichageMesures (Graphics g, int xDep_H, int yDep_H, int tailleMax) {
        int xDeb1;
        int xFin1;
        int yDeb1;
        int yFin1;

        int xPoint1 = 0;
        int yPoint1 = 0;
        int xPoint2 = 0;
        int yPoint2 = 0;

        int i = 0;

        for (ListIterator<Mesure> iter = this.listeMesures.listIterator(); iter.hasNext(); ) {
            xDeb1 = xDep_H + (2 * i + 1) * 5;
            xFin1 = 15;

            Mesure mesureX = iter.next();
            int maximum = mesureX.max;
            int test;

            //Vérification des valeurs mesurées en fonction des unités
            /*test = Graphique.verifTypage(mesureX, tailleMax);

            if (mesureX.unite.equals("hPa")) {
                maximum -= 1000;
                maximum = maximum * tailleMax / 100;
            }*/

            if (mesureX.val >= 0) {
                test = mesureX.val;

                switch (mesureX.unite) {
                    case "%" :
                        //Vérification de pourcentage ≤ 100 % (au cas où)
                        if (test > 100) {
                            AffichageGraphe erreur = new AffichageGraphe();
                            erreur.fermerFenetreErreur();
                        }
                        maximum = maximum * tailleMax / mesureX.max;;
                        test = test * tailleMax / mesureX.max;
                        break;

                    case "°C" :
                        maximum = tailleMax;
                        test = test * tailleMax / mesureX.max;
                        //test = test * tailleMax / 60;
                        break;

                    case "lum" : //A modifier !!
                        maximum = tailleMax;
                        test = test * tailleMax / mesureX.max;
                        //test = test * tailleMax / 100;
                        break;

                    case "dB" : //A modifier !!
                        maximum = tailleMax;
                        test = test * tailleMax / mesureX.max;
                        //test = test * tailleMax / 60;
                        break;

                    case "W" : //A modifier !!
                        maximum = tailleMax;
                        test = test * tailleMax / mesureX.max;
                        //test = test * tailleMax / 3000;
                        break;

                    case "l" : //A modifier !!
                        maximum = tailleMax;
                        test = test * tailleMax / mesureX.max;
                        //test = test * tailleMax / 60;
                        break;

                    case "km/h" : //A modifier !!
                        maximum = tailleMax;
                        test = test * tailleMax / mesureX.max;
                        //test = test * tailleMax / 100;
                        break;

                    case "hPa" : //A modifier !!
                        test -= 1000;
                        maximum -= 1000;
                        maximum = maximum * tailleMax / 100;
                        if (test > 0) {
                            test = test * tailleMax / 100;
                        }
                        else if (test == 0) {
                            test = 1;
                        }
                        else {
                            //test = test * tailleMax / 100;
                        }
                        break;

                    default :
                        test *= 2;
                        System.out.println("Type inconnu...");
                        //System.out.println("Erreur : Type inconnu...");
                        //System.exit(99);
                        break;
                }
            }

            //... Sinon :
            else {
                test = mesureX.val;

                switch (mesureX.unite) {
                    case "°C" :
                        maximum = tailleMax;
                        test = test * tailleMax / mesureX.max;
                        //test = test * tailleMax / 60;
                        break;

                    default :
                        test *= 2;
                        System.out.println("Type inconnu...");
                        //System.out.println("Erreur : Type inconnu...");
                        //System.exit(99);
                        break;
                }

                while (test > tailleMax)
                    test = test % tailleMax;
                if (test == 0)
                    test = tailleMax;
            }

            //Histogramme
            /*if (test > 0) {
                yDeb1 = yDep_H - test;
                yFin1 = test;

                if (test <= maximum)
                    g.setColor(new Color(58, 137, 35));	//Couleur verte
                    //g.setColor(Color.GREEN);
                else if (test > maximum)
                    g.setColor(Color.RED);
                g.fillRect(xDeb1, yDeb1, xFin1, yFin1);
                g.setColor(Color.BLACK);
                g.drawString(Integer.toString(mesureX.val) + mesureX.unite, xDeb1 + 8, yDeb1 - 5);
            }
            else {
                yDeb1 = yDep_H + 5;
                yFin1 = -1 * test;

                g.setColor(Color.BLUE);
                g.fillRect(xDeb1, yDeb1, xFin1, yFin1);
                g.setColor(Color.BLACK);
                g.drawString(Integer.toString(mesureX.val) + mesureX.unite, xDeb1 + 8, yDeb1 + yFin1 + 12);
                g.setColor(Color.WHITE);
            }*/

            //Courbe
            if (test > 0) {
                yDeb1 = yDep_H - test;
                yFin1 = 10;

                if (test <= maximum)
                    g.setColor(new Color(58, 137, 35));	//Couleur verte
                    //g.setColor(Color.GREEN);
                else if (test > maximum)
                    g.setColor(Color.RED);
                xDeb1 = xDeb1 + (xFin1 / 2);
                yDeb1 = yDeb1 + (yFin1 / 2);
                g.fillRect(xDeb1, yDeb1, 2, 2);
                //g.drawString(Integer.toString(mesureX.val) + mesureX.unite, xDeb1 + 8, yDeb1 - 5);

                if (i > 0) {
                    xPoint2 = xDeb1;
                    yPoint2 = yDeb1;
                    relierPoints(g, xPoint1, yPoint1, xPoint2, yPoint2);
                    xPoint1 = xPoint2;
                    yPoint1 = yPoint2;
                }
                else {
                    xPoint1 = xDeb1;
                    yPoint1 = yDeb1;
                }
                g.setColor(Color.BLACK);
            }
            else {
                yDeb1 = yDep_H - test;
                yFin1 = 10;

                g.setColor(Color.BLUE);
                g.fillRect(xDeb1, yDeb1, 2, 2);
                //g.drawString(Integer.toString(mesureX.val) + mesureX.unite, xDeb1 + 8, yDeb1 + yFin1 + 12);
                //g.setColor(Color.WHITE);

                if (i > 0) {
                    xPoint2 = xDeb1;
                    yPoint2 = yDeb1;
                    relierPoints(g, xPoint1, yPoint1, xPoint2, yPoint2);
                    xPoint1 = xPoint2;
                    yPoint1 = yPoint2;
                }
                else {
                    xPoint1 = xDeb1;
                    yPoint1 = yDeb1;
                }
                g.setColor(Color.BLACK);
            }

            //g.drawString(mesureX.typeUnite, xDeb1 + 5, yDep_H + 30);
            g.setColor(Color.BLACK);

            /*g.drawLine(xDeb1 + 20, yDep_H, xDeb1 + 20, yDep_H + 10);
            g.drawLine(xDeb1 + 21, yDep_H, xDeb1 + 21, yDep_H + 10);
            g.drawLine(xDeb1 + 22, yDep_H, xDeb1 + 22, yDep_H + 10);
            g.drawLine(xDeb1 + 23, yDep_H, xDeb1 + 23, yDep_H + 10);
            g.drawLine(xDeb1 + 24, yDep_H, xDeb1 + 24, yDep_H + 10);*/

            i++;
        }
    }


    /**
     * La fonction 'paint' est la fonction principale permettant de dessiner le graphe
     *
     * @param g : Graphics g est la base du dessin
     *
     */
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.BLACK);
        int iMax = listeMesures.size();		//Nombre d'éléments dans la liste des mesures
        int tailleMax = 250;				//Distance verticale [origine ; valeurMax]

        //X Horizontal
        int xDep_H = 120;								//Coordonnée 'x' de départ (trait horizontal)
        //int xArr_H = xDep_H + (2 * iMax + 1) * 15;	    //Coordonnée 'x' d'arrivée (trait horizontal)
        int xArr_H = xDep_H + (2 * iMax + 1) * 5;	    	//Coordonnée 'x' d'arrivée (trait horizontal)



        //Y Vertical
        int yDep_V = 350;								//Coordonnée 'y' de départ (trait vertical)
        int yArr_V = yDep_V - (tailleMax + 50);			//Coordonnée 'y' d'arrivée (trait vertical)
        int yDep_VNeg = yDep_V;							//Coordonnée 'y' de départ (trait vertical négatif)
        int yArr_VNeg = yDep_VNeg + 150;				//Coordonnée 'y' d'arrivée (trait vertical négatif)

        //Y Horizontal
        int yDep_H = yDep_V;							//Coordonnée 'y' de départ (trait horizontal)
        int yArr_H = yDep_V;							//Coordonnée 'y' d'arrivée (trait horizontal)

        //X Vertical
        int xDep_V = xDep_H;							//Coordonnée 'x' de départ (trait vertical)
        int xArr_V = xDep_H;							//Coordonnée 'x' d'arrivée (trait vertical)

        traitHorizontal(g, xDep_H, yDep_H, xArr_H, yArr_H);

        traitVertical(g, xDep_V, yDep_V, xArr_V, yArr_V, yDep_VNeg, yArr_VNeg);

        legende(g, xDep_V, yDep_V, xArr_V, yArr_V, xArr_H, yArr_H);

        pointilles(g, xDep_V, xArr_H, xArr_V, yArr_V, yArr_VNeg, tailleMax);

        affichageMesures(g, xDep_H, yDep_H, tailleMax);
    }
}

