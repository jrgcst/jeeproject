package es.microforum.model;

// Generated 13-ene-2014 19:51:49 by Hibernate Tools 4.0.0

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Empresa generated by hbm2java
 */

@Entity
@Table(name = "empresa")
public class Empresa implements java.io.Serializable {

	private String nif;
	private Integer version;
	private String nombre;
	private String direccionFiscal;
	private Date fechaInicioActividades;
	private Set empleados = new HashSet(0);

	public Empresa() {
	}

	public Empresa(String nif) {
		this.nif = nif;
	}

	public Empresa(String nif, String nombre, String direccionFiscal,
			Date fechaInicioActividades, Set empleados) {
		this.nif = nif;
		this.nombre = nombre;
		this.direccionFiscal = direccionFiscal;
		this.fechaInicioActividades = fechaInicioActividades;
		this.empleados = empleados;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "nif")
	public String getNif() {
		return this.nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	@Version
	@Column(name = "version")
	public Integer getVersion() {
		return this.version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	@Column(name = "nombre")
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "direccionFiscal")
	public String getDireccionFiscal() {
		return this.direccionFiscal;
	}

	public void setDireccionFiscal(String direccionFiscal) {
		this.direccionFiscal = direccionFiscal;
	}

	@Column(name = "fechaInicioActividades")
	public Date getFechaInicioActividades() {
		return this.fechaInicioActividades;
	}

	public void setFechaInicioActividades(Date fechaInicioActividades) {
		this.fechaInicioActividades = fechaInicioActividades;
	}

	@OneToMany(mappedBy = "empresa", cascade=CascadeType.ALL, orphanRemoval=true)
	public Set getEmpleados() {
		return this.empleados;
	}

	public void setEmpleados(Set empleados) {
		this.empleados = empleados;
	}

}
