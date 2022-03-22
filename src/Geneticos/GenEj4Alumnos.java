package Geneticos;

import java.util.List;

import datos.DatosEj4Alumnos;
import datos.SolucionEjercicio4;
import us.lsi.ag.ValuesInRangeData;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;

public class GenEj4Alumnos implements ValuesInRangeData<Integer, SolucionEjercicio4> {
	

	public GenEj4Alumnos() {
		
	}

	@Override
	public ChromosomeType type() {
		// TODO Auto-generated method stub
		return ChromosomeType.Range;
	}

	@Override
	public Double fitnessFunction(List<Integer> value) {
		
		Integer errorDesbordamiento = 0;
		Integer restante = 0;
		Integer llenos = 0;
		for(int j = 0; j < DatosEj4Alumnos.getNumContenedores(); j++) {
			Integer sum = 0;
			for(int i = 0; i < value.size(); i++) {
				if(value.get(i)==j) {
					sum += DatosEj4Alumnos.pesoElemento(i);
				}
			}
			if(sum > DatosEj4Alumnos.getContenedor(j).capacidad()) {
				errorDesbordamiento -= sum;
			} else if(sum == DatosEj4Alumnos.getContenedor(j).capacidad()) {
				llenos += 1;
			} else {
				restante += sum;
			}
		}
		
		
		Integer ocu = 0;
		for(int i = 0; i < value.size(); i++) {
			ocu += DatosEj4Alumnos.pesoElemento(i);
		}
		
		Integer noAdmisible = 0;
		for(Integer i = 0; i < value.size(); i++) {
			noAdmisible += DatosEj4Alumnos.isAdmisible(value.get(i), i) ? 0 : 1;
		}
		
		return  llenos * 100. + errorDesbordamiento * 100000 - restante * 100 - noAdmisible * 1000000;
	}

	@Override
	public SolucionEjercicio4 solucion(List<Integer> value) {
		System.out.println(value);
		// TODO Auto-generated method stub
		return SolucionEjercicio4.of(value);
	}

	@Override
	public Integer size() {
		// TODO Auto-generated method stub
		return DatosEj4Alumnos.getNumElementos();
	}

	@Override
	public Integer max(Integer i) {
		// TODO Auto-generated method stub
		return DatosEj4Alumnos.getNumContenedores();
	}

	@Override
	public Integer min(Integer i) {
		// TODO Auto-generated method stub
		return 0;
	}

}
