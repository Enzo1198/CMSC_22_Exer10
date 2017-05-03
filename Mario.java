/****************************************************************
 *
 * This is patterned after the super mario game.  The game area is map created using Java's
 * 2D array. The dimension of game area is 25 rows x 100 columns.
 * 
 * -Mario's position is at row 21 (x-position).  The value for column number (y-position)
 * is randomized.
 * 
 * Printing:
 * -Print Mario using character 'P'.  
 * -Decorate grass with underscore ("_") at row 22, asterisk ("*") at row 23 and 24.
 * -Cliff is randomly set at the beginning (random integer between 1 and 88, followed by 10 spaces.
 * -Mushroom is randomly placed at row 18 and 19, with letters "B" for brick, "M" for mushroom. '
 *  Mushroom occupies 3 spaces (i.e. three M's). There are 3 bricks on each side of the mushroom 
 *  (i.e. three B's). 
 *
 * Movement:
 * -Using java.util.Scanner, accepts 'W'(up),'A'(left),'S'(right), and 'D'(down) 
 * as input and moves Mario accordingly. If input is 'Q', game terminates.
 *
 * Check Mario's position:
 * -(optional)If Mario's position reaches brick, Mario does not move.
 * -If Mario's position hits a mushroom, score increases by 1.
 * -(optional)If Mario's position hits a mushroom, Mario does not move.
 * -(optional)If Mario's position reaches map boundaries, Mario does not move.
 * -(optional) If Mario's position reaches cliff boundaries, Mario's position increments/decrements
 * by 10 positions (i.e. jumps).  
 *
 * @author Lorenz Matthew B. Afable
 * @date 2017-05-04 3:12
 * 
 ***************************************************************/
import java.util.Random;
import java.io.Serializable;

public class Mario implements Serializable{ //Mario implements serializable
	private int player_pos_x, player_pos_y;
	private int old_player_pos_x, old_player_pos_y;

	public Mario(){ //initializing positions
		Random r = new Random();
		player_pos_x = 21;
		old_player_pos_x = player_pos_x;
		player_pos_y = r.nextInt(100);
		old_player_pos_y = player_pos_y;
	}

	public void moveUp(){ //moves the player up while keeping track of previous position
		this.old_player_pos_x = player_pos_x;
		this.player_pos_x--;
	}

	public void moveDown(){ //moves the player down while keeping track of previous position
		this.old_player_pos_x = player_pos_x;
		this.player_pos_x++;
	}

	public void moveLeft(){ //moves the player left while keeping track of previous position
		this.old_player_pos_y = player_pos_y;
		this.player_pos_y--;
	}

	public void moveRight(){ //moves the player right while keeping track of previous position
		this.old_player_pos_y = player_pos_y;
		this.player_pos_y++;
	}

	public void returnXPos(){ //method for returning to the previous x position
		this.player_pos_x = this.old_player_pos_x;
	}

	public void returnYPos(){ //method for returning to the previous y position
		this.player_pos_y = this.old_player_pos_y;
	}

	public int getXPos(){ //getter for the current x position
		return this.player_pos_x;
	}

	public int getYPos(){ //getter for the current y position
		return this.player_pos_y;
	}

	public int getOldXPos(){ //getter for the previous x position
		return this.old_player_pos_x;
	}

	public int getOldYPos(){ //getter for the previouse y position
		return this.old_player_pos_y;
	}

	public void incYPos(int x){ //setter for the x position
		this.player_pos_y+=x;
	}

	public void decYPos(int x){ //setter for the y position
		this.player_pos_y-=x;
	}
}