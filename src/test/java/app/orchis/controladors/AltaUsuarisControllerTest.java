/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.orchis.controladors;

import app.orchis.classes.AppConfig;
import app.orchis.model.MasterModel;
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
    private MasterModel<Usuari> helperU;
    
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
            emf = appConfig.loadAppConfig('m');
            helperU = new MasterModel(emf, Usuari.class);
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
        helperU.afegir(user,false);
        user = obtenirUsuari(emf,user.getLogin());
        
        //Obtenir usuari
        assertNotNull(user.getLogin());
        helperU.eliminar(user,false);
        
    }
    
    /**
     * Test of eliminarUsuari method, of class AltaUsuarisController.
     */
    @Test(expected = NoResultException.class)
    public void testCercaUsuari(){
        System.out.println("testCercaUsuari");
        
        //Obtenim usuari
        user = obtenirUsuari(emf,user.getLogin());
        
        //Comprovem usuari
        assertEquals(user.getNom(),"JUnit");

    }    
    
    
    @Test
    public void testModificarUsuari(){
        System.out.println("testModificarUsuari");
        Usuari user2;
        
        //Modifiquem usuari
        helperU.afegir(user,false);
        user.setLogin("junit2");
        helperU.actualitzar(user,false);
        
        //Obtenim usuari
        user2 = obtenirUsuari(emf,"junit2");
        assertEquals(user.getLogin(),user2.getLogin());
        
        helperU.eliminar(user,false);
        
    }
    /**
     * Test of eliminarUsuari method, of class AltaUsuarisController.
     */
    @Test(expected = NoResultException.class)
    public void testEliminarUsuari(){
        System.out.println("testEliminarUsuari");
        
        //Afegim usuari
        helperU.afegir(user,false);
        user = obtenirUsuari(emf,user.getLogin());
        
        //Eliminem usuari
        helperU.eliminar(user,false);
        user = obtenirUsuari(emf,user.getLogin());

    }
}
