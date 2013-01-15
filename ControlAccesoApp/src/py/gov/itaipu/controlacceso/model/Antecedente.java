/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gov.itaipu.controlacceso.model;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import org.hibernate.validator.constraints.Length;

/**
 *
 * @author vimartih
 */
@Entity
public class Antecedente implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Long id;
    private Persona persona;
    private TipoAntecedente tipoAntecedente;
    private String observacion;

    public Antecedente() {
    }

    public Antecedente(Persona persona, TipoAntecedente tipoAntecedente, String observacion) {
        this.persona = persona;
        this.tipoAntecedente = tipoAntecedente;
        this.observacion = observacion;
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
    public TipoAntecedente getTipoAntecedente() {
        return tipoAntecedente;
    }

    public void setTipoAntecedente(TipoAntecedente tipoAntecedente) {
        this.tipoAntecedente = tipoAntecedente;
    }

    @Length(max=1500)
    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
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
        if (!(object instanceof Antecedente)) {
            return false;
        }
        Antecedente other = (Antecedente) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Antecedente{" + "id=" + id + ", persona=" + persona + ", tipoAntecedente=" + tipoAntecedente + ", observacion=" + observacion + '}';
    }

       
}
