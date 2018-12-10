package pl.makz.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.io.InputStreamReader;


public class Main {
    private BufferedReader reader;
    private PrintWriter writer;
    private Socket socket;
    private String message;
    private BufferedReader readerCmd = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) {
        Main client = new Main();
        client.startClient();
    }

    private void startClient() {
        configureCommunicationWithServer();
        Thread thread = new Thread(new MessageReceiver());
        thread.start();
        try {
            while (true) {
                message = readerCmd.readLine();
                writer.println(message);
                writer.flush();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void configureCommunicationWithServer() {
        try {
            InetAddress address = InetAddress.getLocalHost();
            socket = new Socket(address, 5000);
            InputStreamReader reader1 = new InputStreamReader(socket.getInputStream());
            reader = new BufferedReader(reader1);
            writer = new PrintWriter(socket.getOutputStream());
            System.out.println("Connected to server");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public class MessageReceiver implements Runnable {
        @Override
        public void run() {
            String message;
            try {
                while ((message = reader.readLine()) != null) {
                    System.out.println(message);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
