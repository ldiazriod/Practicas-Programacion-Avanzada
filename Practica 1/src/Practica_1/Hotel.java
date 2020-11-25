package Practica_1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Vector;

public class Hotel {

	private String nombreHotel;
	private String direccionHotel;
	private int numEstrellas;
	private int numHabitaciones;

	public Hotel() {
	}; // Construcutor sobrecargado

	public Hotel(String nombreHotel, String direccionHotel, int numHabitaciones, int numEstrellas) {
		this.nombreHotel = nombreHotel;
		this.direccionHotel = direccionHotel;
		this.numEstrellas = numEstrellas;
		this.numHabitaciones = numHabitaciones;
	}

	public void printHotel(Hotel p) { // Función print
		System.out.println("Nombre del hotel: " + p.getNombreHotel());
		System.out.println("Dirección del hotel: " + p.getDireccionHotel());
		System.out.println("Número de habitaciones: " + p.getNumHabitaciones());
		System.out.println("Número de estrellas: " + p.getNumEstrellas());
	}

	public void printTodosLosHoteles(String nomUsuario, String apeUsuario) {
		Scanner scn = new Scanner(System.in);
		String carpeta = nomUsuario + apeUsuario + "\\hotelesUsuario";
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;
		
		Vector<String> myFiles = getFiles("C:\\eHotel\\misUsuarios\\" + carpeta);
		
		if(myFiles != null) {
			for(int i = 0; i < myFiles.size(); i++) {
				try {
					archivo = new File( myFiles.elementAt(i));
					fr = new FileReader(archivo);
					br = new BufferedReader(fr);
					
					String linea;
					while((linea = br.readLine()) != null) {
						System.out.println(linea);
					}
					System.out.println("---------------------");
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
		}
	}
	
	public Vector<String> getFiles(String path) {
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

	public boolean eliminarHotel(String nomUsuario, String apeUsuario) {
		Scanner scn = new Scanner(System.in);
		String carpeta = nomUsuario + apeUsuario;
		printTodosLosHoteles(nomUsuario, apeUsuario);
		System.out.print("Mete el nombre del hotel que quieres eliminar: ");
		String hotelAEliminar = scn.nextLine();
		File ruta = new File("C:\\eHotel\\misUsuarios\\" + carpeta + "\\hotelesUsuario\\" + hotelAEliminar + ".txt");
		if (!ruta.exists()) {
			System.out.println("Ese hotel no existe o no te pertenece");
			return false;
		} else {
			System.out.println("Confirme que quiere borrar el hotel metiendo: " + hotelAEliminar);
			String estarSeguro = scn.nextLine();
			if (estarSeguro.contentEquals(hotelAEliminar)) {
				if (ruta.delete()) {
					eliminaHotelDeFichero(hotelAEliminar);
					return true;
				} else {
					return false;
				}
			} else {
				return false;
			}
		}
	}
	
	public void eliminaHotelDeFichero(String hotelAEliminar) {
		Vector<String> misHoteles = new Vector(1, 1);
		File archivo = null;
		FileReader fr = null;
		BufferedReader br = null;
		try {
			archivo = new File("C:\\eHotel\\misHoteles.txt");
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);
			
			String linea;
			while((linea = br.readLine()) != null) {
				misHoteles.addElement(linea);
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
			fichero = new FileWriter("C:\\eHotel\\misHoteles.txt");
			pw = new PrintWriter(fichero);
			
			for(int i = 0; i < misHoteles.size(); i++) {
				if(misHoteles.elementAt(i).contentEquals(hotelAEliminar)){
					misHoteles.removeElementAt(i+1);
				}else {
					pw.println(misHoteles.elementAt(i));
				}
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
	}

	public void nuevoHotel(String nomUsuario, String apeUsuario) { // Añadir nuevo hotel
		String nombreHotel;
		String direccionHotel;
		int numHabitaciones;
		int numEstrellas;
		Scanner scn = new Scanner(System.in);
		Hotel p = new Hotel();

		System.out.print("Añada el nombre del hotel: ");
		nombreHotel = scn.nextLine();
		System.out.print("Añada la dirección del hotel: ");
		direccionHotel = scn.nextLine();
		System.out.print("Añada el número de habitaciones: ");
		numHabitaciones = scn.nextInt();
		System.out.print("Añada el número de estrellas: ");
		numEstrellas = scn.nextInt();

		Hotel newHotel = new Hotel(nombreHotel, direccionHotel, numHabitaciones, numEstrellas); // Comprobar si los
																								// datos son correctos
		System.out.println("Estos son los datos del nuevo hotel: ");
		p.printHotel(newHotel);
		System.out.println("¿Estás seguro de que estos son los datos correctos?: ");
		System.out.println("1. No");
		System.out.println("2. Si");

		int decision = scn.nextInt();

		switch (decision) {
		case 1: // Si no son correctos preguntamos cual es el dato incorrecto y lo
			datosHotelNoCorrectos(newHotel, nomUsuario, apeUsuario);
			break;
		case 2:
			anadirEnFichero(newHotel, nomUsuario, apeUsuario); // Si son correctos, los guardamos en un fichero
			break;
		default:
			System.out.println("No es una opcion valida");
			nuevoHotel(nomUsuario, apeUsuario);
			break;
		}
		
	}

	public void datosHotelNoCorrectos(Hotel p, String nomUsuario, String apeUsuario) { // Funcion para cambiar los datos
																						// de un hotel nuevo(Por si el
																						// usuario se equivoca)
		Scanner scn = new Scanner(System.in);
		String cambioNomYDir;
		int cambioHabitaYEstre;
		System.out.println("¿Cuales es el dato que quieres cambiar?: ");
		System.out.println("1. Nombre del hotel");
		System.out.println("2. Dirección del hotel");
		System.out.println("3. Numero de habitaciones del hotel");
		System.out.println("4. Numero de estrellas del hotel");

		int decision = scn.nextInt();

		switch (decision) {
		case 1:
			System.out.println("Meta el nombre correcto del hotel: ");
			cambioNomYDir = scn.next();
			p.setNombreHotel(cambioNomYDir);
			break;
		case 2:
			System.out.println("Meta la dirección correcta del hotel: ");
			cambioNomYDir = scn.next();
			p.setDireccionHotel(cambioNomYDir);
			break;
		case 3:
			System.out.println("Meta el numero de habitaciones correcto: ");
			cambioHabitaYEstre = scn.nextInt();
			p.setNumHabitaciones(cambioHabitaYEstre);
			break;
		case 4:
			System.out.println("Meta el numero de estrellas correcto: ");
			cambioHabitaYEstre = scn.nextInt();
			p.setNumEstrellas(cambioHabitaYEstre);
			break;
		default:
			System.out.println("No es una decision valida");
			datosHotelNoCorrectos(p, nomUsuario, apeUsuario);
			break;
		}

		System.out.println("Estos son los nuevos datos de tu hotel: ");
		p.printHotel(p);
		System.out.println("¿Son definitivamente correctos?: ");
		System.out.println("1. No");
		System.out.println("2. Si");

		int decision2 = scn.nextInt();

		switch (decision2) {
		case 1:
			datosHotelNoCorrectos(p, nomUsuario, apeUsuario);
			break;
		case 2:
			anadirEnFichero(p, nomUsuario, apeUsuario);
			break;
		default:
			System.out.println("No es una decision valida");
			datosHotelNoCorrectos(p, nomUsuario, apeUsuario);
			break;
		}
	}

	public void anadirEnFichero(Hotel p, String nomUsuario, String apeUsuario) { 
		FileWriter ficheroEscritura = null;
		File ficheroLectura = new File("C:\\eHotel\\misHoteles.txt") ;
		FileReader fr = null;
		PrintWriter pw = null;
		BufferedReader br = null;
		boolean repetido = false;
		if(ficheroLectura.length() == 0) {
			try {
				ficheroEscritura = new FileWriter("C:\\eHotel\\misHoteles.txt", true);
				pw = new PrintWriter(ficheroEscritura);
				pw.println(p.getNombreHotel());
				pw.println("---------------------");
				anadirNuevoFicheroHotel(p, nomUsuario, apeUsuario);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (null != ficheroEscritura) {
						ficheroEscritura.close();
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}else {
			try {
				String linea;
				ficheroLectura = new File("C:\\eHotel\\misHoteles.txt");
				fr = new FileReader(ficheroLectura);
				br = new BufferedReader(fr);
				while((linea = br.readLine()) != null) {
					if(linea.contentEquals(p.getNombreHotel())) {
						repetido = true;
					}
				}
				if(repetido) {
					System.out.println("Ese hotel ya existe");
				}else {
					try {
						ficheroEscritura = new FileWriter("C:\\eHotel\\misHoteles.txt", true);
						pw = new PrintWriter(ficheroEscritura);
						pw.println(p.getNombreHotel());
						pw.println("---------------------");
						anadirNuevoFicheroHotel(p, nomUsuario, apeUsuario);
					}catch(Exception e2) {
						System.out.println("No se ha podido meter el hotel correctamente");
					}finally {
						try {
							if(null != ficheroEscritura){
								ficheroEscritura.close();
							}
						}catch(Exception e3) {
							System.out.println("No se ha podido cerrar el fichero correctamente");
						}
					}
				}
			}catch(Exception e) {
				System.out.println("No se ha podido meter el fichero correctamente");
			}finally {
				try {
					if(null != fr) {
						fr.close();
					}
				}catch(Exception e2) {
					System.out.println("No se ha podido guardar el fichero correctamente");
				}
			}
		}
	}

	public void anadirNuevoFicheroHotel(Hotel p, String nomUsuario, String apeUsuario) { 
		FileWriter fichero = null; // Cambiar
		PrintWriter pw = null;
		String carpeta = nomUsuario + apeUsuario;
		String ruta = "C:\\eHotel\\misUsuarios\\" + carpeta + "\\hotelesUsuario\\" + p.getNombreHotel() + ".txt";
		try {
			fichero = new FileWriter(ruta);
			pw = new PrintWriter(fichero);
			pw.println("Nombre del hotel: " + p.getNombreHotel());
			pw.println("Direccion del hotel: " + p.getDireccionHotel());
			pw.println("Numero habitaciones del hotel: " + p.getNumHabitaciones());
			pw.println("Numero de estrellas del hotel: " + p.getNumEstrellas());
			pw.println("////Habitaciones\\\\");

		} catch (Exception e) {
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
	}

	public String getNombreHotel() {
		return nombreHotel;
	}

	public void setNombreHotel(String nombreHotel) {
		this.nombreHotel = nombreHotel;
	}

	public String getDireccionHotel() {
		return direccionHotel;
	}

	public void setDireccionHotel(String direccionHotel) {
		this.direccionHotel = direccionHotel;
	}

	public int getNumEstrellas() {
		return numEstrellas;
	}

	public void setNumEstrellas(int numEstrellas) {
		this.numEstrellas = numEstrellas;
	}

	public int getNumHabitaciones() {
		return numHabitaciones;
	}

	public void setNumHabitaciones(int numHabitaciones) {
		this.numHabitaciones = numHabitaciones;
	}

}
