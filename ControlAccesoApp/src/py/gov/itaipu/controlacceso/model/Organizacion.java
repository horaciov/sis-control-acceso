/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gov.itaipu.controlacceso.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author vimartih
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Organizacion.findAll", query = "SELECT o FROM Organizacion o"),
    @NamedQuery(name = "Organizacion.findAllInterna", query = "SELECT o FROM Organizacion o where o.tipoOrganizacion='INTERNA' order by o.nombre"),
    @NamedQuery(name = "Organizacion.findAllExterna", query = "SELECT o FROM Organizacion o where o.tipoOrganizacion='EXTERNA' order by o.nombre"),
    @NamedQuery(name = "Organizacion.findOrganizacionPadre", query = "SELECT o FROM Organizacion o where o.nivelOrganigrama = 1 "),
    @NamedQuery(name = "Organizacion.findOrgMaxNivel", query = "SELECT o FROM Organizacion o where o.nivelOrganigrama = ( SELECT max(og.nivelOrganigrama) from Organizacion og )")
    })
@Table(name="organizacion", uniqueConstraints=@UniqueConstraint(columnNames={"nombre","tipoorganizacion"}))
public class Organizacion implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private Long id;
    private String nombre;
    private String descripcion;
    private String tipoOrganizacion;
    private Organizacion organizacionPadre;
    private Long nivelOrganigrama;
    private Estado estado;
    private List<Persona> personas;
    private List<Organizacion> organizacionesHijas;
    private Usuario usuarioCreacion;
    private Usuario usuarioModificacion;
    private Date fechaCreacion;
    private Date fechaModificacion;
    
    public Organizacion(){
        super();
    }

       @ManyToOne
    public Usuario getUsuarioCreacion() {
        return usuarioCreacion;
    }

    public void setUsuarioCreacion(Usuario usuarioCreacion) {
        this.usuarioCreacion = usuarioCreacion;
    }

    @ManyToOne
    public Usuario getUsuarioModificacion() {
        return usuarioModificacion;
    }

    public void setUsuarioModificacion(Usuario usuarioModificacion) {
        this.usuarioModificacion = usuarioModificacion;
    }

    @Temporal(TemporalType.TIMESTAMP)
    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

     @Temporal(TemporalType.TIMESTAMP)
    public Date getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }
    
    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    @NotEmpty
    @Length(max=200)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre.toUpperCase();
    }

    @Length(max=500)
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipoOrganizacion() {
        return tipoOrganizacion;
    }

    public void setTipoOrganizacion(String tipoOrganizacion) {
        this.tipoOrganizacion = tipoOrganizacion;
    }

    @ManyToOne
    public Organizacion getOrganizacionPadre() {
        return organizacionPadre;
    }

    public void setOrganizacionPadre(Organizacion organizacionPadre) {
        this.organizacionPadre = organizacionPadre;
    }

    public Long getNivelOrganigrama() {
        return nivelOrganigrama;
    }

    public void setNivelOrganigrama(Long nivelOrganigrama) {
        this.nivelOrganigrama = nivelOrganigrama;
    }
    
    @ManyToOne
    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }
    
    @OneToMany(mappedBy="organizacion")
    public List<Persona> getPersonas() {
        return personas;
    }

    public void setPersonas(List<Persona> personas) {
        this.personas = personas;
    }   

    @OneToMany(mappedBy="organizacionPadre")
    public List<Organizacion> getOrganizacionesHijas() {
        return organizacionesHijas;
    }

    public void setOrganizacionesHijas(List<Organizacion> organizacionesHijas) {
        this.organizacionesHijas = organizacionesHijas;
    }
    
    
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 11 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Organizacion other = (Organizacion) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return getNombre();
    }
    
}