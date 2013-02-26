/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gov.itaipu.controlacceso.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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
    @NamedQuery(name = "TipoDocumento.findAll", query = "SELECT o FROM TipoDocumento o"),
    @NamedQuery(name = "TipoDocumento.findCI", query = "SELECT o FROM TipoDocumento o where o.nombre = 'CI' ")
})
@Table(name="tipodocumento", uniqueConstraints=@UniqueConstraint(columnNames={"nombre"}))
public class TipoDocumento implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    private String nombre;
    private String descripcion;
 private Usuario usuarioCreacion;
    private Usuario usuarioModificacion;
    private Date fechaCreacion;
    private Date fechaModificacion;
    
    public TipoDocumento() {
    }

    public TipoDocumento(Long id) {
        this.id = id;
    }

    public TipoDocumento(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
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
    @Length(max = 200)
    @Column(nullable=false)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre.toUpperCase();
    }

    @Length(max = 500)
    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
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
        if (!(object instanceof TipoDocumento)) {
            return false;
        }
        TipoDocumento other = (TipoDocumento) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nombre;
    }

}
