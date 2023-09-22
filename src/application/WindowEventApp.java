package application;

import static javafx.stage.WindowEvent.WINDOW_CLOSE_REQUEST;

import javafx.application.Application;
import javafx.event.EventType;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class WindowEventApp extends Application {

    private CheckBox checkPuedeCerrarVentana;

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) {
        //Checkbox que permite cerrar la ventana desde la X
    	checkPuedeCerrarVentana = new CheckBox("Se puede cerrar la ventana");
        
    	//Botón autónomo para cerrar la ventana
        Button btnCerrar = new Button("Cerrar");
        btnCerrar.setOnAction(e -> stage.close());
        
        //Botón para ocultar el contenido de la ventana
        Button btnOcultar = new Button("Hide");
        btnOcultar.setOnAction(e -> {
            showDialog(stage);
            stage.hide();
        });
        
        //Contenedor horizontal
        HBox cajaHorizontal = new HBox();
        cajaHorizontal.setPadding(new Insets(20));
        cajaHorizontal.setSpacing(20);
        //Añadimos los controles
        cajaHorizontal.getChildren().addAll(checkPuedeCerrarVentana, btnCerrar, btnOcultar);
        
        // Añadir los manejadores para los eventos que queramos
        stage.setOnShowing(e -> manejarEvento(e));
        stage.setOnShown(e -> manejarEvento(e));
        stage.setOnHiding(e -> manejarEvento(e));
        stage.setOnHidden(e -> manejarEvento(e));
        stage.setOnCloseRequest(e -> manejarEvento(e));
        
        //Creamos y mostramos la ventana
        Scene scene = new Scene(cajaHorizontal);
        stage.setScene(scene);
        stage.setTitle("Window Events");
        stage.show();
    }

    public void manejarEvento(WindowEvent e) {
        // Si se solicita cerrar desde la X y no está marcada la casilla, evita la propagación del evento y no permite cerrar
        EventType<WindowEvent> type = e.getEventType();
        if (type == WINDOW_CLOSE_REQUEST && !checkPuedeCerrarVentana.isSelected()) {
            e.consume();
        }
        System.out.println(type + ": Consumed=" + e.isConsumed());
    }

    public void showDialog(Stage mainWindow) {
        Stage popup = new Stage();
        Button closeBtn = new Button("Click to Show Main Window");
        closeBtn.setOnAction(e -> {
            popup.close();
            mainWindow.show();
        });
        HBox root = new HBox();
        root.setPadding(new Insets(20));
        root.setSpacing(20);
        root.getChildren().addAll(closeBtn);
        Scene scene = new Scene(root);
        popup.setScene(scene);
        popup.setTitle("Popup");
        popup.show();
    }
	

}
