package cinema;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class Cliente {
    Socket socket;
	BufferedReader in;
	DataOutputStream out;
	int portaServer = 6789;
	String messagge; //stringa ricevuta dal server
	
	public Cliente() {
		//GUI interfaccia = new GUI(this);
	}
	
    public Socket connetti() {
		System.out.println("2) CLIENT: partito in esecuzione ....");
		try {	
			// creo socket client 
	    	socket = new Socket (nomeServer, portaServer);
	    	// miosocket = new Socket (InetAddress.getLocalHost(), portaServer);
	    	
	    	// associo due oggetti al socket per scrittura e lettura
 			out = new DataOutputStream(socket.getOutputStream());
	    	in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		}
		catch (UnknownHostException e) {
			System.out.println("Host sconosciuto");
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Errore durante la connessione");
			System.exit(1);
		}
		return socket;
    }
    
    public String comunica(String s) {
		try {
			// Rimango in attesa della riga trasmessa dal client
			System.out.println("4) CLIENT: Giocatore inserisci 3 numeri dal 1 a 50 staccati da uno spazio \" \" da trasmettere al Server ....");
			
			// Spedisco al server
			System.out.println("5) CLIENT: ..... invio al server e attendo ....");
			out.writeBytes(s + "\n");

			// Leggo risposta del server
			messagge = in.readLine();
			System.out.println("8) CLIENT: risposta dal server: "+messagge);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Errore durante la comunicazione del server");
			System.exit(1);
		}
		return messagge;
	}
    
    public void chiusura() {
    	// Chiudo connessione sul server e chiudo server
    	System.out.println("9) CLIENT: elaborazione terminata e chiudo connessione");
    	try {
			socket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public static void main(String[] args) {
		Cliente cliente = new Cliente();
	}
}