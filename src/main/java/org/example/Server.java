package org.example;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;

public class Server {
    static int portNumber = 1234;
    static PrintWriter out;
    static Gson gson = new Gson();
    static String json;

    static ArrayList<Product> products = new ArrayList<>();

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
        buildProductList();


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


                ControlMessage(s);

                //out.println(s.toUpperCase());
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }

    }

    private static void ControlMessage(String s) {
        switch (s) {
            case "cheaper":
                int index =checkCheaperProduct();

                System.out.println("cheaper");
                json = gson.toJson(products.get(index));
                System.out.println(json);


                break;
            case "all":
                System.out.println("all");
                json = gson.toJson(products);
                System.out.println(json);
                break;

            case "all_sorted":
                System.out.println("all_sorted");
                orderList();

                break;
            default:
                System.out.println("Invalid option!!!");
        }
    }

    private static int checkCheaperProduct() {
        double price=products.get(0).getPrice();
        int index=0;
        for(int i=0;i<products.size();i++) {
            if (price<products.get(i).getPrice())
            {
                price=products.get(i).getPrice();
                index=i;
            }
        }
        return index;
    }

    private static void orderList() {
        ArrayList<Product> orderList=new ArrayList<>();
        for(Product o:products)
            orderList.add(o);

        Collections.sort(orderList);
        json = gson.toJson(orderList);
        System.out.println(json);

    }



    private static void buildProductList() {
        products.add(new Product(36213,"Huawei Honor 8 BLACK",25.94, 6));
        products.add(new Product(36214,"Huawei Honor 8 RED",26.94, 1));
        products.add(new Product(36215,"Apple IPhone 13 RED",1226.94, 10));
    }
}