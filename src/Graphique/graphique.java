package Graphique;

import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.InterruptedException;
import java.util.ArrayList;
import java.util.Scanner;

public class graphique extends JFrame {

    /*protected capteur grapheCapteur;

    public graphique (capteur donnees) {
        this.grapheCapteur = donnees;
    }*/

    public graphique () {
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


    public void creationGraphique () throws FileNotFoundException, InterruptedException {
        //Création de l'objet File
        File f = new File("Images/test");
        if (f == null)
            System.exit(99);
        System.out.println("Chemin absolu du fichier : " + f.getAbsolutePath());
        System.out.println("Nom du fichier : " + f.getName());
        System.out.println("Est-ce qu'il existe ? " + f.exists());
        System.out.println("Est-ce un répertoire ? " + f.isDirectory());
        System.out.println("Est-ce un fichier ? " + f.isFile());

        Scanner scanner = new Scanner(f);
        int nbMots = 0, taille = 0, nbLignesRestantes = 10;
        Boolean finFichier = false, erreurTypage = false;
        String line = null;

        affichageGraphe graphe = null;
        ArrayList<capteur> listeCapteurs = new ArrayList<capteur>();

        localisation where = null;
        interieur in = null;
        exterieur out = null;

        String nom = null;
        String lieu = null;
        String batiment = null;
        int etage = 0, salle = 0;
        String typeUnite = null;
        String unite = null;

        ArrayList<mesure> mesures = new ArrayList<mesure>();

        String[] mots = null;
        /*for(int i=0;i<mots.length;i++){
            System.out.print("Mot["+i+"]="+mots[i]);
        }*/

        while ((scanner.hasNextLine()) && (!finFichier)) {
            //capteur capt = null;
            int cmpt = 0;
            Boolean fin = false;
            mesures = new ArrayList<mesure>();
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
                                    in = new interieur(mots[1], Integer.parseInt(mots[2]), Integer.parseInt(mots[3]));
                                    where = new localisation(0, in, out);
                                } else if (lieu.equalsIgnoreCase("Exterieur") || lieu.equalsIgnoreCase("Extérieur")) {
                                    out = new exterieur(Integer.parseInt(mots[1]), Integer.parseInt(mots[2]));
                                    where = new localisation(1, in, out);
                                } else
                                    erreurTypage = true;

                                break;

                            case 2:
                                typeUnite = mots[0];
                                unite = mots[1];
                                if (!verifierType(typeUnite, unite))
                                    erreurTypage = true;
                                break;

                            default:
                                mesures.add(new mesure(typeUnite, unite, Integer.parseInt(mots[1]), verifierMax(unite)));
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
            }
            else if (cmpt > 2) {
                if (!scanner.hasNextLine()) {
                    finFichier = true;
                }
                else {
                    scanner.nextLine();
                }
                listeCapteurs.add(new capteur(nom, mesures, where));
                //capt.listeMesures = mesures;
                //capt.loc = where;
            }

        }

        graphe = new affichageGraphe(listeCapteurs);
        //test = new affichageGraphe(listeCapteurs.get(0));
    }


    /*public static void main(String[] args) throws FileNotFoundException, InterruptedException {
        creationGraphique();
    }*/

}
