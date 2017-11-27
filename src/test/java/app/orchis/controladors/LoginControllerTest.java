/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.orchis.controladors;

import app.orchis.model.Usuari;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javax.persistence.EntityManagerFactory;
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
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
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
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of bloqueigApp method, of class LoginController.
     */
    @Test
    public void testBloqueigApp() {
        System.out.println("bloqueigApp");
        LoginController instance = new LoginController();
        instance.bloqueigApp();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of usuariBloquejat method, of class LoginController.
     */
    @Test
    public void testUsuariBloquejat() {
        System.out.println("usuariBloquejat");
        String user = "";
        LoginController instance = new LoginController();
        instance.usuariBloquejat(user);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of intents method, of class LoginController.
     */
    @Test
    public void testIntents_String() throws Exception {
        System.out.println("intents");
        String username = "";
        LoginController instance = new LoginController();
        instance.intents(username);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of intents method, of class LoginController.
     */
    @Test
    public void testIntents_String_List() throws Exception {
        System.out.println("intents");
        String username = "";
        List<Usuari> userlist = null;
        LoginController instance = new LoginController();
        instance.intents(username, userlist);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of Login method, of class LoginController.
     */
    @Test
    public void testLogin() throws Exception {
        System.out.println("Login");
        LoginController instance = new LoginController();
        instance.Login();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
