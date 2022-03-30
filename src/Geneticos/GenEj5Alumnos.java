package Geneticos;

import java.util.List;
import java.util.function.Predicate;

import org.jgrapht.alg.shortestpath.DijkstraShortestPath;

import datos.DatosEjercicio5;
import datos.SolucionEjercicio5Alumnos;
import tipos.Carretera;
import tipos.Ciudad;
import us.lsi.ag.SeqNormalData;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;
import us.lsi.common.List2;

public class GenEj5Alumnos implements SeqNormalData<SolucionEjercicio5Alumnos>{
	
	private Ciudad ciudadOrigen;
	private Ciudad ciudadDestino;
	private Predicate<Ciudad> predicadoCiudad;
	private Predicate<Carretera> predicadoCarretera;
	
	public GenEj5Alumnos(Ciudad ciudadOrigen,Ciudad ciudadDestino, Predicate<Ciudad> predicadoCiudad, Predicate<Carretera> predicadoCarretera) {
		this.ciudadDestino = ciudadDestino;
		this.ciudadOrigen = ciudadOrigen;
		this.predicadoCarretera = predicadoCarretera;
		this.predicadoCiudad = predicadoCiudad;
	}

	@Override
	public ChromosomeType type() {
		// TODO Auto-generated method stub
		return ChromosomeType.Permutation;
	}

	@Override
	public Double fitnessFunction(List<Integer> value) {
		var path = DijkstraShortestPath.findPathBetween(DatosEjercicio5.grafo, ciudadOrigen, ciudadDestino);
		Double pesoIdeal = path.getWeight();
		Double res = 0.;
		Double errorCaminos = 0.; //si no existe arista entre los vertices
		Double pesoReal = 0.;
		List<Ciudad> rsCiudad = List2.empty();
		List<Carretera> rsCarretera = List2.empty();
		Double errorPredicadoCiudad = 0.;
		Double errorPredicadoCarretera = 0.;
		
		if(!DatosEjercicio5.getCiudad(value.get(0)).equals(ciudadOrigen)) {
			res = Double.NEGATIVE_INFINITY;
		} else {
			Integer indexCiudad = DatosEjercicio5.ciudades.indexOf(ciudadDestino);
			value = value.subList(0,(value.indexOf(indexCiudad)+1));  //hago una sublista con los valores que realmente me interesan
			//Comprobamos que existan aristas entre todos los vertices
			for(int i = 0; i < value.size()-1; i++) {
				Ciudad or = DatosEjercicio5.getCiudad(value.get(i));
				Ciudad dest = DatosEjercicio5.getCiudad(value.get(i+1));
				
				if(!DatosEjercicio5.grafo.containsEdge(or, dest)) {
					errorCaminos += DatosEjercicio5.ciudades.size() * 1000.;
					
					
				} else {
					Carretera carretera = DatosEjercicio5.grafo.getEdge(or, dest);
					if(i>0) {
						rsCiudad.add(DatosEjercicio5.getCiudad(value.get(i)));
					}
					rsCarretera.add(carretera);
					pesoReal += DatosEjercicio5.grafo.getEdgeWeight(carretera);
				}
			}
			
			if(rsCiudad.stream().anyMatch(c-> predicadoCiudad.test(c))) {
				errorPredicadoCiudad = 0.;
			} else {
				errorPredicadoCiudad = DatosEjercicio5.ciudades.size() * DatosEjercicio5.ciudades.size() * 10000.;
			}
			
			if(rsCarretera.stream().anyMatch(c-> predicadoCarretera.test(c))) {
				errorPredicadoCarretera = 0.;
			} else {
				errorPredicadoCarretera = DatosEjercicio5.ciudades.size() * DatosEjercicio5.ciudades.size() * 10000.;
			}
				
			res = (pesoIdeal - pesoReal) - errorCaminos - errorPredicadoCarretera - errorPredicadoCiudad;
			
		
		}
		return res;
	}

	@Override
	public SolucionEjercicio5Alumnos solucion(List<Integer> value) {
		// TODO Auto-generated method stub
		return SolucionEjercicio5Alumnos.parse(value.subList(0, value.indexOf(DatosEjercicio5.ciudades.indexOf(ciudadDestino))+1));
	}


	@Override
	public Integer itemsNumber() {
		// TODO Auto-generated method stub
		return DatosEjercicio5.ciudades.size();
	}

	

}
