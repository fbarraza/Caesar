package app.orchis.utils.eines;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;


public class AppPropertiesFileHelper {

    private final String propertyFileName;
    private final String propertyKey;
    private final String isPropertyKeyEncrypted;
    private final String encryptorPasswd = "m15.orchis.password";

    private String decryptedUserPassword;
    private boolean verbose = false;

    public String getDecryptedUserPassword() {
        return decryptedUserPassword;
    }

    /**
     *
     * El constructor fa la gran majoria de la feina
     * Inicialitza totes les variables finals i invoca 2 mètodes
     * per les tasques d'enccriptació i desencriptació.
     * Després d'una feina exitosa el constructor posa la contrasenya
     * desencriptada en una variable que pot ser obtesa mitjançant una
     * crida a la classe
     *
     * @param pPropertyFileName       /Nom del fitxer de propietats que conté la contrasenya
     * @param pUserPasswordKey        /Left hand side of the password property as key.
     * @param pIsPasswordEncryptedKey /Clau al fitxer de propietats. Indica si la contrasenya està encriptada o no
     * @param verbose /Mostra el que fa if true
     * @throws PropertiesHelperException    /Excepció
     * @throws ConfigurationException   /Excepció
     */
    public AppPropertiesFileHelper(String pPropertyFileName, String pUserPasswordKey, String pIsPasswordEncryptedKey, boolean verbose) throws PropertiesHelperException, ConfigurationException {
        this.propertyFileName = pPropertyFileName;
        this.propertyKey = pUserPasswordKey;
        this.isPropertyKeyEncrypted = pIsPasswordEncryptedKey;
        try {
            encryptPropertyValue(verbose);
        } catch (ConfigurationException e) {
            throw new PropertiesHelperException("El procés d'encriptació ha fallat", e);
        }

        decryptedUserPassword = decryptPropertyValue(verbose);
    }

    /***
     *
     * Mètode que encripta la contrasenya al fitxer de propietats
     * Primer comprova si la contrasenya està encriptada o no.
     * Si no està encriptada, solament encriptarà la contrasenya
     *
     * @param verbose /Ensenya el que fa if true
     * @throws ConfigurationException  /Excepció de configuració
     */    
    public void encryptPropertyValue(boolean verbose) throws ConfigurationException {
        if (verbose) System.out.println("Iniciant procés d'encriptació");
        if (verbose) System.out.println("Llegint fitxer de propietats");

        //Apache Commons Configuration
        PropertiesConfiguration config = new PropertiesConfiguration(propertyFileName);

        // Obté el valor booleà de la propietat per comprovar si la contrasenya ja està encriptada
        String isEncrypted = config.getString(isPropertyKeyEncrypted);

        //Està la contrasenya encriptada?
        if (isEncrypted.equals("false")) {
            String tmpPwd = config.getString(propertyKey);
            //System.out.println(tmpPwd);
            // Encriptem
            StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();

            // Aquesta és una contrasenya necessària per Jaspyt.
            // Has d'utilitzar la mateixa contrasenya per obtenir la contrasenya desencriptada més endavant
            // Aquesta contrasenya no és la contrasenya que volem encriptar
            encryptor.setPassword(encryptorPasswd);
            String encryptedPassword = encryptor.encrypt(tmpPwd);
            if (verbose) System.out.println("Procés d'encriptació finalitzat. La contrasenya encriptada és: " + encryptedPassword);

            // Sobresescriu la contrasenya amb la contrasenya encriptada al fitxer de propietats
            // utilitzant la llibreria Apache Commons Configuration
            config.setProperty(propertyKey, encryptedPassword);
            // Ajusta el valor boolea a true per indicar que ja està encriptar
            config.setProperty(isPropertyKeyEncrypted, "true");
            // Guarda el fitxer de propietats
            config.save();
        } else {
            if (verbose) System.out.println("La contrasenya d'usuari ja està encriptada.\n ");
        }
    }

    private String decryptPropertyValue(boolean verbose) throws ConfigurationException {
        if (verbose) System.out.println("Iniciant desencriptació");
        PropertiesConfiguration config = new PropertiesConfiguration(propertyFileName);
        String encryptedPropertyValue = config.getString(propertyKey);
        //System.out.println(encryptedPropertyValue);

        StandardPBEStringEncryptor encryptor = new StandardPBEStringEncryptor();
        encryptor.setPassword(encryptorPasswd);
        String decryptedPropertyValue = encryptor.decrypt(encryptedPropertyValue);

        //System.out.println(decryptedPropertyValue);

        return decryptedPropertyValue;
    }    
    
    public static Map llegirFitxerPropietats(String filename, char opc) {
        InputStream fitxer = null;
        Properties credencials = new Properties();
        Map properties = new HashMap();

        try {
            fitxer = AppPropertiesFileHelper.class.getClassLoader().getResourceAsStream(filename);

            if (fitxer == null) {
                System.err.println("No puc llegir " + filename);
            } else {
                credencials.load(fitxer);
                
                if(opc=='m'){
                    properties.put("javax.persistence.jdbc.user", credencials.getProperty("jdbc.username"));
                }
                else if(opc=='o'){
                    properties.put("javax.persistence.jdbc.user", credencials.getProperty("jdbcOdoo.username"));
                }
                
                String p = getEncryptedPassword(opc);
                if (p != null)
                    properties.put("javax.persistence.jdbc.password", p);
                else {
                    throw new PropertiesHelperException("EL procés de recuperació de la contrasenya ha fallat");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (PropertiesHelperException e) {
            e.printStackTrace();
            properties = null;
        } finally {
            if (fitxer != null) {
                try {
                    fitxer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return properties;
    }
    
    private static String getEncryptedPassword(char opc) {
        // Generar/Obtenir la contrasenya encriptada
        AppPropertiesFileHelper appPropertiesFileHelper = null;

        try {
            if(opc=='m'){
            appPropertiesFileHelper = new AppPropertiesFileHelper("app.properties",
                                                                  "jdbc.password",
                                                                  "encrypted",
                                                                  false);
            }
            else if(opc=='o'){
            appPropertiesFileHelper = new AppPropertiesFileHelper("app.properties",
                                                                  "jdbcOdoo.password",
                                                                  "encryptedOdoo",
                                                                  true);                
            }

        } catch (PropertiesHelperException e) {
            e.printStackTrace();
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }

        return appPropertiesFileHelper.getDecryptedUserPassword();
    }    
}
