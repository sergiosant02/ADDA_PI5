package tipos;

import java.util.Objects;

public class Carretera {
	private Integer id;
	private String origen;
	private String destino;
	private String nombre;
	private Double distancia;
	private static int count = 0;
	
	public Carretera(String origen, String destino, String nombre, Double distancia) {
		super();
		this.id = count;
		this.origen = origen;
		this.destino = destino;
		this.nombre = nombre;
		this.distancia = distancia;
		count++;
	}
	
	public static Carretera of(String[] partes) {
		return new Carretera(partes[0], partes[1], partes[2], Double.valueOf(partes[3]));
	}

	public String getOrigen() {
		return origen;
	}

	public void setOrigen(String origen) {
		this.origen = origen;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Double getDistancia() {
		return distancia;
	}

	public void setDistancia(Double distancia) {
		this.distancia = distancia;
	}

	public Integer getId() {
		return id;
	}

	@Override
	public int hashCode() {
		return Objects.hash(destino, distancia, id, nombre, origen);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Carretera other = (Carretera) obj;
		return Objects.equals(destino, other.destino) && Objects.equals(distancia, other.distancia)
				&& Objects.equals(id, other.id) && Objects.equals(nombre, other.nombre)
				&& Objects.equals(origen, other.origen);
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "(nombre: " + this.nombre + ", " + "distancia: " + this.distancia+ ", origen: " + this.origen + ", destino: " + this.destino + ", peso: "+this.distancia + ")";
	}
	
	
}
