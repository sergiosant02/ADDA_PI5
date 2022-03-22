package datos;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import datos.DatosEjercicio2.Conjunto;

public record SolucionEjercicio2(List<Conjunto> conjuntos, Double suma) {
	
	public static SolucionEjercicio2 of(Map<String, Double> values) {
		List<Conjunto> conjuntos = new ArrayList<>();
		Double suma = 0.;
		for(int i = 0; i < DatosEjercicio2.getNumConjuntos(); i++) {
			if(values.get("x_"+i) == 1.0) {
				Conjunto c = DatosEjercicio2.getConjunto(i);
				conjuntos.add(DatosEjercicio2.getConjunto(i));
				suma += c.peso();
			}
		}
		return new SolucionEjercicio2(conjuntos, suma);
	}
	
	public static SolucionEjercicio2 of(List<Integer> ls) {
		List<Conjunto> conjuntos = new ArrayList<>();
		Double suma = 0.;
		for(int i = 0; i < ls.size(); i++) {
			if(ls.get(i)==1) {
				Conjunto c = DatosEjercicio2.getConjunto(i);
				conjuntos.add(DatosEjercicio2.getConjunto(i));
				suma += c.peso();
			}
		}
		return new SolucionEjercicio2(conjuntos, suma);
	}
}
