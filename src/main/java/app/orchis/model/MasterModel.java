/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.orchis.model;

import static app.orchis.utils.Alertes.avis;
import java.util.ArrayList;
import java.util.List;
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
    
    //Vars
    private EntityManagerFactory emf;
    private Class<T> placeClass;
   
    
    //Constructors
    public MasterModel(EntityManagerFactory emf, Class<T> placeClass) {
        this.emf = emf;
        this.placeClass = placeClass;
    }
    
    
    /**
     * Afegeix/Insereix l'objecte a la BBDD.
     * @param t
     */
    public void afegir(T t){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(t);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (ex.getCause() instanceof ConstraintViolationException){
                em.getTransaction().rollback();
                avis("Error al inserir! Ja existeix!");                        
                System.out.println(ex.getMessage());
            }
        } finally {
            em.close();
        }          
    }    
    
    /**
     * Actualitza l'objecte en la BBDD.
     * @param t 
     */
    public void actualitzar(T t){
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.merge(t);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }        
    }
    
    /**
     * Elimina l'objecte en la BBDD.
     * @param t 
     */
    public void eliminar(T t){
        EntityManager em = emf.createEntityManager();
        
        em.getTransaction().begin();
        try {
            em.remove(em.merge(t));
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }                
    }    
    
    public List<T> getAll() {
        CriteriaBuilder cb = this.emf.getCriteriaBuilder();
        EntityManager manager = this.emf.createEntityManager();

        CriteriaQuery<T> cbQuery = cb.createQuery(placeClass);
        Root<T> c = cbQuery.from(placeClass);
        cbQuery.select(c);

        Query query = manager.createQuery(cbQuery);

        return query.getResultList();
    }

}
