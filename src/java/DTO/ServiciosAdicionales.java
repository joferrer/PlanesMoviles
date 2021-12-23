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
@Table(name = "ServiciosAdicionales")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ServiciosAdicionales.findAll", query = "SELECT s FROM ServiciosAdicionales s")
    , @NamedQuery(name = "ServiciosAdicionales.findByCodigoServicio", query = "SELECT s FROM ServiciosAdicionales s WHERE s.codigoServicio = :codigoServicio")})
public class ServiciosAdicionales implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "codigoServicio")
    private Integer codigoServicio;
    @Basic(optional = false)
    @Lob
    @Column(name = "nombreServicio")
    private String nombreServicio;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "serviciosAdicionales")
    private Collection<ServicioXplan> servicioXplanCollection;

    public ServiciosAdicionales() {
    }

    public ServiciosAdicionales(Integer codigoServicio) {
        this.codigoServicio = codigoServicio;
    }

    public ServiciosAdicionales(Integer codigoServicio, String nombreServicio) {
        this.codigoServicio = codigoServicio;
        this.nombreServicio = nombreServicio;
    }

    public Integer getCodigoServicio() {
        return codigoServicio;
    }

    public void setCodigoServicio(Integer codigoServicio) {
        this.codigoServicio = codigoServicio;
    }

    public String getNombreServicio() {
        return nombreServicio;
    }

    public void setNombreServicio(String nombreServicio) {
        this.nombreServicio = nombreServicio;
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
        hash += (codigoServicio != null ? codigoServicio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ServiciosAdicionales)) {
            return false;
        }
        ServiciosAdicionales other = (ServiciosAdicionales) object;
        if ((this.codigoServicio == null && other.codigoServicio != null) || (this.codigoServicio != null && !this.codigoServicio.equals(other.codigoServicio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DTO.ServiciosAdicionales[ codigoServicio=" + codigoServicio + " ]";
    }
    
}
