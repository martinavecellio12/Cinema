package cinema;

import java.io.*;
import java.net.*;

public class Connect extends Thread{
	
	private Socket client = null;
	BufferedReader in = null;
	DataOutputStream out = null;
	
	public Connect(Socket clientSocket){
		client = clientSocket;
		try{
			in = new BufferedReader(new InputStreamReader(client.getInputStream()));
			out = new DataOutputStream(client.getOutputStream());
		}
		catch(Exception e1){
			try {
				client.close();
			}
			catch(Exception e){
				System.out.println(e.getMessage());
				System.out.println("Errore durante l'istanza del server");
				System.exit(1);
			}
			return;
		}
		this.start();
	}
	
	public void run(){
		try{
			System.out.println("Generico messaggio per il Client");
			out.flush();
			// chiude gli stream e le connessioni
			out.close();
			in.close();
			client.close();
		}
		catch(Exception e) {}
	}
}