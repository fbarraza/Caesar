/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.orchis.controladors;

import app.orchis.classes.AppConfig;
import app.orchis.model.Usuari;
import static app.orchis.model.Usuari.obtenirUsuari;
import java.net.URL;
import java.text.ParseException;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
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
    public void testCrearUsuari() throws Exception {
        System.out.println("testCrearUsuari");
        //Variables
        user.afegir(emf);
        user = obtenirUsuari(emf,user.getLogin());
        
        //Obtenir usuari
        assertNotNull(user.getLogin());
        user.eliminar(emf);
        
    }
    
    @Test
    public void testModificarUsuari(){
        System.out.println("testModificarUsuari");
        Usuari user2;
        
        //Modifiquem usuari
        user.afegir(emf);
        user.setLogin("junit2");
        user.actualitzar(emf);
        
        //Obtenim usuari
        user2 = obtenirUsuari(emf,"junit2");
        assertEquals(user.getLogin(),user2.getLogin());
        
        user.eliminar(emf);
        
    }
    /**
     * Test of eliminarUsuari method, of class AltaUsuarisController.
     */
    @Test(expected = NoResultException.class)
    public void testEliminarUsuari(){
        System.out.println("testEliminarUsuari");
        
        //Afegim usuari
        user.afegir(emf);
        user = obtenirUsuari(emf,user.getLogin());
        
        //Eliminem usuari
        user.eliminar(emf);
        user = obtenirUsuari(emf,user.getLogin());

    }

    /**
     * Test of canviarContrasenya method, of class AltaUsuarisController.
     */
}
