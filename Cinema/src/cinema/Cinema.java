package cinema;

import java.io.*;
import java.net.*;

public class Cinema {
	
	private ServerSocket server;
	private Socket client;
	private BufferedReader input;
	private DataOutputStream output;
	
	public Cinema() throws IOException {
		System.out.println("SERVER: partito in esecuzione");
		
		server = new ServerSocket(6789);
	}
	
	public void attendi() {
		try {
			// Resta in attesa di un client
			client = server.accept();
			
			// Chiudo server per inibire altri client (client/server unicast)
			server.close();
		
			// Associo due oggetti al socket client per la scrittura e la lettura
 			output = new DataOutputStream(client.getOutputStream());
			input = new BufferedReader(new InputStreamReader(client.getInputStream()));
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Errore durante l'istanza del server");
			System.exit(1);
		}
	}
	
	public static int[] trasforma(String s) {
		String[] splittata = s.split(" ");
		int[] numeretti = new int[splittata.length];
		for(int i = 0; i<splittata.length ; i++) {
			numeretti[i] = Integer.parseInt(splittata[i]);
		}
		
		return numeretti;
	}
	
	public static String comunicazioneClient(int[] vincitori) {
		String s="";
		for(int i=0; i<vincitori.length; i++) {
			if(vincitori[i]<10){
				s += "0";
			}
			s+=vincitori[i]+" ";
		}
		
		return s;
	}
	
	public static int[] estrazione(int n) {
		int[] vincitori = new int[n];
		for(int i=0; i<vincitori.length; i++) {
			boolean presente = false;
			do {
				int temp = (int)(Math.random() * 51);
				for(int y=0; y<vincitori.length; y++) {
					if(temp == vincitori[y]) {
						presente = true;
					}
				}
				vincitori[i] = temp;
			}
			while(presente);
		}
		return vincitori;
	}
	
	public static String vincenti(int[] numeretti, int[] vincitori) {
		String numeriVincenti = "";
		for(int i : numeretti) {
			for(int y : vincitori) {
				if(i == y) {
					numeriVincenti += i + "  ";
				}
			}
		}
		String comunicazione = "";
		 if(numeriVincenti.length() == 0) {
			comunicazione = numeriVincenti+"0";
		}
		else {
			comunicazione = numeriVincenti+numeriVincenti.length();
		}
		return comunicazione;
	}
	
	public static boolean controlloCheater(int[] numeretti) {
		boolean cheater = false;
		for(int i=0; i<numeretti.length; i++) {
			if(numeretti[i]<0 || numeretti[i]>50)
				cheater=true;
		}
		ordinamento(numeretti);
		for(int i=1; i<numeretti.length; i++) {
			if(numeretti[i] == numeretti[i-1]) {
				cheater = true;
			}
		}
		return cheater;
	}
	
	private static void ordinamento(int arr[]) { 
        int n = arr.length; 
        for (int i=1; i<n; ++i){ 
            int key = arr[i]; 
            int j = i-1;
            while(j>=0 && arr[j] > key) { 
                arr[j+1] = arr[j]; 
                j = j-1; 
            } 
            arr[j+1] = key; 
        } 
    } 
	
	public static void main(String[] args) throws IOException {
		Cinema servente = new Cinema();
		while(true) {
			servente.attendi();
		}
	}
}