package datos;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import us.lsi.common.Files2;
import us.lsi.common.Map2;

public class DatosEj3Alumnos {
	
	private static List<Producto> productos;
	private static List<Componente> componenetes;
	private static Integer productionTime;
	private static Integer manualTime;
	

	public record Componente(String nombre, Integer prod, Integer elab) {
		public static Componente parse(String s) {
			String[] partes = s.trim().split(";");
			String nombre = partes[0].split(":")[0];
			Integer prod = Integer.valueOf(partes[0].split("=")[1]);
			Integer elab = Integer.valueOf(partes[1].split("=")[1]);
			return new Componente(nombre, prod, elab);
		}
	}
	
	public record Producto(String nombre, Integer precio, Map<String, Integer> comp, Integer maxUnidades) {
		public static Producto parse(String s) {
			String[] partes = s.replaceAll(" ", "").split("->");
			String nombre = partes[0];
			String[] datos = partes[1].split(";");
			Integer precio = Integer.valueOf(datos[0].split("=")[1]);
			Integer maxUnidades = Integer.valueOf(datos[2].split("=")[1]);
			Map<String, Integer> comp = Map2.empty();
			String[] info = datos[1].split("=")[1].split(",");
			for(String txt: info) {
				txt = txt.replace("(", "").replace(")", "");
				String namePiece = txt.split(":")[0];
				Integer countPiece = Integer.valueOf(txt.split(":")[1]);
				comp.put(namePiece, countPiece);
			}
			return new Producto(nombre, precio, comp, maxUnidades);
		}
	}
	
	public static void leerDatos(String filePath) {
		List<String> lineas = Files2.linesFromFile(filePath);
		productionTime = Integer.valueOf(lineas.get(0).split("=")[1].trim());
		manualTime = Integer.valueOf(lineas.get(1).trim().split("=")[1].trim());
		componenetes = new ArrayList<Componente>();
		productos = new ArrayList<Producto>();
		Boolean componenteRead = false;
		Boolean productRead = false;
		for(int i = 2; i < lineas.size(); i++) {
			if(lineas.get(i).contains("COMPONENTES")) {
				componenteRead = true;
				productRead = false;
			} else if(lineas.get(i).contains("PRODUCTOS")) {
				componenteRead = false;
				productRead = true;
			} else if(componenteRead && !productRead) {
				componenetes.add(Componente.parse(lineas.get(i)));
			} else if (!componenteRead && productRead) {
				productos.add(Producto.parse(lineas.get(i)));
			}
		}
		
	}
	
	public static List<Producto> getProductos() {
		return productos;
	}

	public static void setProductos(List<Producto> productos) {
		DatosEj3Alumnos.productos = productos;
	}

	public static List<Componente> getComponenetes() {
		return componenetes;
	}

	public static void setComponenetes(List<Componente> componenetes) {
		DatosEj3Alumnos.componenetes = componenetes;
	}
	
	public static Producto getProducto(Integer i) {
		return productos.get(i);
	}
	
	public static Componente getComponente(Integer i) {
		return componenetes.get(i);
	}

	public static Integer getProductionTime() {
		return productionTime;
	}

	public static void setProductionTime(Integer productionTime) {
		DatosEj3Alumnos.productionTime = productionTime;
	}

	public static Integer getManualTime() {
		return manualTime;
	}

	public static void setManualTime(Integer manualTime) {
		DatosEj3Alumnos.manualTime = manualTime;
	}
	
	public static Integer getTiempoProducTotal(Integer i) {
		Integer tiempo = 0;
		Producto producto = productos.get(i);
		for(String key: producto.comp.keySet()) {
			tiempo += producto.comp().get(key) * componenetes.get((Integer.valueOf(key.substring(1))-1)).prod();
		}
		return tiempo;
	}
	
	public static Integer getTiempoManualTotal(Integer i) {
		Integer tiempo = 0;
		Producto producto = productos.get(i);
		for(String key: producto.comp.keySet()) {
			Integer pos = Integer.valueOf(key.substring(1))-1;
			tiempo += producto.comp().get(key) * componenetes.get(pos).elab();
		}
		return tiempo;
	}
	public static Integer getTiempoManualTotal(Producto producto) {
		Integer tiempo = 0;
		for(String key: producto.comp.keySet()) {
			tiempo += producto.comp().get(key) * componenetes.get((Integer.valueOf(key.substring(1))-1)).elab();
		}
		return tiempo;
	}
	public static Integer getTiempoProducTotal(Producto producto) {
		Integer tiempo = 0;
		for(String key: producto.comp.keySet()) {
			tiempo += producto.comp().get(key) * componenetes.get((Integer.valueOf(key.substring(1))-1)).prod();
		}
		return tiempo;
	}
	
	public static Integer getNumProducts() {
		return productos.size();
	}
	
	public static Integer getMaxCapacidadProduccion() {
		return productos.stream().map(p -> p.maxUnidades()).max(Comparator.comparing(p -> p)).orElse(0);
	}
	
	public static Integer getMaxUnidades(Integer i) {
		return productos.get(i).maxUnidades();
	}
	public static Integer getPrecioProd(Integer i) {
		return productos.get(i).precio();
	}
	

}
