package cinema;

import java.io.*;
import java.net.*;
import java.util.*;

public class Cinema extends Thread{
	
	private ServerSocket server;
	private Socket client;
	String stringaRicevuta = null;
	String stringaModificata = null;
	private BufferedReader input;
	
	public Cinema() throws IOException {
		server = new ServerSocket(6789);
		System.out.println("SERVER: partito in esecuzione nella porta 6789");
		this.start();
	}
	
	public void run() {
		while(true) {
			try{
				System.out.println("In attesa di Connessione.");
				// Resta in attesa di un client
				client = server.accept();
				
				System.out.println("Connessione accettata da: " + client.getInetAddress());
				
				// Chiudo server per inibire altri client (client/server unicast)
				Connect c = new Connect(client);
			}
			catch(Exception e) {}
		}
	}
	
	/*public void comunica() {
		try {
			// Rimango in attesa della riga trasmessa dal Client
			System.out.println("3) SERVER: Benvenuto Client, scrivi una frase e la trasformo in maiuscolo. - Attendo ....");
			stringaRicevuta = input.readLine();
			System.out.println("6) SERVER: ricevuta la seguente stringa dal Client -  "+stringaRicevuta);
			
			// Modifico la stringa e la rimando al Client
			stringaModificata = stringaRicevuta.toUpperCase();
			System.out.println("7) SERVER: INVIO la stringa al Client .... "+stringaModificata);
			output.writeBytes(stringaModificata);
			
			// Termino elaborazione sul Server e chiudo Server
			System.out.println("9) SERVER: elaborazione terminata ..... Buona giornata");
			client.close();
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Errore durante la comunicazione del server");
			System.exit(1);
		}
	}*/
	
	public static void main(String[] args) throws IOException {
		new Cinema();
	}
}