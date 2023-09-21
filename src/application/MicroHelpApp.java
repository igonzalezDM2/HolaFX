// MicroHelpApp.java
package application;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MicroHelpApp extends Application {
    private Text helpText = new Text();

    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) {
    	//Creación de los campos de texto
        TextField tfNombre = new TextField();
        TextField tfApellido = new TextField();
        TextField tfSalario = new TextField();
        //Creación del botón cerrar
        Button btnCerrar = new Button("Cerrar");
        //callback para salir de la aplicación al pinchar el botón
        btnCerrar.setOnAction(e -> Platform.exit());
        //Añadimos los placeholders
        tfNombre.getProperties().put("microHelpText", "Inserte el nombre");
        tfApellido.getProperties().put("microHelpText", "Inserte el apellido");
        tfSalario.getProperties().put("microHelpText", "Enter a salary greater than $2000.00.");
        // Dejamos sin manejar el texto de ayuda para que no dependa de ningún nodo
        helpText.setManaged(false);
        //Lo posicionamos arriba
        helpText.setTextOrigin(VPos.TOP);
        //Color de fondo rojo
        helpText.setFill(Color.RED);
        //Tamaño de fuente 9
        helpText.setFont(Font.font(null, 9));
        //Cursor transparente
        helpText.setMouseTransparent(true);
        // Add all nodes to a GridPane
        GridPane root = new GridPane();
        root.add(new Label("First Name:"), 1, 1);
        root.add(tfNombre, 2, 1);
        root.add(new Label("Last Name:"), 1, 2);
        root.add(tfApellido, 2, 2);
        root.add(new Label("Salary:"), 1, 3);
        root.add(tfSalario, 2, 3);
        root.add(btnCerrar, 3, 3);
        root.add(helpText, 4, 3);
        Scene scene = new Scene(root, 300, 100);
        // Add a change listener to the scene, so we know when
        // the focus owner changes and display the micro help
        scene.focusOwnerProperty().addListener((ObservableValue<? extends Node> value, Node oldNode, Node newNode) -> focusChanged(value, oldNode, newNode));
        stage.setScene(scene);
        stage.setTitle("Showing Micro Help");
        stage.show();
    }

    public void focusChanged(ObservableValue<? extends Node> value, Node oldNode, Node newNode) {
        // Focus has changed to a new node
        String microHelpText = (String) newNode.getProperties().get("microHelpText");
        if (microHelpText != null && microHelpText.trim().length() > 0) {
            helpText.setText(microHelpText);
            helpText.setVisible(true);
            // Position the help text node
            double x = newNode.getLayoutX() + newNode.getLayoutBounds().getMinX() - helpText.getLayoutBounds().getMinX();
            double y = newNode.getLayoutY() + newNode.getLayoutBounds().getMinY() + newNode.getLayoutBounds().getHeight() - helpText.getLayoutBounds().getMinX();
            helpText.setLayoutX(x);
            helpText.setLayoutY(y);
            helpText.setWrappingWidth(newNode.getLayoutBounds().getWidth());
        } else {
            helpText.setVisible(false);
        }
    }
}
