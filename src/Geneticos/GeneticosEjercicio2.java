package Geneticos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import datos.DatosEjercicio2;
import datos.SolucionEjercicio2;
import datos.DatosEjercicio2.Conjunto;
import us.lsi.ag.BinaryData;

public class GeneticosEjercicio2 implements BinaryData<SolucionEjercicio2> {

	@Override
	public Integer size() {
		// TODO Auto-generated method stub
		return DatosEjercicio2.getNumConjuntos();
	}

	@Override
	public Double fitnessFunction(List<Integer> value) {
		Set<Integer> numeros = new HashSet<>();
		Double suma = 0.;
		Double obj = 0.;
		for(int i = 0; i < value.size(); i++) {
			if(value.get(i)==1) {
				Conjunto c = DatosEjercicio2.getConjunto(i);
				numeros.addAll(c.numeros());
				suma += c.peso();
				obj -= c.peso();
			}
		}
		Double error = 100000. * (DatosEjercicio2.getTamayoUniverso() - numeros.size());
		
		return obj - error ;
	}

	@Override
	public SolucionEjercicio2 solucion(List<Integer> value) {
		// TODO Auto-generated method stub
		return SolucionEjercicio2.of(value);
	}
	
}
