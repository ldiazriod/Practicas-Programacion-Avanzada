package Practica_3;

import java.io.FileWriter;
import java.io.PrintWriter;

public class Habitacion {
	private double precio_noche;
	private String tipoHabitacion;
	private int numCamas;
	private String num_nombre_Habitacion;
	private String codReserva;
	
	public Habitacion(){}
	
	public Habitacion(String num_nombre_Habitacion, String tipoHabitacion, int numCamas, double precio_noche, String codReserva) {
		this.num_nombre_Habitacion = num_nombre_Habitacion;
		this.precio_noche = precio_noche;
		this.tipoHabitacion = tipoHabitacion;
		this.numCamas = numCamas;
		this.setCodReserva(codReserva);
	}
	
	public void newHabitacion(Habitacion h, String nomHotel ,String nomUsuario, String apeUsuario){
		String carpeta = "C:\\eHotel\\misUsuarios\\" + (nomUsuario + apeUsuario) + "\\hotelesUsuario\\" + nomHotel + ".txt";
		FileWriter fichero = null;
		PrintWriter pw = null;
		try {
			fichero = new FileWriter(carpeta, true);
			pw = new PrintWriter(fichero);
			pw.println("Nombre/Numero de la habitacion: ");
			pw.println(h.num_nombre_Habitacion);
			pw.println("Tipo de habitación: " + h.tipoHabitacion);
			pw.println("Numero de camas de la habitación: " + h.numCamas);
			pw.println("Precio/Noche: " + h.precio_noche);
			pw.println("Codigo de reserva: ");
			pw.println(h.codReserva);
			pw.println("____________________________________");
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

	public double getPrecio_noche() {
		return precio_noche;
	}

	public void setPrecio_noche(double precio_noche) {
		this.precio_noche = precio_noche;
	}

	public String getTipoHabitacion() {
		return tipoHabitacion;
	}

	public int getNumCamas() {
		return numCamas;
	}

	public void setNumCamas(int numCamas) {
		this.numCamas = numCamas;
	}

	public String getNum_nombre_Habitacion() {
		return num_nombre_Habitacion;
	}

	public void setNum_nombre_Habitacion(String num_nombre_Habitacion) {
		this.num_nombre_Habitacion = num_nombre_Habitacion;
	}

	public String getCodReserva() {
		return codReserva;
	}

	public void setCodReserva(String codReserva) {
		this.codReserva = codReserva;
	}
}
