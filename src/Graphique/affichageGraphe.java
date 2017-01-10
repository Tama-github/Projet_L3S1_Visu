package Graphique;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;


public class affichageGraphe extends JFrame {
    private JLayeredPane container = new JLayeredPane();

    private JPanel droite = new JPanel();

    //private JSplitPane gauche = null;
    private JPanel gauche = new JPanel();
    private JPanel haut = new JPanel();
    private JPanel milieu = new JPanel();

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
    private ArrayList<capteur> listeCapteurs;
    //private capteur capt;
    private int typeGraphe = 0;
    private int nbMaxGraphe = 1;


    /**
     * La fonction 'verifLieu' vérifie où se trouve le capteur (intérieur/extérieur) et affiche les valeurs en conséquence.
     *
     * @param capt : il s'agit du capteur à visualiser
     */
    public void verifLieu(capteur capt) {
        if (capt.getLocalisation().lieu == 0) {
            //LOC += loc.inside.batiment + " " + loc.inside.etage + " " + loc.inside.salle;
            this.descriptionLoc = new JLabel("     Interface.Localisation :  Intérieur");
            this.batiment = new JLabel("               Bâtiment " + capt.getLocalisation().inside.batiment);
            this.etage = new JLabel("               " + Integer.toString(capt.getLocalisation().inside.etage) + "ème étage");
            this.salle = new JLabel("               Salle " + Integer.toString(capt.getLocalisation().inside.salle));
        }
        else {
            //LOC += "Extérieur";
            this.descriptionLoc = new JLabel("     Interface.Localisation :  Extérieur");
            this.latitude = new JLabel("          Latitude    :  " + capt.getLocalisation().outside.latitude);
            this.longitude = new JLabel("          Longitude :  " + capt.getLocalisation().outside.longitude);
        }
    }


