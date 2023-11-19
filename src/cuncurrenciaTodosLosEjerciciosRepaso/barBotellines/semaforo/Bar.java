package cuncurrenciaTodosLosEjerciciosRepaso.barBotellines.semaforo;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Bar {
	
	int numBotellinesDisponibles;
	int auxbotellines;
	private ReentrantLock lock = new ReentrantLock();
	private Semaphore cliente = new Semaphore(1);
	private Semaphore esperar = new Semaphore(0);
	private Semaphore camarero = new Semaphore(0);
	private Random r = new Random();
	
	public Bar(int numBotellinesDisponibles) {
		this.numBotellinesDisponibles = numBotellinesDisponibles;
		this.auxbotellines = numBotellinesDisponibles;
	}

	public void tomarBotellin(int id,int numBotellinesConsume) throws InterruptedException {
		cliente.acquire();
		for(int x=0;x<numBotellinesConsume;x++) {
			while(verificarTanque()) {
				System.out.println("Cliente "+id+" no puede satisfacerse llama al camarero.");
				camarero.release();
				esperar.acquire();
			}
			numBotellinesDisponibles--;
			Thread.sleep(r.nextInt(500,1000));
		}
		System.out.println("Cliente "+id +" pilla sus botellines quedan en el barril: "+ numBotellinesDisponibles );
		cliente.release();
	}

	public void reponer() throws InterruptedException {
		while(true) {
			if(verificarTanque()) {
				numBotellinesDisponibles=auxbotellines;
				System.out.println("barril repuesto");
				esperar.release();
			}else {
				camarero.acquire();
			}
		}
	}
	
	private boolean verificarTanque() {
		return numBotellinesDisponibles ==0;
	}
	
	

}