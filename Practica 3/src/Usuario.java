package Practica_3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class Usuario {
	//Clase para diferenciar entre dueños de hoteles, franquicias de hoteles y usuarios que quieran reservar en el hotel
	private String nombreUsuario;
	private String apellidoUsuario;
	private String dniUsuario;
	private String tipoDeLicencia; //Hotelero || Cliente
	private String contrasenaUsuario;
	
	public Usuario(){};
	
	public Usuario(String nombreUsuario, String apellidoUsuario, String dniUsuario, String tipoDeLicencia, String contrasenaUsuario) {
		this.nombreUsuario = nombreUsuario;
		this.apellidoUsuario = apellidoUsuario;
		this.dniUsuario = dniUsuario;
		this.tipoDeLicencia = tipoDeLicencia;
		this.contrasenaUsuario = contrasenaUsuario;
	}
	
	public Usuario(String nombreUsuario, String apellidoUsuario, String contrasenaUsuario){
		this.nombreUsuario = nombreUsuario;
		this.apellidoUsuario = apellidoUsuario;
		this.contrasenaUsuario = contrasenaUsuario;
	}
	
	public void crearNuevoUsuario(Usuario p){
		String carpeta = p.getNombreUsuario() +  p.getApellidoUsuario();
		File nuevoDirectorio = new File("C:\\eHotel\\misUsuarios\\" + carpeta);
		if(!nuevoDirectorio.exists()) {
			if(nuevoDirectorio.mkdir()) {
				File nuevoMisHoteles = new File("C:\\eHotel\\misUsuarios\\" + carpeta + "\\hotelesUsuario");
				nuevoMisHoteles.mkdir();
				FileWriter fichero = null;
				PrintWriter pw = null;
				String ruta = "C:\\eHotel\\misUsuarios\\" + carpeta + "\\datos.txt" ;
				try {
					fichero = new FileWriter(ruta);
					pw = new PrintWriter(fichero);
					
					pw.println(p.getNombreUsuario());
					pw.println(p.getApellidoUsuario());
					pw.println(p.getDniUsuario());
					pw.println(p.getContrasenaUsuario());
					pw.println(p.getTipoDeLicencia());
				}catch(Exception e) {
					e.printStackTrace();
				}finally {
					try {
						if(null != fichero) {
							fichero.close();
						}
					}catch (Exception e2) {
						e2.printStackTrace();
					}
				}
			}else {
				System.out.println("No se ha podido crear el fichero");
			}
		}	
	}
	
	public boolean iniciarSesion(String nomUsuario, String apeUsuario, String conUsuario) {
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;
		String [] guardarDatos = new String[5];
		int counter = 0;
		String carpeta = nomUsuario + apeUsuario;
		String ruta = "C:\\eHotel\\misUsuarios\\" + carpeta + "\\datos.txt"; 
		
		try {
			archivo = new File(ruta);
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);
			String linea;
			while((linea = br.readLine()) != null) {
				guardarDatos[counter] = linea;
				counter++;
			}
		}catch (Exception e){
			return false;
		}finally {
			try {
				if(null != fr) {
					fr.close();
				}
			}catch (Exception e2) {
				return false;
			}
		}
		counter = 0;
		for(int i = 0; i < guardarDatos.length; i++) {
			if(guardarDatos[i].contentEquals(nomUsuario)){
				counter++;
			}else if(guardarDatos[i].contentEquals(apeUsuario)){
				counter++;
			}else if(guardarDatos[i].contentEquals(conUsuario)) {
				counter++;
			}
		}
		if(counter == 3) {
			return true;
		}else {
			return false;
		}
	}
	
	
	public String getTipoDeLicencia(String nomUsuario, String apeUsuario) {
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;
		String licencia = "";
		String carpeta = nomUsuario +  apeUsuario;
		String ruta = "C:\\eHotel\\misUsuarios\\" + carpeta + "\\datos.txt"; 
		
		try {
			archivo = new File(ruta);
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);
			String linea;
			while((linea = br.readLine()) != null) {
				if(linea.contentEquals("Hotelero")) {
					licencia = linea;
				}else if(linea.contentEquals("Cliente")){
					licencia = linea;
				}
			}
		}catch (Exception e){
			return "error";
		}finally {
			try {
				if(null != fr) {
					fr.close();
				}
			}catch (Exception e2) {
				return "error";
			}
		}
		return licencia;
	}
	
	
	public String getNombreUsuario() {
		return nombreUsuario;
	}

	public String getApellidoUsuario() {
		return apellidoUsuario;
	}

	public String getDniUsuario() {
		return dniUsuario;
	}

	public String getTipoDeLicencia() {
		return tipoDeLicencia;
	}

	public String getContrasenaUsuario() {
		return contrasenaUsuario;
	}

	public void setContrasenaUsuario(String contrasenaUsuario) {
		this.contrasenaUsuario = contrasenaUsuario;
	}


}