/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author jeiso
 */
@Embeddable
public class ServicioXplanPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "codigoPlan")
    private int codigoPlan;
    @Basic(optional = false)
    @Column(name = "codigoServicio")
    private int codigoServicio;

    public ServicioXplanPK() {
    }

    public ServicioXplanPK(int codigoPlan, int codigoServicio) {
        this.codigoPlan = codigoPlan;
        this.codigoServicio = codigoServicio;
    }

    public int getCodigoPlan() {
        return codigoPlan;
    }

    public void setCodigoPlan(int codigoPlan) {
        this.codigoPlan = codigoPlan;
    }

    public int getCodigoServicio() {
        return codigoServicio;
    }

    public void setCodigoServicio(int codigoServicio) {
        this.codigoServicio = codigoServicio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) codigoPlan;
        hash += (int) codigoServicio;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ServicioXplanPK)) {
            return false;
        }
        ServicioXplanPK other = (ServicioXplanPK) object;
        if (this.codigoPlan != other.codigoPlan) {
            return false;
        }
        if (this.codigoServicio != other.codigoServicio) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DTO.ServicioXplanPK[ codigoPlan=" + codigoPlan + ", codigoServicio=" + codigoServicio + " ]";
    }
    
}
