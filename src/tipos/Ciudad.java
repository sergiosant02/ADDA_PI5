package tipos;

import java.util.Objects;

public class Ciudad {
	private String nombre;
	private Double poblacion;
	
	public Ciudad(String nombre, Double poblacion) {
		super();
		this.nombre = nombre;
		this.poblacion = poblacion;
	}
	
	public static Ciudad of(String[] partes) {
		String nombreP = partes[0];
		Double poblacionP = Double.valueOf(partes[1]);
		return new Ciudad(nombreP, poblacionP);
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Double getPoblacion() {
		return poblacion;
	}

	public void setPoblacion(Double poblacion) {
		this.poblacion = poblacion;
	}

	@Override
	public int hashCode() {
		return Objects.hash(nombre, poblacion);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ciudad other = (Ciudad) obj;
		return Objects.equals(nombre, other.nombre) && Objects.equals(poblacion, other.poblacion);
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return String.format("(nombre: %s, poblacion: %f)", this.nombre, this.poblacion);
	}
	
	
	
}
