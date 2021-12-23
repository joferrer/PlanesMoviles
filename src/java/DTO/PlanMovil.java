/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jeiso
 */
@Entity
@Table(name = "PlanMovil")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PlanMovil.findAll", query = "SELECT p FROM PlanMovil p")
    , @NamedQuery(name = "PlanMovil.findByCodigoPlan", query = "SELECT p FROM PlanMovil p WHERE p.codigoPlan = :codigoPlan")
    , @NamedQuery(name = "PlanMovil.findByPrecio", query = "SELECT p FROM PlanMovil p WHERE p.precio = :precio")})
public class PlanMovil implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "codigoPlan")
    private Integer codigoPlan;
    @Basic(optional = false)
    @Lob
    @Column(name = "nombrePlan")
    private String nombrePlan;
    @Basic(optional = false)
    @Column(name = "precio")
    private int precio;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "planMovil")
    private Collection<ServicioXplan> servicioXplanCollection;

    public PlanMovil() {
    }

    public PlanMovil(Integer codigoPlan) {
        this.codigoPlan = codigoPlan;
    }

    public PlanMovil(Integer codigoPlan, String nombrePlan, int precio) {
        this.codigoPlan = codigoPlan;
        this.nombrePlan = nombrePlan;
        this.precio = precio;
    }

    public Integer getCodigoPlan() {
        return codigoPlan;
    }

    public void setCodigoPlan(Integer codigoPlan) {
        this.codigoPlan = codigoPlan;
    }

    public String getNombrePlan() {
        return nombrePlan;
    }

    public void setNombrePlan(String nombrePlan) {
        this.nombrePlan = nombrePlan;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    @XmlTransient
    public Collection<ServicioXplan> getServicioXplanCollection() {
        return servicioXplanCollection;
    }

    public void setServicioXplanCollection(Collection<ServicioXplan> servicioXplanCollection) {
        this.servicioXplanCollection = servicioXplanCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoPlan != null ? codigoPlan.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PlanMovil)) {
            return false;
        }
        PlanMovil other = (PlanMovil) object;
        if ((this.codigoPlan == null && other.codigoPlan != null) || (this.codigoPlan != null && !this.codigoPlan.equals(other.codigoPlan))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DTO.PlanMovil[ codigoPlan=" + codigoPlan + " ]";
    }
    
}
