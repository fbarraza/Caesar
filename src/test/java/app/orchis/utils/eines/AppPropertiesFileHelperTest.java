/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.orchis.utils.eines;

import java.util.Map;
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
public class AppPropertiesFileHelperTest {
    
    public AppPropertiesFileHelperTest() {
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
     * Test of getDecryptedUserPassword method, of class AppPropertiesFileHelper.
     */
    @Test
    public void testGetDecryptedUserPassword() {
        System.out.println("getDecryptedUserPassword");
        AppPropertiesFileHelper instance = null;
        String expResult = "";
        String result = instance.getDecryptedUserPassword();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of encryptPropertyValue method, of class AppPropertiesFileHelper.
     */
    @Test
    public void testEncryptPropertyValue() throws Exception {
        System.out.println("encryptPropertyValue");
        boolean verbose = false;
        AppPropertiesFileHelper instance = null;
        instance.encryptPropertyValue(verbose);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of llegirFitxerPropietats method, of class AppPropertiesFileHelper.
     */
    @Test
    public void testLlegirFitxerPropietats() {
        System.out.println("llegirFitxerPropietats");
        String filename = "";
        Map expResult = null;
        Map result = AppPropertiesFileHelper.llegirFitxerPropietats(filename);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
