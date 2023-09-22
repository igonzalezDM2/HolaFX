package application;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class FlowPaneAlignment extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }
    @Override
    public void start(Stage stage) {
    	//FLOWPANES con diferentes alineaciones de elementos (parecido al display flex)
        FlowPane fp1 = createFlowPane(Pos.BOTTOM_RIGHT);
        FlowPane fp2 = createFlowPane(Pos.BOTTOM_LEFT);
        FlowPane fp3 = createFlowPane(Pos.CENTER);
        //contenedor principal horizontal
        HBox root = new HBox(fp1, fp2, fp3);
        
        //Dibujar la escena
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("FlowPane Alignment");
        stage.show();
    }
    
    //Crea cada panel individual con 3 botones cada uno
    private FlowPane createFlowPane(Pos alignment) {
        FlowPane fp = new FlowPane(5, 5);
        fp.setPrefSize(200, 100);
        fp.setAlignment(alignment);
        
        //Añadimos 3 botones
        fp.getChildren().addAll(new Text(alignment.toString()), 
                                new Button("Button 1"), 
                                new Button("Button 2"),
                                new Button("Button 3"));
        
        //Estilos de bordes y padding
        fp.setStyle("-fx-padding: 10;" +
                    "-fx-border-style: solid inside;" + 
                    "-fx-border-width: 2;" +
                    "-fx-border-insets: 5;" + 
                    "-fx-border-radius: 5;" + 
                    "-fx-border-color: blue;");
        return fp;
    }
	
	
}
