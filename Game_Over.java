package final_project;

import java.nio.file.Paths;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
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
 * Description: Final Project: Game Over Scene
 * Contains the game over screen for when the player dies
 * 
 */
public class Game_Over {

	public Game_Over() {
		makeGameOver();
	}
	
	public void makeGameOver() {
		Pane root = new Pane();
		Image game_over = new Image("frames/game_over.jpg");
		Button y = new Button("Yes");
		Button n = new Button("No");
		y.setLayoutX(260);
		y.setLayoutY(500);
		n.setLayoutX(310);
		n.setLayoutY(500);
		
		AudioClip sound = new AudioClip(Paths.get("src/frames/gameover.wav").toUri().toString());
		sound.setPriority(1000);
		sound.play();
		
		 y.setOnAction(new EventHandler<ActionEvent>() {  
	            @Override  
	            public void handle(ActionEvent arg0) {  
	                Pane root2 = new Pane();
	                Image lol = new Image("frames/haha.jpg");
	                root2.getChildren().add(new ImageView(lol));
	                
	                Label text = new Label("Sorry! I was kidding!");
	                Label text2 = new Label("Dead means dead around here");
	                text.setLayoutX(100);
	                text.setLayoutY(15);
	                text.setTextFill(Color.RED);
	                text.setFont(Font.font("Comic Sans", FontWeight.EXTRA_BOLD, FontPosture.ITALIC, 30));
	                
	                text2.setLayoutX(30);
	                text2.setLayoutY(310);
	                text2.setTextFill(Color.RED);
	                text2.setFont(Font.font("Comic Sans", FontWeight.EXTRA_BOLD, FontPosture.ITALIC, 30));
	                
	                Button ok = new Button("Okay fine, time to die :( ");
	                ok.setLayoutX(180);
	                ok.setLayoutY(180);
	                ok.setOnAction(new EventHandler<ActionEvent>() {  
	    	            @Override  
	    	            public void handle(ActionEvent arg0) {  
	    	                System.exit(0);  
	    	        
	    	            }  
	    	        } );  
	                
	                root2.getChildren().addAll(text, text2, ok);
	                Stage troll = new Stage();
	                troll.setScene(new Scene(root2, 480, 360));
	                troll.show();
	                  
	            }  
	        } ); 
		 
		 n.setOnAction(new EventHandler<ActionEvent>() {  
	            @Override  
	            public void handle(ActionEvent arg0) {  
	                System.exit(0);  
	                  
	            }  
	        } );  
		 
		root.getChildren().add(new ImageView(game_over));
		root.getChildren().addAll(y, n);
		
		
		Stage gameOverStage = new Stage();
		gameOverStage.setScene(new Scene(root, 612, 612));
		gameOverStage.show();
		root.requestFocus();
	}
	
}
