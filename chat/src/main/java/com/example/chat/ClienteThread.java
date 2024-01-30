package com.example.chat;

import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class ClienteThread extends Thread{
    ControllerCliente cliente;
    private boolean activado = true;
    DatagramSocket socket;

    public ClienteThread(ControllerCliente cliente) throws SocketException {
        this.cliente = cliente;
        socket = new DatagramSocket();
    }
    /**
     * Método que se encarga de primero hacer un mensaje de conexion para que el servidor almacene su puerto, luego
     * escucha mensajes desde el servidor para mostrarlos en pantalla
     * */
    @Override
    public void run(){
        byte[] buffer;
        DatagramPacket peticionConex;

        String mensaje = "REGISTRO";
        buffer = mensaje.getBytes();
        try {
            peticionConex =new DatagramPacket(buffer,buffer.length, InetAddress.getByName("localhost"),this.cliente.getPuertoServer());
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }

        try {
            socket.send(peticionConex);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        while (activado){
            buffer = new byte[1024];

            DatagramPacket peticion = new DatagramPacket(buffer,buffer.length);
            try {
                socket.receive(peticion);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            String mensajeTexto = new String(peticion.getData(),0,peticion.getLength(), StandardCharsets.UTF_8);

            this.cliente.meterMensaje(mensajeTexto);

        }

    }
}
