import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class AffichageGraphe extends JFrame {
    private JLayeredPane container = new JLayeredPane();

    private JPanel droite = new JPanel();

    //private JSplitPane gauche = null;
    private JPanel gauche = new JPanel();
    private JPanel bas = new JPanel();
    private JPanel haut = new JPanel();

    private JLabel labelError = new JLabel();

    private JLabel hautGauche;

    private JLabel id;
    //private JLabel valID;

    private JLabel descriptionLoc = null;
    private JLabel latitude = null;
    private JLabel longitude = null;
    private JLabel batiment = null;
    private JLabel etage = null;
    private JLabel salle = null;
    //private JLabel valLoc;

    private JLabel test = new JLabel("     Test : ");
    private JLabel valTest = new JLabel("test n°1");

    private JLabel test2 = new JLabel("Test : ");
    private JLabel valTest2 = new JLabel("test n°2");

    //private JSplitPane baseGlobale;
    private JPanel baseGlobale = new JPanel();

    //private JPanel ligneBas = new JPanel();

    /*private JPanel ligneHaut = new JPanel();

    private JLabel labelError = new JLabel();

    private JTextField jtfIP = new JTextField();
    private JLabel label1 = new JLabel("Adresse IP : ");

    private JTextField jtfPORT = new JTextField();
    private JLabel label2 = new JLabel("     Port : ");

    private JButton buttonConnection = new JButton("Connexion");*/
    //private JButton button1;
    //private JButton buttonGraph;
    private JButton buttonClose;

    private ArrayList<Graphe> listeGraphes;
    private ArrayList<InformationsCapteur> listeInformationsCapteurs;
    //private InformationsCapteur capt;
    private int typeGraphe = 0;
    private int nbMaxGraphe = 1;

    public AffichageGraphe() {

    }


    public void fermerFenetreErreur() {
        System.out.println("Erreur logique : pourcentage supérieur à 100 !");
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }


    /**
     * La fonction 'verifLieu' vérifie où se trouve le InformationsCapteur (intérieur/extérieur) et affiche les valeurs en conséquence.
     *
     * @param capt : il s'agit du InformationsCapteur à visualiser
     */
    public void verifLieu(InformationsCapteur capt) {
        if (capt.getLocalisation().lieu == 0) {
            //LOC += loc.inside.batiment + " " + loc.inside.etage + " " + loc.inside.salle;
            this.descriptionLoc = new JLabel("     LieuCapteur :  Intérieur");
            this.batiment = new JLabel("               Bâtiment " + capt.getLocalisation().inside.batiment);
            this.etage = new JLabel("               " + Integer.toString(capt.getLocalisation().inside.etage) + "ème étage");
            this.salle = new JLabel("               Salle " + Integer.toString(capt.getLocalisation().inside.salle));
        }
        else {
            //LOC += "Extérieur";
            this.descriptionLoc = new JLabel("     LieuCapteur :  Extérieur");
            this.latitude = new JLabel("          Latitude    :  " + capt.getLocalisation().outside.latitude);
            this.longitude = new JLabel("          Longitude :  " + capt.getLocalisation().outside.longitude);
        }
    }


    /**
     * La fonction 'creationGraphe' créé les différentes partie de la fenêtre
     *
     * @param lC : cette liste contient tous les capteurs à visualiser
     */
    //public creationGraphe(String nom, ArrayList<Mesure> listeMesures, LieuCapteur loc) {
    public void creationGraphe(ArrayList<InformationsCapteur> lC) { //, int choice) {

        listeInformationsCapteurs = lC;
        listeGraphes = new ArrayList<Graphe>();

        ImageIcon iconeFenetre = new ImageIcon("Icon.png");
        this.setIconImage(iconeFenetre.getImage());
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        InformationsCapteur capt = null;

        nbMaxGraphe = listeInformationsCapteurs.size();
        int taille = 0;

        for (int cmpt = 0 ; cmpt < nbMaxGraphe ; cmpt ++) {
            //capt = listeInformationsCapteurs.get(cmpt);
            taille = 400 + (2 * listeInformationsCapteurs.get(cmpt).getListeMesures().size() + 1) * 45;

            Graphe jc = new Graphe();
            jc.setValeur(listeInformationsCapteurs.get(cmpt).getListeMesures());
            jc.setBackground(Color.WHITE);
            jc.setPreferredSize(new Dimension(taille-250,500));
            jc.setxMax(200 + (2 * listeInformationsCapteurs.get(cmpt).getListeMesures().size() + 1) * 5);

            listeGraphes.add(jc);
        }

        capt = listeInformationsCapteurs.get(0);

        String ID = capt.getType();
        verifLieu(capt);
        //this.id = new JLabel(ID);


        /*Icon icone;

        Image imageClose = Toolkit.getDefaultToolkit().getImage("Images/close.png");

        if (imageClose != null) {
            icone = new ImageIcon(imageClose);
            this.buttonClose = new JButton(icone);
        }
        else {
            this.buttonClose = new JButton("Fermer");
        }*/

        this.buttonClose = new JButton("Fermer");

        this.buttonClose.setMaximumSize(new Dimension(200, 150));

        this.buttonClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                if(e.getSource() == buttonClose){
                    setVisible(false);
                    dispose();
                }

            }
        });


        //Font police = new Font("Arial", Font.BOLD, 12);

        haut.setMinimumSize(new Dimension(240, 150));
        haut.setMaximumSize(new Dimension(240, 150));
        haut.setBorder(BorderFactory.createTitledBorder(ID));
        haut.setLayout(new BoxLayout(haut, BoxLayout.PAGE_AXIS));
        haut.setAlignmentX(0);
        haut.setBackground(Color.WHITE);
        haut.add(new JLabel(" "));
        haut.add(this.descriptionLoc);
        if (capt.getLocalisation().lieu == 0) {
            haut.add(this.batiment);
            haut.add(this.etage);
            haut.add(this.salle);
        }
        else {
            haut.add(this.latitude);
            haut.add(this.longitude);
        }
        haut.add(new JLabel(" "));

        //La couleur des ensembleCapteurs
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

        //bas.setMaximumSize(new Dimension(200, 300));
        //bas.setLayout(new BoxLayout(bas, BoxLayout.PAGE_AXIS));
        //bas.add(this.buttonGraph, BorderLayout.CENTER);
        bas.setMinimumSize(new Dimension(240, 60));
        bas.setMaximumSize(new Dimension(240, 60));
        bas.setAlignmentX(0);
        bas.setBackground(Color.white);
        bas.setBorder(BorderFactory.createTitledBorder("Fermer la fenêtre"));
        bas.add(this.buttonClose, BorderLayout.SOUTH);
        bas.setAlignmentX(0);

        int largeurGauche = 240;

        //TODO: gauche -> pas de maximum de taille
        //gauche.setPreferredSize(new Dimension(200, 600));
        gauche.setMinimumSize(new Dimension(largeurGauche, 620));
        gauche.setPreferredSize(new Dimension(largeurGauche, 620));
        gauche.setMaximumSize(new Dimension(largeurGauche, 4000));
        gauche.setLayout(new BoxLayout(gauche, BoxLayout.PAGE_AXIS));
        gauche.setBorder(BorderFactory.createTitledBorder("Informations"));
        gauche.add(this.haut);
        gauche.add(new JLabel(" "));
        gauche.add(milieu);
        gauche.add(new JLabel(" "));
        gauche.add(this.bas);

        int largeurDroite = 2*120 + listeGraphes.get(0).getxMax();

        //taille = 400 + (2 * listeInformationsCapteurs.get(1).getListeMesures().size() + 1) * 45;
        //taille = listeGraphes.get(0).getxMax() + 250 + 20;
        taille = largeurGauche + largeurDroite + 30;

        droite.setMinimumSize(new Dimension(largeurDroite, 600));
        droite.setPreferredSize(new Dimension(largeurDroite, 600));
        //droite.setMaximumSize(new Dimension(taille - gauche.getWidth() - 20, 620));
        droite.setLayout(new BoxLayout(droite, BoxLayout.LINE_AXIS));
        droite.setBorder(BorderFactory.createTitledBorder("Graphique"));
        droite.add(listeGraphes.get(typeGraphe));
        droite.add(new JScrollPane(listeGraphes.get(typeGraphe)), BorderLayout.CENTER);

        //baseGlobale = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, gauche, this.droite);
        //baseGlobale.setDividerLocation(200);
        baseGlobale.setPreferredSize(new Dimension(800, 650));
        baseGlobale.setLayout(new BoxLayout(baseGlobale, BoxLayout.LINE_AXIS));
        baseGlobale.add(gauche);
        baseGlobale.add(new JLabel(" "));
        baseGlobale.add(droite);

        container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));
        container.setMinimumSize(new Dimension(800, 700));
        container.setMaximumSize(new Dimension(1000, 800));
        container.setPreferredSize(new Dimension(800, 750));
        container.setMaximumSize(new Dimension(1920, 1080));
        container.add(baseGlobale);


        this.setTitle("Graphe");
        this.setSize(taille, 650);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);






        ensembleCapteurs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                if(e.getSource() == ensembleCapteurs){
                    int numCapteur = ensembleCapteurs.getSelectedIndex();
                    droite.removeAll();
                    droite.add(listeGraphes.get(numCapteur));
                    droite.add(new JScrollPane(listeGraphes.get(numCapteur)), BorderLayout.CENTER);

                    droite.revalidate();
                    droite.repaint();

                    id = new JLabel("     ID : " + listeInformationsCapteurs.get(numCapteur).getType());

                    haut.removeAll();
                    haut.setBorder(BorderFactory.createTitledBorder(listeInformationsCapteurs.get(numCapteur).getType()));
                    haut.add(new JLabel(" "));
                    verifLieu(listeInformationsCapteurs.get(numCapteur));
                    haut.add(descriptionLoc);
                    if (listeInformationsCapteurs.get(numCapteur).getLocalisation().lieu == 0) {
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



        /*this.buttonGraph.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                if(e.getSource() == buttonGraph){
                    typeGraphe = (typeGraphe + 1) % nbMaxGraphe;
                    droite.removeAll();
					/*droite = new JPanel();
					droite.setPreferredSize(new Dimension(600, 600));
					droite.setLayout(new BoxLayout(droite, BoxLayout.LINE_AXIS)); * /
                    droite.add(listeGraphes.get(typeGraphe));
                    droite.add(new JScrollPane(listeGraphes.get(typeGraphe)), BorderLayout.CENTER);

                    droite.revalidate();
                    droite.repaint();

                    String ID = "     ID : " + listeInformationsCapteurs.get(typeGraphe).type;
                    id = new JLabel(ID);
                    //haut.remove(1);
                    //haut.add(id, 1);

                    haut.removeAll();
                    haut.add(new JLabel(" "));
                    haut.add(id);
                    haut.add(new JLabel(" "));
                    verifLieu(listeInformationsCapteurs.get(typeGraphe));
                    haut.add(descriptionLoc);
                    if (listeInformationsCapteurs.get(typeGraphe).loc.lieu == 0) {
                        haut.add(batiment);
                        haut.add(etage);
                        haut.add(salle);
                    }
                    else {
                        haut.add(latitude);
                        haut.add(longitude);
                    }



                    haut.revalidate();
                    haut.repaint();
                }

            }
        });*/

		/*this.button1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				if(e.getSource() == button1){
					droite = new JPanel();
					droite.setPreferredSize(new Dimension(600, 600));
					droite.setLayout(new BoxLayout(droite, BoxLayout.LINE_AXIS));
					droite.add(listeGraphes.get(typeGraphe));
					JScrollPane scroll = new JScrollPane(listeGraphes.get(typeGraphe));
					droite.add(scroll, BorderLayout.CENTER);

					//droite.revalidate();
					droite.repaint();
				}

			}
		});*/

		/*this.buttonGraph.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				if(e.getSource() == buttonClose){
					typeGraphe = (typeGraphe + 1) % nbMaxGraphe;
					droite.removeAll();
					droite.add(listeGraphes.get(typeGraphe));
					JScrollPane scroll = new JScrollPane(listeGraphes.get(typeGraphe));
					droite.add(scroll, BorderLayout.CENTER);
					container.removeAll();
				    container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));
				    container.add(baseGlobale);
				}

			}
		});*/

        this.getContentPane().add(container);
        this.setVisible(true);
    }


	/*public JTextField getJtfIP() {
		return this.jtfIP;
	}


	public JTextField getJtfPORT() {
		return this.jtfPORT;
	}


	public JButton getButtonConnection() {
		return this.buttonConnection;
	}


	public boolean verifIP () {
		// À faire
		return false;
	}*/


    public JButton getButtonClose() {
        return this.buttonClose;
    }
}
