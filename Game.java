/****************************************************************
 *
 * This is patterned after the super mario game.  The game area is map created using Java's
 * 2D array. The dimension of game area is 25 rows x 100 columns.
 * 
 * -Mario's position is at row 21 (x-position).  The value for column number (y-position)
 * is provided as a command line argument.
 * 
 * Printing:
 * -Print Mario using character 'P'.  
 * -Score is on row 5 printed as "Score: ", followed by the player's score.
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
 * Note: This is lab exercise on Java Basics.  The scope is 
 * only from data types, control structures, java.util.Scanner (System.in), 
 * try-catch block, command line arguments, and Arrays.
 *
 * @author Mylah Rystie U. Anacleto
 * @date 2017-02-06 14:30
 * 
 ***************************************************************/

import java.util.Scanner;
import java.util.Random;

public class Game {
   	static final String ANSI_RED = "\u001B[31m";

    public static void main(String[] args){

    	try{
	    	int player_position_y = Integer.parseInt(args[0]);

	    	Scanner sc = new Scanner(System.in);
	    	char c = ' ';

	    	final int SPACE = 0;
	    	final int PLAYER = 2;
	    	final int ROAD = 3;
	    	final int GRASS = 4;
	    	final int MUSHROOM = 5;
	    	final int BRICK = 6;

	    	final char QUIT = 'q';

	    	int score = 0;

	    	// randomly set a cliff
	    	// do this only once
	    	Random r = new Random();
	    	int cliff = 0;
	    	boolean valid_player_position = true;

	    	//----------- start of optional portion of code-----------------------
	    	do {
	    		cliff = r.nextInt(100);
		    	if ( (cliff == 0) || (cliff > 87) )
	    			valid_player_position = false;
	    		if ( (cliff >= (player_position_y-10) ) && (cliff < player_position_y) )
	    			valid_player_position = false;
	    	} while (!valid_player_position);
	    	//----------- end of optional portion of code-----------------------

	    	int mushroom_position = r.nextInt(100);
	    	int brick1_position = mushroom_position - 2;
	    	int brick2_position = mushroom_position + 2;

	    	// initialize player position
	    	int player_position_x = 21;
	    	int old_player_position_x = 21;
	    	int old_player_position_y = player_position_y;
	    	
	    	do {

	    		switch (c) {
	    			case ' ': break;
	    			case 'w': player_position_x--; break;
	    			case 'a': player_position_y--; break;
	    			case 's': player_position_x++; break;
	    			case 'd': player_position_y++; break;
	    		}

	    	//----------- start of optional portion of code-----------------------

	    		// do not pass through grass
	    		if ((player_position_x == -1) || (player_position_x == 22) || (player_position_x == 23) || (player_position_x == 24) ){
	  					player_position_x = old_player_position_x;
	    		}
	    		if ((player_position_y == -1) || (player_position_y == 100) )
	    			player_position_y = old_player_position_y;

	    		if (player_position_x == 21){
	    			if (player_position_y == cliff+1) 
	    				player_position_y += 10;
	    			if (player_position_y == cliff+10) 
	    				player_position_y -= 10;
	    		}

	    	//----------- end of optional portion of code-----------------------

	    	// Fill array with characters for player, road, grass, mushroom, brick, cliff 
		    	int[][] map = new int[25][100];
		    	
		    	for(int i=0; i<25; i++){
		    		for (int j=0; j<100; j++){
		    			int k = SPACE;
		    			if ((i==22) || (i==23) || (i==24)) {
		    				//create a cliff 10 spaces apart
		    				if ( (j>cliff) && (j<=(cliff+10)) )  {
		    					k = SPACE;
		    				} else {
		    					switch (i){
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

		    			} else if ((i==18) || (i==19)) {

		    				if ((j >= mushroom_position-1) && (j <= mushroom_position+1)){
								k = MUSHROOM;
			    				if ((player_position_x == i) && (player_position_y == j)) {  
			    					player_position_x = old_player_position_x;
			    					player_position_y = old_player_position_y;
			    					score++;
			    				}

		    				} else if ((j >= brick1_position-2) && (j <= brick1_position)) {
		    					k = BRICK;
			    				if ((player_position_x == i) && (player_position_y == j)) {  
			    					player_position_x = old_player_position_x;
			    					player_position_y = old_player_position_y;
			    				}


		    				}  else if ((j >= brick2_position) && (j <= brick2_position+2)) {
		    					k = BRICK;
			    				if ((player_position_x == i) && (player_position_y == j)) {  
			    					player_position_x = old_player_position_x;
			    					player_position_y = old_player_position_y;
			    				}
		    				} else if ((i==player_position_x) && (j==player_position_y)) {
		    					k = PLAYER;
		    				}

			    		} else if ((i==player_position_x) && (j==player_position_y)) {
		    				k = PLAYER;
		    			} else if ((i==old_player_position_x) && (j==old_player_position_y)) {
		    				k = SPACE;
		    			}

		    			map[i][j] = k;

		    		}
		    	}

		    	// print map
		    	// according to array contents for player, road, grass, mushroom, brick, cliff 
		    	for(int i=0; i<25; i++){
		    		if (i == 5){
		    			System.out.println("Score: " + score);
		    			continue;
		    		}
		    		for (int j=0; j<100; j++){
		    			int k = map[i][j];
		    			//System.out.print(k);	
		    			switch(k) {
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

		    	// remember current position so caan easily go back 
		    	// in case succeeding player position is invalid
	    		old_player_position_x = player_position_x;
	    		old_player_position_y = player_position_y;

	    		// Get next move
	    		// get only first character, make it case insensitive
		    	System.out.println("Enter your move: ");
		    	c = sc.next().toLowerCase().charAt(0);

	    	} while (c != QUIT);

    	} catch (Exception e){
    		System.out.println("Something went wrong!");
    	}

    } 


}