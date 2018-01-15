/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.orchis.model;

import static app.orchis.utils.Alertes.avis;
import java.util.ArrayList;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.exception.ConstraintViolationException;

/**
 *
 * @author m15
 */
public class MasterModel<T> {
    
    //private ArrayList<T> llista;
    //private Class<T> placeClass;
   
   /**
     * Afegeix/Insereix l'objecte a la BBDD.
     * @param emf EntityManagerFactory per passar la connexió.
     */
    public void afegir(EntityManagerFactory emf){
        //Variables
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        //Afegim usuari a la base de dades
        try {
        em.persist(this);
        //em.flush();
        em.getTransaction().commit();   
        
        }catch (Exception ex) {                    
            if (ex.getCause() instanceof ConstraintViolationException){
                em.getTransaction().rollback();
                avis("Error al inserir! Ja existeix!");                        
                System.out.println(ex.getMessage());
            }
        }finally {
            em.close();
        }           
    }    
    
    /**
     * Actualitza l'objecte en la BBDD.
     * @param emf EntityManagerFactory per passar la connexió.
     */
    public void actualitzar(EntityManagerFactory emf){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.merge(this);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }        
    }
    
    /**
     * Elimina l'objecte en la BBDD.
     * @param emf EntityManagerFactory per passar la connexió.
     */
    public void eliminar(EntityManagerFactory emf){
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        try {
            em.remove(em.merge(this));
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }        
        
    }    
}
