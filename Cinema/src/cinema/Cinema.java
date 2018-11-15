package cinema;

import java.io.*;
import java.net.*;

public class Cinema {
	ServerSocket server;
	Socket client;
	String stringaRicevuta;
	String stringaModificata;
	BufferedReader inDalClient;
	DataOutputStream outVersoClient;
	
	public Socket attendi() {
		try {
			System.out.println("1) SERVER: partito in esecuzione");
		
			// Creo un serve nella porta 6789
			server = new ServerSocket(6789);
		
			// Resta in attesa di un client
			client = server.accept();
		
			// Chiudo server per inibire altri client (client/server unicast)
			server.close();
		
			// Associo due oggetti al socket client per la scrittura e la lettura
 			outVersoClient = new DataOutputStream(client.getOutputStream());
			inDalClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Errore durante l'istanza del server");
			System.exit(1);
		}
		return client;
	}
	
	public void comunica() {
		try {
			// Rimango in attesa della riga trasmessa dal Client
			System.out.println("3) SERVER: Benvenuto Client, scrivi una frase e la trasformo in maiuscolo. - Attendo ....");
			stringaRicevuta = inDalClient.readLine();
			System.out.println("6) SERVER: ricevuta la seguente stringa dal Client -  "+stringaRicevuta);
			
			// Modifico la stringa e la rimando al Client
			stringaModificata = stringaRicevuta.toUpperCase();
			System.out.println("7) SERVER: INVIO la stringa al Client .... "+stringaModificata);
			outVersoClient.writeBytes(stringaModificata);
			
			// Termino elaborazione sul Server e chiudo Server
			System.out.println("9) SERVER: elaborazione terminata ..... Buona giornata");
			client.close();
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Errore durante la comunicazione del server");
			System.exit(1);
		}
	}
	public static void main(String[] args) {
		Cinema servente = new Cinema();
		servente.attendi();
		servente.comunica();
	}
}