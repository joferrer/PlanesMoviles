/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jeiso
 */
@Entity
@Table(name = "ServicioXplan")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ServicioXplan.findAll", query = "SELECT s FROM ServicioXplan s")
    , @NamedQuery(name = "ServicioXplan.findByCodigoPlan", query = "SELECT s FROM ServicioXplan s WHERE s.servicioXplanPK.codigoPlan = :codigoPlan")
    , @NamedQuery(name = "ServicioXplan.findByCodigoServicio", query = "SELECT s FROM ServicioXplan s WHERE s.servicioXplanPK.codigoServicio = :codigoServicio")
    , @NamedQuery(name = "ServicioXplan.findByPrecio", query = "SELECT s FROM ServicioXplan s WHERE s.precio = :precio")})
public class ServicioXplan implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected ServicioXplanPK servicioXplanPK;
    @Basic(optional = false)
    @Column(name = "precio")
    private int precio;
    @JoinColumn(name = "codigoServicio", referencedColumnName = "codigoServicio", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private ServiciosAdicionales serviciosAdicionales;
    @JoinColumn(name = "codigoPlan", referencedColumnName = "codigoPlan", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private PlanMovil planMovil;

    public ServicioXplan() {
    }

    public ServicioXplan(ServicioXplanPK servicioXplanPK) {
        this.servicioXplanPK = servicioXplanPK;
    }

    public ServicioXplan(ServicioXplanPK servicioXplanPK, int precio) {
        this.servicioXplanPK = servicioXplanPK;
        this.precio = precio;
    }

    public ServicioXplan(int codigoPlan, int codigoServicio) {
        this.servicioXplanPK = new ServicioXplanPK(codigoPlan, codigoServicio);
    }

    public ServicioXplanPK getServicioXplanPK() {
        return servicioXplanPK;
    }

    public void setServicioXplanPK(ServicioXplanPK servicioXplanPK) {
        this.servicioXplanPK = servicioXplanPK;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public ServiciosAdicionales getServiciosAdicionales() {
        return serviciosAdicionales;
    }

    public void setServiciosAdicionales(ServiciosAdicionales serviciosAdicionales) {
        this.serviciosAdicionales = serviciosAdicionales;
    }

    public PlanMovil getPlanMovil() {
        return planMovil;
    }

    public void setPlanMovil(PlanMovil planMovil) {
        this.planMovil = planMovil;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (servicioXplanPK != null ? servicioXplanPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ServicioXplan)) {
            return false;
        }
        ServicioXplan other = (ServicioXplan) object;
        if ((this.servicioXplanPK == null && other.servicioXplanPK != null) || (this.servicioXplanPK != null && !this.servicioXplanPK.equals(other.servicioXplanPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DTO.ServicioXplan[ servicioXplanPK=" + servicioXplanPK + " ]";
    }
    
}
