package TestAlumnos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import Geneticos.GenEj4Alumnos;
import datos.DatosEj1Alumnos;
import datos.DatosEj4Alumnos;
import datos.DatosEj4Alumnos.Elemento;
import datos.SolucionEjercicio4Alumnos;
import us.lsi.ag.agchromosomes.AlgoritmoAG;
import us.lsi.ag.agstopping.StoppingConditionFactory;
import us.lsi.gurobi.GurobiLp;
import us.lsi.gurobi.GurobiSolution;
import us.lsi.solve.AuxGrammar;

public class TestEj4 {
	
	public static void test4PLE(String filePath) throws IOException {
		
		DatosEj4Alumnos.leerDatos(filePath);
		AuxGrammar.generate(DatosEj4Alumnos.class, "grb/Ejercicio4Alumnos.lsi", "ple/ejercicio4Alumnos.lp");
		GurobiSolution gs = GurobiLp.gurobi("ple/Ejercicio4Alumnos.lp");
		Map<String, Double> m = gs.values;
		SolucionEjercicio4Alumnos sol = SolucionEjercicio4Alumnos.parse(m);
		for(String s: sol.map().keySet()) {
			System.out.println(s);
			for(Elemento e: sol.map().get(s)) {
				System.out.println("\t"+e.toString());
			}
		}
		System.out.println("=============================");
	
}

	public static void testGen(String filePath) {
		DatosEj4Alumnos.leerDatos(filePath);

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
	
	
	
	public static void main(String[] args) throws IOException {
		testGen("Ficheros/PI5Ej4DatosEntrada1.txt");
		//testGen("Ficheros/PI5Ej4DatosEntrada2.txt");
		//testGen("Ficheros/PI5Ej4DatosEntrada3.txt");
		//test4PLE("Ficheros/PI5Ej4DatosEntrada1.txt");
		//test4PLE("Ficheros/PI5Ej4DatosEntrada2.txt");
		//test4PLE("Ficheros/PI5Ej4DatosEntrada3.txt");
	}

}
