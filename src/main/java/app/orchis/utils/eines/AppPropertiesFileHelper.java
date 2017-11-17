package app.orchis.utils.eines;

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
     * @throws Exception
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
     * @throws ConfigurationException
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
}
