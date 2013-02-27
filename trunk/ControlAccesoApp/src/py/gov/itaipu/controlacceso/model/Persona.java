/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package py.gov.itaipu.controlacceso.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author fboy
 */
@Entity
@Table(name="persona", uniqueConstraints=@UniqueConstraint(columnNames={"numerodocumento","tipodocumento_id"}))
public class Persona implements Serializable{
    
    private Long id;
    private String nombre;
    private String apellido;
    private Date fechaNacimiento;
    private Estado estado;
    private String numeroDocumento;
    private TipoDocumento tipoDocumento;
    private Nacionalidad nacionalidad;
    private String sexo;
    private String estadoCivil;
    private Organizacion organizacion;
    private String fotografiaPath;
    private byte[] fotografia;
    private List<Antecedente> antecedentes;
    private List<Visita> visitasRealizadas;
    private List<Visita> visitasRecibidas;
    private Usuario usuarioCreacion;
    private Usuario usuarioModificacion;
    private Date fechaCreacion;
    private Date fechaModificacion;

    public Persona() {
        
    }

    public Persona(String nombre, String apellido, Date fechaNacimiento) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.fechaNacimiento = fechaNacimiento;
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

    @Column(nullable=false)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre.toUpperCase();
    }

    @Column(nullable=false)
    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido.toUpperCase();
    }


    @Temporal(javax.persistence.TemporalType.DATE)
    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    @ManyToOne
    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    @Column(nullable=false)
    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    @ManyToOne
    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    @ManyToOne
    public Nacionalidad getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(Nacionalidad nacionalidad) {
        this.nacionalidad = nacionalidad;
    }
    
    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getFotografiaPath() {
        return fotografiaPath;
    }

    public void setFotografiaPath(String fotografiaPath) {
        this.fotografiaPath = fotografiaPath;
    }
    
    

    @OneToMany(cascade= CascadeType.ALL,mappedBy="persona")
    public List<Antecedente> getAntecedentes() {
        return antecedentes;
    }

    public void setAntecedentes(List<Antecedente> antecedentes) {
        this.antecedentes = antecedentes;
    }

    @OneToMany(cascade= CascadeType.ALL,mappedBy="persona")
    public List<Visita> getVisitasRealizadas() {
        return visitasRealizadas;
    }

    public void setVisitasRealizadas(List<Visita> visitasRealizadas) {
        this.visitasRealizadas = visitasRealizadas;
    }

    @OneToMany(cascade= CascadeType.ALL,mappedBy="personaVisitada")
    public List<Visita> getVisitasRecibidas() {
        return visitasRecibidas;
    }

    public void setVisitasRecibidas(List<Visita> visitasRecibidas) {
        this.visitasRecibidas = visitasRecibidas;
    }

    @ManyToOne(optional=true)
    public Organizacion getOrganizacion() {
        return organizacion;
    }

    public void setOrganizacion(Organizacion organizacion) {
        this.organizacion = organizacion;
    }

    public byte[] getFotografia() {
        return fotografia;
    }

    public void setFotografia(byte[] fotografia) {
        this.fotografia = fotografia;
    }
        
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + (this.id != null ? this.id.hashCode() : 0);
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
        final Persona other = (Persona) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return  apellido + ", "+nombre;
    }
    
}
