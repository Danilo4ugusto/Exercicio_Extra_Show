package view;

import java.util.concurrent.Semaphore;

import controller.ThreadProcessamento;

public class Principal {

	public static void main(String[] args) {

		int permissoes = 1;
		Semaphore semaforo = new Semaphore(permissoes);
		

		for (int comprador = 1; comprador <= 300; comprador++) {
			Thread Comprar = new ThreadProcessamento(comprador,semaforo);
			Comprar.start();
		}

	}

}