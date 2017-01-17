import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;


public class AffichageGraphe extends JFrame {
    private JLayeredPane container = new JLayeredPane();

    private JPanel droite = new JPanel();

    private JPanel gauche = new JPanel();
    private JPanel haut = new JPanel();

    private JLabel descriptionLoc = null;
    private JLabel latitude = null;
    private JLabel longitude = null;
    private JLabel batiment = null;
    private JLabel etage = null;
    private JLabel salle = null;

    private JPanel baseGlobale = new JPanel();
    private JButton buttonClose;

    private ArrayList<Graphe> listeGraphes;
    private ArrayList<InformationsCapteur> listeInformationsCapteurs;

    private boolean ouvert;


    /**
     * Le getter 'isOuvert' renvoie la valeur du booléen 'ouvert'
     */
    public boolean isOuvert() {
        return this.ouvert;
    }


    /**
     * Le setter 'setOuvert' modifie la valeur du booléen 'ouvert'
     */
    public void setOuvert(boolean bool) {
        this.ouvert = bool;
    }


    public AffichageGraphe() {
        setOuvert(false);
    }


    /**
     * La fonction 'fermerFenetreErreur' n'est utilisée que si, pour une raison inconnue, la lecture du fichier laisse passer un pourcentage supérieur à 100%
     */
    public void fermerFenetreErreur() {
        System.out.println("Erreur logique : pourcentage supérieur à 100 !");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }


    /**
     * La fonction 'verifLieu' vérifie où se trouve le InformationsCapteur (intérieur/extérieur) et affiche les valeurs en conséquence.
     *
     * @param capt : il s'agit du InformationsCapteur à visualiser
     */
    private void verifLieu(InformationsCapteur capt) {
        String[] informations = capt.getLocalisation().getString().split(";");

        if (capt.getLocalisation().getType().equals("Interieur")) {
            this.descriptionLoc = new JLabel("     LieuCapteur :  Intérieur");
            this.batiment = new JLabel("               Bâtiment " + informations[0]);
            this.etage = new JLabel("               " + informations[1] + "ème étage");
            this.salle = new JLabel("               Salle " + informations[2]);
        }
        else {
            this.descriptionLoc = new JLabel("     LieuCapteur :  Extérieur");
            this.latitude = new JLabel("          Latitude    :  " + informations[0]);
            this.longitude = new JLabel("          Longitude :  " + informations[1]);
        }
    }


