public class MarioGame{
	public static void main(String[] args) {
		Mario player = new Mario();
		Map map = new Map(player);

		map.playGame();
	}
}