package com.example.chat;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.*;
import java.util.ResourceBundle;

public class ControllerCliente implements Initializable {
    @FXML
    private TextArea area;

    @FXML
    private Button btnenviar;

    @FXML
    private TextField introducirMensaje;

    @FXML
    private Label labelNombre;
    private ClienteThread clienteThread;
    private String nombre = "Pepe";
    private int puertoServer = 3000;
    private byte[] buffer = new byte[1024];
    InetAddress direccionSever;
    DatagramSocket socket;
    @FXML
    void enviar(MouseEvent event) throws IOException {

        String mensaje = this.introducirMensaje.getText();
        if (mensaje.isEmpty()){
            return;
        }
        System.out.println("enviando mensaje");
        buffer = mensaje.getBytes();
        DatagramPacket paqueteMensaje = new DatagramPacket(buffer,buffer.length,direccionSever,puertoServer);
        socket.send(paqueteMensaje);
        buffer = new byte[1024];

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            clienteThread = new ClienteThread(this);
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
        clienteThread.start();
        try {
            direccionSever = InetAddress.getByName("localhost");
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
        try {
            socket = new DatagramSocket();
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
        System.out.println(clienteThread.getPuertoHilo());
        String puertoRecibir = String.valueOf(clienteThread.getPuertoHilo());
        buffer = puertoRecibir.getBytes();
        DatagramPacket peticionConex =new DatagramPacket(buffer,buffer.length,direccionSever,puertoServer);
        try {
            socket.send(peticionConex);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    public int getPuertoServer() {
        return puertoServer;
    }

    public byte[] getBuffer() {
        return buffer;
    }

    public DatagramSocket getSocket() {
        return socket;
    }
    public void meterMensaje(String mensaje){
        System.out.println("mensaje recibido");
        this.area.setText(mensaje);
    }
}
