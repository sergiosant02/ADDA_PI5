package datos;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import us.lsi.common.Files2;

public class DatoEjercicio1 {
	private static List<Integer> conjunto;
	private static Integer suma;
	
	public static void leerDatos(Integer linea) {
		List<String> lineas = Files2.linesFromFile("Ficheros/Ejemplo1DatosEntrada.txt");
		String linea1 = lineas.get(0);
		String[] partes = linea1.split(":");
		suma = Integer.valueOf(partes[1]);
		String[] numeros = partes[0].split(",");
		conjunto = Arrays.stream(numeros).map(n -> Integer.valueOf(n)).collect(Collectors.toList());
	}
	
	public static List<Integer> getConjunto(){
		return conjunto;
	}
	
	public static Integer getSuma() {
		return suma;
	}
	public static Integer getNumero(Integer i) {
		return conjunto.get(i);
	}
	public static Integer getMultiplicidad(Integer i) {
		return suma / conjunto.get(i);
	}
	
	public static Integer getNumElements() {
		return conjunto.size();
	}
}