    /**
     * La fonction 'affichageGraphe' créé les différentes partie de la fenêtre
     *
     * @param lC : cette liste contient tous les capteurs à visualiser
     */
    //public affichageGraphe(String nom, ArrayList<mesure> listeMesures, localisation loc) {
    public affichageGraphe(ArrayList<capteur> lC) { //, int choice) {
        listeCapteurs = lC;
        listeGraphes = new ArrayList<Graphe>();

        ImageIcon iconeFenetre = new ImageIcon("Icon.png");
        this.setIconImage(iconeFenetre.getImage());

        capteur capt = null;

        nbMaxGraphe = listeCapteurs.size();
        int taille = 0;

        for (int cmpt = 0 ; cmpt < nbMaxGraphe ; cmpt ++) {
            //capt = listeCapteurs.get(cmpt);
            taille = 400 + (2 * listeCapteurs.get(cmpt).getListeMesures().size() + 1) * 45;

            Graphe jc = new Graphe();
            jc.setValeur(listeCapteurs.get(cmpt).getListeMesures());
            jc.setBackground(Color.WHITE);
            jc.setPreferredSize(new Dimension(taille-250,500));

            listeGraphes.add(jc);
        }

        taille = 400 + (2 * listeCapteurs.get(0).getListeMesures().size() + 1) * 45;

        capt = listeCapteurs.get(0);

        String ID = "     ID : " + capt.getType();
        verifLieu(capt);
        this.id = new JLabel(ID);


        Icon icone;

        Image imageClose = Toolkit.getDefaultToolkit().getImage("Images/close.png");

        if (imageClose != null) {
            icone = new ImageIcon(imageClose);
            this.buttonClose = new JButton(icone);
        }
        else {
            this.buttonClose = new JButton("Fermer");
        }
        this.buttonClose.setMaximumSize(new Dimension(200, 150));

        this.buttonClose.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                if(e.getSource() == buttonClose){
                    setVisible(false);
                    dispose();
                }

            }
        });

        this.setTitle("Graphe");
        this.setSize(taille, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);


        //Font police = new Font("Arial", Font.BOLD, 12);

        //milieu.setMaximumSize(new Dimension(200, 175));
        milieu.setBorder(BorderFactory.createTitledBorder("Informations du capteur"));
        milieu.setLayout(new BoxLayout(milieu, BoxLayout.PAGE_AXIS));
        milieu.setBackground(Color.WHITE);
        milieu.add(this.id);
        milieu.add(new JLabel(" "));
        milieu.add(this.descriptionLoc);
        if (capt.getLocalisation().lieu == 0) {
            milieu.add(this.batiment);
            milieu.add(this.etage);
            milieu.add(this.salle);
        }
        else {
            milieu.add(this.latitude);
            milieu.add(this.longitude);
        }
        milieu.add(new JLabel(" "));

        //La couleur des ensembleCapteurs
        JComboBox ensembleCapteurs = new JComboBox();
        ensembleCapteurs.setMaximumSize(new Dimension(200, 60));
        for (int i = 0 ; i < listeCapteurs.size() ; i++) {
            ensembleCapteurs.addItem(listeCapteurs.get(i).getType());
        }

        JPanel bas = new JPanel();
        bas.setMaximumSize(new Dimension(200, 100));
        bas.setLayout(new BoxLayout(bas, BoxLayout.PAGE_AXIS));
        bas.setBackground(Color.white);
        bas.setBorder(BorderFactory.createTitledBorder("Capteurs"));
        bas.add(new JLabel(" "));
        bas.add(ensembleCapteurs);
        bas.add(new JLabel(" "));

        //haut.setMaximumSize(new Dimension(200, 300));
        //haut.setLayout(new BoxLayout(haut, BoxLayout.PAGE_AXIS));
        //haut.add(this.buttonGraph, BorderLayout.CENTER);
        haut.add(this.buttonClose, BorderLayout.SOUTH);

        //TODO: gauche -> pas de maximum de taille
        //gauche.setPreferredSize(new Dimension(200, 600));
        gauche.setMaximumSize(new Dimension(250, 600));
        gauche.setLayout(new BoxLayout(gauche, BoxLayout.PAGE_AXIS));
        gauche.setBorder(BorderFactory.createTitledBorder("Informations"));
        gauche.add(this.haut);
        gauche.add(new JLabel(" "));
        gauche.add(this.milieu);
        gauche.add(new JLabel(" "));
        gauche.add(bas);

        droite.setPreferredSize(new Dimension(620, 620));
        droite.setLayout(new BoxLayout(droite, BoxLayout.LINE_AXIS));
        droite.setBorder(BorderFactory.createTitledBorder("Graphique"));
        droite.add(listeGraphes.get(typeGraphe));
        droite.add(new JScrollPane(listeGraphes.get(typeGraphe)), BorderLayout.CENTER);

        //baseGlobale = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, gauche, this.droite);
        //baseGlobale.setDividerLocation(200);
        baseGlobale.setPreferredSize(new Dimension(800, 650));
        container.setMaximumSize(new Dimension(1000, 800));
        baseGlobale.setLayout(new BoxLayout(baseGlobale, BoxLayout.LINE_AXIS));
        baseGlobale.add(gauche);
        baseGlobale.add(new JLabel(" "));
        baseGlobale.add(droite);

        container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));
        container.setMinimumSize(new Dimension(800, 700));
        container.setPreferredSize(new Dimension(800, 750));
        container.add(baseGlobale);






        ensembleCapteurs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                if(e.getSource() == ensembleCapteurs){
                    int numCapteur = ensembleCapteurs.getSelectedIndex();
                    droite.removeAll();
                    droite.add(listeGraphes.get(numCapteur));
                    droite.add(new JScrollPane(listeGraphes.get(numCapteur)), BorderLayout.CENTER);

                    droite.revalidate();
                    droite.repaint();

                    id = new JLabel("     ID : " + listeCapteurs.get(numCapteur).getType());

                    milieu.removeAll();
                    milieu.add(new JLabel(" "));
                    milieu.add(id);
                    milieu.add(new JLabel(" "));
                    verifLieu(listeCapteurs.get(numCapteur));
                    milieu.add(descriptionLoc);
                    if (listeCapteurs.get(numCapteur).getLocalisation().lieu == 0) {
                        milieu.add(batiment);
                        milieu.add(etage);
                        milieu.add(salle);
                    }
                    else {
                        milieu.add(latitude);
                        milieu.add(longitude);
                    }

                    milieu.revalidate();
                    milieu.repaint();
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

                    String ID = "     ID : " + listeCapteurs.get(typeGraphe).type;
                    id = new JLabel(ID);
                    //milieu.remove(1);
                    //milieu.add(id, 1);

                    milieu.removeAll();
                    milieu.add(new JLabel(" "));
                    milieu.add(id);
                    milieu.add(new JLabel(" "));
                    verifLieu(listeCapteurs.get(typeGraphe));
                    milieu.add(descriptionLoc);
                    if (listeCapteurs.get(typeGraphe).loc.lieu == 0) {
                        milieu.add(batiment);
                        milieu.add(etage);
                        milieu.add(salle);
                    }
                    else {
                        milieu.add(latitude);
                        milieu.add(longitude);
                    }



                    milieu.revalidate();
                    milieu.repaint();
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
