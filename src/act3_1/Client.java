package act3_1;

	import java.io.*;
	import java.net.*;

	public class Client {
	    public static void main(String[] args) {
	        try {
	            Socket socket = new Socket("localhost", 1234);
	            
	            
	            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

	            writer.println("Bonjour serveur");

	            String response = reader.readLine();
	            System.out.println("Réponse du serveur : " + response);

	            socket.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	}

	
	
	
	

