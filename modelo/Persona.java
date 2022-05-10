package tsi.rp.s1.modelo;

public class Persona {
	private Integer idP, nroCi;
	private String nombre, apellido, email, fechaNac;

	public Persona(Integer idP, Integer nroCi, String nombre, String apellido, String email, String fechaNac) {
		this.idP = idP;
		this.nroCi = nroCi;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.fechaNac = fechaNac;
	}

	public Persona(Integer nroCi, String nombre, String apellido, String email, String fechaNac) {
		this.nroCi = nroCi;
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.fechaNac = fechaNac;
	}

	public Persona() {
	}

	public Integer getIdP() {
		return idP;
	}

	public void setIdP(Integer idP) {
		this.idP = idP;
	}

	public Integer getNroCi() {
		return nroCi;
	}

	public void setNroCi(Integer nroCi) {
		this.nroCi = nroCi;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFechaNac() {
		return fechaNac;
	}

	public void setFechaNac(String fechaNac) {
		this.fechaNac = fechaNac;
	}
}
