package application;
	
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		Maze maze = new Maze();
		
		ControlPane controlPane = new ControlPane();
		controlPane.setId("control-pane");

		int margin = 10;
		BorderPane.setMargin(maze.layout, new Insets(margin));
		BorderPane.setMargin(controlPane, new Insets(margin, 0, margin, margin));
		
		BorderPane root = new BorderPane();
		root.setId("root");
		root.setLeft(controlPane);
		root.setCenter(maze.layout);
		
		Scene scene = new Scene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

		maze.layout.prefWidthProperty().bind(root.widthProperty().subtract(controlPane.getMinWidth() + 3 * margin));
		maze.layout.prefHeightProperty().bind(root.heightProperty().subtract(2 * margin));
		
		root.widthProperty().addListener(l -> {
			maze.createGrid();
		});
		root.heightProperty().addListener(l -> {
			maze.createGrid();
		});
		
		primaryStage.setTitle("Maze");
		primaryStage.setScene(scene);
		primaryStage.setMinWidth(maze.layout.getMinWidth() + controlPane.getMinWidth() + 50);
		primaryStage.setMinHeight(Math.max(maze.layout.getMinHeight(), controlPane.getMinHeight()) + 60);
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
