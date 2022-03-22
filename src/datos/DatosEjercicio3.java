package datos;

import java.util.ArrayList;
import java.util.List;

import us.lsi.common.Files2;
import us.lsi.common.List2;

public class DatosEjercicio3 {
	
	public record Alumno(String alumno, List<Integer> afinidades) {
		public static Alumno of(String s) {
			String[] partes = s.split(":");
			String nombre = partes[0];
			List<Integer> afinidades = List2.parse(partes[1], ",", Integer::valueOf);
			return new Alumno(nombre, afinidades);
 		}
		
		public static void leerDatos(String filePath) {
			alumnos = new ArrayList<>();
			List<String> lineas = Files2.linesFromFile(filePath);
			for(String l: lineas) {
				Alumno alumno = Alumno.of(l);
				alumnos.add(alumno);
			}
		}
		
	}
	
	private static List<Alumno> alumnos;
	
	public static Integer getNumAlumnos() {
		return alumnos.size();
	}
	
	public static Integer getAfinidad(Integer i, Integer j){
		return alumnos.get(i).afinidades().get(j);
	}

	public static List<Alumno> getAlumnos() {
		return alumnos;
	}

	public static void setAlumnos(List<Alumno> alumnos) {
		DatosEjercicio3.alumnos = alumnos;
	}


	public static Integer getNumGrupos() {
		return alumnos.get(0).afinidades().size();
	}
	
	public static Integer getTamayoGrupos() {
		return getNumAlumnos()/getNumGrupos();
	}

}
