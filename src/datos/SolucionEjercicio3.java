package datos;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public record SolucionEjercicio3(List<Integer> asignaciones) {
	public static SolucionEjercicio3 of(Map<String, Double> value) {
		List<Integer> asignaciones = new ArrayList<>();
		for(int i = 0; i < DatosEjercicio3.getNumAlumnos(); i++) {
			for(int j = 0; j < DatosEjercicio3.getNumGrupos(); j++) {
				if(value.get("x_"+i+"_"+j) > 0) {
					asignaciones.add(j);
				}
			}
		}
		return new SolucionEjercicio3(asignaciones);
	}
}
