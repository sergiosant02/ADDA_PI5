package Geneticos;

import java.util.List;

import datos.DatosEjercicio3;
import datos.SolucionEjercicio3;
import us.lsi.ag.ValuesInRangeData;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;
import us.lsi.common.List2;

public class GeneticosEjercicio3 implements ValuesInRangeData<Integer, SolucionEjercicio3> {

	@Override
	public Integer size() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ChromosomeType type() {
		// TODO Auto-generated method stub
		return ChromosomeType.Range;
	}

	@Override
	public Double fitnessFunction(List<Integer> value) {
		Double obj = 0.;
		Integer error1 = 0;
		Integer error2 = 0;
		List<Integer> tamayoGrupo = List2.ofTam(DatosEjercicio3.getNumGrupos(), 0); 
		
		for (int i = 0; i < value.size(); i++) {
			Integer grupo = value.get(i);
			Integer afinidad = DatosEjercicio3.getAfinidad(i, value.get(i));
			obj += afinidad;
			if(afinidad < 1) {
				//no puede estar un alumno en un grupo con afinidad = 0
				error2 += 10000;
			}
			//Se suma 1 al tamañ del grupo en que se ha metido el alumno
			tamayoGrupo.set(grupo, tamayoGrupo.get(grupo)+1);
		}
		//El tamaño de cada grupo debe ser n/m
		for (Integer tamayo : tamayoGrupo) {
			error1 += 1000 *  Math.abs(tamayo-DatosEjercicio3.getTamayoGrupos());
		}
		return obj - error1 - error2;
	}

	@Override
	public SolucionEjercicio3 solucion(List<Integer> value) {
		// TODO Auto-generated method stub
		return new SolucionEjercicio3(value);
	}

	@Override
	public Integer max(Integer i) {
		// TODO Auto-generated method stub
		return DatosEjercicio3.getNumAlumnos();
	}

	@Override
	public Integer min(Integer i) {
		// TODO Auto-generated method stub
		return 0;
	}

}
