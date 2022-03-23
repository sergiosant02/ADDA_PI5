package TestAlumnos;

import java.util.Locale;
import java.util.function.Predicate;

import Geneticos.GenEj5Alumnos;
import datos.DatosEjercicio5;
import tipos.Carretera;
import tipos.Ciudad;
import us.lsi.ag.agchromosomes.AlgoritmoAG;
import us.lsi.ag.agstopping.StoppingConditionFactory;

public class TestEj5 {
	
	public static void testGen(String filePath, Predicate<Ciudad> predicadoCiudad, Predicate<Carretera> predicadoCarretera, String ciudadOrigen, String ciudadDestino) {
		DatosEjercicio5.leerDatos(filePath);
		for(int i = 0; i < DatosEjercicio5.ciudades.size(); i++) {
			System.out.println(i + ": " +DatosEjercicio5.ciudades.get(i));
		}
		System.out.println();
		for(int i = 0; i < DatosEjercicio5.carreteras.size(); i++) {
			System.out.println(i + ": " +DatosEjercicio5.carreteras.get(i));
		}
		//System.out.println(DatosEjercicio5.grafo.containsEdge(DatosEjercicio5.ciudades.get(0), DatosEjercicio5.ciudades.get(23)));

		StoppingConditionFactory.NUM_GENERATIONS = 1000;
		Locale.setDefault(new Locale("en", "US"));
		AlgoritmoAG.POPULATION_SIZE = 50;
		Ciudad origen = DatosEjercicio5.ciudades.stream().filter(c -> c.getNombre().toLowerCase().equals(ciudadOrigen.toLowerCase())).findFirst().orElse(null);
		Ciudad destino = DatosEjercicio5.ciudades.stream().filter(c -> c.getNombre().toLowerCase().equals(ciudadDestino.toLowerCase())).findFirst().orElse(null);
		GenEj5Alumnos genEj5Alumnos = new GenEj5Alumnos(origen, destino, predicadoCiudad, predicadoCarretera);
		
		AlgoritmoAG alg = AlgoritmoAG.of(genEj5Alumnos);
		alg.ejecuta();
		
		System.out.println("==========================");
		System.out.println(alg.bestSolution());
		System.out.println("==========================");
		
	}
	
	
	public static void main(String[] args) {
		Predicate<Ciudad> predicadoCiudad1 = c -> c.getPoblacion() > 100000;
		Predicate<Carretera> predicadoCarretera1 = c -> c.getDistancia() > 100;
		
		Predicate<Ciudad> predicadoCiudad2 = c -> c.getPoblacion() <= 200000;
		Predicate<Carretera> predicadoCarretera2 = c -> c.getDistancia() >= 120;
		
		Predicate<Ciudad> predicadoCiudad3 = c -> c.getPoblacion() > 25000;
		Predicate<Carretera> predicadoCarretera3 = c -> c.getDistancia() < 200;
		
		
		testGen("Ficheros/PI5Ej5DatosEntrada1.txt", predicadoCiudad1, predicadoCarretera1, "Cadiz", "Granada");
		testGen("Ficheros/PI5Ej5DatosEntrada2.txt", predicadoCiudad2, predicadoCarretera2, "Toledo", "Guadalajara");
		testGen("Ficheros/PI5Ej5DatosEntrada3.txt", predicadoCiudad3, predicadoCarretera3, "C01", "C25");
	}
}
