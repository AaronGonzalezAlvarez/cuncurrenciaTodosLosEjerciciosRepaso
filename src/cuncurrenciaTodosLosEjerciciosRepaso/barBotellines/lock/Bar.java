package cuncurrenciaTodosLosEjerciciosRepaso.barBotellines.lock;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Bar {
	
	int numBotellinesDisponibles;
	int auxbotellines;
	private ReentrantLock lock = new ReentrantLock();
	private Condition esperar = lock.newCondition();
	private Condition camarero = lock.newCondition();
	private Random r = new Random();
	
	public Bar(int numBotellinesDisponibles) {
		this.numBotellinesDisponibles = numBotellinesDisponibles;
		this.auxbotellines = numBotellinesDisponibles;
	}

	public void tomarBotellin(int id,int numBotellinesConsume) throws InterruptedException {
		lock.lock();
		for(int x=0;x<numBotellinesConsume;x++) {
			while(verificarTanque()) {
				//System.out.println("Cliente "+id+" no puede satisfacerse llama al camarero.");
				camarero.signal();
				esperar.await();
			}
			numBotellinesDisponibles--;
			Thread.sleep(r.nextInt(500,1000));
		}
		System.out.println("Cliente "+id +" pilla sus botellines quedan en el barril: "+ numBotellinesDisponibles );
		lock.unlock();
	}

	public void reponer() throws InterruptedException {
		lock.lock();
		while(true) {
			if(verificarTanque()) {
				numBotellinesDisponibles=auxbotellines;
				System.out.println("barril repuesto");
				esperar.signalAll();
			}else {
				camarero.await();
			}
		}
	}
	
	private boolean verificarTanque() {
		return numBotellinesDisponibles ==0;
	}
	
	

}