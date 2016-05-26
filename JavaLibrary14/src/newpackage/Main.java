/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newpackage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import newpackage.net.ChatClient;

/**
 *
 * @author pavel
 */
public class Main {

    private static final String SERVER_ADRESS = "127.0.0.1";
    private static final int SERVER_PORT = 7812;

    public static void main(String[] args) {
        try (ChatClient chatClient = new ChatClient(SERVER_ADRESS, SERVER_PORT)) {
            
           if( chatClient.connect()){
            chatClient.sendMessage("192.168.1.121", "hello world");
           }
        } catch (Exception ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
