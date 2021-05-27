package final_project;

import java.io.File;
import java.nio.file.Paths;

import javafx.scene.Node;
import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
/* Esteban Alvarado
 * CS2012
 * Section: 05 Lab: 06
 * Description: Final Project: Player Object
 * Note: In the future, make an "item" class to add some inheritance @EA!!
 */
public class Player extends Node{
	private String name;
	private int HP;
	private int xPos;
	private int yPos;

	public Player(int HP) {
		this.HP = HP;
	}

	// Setters
	public void setName(String name) {
		this.name = name;
	}

	public void setHP(int HP) {
		this.HP = HP;
	}
	
	public void setXPos(int xPos) {
		this.xPos = xPos;
	}
	
	public void setYPos(int yPos) {
		this.yPos = yPos;
	}

	// Getters
	public String getName() {
		return this.name;
	}

	public int getHP() {
		return this.HP;
	}
	
	public int getXPos() {
		return this.xPos;
	}
	
	public int getYPos() {
		return this.yPos;
	}

	// Spells: All consume HP as a resource
	public void castFirebolt() { // Insant kills boss
		this.HP = this.HP - 5;
		if(this.HP < 0) {this.HP = 0;}
		AudioClip sound = new AudioClip(Paths.get("src/frames/fire2.wav").toUri().toString());
		sound.play();
	}
	
	public void castRadiance() { // removes fog from 1 tile away from player
		this.HP = this.HP - 10;
		if(this.HP < 0) {this.HP = 0;}
		AudioClip sound = new AudioClip(Paths.get("src/frames/shield1.wav").toUri().toString());
		sound.play();
	}

	

}
