/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.orchis.controladors;

import app.orchis.model.Usuari;
import static app.orchis.utils.eines.AppPropertiesFileHelper.llegirFitxerPropietats;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author m15
 */
public class LoginControllerTest {
    
    public LoginControllerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        Usuari user = new Usuari(3,"Patatrufen","lol",false,"potato");
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of generar method, of class LoginController.
     */
    @Test
    public void testGenerar() {
        System.out.println("generar");
        EntityManagerFactory expResult = null;
        EntityManagerFactory result = LoginController.generar();
        assertThat(result, not(expResult));
    }

    /**
     * Test of initialize method, of class LoginController.
     */
    @Test
    public void testInitialize() {
        System.out.println("initialize");
        URL location = null;
        ResourceBundle resources = null;
        LoginController instance = new LoginController();
        instance.initialize(location, resources);
    }

    /**
     * Test of bloqueigApp method, of class LoginController.
     */
    @Test
    public void testBloqueigApp() {
        System.out.println("bloqueigApp");
        LoginController instance = new LoginController();
        instance.bloqueigApp();
    }

    /**
     * Test of usuariBloquejat method, of class LoginController.
     */
    @Test
    public void testUsuariBloquejat() {
        System.out.println("usuariBloquejat");
        String user = "dtrump";
        LoginController instance = new LoginController();
        instance.usuariBloquejat(user);
    }

    /**
     * Test of intents method, of class LoginController.
     */
    @Test
    public void testIntents_String() throws Exception {
        System.out.println("intents");
        String username = "dtrump";
        LoginController instance = new LoginController();
        instance.intents(username);
    }

    /**
     * Test of intents method, of class LoginController.
     */
    @Test
    public void testIntents_String_List() throws Exception {
        System.out.println("intents");
        List<Usuari> userlist = null;
        Usuari user = new Usuari(3,"Patatrufen","lol",false,"potato");
        userlist.add(user);
        LoginController instance = new LoginController();
        instance.intents(userlist.get(0).getLogin(), userlist);
    }

    /**
     * Test of Login method, of class LoginController.
     */
    @Test
    public void testLogin() throws Exception {
        System.out.println("Login");
        LoginController instance = new LoginController();
        instance.Login();
    }
    
}
