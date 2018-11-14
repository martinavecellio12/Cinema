package cinema;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente {
	String nomeServer ="localhost";     // InetAddress.getLocalHost();
    int portaServer = 6789;
    Socket miosocket;
	BufferedReader inDalServer;
	String stringaRicevuta; //stringa ricevuta dal server
	DataOutputStream outVersoServer;
	
	public Cliente() {
		//GUI interfaccia = new GUI(this);
	}
	
    public Socket connetti() {
		System.out.println("2) CLIENT: partito in esecuzione ....");
		try {	
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
    
    public String comunica(String s) {
		try {
			// Rimango in attesa della riga trasmessa dal client
			System.out.println("4) CLIENT: Giocatore inserisci 3 numeri dal 1 a 50 staccati da uno spazio \" \" da trasmettere al Server ....");
			
			// Spedisco al server
			System.out.println("5) CLIENT: ..... invio al server e attendo ....");
			outVersoServer.writeBytes(s + "\n");

			// Leggo risposta del server
			stringaRicevuta = inDalServer.readLine();
			System.out.println("8) CLIENT: risposta dal server: "+stringaRicevuta);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Errore durante la comunicazione del server");
			System.exit(1);
		}
		return stringaRicevuta;
	}
    
    public void chiusura() {
    	// Chiudo connessione sul server e chiudo server
    	System.out.println("9) CLIENT: elaborazione terminata e chiudo connessione");
    	try {
			miosocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public static void main(String[] args) {
		Cliente cliente = new Cliente();
	}
}