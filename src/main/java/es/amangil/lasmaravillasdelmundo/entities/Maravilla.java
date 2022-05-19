/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.amangil.lasmaravillasdelmundo.entities;

import es.amangil.lasmaravillasdelmundo.entities.Localizacion;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author Usuario
 */
@javax.persistence.Entity
@javax.persistence.Table(name = "MARAVILLA")
@javax.persistence.NamedQueries({
    @javax.persistence.NamedQuery(name = "Maravilla.findAll", query = "SELECT m FROM Maravilla m"),
    @javax.persistence.NamedQuery(name = "Maravilla.findById", query = "SELECT m FROM Maravilla m WHERE m.id = :id"),
    @javax.persistence.NamedQuery(name = "Maravilla.findByNombre", query = "SELECT m FROM Maravilla m WHERE m.nombre = :nombre"),
    @javax.persistence.NamedQuery(name = "Maravilla.findByApellidos", query = "SELECT m FROM Maravilla m WHERE m.apellidos = :apellidos"),
    @javax.persistence.NamedQuery(name = "Maravilla.findByFechaDeConstruccion", query = "SELECT m FROM Maravilla m WHERE m.fechaDeConstruccion = :fechaDeConstruccion"),
    @javax.persistence.NamedQuery(name = "Maravilla.findByNumHijos", query = "SELECT m FROM Maravilla m WHERE m.numHijos = :numHijos"),
    @javax.persistence.NamedQuery(name = "Maravilla.findByEstadoDeLaMaravilla", query = "SELECT m FROM Maravilla m WHERE m.estadoDeLaMaravilla = :estadoDeLaMaravilla"),
    @javax.persistence.NamedQuery(name = "Maravilla.findByPrecioDeEntrada", query = "SELECT m FROM Maravilla m WHERE m.precioDeEntrada = :precioDeEntrada"),
    @javax.persistence.NamedQuery(name = "Maravilla.findBySigueExistiendo", query = "SELECT m FROM Maravilla m WHERE m.sigueExistiendo = :sigueExistiendo"),
    @javax.persistence.NamedQuery(name = "Maravilla.findByFoto", query = "SELECT m FROM Maravilla m WHERE m.foto = :foto")})
public class Maravilla implements Serializable {

    private static final long serialVersionUID = 1L;
    @javax.persistence.Id
    @javax.persistence.GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    @javax.persistence.Basic(optional = false)
    @javax.persistence.Column(name = "ID")
    private Integer id;
    @javax.persistence.Basic(optional = false)
    @javax.persistence.Column(name = "NOMBRE")
    private String nombre;
    @javax.persistence.Basic(optional = false)
    @javax.persistence.Column(name = "APELLIDOS")
    private String apellidos;
    @javax.persistence.Column(name = "FECHA_DE_CONSTRUCCION")
    @javax.persistence.Temporal(javax.persistence.TemporalType.DATE)
    private Date fechaDeConstruccion;
    @javax.persistence.Column(name = "NUM_HIJOS")
    private Short numHijos;
    @javax.persistence.Column(name = "ESTADO_DE_LA_MARAVILLA")
    private Character estadoDeLaMaravilla;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @javax.persistence.Column(name = "PRECIO_DE_ENTRADA")
    private BigDecimal precioDeEntrada;
    @javax.persistence.Column(name = "SIGUE_EXISTIENDO")
    private Boolean sigueExistiendo;
    @javax.persistence.Column(name = "FOTO")
    private String foto;
    @javax.persistence.JoinColumn(name = "LOCALIZACION", referencedColumnName = "ID")
    @javax.persistence.ManyToOne
    private Localizacion localizacion;

    public Maravilla() {
    }

    public Maravilla(Integer id) {
        this.id = id;
    }

    public Maravilla(Integer id, String nombre, String apellidos) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
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

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public Date getFechaDeConstruccion() {
        return fechaDeConstruccion;
    }

    public void setFechaDeConstruccion(Date fechaDeConstruccion) {
        this.fechaDeConstruccion = fechaDeConstruccion;
    }

    public Short getNumHijos() {
        return numHijos;
    }

    public void setNumHijos(Short numHijos) {
        this.numHijos = numHijos;
    }

    public Character getEstadoDeLaMaravilla() {
        return estadoDeLaMaravilla;
    }

    public void setEstadoDeLaMaravilla(Character estadoDeLaMaravilla) {
        this.estadoDeLaMaravilla = estadoDeLaMaravilla;
    }

    public BigDecimal getPrecioDeEntrada() {
        return precioDeEntrada;
    }

    public void setPrecioDeEntrada(BigDecimal precioDeEntrada) {
        this.precioDeEntrada = precioDeEntrada;
    }

    public Boolean getSigueExistiendo() {
        return sigueExistiendo;
    }

    public void setSigueExistiendo(Boolean sigueExistiendo) {
        this.sigueExistiendo = sigueExistiendo;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Localizacion getLocalizacion() {
        return localizacion;
    }

    public void setLocalizacion(Localizacion localizacion) {
        this.localizacion = localizacion;
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
        if (!(object instanceof Maravilla)) {
            return false;
        }
        Maravilla other = (Maravilla) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "es.amangil.lasmaravillasdelmundo.entities.Maravilla[ id=" + id + " ]";
    }
    
}
