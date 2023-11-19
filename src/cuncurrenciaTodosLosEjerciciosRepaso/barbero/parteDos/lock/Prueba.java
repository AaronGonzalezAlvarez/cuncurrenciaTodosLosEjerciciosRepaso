package cuncurrenciaTodosLosEjerciciosRepaso.barbero.parteDos.lock;

public class Prueba {

	int num =0;
	
	public synchronized void add() {
		num++;
	}
	
	public synchronized void delete() {
		num--;
	}
	
	public synchronized int mostrar() {
		return num;
	}
	
	public synchronized boolean puedeSentarse() {
		return num <=5;
	}
}
