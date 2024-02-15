package mainApp;

import controller.ControllerLogin;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import modelo.CambiarIdioma;
import modelo.Data;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        CambiarIdioma.getInstance();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/vista/login.fxml"),CambiarIdioma.getInstance().getBundle());
        Parent root = fxmlLoader.load();
        ControllerLogin controllerLogin = fxmlLoader.getController();
        controllerLogin.recibirData(new Data());
        Scene scene = new Scene(root);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}