package cuncurrenciaTodosLosEjerciciosRepaso.pajaro.conAdiestrador.lock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Acciones {
	
	ReentrantLock lock = new ReentrantLock();
	/*Condition rojoCanta = lock.newCondition();
	Condition verdeCanta = lock.newCondition();
	Condition negroCanta = lock.newCondition();*/
	int rojo=0;
	int verde=0;
	int negro=0;
	HashMap<String, Raza> razaAlmacen = new HashMap<String, Raza>();

	public void cantar(int id, String raza) throws InterruptedException {
		lock.lock();

		if (!razaAlmacen.containsKey(raza)) {
			razaAlmacen.put(raza, new Raza(raza, lock.newCondition()));
		}

		razaAlmacen.get(raza).add();
		System.out.println("Dragon " + id + " de raza " + raza + " a la espera de cantar");
		razaAlmacen.get(raza).parar();
		razaAlmacen.get(raza).delete();

		System.out.println("Dragon " + id + " de raza " + raza + " cantando");
		Thread.sleep(500);

		boolean change = false;
		Object[] keys = razaAlmacen.keySet().toArray();
		Random random = new Random();
		/*int aux = razaAlmacen.size();
		for (Raza x : razaAlmacen.values()) {
			if (x.mostrar() == 0) {
				aux--;
			}
		}
		if (aux == 0) {
			razaAlmacen.get(raza).segir();
		} else {*/
			while (!change) {
				Object claveAleatoria = keys[random.nextInt(keys.length)];
				Raza proximaCanta = razaAlmacen.get(claveAleatoria);
				if (proximaCanta.nombre != raza & proximaCanta.mostrar() > 0) {
					proximaCanta.segir();
					change = true;
				}
				int aux2= razaAlmacen.size();
				for (Raza x : razaAlmacen.values()) {
					if (x.mostrar() == 0) {
						aux2--;
					}
				}
				
				if(aux2 == 1 || aux2==0) {
					razaAlmacen.get(raza).segir();
					change=true;
				}
			}
		//}
		lock.unlock();
	}

	public void darAviso() throws InterruptedException {
		lock.lock();
		Object[] keys = razaAlmacen.keySet().toArray();
		Random random = new Random();
		Object claveAleatoria = keys[random.nextInt(keys.length)];
		Raza proximaCanta = razaAlmacen.get(claveAleatoria);
		proximaCanta.segir();
		lock.unlock();
	}

}
