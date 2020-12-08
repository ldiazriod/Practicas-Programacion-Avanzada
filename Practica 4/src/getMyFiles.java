package Practica_4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Vector;

public class getMyFiles implements IBusqueda{
	
	private String nomHotel;
	public getMyFiles(String nomHotel) {
		this.nomHotel = nomHotel;
	}
	
	public getMyFiles(){};

	@Override
	public String getFiles(String nomHotel) {
		File misUsuarios = new File("C:\\eHotel\\misUsuarios");
		Vector<String> usuarios = new Vector<String>(0, 1);
		String aux = "";
		File myAux = null;
		Vector<String> cadaUsuario = new Vector<String>(0, 1);
		if(misUsuarios.isDirectory()){
			File[] filesContent = misUsuarios.listFiles();
			for(int i = 0; i < filesContent.length; i++) {
				if(filesContent[i].isDirectory()) {
					usuarios.addElement(filesContent[i].toString());
				}
			}
		}
		
		for(int i = 0; i < usuarios.size(); i++){
			aux = usuarios.elementAt(i);
			myAux = new File(aux);
			if(myAux.isDirectory()) {
				File[] filesContent = myAux.listFiles();
				for(int j = 0; j < filesContent.length; j++) {
					if(filesContent[j].isDirectory()){
						cadaUsuario.addElement(filesContent[j].toString());
					}
				}
			}
		}
		
		String urlHotel = "";
		
		for(int j = 0; j < cadaUsuario.size(); j++) {
			aux = cadaUsuario.elementAt(j);
			myAux = new File(aux);
			if(myAux.isDirectory()) {
				File[] filesContent = myAux.listFiles();
				for(int k = 0; k < filesContent.length; k++) {
					if(filesContent[k].isFile()) {
						if(filesContent[k].getName().contentEquals(nomHotel + ".txt")) {
							urlHotel = filesContent[k].toString();
						}
					}
				}
			}
		}
		return urlHotel;
	}

	@Override
	public Vector<String> getFiles2(String path) {
		File myFiles = new File(path);
		Vector<String> myVec = new Vector(0, 1);
		if(myFiles.isDirectory()) {
			File[] filesContent = myFiles.listFiles();
			for(int i = 0; i < filesContent.length; i++) {
				if(filesContent[i].isFile()) {
					myVec.addElement(filesContent[i].toString());
				}
			}
		}else {
			System.out.println("No es un path valido");
		}
		return myVec;
	}

	@Override
	public Vector<String> guardarInfoDatostxt(String path) {
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;
		Vector<String> aux = new Vector<String>(0, 1);
		try {
			archivo = new File(path);
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);
			String linea;
			while((linea = br.readLine()) != null){
				aux.addElement(linea);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(null != fr) {
					fr.close();
				}
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
		return aux;
	}

	@Override
	public boolean borrarFicheros(File directorio) {
		File[] ficheros = directorio.listFiles();
		for(int i = 0; i < ficheros.length; i++) {
			if(ficheros[i].isDirectory()) {
				borrarFicheros(ficheros[i]);
			}
			ficheros[i].delete();
		}
		if(directorio.delete()) {
			return true;
		}else {
			return false;
		}
	}
}
