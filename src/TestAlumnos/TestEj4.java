package TestAlumnos;

import java.util.Locale;

import Geneticos.GenEj4Alumnos;
import datos.DatosEj4Alumnos;
import us.lsi.ag.agchromosomes.AlgoritmoAG;
import us.lsi.ag.agstopping.StoppingConditionFactory;

public class TestEj4 {

	public static void testGen(String filePath) {
		DatosEj4Alumnos.leerDatos(filePath);
		System.out.println(DatosEj4Alumnos.muestra());

		StoppingConditionFactory.NUM_GENERATIONS = 5000;
		Locale.setDefault(new Locale("en", "US"));
		AlgoritmoAG.POPULATION_SIZE = 100;
		
		
			GenEj4Alumnos p = new GenEj4Alumnos();
			
			AlgoritmoAG alg = AlgoritmoAG.of(p);
			alg.ejecuta();
			
			System.out.println("==========================");
			System.out.println(alg.bestSolution().toString());
			System.out.println("==========================");
		}
	
	
	
	public static void main(String[] args) {
		testGen("Ficheros/PI5Ej4DatosEntrada1.txt");

	}

}
