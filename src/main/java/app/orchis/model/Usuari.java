package app.orchis.model;

import static app.orchis.utils.Alertes.avis;
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
    
    public static Usuari obtenirUsuari(EntityManagerFactory emf, String username){
        //Variables
        EntityManager em = emf.createEntityManager();
        Usuari user;
        
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
        
        return  user;
    }
    
    public void afegirUsuari(EntityManagerFactory emf){
        //Variables
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        //Afegim usuari a la base de dades
        try {
        em.persist(this);
        //em.flush();
        em.getTransaction().commit();   
        
        }catch (Exception ex) {                    
            if (ex.getCause() instanceof ConstraintViolationException){
                em.getTransaction().rollback();
                avis("Error a la hora d'inserir l'usuari! Nom d'usuari ja existeix!");                        
                System.out.println(ex.getMessage());
            }
        }        
    }    
    
    public void actualitzarUsuari(EntityManagerFactory emf){
        //Variables Query
        CriteriaBuilder cb = emf.getCriteriaBuilder();
        CriteriaUpdate<Usuari> update = cb.createCriteriaUpdate(Usuari.class);                        
        Root<Usuari> c = update.from(Usuari.class);     

        //Sentència SQL
        update.set("nom", this.nom);   
        update.set("login", this.login);
        update.set("passwd", this.passwd);  
        update.set("bloquejat", this.bloquejat);
        update.set("data", this.data);
        update.set("admin", this.admin);                 
        update.where(cb.equal(c.get("codi"), this.codi));  
        
        //Actualitzar BBDD
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.createQuery(update).executeUpdate();
        em.getTransaction().commit(); 
        
        em.close();
    }
    
    public void eliminarUsuari(EntityManagerFactory emf){
        EntityManager em = emf.createEntityManager();
        CriteriaBuilder cb = emf.getCriteriaBuilder();
        CriteriaDelete<Usuari> delete = cb.createCriteriaDelete(Usuari.class);                        
        Root<Usuari> c = delete.from(Usuari.class);

        //Sentència SQL
        delete.where(cb.equal(c.get("codi"), this.getCodi()));

        //Actualitzar BBDD
        em.getTransaction().begin();
        em.createQuery(delete).executeUpdate();
        em.getTransaction().commit();  
        
        //Tencar Entity
        em.close();
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
