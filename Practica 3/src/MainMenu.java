package Practica_3;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class MainMenu {

	public static void main(String[] args) {          //Creamos todos los ficheros
		File existeCarpetaPrograma = new File("C:\\eHotel");
		if(!existeCarpetaPrograma.exists()){
			if(existeCarpetaPrograma.mkdir()) {
				File existeCarpetaMisUsuarios = new File("C:\\eHotel\\misUsuarios");
				File existeFicheroMisHoteles = new File("C:\\eHotel\\misHoteles.txt");
				if(!existeCarpetaMisUsuarios.exists()) {
					existeCarpetaMisUsuarios.mkdir();
				}
				try {
					if(existeFicheroMisHoteles.createNewFile()){
						menuInicioDeSesion();
					}
				}catch(IOException ioe){
					ioe.printStackTrace();
				}
			}
		}else {
			menuInicioDeSesion();
		}
	}
	
	public static void menuNuevoUsuario() { //Meno de creación de Usuario
		Scanner scn = new Scanner(System.in);
		Usuario p = new Usuario();
		System.out.print("Meta su nombre: ");
		String nomUsuario = scn.nextLine();
		System.out.print("Meta su primer apellido: ");
		String apeUsuario = scn.nextLine();
		System.out.print("Meta su DNI: ");
		String dniUsuario = scn.nextLine();
		System.out.print("Meta su tipoDeLicencia (Hotelero/Cliente): ");
		String tipoDeLicencia = scn.nextLine();
		System.out.print("Meta su contraseña: ");
		String contrasenaUsuario = scn.nextLine();
		
		apeUsuario = apeUsuario.replaceAll(" ", "");
		
		Usuario newUsuario = new Usuario(nomUsuario, apeUsuario, dniUsuario, tipoDeLicencia, contrasenaUsuario);
		p.crearNuevoUsuario(newUsuario);
		menuInicioDeSesion(newUsuario);
	}
	

	public static void menuInicioDeSesion() { //Metodo inicio de sesión
		Usuario u = new Usuario();
		Scanner scn = new Scanner(System.in);     //Le preguntamos que quiere hacer
		System.out.println("¿Que quieres hacer?");
		System.out.println("1. Iniciar Sesion");
		System.out.println("2. Crear cuenta");
		
		int decision = scn.nextInt();
		
		switch(decision) { //Si decide iniciar sesión le pedimos los datos de acceso 
		case 1:
			System.out.println("Meta su nombre: ");
			scn.nextLine();
			String nomUsuario = scn.next();
			scn.nextLine();
			System.out.println("Meta su apellido: ");
			String apeUsuario = scn.nextLine();
			scn.nextLine();
			System.out.println("Meta su contraseña: ");
			String conUsuario = scn.nextLine();
			scn.nextLine();
			
			apeUsuario = apeUsuario.replaceAll(" ", "");
			System.out.println(nomUsuario + apeUsuario + conUsuario);
			
			if(u.iniciarSesion(nomUsuario, apeUsuario, conUsuario) == true && u.getTipoDeLicencia(nomUsuario, apeUsuario).contentEquals("Hotelero")) { //Comprobamos si el usuario existe o es correcto y comprobamos su licencia
				menuInicioHotelero(nomUsuario, apeUsuario); 																							//Si es hotelero le llevamos al menú para hoteleros
			}else if(u.iniciarSesion(nomUsuario, apeUsuario, conUsuario) == true && u.getTipoDeLicencia(nomUsuario, apeUsuario).contentEquals("Cliente")){
				menuInicioCliente(nomUsuario, apeUsuario);																								//Si es cliente le mandamos al menú para clientes
			}else{
				System.out.println("Los datos no son correctos o no existe la cuenta");
				menuInicioDeSesion();			//Si los datos son incorrectos, no existe la cuenta o la licencia no es valida le mandamos de nuevo al principio
			}
			break;
		case 2:
			menuNuevoUsuario();					//Si quiere creear una cuenta le mandamos al menu de nuevi usuario
			break;
		default:
			System.out.println("No es una opcion disponible");
			menuInicioDeSesion();
			break;
		}
	}
	
	public static void menuInicioDeSesion(Usuario p) {  //metodo sobrecargado menuInicioDeSesion, para mandar al nuevo usuario directamente al menu principal
		boolean auxTernario = (p.getTipoDeLicencia().contentEquals("Hotelero"))?true:false;
		if(auxTernario) {
			menuInicioHotelero(p.getNombreUsuario(), p.getApellidoUsuario());
		}else {
			menuInicioHotelero(p.getNombreUsuario(),p.getApellidoUsuario());
		}
	}

	public static void menuInicioHotelero(String nomUsuario, String apeUsuario) { //Menú principal de hotelero
		Scanner scn = new Scanner(System.in);
		int decision = 0;

		System.out.println("¿Que quieres hacer?: ");
		System.out.println("1. Meter nuevo hotel");
		System.out.println("2. Eliminar un hotel");
		System.out.println("3. Ver mis hoteles");
		System.out.println("4. Meter una habitación a un hotel");

		decision = scn.nextInt();

		switch (decision) {
		case 1:
			menuNuevoHotel(nomUsuario, apeUsuario);
			break;
		case 2:
			menuEliminarHotel(nomUsuario, apeUsuario);
			break;
		case 3:
			Hotel p = new Hotel();
			p.printTodosLosHoteles(nomUsuario, apeUsuario);
			menuInicioHotelero(nomUsuario, apeUsuario);
			break;
		case 4: 
			menuNuevaHabitacion(nomUsuario, apeUsuario);
			break;
		default:
			System.out.println("No es una opcion disponible");
			menuInicioHotelero(nomUsuario, apeUsuario);
			break;
		}
	}
	
	
	public static void menuNuevaHabitacion(String nomUsuario, String apeUsuario){
		Scanner scn = new Scanner(System.in);
		Hotel p = new Hotel();
		Habitacion h = new Habitacion();
		
		String codReserva = "none";
		System.out.println("Estos son tus hoteles: ");
		p.printTodosLosHoteles(nomUsuario, apeUsuario);
		
		System.out.println("Meta el nombre del hotel al que quiere añadir una habitacion: ");
		String nomHotel = scn.next();
		System.out.println("Meta el numero o nombre de la habitacion: ");
		String num_nom_Habitacion = scn.next();
		System.out.println("Meta el tipo de habitacion (Suit, individual, doble,...)");
		String tipoHabitacion = scn.next();
		System.out.println("Meta el numero de camas: ");
		int numCamas = scn.nextInt();
		System.out.println("Meta el precio/noche de la habitacion: ");
		double precio_noche = scn.nextDouble();
		
		Habitacion newHabitacion = new Habitacion(num_nom_Habitacion, tipoHabitacion, numCamas, precio_noche, codReserva);
		h.newHabitacion(newHabitacion, nomHotel, nomUsuario, apeUsuario);
		menuInicioHotelero(nomUsuario, apeUsuario);
	}
	
	
	public static void menuEliminarHotel(String nomUsuario, String apeUsuario){
		Scanner scn = new Scanner(System.in);
		Hotel p = new Hotel();
		int decision = 0;
		System.out.println("¿Estás seguro de que quieres borrar el hotel?: ");
		System.out.println("1. No");
		System.out.println("2. Si");
		
		
		decision = scn.nextInt();
		switch(decision) {
		case 1:
			menuInicioHotelero(nomUsuario, apeUsuario);
			break;
		case 2:
			if(p.eliminarHotel(nomUsuario, apeUsuario)) {
				System.out.println("El hotel ha sido eliminado correctamente");
				menuInicioHotelero(nomUsuario, apeUsuario);
			}else {
				System.out.println("El hotel no ha se borrado correctamente");
				menuInicioHotelero(nomUsuario, apeUsuario);
			}
			break;
		default:
			System.out.println("No es una opción disponible");
			menuEliminarHotel(nomUsuario, apeUsuario);
			break;
		}
	}
	
	public static void menuInicioCliente(String nomUsuario, String apeUsuario) { //Menú principal de cliente
		Scanner scn = new Scanner(System.in);
		HotelUsuario hu = new HotelUsuario();
		int decision = 0;
		System.out.println("Que quieres hacer?: ");
		System.out.println("1. Reservar un hotel");
		
		decision = scn.nextInt();
		
		switch(decision) {
		case 1:
			hu.reservarHotel(nomUsuario, apeUsuario);
			menuInicioCliente(nomUsuario, apeUsuario);
			break;
		default:
			System.out.println("No es una opcion disponible");
			menuInicioCliente(nomUsuario, apeUsuario);
			break;
		}
	}

	public static void menuNuevoHotel(String nomUsuario, String apeUsuario) {	//Menú crear nuevo hotel
		Scanner scn = new Scanner(System.in);
		System.out.println("¿Estas seguro de que quieres añadir un nuevo hotel?: ");
		System.out.println("1. No");
		System.out.println("2. Sí");
		
		int decision = scn.nextInt();
		switch (decision) {
		case 1:
			menuInicioHotelero(nomUsuario, apeUsuario);
			break;
			
		case 2:
			 Hotel p = new Hotel();
			 p.nuevoHotel(nomUsuario, apeUsuario);
			 menuInicioHotelero(nomUsuario, apeUsuario);
			 break;
		default:
			System.out.println("No es una opcion disponible");
			menuNuevoHotel(nomUsuario, apeUsuario);
			break;
		}
	}
}