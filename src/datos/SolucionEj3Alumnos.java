package datos;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import datos.DatosEj3Alumnos.Producto;

public record SolucionEj3Alumnos(Map<Producto, Integer> map) {
	
	public static SolucionEj3Alumnos parse(Map<String, Double> solucion) {
		Map<Producto, Integer> mapAux = new HashMap<Producto, Integer>();
		for(String s: solucion.keySet()) {
			if(solucion.get(s)>0 && s.contains("x")) {
				mapAux.put(DatosEj3Alumnos.getProducto(Integer.valueOf(s.split("_")[1])), solucion.get(s).intValue());
			}
		}
		return new  SolucionEj3Alumnos(mapAux);
	}
	
	public static SolucionEj3Alumnos of(List<Integer> ls) {
		Map<Producto, Integer> solucion = new HashMap<Producto, Integer>();
		for(int i = 0; i < ls.size(); i++) {
			if(ls.get(i) != 0) {
				solucion.put(DatosEj3Alumnos.getProducto(i), ls.get(i));
			}
		}
		return new SolucionEj3Alumnos(solucion);
	}
	
	public Integer beneficio() {
		Integer beneficio = 0;
	
		for(Producto p: map.keySet()) {
			beneficio += p.precio() * map.get(p);
			
		} 
		return beneficio;
	}
	
	@Override
	public String toString() {
		String s = "";
		for(Producto p: map.keySet()) {
			s += String.format("\n%s: %d unidades", p.nombre(), map.get(p));
		}
		Integer beneficio = 0;
		Integer tiempoProd = 0;
		Integer tiempoMan = 0;
		for(Producto p: map.keySet()) {
			beneficio += p.precio() * map.get(p);
			tiempoMan += DatosEj3Alumnos.getTiempoManualTotal(p) * map.get(p);
			tiempoProd += DatosEj3Alumnos.getTiempoProducTotal(p) * map.get(p);
			
		}
		s += "\nProd: " + DatosEj3Alumnos.getProductionTime() + " Manual: " + DatosEj3Alumnos.getManualTime();
		s += "\nBeneficio: " + beneficio;
		s += "\nT. produccion total: " + tiempoProd;
		s += "\nT. manual total: " + tiempoMan;
		return s;
	}
}
