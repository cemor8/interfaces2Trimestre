package com.example.chat;

import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXScrollPane;
import io.github.palexdev.materialfx.controls.MFXTextField;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
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
    private MFXButton btnenviar;

    @FXML
    private MFXTextField introducirMensaje;

    @FXML
    private Label labelNombre;
    private ClienteThread clienteThread;
    private String nombre;
    private int puertoServer = 3000;
    private byte[] buffer = new byte[1024];
    @FXML
    private VBox meter;
    InetAddress direccionSever;
    DatagramSocket socket;
    /**
     * Método que se encarga de enviar los mensajes al sevidor y representar los
     * mensajes propios en el chat
     * */
    @FXML
    void enviar(MouseEvent event) throws IOException {

        String mensaje = this.introducirMensaje.getText();
        if (mensaje.isEmpty()){
            return;
        }
        this.introducirMensaje.setText("");
        HBox hboxLocal = new HBox();
        hboxLocal.setAlignment(Pos.CENTER_RIGHT);
        Label label = new Label("Tu : "+mensaje);
        label.getStyleClass().add("propio");
        hboxLocal.getChildren().add(label);
        VBox.setMargin(hboxLocal,new Insets(10,10,0,0));
        this.meter.getChildren().add(hboxLocal);
        mensaje = this.nombre+" : "+mensaje;

        buffer = mensaje.getBytes();
        DatagramPacket paqueteMensaje = new DatagramPacket(buffer,buffer.length,direccionSever,puertoServer);
        socket.send(paqueteMensaje);
        buffer = new byte[1024];

    }
    public void recibirNombre(String nombre){
        this.nombre = nombre;
        this.labelNombre.setText("Hola, "+nombre);
    }
    /**
     * Se hace una conexion para indicar al server que hay un nuevo cliente, tambien se crea el hilo
     * asociado a este cliente
     * */
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

    /**
     * Método que mete el mensaje recibido en la interfaz
     * */
    public void meterMensaje(String mensaje){
        Platform.runLater(() -> {
            HBox hboxRemote = new HBox();
            hboxRemote.setAlignment(Pos.CENTER_LEFT);
            Label label = new Label(mensaje);
            label.getStyleClass().add("otro");
            hboxRemote.getChildren().add(label);
            VBox.setMargin(hboxRemote,new Insets(10,0,0,10));
            this.meter.getChildren().add(hboxRemote);
        });

    }
}
