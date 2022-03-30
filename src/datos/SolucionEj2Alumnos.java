package datos;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import datos.DatosEj2Alumnos.Candidato;
import us.lsi.common.List2;

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
	public static SolucionEj2Alumnos parse(Map<String, Double> map) {
		List<Candidato> ls = List2.empty();
		for(String key: map.keySet()) {
			if(map.get(key) >0 ) {
				String[] partes = key.split("_");
				Integer indice = Integer.valueOf(partes[1]);
				ls.add(DatosEj2Alumnos.getCandidato(indice));
			}
		}
		return new SolucionEj2Alumnos(ls);
	}
	public String toString() {
		String s = "\n";
		Double coste = 0.;
		Double valoracionMed = 0.;
		List<String> nombres = new ArrayList<String>();
		for(Candidato c: candidatos) {
			/*s += c.nombre()+"\n";
			s += "\tCualidades: " + c.cualidades()+"\n";
			s += "\tSueldo: " + c.sueldo()+"\n";
			s += "\tValoracion: " + c.valoracion()+"\n";
			s += "\tIncompatibilidades: " + c.incompatibilidades()+"\n";*/
			coste += c.sueldo();
			nombres.add(c.nombre());
			valoracionMed += c.valoracion();
		}
		valoracionMed = valoracionMed / candidatos().size();
		
		s += "Total coste: " + Double.valueOf(Math.round(coste * 10))/10;
		s += "\n escogidos: " + nombres;
		s += "\n valoracion media: " + Double.valueOf(Math.round(valoracionMed * 10))/10;
		return s;
	}
}
