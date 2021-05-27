package final_project;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
/* Esteban Alvarado
 * CS2012
 * Section: 05 Lab: 06
 * Description: Final Project: Main
 * This class contains the start method which activates the entire game
 */
public class Main extends Application {

	public void start(Stage primaryStage) throws Exception {
		Menu menu = new Menu();
		primaryStage.setScene(menu.makeMenu(primaryStage));
		primaryStage.show();
	}
	

	public static void main(String[] args) {
		Application.launch();
	}

}
