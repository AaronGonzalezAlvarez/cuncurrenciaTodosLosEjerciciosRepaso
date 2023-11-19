package cuncurrenciaTodosLosEjerciciosRepaso.pajaro.conAdiestrador.lock;

import java.util.concurrent.locks.Condition;

public class Raza {
	
	int numDragonesPendientes;
	String nombre;
	Condition esperar;
	
	public Raza(String nombre,Condition esperar) {
		this.numDragonesPendientes = 0;
		this.nombre = nombre;
		this.esperar = esperar;
	}
	
	public void add() {
		numDragonesPendientes++;
	}
	
	public void delete() {
		numDragonesPendientes--;
	}
	
	public int mostrar() {
		return numDragonesPendientes;
	}
	
	public void parar() throws InterruptedException {
		esperar.await();
	}
	
	public void segir() throws InterruptedException {
		esperar.signal();
	}

}
