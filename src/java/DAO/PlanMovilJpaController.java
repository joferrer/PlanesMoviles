/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.exceptions.IllegalOrphanException;
import DAO.exceptions.NonexistentEntityException;
import DAO.exceptions.PreexistingEntityException;
import DTO.PlanMovil;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import DTO.ServicioXplan;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author jeiso
 */
public class PlanMovilJpaController implements Serializable {

    public PlanMovilJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PlanMovil planMovil) throws PreexistingEntityException, Exception {
        if (planMovil.getServicioXplanCollection() == null) {
            planMovil.setServicioXplanCollection(new ArrayList<ServicioXplan>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<ServicioXplan> attachedServicioXplanCollection = new ArrayList<ServicioXplan>();
            for (ServicioXplan servicioXplanCollectionServicioXplanToAttach : planMovil.getServicioXplanCollection()) {
                servicioXplanCollectionServicioXplanToAttach = em.getReference(servicioXplanCollectionServicioXplanToAttach.getClass(), servicioXplanCollectionServicioXplanToAttach.getServicioXplanPK());
                attachedServicioXplanCollection.add(servicioXplanCollectionServicioXplanToAttach);
            }
            planMovil.setServicioXplanCollection(attachedServicioXplanCollection);
            em.persist(planMovil);
            for (ServicioXplan servicioXplanCollectionServicioXplan : planMovil.getServicioXplanCollection()) {
                PlanMovil oldPlanMovilOfServicioXplanCollectionServicioXplan = servicioXplanCollectionServicioXplan.getPlanMovil();
                servicioXplanCollectionServicioXplan.setPlanMovil(planMovil);
                servicioXplanCollectionServicioXplan = em.merge(servicioXplanCollectionServicioXplan);
                if (oldPlanMovilOfServicioXplanCollectionServicioXplan != null) {
                    oldPlanMovilOfServicioXplanCollectionServicioXplan.getServicioXplanCollection().remove(servicioXplanCollectionServicioXplan);
                    oldPlanMovilOfServicioXplanCollectionServicioXplan = em.merge(oldPlanMovilOfServicioXplanCollectionServicioXplan);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPlanMovil(planMovil.getCodigoPlan()) != null) {
                throw new PreexistingEntityException("PlanMovil " + planMovil + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PlanMovil planMovil) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PlanMovil persistentPlanMovil = em.find(PlanMovil.class, planMovil.getCodigoPlan());
            Collection<ServicioXplan> servicioXplanCollectionOld = persistentPlanMovil.getServicioXplanCollection();
            Collection<ServicioXplan> servicioXplanCollectionNew = planMovil.getServicioXplanCollection();
            List<String> illegalOrphanMessages = null;
            for (ServicioXplan servicioXplanCollectionOldServicioXplan : servicioXplanCollectionOld) {
                if (!servicioXplanCollectionNew.contains(servicioXplanCollectionOldServicioXplan)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ServicioXplan " + servicioXplanCollectionOldServicioXplan + " since its planMovil field is not nullable.");
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
            planMovil.setServicioXplanCollection(servicioXplanCollectionNew);
            planMovil = em.merge(planMovil);
            for (ServicioXplan servicioXplanCollectionNewServicioXplan : servicioXplanCollectionNew) {
                if (!servicioXplanCollectionOld.contains(servicioXplanCollectionNewServicioXplan)) {
                    PlanMovil oldPlanMovilOfServicioXplanCollectionNewServicioXplan = servicioXplanCollectionNewServicioXplan.getPlanMovil();
                    servicioXplanCollectionNewServicioXplan.setPlanMovil(planMovil);
                    servicioXplanCollectionNewServicioXplan = em.merge(servicioXplanCollectionNewServicioXplan);
                    if (oldPlanMovilOfServicioXplanCollectionNewServicioXplan != null && !oldPlanMovilOfServicioXplanCollectionNewServicioXplan.equals(planMovil)) {
                        oldPlanMovilOfServicioXplanCollectionNewServicioXplan.getServicioXplanCollection().remove(servicioXplanCollectionNewServicioXplan);
                        oldPlanMovilOfServicioXplanCollectionNewServicioXplan = em.merge(oldPlanMovilOfServicioXplanCollectionNewServicioXplan);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = planMovil.getCodigoPlan();
                if (findPlanMovil(id) == null) {
                    throw new NonexistentEntityException("The planMovil with id " + id + " no longer exists.");
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
            PlanMovil planMovil;
            try {
                planMovil = em.getReference(PlanMovil.class, id);
                planMovil.getCodigoPlan();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The planMovil with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<ServicioXplan> servicioXplanCollectionOrphanCheck = planMovil.getServicioXplanCollection();
            for (ServicioXplan servicioXplanCollectionOrphanCheckServicioXplan : servicioXplanCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This PlanMovil (" + planMovil + ") cannot be destroyed since the ServicioXplan " + servicioXplanCollectionOrphanCheckServicioXplan + " in its servicioXplanCollection field has a non-nullable planMovil field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(planMovil);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PlanMovil> findPlanMovilEntities() {
        return findPlanMovilEntities(true, -1, -1);
    }

    public List<PlanMovil> findPlanMovilEntities(int maxResults, int firstResult) {
        return findPlanMovilEntities(false, maxResults, firstResult);
    }

    private List<PlanMovil> findPlanMovilEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PlanMovil.class));
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

    public PlanMovil findPlanMovil(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PlanMovil.class, id);
        } finally {
            em.close();
        }
    }

    public int getPlanMovilCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PlanMovil> rt = cq.from(PlanMovil.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
