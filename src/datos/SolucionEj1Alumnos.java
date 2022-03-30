package datos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import datos.DatosEj1Alumnos.Fichero;
import us.lsi.common.List2;

public record SolucionEj1Alumnos(Map<String, List<Fichero>> solucion) {
	
	public static SolucionEj1Alumnos of(List<Integer> ls) {
		Map<String, List<Fichero>> map = new HashMap<String, List<Fichero>>();
		
		for(int i = 0; i < ls.size(); i++) {
			if(ls.get(i) != DatosEj1Alumnos.numMemorias()-1) {
				String nombreMemo = DatosEj1Alumnos.getMemoria(ls.get(i)).nombre();
				if(map.containsKey(nombreMemo)){
					map.get(nombreMemo).add(DatosEj1Alumnos.getFichero(i));
				} else {
					map.put(nombreMemo, new ArrayList<Fichero>());
					map.get(nombreMemo).add(DatosEj1Alumnos.getFichero(i));
				}
			}
		}
		return new SolucionEj1Alumnos(map);
	}
	
	public static SolucionEj1Alumnos parse(Map<String, Double> map) {
		Map<String, List<Fichero>> mapAux = new HashMap<String, List<Fichero>>();
		for(String s: map.keySet()) {
			if(map.get(s) > 0) {
				String key = DatosEj1Alumnos.getMemoria(Integer.valueOf(s.split("_")[1])).nombre();
				Fichero file = DatosEj1Alumnos.getFichero(Integer.valueOf(s.split("_")[2]));
				if(mapAux.containsKey(key)) {
					mapAux.get(key).add(file);
				} else {
					List<Fichero> ls = List2.empty();
					ls.add(file);
					mapAux.put(key, ls);
				}
				
			}
		}
		return new SolucionEj1Alumnos(mapAux);
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