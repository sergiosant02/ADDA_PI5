package datos;

import java.util.ArrayList;
import java.util.List;

public record SolucionEjercicio5Alumnos(List<String> ls) {
	public static SolucionEjercicio5Alumnos parse(List<Integer> solucion) {
		List<String> res = new ArrayList<>();
		solucion.stream().map(i -> DatosEjercicio5.getCiudad(i)).forEach(s -> res.add(s.getNombre()));
		return new SolucionEjercicio5Alumnos(res);
	}

	@Override
	public String toString() {
		return ls.toString();
	}
}
