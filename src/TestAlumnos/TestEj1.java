package TestAlumnos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import Geneticos.GenEj1Alumnos;
import datos.DatoEjercicio1;
import datos.DatosEj1Alumnos;
import us.lsi.ag.agchromosomes.AlgoritmoAG;
import us.lsi.ag.agstopping.StoppingConditionFactory;
import us.lsi.gurobi.GurobiLp;
import us.lsi.gurobi.GurobiSolution;
import us.lsi.solve.AuxGrammar;

public class TestEj1 {
	
	public static void test1PLE(String filePath) throws IOException {
		
			DatosEj1Alumnos.leerDatos(filePath);
			AuxGrammar.generate(DatosEj1Alumnos.class, "grb/Ejercicio1Alumnos.lsi", "ple/ejercicio1Alumnos.lp");
			GurobiSolution gs = GurobiLp.gurobi("ple/Ejercicio1Alumnos.lp");
			System.out.println(gs.values);
			Map<String, Double> m = gs.values;
			List<Double> l = new ArrayList<Double>();
			System.out.println(m);
			System.out.println("=============================");
			System.out.println(l);
		
	}
	
	public static void testGen(String filePath) {
		DatosEj1Alumnos.leerDatos(filePath);
		System.out.println(DatosEj1Alumnos.getFicheros());
		System.out.println(DatosEj1Alumnos.getMemorias());
		StoppingConditionFactory.NUM_GENERATIONS = 1000;
		/*AlgoritmoAG.MUTATION_RATE = 0.7;
		AlgoritmoAG.ELITISM_RATE = 0.7;*/
		Locale.setDefault(new Locale("en", "US"));
		AlgoritmoAG.POPULATION_SIZE = 100;
		
		GenEj1Alumnos p = new GenEj1Alumnos();
		
		AlgoritmoAG alg = AlgoritmoAG.of(p);
		alg.ejecuta();
		
		System.out.println("==========================");
		System.out.println(alg.bestSolution().toString());
		System.out.println("==========================");
	}

	public static void main(String[] args) throws IOException {
		//testGen("Ficheros/PI5Ej1DatosEntrada1.txt");
		test1PLE("Ficheros/PI5Ej1DatosEntrada1.txt");
	}

}
