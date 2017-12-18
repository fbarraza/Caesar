package app.orchis.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
//import java.util.Date;



@Entity
@Table (name = "user")
public class Usuari implements Serializable{
 

    private static final long serialVersionUID = 1;

    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private int codi;

    @Column(name = "nom")
    private String nom;

    @Column(name = "password")
    private String passwd;
    
    @Column(name = "bloquejat")
    private boolean bloquejat;
    
    @Column(name = "login", unique=true)
    private String login;
    
    @Column(name ="data")
    private Date data;
    
    @Column(name = "admin")
    private boolean admin;    

    //Constructor
    public Usuari() {
    }

    public Usuari(int codi, String nom, String passwd, boolean bloquejat, String login, Date data, boolean admin) {
        this.codi = codi;
        this.nom = nom;
        this.passwd = passwd;
        this.bloquejat = bloquejat;
        this.login = login;
        this.data = data;
        this.admin = admin;
    }

    public long getCodi() {
        return codi;
    }

    public void setCodi(int codi) {
        this.codi = codi;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public boolean isBloquejat() {
        return bloquejat;
    }

    public void setBloquejat(boolean bloquejat) {
        this.bloquejat = bloquejat;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
    
    public Date getAvui() throws ParseException{
        Date data = Calendar.getInstance().getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String avui = format.format(data);     

        data = format.parse(avui);
        
        return data;
    }
    
    public void actualitzarBloqueig(){
        
    }
    

    @Override
    public String toString() {
        return "Usuari{" +
                "codi=" + codi +
                ", nom='" + nom + '\'' +
                ", login='" + login + '\'' +
                ", bloquejat=" + bloquejat +
                '}';
    }
    
}
