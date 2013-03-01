/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gov.itaipu.controlacceso.model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.validator.constraints.Length;

/**
 *
 * @author vimartih
 */
@Entity
public class Visita implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Long id;
    private Persona persona;
    private Persona personaVisitada;
    private Organizacion organizacionInterna;
    private Motivo motivo;
    private Date fechaIngreso;
    private Date fechaSalida;
    private String observacion;
    private String recibido;
    private String anulado;
    private Usuario usuarioCreacion;
    private Usuario usuarioModificacion;
    private Date fechaCreacion;
    private Date fechaModificacion;
    
    
    public Visita() {
    }

    public Visita(Persona persona, Persona personaVisitada, Motivo motivo, Date fechaIngreso) {
        this.persona = persona;
        this.personaVisitada = personaVisitada;
        this.motivo = motivo;
        this.fechaIngreso = fechaIngreso;
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

    @ManyToOne
    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    @ManyToOne
    public Persona getPersonaVisitada() {
        return personaVisitada;
    }

    public void setPersonaVisitada(Persona personaVisitada) {
        this.personaVisitada = personaVisitada;
    }

    @ManyToOne
    public Organizacion getOrganizacionInterna() {
        return organizacionInterna;
    }

    public void setOrganizacionInterna(Organizacion organizacionInterna) {
        this.organizacionInterna = organizacionInterna;
    }

    @ManyToOne
    public Motivo getMotivo() {
        return motivo;
    }

    public void setMotivo(Motivo motivo) {
        this.motivo = motivo;
    }

    //@Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

//    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(Date fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    @Length(max=1500)
    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion.toUpperCase();
    }

    public String getRecibido() {
        return recibido;
    }

    public void setRecibido(String recibido) {
        this.recibido = recibido;
    }
    @Length(max=1)
    public String getAnulado() {
        return anulado;
    }

    public void setAnulado(String anulado) {
        this.anulado = anulado.toUpperCase();
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
        if (!(object instanceof Visita)) {
            return false;
        }
        Visita other = (Visita) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        
        if (fechaSalida==null) {
            return persona +" "+ fechaIngreso.getHours()+":"+fechaIngreso.getMinutes();
        }else{
            return persona +" "+ fechaIngreso.getHours()+":"+fechaIngreso.getMinutes()+" - "+fechaSalida.getHours()+":"+fechaSalida.getMinutes();
        }
        
    }

    
}
