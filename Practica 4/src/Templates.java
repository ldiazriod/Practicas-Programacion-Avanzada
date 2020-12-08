package Practica_4;

import java.util.Vector;

public class Templates<T> {
	private T valor;
	
	public void printTemplated(Vector<T> aux) {
		for(int i = 0; i < aux.size(); i++) {
			System.out.println(aux.elementAt(i));
		}
	}
	
	public T getValor() {
		return this.valor;
	}
	
	public void setValor(T valor) {
		this.valor = valor;
	}
}
