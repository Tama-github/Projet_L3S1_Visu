import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.InterruptedException;
import java.util.ArrayList;
import java.util.Scanner;

public class FenetreGraphe extends JFrame {

    /*protected InformationsCapteur grapheCapteur;

    public Graphique (InformationsCapteur donnees) {
        this.grapheCapteur = donnees;
    }*/

    private AffichageGraphe graphe = new AffichageGraphe();

    public boolean isOuvert() {
        return graphe.isOuvert();
    }

    public void setOuvert(boolean bool) {
        graphe.setOuvert(bool);
    }

    public FenetreGraphe() {
        graphe.setOuvert(false);
    }


    /**
     * La fonction 'verifTypage' renvoie la valeur maximale possible suivant le type
     *
     * @param unite 	: la mesure observée
     * @param valeur    : la valeur à vérifier
     *
     * @return boolean  : renvoie 'true' si les valeurs correspondent à l'unité
     */
    protected static boolean verifTypage(String unite, int valeur) {
        if ((unite.equals("%")) && ((valeur > 100) || (valeur < 0)))
            return false;
        else if (unite.equals("°C")) {
            if (valeur < -273)
                return false;
            return true;
        }
        else if (valeur < 0)
            return false;
        else
            return true;
    }


    /**
     * La fonction 'verifierMax' renvoie la valeur maximale possible suivant le type
     *
     * @param type	: type des valeurs observées
     *
     * @return entier : valeur maximale possible suivant le type
     */
    private static int verifierMax(String type) {
        switch (type) {
            case "%" :
                return 100;

            case "°C" :
                return 50;

            case "lum" :
                return 1000;

            case "dB" :
                return 120;

            case "W" :
                return 3000;

            case "l" :
                return 100;

            case "km/h" :
                return 150;

            case "hPa" :
                return 1100;

            default :
                return 0;
        }
    }


    /**
     * La fonction 'verifierMin' renvoie la valeur représentée sur l'axe des abscisses suivant le type
     *
     * @param type	: type des valeurs observées
     */
    private static int verifierMin(String type) {
        if (type.equals("hPa")) {
            return 1000;
        }
        else
            return 0;
    }


    /**
     * La fonction 'verifierType' vérifie si l'unité de valeur correspond bien au type souhaité.
     *
     * @param type	: type  des valeurs observées
     * @param unite	: unité des valeurs observées
     *
     * @return boolean : retourne 'true' si le couple type/unité correspond, 'false' sinon
     */
    private static boolean verifierType(String type, String unite) {
        switch (unite) {
            case "%" :
                if (type.equalsIgnoreCase("humidité") || type.equalsIgnoreCase("humidite"))
                    return true;
                return false;

            case "°C" :
                if (type.equalsIgnoreCase("température") || type.equalsIgnoreCase("temperature"))
                    return true;
                return false;

            case "lum" :
                if (type.equalsIgnoreCase("luminosité") || type.equalsIgnoreCase("luminosite"))
                    return true;
                return false;

            case "dB" :
                if (type.equalsIgnoreCase("son"))
                    return true;
                return false;

            case "W" :
                if (type.equalsIgnoreCase("consommation"))
                    return true;
                return false;

            case "l" :
                if (type.equalsIgnoreCase("eau"))
                    return true;
                return false;

            case "km/h" :
                if (type.equalsIgnoreCase("vent"))
                    return true;
                return false;

            case "hPa" :
                if (type.equalsIgnoreCase("pression"))
                    return true;
                return false;

            default :
                return false;
        }
    }


    /**
     * La fonction 'verifierTemps' vérifie si l'unité de temps correspond à une unité connue.
     *
     * @param unite	: unité de temps à vérifier
     *
     * @return String : retourne le paramètre 'unite' si elle existe, "?" sinon
     */
    private static String verifierTemps(String unite) {
        if ((unite.equals("ms")) || (unite.equals("s")) || (unite.equals("min")) || (unite.equals("h")) || (unite.equals("jour")) || (unite.equals("mois")) || (unite.equals("annee"))) {
            return unite;
        }
        return "s";
    }


