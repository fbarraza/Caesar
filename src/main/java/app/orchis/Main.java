package app.orchis;

import app.orchis.model.Usuari;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;


/**
 *
 * @author Bobob
 */
public class Main {
   /* private static EntityManager manager;
    private static EntityManagerFactory emf;

    public static void main(String[] args) {
        // Creem el gestor de la persistència
        emf = Persistence.createEntityManagerFactory( "cat.m15.hibernate.demo" );
        manager = emf.createEntityManager();

        List<Usuari> llista = (List<Usuari>) manager.createQuery("FROM Usuari").getResultList();

        for(Usuari u : llista)
        {
            System.out.println(u);
        }

        if (manager.isOpen()) {
            manager.close();
        }
        emf.close();
    }*/
    
}
