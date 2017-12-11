/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.orchis.classes;
import static app.orchis.utils.eines.AppPropertiesFileHelper.llegirFitxerPropietats;
import java.util.Map;
import javax.persistence.*;

/**
 * Patró Singleton
 *
 * Solament 1 instància de la classe
 */
public class AppConfig {
    private String persistenceUnit;

    private static AppConfig _instancia;

    public static AppConfig getInstance() {
        if (_instancia == null)
            _instancia = new AppConfig();

        return _instancia;
    }

    private AppConfig() {

    }

    public EntityManagerFactory loadAppConfig(){
        if (!persistenceUnit.isEmpty()) {
            Map properties = llegirFitxerPropietats("app.properties");
            final EntityManagerFactory emf = Persistence.createEntityManagerFactory(persistenceUnit,properties);
            //emf.close();
            return emf;
            
        } else {
            System.out.println("Nom de la unitat de persistència incorrecte");
            return null;
        }     
    }

    // Getters & Setters
    public String getPersistenceUnit() {
        return persistenceUnit;
    }

    public void setPersistenceUnit(String persistenceUnit) {
        this.persistenceUnit = persistenceUnit;
    }
}