    public void creationGraphique () throws FileNotFoundException, InterruptedException {

        //Création de l'objet File
        File f = new File("Fichiers/test");
        if (f == null) {
            System.out.println("Erreur : Problème lors de la recherche du fichier !");
            setVisible(false);
            dispose();
        }

        else {
            Scanner scanner = new Scanner(f);
            int nbMots = 0, taille = 0, nbLignesRestantes = 10;
            Boolean finFichier = false, erreurTypage = false;
            String line = null;

            ArrayList<InformationsCapteur> listeInformationsCapteurs = new ArrayList<InformationsCapteur>();

            LieuCapteur where = null;
            Interieur in = null;
            Exterieur out = null;

            String nom = null;
            String lieu = null;
            String batiment = null;
            int etage = 0, salle = 0;
            String typeUnite = null;
            String unite = null;
            String typeTemps = null;
            String uniteTemps = null;

            ArrayList<Mesure> mesures = new ArrayList<Mesure>();

            String[] mots = null;
        /*for(int i=0;i<mots.length;i++){
            System.out.print("Mot["+i+"]="+mots[i]);
        }*/

            while ((scanner.hasNextLine()) && (!finFichier)) {
                //InformationsCapteur capt = null;
                int cmpt = 0;
                Boolean fin = false;
                mesures = new ArrayList<Mesure>();
                in = null;
                out = null;

                erreurTypage = false;

                while ((scanner.hasNextLine()) && (!fin) && (!erreurTypage)) {
                    line = scanner.nextLine();

                    while ((scanner.hasNextLine()) && (line.length() == 0))
                        line = scanner.nextLine();

                    if (!scanner.hasNextLine())
                        finFichier = true;
                    else {
                        mots = line.split(" ");

                        if (!mots[0].equals("end")) {

                            switch (cmpt) {
                                case 0:
                                    nom = mots[0];
                                    for (int i = 1; i < mots.length; i++)
                                        nom = nom + " " + mots[i];
                                    break;

                                case 1:
                                    lieu = mots[0];


                                    if (lieu.equalsIgnoreCase("Interieur") || lieu.equalsIgnoreCase("Intérieur")) {
                                        in = new Interieur(mots[1], Integer.parseInt(mots[2]), Integer.parseInt(mots[3]));
                                        where = new LieuCapteur(0, in, out);
                                    } else if (lieu.equalsIgnoreCase("Exterieur") || lieu.equalsIgnoreCase("Extérieur")) {
                                        out = new Exterieur(Integer.parseInt(mots[1]), Integer.parseInt(mots[2]));
                                        where = new LieuCapteur(1, in, out);
                                    } else
                                        erreurTypage = true;

                                    break;

                                case 2:
                                    typeUnite = mots[0];
                                    unite = mots[1];
                                    if (unite.equals("C"))
                                        unite = "°C";
                                    if (!verifierType(typeUnite, unite))
                                        erreurTypage = true;
                                    break;

                                case 3:
                                    uniteTemps = mots[1];
                                    if (!verifierType(typeUnite, unite))
                                        erreurTypage = true;
                                    break;

                                default:
                                    if (!verifTypage(unite, Integer.parseInt(mots[1])))
                                        erreurTypage = true;
                                    else
                                        mesures.add(new Mesure(typeUnite, unite, Integer.parseInt(mots[1]), verifierMax(unite), verifierMin(unite), verifierTemps(uniteTemps)));
                                    break;
                            }

                            cmpt++;
                        } else {
                            fin = true;
                        }
                    }
                }

                if (erreurTypage) {
                    System.out.println("Type de valeur non reconnu...");
                    System.out.println("Unite : " + unite + ", type : " + typeUnite);
                    while (!mots[0].equals("end")) {
                        line = scanner.nextLine();
                        mots = line.split(" ");
                    }
                    cmpt = 0;
                } else if (cmpt > 3) {
                    if (!scanner.hasNextLine()) {
                        finFichier = true;
                    } else {
                        scanner.nextLine();
                    }
                    listeInformationsCapteurs.add(new InformationsCapteur(nom, mesures, where));
                    //capt.listeMesures = mesures;
                    //capt.loc = where;
                }

            }

            graphe.creationGraphe(listeInformationsCapteurs);
            //test = new creationGraphe(listeInformationsCapteurs.get(0));
        }
    }

    /*public static void main(String[] args) throws FileNotFoundException, InterruptedException {
        creationGraphique();
    }*/

}