    /**
     * La fonction 'creationGraphe' créé les différentes partie de la fenêtre
     *
     * @param lC : cette liste contient tous les capteurs à visualiser
     */
    public void creationGraphe(ArrayList<InformationsCapteur> lC) {
        listeInformationsCapteurs = lC;
        listeGraphes = new ArrayList<>();

        ImageIcon iconeFenetre = new ImageIcon("Icon.png");
        this.setIconImage(iconeFenetre.getImage());
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        int nbMaxGraphe = listeInformationsCapteurs.size();
        int taille;

        //Récupération de tous les capteurs
        for (int cmpt = 0 ; cmpt < nbMaxGraphe ; cmpt ++) {
            taille = 400 + (2 * listeInformationsCapteurs.get(cmpt).getListeMesures().size() + 1) * 45;

            Graphe jc = new Graphe();
            jc.setValeur(listeInformationsCapteurs.get(cmpt).getListeMesures());
            jc.setBackground(Color.WHITE);
            jc.setPreferredSize(new Dimension(taille-250,500));
            jc.setxMax(200 + (2 * listeInformationsCapteurs.get(cmpt).getListeMesures().size() + 1) * 5);

            listeGraphes.add(jc);
        }

        InformationsCapteur capt = listeInformationsCapteurs.get(0);

        String ID = capt.getType();
        verifLieu(capt);

        //Informations du capteur
        haut.setMinimumSize(new Dimension(240, 150));
        haut.setMaximumSize(new Dimension(240, 150));
        haut.setBorder(BorderFactory.createTitledBorder(ID));
        haut.setLayout(new BoxLayout(haut, BoxLayout.PAGE_AXIS));
        haut.setAlignmentX(0);
        haut.setBackground(Color.WHITE);
        haut.add(new JLabel(" "));
        haut.add(this.descriptionLoc);
        if (capt.getLocalisation().getType().equals("Interieur")) {
            haut.add(this.batiment);
            haut.add(this.etage);
            haut.add(this.salle);
        }
        else {
            haut.add(this.latitude);
            haut.add(this.longitude);
        }
        haut.add(new JLabel(" "));

        //ComboBox pour sélectionner le capteur
        JComboBox ensembleCapteurs = new JComboBox();
        ensembleCapteurs.setMaximumSize(new Dimension(200, 60));
        for (int i = 0; i < listeInformationsCapteurs.size() ; i++) {
            ensembleCapteurs.addItem(listeInformationsCapteurs.get(i).getType());
        }

        JPanel milieu = new JPanel();
        milieu.setMinimumSize(new Dimension(240, 80));
        milieu.setMaximumSize(new Dimension(240, 80));
        milieu.setLayout(new BoxLayout(milieu, BoxLayout.PAGE_AXIS));
        milieu.setAlignmentX(0);
        milieu.setBackground(Color.white);
        milieu.setBorder(BorderFactory.createTitledBorder("Choix du capteurs"));
        milieu.add(new JLabel(" "));
        milieu.add(ensembleCapteurs);
        milieu.add(new JLabel(" "));

        //Bouton de fermeture
        this.buttonClose = new JButton("Fermer");
        this.buttonClose.setMaximumSize(new Dimension(200, 150));

        //Lorsque l'on clique sur le bouton 'Fermer', la fenêtre doit mettre le booléen 'ouvert' sur 'false'
        this.buttonClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                if(e.getSource() == buttonClose){
                    setVisible(false);
                    dispose();
                    setOuvert(false);
                }

            }
        });

        JPanel bas = new JPanel();
        bas.setMinimumSize(new Dimension(240, 60));
        bas.setMaximumSize(new Dimension(240, 60));
        bas.setAlignmentX(0);
        bas.setBackground(Color.white);
        bas.setBorder(BorderFactory.createTitledBorder("Fermer la fenêtre"));
        bas.add(this.buttonClose, BorderLayout.SOUTH);
        bas.setAlignmentX(0);

        //Regroupement des informations, de la comboBox et du bouton de fermeture sur la gauche
        int largeurGauche = 240;

        gauche.setMinimumSize(new Dimension(largeurGauche, 620));
        gauche.setPreferredSize(new Dimension(largeurGauche, 620));
        gauche.setMaximumSize(new Dimension(largeurGauche, 4000));
        gauche.setLayout(new BoxLayout(gauche, BoxLayout.PAGE_AXIS));
        gauche.setBorder(BorderFactory.createTitledBorder("Informations"));
        gauche.add(this.haut);
        gauche.add(new JLabel(" "));
        gauche.add(milieu);
        gauche.add(new JLabel(" "));
        gauche.add(bas);


        //Création du graphe sur la droite
        int largeurDroite = 2*120 + listeGraphes.get(0).getxMax();
        taille = largeurGauche + largeurDroite + 30;

        droite.setMinimumSize(new Dimension(largeurDroite, 600));
        droite.setPreferredSize(new Dimension(largeurDroite, 600));
        droite.setLayout(new BoxLayout(droite, BoxLayout.LINE_AXIS));
        droite.setBorder(BorderFactory.createTitledBorder("Graphique"));
        droite.add(new JScrollPane(listeGraphes.get(0)));

        baseGlobale.setPreferredSize(new Dimension(800, 650));
        baseGlobale.setLayout(new BoxLayout(baseGlobale, BoxLayout.LINE_AXIS));
        baseGlobale.add(gauche);
        baseGlobale.add(new JLabel(" "));
        baseGlobale.add(droite);

        container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));
        container.setMinimumSize(new Dimension(800, 700));
        container.setPreferredSize(new Dimension(800, 750));
        container.setMaximumSize(new Dimension(1920, 1080));
        container.add(baseGlobale);


        //Modification du graphe lors d'un changement de capteur : on récupère les bonnes informations, on les place dans le graphe, et on l'actualise
        ensembleCapteurs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                if(e.getSource() == ensembleCapteurs){
                    int numCapteur = ensembleCapteurs.getSelectedIndex();
                    droite.removeAll();
                    droite.add(listeGraphes.get(numCapteur));
                    droite.add(new JScrollPane(listeGraphes.get(numCapteur)), BorderLayout.CENTER);

                    droite.revalidate();
                    droite.repaint();

                    haut.removeAll();
                    haut.setBorder(BorderFactory.createTitledBorder(listeInformationsCapteurs.get(numCapteur).getType()));
                    haut.add(new JLabel(" "));
                    verifLieu(listeInformationsCapteurs.get(numCapteur));
                    haut.add(descriptionLoc);
                    if (listeInformationsCapteurs.get(numCapteur).getLocalisation().getType().equals("Interieur")) {
                        haut.add(batiment);
                        haut.add(etage);
                        haut.add(salle);
                    }
                    else {
                        haut.add(latitude);
                        haut.add(longitude);
                    }
                    haut.add(new JLabel(" "));

                    haut.revalidate();
                    haut.repaint();
                }

            }
        });


        //Lorsque l'on clique sur la croix de fermeture, la fenêtre doit mettre le booléen 'ouvert' sur 'false'
        this.addWindowListener(new WindowAdapter() {
            @Override public void windowClosing (WindowEvent e) {
                super.windowClosing(e);
                setVisible(false);
                dispose();
                setOuvert(false);
            }
        });


        //Informations de la fenêtre
        this.getContentPane().add(container);
        this.setVisible(true);
        this.setTitle("Graphe");
        this.setSize(taille, 680);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }
}
