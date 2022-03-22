package Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import Geneticos.GeneticosEjercicio2;
import datos.DatoEjercicio1;
import datos.DatosEjercicio2;
import datos.SolucionEjercicio2;
import us.lsi.ag.agchromosomes.AlgoritmoAG;
import us.lsi.ag.agstopping.StoppingConditionFactory;
import us.lsi.gurobi.GurobiLp;
import us.lsi.gurobi.GurobiSolution;
import us.lsi.solve.AuxGrammar;

public class TestEjercicio2 {
	
	public static void ej2Geneticos() {
		DatosEjercicio2.leerDatos("Ficheros/Ejemplo2DatosEntrada1.txt");
		System.out.println(DatosEjercicio2.getConjuntos());
		System.out.println(DatosEjercicio2.getUniverso());
		StoppingConditionFactory.NUM_GENERATIONS = 50;
		Locale.setDefault(new Locale("en", "US"));
		AlgoritmoAG.POPULATION_SIZE = 100;
		
		GeneticosEjercicio2 p = new GeneticosEjercicio2();
		
		AlgoritmoAG alg = AlgoritmoAG.of(p);
		alg.ejecuta();
		
		System.out.println("==========================");
		System.out.println(alg.bestSolution());
		System.out.println("==========================");
		
	}
	
	public static void test2PLE() throws IOException {
		DatosEjercicio2.leerDatos("Ficheros/Ejemplo2DatosEntrada1.txt");
		AuxGrammar.generate(DatosEjercicio2.class, "grb/Ejercicio2.lsi", "ple/ejercicio2.lp");
		GurobiSolution gs = GurobiLp.solveSolution("ple/ejercicio2.lp");
		System.out.println(SolucionEjercicio2.of(gs.values));
	}
	
	public static void main(String[] args) throws IOException {
		ej2Geneticos();
		test2PLE();
	}
}
