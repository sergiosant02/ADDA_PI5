package Geneticos;

import java.util.List;

import datos.DatosEj3Alumnos;
import datos.SolucionEj3Alumnos;
import us.lsi.ag.AuxiliaryAg;
import us.lsi.ag.ValuesInRangeData;
import us.lsi.ag.agchromosomes.ChromosomeFactory.ChromosomeType;

public class GenEj3Alumnos implements ValuesInRangeData<Integer, SolucionEj3Alumnos> {

	@Override
	public Integer size() {
		// TODO Auto-generated method stub
		return DatosEj3Alumnos.getNumProducts();
	}

	@Override
	public ChromosomeType type() {
		// TODO Auto-generated method stub
		return ChromosomeType.Range;
	}

	@Override
	public Double fitnessFunction(List<Integer> value) {
		Double obj = 0.;
		Double errorManual = 0.;
		Double errorProduccion = 0.;
		Integer errorUnidadesMaximas = 0;
		//Maximizo el precio
		for(int i= 0; i < value.size(); i++) {
			obj += (value.get(i) * DatosEj3Alumnos.getProducto(i).precio());
		}
		//Calculo de error sobre tiempo manual
		for(int i= 0; i < value.size(); i++) {
			errorManual += (value.get(i) * DatosEj3Alumnos.getTiempoManualTotal(i));
		}
		//Calculo de error sobre tiempo produccion
		for(int i= 0; i < value.size(); i++) {
					errorProduccion += (value.get(i) * DatosEj3Alumnos.getTiempoProducTotal(i));
		}
		//Calculo error maximo unidades
		for(int i = 0; i < value.size(); i++) {
			errorUnidadesMaximas -= (DatosEj3Alumnos.getProducto(i).maxUnidades() - value.get(i)) < 0 
										? (DatosEj3Alumnos.getProducto(i).maxUnidades() - value.get(i)) 
												: 0;
		}
		errorManual = errorManual - DatosEj3Alumnos.getManualTime();
		errorProduccion =  errorProduccion - DatosEj3Alumnos.getProductionTime();
		Double res = obj*10000 - errorManual * 100000 - errorProduccion * 100000;
		if(res>=0 && SolucionEj3Alumnos.of(value).beneficio() > 1690 && value.get(6)>16) System.out.println(value);
		return res;
	}

	@Override
	public SolucionEj3Alumnos solucion(List<Integer> value) {
		// TODO Auto-generated method stub
		return SolucionEj3Alumnos.of(value);
	}

	@Override
	public Integer max(Integer i) {
		Integer maximo = DatosEj3Alumnos.getProducto(i).maxUnidades();
		return maximo;
	}

	@Override
	public Integer min(Integer i) {
		// TODO Auto-generated method stub
		return 0;
	}

}
