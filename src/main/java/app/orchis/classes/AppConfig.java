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
    //Variables de la classe
    private String persistenceUnit;
    private static AppConfig _instancia;

    /**
     * Retorna instància 
     * @return 
     */
    public static AppConfig getInstance() {
        if (_instancia == null)
            _instancia = new AppConfig();

        return _instancia;
    }

    /**
     * Constructor sense paràmetres
     */
    private AppConfig() {

    }

    /**
     * Crea la connexió amb la base de dades fent servir el fitxer app.config.
     * @return Retorna connexió BBDD.
     */
    public EntityManagerFactory loadAppConfig(char opc){
        if (!persistenceUnit.isEmpty()) {
            Map properties = llegirFitxerPropietats("app.properties", opc);
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