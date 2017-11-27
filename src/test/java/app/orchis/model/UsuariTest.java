/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.orchis.model;

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
public class UsuariTest {
    
    public UsuariTest() {
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
     * Test of getCodi method, of class Usuari.
     */
    @Test
    public void testGetCodi() {
        System.out.println("getCodi");
        Usuari instance = new Usuari();
        long expResult = 0L;
        long result = instance.getCodi();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCodi method, of class Usuari.
     */
    @Test
    public void testSetCodi() {
        System.out.println("setCodi");
        int codi = 0;
        Usuari instance = new Usuari();
        instance.setCodi(codi);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNom method, of class Usuari.
     */
    @Test
    public void testGetNom() {
        System.out.println("getNom");
        Usuari instance = new Usuari();
        String expResult = "";
        String result = instance.getNom();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setNom method, of class Usuari.
     */
    @Test
    public void testSetNom() {
        System.out.println("setNom");
        String nom = "";
        Usuari instance = new Usuari();
        instance.setNom(nom);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLogin method, of class Usuari.
     */
    @Test
    public void testGetLogin() {
        System.out.println("getLogin");
        Usuari instance = new Usuari();
        String expResult = "";
        String result = instance.getLogin();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setLogin method, of class Usuari.
     */
    @Test
    public void testSetLogin() {
        System.out.println("setLogin");
        String login = "";
        Usuari instance = new Usuari();
        instance.setLogin(login);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isBloquejat method, of class Usuari.
     */
    @Test
    public void testIsBloquejat() {
        System.out.println("isBloquejat");
        Usuari instance = new Usuari();
        boolean expResult = false;
        boolean result = instance.isBloquejat();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setBloquejat method, of class Usuari.
     */
    @Test
    public void testSetBloquejat() {
        System.out.println("setBloquejat");
        boolean bloquejat = false;
        Usuari instance = new Usuari();
        instance.setBloquejat(bloquejat);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPasswd method, of class Usuari.
     */
    @Test
    public void testGetPasswd() {
        System.out.println("getPasswd");
        Usuari instance = new Usuari();
        String expResult = "";
        String result = instance.getPasswd();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPasswd method, of class Usuari.
     */
    @Test
    public void testSetPasswd() {
        System.out.println("setPasswd");
        String passwd = "";
        Usuari instance = new Usuari();
        instance.setPasswd(passwd);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Usuari.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Usuari instance = new Usuari();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
