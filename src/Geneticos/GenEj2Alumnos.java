package Geneticos;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import datos.DatosEj2Alumnos;
import datos.SolucionEj2Alumnos;
import us.lsi.ag.BinaryData;


public class GenEj2Alumnos implements BinaryData<SolucionEj2Alumnos>{

	@Override
	public Integer size() {
		// TODO Auto-generated method stub
		return DatosEj2Alumnos.getListaCandidatos().size();
	}

	@Override
	public Double fitnessFunction(List<Integer> value) {
		
		Set<String> cualidadesPresentes = new HashSet<>();
		Integer faltan = DatosEj2Alumnos.getCualidadesDeseadas().size();
		for(int i = 0; i < value.size(); i++) {
			if(value.get(i) == 1) {
				cualidadesPresentes.addAll(DatosEj2Alumnos.getCandidato(i).cualidades());
			}
		}
		for (String cualidad : DatosEj2Alumnos.getCualidadesDeseadas()) {
			if(cualidadesPresentes.contains(cualidad)) {
				faltan -= 1;
			}
		}
		Integer valoracionMax = 0;
		
		Set<String> incompatibilidades = new HashSet<>();
		Set<String> candidatosPresentes = new HashSet<>();
		for(int i = 0; i < value.size(); i++) {
			if(value.get(i) == 1) {
				incompatibilidades.addAll(DatosEj2Alumnos.getCandidato(i).incompatibilidades());
				candidatosPresentes.add(DatosEj2Alumnos.getCandidato(i).nombre());
				valoracionMax += DatosEj2Alumnos.getCandidato(i).valoracion();
			}
		}
		Integer incompatibleNumber = 0;
		for(String c: candidatosPresentes) {
			if(incompatibilidades.contains(c)) {
				incompatibleNumber += 1;
				
			}
		}
	
		
		
		Double obj = 0.;
		obj = DatosEj2Alumnos.getTotalValoracion(value) * 100. + valoracionMax * 1000;
		Integer errorSatisfaccionCualidades = 99999 * faltan;
		Integer errorIncompatibilidades = incompatibleNumber * 9999;
		double errorSobrecoste = (DatosEj2Alumnos.getPresupuesto() - DatosEj2Alumnos.getTotalCosteContratacion(value)) < 0 
					? (Math.abs(DatosEj2Alumnos.getPresupuesto() - DatosEj2Alumnos.getTotalCosteContratacion(value)) * -100000) 
							: DatosEj2Alumnos.getPresupuesto() - DatosEj2Alumnos.getTotalCosteContratacion(value);
		return obj - errorSatisfaccionCualidades - errorIncompatibilidades + errorSobrecoste;
	}

	@Override
	public SolucionEj2Alumnos solucion(List<Integer> value) {
		// TODO Auto-generated method stub
		System.out.println(" satisfaccion cualidades: " + DatosEj2Alumnos.getSatisfaccionCualidades(value));
		System.out.println(DatosEj2Alumnos.getTotalCosteContratacion(value));
		System.out.println(DatosEj2Alumnos.getCualidadesDeseadas());
		Set<String> cualidadesPresentes = new HashSet<>();
		Integer faltan = DatosEj2Alumnos.getCualidadesDeseadas().size();
		for(int i = 0; i < value.size(); i++) {
			if(value.get(i) == 1) {
				cualidadesPresentes.addAll(DatosEj2Alumnos.getCandidato(i).cualidades());
			}
		}
		for (String cualidad : DatosEj2Alumnos.getCualidadesDeseadas()) {
			if(cualidadesPresentes.contains(cualidad)) {
				faltan -= 1;
			}
		}
		System.out.println("presentes: " + cualidadesPresentes + " number: " + faltan);
		return SolucionEj2Alumnos.of(value);
	}

}
