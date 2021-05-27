package final_project;

import java.nio.file.Paths;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
/* Esteban Alvarado
 * CS2012
 * Section: 05 Lab: 06
 * Description: Final Project: Menu
 * This class contains the menu and the different options for grid sizes
 */
public class Menu {
	//Default Constructor 
	public Menu() {
	}
	
	public Scene makeMenu(Stage primaryStage) {
		Game game = new Game();
		Pane root = new Pane();
		Scene menuScene = new Scene(root);
		Image menu = new Image("frames/menu_scaled.png");
		
		Button five = new Button("5 x 5 Grid");
		Button seven = new Button("7 x 7 Grid");
		Button ten = new Button("10 x 7 Grid");
		
		setStyle(five);
		setStyle(seven);
		setStyle(ten);
		
		five.setLayoutX(270);
		five.setLayoutY(460);
		seven.setLayoutX(370);
		seven.setLayoutY(460);
		ten.setLayoutX(470);
		ten.setLayoutY(460);
		
		
		root.getChildren().add(new ImageView(menu));
		root.getChildren().addAll(five, seven, ten);
		

		 five.setOnAction(new EventHandler<ActionEvent>() {  
	            @Override  
	            public void handle(ActionEvent arg0) {  
	                primaryStage.setScene(game.makeGame(5, 5));
	    	            }  
	    	        } );  
		 
		 seven.setOnAction(new EventHandler<ActionEvent>() {  
	            @Override  
	            public void handle(ActionEvent arg0) {  
	            	primaryStage.setScene(game.makeGame(7, 7));
	    	            }  
	    	        } );
		 
		 ten.setOnAction(new EventHandler<ActionEvent>() {  
	            @Override  
	            public void handle(ActionEvent arg0) {  
	            	primaryStage.setScene(game.makeGame(7, 10)); //(y, x)
	    	            }  
	    	        } );
		 
		root.requestFocus();
		return menuScene;
	}
	
	private Button setStyle(Button b ) {
		//Method styles buttons used in main menu
		b.setTextFill(Color.BLACK);
		b.setFont(Font.font("Merienda", FontWeight.EXTRA_BOLD, FontPosture.ITALIC, 15));
		return b;
	}
	
}
