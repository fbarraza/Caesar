/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.orchis.controladors;

import app.orchis.classes.AppConfig;
import app.orchis.model.Usuari;
import static app.orchis.utils.Alertes.avis;
import static app.orchis.utils.Alertes.info;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.Root;
import org.hibernate.HibernateException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author m15
 */
public class AltaUsuarisControllerTest {
    
    private EntityManagerFactory emf;
    
    public AltaUsuarisControllerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        AppConfig appConfig = AppConfig.getInstance();
        appConfig.setPersistenceUnit("app.orchis.persistencia");

        try {
            emf = appConfig.loadAppConfig();
        } catch (Exception e){
            System.out.println("Error!");
        }        
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of eliminarUsuari method, of class AltaUsuarisController.
     */
    @Test    
    public void testcrearUsuari() throws Exception {
        //Variables
        /*EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        //Afegim usuari a la base de dades
        try {
        em.persist(usuari);
        em.getTransaction().commit();   
        info("Usuari introduït satisfactòriament");      
        }catch (HibernateException ex) {                    


                avis("Error a la hora d'inserir l'usuari! Nom d'usuari ja existeix!");
                em.getTransaction().rollback();
                System.out.println(ex.getMessage());

        }
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");*/
    }
    /**
     * Test of eliminarUsuari method, of class AltaUsuarisController.
     */
    @Test
    public void testeliminarUsuari() throws Exception {
        System.out.println("Eliminar Usuari");
        EntityManager em = emf.createEntityManager();
        CriteriaBuilder cb = emf.getCriteriaBuilder();
        CriteriaDelete<Usuari> delete = cb.createCriteriaDelete(Usuari.class);                        
        Root<Usuari> c = delete.from(Usuari.class);
        Usuari usuari = new Usuari();
        //Sentència SQL
        delete.where(cb.equal(c.get("codi"), usuari.getCodi()));

        //Actualitzar BBDD
        em.getTransaction().begin();
        em.createQuery(delete).executeUpdate();
        em.getTransaction().commit();  
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of canviarContrasenya method, of class AltaUsuarisController.
     */
    @Test
    public void testCanviarContrasenya() throws Exception {
        System.out.println("canviarContrasenya");
        char opc = ' ';
        AltaUsuarisController instance = new AltaUsuarisController();
        instance.canviarContrasenya(opc);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
