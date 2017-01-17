import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.regex.Pattern;

import javax.swing.*;

/**
* Credit icone Maxim Basinski
* */


public class FenetreConnexionIP extends JFrame {
	private JPanel container = new JPanel();
	private JPanel ligneHaut = new JPanel();
	private JPanel ligneHautG = new JPanel();
	private JPanel ligneHautD = new JPanel();
	private JPanel ligneBas = new JPanel();
	
	private JLabel labelError = new JLabel();

	Pattern ipPattern = Pattern.compile("^([0-9]{1,3}\\.){3}[0-9]{1,3}$");
	private JTextField jtfIP = new JTextField();
	private JLabel label1 = new JLabel("Adresse IP : ");
	
	private JSpinner jtfPORT = new JSpinner();
	private JLabel label2 = new JLabel("     Port : ");
	
	private JButton buttonConnection = new JButton("Connexion");
	private JButton buttonCancel = new JButton("Annuler");
	private ProtocolManager protocolManager;
	private FenetreVisualisation fenetreVisualisation;
	
	public FenetreConnexionIP(FenetreVisualisation fenetreVisualisation){

		this.fenetreVisualisation = fenetreVisualisation;
		this.setTitle("Connexion");
		this.setSize(500, 100);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLocationRelativeTo(null);

		ImageIcon icone = new ImageIcon("Icon.png");
		this.setIconImage(icone.getImage());

		Font police = new Font("Arial", Font.BOLD, 12);
		jtfIP.setFont(police);
		jtfIP.setPreferredSize(new Dimension(125, 30));	// La taille n'est pas respectée pour raison inconnue...
		jtfIP.setForeground(Color.BLACK);
		
		jtfPORT.setFont(police);
		jtfPORT.setPreferredSize(new Dimension(125, 30));	// La taille n'est pas respectée pour raison inconnue...
		jtfPORT.setForeground(Color.BLACK);
		
		
		ligneHaut.setPreferredSize(new Dimension(125, 30));
		ligneHaut.setLayout(new BoxLayout(ligneHaut, BoxLayout.LINE_AXIS));
		ligneHautG.add(label1);
		ligneHautG.add(jtfIP);
		ligneHautD.add(label2);
		ligneHautD.add(jtfPORT);
		ligneHaut.add(ligneHautG);
		ligneHaut.add(ligneHautD);
		
		
		ligneBas.setLayout(new BoxLayout(ligneBas, BoxLayout.LINE_AXIS));
		ligneBas.add(buttonConnection);
		//ligneBas.add(buttonCancel);
	    

	    container.setLayout(new BoxLayout(container, BoxLayout.PAGE_AXIS));
	    container.add(ligneHaut);
	    container.add(ligneBas);
        container.add(labelError);
			
	    this.getContentPane().add(container);
        this.setVisible(true);


		this.getButtonConnexion().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				super.mouseClicked(e);
				if (verifIP(getJtfIP().getText()) && (int) getJtfPORT().getValue() >= 0) {
					getLabelError().setText("");
					protocolManager = new ProtocolManager(getJtfIP().getText(), (int)getJtfPORT().getValue());

					try {
						if (protocolManager.connexionVisu().equals("ConnexionKO")) {
							printErr("Erreur lors de la connexion au serveur");
						} else {
							setVisible(false);
							fenetreVisualisation.setProtocolManager(protocolManager);
							fenetreVisualisation.setVisible(true);
						}
					} catch (IOException e1) {
						printErr("Erreur lors de la connexion au serveur");
					}
				} else {
					printErr("L'adresse IP et le port doivent etre valide.");
				}
			}
		});
	}


	/**
	 *  Champs d'dresse IP
	 *
	 *  @return JTextField : Renvoi le champs de texte contenant l'adresse IP de connexion
	 */
	public JTextField getJtfIP() {
		return this.jtfIP;
	}


	/**
	 *  Champs du port
	 *
	 *  @return JSpinner : Renvoi le champs de texte contenant le port de connexion
	 */
	public JSpinner getJtfPORT() {
		return this.jtfPORT;
	}


	/**
	 *  Bouton "Connexion"
	 *
	 *  @return JButton : Renvoi le bouton de connexion
	 */
	public JButton getButtonConnexion() {
		return this.buttonConnection;
	}


	/**
	 *  Bouton "Annuler"
	 *
	 *  @return JButton : Renvoi le bouton d'annulation
	 */
	public JButton getButtonCancel() {
		return this.buttonCancel;
	}


	/**
	 *  Vérification de la conformité de l'IP passée en paramètre
	 *
	 *  @param ip String : L'adresse IP à tester
	 *
	 *  @return boolean : Renvoi true si l'adresse IP est conforme
	 */
	public boolean verifIP (String ip) {
		return ipPattern.matcher(ip).find();
	}

	public void printErr(String msg)
	{
		this.labelError.setForeground(Color.red);
		this.labelError.setText(msg);
		this.setSize(500,150);
	}

    public JLabel getLabelError() {
        return labelError;
    }

}
