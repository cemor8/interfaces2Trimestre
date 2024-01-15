package model;

import java.util.Locale;
import java.util.ResourceBundle;

public class CambiarIdioma {
    public static CambiarIdioma instance;
    private ResourceBundle resourceBundle;

    public CambiarIdioma() {
        cargarIdioma("es","ES");
    }
    public static CambiarIdioma getInstance(){
        if (instance == null){
            instance = new CambiarIdioma();
        }
        return instance;
    }
    public void cargarIdioma(String idioma, String pais){
        Locale locale = new Locale(idioma,pais);
        resourceBundle = ResourceBundle.getBundle("bundles.MessagesBundle",locale);
    }
    public ResourceBundle getBundle(){
        return resourceBundle;
    }
}
