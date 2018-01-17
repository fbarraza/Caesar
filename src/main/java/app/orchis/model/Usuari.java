package app.orchis.model;

import static app.orchis.utils.Alertes.avis;
import static app.orchis.utils.CryptoHelper.encripta;
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
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import org.hibernate.exception.ConstraintViolationException;
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
    
    @Column(name = "login", unique=true)
    private String login;    

    @Column(name = "password")
    private String passwd;
    
    @Column(name = "bloquejat")
    private boolean bloquejat;
    
    @Column(name ="data")
    private Date data;
    
    @Column(name = "admin")
    private boolean admin;    

    //Constructor
    public Usuari() {
        
    }

    public Usuari(int codi, String nom, String login, String passwd, boolean bloquejat, Date data, boolean admin) {
        this.codi = codi;
        this.nom = nom;
        this.login = login;
        this.passwd = passwd;
        this.bloquejat = bloquejat;
        this.data = data;
        this.admin = admin;
    }

    //Funcions
    /**
     * Obté l'usuari de la BBDD
     * @param emf
     * @param username
     * @return 
     */
    public static Usuari obtenirUsuari(EntityManagerFactory emf, String username){
        //Variables
        EntityManager em = emf.createEntityManager();
        Usuari user = new Usuari();
        //Obtenir dades de l'usuari introduit
        CriteriaBuilder cb = emf.getCriteriaBuilder();
        CriteriaQuery<Usuari> cbQuery = cb.createQuery(Usuari.class);
        Root<Usuari> c = cbQuery.from(Usuari.class);
        cbQuery.select(c);
        cbQuery.where(cb.equal(c.get("login"), username));
        Query query = em.createQuery(cbQuery);           
        
        //Obtenir usuari
        user = (Usuari) query.getSingleResult();
        em.close();
        
        return user;
    }
    
    public static Usuari obteAdmin(List<Usuari> llista){        
        for(int i=0; i<llista.size();i++){
            if(llista.get(i).isAdmin()){
                return llista.get(i);
            }
        }     
        return null;
    }    
 
    public void actualitzaPasswd(EntityManagerFactory emf, String nou){
            //Creació Entity Manager i del CB
            EntityManager manager = emf.createEntityManager();
            CriteriaBuilder cb = emf.getCriteriaBuilder();
            CriteriaUpdate<Usuari> update = cb.createCriteriaUpdate(Usuari.class);                        
            Root<Usuari> c = update.from(Usuari.class);
            
            
            //Sentència SQL
            update.set("passwd", nou);
            update.where(cb.equal(c.get("codi"), this.getCodi())); 
            
            //Actualitzar BBDD
            manager.getTransaction().begin();
            manager.createQuery(update).executeUpdate();
            manager.getTransaction().commit();     
            
            manager.close();
    }
    
    //GETTERS AND SETTERS
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
    
    public void setUsuari(Usuari user){
        this.codi = (int) user.getCodi();
        this.nom = user.getNom();
        this.login = user.getLogin();
        this.passwd = user.getPasswd();
        this.bloquejat = user.isBloquejat();
        this.data = user.getData();
        this.admin = user.isAdmin();
    }
    
    public Date getAvui() throws ParseException{
        Date data = Calendar.getInstance().getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String avui = format.format(data);     

        data = format.parse(avui);
        
        return data;
    }    
}
