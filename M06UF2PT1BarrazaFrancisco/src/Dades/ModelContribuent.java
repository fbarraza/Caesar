package Dades;

public class ModelContribuent {

    private int IDCon;
    private String NIF;
    private String Nom;
    private String Domicili;

    public ModelContribuent() {

    }

    public ModelContribuent(int idcon, String nif, String nom, String domicili) {
        this.IDCon = idcon;
        this.NIF = nif;
        this.Nom = nom;
        this.Domicili = domicili;
    }

    public int getIDCon() {
        return IDCon;
    }

    public void setIDCon(int IDCon) {
        this.IDCon = IDCon;
    }

    public String getNIF() {
        return NIF;
    }

    public void setNIF(String NIF) {
        this.NIF = NIF;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String Nom) {
        this.Nom = Nom;
    }

    public String getDomicili() {
        return Domicili;
    }

    public void setDomicili(String Domicili) {
        this.Domicili = Domicili;
    }

}
