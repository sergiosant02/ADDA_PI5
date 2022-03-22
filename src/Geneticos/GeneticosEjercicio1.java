package Geneticos;

import java.util.Iterator;
import java.util.List;

import datos.DatoEjercicio1;
import us.lsi.ag.ValuesInRangeData;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;

public class GeneticosEjercicio1 implements ValuesInRangeData<Integer, List<Integer>>{

	@Override
	public Integer size() {
		// TODO Auto-generated method stub
		return DatoEjercicio1.getConjunto().size();
	}

	@Override
	public ChromosomeType type() {
		// TODO Auto-generated method stub
		return ChromosomeType.Range;
	}



	@Override
	public Double fitnessFunction(List<Integer> value) {
		Double objetivo = 0.;
		Integer suma = 0;
		for (int i = 0;i < value.size() ; i++) {
			Integer numero = DatoEjercicio1.getNumero(i);
			Integer multiplicidad = value.get(i);
			suma = numero * multiplicidad;
			objetivo -= multiplicidad;
			
			
		}
		Integer error = Math.abs(suma - DatoEjercicio1.getSuma()) * 10000000;
		return objetivo - error;
	}

	@Override
	public List<Integer> solucion(List<Integer> value) {
		// TODO Auto-generated method stub
		return value;
	}

	@Override
	public Integer max(Integer i) {
		// TODO Auto-generated method stub
		return DatoEjercicio1.getMultiplicidad(i)+1;
	}

	@Override
	public Integer min(Integer i) {
		// TODO Auto-generated method stub
		return 0;
	}


	
}
