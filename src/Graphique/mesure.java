package Graphique;

public class mesure {
    protected String typeUnite;
    protected String unite;
    protected int val;
    protected int max;

    public mesure (String typeUnite, String unite, int valeur, int maximum) {
        this.typeUnite = typeUnite;
        this.unite = unite;
        this.val = valeur;
        this.max = maximum;
    }
}
