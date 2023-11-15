package act3_1;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMT extends Thread {
    private int nbrClient = 1;

    public static void main(String[] args) {
        new ServerMT().start();
    }

    @Override
    public void run() {
        try {
            ServerSocket ss = new ServerSocket(1234);
            System.out.println("Un serveur attend un client ");

            while (true) {
                Socket s = ss.accept();
                new ClientProcess(s, nbrClient).start();
                nbrClient++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public class ClientProcess extends Thread {
        private Socket s;
        private int nbrClient;

        ClientProcess(Socket s, int nbrClient) {
            this.s = s;
            this.nbrClient = nbrClient;
        }

        public void run() {
            try {
                InputStream is = s.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);
                BufferedReader bf = new BufferedReader(isr);

                OutputStream os = s.getOutputStream();
                PrintWriter pw = new PrintWriter(os, true);

                String IP = s.getRemoteSocketAddress().toString();
                System.out.println("Connexion du client num " + nbrClient + IP);

                pw.println("Client num : " + nbrClient);

                String req;
                while ((req = bf.readLine()) != null) {
                    pw.println(req.length());
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    s.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}