package Practica_4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Vector;
import java.util.Scanner;

//Clase para que el administrador pueda probar cosas en desarrollo.
//Por ahora solo puede borrar todos los archivos
public class Administrador extends Usuario {
	private String constrasenaBorrado;
	
	public Administrador (String nombreUsuario, String apeUsuario, String dniUsuario, String tipoLicencia, String constrasenaUsuario, String constrasenaBorrado) {
		super(nombreUsuario, apeUsuario, dniUsuario, tipoLicencia, constrasenaUsuario);
		this.constrasenaBorrado = constrasenaBorrado;
	}
	
	public Administrador() {}
	
	public String encryptCon(String nomUsuario, String apeUsuario, String contrasena) throws Exception{
		RSA rsa = new RSA();
		
		rsa.genKeyPair(512);
		
		String file_private = "C://eHotel//misUsuarios//" + (nomUsuario + apeUsuario) + "//rsa.pri";
		String file_public = "C://eHotel//misUsuarios//" + (nomUsuario + apeUsuario) + "//rsa.pub";
		
		rsa.saveToDiskPrivateKey(file_private);
		rsa.saveToDiskPublicKey(file_public);
		
		String secure = rsa.Encrypt(contrasena);
		return secure;
	}
	
	public boolean borrarEHotel() {
		String path = "C://eHotel";
		getMyFiles fi = new getMyFiles();
		File f = new File(path);
		
		boolean borrado = fi.borrarFicheros(f);
		if(borrado) {
			return true;
		}else {
			System.out.println("No se ha podido borrar");
			return false;
		}
	}
	
	public boolean comprobarContrasenaBorrado(String nomUsuario, String apeUsuario, String conBorra) throws Exception{
		RSA rsa2 = new RSA();
		getMyFiles fi = new getMyFiles();
		String path = "C://eHotel//misUsuarios//" + (nomUsuario + apeUsuario) + "//datos.txt";
		
		rsa2.openFromDiskPrivateKey("C://eHotel//misUsuarios//" + (nomUsuario + apeUsuario) + "//rsa.pri");
		rsa2.openFromDiskPublicKey("C://eHotel//misUsuarios//" + (nomUsuario + apeUsuario) + "//rsa.pub");
		
		Vector<String> aux = fi.guardarInfoDatostxt(path);
		
		String secure = aux.elementAt(5);
		String decryptCon = rsa2.Decrypt(secure);
		
		if(decryptCon.contentEquals(conBorra)) {
			return true;
		}
		return false;
	}
	
	public void addContrasenaBorrado(String nomUsuario, String apeUsuario) throws Exception{
		String path = "C://eHotel//misUsuarios//" + (nomUsuario + apeUsuario) + "//datos.txt";
		Scanner scn = new Scanner(System.in);
		Administrador ad = new Administrador();
		getMyFiles fi = new getMyFiles();
		System.out.println("Meta la contraseña que desea para el borrado de la aplicacion: ");
		String contrasena = scn.next();
		
		String conCifrada = ad.encryptCon(nomUsuario, apeUsuario, contrasena);
		FileWriter fichero = null;
		PrintWriter pw = null;
		Vector<String> aux = fi.guardarInfoDatostxt(path);
		try {
			fichero = new FileWriter(path);
			pw = new PrintWriter(fichero);
			for(int i = 0; i < aux.size(); i++) {
				pw.println(aux.elementAt(i));
			}
			pw.print(conCifrada);
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(null != fichero) {
					fichero.close();
				}
			}catch(Exception e2) {
				e2.printStackTrace();
			}
		}
	}
	
	public boolean conAdminExiste(String nomUsuario, String apeUsuario) {
		String path = "C://eHotel//misUsuarios//" + (nomUsuario + apeUsuario) + "//datos.txt";
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;
		boolean existeContrasena = false;
		int counter = 0;
		try {
			archivo = new File(path);
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);
			String linea;
			while((linea = br.readLine()) != null){
				counter++;
			}
			
			if(counter == 6) {
				existeContrasena = true;
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
		return existeContrasena;
	}
	
	public String getConstrasenaBorrado() {
		return this.constrasenaBorrado;
	}
	
	
}
