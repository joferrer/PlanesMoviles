/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import DAO.exceptions.NonexistentEntityException;
import DAO.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import DTO.ServiciosAdicionales;
import DTO.PlanMovil;
import DTO.ServicioXplan;
import DTO.ServicioXplanPK;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author jeiso
 */
public class ServicioXplanJpaController implements Serializable {

    public ServicioXplanJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ServicioXplan servicioXplan) throws PreexistingEntityException, Exception {
        if (servicioXplan.getServicioXplanPK() == null) {
            servicioXplan.setServicioXplanPK(new ServicioXplanPK());
        }
        servicioXplan.getServicioXplanPK().setCodigoServicio(servicioXplan.getServiciosAdicionales().getCodigoServicio());
        servicioXplan.getServicioXplanPK().setCodigoPlan(servicioXplan.getPlanMovil().getCodigoPlan());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ServiciosAdicionales serviciosAdicionales = servicioXplan.getServiciosAdicionales();
            if (serviciosAdicionales != null) {
                serviciosAdicionales = em.getReference(serviciosAdicionales.getClass(), serviciosAdicionales.getCodigoServicio());
                servicioXplan.setServiciosAdicionales(serviciosAdicionales);
            }
            PlanMovil planMovil = servicioXplan.getPlanMovil();
            if (planMovil != null) {
                planMovil = em.getReference(planMovil.getClass(), planMovil.getCodigoPlan());
                servicioXplan.setPlanMovil(planMovil);
            }
            em.persist(servicioXplan);
            if (serviciosAdicionales != null) {
                serviciosAdicionales.getServicioXplanCollection().add(servicioXplan);
                serviciosAdicionales = em.merge(serviciosAdicionales);
            }
            if (planMovil != null) {
                planMovil.getServicioXplanCollection().add(servicioXplan);
                planMovil = em.merge(planMovil);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findServicioXplan(servicioXplan.getServicioXplanPK()) != null) {
                throw new PreexistingEntityException("ServicioXplan " + servicioXplan + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(ServicioXplan servicioXplan) throws NonexistentEntityException, Exception {
        servicioXplan.getServicioXplanPK().setCodigoServicio(servicioXplan.getServiciosAdicionales().getCodigoServicio());
        servicioXplan.getServicioXplanPK().setCodigoPlan(servicioXplan.getPlanMovil().getCodigoPlan());
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ServicioXplan persistentServicioXplan = em.find(ServicioXplan.class, servicioXplan.getServicioXplanPK());
            ServiciosAdicionales serviciosAdicionalesOld = persistentServicioXplan.getServiciosAdicionales();
            ServiciosAdicionales serviciosAdicionalesNew = servicioXplan.getServiciosAdicionales();
            PlanMovil planMovilOld = persistentServicioXplan.getPlanMovil();
            PlanMovil planMovilNew = servicioXplan.getPlanMovil();
            if (serviciosAdicionalesNew != null) {
                serviciosAdicionalesNew = em.getReference(serviciosAdicionalesNew.getClass(), serviciosAdicionalesNew.getCodigoServicio());
                servicioXplan.setServiciosAdicionales(serviciosAdicionalesNew);
            }
            if (planMovilNew != null) {
                planMovilNew = em.getReference(planMovilNew.getClass(), planMovilNew.getCodigoPlan());
                servicioXplan.setPlanMovil(planMovilNew);
            }
            servicioXplan = em.merge(servicioXplan);
            if (serviciosAdicionalesOld != null && !serviciosAdicionalesOld.equals(serviciosAdicionalesNew)) {
                serviciosAdicionalesOld.getServicioXplanCollection().remove(servicioXplan);
                serviciosAdicionalesOld = em.merge(serviciosAdicionalesOld);
            }
            if (serviciosAdicionalesNew != null && !serviciosAdicionalesNew.equals(serviciosAdicionalesOld)) {
                serviciosAdicionalesNew.getServicioXplanCollection().add(servicioXplan);
                serviciosAdicionalesNew = em.merge(serviciosAdicionalesNew);
            }
            if (planMovilOld != null && !planMovilOld.equals(planMovilNew)) {
                planMovilOld.getServicioXplanCollection().remove(servicioXplan);
                planMovilOld = em.merge(planMovilOld);
            }
            if (planMovilNew != null && !planMovilNew.equals(planMovilOld)) {
                planMovilNew.getServicioXplanCollection().add(servicioXplan);
                planMovilNew = em.merge(planMovilNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                ServicioXplanPK id = servicioXplan.getServicioXplanPK();
                if (findServicioXplan(id) == null) {
                    throw new NonexistentEntityException("The servicioXplan with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(ServicioXplanPK id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            ServicioXplan servicioXplan;
            try {
                servicioXplan = em.getReference(ServicioXplan.class, id);
                servicioXplan.getServicioXplanPK();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The servicioXplan with id " + id + " no longer exists.", enfe);
            }
            ServiciosAdicionales serviciosAdicionales = servicioXplan.getServiciosAdicionales();
            if (serviciosAdicionales != null) {
                serviciosAdicionales.getServicioXplanCollection().remove(servicioXplan);
                serviciosAdicionales = em.merge(serviciosAdicionales);
            }
            PlanMovil planMovil = servicioXplan.getPlanMovil();
            if (planMovil != null) {
                planMovil.getServicioXplanCollection().remove(servicioXplan);
                planMovil = em.merge(planMovil);
            }
            em.remove(servicioXplan);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<ServicioXplan> findServicioXplanEntities() {
        return findServicioXplanEntities(true, -1, -1);
    }

    public List<ServicioXplan> findServicioXplanEntities(int maxResults, int firstResult) {
        return findServicioXplanEntities(false, maxResults, firstResult);
    }

    private List<ServicioXplan> findServicioXplanEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(ServicioXplan.class));
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

    public ServicioXplan findServicioXplan(ServicioXplanPK id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(ServicioXplan.class, id);
        } finally {
            em.close();
        }
    }

    public int getServicioXplanCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<ServicioXplan> rt = cq.from(ServicioXplan.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
