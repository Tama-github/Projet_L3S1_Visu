public class LieuCapteur {

    protected int lieu;	/*si 0 intérieur, sinon extérieur */
    protected Interieur inside;
    protected Exterieur outside;

    public LieuCapteur(int where, Interieur in, Exterieur out) {
        this.lieu = where;
        this.inside = in;
        this.outside = out;
    }

}
