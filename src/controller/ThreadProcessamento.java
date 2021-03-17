package controller;

import java.util.concurrent.Semaphore;

import javax.swing.JOptionPane;

public class ThreadProcessamento extends Thread {

	static private int qtdIngressos = 100;;
	private Semaphore semaforo;
	private int ingressos;
	private int IdComprador;

	public ThreadProcessamento(int idComprador, Semaphore semaforo) {
		this.IdComprador = idComprador;
		this.semaforo = semaforo;

	}

	public void run() {
		Acesso();
	}

	public void Acesso() {
		
		ingressos = (int) ((Math.random() * 4) + 1);
		double tempoLogin = (double) ((Math.random() * 1951) + 50);		
		tempoLogin = (double) (tempoLogin / Math.pow(10, 3));
				
		try
		{
			sleep((long) tempoLogin);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}

		if (tempoLogin >= 2)
		{
			JOptionPane.showMessageDialog(null, "Tempo Esgotado!!!");
		}
		else
		{
			ProcessoCompra();
		}

		
	}
	
	private void ProcessoCompra()

	{
		double tempoCompra = (double) ((Math.random() * 2001) + 1000);
		tempoCompra = (double) (tempoCompra / Math.pow(10, 3));

		try
		{
			sleep((long) tempoCompra);
		}
		catch (InterruptedException e)
		{
			e.printStackTrace();
		}
		
		if (tempoCompra > 3)
		{
			JOptionPane.showMessageDialog(null, "Tempo Esgotado");
		}
		else
		{
			try
			{
				semaforo.acquire();
				Exibicao();
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			finally
			{
				semaforo.release();
			}			
		}
	}

	private void Exibicao()
	{
		
		
		if (ingressos > qtdIngressos)
		{
			System.out.println("Quantidade de ingressos não disponível!");
		}
		else
		{
			qtdIngressos = qtdIngressos - ingressos;
			System.out.println("\nComprador #" + IdComprador + " - " + ingressos + " ingressos comprados!" + "\n Ingressos restantes: " + qtdIngressos);
			
		}
	}
}
