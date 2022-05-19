/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.amangil.lasmaravillasdelmundo.entities;

import es.amangil.lasmaravillasdelmundo.entities.Maravilla;
import java.io.Serializable;
import java.util.Collection;

/**
 *
 * @author Usuario
 */
@javax.persistence.Entity
@javax.persistence.Table(name = "LOCALIZACION")
@javax.persistence.NamedQueries({
    @javax.persistence.NamedQuery(name = "Localizacion.findAll", query = "SELECT l FROM Localizacion l"),
    @javax.persistence.NamedQuery(name = "Localizacion.findById", query = "SELECT l FROM Localizacion l WHERE l.id = :id"),
    @javax.persistence.NamedQuery(name = "Localizacion.findByCodigo", query = "SELECT l FROM Localizacion l WHERE l.codigo = :codigo"),
    @javax.persistence.NamedQuery(name = "Localizacion.findByNombre", query = "SELECT l FROM Localizacion l WHERE l.nombre = :nombre")})
public class Localizacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @javax.persistence.Id
    @javax.persistence.GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    @javax.persistence.Basic(optional = false)
    @javax.persistence.Column(name = "ID")
    private Integer id;
    @javax.persistence.Column(name = "CODIGO")
    private String codigo;
    @javax.persistence.Basic(optional = false)
    @javax.persistence.Column(name = "NOMBRE")
    private String nombre;
    @javax.persistence.OneToMany(mappedBy = "localizacion")
    private Collection<Maravilla> maravillaCollection;

    public Localizacion() {
    }

    public Localizacion(Integer id) {
        this.id = id;
    }

    public Localizacion(Integer id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Collection<Maravilla> getMaravillaCollection() {
        return maravillaCollection;
    }

    public void setMaravillaCollection(Collection<Maravilla> maravillaCollection) {
        this.maravillaCollection = maravillaCollection;
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
        if (!(object instanceof Localizacion)) {
            return false;
        }
        Localizacion other = (Localizacion) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "es.amangil.lasmaravillasdelmundo.entities.Localizacion[ id=" + id + " ]";
    }
    
}
