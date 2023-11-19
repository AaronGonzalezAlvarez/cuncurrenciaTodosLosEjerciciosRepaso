package cuncurrenciaTodosLosEjerciciosRepaso.barbero.parteUno.semaforo;

import java.util.concurrent.Semaphore;

public class Barberia {
	
	Semaphore sillas = new Semaphore(5);
	Semaphore sillon = new Semaphore(1);
	Semaphore peluquero = new Semaphore(0);
	Semaphore esperar = new Semaphore(0);
	boolean auxsillon=false;

	public void pelarse(int id) throws InterruptedException {
		//aqui se espera la cola
		sillas.acquire();
		System.out.println("cliente "+id +" sentado en la silla");
		sillon.acquire();
		sillas.release();
		System.out.println("cliente "+id +" sentado en el sillon a la espera del peluqiero");
		auxsillon=true;
		peluquero.release();
		esperar.acquire();
		auxsillon=false;
		System.out.println("Cliente "+id +" se va satisfecho");
		sillon.release();
	}
	
	public void trabajar() throws InterruptedException {
		while(true) {
			if(auxsillon) {
				System.out.println("VOY A PELAR");
				Thread.sleep(5000);
				System.out.println("TERMINADO");
				Thread.sleep(1500);
				esperar.release();
				
			}
			peluquero.acquire();
		}

	}

}
