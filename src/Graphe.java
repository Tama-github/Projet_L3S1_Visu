import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.ListIterator;

public class Graphe extends JPanel {
    /*** Paramètre ***/
    private int tailleMax;				//Distance verticale [origine ; valeurMax]
    private int marge;				    //Distance verticale entre la plus haute valeur et la flèche verticale

    //X Horizontal
    private int xDep_H;					//Coordonnée 'x' de départ (trait horizontal)
    private int xArr_H;


    //Y Vertical
    private int yDep_V;					//Coordonnée 'y' de départ (trait vertical)
    private int yArr_V;		        	//Coordonnée 'y' d'arrivée (trait vertical)
    private int yDep_VNeg;				//Coordonnée 'y' de départ (trait vertical négatif)
    private int yArr_VNeg;				//Coordonnée 'y' d'arrivée (trait vertical négatif)

    //Y Horizontal
    private int yDep_H;					//Coordonnée 'y' de départ (trait horizontal)
    private int yArr_H;					//Coordonnée 'y' d'arrivée (trait horizontal)

    //X Vertical
    private int xDep_V;					//Coordonnée 'x' de départ (trait vertical)
    private int xArr_V;					//Coordonnée 'x' d'arrivée (trait vertical)

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
     */
    public void traitHorizontal(Graphics g) {
        //Ligne horizontale
        g.drawLine(xDep_H, yDep_H - 1, xArr_H, yArr_H - 1);
        g.drawLine(xDep_H, yDep_H + 0, xArr_H, yArr_H + 0);
        g.drawLine(xDep_H, yDep_H + 1, xArr_H, yArr_H + 1);
        g.drawLine(xDep_H, yDep_H + 2, xArr_H, yArr_H + 2);

        //Flèche horizontale
        g.drawLine(xArr_H, yDep_H - 11, xArr_H + 12, yArr_H);
        g.drawLine(xArr_H, yDep_H - 10, xArr_H + 11, yArr_H);
        g.drawLine(xArr_H, yDep_H - 9, xArr_H + 10, yArr_H);
        g.drawLine(xArr_H, yDep_H - 8, xArr_H + 9, yArr_H);
        g.drawLine(xArr_H, yDep_H - 7, xArr_H + 8, yArr_H);
        g.drawLine(xArr_H, yDep_H + 7, xArr_H + 8, yArr_H);
        g.drawLine(xArr_H, yDep_H + 8, xArr_H + 9, yArr_H);
        g.drawLine(xArr_H, yDep_H + 9, xArr_H + 10, yArr_H);
        g.drawLine(xArr_H, yDep_H + 10, xArr_H + 11, yArr_H);
        g.drawLine(xArr_H, yDep_H + 11, xArr_H + 12, yArr_H);
    }


