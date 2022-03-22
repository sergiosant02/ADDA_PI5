package datos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.stream.IntStream;

import us.lsi.common.Files2;

public class DatosEj4Alumnos {
	
	
	public static String muestra() {
		String s = "";
		s += "Elementos:\n";
		for(Elemento c: elementos) {
			s += "\t"+c.toString() +"\n";
		}
		s += "Contendores:\n";
		for(Contenedor c: contenedores) {
			s += "\t"+c.toString() +"\n";
		}
		
		return s;
	}
	
	private static List<Contenedor> contenedores;
	private static List<Elemento> elementos;
	
	public record Contenedor(String nombre, Integer capacidad, String tipo) {
	
		@Override
		public String toString() {
			return "(nombre: "+nombre+", capacidad: " + capacidad + ", tipo: "+tipo+")";
		}
		
		public static Contenedor parse(String s) {
			String[] partes = s.replaceAll(" ", "").split(";");
			String nombre = partes[0].split(":")[0];
			Integer capacidad = Integer.valueOf(partes[0].split("=")[1]);
			String tipo = partes[1].split("=")[1];
			return new Contenedor(nombre, capacidad, tipo);
		}
	}
	
	public record Elemento(String nombre, Integer tam, List<String> posibilidades) {
		
		@Override
		public String toString() {
			return "(nombre: "+nombre+", tamaño: " + tam + ", posibilidades: "+posibilidades+")";
		}
		
		public static Elemento parse(String s) {
			String[] partes = s.replaceAll(" ", "").split(";");
			String nombre = partes[0].split(":")[0];
			Integer tam = Integer.valueOf(partes[0].split(":")[1]);
			List<String> posibilidades = List.of(partes[1].split(","));
			return new Elemento(nombre, tam, posibilidades);
		}
	}
	
	public static void leerDatos(String filePath) {
		contenedores = new ArrayList<>();
		elementos = new ArrayList<Elemento>();
		List<String> lineas = Files2.linesFromFile(filePath);
		Boolean contenedoresRead = false;
		Boolean elementRead = false;
		for(int i = 0; i < lineas.size(); i++) {
			if(lineas.get(i).contains("CONTENEDORES")) {
				contenedoresRead = true;
				elementRead = false;
			} else if(lineas.get(i).contains("ELEMENTOS")) {
				contenedoresRead = false;
				elementRead = true;
			} else if(contenedoresRead && !elementRead) {
				contenedores.add(Contenedor.parse(lineas.get(i)));
			} else if (!contenedoresRead && elementRead) {
				elementos.add(Elemento.parse(lineas.get(i)));
			}
		}
		Integer maxCapacity = elementos.stream().map(e->e.tam).reduce((acum, num) -> acum + num).orElse(0);
		Contenedor contenedorAux = new Contenedor("CONT"+(contenedores.size()+1), maxCapacity, "MASTER");
		contenedores.add(contenedorAux);
	}

	public static List<Contenedor> getContenedores() {
		return contenedores;
	}

	public static void setContenedores(List<Contenedor> contenedores) {
		DatosEj4Alumnos.contenedores = contenedores;
	}

	public static List<Elemento> getElementos() {
		return elementos;
	}

	public static void setElementos(List<Elemento> elementos) {
		DatosEj4Alumnos.elementos = elementos;
	}
	
	public static Contenedor getContenedor(Integer i) {
		return contenedores.get(i);
	}
	
	public static Elemento getElemento(Integer i) {
		return elementos.get(i);
	}
	
	public static Integer pesoElemento(Integer i) {
		return elementos.get(i).tam();
	}
	
	public static Boolean isAdmisible(Contenedor contenedor, Elemento elemento) {
		return elemento.posibilidades().contains(contenedor.tipo()) || contenedor.tipo() == "MASTER";
	}
	
	public static Boolean isAdmisible(Integer contenedor, Integer elemento) {
		return elementos.get(elemento).posibilidades().contains(contenedores.get(contenedor).tipo()) || contenedores.get(contenedor).tipo()=="MASTER";
	}
	
	public static Integer getNumContenedores() {
		return contenedores.size();
	}
	public static Integer getNumElementos() {
		return elementos.size();
	}
	
	public static Integer getOcupado(List<Integer> asignacion, Integer contenedor) {
		Integer sum = 0;
		for(int i = 0; i < asignacion.size(); i++) {
			if(asignacion.get(i)==contenedor) {
				sum += pesoElemento(i);
			}
		}
		return contenedores.get(contenedor).capacidad()-sum;
	}
	
	
	
	
	
	public record SolucionEjercicio4B(Map<String,List<Elemento>> map) {
		public static SolucionEjercicio4B of(List<Integer> value) {
			Map<String, List<Elemento>> map = new HashMap<>();
			
			for(int i = 0; i < value.size(); i ++) {
				String key = "CONT"+(value.get(i)+1);
				if(map.containsKey(key)) {
					
					map.get(key).add(DatosEj4Alumnos.getElemento(i));
				} else {
					List<Elemento> ls = new ArrayList<>();
					ls.add(DatosEj4Alumnos.getElemento(i));
					map.put(key, ls);
				}
			}
			return new SolucionEjercicio4B(map);
		}
		
		@Override
		public String toString() {
			String s = "";
			for(String key: map.keySet()) {
				s += key + ": " + map.get(key).stream().map(e -> e.nombre()).toList().toString() + "\n";
			}
			
			return s;
		}
	}
	

}
