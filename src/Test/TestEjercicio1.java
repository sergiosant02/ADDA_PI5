package Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import Geneticos.GeneticosEjercicio1;
import datos.DatoEjercicio1;
import us.lsi.ag.agchromosomes.AlgoritmoAG;
import us.lsi.ag.agstopping.StoppingConditionFactory;
import us.lsi.gurobi.GurobiLp;
import us.lsi.gurobi.GurobiSolution;
import us.lsi.solve.AuxGrammar;

public class TestEjercicio1 {
	
	public static void test1() {
		DatoEjercicio1.leerDatos(0);
		AlgoritmoAG.POPULATION_SIZE = 100;
		StoppingConditionFactory.NUM_GENERATIONS = 50;
		for(int i = 0; i < 7; i++) {
			var p = new GeneticosEjercicio1();
			var alg = AlgoritmoAG.of(p);
			alg.ejecuta();
			System.out.println("==========================");
			System.out.println(alg.bestSolution());
			System.out.println("==========================");
		}
		
	}
	
	public static void test1PLE() throws IOException {
		for(int i=0; i < 7; i++) {
			DatoEjercicio1.leerDatos(i);
			AuxGrammar.generate(DatoEjercicio1.class, "grb/Ejercicio1.lsi", "ple/ejercicio1.lp");
			GurobiSolution gs = GurobiLp.gurobi("ple/ejercicio1.lp");
			System.out.println(gs.values);
			Map<String, Double> m = gs.values;
			List<Double> l = new ArrayList<Double>();
			for(int j= 0; j < DatoEjercicio1.getNumElements(); j++) {
				l.add(m.get("x_"+j));
				
			}
			System.out.println("=============================");
			System.out.println(l);
		}
	}
	
	public static void main(String[] args) throws IOException {
		/*DatoEjercicio1.leerDatos(2);
		System.out.println(DatoEjercicio1.getConjunto());
		System.out.println(DatoEjercicio1.getSuma());*/
		//test1();
		test1PLE();
		
		
	}
}
