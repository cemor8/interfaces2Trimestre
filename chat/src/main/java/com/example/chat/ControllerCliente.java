package com.example.chat;

import io.github.palexdev.materialfx.controls.MFXScrollPane;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.*;
import java.util.ResourceBundle;

public class ControllerCliente implements Initializable {
    @FXML
    private MFXScrollPane area;


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
    @FXML
    private VBox meter;
    InetAddress direccionSever;
    DatagramSocket socket;
    @FXML
    void enviar(MouseEvent event) throws IOException {

        String mensaje = this.introducirMensaje.getText();
        if (mensaje.isEmpty()){
            return;
        }
        HBox hboxLocal = new HBox();
        hboxLocal.setAlignment(Pos.CENTER_RIGHT);
        Label label = new Label("Tu : "+mensaje);
        label.getStyleClass().add("propio");
        hboxLocal.getChildren().add(label);
        this.meter.getChildren().add(hboxLocal);
        mensaje = this.nombre+" : "+mensaje;

        System.out.println("enviando mensaje");

        buffer = mensaje.getBytes();
        DatagramPacket paqueteMensaje = new DatagramPacket(buffer,buffer.length,direccionSever,puertoServer);
        socket.send(paqueteMensaje);
        buffer = new byte[1024];

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.meter = new VBox();
        this.meter.setFillWidth(true);
        this.area.setContent(meter);
        this.area.setFitToWidth(true);

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
        String mensaje = "REGISTRO";
        buffer = mensaje.getBytes();
        DatagramPacket pregunta = new DatagramPacket(buffer, buffer.length,direccionSever,puertoServer);
        try {
            socket.send(pregunta);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        buffer = new byte[1024];
        try {
            clienteThread = new ClienteThread(this);
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
        clienteThread.start();


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
        Platform.runLater(() -> {
            HBox hboxRemote = new HBox();
            hboxRemote.setAlignment(Pos.CENTER_LEFT);
            System.out.println("mensaje recibido");
            Label label = new Label(mensaje);
            label.getStyleClass().add("otro");
            hboxRemote.getChildren().add(label);
            this.meter.getChildren().add(hboxRemote);
        });

    }
}
