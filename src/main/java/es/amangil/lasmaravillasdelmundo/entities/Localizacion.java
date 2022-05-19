/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.amangil.lasmaravillasdelmundo.entities;

import es.amangil.lasmaravillasdelmundo.entities.Maravilla;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "LOCALIZACION")
@NamedQueries({
    @NamedQuery(name = "Localizacion.findAll", query = "SELECT l FROM Localizacion l"),
    @NamedQuery(name = "Localizacion.findById", query = "SELECT l FROM Localizacion l WHERE l.id = :id"),
    @NamedQuery(name = "Localizacion.findByCodigo", query = "SELECT l FROM Localizacion l WHERE l.codigo = :codigo"),
    @NamedQuery(name = "Localizacion.findByNombre", query = "SELECT l FROM Localizacion l WHERE l.nombre = :nombre")})
public class Localizacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Column(name = "CODIGO")
    private String codigo;
    @Basic(optional = false)
    @Column(name = "NOMBRE")
    private String nombre;
    @OneToMany(mappedBy = "localizacion")
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
