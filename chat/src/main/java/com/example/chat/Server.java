package com.example.chat;

import javafx.scene.chart.PieChart;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class Server {
    private  int puerto = 3000;
    private byte[] buffer =new byte[1024];
    private DatagramSocket socket;
    private DatagramPacket paquete;
    private boolean activado = true;
    private ArrayList<Integer> puertosClientes = new ArrayList<>();
    public Server() throws SocketException {
        socket = new DatagramSocket(puerto);
        paquete = new DatagramPacket(buffer, buffer.length);
    }

    public static void main(String[] args) throws IOException {
        Server server = new Server();
        server.iniciarServer();
    }
    public void iniciarServer() throws IOException {
        while (activado){
            DatagramPacket peticion = new DatagramPacket(buffer, buffer.length);
            socket.receive(peticion);
            int puertoCliente = peticion.getPort();

            String mensajeRecibido = new String(peticion.getData(),0, peticion.getLength(), StandardCharsets.UTF_8);
            System.out.println(mensajeRecibido);

            if(mensajeRecibido.equals("REGISTRO")&&!puertosClientes.contains(puertoCliente)){
                puertosClientes.add(puertoCliente);
                continue;
            }

            if(!mensajeRecibido.isEmpty()){
                System.out.println("a replicar");
                for(int puerto : puertosClientes){
                    System.out.println(puerto);
                    if(puerto != puertoCliente){
                        System.out.println("enviando");
                        System.out.println(mensajeRecibido);
                        buffer = mensajeRecibido.getBytes();
                        DatagramPacket replicar = new DatagramPacket(buffer,buffer.length,InetAddress.getByName("localhost"),puerto);
                        socket.send(replicar);
                        buffer = new byte[1024];
                    }
                }
            }

        }
    }
}

