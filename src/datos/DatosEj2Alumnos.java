package datos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import us.lsi.common.Files2;
import us.lsi.common.List2;

public class DatosEj2Alumnos {
	
	private static List<Candidato> listaCandidatos;
	private static List<String> cualidadesDeseadas;
	private static Double presupuesto;
	
	public record Candidato(String nombre, List<String> cualidades, Double sueldo, Integer valoracion, List<String> incompatibilidades) {
		public static Candidato parse(String s) {
			String[] partes = s.replaceAll(" ", "").split(";");
			String nombre = partes[0].split(":")[0];
			List<String> cualidades = List2.of(partes[0].split(":")[1].split(","));
			Double sueldo = Double.valueOf(partes[1]);
			Integer valoracion = Integer.valueOf(partes[2]);
			List<String> incompatiblidades = List2.of(partes[3].split(","));
			return new Candidato(nombre, cualidades, sueldo, valoracion, incompatiblidades);
		}
	}
	
	public static void leerDatos(String filePath) {
		List<String> lineas = Files2.linesFromFile(filePath);
		listaCandidatos = new ArrayList<Candidato>();
		cualidadesDeseadas = new ArrayList<>();
		cualidadesDeseadas = List2.of(lineas.get(0).replaceAll(" ", "").split(":")[1].split(","));
		presupuesto = Double.valueOf(lineas.get(1).replaceAll(" ", "").split(":")[1]);
		for(int i = 2; i < lineas.size(); i++) {
			listaCandidatos.add(Candidato.parse(lineas.get(i).replaceAll(" ", "")));
		}
	}
	
	public static Integer getValoracion(Integer i) {
		return listaCandidatos.get(i).valoracion();
	}

	public static List<Candidato> getListaCandidatos() {
		return listaCandidatos;
	}

	public static void setListaCandidatos(List<Candidato> listaCandidatos) {
		DatosEj2Alumnos.listaCandidatos = listaCandidatos;
	}

	public static List<String> getCualidadesDeseadas() {
		return cualidadesDeseadas;
	}

	public static void setCualidadesDeseadas(List<String> cualidadesDeseadas) {
		DatosEj2Alumnos.cualidadesDeseadas = cualidadesDeseadas;
	}

	public static Double getPresupuesto() {
		return presupuesto;
	}

	public static void setPresupuesto(Double presupuesto) {
		DatosEj2Alumnos.presupuesto = presupuesto;
	}
	
	public static Candidato getCandidato(Integer i) {
		return listaCandidatos.get(i);
	}
	
	public static Integer getNumCandidatos() {
		return listaCandidatos.size();
	}
	
	public static Double costeContratacion(Integer i) {
		return listaCandidatos.get(i).sueldo();
	}
	
	public static Double getTotalCosteContratacion(List<Integer> ls) {
		Double sum = 0.;
		for(int i = 0; i < ls.size(); i++) {
			if(ls.get(i) == 1) {
				sum += listaCandidatos.get(i).sueldo();
			}
		}
		return sum;
	}
	
	public static Integer getTotalValoracion(List<Integer> ls) {
		Integer sum = 0;
		for(int i = 0; i < ls.size(); i++) {
			if(ls.get(i) == 1) {
				sum += listaCandidatos.get(i).valoracion();
			}
		}
		return sum;
	}
	
	public static Integer getSatisfaccionCualidades(List<Integer> ls) {
		Set<String> cualidadesPresentes = new HashSet<>();
		Integer faltan = cualidadesDeseadas.size();
		for(int i = 0; i < ls.size(); i++) {
			if(ls.get(i) == 1) {
				cualidadesPresentes.addAll(listaCandidatos.get(ls.get(i)).cualidades());
			}
		}
		for (String cualidad : cualidadesDeseadas) {
			if(cualidadesPresentes.contains(cualidad)) {
				faltan -= 1;
			}
		}
		return faltan;
	}
	
	public static Integer calculoIncompatibilidades(List<Integer> ls) {
		Set<String> incompatibilidades = new HashSet<>();
		Set<String> candidatosPresentes = new HashSet<>();
		for(int i = 0; i < ls.size(); i++) {
			if(ls.get(i) == 1) {
				incompatibilidades.addAll(listaCandidatos.get(ls.get(i)).incompatibilidades());
				candidatosPresentes.add(listaCandidatos.get(i).nombre());
			}
		}
		Integer incompatibleNumber = 0;
		for(String c: candidatosPresentes) {
			if(incompatibilidades.contains(c)) {
				incompatibleNumber += 1;
			}
		}
		return incompatibleNumber * 9999;
	}
	
	public static Integer incompatible(Integer i, Integer j) {
		Candidato c1 = listaCandidatos.get(i);
		Candidato c2 = listaCandidatos.get(2);
		Integer res = 0;
		if(c1.incompatibilidades().contains(c2.nombre()) || c2.incompatibilidades().contains(c1.nombre())) {
			res = 1;
		} 
		return res;
	} 
	
	public static Integer contiene(Integer i) {
		return listaCandidatos.get(i).cualidades().stream().anyMatch(s ->cualidadesDeseadas.contains(s)) ? 1 : 0;
	}
	
	
	
	
}
