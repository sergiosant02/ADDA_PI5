package datos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import datos.DatosEj1Alumnos.Fichero;

public record SolucionEj1Alumnos(Map<String, List<Fichero>> solucion) {
	
	public static SolucionEj1Alumnos of(List<Integer> ls) {
		Map<String, List<Fichero>> map = new HashMap<String, List<Fichero>>();
		System.out.println(ls);
		
		for(int i = 0; i < ls.size(); i++) {
			String nombreMemo = DatosEj1Alumnos.getMemoria(ls.get(i)).nombre();
			if(map.containsKey(nombreMemo)){
				map.get(nombreMemo).add(DatosEj1Alumnos.getFichero(i));
			} else {
				map.put(nombreMemo, new ArrayList<Fichero>());
				map.get(nombreMemo).add(DatosEj1Alumnos.getFichero(i));
			}
		}
		System.out.println(map.keySet());
		return new SolucionEj1Alumnos(map);
	}
	
	public String toString() {
		String s = "";
		for(String key: solucion.keySet()) {
			s += (key) +"-> capaciadad: "+ DatosEj1Alumnos.getMemorias().stream().filter(m -> m.nombre() == key).limit(1).toList().get(0).capacidad() + ", transf max: " + DatosEj1Alumnos.getMemorias().stream().filter(m -> m.nombre() == key).limit(1).toList().get(0).tamMax() + ", nº de ficheros: " + solucion.get(key).size() + ", espacio ocupado: " + solucion.get(key).stream().map(f -> f.tam()).reduce((acum, num) -> acum+ num).orElse(0)+ "\n";
			List<Fichero> files = solucion.get(key);
			for(Fichero f: files) {
				s += ("\t\t"+f+"\n");
			}
		}
		return s;
	}
}
