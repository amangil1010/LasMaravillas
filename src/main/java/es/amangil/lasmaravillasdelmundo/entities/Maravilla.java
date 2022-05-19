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
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Usuario
 */
@Entity
@Table(name = "MARAVILLA")
@NamedQueries({
    @NamedQuery(name = "Maravilla.findAll", query = "SELECT m FROM Maravilla m"),
    @NamedQuery(name = "Maravilla.findById", query = "SELECT m FROM Maravilla m WHERE m.id = :id"),
    @NamedQuery(name = "Maravilla.findByNombre", query = "SELECT m FROM Maravilla m WHERE m.nombre = :nombre"),
    @NamedQuery(name = "Maravilla.findByNombreCreador", query = "SELECT m FROM Maravilla m WHERE m.nombreCreador = :nombreCreador"),
    @NamedQuery(name = "Maravilla.findByFechaDeConstruccion", query = "SELECT m FROM Maravilla m WHERE m.fechaDeConstruccion = :fechaDeConstruccion"),
    @NamedQuery(name = "Maravilla.findByEstadoDeLaMaravilla", query = "SELECT m FROM Maravilla m WHERE m.estadoDeLaMaravilla = :estadoDeLaMaravilla"),
    @NamedQuery(name = "Maravilla.findByPrecioDeEntrada", query = "SELECT m FROM Maravilla m WHERE m.precioDeEntrada = :precioDeEntrada"),
    @NamedQuery(name = "Maravilla.findBySigueExistiendo", query = "SELECT m FROM Maravilla m WHERE m.sigueExistiendo = :sigueExistiendo"),
    @NamedQuery(name = "Maravilla.findByFoto", query = "SELECT m FROM Maravilla m WHERE m.foto = :foto")})
public class Maravilla implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "NOMBRE_CREADOR")
    private String nombreCreador;
    @Column(name = "FECHA_DE_CONSTRUCCION")
    @Temporal(TemporalType.DATE)
    private Date fechaDeConstruccion;
    @Column(name = "ESTADO_DE_LA_MARAVILLA")
    private Character estadoDeLaMaravilla;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "PRECIO_DE_ENTRADA")
    private BigDecimal precioDeEntrada;
    @Column(name = "SIGUE_EXISTIENDO")
    private Boolean sigueExistiendo;
    @Column(name = "FOTO")
    private String foto;
    @JoinColumn(name = "LOCALIZACION", referencedColumnName = "ID")
    @ManyToOne
    private Localizacion localizacion;

    public Maravilla() {
    }

    public Maravilla(Integer id) {
        this.id = id;
    }

    public Maravilla(Integer id, String nombre, String nombreCreador) {
        this.id = id;
        this.nombre = nombre;
        this.nombreCreador = nombreCreador;
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

    public String getNombreCreador() {
        return nombreCreador;
    }

    public void setNombreCreador(String nombreCreador) {
        this.nombreCreador = nombreCreador;
    }

    public Date getFechaDeConstruccion() {
        return fechaDeConstruccion;
    }

    public void setFechaDeConstruccion(Date fechaDeConstruccion) {
        this.fechaDeConstruccion = fechaDeConstruccion;
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
