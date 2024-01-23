package com.example.chat;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class ClienteThread extends Thread{
    ControllerCliente cliente;
    private boolean activado = true;
    DatagramSocket socket;

    public ClienteThread(ControllerCliente cliente) throws SocketException {
        this.cliente = cliente;
        socket = new DatagramSocket();
        System.out.println(socket.getPort());
    }

    @Override
    public void run(){
        while (activado){
            System.out.println("hilo esperando mensaje");
            DatagramPacket peticion = new DatagramPacket(cliente.getBuffer(), cliente.getBuffer().length);
            try {
                socket.receive(peticion);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            System.out.println("hilo acepta peticion");
            String mensaje = new String(peticion.getData());
            System.out.println(mensaje);
            this.cliente.meterMensaje(mensaje);
            socket.close();
        }

    }

    public void setActivado(boolean activado) {
        this.activado = activado;
    }
    public int getPuertoHilo(){
        return this.socket.getPort();
    }
}
