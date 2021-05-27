package final_project;
/* Esteban Alvarado
 * CS2012
 * Section: 05 Lab: 06
 * Description: Final Project: Boss Object
 * Future Additions: 
 * - utilize the HP field to create a dynamic battle
 */
public class Boss {
//	private int HP;
	private int xPos;
	private int yPos;

	//NOT USED
//	public Boss(int HP) {
//		this.HP = HP;
//	}
	
	public Boss() {
		
	}

	// Setters
//	public void setHP(int HP) {
//		this.HP = HP;
//	}
	
	public void setXPos(int xPos) {
		this.xPos = xPos;
	}
	
	public void setYPos(int yPos) {
		this.yPos = yPos;
	}

	// Getters

//	public int getHP() {
//		return this.HP;
//	}
	
	public int getXPos() {
		return this.xPos;
	}
	
	public int getYPos() {
		return this.yPos;
	}
	
	@Override
	public String toString() {
		return "Ogre:" + "Y: " + this.yPos + "\tX: " + this.xPos;
	}
}
