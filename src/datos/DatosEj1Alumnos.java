package datos;

import java.util.ArrayList;
import java.util.List;

import us.lsi.common.Files2;

public class DatosEj1Alumnos {
	
	private static List<Memoria> memorias;
	private static List<Fichero> ficheros;
	
	public record Memoria(String nombre, Integer capacidad, Integer tamMax) {
		public static Memoria parse(String s) {
			String[] cadena = s.replaceAll(":", "").replaceAll(";", "").split(" ");
			Integer capacidad = Integer.valueOf(cadena[1].split("=")[1]);
			Integer tamMax = Integer.valueOf(cadena[2].split("=")[1]);
			return new Memoria(cadena[0], capacidad, tamMax);
		}
	}
	
	public record Fichero(String nombre, Integer tam) {
		public static Fichero parse(String s) {
			String[] cadena = s.split(": ");
			Integer tam = Integer.valueOf(cadena[1]);
			return new Fichero(cadena[0], tam);
		}
	}
	
	public static void leerDatos(String filePath) {
		memorias = new ArrayList<Memoria>();
		ficheros = new ArrayList<Fichero>();
		List<String> lineas = Files2.linesFromFile(filePath);
		Boolean memoriaF = false;
		Boolean ficheroF = false;
		for(String linea: lineas) {
			if(linea.contains("MEMORIAS")) {
				ficheroF = false;
				memoriaF = true;
			} else if(!linea.contains("FICHEROS") && !linea.contains("ARCHIVOS") && memoriaF && !ficheroF) {
				memorias.add(Memoria.parse(linea));
			} else if(linea.contains("FICHEROS") || linea.contains("ARCHIVOS")) {
				memoriaF = false;
				ficheroF = true;
			} else if(ficheroF){
				ficheros.add(Fichero.parse(linea));
			}
		}
		Integer maxCapaciadAux = memorias.stream().map(m -> m.capacidad()).reduce((acum, num) -> acum +num).orElse(0) * 10000;
		Memoria mAux = new Memoria("MEM"+(memorias.size()+1), maxCapaciadAux, maxCapaciadAux);
		memorias.add(mAux);
	}

	public static List<Memoria> getMemorias() {
		return memorias;
	}

	public static void setMemorias(List<Memoria> memorias) {
		DatosEj1Alumnos.memorias = memorias;
	}

	public static List<Fichero> getFicheros() {
		return ficheros;
	}

	public static void setFicheros(List<Fichero> ficheros) {
		DatosEj1Alumnos.ficheros = ficheros;
	}
	
	public static Integer numMemorias() {
		return memorias.size();
	}
	
	public static Integer numFicheros() {
		return ficheros.size();
	}
	
	public static Memoria getMemoria(Integer i) {
		return memorias.get(i);
	}
	
	public static Fichero getFichero(Integer i) {
		return ficheros.get(i);
	}
	
	public static Integer getNumMemo() {
		return memorias.size();
	}
	
	public static Integer getNumFicheros() {
		return ficheros.size();
	}
	
	public static Integer getTamFichero(Integer i) {
		return ficheros.get(i).tam();
	}
	
	public static Integer getMaxMemoria(Integer memoria) {
		return memorias.get(memoria).capacidad();
	}
	
	public static Integer getMaxTransf(Integer i) {
		return memorias.get(i).tamMax();
	}
	
	public static Integer isInvalid(Integer memoria, Integer fichero) {
		return memorias.get(memoria).tamMax < ficheros.get(fichero).tam() ? 1 : 0;
	}
	
	public static Integer isOverflow(Integer memoria, Integer tamTotal) {
		return (memorias.get(memoria).capacidad - tamTotal) < 0 ? 1 : 0;
	}
	
	public static Integer getErrorSobreMemoria(List<Integer> ls) {
		List<Integer> excesos = new ArrayList<Integer>();
		for(int i = 0; i < memorias.size(); i ++) {
			excesos.add(memorias.get(i).capacidad());
		}
		for(int i = 0; i < ls.size(); i++ ) {
			Integer memoAct = ls.get(i);  //memoria sobre la que estoy actuando
			Integer errorExtra = memorias.get(memoAct).tamMax() <= ficheros.get(i).tam() ? 0 : 10000;
			
			excesos.add(memoAct, excesos.get(memoAct)-ficheros.get(i).tam() - errorExtra); //actualizo el exceso de esa memoria
		}
		return excesos.stream().reduce((acum, num) -> {
			return acum += num;
		}).orElse(0);
									
									
									
	}
	
	public static Integer getErrorSobreMemoria(List<Integer> ls, Integer memoria) {
		Integer sum = 0;
		for(int i= 0; i< ls.size(); i++) {
			if(ls.get(i) == memoria) {
				sum += DatosEj1Alumnos.getFichero(i).tam();
			}
		}
		return sum;
									
									
	}
	

}

