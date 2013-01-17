/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gov.itaipu.controlacceso.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
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
    
    public Visita() {
    }

    public Visita(Persona persona, Persona personaVisitada, Motivo motivo, Date fechaIngreso) {
        this.persona = persona;
        this.personaVisitada = personaVisitada;
        this.motivo = motivo;
        this.fechaIngreso = fechaIngreso;
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

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
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
        this.observacion = observacion;
    }

    public String getRecibido() {
        return recibido;
    }

    public void setRecibido(String recibido) {
        this.recibido = recibido;
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
        return "Visita{" + "id=" + id + ", persona=" + persona + ", personaVisitada=" + personaVisitada + ", motivo=" + motivo + ", fechaIngreso=" + fechaIngreso + ", fechaSalida=" + fechaSalida + ", observacion=" + observacion + ", recibido=" + recibido + '}';
    }

    
}
