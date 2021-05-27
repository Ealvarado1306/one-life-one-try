package final_project;
/* Esteban Alvarado
 * CS2012
 * Section: 05 Lab: 06
 * Description: Final Project: Monster Object
 * Note: In the future, make an "item" class to add some inheritance @EA!!
 */
public class Monster {
	private int xPos;
	private int yPos;

	public Monster() {
		
	}

	// Setters
	public void setXPos(int xPos) {
		this.xPos = xPos;
	}
	
	public void setYPos(int yPos) {
		this.yPos = yPos;
	}

	// Getters
	public int getXPos() {
		return this.xPos;
	}
	
	public int getYPos() {
		return this.yPos;
	}

	@Override
	public String toString() {
		return "Monster:" + "Y: " + this.yPos + "\tX: " + this.xPos;
	}
}
