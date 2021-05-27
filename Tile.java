package final_project;

import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
/* Esteban Alvarado
 * CS2012
 * Section: 05 Lab: 06
 * Description: Final Project: Tile Object
 * Note: In the future, make an "item" class to add some inheritance @EA!!
 */


public class Tile extends StackPane{
	private boolean hasCover;
	private boolean hasPlayer;
	private boolean hasMonster;
	private boolean hasSword;
	private boolean hasHeart;
	private boolean hasTrap;
	private boolean hasBoss;
	

	public Tile() {
		fillFloor();
	} 
	
	public void fillFloor() {
		Image floor = new Image("frames/floor_1.png");
		ImageView floorView = new ImageView(floor);
		floorView.setFitHeight(40);
		floorView.setFitWidth(40);
		this.getChildren().add(floorView);
	}
	
	//Removers
	public void removePlayer() {
		fillFloor();
		this.hasPlayer = false;
	}
	
	public void removeBoss() {
		fillFloor();
		this.hasBoss = false;
	}
	
	public void removeHeart() {
		fillFloor();
		this.hasHeart = false;
	}
	
	public void removeMonster() {
		fillFloor();
		this.hasMonster = false;
	}
	
	public void removeCover() {
		//Removes the last index of the StackPane which is the cover
		if(this.hasCover == true){
			this.getChildren().remove(this.getChildren().size()-1);
		}
		this.hasCover = false;
	}
	

	//Setters
	public void setCover(Rectangle rec) {
		this.getChildren().add(rec);
		this.hasCover = true;
	}
	
	public void setCoverBool(Boolean bool) {
		this.hasCover = bool;
	}
	
	public void setPlayer() {
		//Method fills the floor first, then stacks the player on top
		fillFloor();
		Image player = new Image("frames/knight_f_idle_anim_f1.png");
		this.getChildren().add(new ImageView(player));
		this.hasPlayer = true;
	}
	
	public void setMonster() {
		fillFloor();
		Image monster = new Image("frames/orc_warrior_idle_anim_f0.png");
		this.getChildren().add(new ImageView(monster));
		this.hasMonster = true;
	}
	
	public void setSword() {
		fillFloor();
		Image sword = new Image("frames/weapon_golden_sword.png");
		this.getChildren().add(new ImageView(sword));
		this.hasSword = true;
	}
	
	public void setHeart() {
		fillFloor();
		Image heart = new Image("frames/ui_heart_full.png");
		this.getChildren().add(new ImageView(heart));
		this.hasHeart = true;
				
	}
	
	public void setTrap() {
		fillFloor();
		Image trap = new Image("frames/floor_spikes_anim_f2.png");
		this.getChildren().add(new ImageView(trap));
		this.hasTrap = true;
	}
	
	public void setBoss() {
		fillFloor();
		Image boss = new Image("frames/ogre_idle_anim_f1.png");
		this.getChildren().add(new ImageView(boss));
		this.hasBoss = true;
	}
	
	
	
	//Getters
	public boolean hasCover() {
		return this.hasCover;
	}
	
	public boolean hasPlayer() {
		return this.hasPlayer;
	}
	
	public boolean hasMonster() {
		return this.hasMonster;
	}
	
	public boolean hasSword() {
		return this.hasSword;
	}
	
	public boolean hasHeart() {
		return this.hasHeart;
	}
	
	public boolean hasTrap() {
		return this.hasTrap;
	}
	
	public boolean hasBoss() {
		return this.hasBoss;
	}
	
	
	public boolean hasAnything() {
		//Method checks if a Tile contains any object that the player can collide with
		boolean result;
		if(this.hasPlayer == true) {
			result = true;
		}
		else if(this.hasMonster == true) {
			result = true;
		}
		
		else if(this.hasSword == true) {
			result = true;
		}
		
		else if(this.hasHeart == true) {
			result = true;
		}
		
		else if(this.hasTrap == true) {
			result = true;
		}
		
		else if(this.hasBoss == true) {
			result = true;
		}
		
		else{
			result = false;
		}
		
		return result;
	}
	
	
	
	

}
