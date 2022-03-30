package Geneticos;

import java.util.ArrayList;
import java.util.List;

import datos.DatosEj1Alumnos;
import datos.DatosEj1Alumnos.Memoria;
import datos.SolucionEj1Alumnos;
import us.lsi.ag.AuxiliaryAg;
import us.lsi.ag.ValuesInRangeData;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;

public class GenEj1Alumnos implements ValuesInRangeData<Integer,SolucionEj1Alumnos> {

	@Override
	public ChromosomeType type() {
		// TODO Auto-generated method stub
		return ChromosomeType.Range;
	}

	@Override
	public Double fitnessFunction(List<Integer> value) {
		
		List<Memoria> l = DatosEj1Alumnos.getMemorias();
		Integer errorExtra = 0; // Error relativo a la tasa máxima de transferencia
		Integer errorExceso = 0; // Error relativo a cargar una memoria con archivos demasiado pesados
		
		List<Integer> excesos = new ArrayList<Integer>();
		for(int i = 0; i < DatosEj1Alumnos.getMemorias().size()-1; i ++) {
			excesos.add(DatosEj1Alumnos.getMemoria(i).capacidad());
		}
		for(int i = 0; i < value.size(); i++ ) {
			if(value.get(i) != DatosEj1Alumnos.getMemorias().size()-1) {
				Integer memoAct = value.get(i);  //memoria sobre la que estoy actuando
				
				errorExtra += DatosEj1Alumnos.getMemoria(memoAct).tamMax() >= DatosEj1Alumnos.getFichero(i).tam() ? 0 : 10000;
				
				excesos.set(memoAct, excesos.get(memoAct)-DatosEj1Alumnos.getFichero(i).tam()); //actualizo el exceso de esa memoria
			
			}
		}
		Double error = excesos.stream().reduce((acum, num) -> {
			return acum += num < 0 ? 1000 : num;
		}).orElse(0) * 1.;
		errorExceso = excesos.stream().filter(n -> n<0).reduce((acum, num) -> {
			return acum += num ;
		}).orElse(0);
		error = AuxiliaryAg.distanceToEqZero(error);
		Integer numArchivosAlmacenados = value.stream()
				.filter(n -> n!= DatosEj1Alumnos.getMemorias().size()-1)
				.toList().size();
		
		
		Double res = numArchivosAlmacenados * 200000 - error * 10 - errorExtra * 2000 + errorExceso * 22000;
		return res;
	}

	@Override
	public SolucionEj1Alumnos solucion(List<Integer> value) {
		// TODO Auto-generated method stub
		return SolucionEj1Alumnos.of(value);
	}

	@Override
	public Integer size() {
		// TODO Auto-generated method stub
		return DatosEj1Alumnos.getFicheros().size();
	}

	@Override
	public Integer max(Integer i) {
		// TODO Auto-generated method stub
		return DatosEj1Alumnos.numMemorias();
	}

	@Override
	public Integer min(Integer i) {
		// TODO Auto-generated method stub
		return 0;
	}

}
