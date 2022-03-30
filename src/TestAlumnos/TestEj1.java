package TestAlumnos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import Geneticos.GenEj1Alumnos;
import datos.DatoEjercicio1;
import datos.DatosEj1Alumnos;
import datos.SolucionEj1Alumnos;
import tipos.Ciudad;
import us.lsi.ag.agchromosomes.AlgoritmoAG;
import us.lsi.ag.agstopping.StoppingConditionFactory;
import us.lsi.common.List2;
import us.lsi.gurobi.GurobiLp;
import us.lsi.gurobi.GurobiSolution;
import us.lsi.solve.AuxGrammar;

public class TestEj1 {
	
	public static void test1PLE(String filePath) throws IOException {
		
			DatosEj1Alumnos.leerDatos(filePath);
			AuxGrammar.generate(DatosEj1Alumnos.class, "grb/Ejercicio1Alumnos.lsi", "ple/ejercicio1Alumnos.lp");
			GurobiSolution gs = GurobiLp.gurobi("ple/Ejercicio1Alumnos.lp");
			System.out.println(SolucionEj1Alumnos.parse(gs.values));
			System.out.println("=============================");
	
		
	}
	
	public static void testGen(String filePath) {
		DatosEj1Alumnos.leerDatos(filePath);
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
		System.out.println("Fichero 1");
		testGen("Ficheros/PI5Ej1DatosEntrada1.txt");
		test1PLE("Ficheros/PI5Ej1DatosEntrada1.txt");
		System.out.println("Fichero 2");
		testGen("Ficheros/PI5Ej1DatosEntrada2.txt");
		test1PLE("Ficheros/PI5Ej1DatosEntrada2.txt");
		System.out.println("Fichero 3");
		testGen("Ficheros/PI5Ej1DatosEntrada3.txt");
		test1PLE("Ficheros/PI5Ej1DatosEntrada3.txt");
	
	}

}
