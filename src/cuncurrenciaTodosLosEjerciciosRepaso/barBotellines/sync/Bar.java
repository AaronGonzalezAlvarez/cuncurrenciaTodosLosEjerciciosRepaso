package cuncurrenciaTodosLosEjerciciosRepaso.barBotellines.sync;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Bar {
	
	int numBotellinesDisponibles;
	int auxbotellines;
	private Random r = new Random();
	private Object o1 = new Object(); 
	private Object o2 = new Object(); 
	private boolean cambio=false;
	
	public Bar(int numBotellinesDisponibles) {
		this.numBotellinesDisponibles = numBotellinesDisponibles;
		this.auxbotellines = numBotellinesDisponibles;
	}

	public void tomarBotellin(int id, int numBotellinesConsume) throws InterruptedException {
		synchronized (o1) {
			for (int x = 0; x < numBotellinesConsume; x++) {
				while (verificarTanque()) {
					System.out.println("Cliente " + id + " no puede satisfacerse llama al camarero.");
					o1.notifyAll();
					cambio=true;
					while(cambio) {
						o1.wait();
					}
				}
				//Thread.sleep(r.nextInt(500, 1000));
				numBotellinesDisponibles--;
			}
			System.out.println("Cliente " + id + " pilla sus botellines quedan en el barril: " + numBotellinesDisponibles);
			//o1.notifyAll();
		}
	}

	public void reponer() throws InterruptedException {
		synchronized (o1) {
			while (true) {
				if (!verificarTanque()) {
					o1.wait();
				}
				numBotellinesDisponibles = auxbotellines;
				System.out.println("barril repuesto");
				cambio=false;
				o1.notifyAll();
			}
		}
	}
	
	private boolean verificarTanque() {
		return numBotellinesDisponibles ==0;
	}
}