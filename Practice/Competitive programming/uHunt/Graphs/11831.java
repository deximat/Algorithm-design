import java.util.*;

class Robot {

	
	private final int[] movesI = {-1, 0, 1, 0};
	private final int[] movesJ = { 0, 1, 0,-1};
	
	private final char[] orientations = {'N', 'L', 'S', 'O'};
	private final char TURN_RIGHT = 'D';
	private final char TURN_LEFT = 'E';
	private final char MOVE_FORWARD = 'F';
	private final char OBSTICLE = '#';
	private final char STICKER = '*';
	
	private int i;
	private int j;
	private int orientationIndex;
	private char[][] matrix;
	private int score;
	
	public Robot(int i, int j, char orientation, char[][] matrix) {
		this.i = i;
		this.j = j;
		this.orientationIndex = find(orientation);
		this.matrix = matrix;
	}

	private int find(char orientation) {
		for (int i = 0; i < orientations.length; i++) {
			if (orientations[i] == orientation) {
				return i;
			}
		}
		return -1;
	}
	
	private boolean isInRange(int i, int j) {
		return i >= 0 
			&& j >= 0 
			&& i < matrix.length 
			&& j < matrix[0].length;
	}
	
	private boolean isObsticle(int i, int j) {
		return this.matrix[i][j] == OBSTICLE;
	}
	
	private void moveForward() {
		int newI = this.i + movesI[orientationIndex];
		int newJ = this.j + movesJ[orientationIndex];
		
		if (isInRange(newI, newJ) && !isObsticle(newI, newJ)) {
			this.i = newI;
			this.j = newJ;
		}
		
	}
	
	private void changeOrientation(int turns) {
		int newOrientation = this.orientationIndex + turns;
		if (newOrientation >= this.orientations.length) {
			newOrientation -= this.orientations.length;
		} else if (newOrientation < 0) {
			newOrientation += this.orientations.length;
		}
		this.orientationIndex = newOrientation;
	}
	
	private void claim() {
		char toClaim = this.matrix[this.i][this.j];
		if (toClaim == STICKER) {
			this.score++;
		}
		this.matrix[this.i][this.j] = '.';
	}
	
	public void process(char order) {
		switch (order) {
			case TURN_RIGHT:
				changeOrientation(1);
				break;
			case TURN_LEFT:
				changeOrientation(-1);
				break;
			case MOVE_FORWARD:
				moveForward();
				break;
		}
		claim();
	}
	
	public int getScore() {
		return this.score;
	}
}


class Main {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		while (true) {
			int n = in.nextInt();
			int m = in.nextInt();
			int s = in.nextInt();
			if (n == 0 && m == 0 && s == 0) {
				return;
			}
			
			Robot robot = null;
			char[][] matrix = new char[n][m];
			for (int i = 0; i < n; i++) {
				String line = in.next();
				for (int j = 0; j < m; j++) {
					char symbol = line.charAt(j);
					if (symbol == 'N' 
						|| symbol == 'S'
						|| symbol == 'L'
						|| symbol == 'O') {
						// this is robot position
						robot = new Robot(i, j, symbol, matrix);
						matrix[i][j] = '.';
					} else {
						matrix[i][j] = line.charAt(j);
					}
				}
			}
			
			String orders = in.next();
			for (int i = 0; i < s; i++) {
				char order = orders.charAt(i);
				robot.process(order);
			}
			
			System.out.println(robot.getScore());
		}
	}
}