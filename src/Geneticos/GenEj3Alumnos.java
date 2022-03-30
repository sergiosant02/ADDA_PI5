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
		Double errorUnidadesMaximas = 0.;
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
			errorUnidadesMaximas = ((errorUnidadesMaximas != Double.NEGATIVE_INFINITY) && (DatosEj3Alumnos.getProducto(i).maxUnidades() - value.get(i)) < 0) 
										? Double.NEGATIVE_INFINITY
												: 0;
		}
		errorManual = DatosEj3Alumnos.getManualTime() - errorManual;
		//if(errorManual < 0) errorManual = Double.NEGATIVE_INFINITY;
		errorProduccion = DatosEj3Alumnos.getProductionTime() - errorProduccion;
		//if(errorProduccion < 0 ) errorProduccion = Double.NEGATIVE_INFINITY;
		//errorManual = AuxiliaryAg.distanceToEqZero(errorManual);
		//errorProduccion = AuxiliaryAg.distanceToEqZero(errorProduccion);
		Double res = (errorManual + errorProduccion + errorUnidadesMaximas) < 0 
				? (errorManual + errorProduccion + errorUnidadesMaximas) 
						: obj;
		return res * 1000;
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
