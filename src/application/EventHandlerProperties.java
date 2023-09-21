package application;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class EventHandlerProperties extends Application {

	public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) {
        Circle circle = new Circle(100, 100, 50);
        circle.setFill(Color.CORAL);
        HBox root = new HBox();
        root.getChildren().add(circle);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Using convenience event handler properties");
        // Create a MouseEvent handler
        EventHandler<MouseEvent> eventHandler = e -> 
            System.out.println("Mouse event handler has been called.");
        // Register the handler using the setter method for
        // the onMouseCicked convenience event property
        circle.setOnMouseClicked(eventHandler);
        
        Rectangle rectangle = new Rectangle(100, 50);
        rectangle.setFill(Color.RED);
        rectangle.setOnMouseClicked(e -> {
        	System.out.println("Click en el rectángulo");
        });
        root.getChildren().add(rectangle);
        stage.sizeToScene();
        stage.show();
    }
	
}
