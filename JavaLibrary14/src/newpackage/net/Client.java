/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newpackage.net;

/**
 *
 * @author pavel
 */
public interface Client {

    public static String PING = "PING";
    public static String PONG = "PONG";
    public static String COMPLETE = "Ok";

    boolean connect();
}