    /**
     * La fonction 'traitVertical' trace le trait des ordonnées et sa flèche
     *
     * @param g : Graphics g est la base du dessin
     */
    public void traitVertical(Graphics g) {
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
     */
    public void legende(Graphics g) {
        Font saveFont = g.getFont();					//Cette ligne garde en mémoire les paramètres d'écriture du graphe.

        //Affichage du type des valeurs
        g.setFont(new Font("Arial", Font.BOLD, 14));
        String ordonnees = listeMesures.get(0).typeUnite + " (" + listeMesures.get(0).unite + ")";
        int valX;
        if ((ordonnees.length() * 8) > xDep_V - 15)
            valX = 15;
        else
            valX = xDep_V + 20 - (ordonnees.length() * 8);
        g.drawString(ordonnees, valX, yArr_V - 25);

        int yMax = yArr_V + marge;
        int ecart = tailleMax / 10;
        int valeurMax = listeMesures.get(0).max;
        int valeurMin = listeMesures.get(0).min;

        //Affichage des espaces de valeurs positives
        int valeur = valeurMax - valeurMin;

        for (int i = 9 ; i > -1 ; i--) {
            int y = yMax + ecart * i;
            int x = valeur - valeur * i / 10;
            if (i == 0) {
                g.drawLine(xDep_V, y - 2, xArr_H, y - 2);
                g.drawLine(xDep_V, y - 1, xArr_H, y - 1);
                g.drawLine(xDep_V, y + 1, xArr_H, y + 1);
            }
            g.drawLine(xDep_V, y, xArr_H, y);
            g.drawString(Integer.toString(x + valeurMin), xDep_V - (5 + String.valueOf(x + valeurMin).length() * 8), y + 5);
        }

        //Affichage des espaces de valeurs positives et supérieures au maximum (température et pression uniquement)
        valeur = (valeurMax - valeurMin) / 10;

        if (!this.listeMesures.get(0).unite.equals("%")) {
            for (int i = 1 ; i < 3 ; i++) {
                int y = yMax - ecart * i;
                int x = valeurMax + valeur * i;
                if (i == 0) {
                    g.drawLine(xDep_V, y - 2, xArr_H, y - 2);
                    g.drawLine(xDep_V, y - 1, xArr_H, y - 1);
                    g.drawLine(xDep_V, y + 1, xArr_H, y + 1);
                }
                g.drawLine(xDep_V, y, xArr_H, y);
                g.drawString(Integer.toString(x), xDep_V - (5 + String.valueOf(x).length() * 8), y + 5);
            }
        }

        //Affichage des espaces de valeurs négatives (température et pression uniquement)
        if (this.listeMesures.get(0).unite.equals("°C") || this.listeMesures.get(0).unite.equals("hPa")) {
            for (int i = 0 ; i < 6 ; i++) {
                int y = yDep_V + ecart * i;
                int x = valeurMin - valeur * i;
                if (i == 0) {
                    g.drawLine(xDep_V, y - 2, xArr_H, y - 2);
                    g.drawLine(xDep_V, y - 1, xArr_H, y - 1);
                    g.drawLine(xDep_V, y + 1, xArr_H, y + 1);
                }
                else
                    g.drawString(Integer.toString(x), xDep_V - (5 + String.valueOf(x).length() * 8), y + 5);
                g.drawLine(xDep_V, y, xArr_H, y);
            }
        }

        //Origine
        g.setFont(new Font("Arial", Font.BOLD, 14));
        if (listeMesures.get(0).unite.equals("hPa"))
            g.drawString("1000", xDep_V - (5 + String.valueOf("1000").length() * 8), yDep_V + 7);
        else
            g.drawString("0", xDep_V - 13, yDep_V + 7);
        g.setFont(saveFont);							//Cette ligne remet les paramètres d'écriture du graphe à la normale.

        //Légende axe abscisses
        g.setFont(new Font("Arial", Font.BOLD, 14));
        g.drawString("Temps (" + listeMesures.get(0).uniteTemps + ")", xArr_H + 15, yArr_H + 30);
        g.setFont(new Font("Arial", Font.PLAIN, 12));

        int nbElements = listeMesures.size();
        int distance = (xArr_H - xDep_H) / nbElements;
        for (int i = 0 ; i < nbElements ; i++) {
            if ((i % 5 == 0) && (i > 0)) {
                g.drawString(Integer.toString(i), xDep_H + 7 + i * distance - String.valueOf(i).length() * 3, yDep_H + 20);
            }
            g.drawLine(xDep_H + 7 + i * distance, yDep_H, xDep_H + 7 + i * distance, yDep_H + 5);
            g.drawLine(xDep_H + 8 + i * distance, yDep_H, xDep_H + 8 + i * distance, yDep_H + 5);
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

        g.setColor(new Color(58, 137, 35));  //Couleur verte
        g.drawLine(xPoint1, yPoint1, xPoint2, yPoint2);
        g.drawLine(xPoint1 + 1, yPoint1, xPoint2 + 1, yPoint2);
        g.drawLine(xPoint1, yPoint1 + 1, xPoint2, yPoint2 + 1);
        g.drawLine(xPoint1 + 1, yPoint1 + 1, xPoint2 + 1, yPoint2 + 1);
        g.setColor(Color.BLACK);
    }


    /**
     * La fonction 'affichageMesures' affiche toutes les mesures relevées dans la base de données (température, humidité, etc.)
     *
     * @param g : Graphics g est la base du dessin
     */
    public void affichageMesures (Graphics g) {
        int xDeb1;
        int xFin1;
        int yDeb1;
        int yFin1;

        int xPoint1 = 0;
        int yPoint1 = 0;
        int xPoint2;
        int yPoint2;

        boolean verifMax1 = true, verifMax2 = true, supZero = true;

        int nbElements = this.listeMesures.size();
        int distance = (xArr_H - xDep_H) / nbElements;

        int i = 0;

        for (ListIterator<Mesure> iter = this.listeMesures.listIterator(); iter.hasNext(); ) {
            xDeb1 = xDep_H + i * distance;
            xFin1 = 15;

            Mesure mesureX = iter.next();
            int maximum = mesureX.max;
            int test;

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

                    case "hPa" :
                        test -= 1000;
                        maximum -= 1000;
                        maximum = maximum * tailleMax / 100;
                        test = test * tailleMax / 100;
                        break;

                    default :
                        maximum = tailleMax;
                        test = test * tailleMax / mesureX.max;
                        break;
                }
            }

            //... Sinon :
            else {
                test = mesureX.val;

                maximum = tailleMax;
                test = test * tailleMax / mesureX.max;

                while (test > tailleMax)
                    test = test % tailleMax;
                if (test == 0)
                    test = tailleMax;
            }

            //Courbe
            if (test > 0) {
                yDeb1 = yDep_H - test;

                if (test <= maximum) {
                    g.setColor(new Color(58, 137, 35));    //Couleur verte
                }
                else if (test > maximum) {
                    g.setColor(Color.RED);
                }
                xDeb1 = xDeb1 + (xFin1 / 2);
                g.fillRect(xDeb1, yDeb1, 2, 2);

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
            }
            else {
                yDeb1 = yDep_H - test;
                xDeb1 = xDeb1 + (xFin1 / 2);

                g.setColor(Color.BLUE);
                g.fillRect(xDeb1, yDeb1, 2, 2);

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
            }

            g.setColor(Color.BLACK);

            if ((i % 5 == 0) && (i > 0))
                g.drawString(Integer.toString(i), xDep_H + 7 + i * distance - String.valueOf(i).length() * 3, yDep_H + 20);
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
        int iMax = listeMesures.size();
        tailleMax = 10 * 25;        //Multiple de 10 très fortement conseillé afin de garder des écart de pixel en int et non en float ramenés en int
        marge = 85;

        //X Horizontal
        xDep_H = 120;
        xArr_H = xDep_H + this.xMax;


        //Y Vertical
        yDep_V = 400;
        yArr_V = yDep_V - (tailleMax + marge);
        yDep_VNeg = yDep_V;
        yArr_VNeg = yDep_VNeg + 150;

        //Y Horizontal
        yDep_H = yDep_V;
        yArr_H = yDep_V;

        //X Vertical
        xDep_V = xDep_H;
        xArr_V = xDep_H;

        traitHorizontal(g);

        traitVertical(g);

        legende(g);

        affichageMesures(g);
    }
}

