package datos;

import java.util.List;

import org.jgrapht.Graph;

import tipos.Carretera;
import tipos.Ciudad;
import us.lsi.graphs.Graphs2;
import us.lsi.graphs.GraphsReader;

public class DatosEjercicio5 {
	public static Graph<Ciudad, Carretera> grafo;
	public static List<Ciudad> ciudades;
	public static List<Carretera> carreteras;
	
	public static void leerDatos(String filePath) {
		grafo = GraphsReader.newGraph(filePath, 
				Ciudad::of, 
				Carretera::of, 
				Graphs2::simpleWeightedGraph, 
				Carretera::getDistancia);
		ciudades = grafo.vertexSet().stream().toList();
		carreteras = grafo.edgeSet().stream().toList();
	}

	public static Graph<Ciudad, Carretera> getGrafo() {
		return grafo;
	}

	public static void setGrafo(Graph<Ciudad, Carretera> grafo) {
		DatosEjercicio5.grafo = grafo;
	}

	public static List<Ciudad> getCiudades() {
		return ciudades;
	}

	public static void setCiudades(List<Ciudad> ciudades) {
		DatosEjercicio5.ciudades = ciudades;
	}

	public static List<Carretera> getCarreteras() {
		return carreteras;
	}

	public static void setCarreteras(List<Carretera> carreteras) {
		DatosEjercicio5.carreteras = carreteras;
	}
	
	public static Ciudad getCiudad(Integer i) {
		return ciudades.get(i);
	}
	
	public static Carretera getCarretera(Integer i) {
		return carreteras.get(i);
	}
	
	
}
