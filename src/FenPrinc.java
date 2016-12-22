import javax.swing.*;

/**
 * Created by jb on 19/12/16.
 */
public class FenPrinc extends JFrame {

    public FenPrinc()
    {
        super("Visualisation");
        this.setSize(400, 600);
        JPanel panelGlobal = new JPanel();

        TableauDonnees tableau = new TableauDonnees();


        this.add(panelGlobal);
        this.setVisible(true);
    }

    public static void main(String[] args)
    {
        FenPrinc fenetrePrincipale = new FenPrinc();
    }
}
