/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newpackage.net;

import static java.awt.MediaTracker.COMPLETE;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import newpackage.Main;

/**
 *
 * @author pavel
 */
public class ChatClient implements Client, AutoCloseable {

    private String addres;
    private int port;
    private Socket socket;
    DataOutputStream dos;
    DataInputStream dis;

    public ChatClient(String addres, int port) {
        this.addres = addres;
        this.port = port;
    }

    public String getAddres() {
        return addres;
    }

    public int getPort() {
        return port;
    }

    @Override
    public boolean connect() {
        if (socket == null) {
            try {
                socket = new Socket();
                socket.setSoTimeout(1000);

                InetSocketAddress servarDestination = new InetSocketAddress(addres, port);
                socket.connect(servarDestination);

                connected(socket);

                return true;
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;

    }

    public void connected(Socket socket) throws IOException {

        dos = new DataOutputStream(socket.getOutputStream());
        dis = new DataInputStream(socket.getInputStream());
       /* boolean PingPong = sendPingPong(dos, dis);
        if (PingPong) {
            System.out.println("IF");
        }*/
    }

    private boolean sendPingPong(DataOutputStream dos, DataInputStream dis) throws IOException {
        dos.writeUTF(Client.PING);
        dos.flush();

        String respond = dis.readUTF();
        return Client.PONG.equals(respond);

    }

    public boolean sendMessage(String resipiend, String messeng) throws IOException {
        dos.writeUTF("MSG");
        dos.flush();
        dos.writeUTF(resipiend);
        dos.flush();

        dos.writeUTF(messeng);
        dos.flush();
        return COMPLETE.equals(dis.readUTF());
    }

    @Override

    public void close() throws Exception {
        socket.close();
    }
}
/*
 
JavaChatServer для клиента  такойже. 
ЗА исключением обработки команд 
1  от кого 
2 само сообщение
В ответ присылаем "ОК"
 
*/