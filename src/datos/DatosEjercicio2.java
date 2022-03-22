package datos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import us.lsi.common.Files2;
import us.lsi.common.Set2;

public class DatosEjercicio2 {
	
	private static List<Conjunto> conjuntos;
	private static List<Integer> universo;
	
	public record Conjunto(Double peso, Set<Integer> numeros) {
		public static Conjunto of(String s) {
			String[] partes = s.split(":");
			Double peso = Double.valueOf(partes[1]);
			Set<Integer> numeros = Set2.parse(partes[0], "{,}", Integer::valueOf);
			return new Conjunto(peso, numeros);
		}
	}
	
	public static void leerDatos(String fileName) {
		List<String> lineas = Files2.linesFromFile(fileName);
		conjuntos = new ArrayList<Conjunto>();
		
		Set<Integer> universoSet = new HashSet<Integer>();
		for(String linea: lineas) {
			Conjunto c = Conjunto.of(linea);
			conjuntos.add(c);
			universoSet.addAll(c.numeros());
		}
		universo = new ArrayList<Integer>(universoSet);
	}
	
	public static Integer getNumConjuntos() {
		return conjuntos.size();
	}
	
	public static Integer getTamayoUniverso() {
		return universo.size();
	}
	
	public static Conjunto getConjunto(Integer i) {
		return conjuntos.get(i);
	}

	public static List<Conjunto> getConjuntos() {
		return conjuntos;
	}

	public static void setConjuntos(List<Conjunto> conjuntos) {
		DatosEjercicio2.conjuntos = conjuntos;
	}

	public static List<Integer> getUniverso() {
		return universo;
	}

	public static void setUniverso(List<Integer> universo) {
		DatosEjercicio2.universo = universo;
	}
	
	public static Double getPesoConjunto(Integer i ) {
		return conjuntos.get(i).peso();
	}
	
	public static Integer contiene(Integer i, Integer j) {
		Conjunto c = conjuntos.get(i);
		Integer numero = universo.get(j);
		return c.numeros.contains(numero) ? 1 : 0;
	}
	
}
