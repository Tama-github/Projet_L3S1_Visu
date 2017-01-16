public class Mesure {
    protected String typeUnite;
    protected String unite;
    protected int val;
    protected int max;
    protected int min;
    protected String uniteTemps;

    public Mesure(String typeUnite, String unite, int valeur, int maximum, int minimum, String temps) {
        this.typeUnite = typeUnite;
        this.unite = unite;
        this.val = valeur;
        this.max = maximum;
        this.min = minimum;
        this.uniteTemps = temps;
    }
}
