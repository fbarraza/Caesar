/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.orchis.utils;

import app.orchis.model.Usuari;
import app.orchis.utils.eines.AppPropertiesFileHelper;
import app.orchis.utils.eines.PropertiesHelperException;
import org.apache.commons.configuration.ConfigurationException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;


/**
 *
 * @author m15
 */
public class EntityMan {
            
    public static void connectar(){
       // Creem el gestor de la persistència
        Map properties = null;

        properties = llegirFitxerPropietats("app.properties");

        if (properties == null) {
            System.out.println("Error greu. Contacti amb l'administrador");
        } else {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("app.orchis.persistencia", properties);


            EntityManager manager = emf.createEntityManager();

            List<Usuari> llista = (List<Usuari>) manager.createQuery("FROM " + Usuari.class.getName()).getResultList();

            for (Usuari u : llista) {
                System.out.println(u);
            }

            if (manager.isOpen()) {
                manager.close();
            }
            emf.close();
        }
    }
    protected static Map llegirFitxerPropietats(String filename) {
        InputStream fitxer = null;
        Properties credencials = new Properties();
        Map properties = new HashMap();

        try {
            fitxer = EntityMan.class.getClassLoader().getResourceAsStream(filename);

            if (fitxer == null) {
                System.err.println("No puc llegir " + filename);
            } else {
                credencials.load(fitxer);

                properties.put("javax.persistence.jdbc.user", credencials.getProperty("jdbc.username"));
                String p = getEncryptedPassword();
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
    protected static String getEncryptedPassword() {
        // Generar/Obtenir la contrasenya encriptada
        AppPropertiesFileHelper appPropertiesFileHelper = null;

        try {
            appPropertiesFileHelper = new AppPropertiesFileHelper("app.properties",
                                                                  "jdbc.password",
                                                                  "encrypted",
                                                                  false);

        } catch (PropertiesHelperException e) {
            e.printStackTrace();
        } catch (ConfigurationException e) {
            e.printStackTrace();
        }

        return appPropertiesFileHelper.getDecryptedUserPassword();
    }
    
}
