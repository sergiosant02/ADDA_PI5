package TestAlumnos;

import java.util.Locale;

import Geneticos.GenEj3Alumnos;
import datos.DatosEj3Alumnos;
import us.lsi.ag.agchromosomes.AlgoritmoAG;
import us.lsi.ag.agstopping.StoppingConditionFactory;

public class TestEj3 {
	
	public static void testGen(String filePath) {
		DatosEj3Alumnos.leerDatos(filePath);
		System.out.println(DatosEj3Alumnos.getComponenetes());
		System.out.println(DatosEj3Alumnos.getProductos());
		System.out.println(DatosEj3Alumnos.getProductionTime());
		System.out.println(DatosEj3Alumnos.getManualTime());
		System.out.println(DatosEj3Alumnos.getTiempoProducTotal(0));
		StoppingConditionFactory.NUM_GENERATIONS = 500;
		Locale.setDefault(new Locale("en", "US"));
		AlgoritmoAG.POPULATION_SIZE = 50;
		//AlgoritmoAG.ELITISM_RATE = 0.8;
		//AlgoritmoAG.MUTATION_RATE = 0.5;
		
		GenEj3Alumnos p = new GenEj3Alumnos();
		
		AlgoritmoAG alg = AlgoritmoAG.of(p);
		alg.ejecuta();
		
		System.out.println("==========================");
		System.out.println(alg.bestSolution());
		System.out.println("==========================");
	}

	public static void main(String[] args) {
		testGen("Ficheros/PI5Ej3DatosEntrada1.txt");

	}

}
