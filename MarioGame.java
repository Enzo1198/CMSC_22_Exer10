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

import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;
import java.util.Scanner;

public class MarioGame{
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int choice=3;
		Map map;
		Mario player;
		String playertxt = "player.txt"; // file names
		String maptxt = "map.txt";		 //
		do{	
			System.out.println("---------------------------");
			System.out.println("MARIO GAME");
			System.out.println("---------------------------");
			System.out.println("[1] New Game");
			System.out.println("[2] Load Game");
			System.out.println("[0] Exit");
			System.out.print("Choice: ");
			choice = sc.nextInt();
			switch(choice){
				/*
				If player selects new game, a new map and player will be generated
				*/
				case 1:
					player = new Mario();
					map = new Map(player);
					map.playGame();

					/*After playing the game(when the player types in 'quit'), the game is automatically saved*/
					try{
						ObjectOutputStream mapObject = new ObjectOutputStream(new FileOutputStream(maptxt));
						mapObject.writeObject(map);
						ObjectOutputStream playerObject = new ObjectOutputStream(new FileOutputStream(playertxt));
						playerObject.writeObject(player);

						mapObject.close();
						playerObject.close();
					}
					catch(Exception e){
						System.out.println(e.toString());
					}
					break;
				case 2:
					/*If player chooses to load a game, the game tries to load if a game has been saved before*/
					try{
						ObjectInputStream loadPlayer = new ObjectInputStream(new FileInputStream(playertxt));
						player = (Mario) loadPlayer.readObject();
						ObjectInputStream loadMap = new ObjectInputStream(new FileInputStream(maptxt));
						map = (Map) loadMap.readObject();

						loadPlayer.close();
						loadMap.close();

						map.playGame();

						/*Saves the game as soon as it ends*/
						try{
							ObjectOutputStream mapObject = new ObjectOutputStream(new FileOutputStream(maptxt));
							mapObject.writeObject(map);
							ObjectOutputStream playerObject = new ObjectOutputStream(new FileOutputStream(playertxt));
							playerObject.writeObject(player);

							mapObject.close();
							playerObject.close();
						}
						catch(Exception e){
							System.out.println(e.toString());
						}
					}
					catch(Exception e){
						System.out.println(e.toString());
					}
					break;
			}
		}while(choice != 0);
	}
}