/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jeiso
 */
@Entity
@Table(name = "Smartphone")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Smartphone.findAll", query = "SELECT s FROM Smartphone s")
    , @NamedQuery(name = "Smartphone.findById", query = "SELECT s FROM Smartphone s WHERE s.id = :id")
    , @NamedQuery(name = "Smartphone.findByNombre", query = "SELECT s FROM Smartphone s WHERE s.nombre = :nombre")
    , @NamedQuery(name = "Smartphone.findByMarca", query = "SELECT s FROM Smartphone s WHERE s.marca = :marca")
    , @NamedQuery(name = "Smartphone.findByPrecio", query = "SELECT s FROM Smartphone s WHERE s.precio = :precio")
    , @NamedQuery(name = "Smartphone.findByUrlImagen", query = "SELECT s FROM Smartphone s WHERE s.urlImagen = :urlImagen")})
public class Smartphone implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "marca")
    private String marca;
    @Basic(optional = false)
    @Column(name = "precio")
    private int precio;
    @Basic(optional = false)
    @Column(name = "url_imagen")
    private String urlImagen;

    public Smartphone() {
    }

    public Smartphone(Integer id) {
        this.id = id;
    }

    public Smartphone(Integer id, String nombre, String marca, int precio, String urlImagen) {
        this.id = id;
        this.nombre = nombre;
        this.marca = marca;
        this.precio = precio;
        this.urlImagen = urlImagen;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Smartphone)) {
            return false;
        }
        Smartphone other = (Smartphone) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DTO.Smartphone[ id=" + id + " ]";
    }
    
}
