package cuncurrenciaTodosLosEjerciciosRepaso.barbero.parteUno.sync;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Barberia {
	
	Object barbero = new Object();
	Object sillas = new Object(); 
	Object sillon = new Object();
	Object cola = new Object(); 
	Object o1 = new Object(); 
	boolean auxsillonBoolean=false;
	int uaxSillas=0;
	int auxSillon=0;

	public void pelarse(int id) throws InterruptedException {
		synchronized (sillas) {
			while (uaxSillas == 5) {
				System.out.println("Cliente " + id + " a la espera de coger silla");
				sillas.wait();
			}
			uaxSillas++;
			while (auxSillon == 1) {
				System.out.println("cliente " + id + " sentado en silla " + uaxSillas);
				sillas.wait();
			}
			uaxSillas--;
		}
		synchronized (sillon) {
			auxSillon++;
			System.out.println("Cliente " + id + " esperando al peluqiero");
			auxsillonBoolean = true;
			synchronized (barbero) {
				barbero.notify();
			}
			sillon.wait();
			auxsillonBoolean = false;
			auxSillon--;
			synchronized (sillas) {
				sillas.notify();
			}
			System.out.println("Cliente "+ id + " sale contento");
		}
	}
	
	public void trabajar() throws InterruptedException {
		while(true) {
			synchronized (barbero) {
				if(auxsillonBoolean) {
					System.out.println("VOY A PELAR");
					Thread.sleep(5000);
					System.out.println("TERMINADO");
					Thread.sleep(1500);
					//luego
					synchronized (sillon) {
						sillon.notify();
					}
				}
				barbero.wait();
			}			
		}

	}

}
