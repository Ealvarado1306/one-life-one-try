package final_project;

import java.nio.file.Paths;
import java.util.Random;
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
/* Esteban Alvarado
 * CS2012
 * Section: 05 Lab: 06
 * Description: Final Project: Game Object
 * This class contains the actual game for the final project
 */
public class Game {
	public Game() {
		
	}
	
	public Scene makeGame(int gridYMax, int gridXMax) {

		BorderPane root = new BorderPane(); // container for game

		GridPane grid = new GridPane();
		grid.setPadding(new Insets(10)); // outside border of grid
		grid.setHgap(1); // distance b/t horizontal grid
		grid.setVgap(1); // distance b/t vertical grid

		
		Tile[][] tiles = new Tile[gridYMax][gridXMax]; // Arr to store each tile
		for (int y = 0; y < tiles.length; y++) {
			for (int x = 0; x < tiles[y].length; x++) {
				tiles[y][x] = new Tile();
				grid.add(tiles[y][x], x, y);
			}
		}

		// Creating each game piece;
		Random rand = new Random();
		Player hero = new Player(40);
		Boss ogre = new Boss();
		Heart heart1 = new Heart();
		Heart heart2 = new Heart();
		Trap trap1 = new Trap();
		Trap trap2 = new Trap();
		Trap trap3 = new Trap();
		Monster orc1 = new Monster();
		Monster orc2 = new Monster();
		Monster orc3 = new Monster();

		// This portion of code populates the grid with 10 total characters/game objects
		int count = 0; // 10 Pieces in the game total
		while (count < 10) {
			int y = rand.nextInt(gridYMax);
			int x = rand.nextInt(gridXMax);

			if (tiles[y][x].hasAnything() == false) {
				switch (count) {
				case 0:
					hero.setYPos(y);
					hero.setXPos(x);
					tiles[y][x].setPlayer();
					count++;
					break;
				case 1:
					ogre.setYPos(y);
					ogre.setXPos(x);
					tiles[y][x].setBoss();
					count++;
					break;
				case 2:
					heart1.setYPos(y);
					heart1.setXPos(x);
					tiles[y][x].setHeart();
					count++;
					break;
				case 3:
					heart2.setYPos(y);
					heart2.setXPos(x);
					tiles[y][x].setHeart();
					count++;
					break;
				case 4:
					trap1.setYPos(y);
					trap1.setXPos(x);
					tiles[y][x].setTrap();
					count++;
					break;
				case 5:
					trap2.setYPos(y);
					trap2.setXPos(x);
					tiles[y][x].setTrap();
					count++;
					break;
				case 6:
					trap3.setYPos(y);
					trap3.setXPos(x);
					tiles[y][x].setTrap();
					count++;
					break;
				case 7:
					orc1.setYPos(y);
					orc1.setXPos(x);
					tiles[y][x].setMonster();
					count++;
					break;
				case 8:
					orc2.setYPos(y);
					orc2.setXPos(x);
					tiles[y][x].setMonster();
					count++;
					break;
				case 9:
					orc3.setYPos(y);
					orc3.setXPos(x);
					tiles[y][x].setMonster();
					count++;
					break;
				}

			}
		}
		
		//Shrouds the map in a cover
		shroudMap(tiles);

		//Adventure Log Management
		ScrollPane advLogContainer = new ScrollPane();
		advLogContainer.setMaxWidth(250);
		advLogContainer.setMaxHeight(350);
		advLogContainer.setHbarPolicy(ScrollBarPolicy.NEVER);
		advLogContainer.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		FlowPane advLog = new FlowPane();
		advLog.setMaxWidth(100);
		advLog.setPadding(new Insets(5));
		Label advLogTitle = new Label("Adventure Log");
		advLogTitle.setFont(Font.font("Consolas", FontWeight.EXTRA_BOLD, FontPosture.ITALIC, 30));
		advLog.getChildren().add(advLogTitle);
		advLogContainer.setContent(advLog);

		// Container for Health
		StackPane health = new StackPane();
		Label initialHealth = new Label("Health: 40/40");
		initialHealth.setFont(Font.font("Consolas", FontWeight.EXTRA_BOLD, FontPosture.ITALIC, 15));
		health.getChildren().add(initialHealth);

		// This section of code contains all player movement
		root.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent e) {
				switch (e.getCode()) {
				
				case DOWN://Movement South
					if (hero.getYPos() == gridYMax - 1) {
						// Bounds Check: Player cannot go below grid 
						break;
					}
					if (tiles[hero.getYPos() + 1][hero.getXPos()].hasBoss() == true) {
						//Collision with boss
						collisionBoss(hero, advLog, health);
						break;
					}
					else if (tiles[hero.getYPos() + 1][hero.getXPos()].hasAnything() == true) {
						//Collision with anything
						tiles[hero.getYPos()][hero.getXPos()].removePlayer();
						tiles[hero.getYPos()][hero.getXPos()].setCoverBool(false);
						collider(tiles[hero.getYPos() + 1][hero.getXPos()], hero,advLog, health);
						hero.setYPos(hero.getYPos() + 1); // backend new position
						tiles[hero.getYPos()][hero.getXPos()].setPlayer(); // GUI new position
						break;
					}
					else {
						//Regular Movement
						tiles[hero.getYPos()][hero.getXPos()].removePlayer(); // Fill the old tile with a blank floor
						tiles[hero.getYPos()][hero.getXPos()].setCoverBool(false);
						hero.setYPos(hero.getYPos() + 1); // backend new position
						tiles[hero.getYPos()][hero.getXPos()].setPlayer(); // GUI new position
						break;
					}

				case UP: //Movement North
					if (hero.getYPos() == 0 ) {
						// Bounds Check: Player cannot go above grid
						break;
					} 
					
					if(tiles[hero.getYPos() - 1][hero.getXPos()].hasBoss() == true ) {
						//Collision with boss
						collisionBoss(hero, advLog, health);
						break;
					}
					
					else if (tiles[hero.getYPos() - 1][hero.getXPos()].hasAnything() == true) {
						//Collision with anything else
						tiles[hero.getYPos()][hero.getXPos()].removePlayer();
						tiles[hero.getYPos()][hero.getXPos()].setCoverBool(false);
						collider(tiles[hero.getYPos() - 1][hero.getXPos()], hero,advLog, health);
						hero.setYPos(hero.getYPos() - 1); // backend new position
						tiles[hero.getYPos()][hero.getXPos()].setPlayer(); // GUI new position
						break;
					}
					
					else {
						//Regular Movement
						tiles[hero.getYPos()][hero.getXPos()].removePlayer(); // Fill the old tile with a blank floor
						tiles[hero.getYPos()][hero.getXPos()].setCoverBool(false);
						hero.setYPos(hero.getYPos() - 1);
						tiles[hero.getYPos()][hero.getXPos()].setPlayer();
						break;
					}

				case LEFT: //Movement West
					if (hero.getXPos() == 0) {
						// Bounds Check: Player cannot go beyond left limit of grid
						break;
					}
					
					if (tiles[hero.getYPos()][hero.getXPos()-1].hasBoss() == true) {
						//Collision with boss
						collisionBoss(hero, advLog, health);
						break;
					}
					
					else if (tiles[hero.getYPos()][hero.getXPos()-1].hasAnything() == true) {
						//collision with anything else
						tiles[hero.getYPos()][hero.getXPos()].removePlayer();
						tiles[hero.getYPos()][hero.getXPos()].setCoverBool(false);
						collider(tiles[hero.getYPos()][hero.getXPos()-1], hero,advLog, health);
						hero.setXPos(hero.getXPos() - 1); // backend new position
						tiles[hero.getYPos()][hero.getXPos()].setPlayer(); // GUI new position
						break;
					}
					else {
						//Regular Movement
						tiles[hero.getYPos()][hero.getXPos()].removePlayer();
						tiles[hero.getYPos()][hero.getXPos()].setCoverBool(false);
						hero.setXPos(hero.getXPos() - 1);
						tiles[hero.getYPos()][hero.getXPos()].setPlayer();
						break;
					}

				case RIGHT: //Movement East
					if (hero.getXPos() == gridXMax - 1) {
						// Bounds Check: Player cannot go beyond right limit of grid
						break;
					}
					
					if (tiles[hero.getYPos()][hero.getXPos()+1].hasBoss() == true) {
						//Collision with boss
						collisionBoss(hero, advLog, health);
						break;
					}
					else if (tiles[hero.getYPos()][hero.getXPos()+1].hasAnything() == true) {
						//Collision with anything else
						tiles[hero.getYPos()][hero.getXPos()].removePlayer();
						tiles[hero.getYPos()][hero.getXPos()].setCoverBool(false);
						collider(tiles[hero.getYPos()][hero.getXPos()+1], hero,advLog, health);
						hero.setXPos(hero.getXPos() + 1); // backend new position
						tiles[hero.getYPos()][hero.getXPos()].setPlayer(); // GUI new position
						break;
					}
					else {
						//Regular Movement
						tiles[hero.getYPos()][hero.getXPos()].removePlayer();
						tiles[hero.getYPos()][hero.getXPos()].setCoverBool(false);
						hero.setXPos(hero.getXPos() + 1);
						tiles[hero.getYPos()][hero.getXPos()].setPlayer();
						break;
					}

				case W: //Firebolt North
					advLog.getChildren().add(new Label("You casted fire to the North"));
					hero.castFirebolt();
					updateHealthUI(health, hero);
					if(hero.getYPos() == 0) {
						break;
					}
					else {
						if(tiles[hero.getYPos() - 1][hero.getXPos()].hasBoss() == true) {
							youWin(advLog, tiles, hero, ogre);
							break;
						}
						
						else {
							advLog.getChildren().add(new Label("Miss. You hear large footsteps scurry away"));
							bossReposition(tiles, ogre, gridYMax, gridXMax);
							break;
						}
					}
					
				case S: //Firebolt South
					advLog.getChildren().add(new Label("You casted fire to the South"));
					hero.castFirebolt();
					updateHealthUI(health, hero);
					
					if(hero.getYPos() == gridYMax-1) {
						break;
					}
					else {
						if(tiles[hero.getYPos() + 1][hero.getXPos()].hasBoss() == true) {
							youWin(advLog, tiles, hero, ogre);
							break;
						}
						
						else {
							advLog.getChildren().add(new Label("Miss. You hear large footsteps scurry away"));
							bossReposition(tiles, ogre, gridYMax, gridXMax);
							break;
						}
					}
					
				case A: //Firebolt West
					advLog.getChildren().add(new Label("You casted fire to the West"));
					hero.castFirebolt();
					updateHealthUI(health, hero);
					
					if(hero.getXPos() == 0) {
						break;
					}
					else {
						if(tiles[hero.getYPos()][hero.getXPos()-1].hasBoss() == true) {
							youWin(advLog, tiles, hero, ogre);
							break;
						}
						
						else {
							advLog.getChildren().add(new Label("Miss. You hear large footsteps scurry away"));
							bossReposition(tiles, ogre, gridYMax, gridXMax);
							break;
						}
					}
					
				case D: //Firebolt east
					advLog.getChildren().add(new Label("You casted fire to the East"));
					hero.castFirebolt();
					updateHealthUI(health, hero);
					
					if(hero.getXPos() == gridXMax -1) {
						break;
					}
					else {
						if(tiles[hero.getYPos()][hero.getXPos()+1].hasBoss() == true) {
							youWin(advLog, tiles, hero, ogre);
							break;
						}
						
						else {
							advLog.getChildren().add(new Label("Miss. You hear large footsteps scurry away"));
							bossReposition(tiles, ogre, gridYMax, gridXMax);
							break;
						}
					}
					
				case E: //Removes cover 1 tile away from player
					hero.castRadiance();
					advLog.getChildren().add(new Label("You cast Radiance! You can see deep into \nthe caves. It cost 10HP."));
					updateHealthUI(health, hero);
					
					if(hero.getYPos() != 0) {
						tiles[hero.getYPos()-1][hero.getXPos()].removeCover();
					}
					if(hero.getYPos() != gridYMax-1) {
						tiles[hero.getYPos()+1][hero.getXPos()].removeCover();
					}
					if(hero.getXPos() != 0) {
						tiles[hero.getYPos()][hero.getXPos()-1].removeCover();
					}
					if(hero.getXPos() != gridXMax-1) {
						tiles[hero.getYPos()][hero.getXPos()+1].removeCover();
					}
					break;
				
				case F:
					debugMap(tiles);
					break;

				default:
					break;
				}

				

				// Warning and Notification System
				if(hero.getYPos() != 0 ) { //Check Above
					if(tiles[hero.getYPos() - 1][hero.getXPos()].hasAnything() == true) {
						advLog.getChildren().add(tileChecker(tiles[hero.getYPos() -1][hero.getXPos()]));
					}
				}
				
				if(hero.getYPos() != gridYMax-1) { //Check below
					if (tiles[hero.getYPos() + 1][hero.getXPos()].hasAnything() == true) {
						advLog.getChildren().add(tileChecker(tiles[hero.getYPos() + 1][hero.getXPos()]));
					}
				}
				
				if(hero.getXPos() != 0) { //Check left
					if(tiles[hero.getYPos()][hero.getXPos()-1].hasAnything() == true) {
						advLog.getChildren().add(tileChecker(tiles[hero.getYPos()][hero.getXPos()-1]));
					}
				}
				if(hero.getXPos() != gridXMax-1) { //Check Right
					if(tiles[hero.getYPos()][hero.getXPos()+1].hasAnything() == true) {
						advLog.getChildren().add(tileChecker(tiles[hero.getYPos()][hero.getXPos()+1]));
					}
				}
				
				
				
				//Event: Player health reaches zero
				if (hero.getHP() == 0) {
					Game_Over dead = new Game_Over();
				}
				
				
			}
		});

		// Container for Buttons
		FlowPane controls = new FlowPane();
		controls.setPadding(new Insets(5)); // outside border of grid
		controls.setHgap(10); // distance b/t horizontal grid
		//controls.setVgap(5); // distance b/t 
		
		Button b = new Button();
		b.setText("Debug Mode");
		controls.getChildren().add(b);
		b.setOnAction(new EventHandler<ActionEvent>() {  
            @Override  
            public void handle(ActionEvent arg0) {  
                debugMap(tiles);  
                root.requestFocus();
            }  
        } );  
		
		
		// Placing everything into the root grid
		root.setCenter(grid);
		root.setBottom(controls);
		root.setTop(health);
		root.setRight(advLogContainer);

		Scene gameScene = new Scene(root);
		root.requestFocus();
		return gameScene;
	}

	private static void debugMap(Tile[][] tiles) {
		//Method removes the most recent node in the tile stackpane (which should be the cover)
		for (int y = 0; y < tiles.length; y++) {
			for (int x = 0; x < tiles[y].length; x++) {
				tiles[y][x].removeCover();
				}
			}
		}
	
	
	private static void shroudMap(Tile[][] tiles) {
		//Method shrouds map in a black rectangle
		for (int y = 0; y < tiles.length; y++) {
			for (int x = 0; x < tiles[y].length; x++) {
				if(tiles[y][x].hasPlayer() == false) {
					Rectangle rec = new Rectangle(40, 40);
					rec.setFill(javafx.scene.paint.Color.BLACK);
					rec.setStyle("-fx-arc-height: 10; -fx-arc-width: 10;");
					tiles[y][x].setCover(rec);
				}
			}
		}
	}
	
	private static void youWin(FlowPane advLog, Tile[][] tiles, Player hero, Boss ogre) {
		Label win = new Label("Hit! You have defeated the boss! You win!");
		win.setTextFill(Color.BLUE);
		advLog.getChildren().add(win);
		tiles[ogre.getYPos()][ogre.getXPos()].removeBoss();
		AudioClip sound = new AudioClip(Paths.get("src/frames/eb_winboss.wav").toUri().toString());
		sound.setPriority(1000);
		sound.play();
	}
	
	
	private static void bossReposition(Tile[][] tiles, Boss ogre, int gridYMax, int gridXMax) {
		//Method randomly selects a direction and moves the boss to a new location, 1 space away from its original location 
		//Boss cannot exit grid nor collide with other object
		Random r = new Random();
		
		boolean valid = false;
		
		while(valid == false) {
			int value = r.nextInt(100);
			int direction = 0;
			//0 = north
			//1 = south
			//2 = west
			//3 = east
			
			if (value >= 0 && value < 25) {
				direction = 0;
			}
			else if(value >=25 && value < 50) {
				direction = 1;
			}
			else if(value >=50 && value < 75) {
				direction = 2;
			}
			else if(value >=75 && value <=100) {
				direction = 3;
			}
			
			switch(direction) {
			case 0: //North
				if(ogre.getYPos() == 0) {
					valid = false;
					break;
				}
				else {
					if (tiles[ogre.getYPos() -1][ogre.getXPos()].hasAnything() == true) {
						valid = false;
						break;
					}
					
					else {
						valid = true;
						tiles[ogre.getYPos()][ogre.getXPos()].removeBoss();
						tiles[ogre.getYPos()][ogre.getXPos()].setCover(makeCover());

						ogre.setYPos(ogre.getYPos() -1);
						tiles[ogre.getYPos()][ogre.getXPos()].setBoss();
						tiles[ogre.getYPos()][ogre.getXPos()].setCover(makeCover());
						
						break;
					}
				}
				
				
			
			case 1: //South
				if(ogre.getYPos() == gridYMax-1) {
					valid = false;
					break;
				}
				else {
					if (tiles[ogre.getYPos() +1][ogre.getXPos()].hasAnything() == true) {
						valid = false;
						break;
					}
					
					else {
						valid = true;
						tiles[ogre.getYPos()][ogre.getXPos()].removeBoss();
						tiles[ogre.getYPos()][ogre.getXPos()].setCover(makeCover());

						ogre.setYPos(ogre.getYPos() +1);
						tiles[ogre.getYPos()][ogre.getXPos()].setBoss();
						tiles[ogre.getYPos()][ogre.getXPos()].setCover(makeCover());
						
						break;
					}
				}
				
				
			case 2: //West
				if(ogre.getXPos() == 0) {
					valid = false;
					break;
				}
				else {
					if (tiles[ogre.getYPos()][ogre.getXPos()-1].hasAnything() == true) {
						valid = false;
						break;
					}
					
					else {
						valid = true;
						tiles[ogre.getYPos()][ogre.getXPos()].removeBoss();
						tiles[ogre.getYPos()][ogre.getXPos()].setCover(makeCover());
						
						ogre.setXPos(ogre.getXPos() -1);
						tiles[ogre.getYPos()][ogre.getXPos()].setBoss();
						tiles[ogre.getYPos()][ogre.getXPos()].setCover(makeCover());
						break;
					}
				}
				
				
			case 3: //East
				if(ogre.getXPos()== gridXMax -1) {
					valid = false;
					break;
				}
				else {
					if (tiles[ogre.getYPos()][ogre.getXPos()+1].hasAnything() == true) {
						valid = false;
						break;
					}
					
					else {
						valid = true;
						tiles[ogre.getYPos()][ogre.getXPos()].removeBoss();
						tiles[ogre.getYPos()][ogre.getXPos()].setCover(makeCover());
						ogre.setXPos(ogre.getXPos() +1);
						tiles[ogre.getYPos()][ogre.getXPos()].setBoss();
						tiles[ogre.getYPos()][ogre.getXPos()].setCover(makeCover());
						
						break;
					}
				}
				
				
			}	
		}
		
	}
	
	private static Rectangle makeCover() {
		Rectangle rec = new Rectangle(40, 40);
		rec.setFill(javafx.scene.paint.Color.BLACK);
		rec.setStyle("-fx-arc-height: 10; -fx-arc-width: 10;");
		rec.toFront();
		return rec;
	}
	
	private static void updateHealthUI(StackPane health, Player hero) {
		//Method clears the health pane and updates it with new HP
		health.getChildren().clear();
		Label h = new Label("Health: " + hero.getHP() + "/40");
		h.setFont(Font.font("Consolas", FontWeight.EXTRA_BOLD, FontPosture.ITALIC, 15));
		if(hero.getHP() <= 30 && hero.getHP() > 20) {
			h.setTextFill(Color.ORANGE);
		}
		else if(hero.getHP() <= 20 && hero.getHP() > 10) {
			h.setTextFill(Color.ORANGERED);
		}
		else if(hero.getHP() <= 10) {
			h.setTextFill(Color.RED);
		}
		health.getChildren().add(h);
		
	}
	
	private static void collisionHeart(Player hero, FlowPane advLog, StackPane health) {
		//Method designates action: player colliding with a heart
		//In Game: sets player health to max (40) and updates adventure log
		hero.setHP(40);
		updateHealthUI(health, hero);
		advLog.getChildren().add(new Label ("You found a heart! \nYour HP has been restored to max."));
		AudioClip sound = new AudioClip(Paths.get("src/frames/heal1.wav").toUri().toString());
		sound.play();
	}
	
	private static void collisionMonster(Player hero, FlowPane advLog, StackPane health) {
		//Method designates action: Player colliding with monster
		//In game: Player loses 10 HP
		hero.setHP(hero.getHP() - 10);
		if(hero.getHP() < 0) {hero.setHP(0);}
		updateHealthUI(health, hero);
		advLog.getChildren().add(new Label("OUCH. A gang of orcs beat you up. \nYou lost 10 HP."));
		AudioClip sound = new AudioClip(Paths.get("src/frames/enemyhit.wav").toUri().toString());
		sound.play();
	}
	
	private static void collisionBoss(Player hero, FlowPane advLog, StackPane health) {
		//Method designates action: Player colliding with boss
		//in game: Player HP reduced to 1
		advLog.getChildren().add(new Label("It's the Ogre! He snuck up on you!\nSMMASSSHHHHH you took a mortal blow!\nHP has been reduced to 1!"));
		AudioClip sound = new AudioClip(Paths.get("src/frames/smash.wav").toUri().toString());
		sound.setPriority(1000);
		sound.play();
		hero.setHP(1);
		updateHealthUI(health, hero);
	}
	
	
	private static void collider(Tile tile, Player hero, FlowPane advLog, StackPane health) {
		//Houses all collisions with exception of the boss
		if (tile.hasHeart() == true) {
			collisionHeart(hero, advLog, health);
			tile.removeHeart();
		}
		if (tile.hasTrap() == true) {
			Game_Over end = new Game_Over();
		}
		if (tile.hasMonster() == true) {
			collisionMonster(hero, advLog, health);
			tile.removeMonster();
		}
		
	}
	
	private static Label tileChecker(Tile tile) {
		//Method checks a tile and returns an adequate label to warn the player
		Label message = new Label();
		message.setTextFill(Color.RED);
		
		if(tile.hasBoss() == true) {
			message.setText("WARNING: You hear a menacing foe...\nyou feel a shiver run down your spine.");
		}
		if(tile.hasHeart() == true) {
			message.setText("WARNING: You feel a warm glow nearby...");
		}
		if(tile.hasTrap() == true) {
			message.setText("WARNING: You smell blood... beware");
		}
		if(tile.hasMonster() == true) {
			message.setText("WARNING: You hear footsteps nearby");
		}
		return message;
	}
}
