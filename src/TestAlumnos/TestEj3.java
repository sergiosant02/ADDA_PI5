package TestAlumnos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import Geneticos.GenEj3Alumnos;
import datos.DatosEj1Alumnos;
import datos.DatosEj3Alumnos;
import datos.SolucionEj3Alumnos;
import us.lsi.ag.agchromosomes.AlgoritmoAG;
import us.lsi.ag.agstopping.StoppingConditionFactory;
import us.lsi.gurobi.GurobiLp;
import us.lsi.gurobi.GurobiSolution;
import us.lsi.solve.AuxGrammar;

public class TestEj3 {
	
	
	public static void test3PLE(String filePath) throws IOException {
		
		DatosEj3Alumnos.leerDatos(filePath);
		AuxGrammar.generate(DatosEj3Alumnos.class, "grb/Ejercicio3Alumnos.lsi", "ple/ejercicio3Alumnos.lp");
		GurobiSolution gs = GurobiLp.gurobi("ple/Ejercicio3Alumnos.lp");
		System.out.println(SolucionEj3Alumnos.parse(gs.values));
		System.out.println("=============================");
	
}
	
	public static void testGen(String filePath) {
		DatosEj3Alumnos.leerDatos(filePath);
		System.out.println(DatosEj3Alumnos.getComponenetes());
		System.out.println(DatosEj3Alumnos.getProductos());
		System.out.println(DatosEj3Alumnos.getProductionTime());
		System.out.println(DatosEj3Alumnos.getManualTime());
		StoppingConditionFactory.NUM_GENERATIONS = 1000;
		Locale.setDefault(new Locale("en", "US"));
		AlgoritmoAG.POPULATION_SIZE = 50;
		AlgoritmoAG.ELITISM_RATE = 0.2;
		AlgoritmoAG.MUTATION_RATE = 0.6;
		
		GenEj3Alumnos p = new GenEj3Alumnos();
		
		AlgoritmoAG alg = AlgoritmoAG.of(p);
		alg.ejecuta();
		
		System.out.println("==========================");
		System.out.println(alg.bestSolution());
		System.out.println("==========================");
	}

	public static void main(String[] args) throws IOException {
		//testGen("Ficheros/PI5Ej3DatosEntrada1.txt");
		//testGen("Ficheros/PI5Ej3DatosEntrada2.txt");
		testGen("Ficheros/PI5Ej3DatosEntrada3.txt");
		//test3PLE("Ficheros/PI5Ej3DatosEntrada1.txt");
		//test3PLE("Ficheros/PI5Ej3DatosEntrada2.txt");
		//test3PLE("Ficheros/PI5Ej3DatosEntrada3.txt");
		
	}

}
