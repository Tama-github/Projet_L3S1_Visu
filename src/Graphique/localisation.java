package Graphique;

public class localisation {

    protected int lieu;	/*si 0 intérieur, sinon extérieur */
    protected interieur inside;
    protected exterieur outside;

    public localisation (int where, interieur in, exterieur out) {
        this.lieu = where;
        this.inside = in;
        this.outside = out;
    }

}
