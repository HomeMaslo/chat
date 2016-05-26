/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javachatserver;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author human
 */
public class Main {

    private static final int SERVER_PORT = 7812;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        try {
            ServerSocket serverSocket = new ServerSocket(SERVER_PORT);
            System.out.println("SERVER STARTED ON " + SERVER_PORT);

            while (true) {
                Socket acceptedSocket = serverSocket.accept();
                System.out.println("CONNECTED: " + acceptedSocket.getInetAddress());

                DataInputStream dis = new DataInputStream(acceptedSocket.getInputStream());
                DataOutputStream dos = new DataOutputStream(acceptedSocket.getOutputStream());
                if (acceptedSocket != null) {
                    String from = dis.readUTF();
                    String massage = dis.readUTF();
                    System.out.println("1 from: " + from);
                    System.out.println("2 massage: " + massage);
                    if (from != null && massage != null) {
                        dos.writeUTF("Ok");
                        dos.flush();
                    }
                }
                 
            }
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
