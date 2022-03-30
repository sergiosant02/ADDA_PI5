package datos;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import datos.DatosEj4Alumnos.Elemento;

public record SolucionEjercicio4Alumnos(SortedMap<String, List<Elemento>> map) {
	public static SolucionEjercicio4Alumnos parse(Map<String, Double> solucion) {
		SortedMap<String, List<Elemento>> mapSolucion = new TreeMap<>();
		for(String key: solucion.keySet()) {
			if(solucion.get(key) > 0 && key.contains("x")) {
				String[] partes = key.split("_");
				String newKey = String.format("Contenedor %d:", (Integer.valueOf(partes[1]) +1));
				Elemento elemento = DatosEj4Alumnos.getElemento(Integer.valueOf(partes[2]));
				if(mapSolucion.containsKey(newKey)) {
					mapSolucion.get(newKey).add(elemento);
				} else {
					List<Elemento> ls = new ArrayList<Elemento>();
					ls.add(elemento);
					mapSolucion.put(newKey, ls);
				}
			}
		}
		return new SolucionEjercicio4Alumnos(mapSolucion);
	}
	
	public static SolucionEjercicio4Alumnos of(List<Integer> value) {
		SortedMap<String, List<Elemento>> map = new TreeMap<>();
		for(int i = 0; i < value.size(); i++) {
			String name = "CONT"+(value.get(i)+1);
			if(map.containsKey(name)) {
				map.get(name).add(DatosEj4Alumnos.getElemento(i));
			} else {
				List<Elemento> ls = new ArrayList<Elemento>();
				ls.add(DatosEj4Alumnos.getElemento(i));
				map.put(name, ls);
			}
		}
	
		return new SolucionEjercicio4Alumnos(map);
	}
	
	@Override
	public String toString() {
		String s = "";
		for(String key: map.keySet()) {
			if(!key.equals("CONT"+DatosEj4Alumnos.getNumContenedores())) {
				s += key +": " + mostrarListaElementos(map.get(key))+"\n";
			}
			
		}
		return s;
	}
	
	public String mostrarListaElementos(List<Elemento> ls) {
		String s = "";
		for(Elemento e: ls) {
			s += String.format("\n\tnombre: %s, tam: %d, admisible: %s", e.nombre(), e.tam(), e.posibilidades().toString());
		}
		return s;
	}
}
