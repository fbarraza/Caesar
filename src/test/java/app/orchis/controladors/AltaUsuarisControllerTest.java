/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.orchis.controladors;

import app.orchis.classes.AppConfig;
import app.orchis.model.Usuari;
import static app.orchis.model.Usuari.obtenirUsuari;
import static app.orchis.utils.Alertes.avis;
import static app.orchis.utils.Alertes.info;
import java.net.URL;
import java.text.ParseException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
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
    private Usuari user = new Usuari();
    
    public AltaUsuarisControllerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws ParseException {
        AppConfig appConfig = AppConfig.getInstance();
        appConfig.setPersistenceUnit("app.orchis.persistencia");

        user.setNom("JUnit");
        user.setLogin("junit");
        user.getAvui();
        
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
        user.afegirUsuari(emf);
        user = obtenirUsuari(emf,user.getLogin());
        
        //Obtenir usuari
        assertNotNull(user.getLogin());
        
    }
    /**
     * Test of eliminarUsuari method, of class AltaUsuarisController.
     */
    @Test
    public void testeliminarUsuari(){
        user.eliminarUsuari(emf);

    }

    /**
     * Test of canviarContrasenya method, of class AltaUsuarisController.
     */
    /*
    @Test
    public void testCanviarContrasenya() throws Exception {
        System.out.println("canviarContrasenya");
        char opc = ' ';
        AltaUsuarisController instance = new AltaUsuarisController();
        instance.canviarContrasenya(opc);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }*/
}
