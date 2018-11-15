package cinema;

import java.io.*;
import java.net.*;

public class Client {
	String nomeServer ="localhost";     // InetAddress.getLocalHost();
    int portaServer = 6789;
    Socket miosocket;
	BufferedReader tastiera;  //buffer per input da tastiera
	String stringaUtente; //stringa inserita da utente
	String stringaRicevuta;//stringa ricevuta dal server
	DataOutputStream outVersoServer;
	BufferedReader inDalServer;
	
    public Socket connetti() {
		System.out.println("2) CLIENT: partito in esecuzione ....");
		try {
			// input da tastiera
			tastiera = new BufferedReader(new InputStreamReader(System.in));
			
			// creo socket client 
	    	miosocket = new Socket (nomeServer, portaServer);
	    	// miosocket = new Socket (InetAddress.getLocalHost(), portaServer);
	    	
	    	// associo due oggetti al socket per scrittura e lettura
 			outVersoServer = new DataOutputStream(miosocket.getOutputStream());
	    	inDalServer = new BufferedReader(new InputStreamReader(miosocket.getInputStream()));
		}
		catch (UnknownHostException e) {
			System.out.println("Host sconosciuto");
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Errore durante la connessione");
			System.exit(1);
		}
		return miosocket;
    }
    
    public void comunica() {
		try {
			// Rimango in attesa della riga trasmessa dal client
			System.out.println("4) CLIENT: Utente inserisci la stringa da trasmettere al Server ....");
			stringaUtente = tastiera.readLine();
			
			// Spedisco al server
			System.out.println("5) CLIENT; ..... invio al server e attendo ....");
			outVersoServer.writeBytes(stringaUtente+"\n");

			// Leggo risposta del server
			stringaRicevuta = inDalServer.readLine();
			System.out.println("8) CLIENT: risposta dal server: "+stringaRicevuta);
			
			// Chiudo connessione sul server e chiudo server
			System.out.println("9) CLIENT: elaborazione terminata e chiudo connessione");
			miosocket.close();
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Errore durante la comunicazione del server");
			System.exit(1);
		}
	}
    public static void main(String[] args) {
		Client cliente = new Client();
		cliente.connetti();
		cliente.comunica();
	}
}
