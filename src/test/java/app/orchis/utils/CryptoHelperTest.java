/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.orchis.utils;

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
public class CryptoHelperTest {
    
    public CryptoHelperTest() {
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
     * Test of encripta method, of class CryptoHelper.
     */
    @Test
    public void testEncripta() {
        System.out.println("encripta");
        String s = "";
        String expResult = "";
        String result = CryptoHelper.encripta(s);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of testPassword method, of class CryptoHelper.
     */
    @Test
    public void testTestPassword() {
        System.out.println("testPassword");
        String bbdd = "";
        String encriptat = "";
        boolean expResult = false;
        boolean result = CryptoHelper.testPassword(bbdd, encriptat);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of desencriptarPublic method, of class CryptoHelper.
     */
    @Test
    public void testDesencriptarPublic() throws Exception {
        System.out.println("desencriptarPublic");
        byte[] array = null;
        String expResult = "";
        String result = CryptoHelper.desencriptarPublic(array);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
