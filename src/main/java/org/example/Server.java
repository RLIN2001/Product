package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    static int portNumber = 1234;
    static PrintWriter out;
    public static void main(String[] args) {

        System.out.println("Start Server");
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(portNumber);
        } catch (IOException e) {
            System.out.println("create socket failed");
            e.printStackTrace();
        }
        Socket clientSocket = null;
        try {
            clientSocket = serverSocket.accept();
        } catch (IOException e) {
            System.out.println("Accept failed");
            e.printStackTrace();
        }


        System.out.println("Accettato");
        try {
            out =
                    new PrintWriter(clientSocket.getOutputStream(), true);
        } catch (IOException e) {
            System.out.println("Write failed");
            e.printStackTrace();
        }


        BufferedReader in=null;

        try {
            in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));

        } catch (IOException e) {
            System.out.println("reader failed");
            e.printStackTrace();
        }

        String s="";
        try {
            while ((s = in.readLine()) != null) {
                System.out.println(s);
                //out.println(s.toUpperCase());
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }
}