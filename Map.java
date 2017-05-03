import java.util.Random;
import java.util.Scanner;

public class Map{
	final int MAX_X_POS = 100;
	final int MAX_Y_POS = 25;
	final int SPACE = 0;
	final int PLAYER = 2;
	final int ROAD = 3;
	final int GRASS = 4;
	final int MUSHROOM = 5;
	final int BRICK = 6;
	final char QUIT = 'q';

	private int[][] map;
	private Mario player;
	int cliff, mushroom_pos, brick1_pos, brick2_pos;
	boolean valid_player_position;

	public Map(Mario player){
		this.player = player;
		this.map = new int[25][100];

		Random r = new Random();

		this.cliff = 0;
	    boolean valid_player_position = true;

	    do {
	    	this.cliff = r.nextInt(100);
		    if ( (this.cliff == 0) || (this.cliff > 87) )
	    		this.valid_player_position = false;
	    	if ( (this.cliff >= (this.player.getYPos()-10) ) && (this.cliff < this.player.getYPos()) )
	    		this.valid_player_position = false;
	    } while (!valid_player_position);

		this.mushroom_pos = r.nextInt(100);
		this.brick1_pos = this.mushroom_pos-2;
		this.brick2_pos = this.mushroom_pos+2;

		for(int i = 0; i<25; i++){
			for(int j = 0; j<100; j++){
				int k = SPACE;
				if((i==22)||(i==23)||(i==24)){
					if((j>cliff) && (j<=(cliff+10))){
						k = SPACE;
					}
					else{
						switch(i){
							case 22:
								k = ROAD;
								break;
							case 23:
								k = GRASS;
								break;
							case 24:
								k = GRASS;
								break;
						}
					}
				}
				else if((i==18)||(i==19)){
					if((j>=mushroom_pos-1) && (j<=mushroom_pos+1)){
						k = MUSHROOM;
					}
					else if((j>=brick1_pos-2) && (j<=brick1_pos)){
						k = BRICK;
					}
					else if((j>=brick2_pos) && (j<=brick2_pos+2)){
						k = BRICK;
					}
					else if((i==this.player.getXPos()) && (j==this.player.getYPos())) k = PLAYER;
				}
				else if((i==this.player.getXPos()) && (j==this.player.getYPos())){
					k = PLAYER;
				}
				else if((i==this.player.getOldXPos()) && (j==this.player.getOldYPos())){
					k = SPACE;
				}
				this.map[i][j] = k;
			}
		}



		playGame();
	}

	public void playGame(){
		Scanner sc = new Scanner(System.in);
		char c = ' ';
		do{	
			updatePlayerPos(c);
			printMap();
			System.out.print("Enter move[w,a,s,d]: ");
			c = sc.next().toLowerCase().charAt(0);
		}while(c != QUIT);
	}

	public void updatePlayerPos(char c){
		switch(c){
			case ' ':
				break;
			case 'w':
				this.player.moveUp();
				break;
			case 'a': 
				this.player.moveLeft();
				break;
			case 's':
				this.player.moveDown();
				break;
			case 'd':
				this.player.moveRight();
		}

		if(this.player.getXPos() == -1 || this.player.getXPos() == 22 || this.player.getXPos() == 23 || this.player.getXPos() == 24){
			this.player.returnXPos();
		}
		if(this.player.getYPos() == -1 || this.player.getYPos() == 100){
			this.player.returnYPos();
		}
		if(this.player.getXPos() == 21){
			if(this.player.getYPos() == cliff+1) this.player.incYPos(10);
			if(this.player.getYPos() == cliff+10) this.player.decYPos(10);
		}

		for(int i = 0; i<25; i++){
			for(int j = 0; j<100; j++){
				int k = SPACE;
				if((i==22)||(i==23)||(i==24)){
					if((j>cliff) && (j<=(cliff+10))){
						k = SPACE;
					}
					else{
						switch(i){
							case 22:
								k = ROAD;
								break;
							case 23:
								k = GRASS;
								break;
							case 24:
								k = GRASS;
								break;
						}
					}
				}
				else if((i==18)||(i==19)){
					if((j>=mushroom_pos-1) && (j<=mushroom_pos+1)){
						k = MUSHROOM;
						if((this.player.getXPos()==i) && (this.player.getYPos()==j)){
							this.player.returnXPos();
							this.player.returnYPos();
						}
					}
					else if((j>=brick1_pos-2) && (j<=brick1_pos)){
						k = BRICK;
						if((this.player.getXPos()==i) && (this.player.getYPos()==j)){
							this.player.returnXPos();
							this.player.returnYPos();
						}
					}
					else if((j>=brick2_pos) && (j<=brick2_pos+2)){
						k = BRICK;
						if((this.player.getXPos()==i) && (this.player.getYPos()==j)){
							this.player.returnXPos();
							this.player.returnYPos();
						}
					}
					else if((i==this.player.getXPos()) && (j==this.player.getYPos())) k = PLAYER;
				}
				else if((i==this.player.getXPos()) && (j==this.player.getYPos())){
					k = PLAYER;
				}
				else if((i==this.player.getOldXPos()) && (j==this.player.getOldYPos())){
					k = SPACE;
				}
				this.map[i][j] = k;
			}
		}
	}

	public void printMap(){
		for (int i = 0; i<25; i++) {
			for (int j = 0; j<100; j++) {
				int k = map[i][j];
				switch(k){
					case SPACE:
		    			System.out.print(" ");
		    			break;
		    		case ROAD:
		    			System.out.print("_");
		    			break;
		    		case PLAYER:
		    			//System.out.print(ANSI_RED);
		    			System.out.print("P");
		    			break;
		    		case GRASS:
		    			System.out.print("*");
		    			break;
		    		case MUSHROOM:
		    			System.out.print("M");
		    			break;
		    		case BRICK:
		    			System.out.print("B");
		    			break;
				}
			}
			System.out.println();
		}
	}


}