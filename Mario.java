import java.util.Random;

public class Mario{
	private int player_pos_x, player_pos_y;
	private int old_player_pos_x, old_player_pos_y;

	public Mario(){
		Random r = new Random();
		player_pos_x = 21;
		old_player_pos_x = player_pos_x;
		player_pos_y = r.nextInt(100);
		old_player_pos_y = player_pos_y;
	}

	public void moveUp(){
		this.old_player_pos_x = player_pos_x;
		this.player_pos_x--;
	}

	public void moveDown(){
		this.old_player_pos_x = player_pos_x;
		this.player_pos_x++;
	}

	public void moveLeft(){
		this.old_player_pos_y = player_pos_y;
		this.player_pos_y--;
	}

	public void moveRight(){
		this.old_player_pos_y = player_pos_y;
		this.player_pos_y++;
	}

	public void returnXPos(){
		this.player_pos_x = this.old_player_pos_x;
	}

	public void returnYPos(){
		this.player_pos_y = this.old_player_pos_y;
	}

	public int getXPos(){
		return this.player_pos_x;
	}

	public int getYPos(){
		return this.player_pos_y;
	}

	public int getOldXPos(){
		return this.old_player_pos_x;
	}

	public int getOldYPos(){
		return this.old_player_pos_y;
	}

	public void incYPos(int x){
		this.player_pos_y+=x;
	}

	public void decYPos(int x){
		this.player_pos_y-=x;
	}
}