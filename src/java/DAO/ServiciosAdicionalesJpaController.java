/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.exceptions.IllegalOrphanException;
import DAO.exceptions.NonexistentEntityException;
import DAO.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import DTO.ServicioXplan;
import DTO.ServiciosAdicionales;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author jeiso
 */
public class ServiciosAdicionalesJpaController implements Serializable {

    public ServiciosAdicionalesJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ServiciosAdicionales serviciosAdicionales) throws PreexistingEntityException, Exception {
        if (serviciosAdicionales.getServicioXplanCollection() == null) {
            serviciosAdicionales.setServicioXplanCollection(new ArrayList<ServicioXplan>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<ServicioXplan> attachedServicioXplanCollection = new ArrayList<ServicioXplan>();
            for (ServicioXplan servicioXplanCollectionServicioXplanToAttach : serviciosAdicionales.getServicioXplanCollection()) {
                servicioXplanCollectionServicioXplanToAttach = em.getReference(servicioXplanCollectionServicioXplanToAttach.getClass(), servicioXplanCollectionServicioXplanToAttach.getServicioXplanPK());
                attachedServicioXplanCollection.add(servicioXplanCollectionServicioXplanToAttach);
            }
            serviciosAdicionales.setServicioXplanCollection(attachedServicioXplanCollection);
            em.persist(serviciosAdicionales);
            for (ServicioXplan servicioXplanCollectionServicioXplan : serviciosAdicionales.getServicioXplanCollection()) {
                ServiciosAdicionales oldServiciosAdicionalesOfServicioXplanCollectionServicioXplan = servicioXplanCollectionServicioXplan.getServiciosAdicionales();
                servicioXplanCollectionServicioXplan.setServiciosAdicionales(serviciosAdicionales);
                servicioXplanCollectionServicioXplan = em.merge(servicioXplanCollectionServicioXplan);
                if (oldServiciosAdicionalesOfServicioXplanCollectionServicioXplan != null) {
                    oldServiciosAdicionalesOfServicioXplanCollectionServicioXplan.getServicioXplanCollection().remove(servicioXplanCollectionServicioXplan);
                    oldServiciosAdicionalesOfServicioXplanCollectionServicioXplan = em.merge(oldServiciosAdicionalesOfServicioXplanCollectionServicioXplan);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findServiciosAdicionales(serviciosAdicionales.getCodigoServicio()) != null) {
                throw new PreexistingEntityException("ServiciosAdicionales " + serviciosAdicionales + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ServiciosAdicionales serviciosAdicionales) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ServiciosAdicionales persistentServiciosAdicionales = em.find(ServiciosAdicionales.class, serviciosAdicionales.getCodigoServicio());
            Collection<ServicioXplan> servicioXplanCollectionOld = persistentServiciosAdicionales.getServicioXplanCollection();
            Collection<ServicioXplan> servicioXplanCollectionNew = serviciosAdicionales.getServicioXplanCollection();
            List<String> illegalOrphanMessages = null;
            for (ServicioXplan servicioXplanCollectionOldServicioXplan : servicioXplanCollectionOld) {
                if (!servicioXplanCollectionNew.contains(servicioXplanCollectionOldServicioXplan)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ServicioXplan " + servicioXplanCollectionOldServicioXplan + " since its serviciosAdicionales field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<ServicioXplan> attachedServicioXplanCollectionNew = new ArrayList<ServicioXplan>();
            for (ServicioXplan servicioXplanCollectionNewServicioXplanToAttach : servicioXplanCollectionNew) {
                servicioXplanCollectionNewServicioXplanToAttach = em.getReference(servicioXplanCollectionNewServicioXplanToAttach.getClass(), servicioXplanCollectionNewServicioXplanToAttach.getServicioXplanPK());
                attachedServicioXplanCollectionNew.add(servicioXplanCollectionNewServicioXplanToAttach);
            }
            servicioXplanCollectionNew = attachedServicioXplanCollectionNew;
            serviciosAdicionales.setServicioXplanCollection(servicioXplanCollectionNew);
            serviciosAdicionales = em.merge(serviciosAdicionales);
            for (ServicioXplan servicioXplanCollectionNewServicioXplan : servicioXplanCollectionNew) {
                if (!servicioXplanCollectionOld.contains(servicioXplanCollectionNewServicioXplan)) {
                    ServiciosAdicionales oldServiciosAdicionalesOfServicioXplanCollectionNewServicioXplan = servicioXplanCollectionNewServicioXplan.getServiciosAdicionales();
                    servicioXplanCollectionNewServicioXplan.setServiciosAdicionales(serviciosAdicionales);
                    servicioXplanCollectionNewServicioXplan = em.merge(servicioXplanCollectionNewServicioXplan);
                    if (oldServiciosAdicionalesOfServicioXplanCollectionNewServicioXplan != null && !oldServiciosAdicionalesOfServicioXplanCollectionNewServicioXplan.equals(serviciosAdicionales)) {
                        oldServiciosAdicionalesOfServicioXplanCollectionNewServicioXplan.getServicioXplanCollection().remove(servicioXplanCollectionNewServicioXplan);
                        oldServiciosAdicionalesOfServicioXplanCollectionNewServicioXplan = em.merge(oldServiciosAdicionalesOfServicioXplanCollectionNewServicioXplan);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = serviciosAdicionales.getCodigoServicio();
                if (findServiciosAdicionales(id) == null) {
                    throw new NonexistentEntityException("The serviciosAdicionales with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ServiciosAdicionales serviciosAdicionales;
            try {
                serviciosAdicionales = em.getReference(ServiciosAdicionales.class, id);
                serviciosAdicionales.getCodigoServicio();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The serviciosAdicionales with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<ServicioXplan> servicioXplanCollectionOrphanCheck = serviciosAdicionales.getServicioXplanCollection();
            for (ServicioXplan servicioXplanCollectionOrphanCheckServicioXplan : servicioXplanCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This ServiciosAdicionales (" + serviciosAdicionales + ") cannot be destroyed since the ServicioXplan " + servicioXplanCollectionOrphanCheckServicioXplan + " in its servicioXplanCollection field has a non-nullable serviciosAdicionales field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(serviciosAdicionales);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ServiciosAdicionales> findServiciosAdicionalesEntities() {
        return findServiciosAdicionalesEntities(true, -1, -1);
    }

    public List<ServiciosAdicionales> findServiciosAdicionalesEntities(int maxResults, int firstResult) {
        return findServiciosAdicionalesEntities(false, maxResults, firstResult);
    }

    private List<ServiciosAdicionales> findServiciosAdicionalesEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ServiciosAdicionales.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public ServiciosAdicionales findServiciosAdicionales(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ServiciosAdicionales.class, id);
        } finally {
            em.close();
        }
    }

    public int getServiciosAdicionalesCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ServiciosAdicionales> rt = cq.from(ServiciosAdicionales.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
