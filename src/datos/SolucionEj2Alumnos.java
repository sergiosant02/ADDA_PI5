package datos;

import java.util.ArrayList;
import java.util.List;

import datos.DatosEj2Alumnos.Candidato;

public record SolucionEj2Alumnos(List<Candidato> candidatos) {
	public static SolucionEj2Alumnos of(List<Integer> ls) {
		List<Candidato> candidatos = new ArrayList<>();
		for(int i = 0; i < ls.size(); i++) {
			if(ls.get(i) == 1) {
				candidatos.add(DatosEj2Alumnos.getCandidato(i));
			}
		}
		return new SolucionEj2Alumnos(candidatos);
	}
	public String toString() {
		String s = "";
		Double coste = 0.;
		Double valoracionMed = 0.;
		List<String> nombres = new ArrayList<String>();
		for(Candidato c: candidatos) {
			s += c.nombre()+"\n";
			s += "\tCualidades: " + c.cualidades()+"\n";
			s += "\tSueldo: " + c.sueldo()+"\n";
			s += "\tValoracion: " + c.valoracion()+"\n";
			s += "\tIncompatibilidades: " + c.incompatibilidades()+"\n";
			coste += c.sueldo();
			nombres.add(c.nombre());
			valoracionMed += c.valoracion();
		}
		valoracionMed = valoracionMed / candidatos().size();
		
		s += "Total coste: " + coste;
		s += "\n escogidos: " + nombres;
		s += "\n valoracion media: " + valoracionMed;
		return s;
	}
}
