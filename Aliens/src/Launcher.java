import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Duration;
import world.World;

public class Launcher extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage primaryStage) {
		primaryStage.setTitle("Alien Defense");

		Group root = new Group();
		Scene primaryScene = new Scene(root);
		primaryStage.setScene(primaryScene);
		World gameWorld = new World();
		gameWorld.setListeners(primaryScene);
		root.getChildren().add(gameWorld.canvas);
		AnimationTimer timer = new AnimationTimer() {
		public void handle(long arg0) {
			gameWorld.update();
			
		}
		};
		timer.start();
		primaryStage.show();
	}
}