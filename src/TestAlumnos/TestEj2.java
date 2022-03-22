package TestAlumnos;

import java.io.IOException;
import java.util.Locale;

import Geneticos.GenEj2Alumnos;
import datos.DatosEj2Alumnos;
import datos.DatosEjercicio2;
import datos.SolucionEjercicio2;
import us.lsi.ag.agchromosomes.AlgoritmoAG;
import us.lsi.ag.agstopping.StoppingConditionFactory;
import us.lsi.gurobi.GurobiLp;
import us.lsi.gurobi.GurobiSolution;
import us.lsi.solve.AuxGrammar;

public class TestEj2 {

	public static void testGen(String filePath) {
		DatosEj2Alumnos.leerDatos(filePath);
		System.out.println(DatosEj2Alumnos.getListaCandidatos());
		System.out.println(DatosEj2Alumnos.getPresupuesto());
		System.out.println(DatosEj2Alumnos.getCualidadesDeseadas());
		StoppingConditionFactory.NUM_GENERATIONS = 5000;
		AlgoritmoAG.ELITISM_RATE = 0.1;
		AlgoritmoAG.MUTATION_RATE = 0.8;
		Locale.setDefault(new Locale("en", "US"));
		AlgoritmoAG.POPULATION_SIZE = 150;
		
		GenEj2Alumnos p = new GenEj2Alumnos();
		
		AlgoritmoAG alg = AlgoritmoAG.of(p);
		alg.ejecuta();
		
		System.out.println("==========================");
		System.out.println(alg.bestSolution().toString());
		System.out.println("==========================");
	}
	
	public static void test2PLE(String filePath) throws IOException {
		DatosEj2Alumnos.leerDatos(filePath);
		AuxGrammar.generate(DatosEj2Alumnos.class, "grb/Ejercicio2Alumnos.lsi", "ple/Ejercicio2Alumnos.lp");
		GurobiSolution gs = GurobiLp.solveSolution("ple/Ejercicio2Alumnos.lp");
		System.out.println(gs.values);
	}
	
	public static void main(String[] args) throws IOException {
		
		testGen("Ficheros/PI5Ej2DatosEntrada1.txt");
		//test2PLE("Ficheros/PI5Ej2DatosEntrada1.txt");
	}
	
	
	
}
