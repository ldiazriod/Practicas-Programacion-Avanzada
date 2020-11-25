package Practica_1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

public class HotelUsuario {
	private String codigoReserva;
	private String url_HotelReservado;
	private String nomHotelReservado;
	private String nomHabitacionReservada;
	
	public HotelUsuario(){}
	
	public HotelUsuario(String codigoReserva, String url_HotelReservado, String nomHotelReservado, String nomHabitacionReservada) {
		this.codigoReserva = codigoReserva;
		this.url_HotelReservado = url_HotelReservado;
		this.nomHotelReservado = nomHotelReservado;
		this.nomHabitacionReservada = nomHabitacionReservada;
	}
	
	public void printMisHotelestext(String nomUsuario, String apeUsuario){
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;
		
		try {
			archivo = new File("C:\\eHotel\\misHoteles.txt");
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);
			
			String linea;
			while((linea = br.readLine()) != null) {
				System.out.println(linea);
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
	}
	
	public void printHotelByUrl(String url){
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;
		
		try {
			archivo = new File(url);
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);
			
			String linea;
			while((linea = br.readLine()) != null) {
				System.out.println(linea);
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
	}
	
	public String getFiles(String nomHotel){
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
	
	public String generarNumReserva(){
		char[] chars = "abcdefghijklmnopqrstuvwxyz123456789".toCharArray();
		StringBuilder sb = new StringBuilder(10);
		Random random = new Random();
		for(int i = 0; i < 10; i++) {
			char c = chars[random.nextInt(chars.length)];
			sb.append(c);
		}
		String result = sb.toString();
		return result;
	}
	
	public boolean buscarEnArchivo(String url, String nomBuscando) {
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;
		boolean existe = false;
		try {
			archivo = new File(url);
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);
			
			String linea;
			while((linea = br.readLine()) != null){
				if(linea.contentEquals(nomBuscando)) {
					existe = true;
				}
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
		return existe;
	}
	
	public void guardarReserva(HotelUsuario hu, String nomUsuario, String apeUsuario) {
		boolean repetido = false;
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;
		Vector<String> infoHotel = new Vector<String>(0, 1);
		try {
			archivo = new File(hu.getUrl_HotelReservado());
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);
			
			String linea;
			while((linea = br.readLine()) != null) {
				infoHotel.addElement(linea);
			}
			archivo.delete();
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
		
		FileWriter fichero = null;
		PrintWriter pw = null;
		try {
			
			fichero = new FileWriter(hu.getUrl_HotelReservado());
			pw = new PrintWriter(fichero);
			
			for(int i = 0; i < infoHotel.size(); i++) {
				if(infoHotel.elementAt(i).contentEquals(hu.getNomHabitacionReservada())) {
					if(infoHotel.elementAt(i+5).contentEquals("none")) {
						infoHotel.setElementAt(hu.getCodigoReserva(), i+5);
						repetido = false;
					}else {
						repetido = true;
					}
				}
				pw.println(infoHotel.elementAt(i));
			}
		}catch(Exception e){
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
		
		if(!repetido){
			fichero = null;
			pw = null;
			
			try {
				fichero = new FileWriter("C:\\eHotel\\misUsuarios\\" + (nomUsuario + apeUsuario) + "\\hotelesUsuario\\" + hu.getCodigoReserva() + ".txt");
				pw = new PrintWriter(fichero);
				pw.println("Codigo reserva: " + hu.codigoReserva);
				pw.println(hu.getCodigoReserva());
				pw.println("URL Hotel Reservado: ");
				pw.println(hu.getUrl_HotelReservado());
				pw.println("Nombre del hotel reservado: " + hu.getNomHotelReservado());
				pw.println("Nombre de la habitacion reservada: ");
				pw.println(hu.getNomHabitacionReservada());
			}catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (null != fichero) {
						fichero.close();
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}	
		}else {
			System.out.println("Esta habitación ya esta reservada");
		}
	}
	
	public void reservarHotel(String nomUsuario, String apeUsuario) {
		Scanner scn = new Scanner(System.in);
		HotelUsuario hu = new HotelUsuario();
		boolean existe = false;
		System.out.println("Estos son los hoteles disponibles: ");
		hu.printMisHotelestext(nomUsuario, apeUsuario);
		
		System.out.println("Meta el nombre del hotel que quieras reservar: ");
		String nomHotelReservar = scn.next();
		
		existe = hu.buscarEnArchivo("C:\\eHotel\\misHoteles.txt", nomHotelReservar);
		
		if(existe){
			String codReserva = generarNumReserva();
			boolean existe2 = false;
			String url = hu.getFiles(nomHotelReservar);
			System.out.println("Este es el hotel: ");
			hu.printHotelByUrl(url);
			
			System.out.println("Meta el nombre o numero de la habitación que quieres reservar: ");
			String nomHabitacionReservar = scn.next();
			
			existe2 = hu.buscarEnArchivo(url, nomHabitacionReservar);
			if(existe2){
				HotelUsuario newHotelUsuario = new HotelUsuario(codReserva, url, nomHotelReservar, nomHabitacionReservar);
				hu.guardarReserva(newHotelUsuario, nomUsuario, apeUsuario);
			}else {
				System.out.println("Esta habitacion no existe");
			}
		}else {
			System.out.println("Esa habitación no existe");
			hu.reservarHotel(nomUsuario, apeUsuario);
		}
	}

	public String getCodigoReserva() {
		return codigoReserva;
	}

	public void setCodigoReserva(String codigoReserva) {
		this.codigoReserva = codigoReserva;
	}

	public String getUrl_HotelReservado() {
		return url_HotelReservado;
	}

	public void setUrl_HotelReservado(String url_HotelReservado) {
		this.url_HotelReservado = url_HotelReservado;
	}
	
	public String getNomHotelReservado() {
		return nomHotelReservado;
	}
	
	public void setNombreHotelReservado(String nomHotelReservado) {
		this.nomHotelReservado = nomHotelReservado;
	}
	
	public String getNomHabitacionReservada() {
		return nomHabitacionReservada;
	}
	
	public void setNomHabitacionReservada(String nomHabitacionReservada) {
		this.nomHabitacionReservada = nomHabitacionReservada;
	}

}