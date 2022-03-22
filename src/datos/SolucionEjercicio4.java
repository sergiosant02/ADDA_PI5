package datos;

import java.util.ArrayList;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

import datos.DatosEj4Alumnos.Elemento;


public record SolucionEjercicio4(SortedMap<String, List<Elemento>> map) {
	public static SolucionEjercicio4 of(List<Integer> value) {
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
	
		return new SolucionEjercicio4(map);
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