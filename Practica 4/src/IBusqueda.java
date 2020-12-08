package Practica_4;

import java.io.File;
import java.util.Vector;

public interface IBusqueda {
	String getFiles(String nomHotel);
	Vector<String> getFiles2(String path);
	Vector<String> guardarInfoDatostxt(String path);
	boolean borrarFicheros(File directorio);
}
