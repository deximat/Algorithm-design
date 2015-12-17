import java.util.*;

class Node {

	private static int[] movesI = { 1,-1, 0, 0};
	private static int[] movesJ = { 0, 0, 1,-1};
	
	private int i;
	private int j;
	
	public Node(int i, int j) {
		this.i = i;
		this.j = j;
	}
	
	public int getI() {
		return this.i;
	}
	
	public int getJ() {
		return this.j;
	}
	
	private boolean isInRange(int i, int j, char[][] matrix) {
		return i >= 0 
			&& j >= 0
			&& i < matrix.length
			&& j < matrix[0].length;
	}
	
	public Collection<Node> neighbours(char[][] matrix) {
		List<Node> nodes = new ArrayList();
		for (int i = 0; i < movesI.length; i++) {
			int newI = this.i + movesI[i];
			int newJ = this.j + movesJ[i];
			if (isInRange(newI, newJ, matrix)) {
				nodes.add(new Node(newI, newJ));
			}
		}
		return nodes;
	}
}

class Main {

	
	private static char FILLED = '1';
	private static char EMPTY = '0';
	private static char BACKGROUND = 'B';
	
	private static int floodFill(char[][] matrix, int i, int j, char color, char wholeColor) {
		char toFillColor = matrix[i][j];
		int wholesCount = 0;
		Queue<Node> queue = new LinkedList<>();
		queue.add(new Node(i, j));
		matrix[i][j] = color;
		
		while (!queue.isEmpty()) {
			Node node = queue.poll();
			for (Node neighbour : node.neighbours(matrix)) {
				if (matrix[neighbour.getI()][neighbour.getJ()] == toFillColor) {
					matrix[neighbour.getI()][neighbour.getJ()] = color;
					queue.add(neighbour);
				} else if (matrix[neighbour.getI()][neighbour.getJ()] == EMPTY) {
					if (wholeColor != -1) {
						floodFill(matrix, neighbour.getI(), neighbour.getJ(), wholeColor, (char)-1);
						wholesCount++;
					}
				}
			}
		}
		
		return wholesCount;
	}
	
	private static String transform(String in) {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < in.length(); i++) {
			buffer.append(String.format("%4s", Integer.toBinaryString(Integer.parseInt("" + in.charAt(i), 16))).replace(" ", "0"));
		}
		return buffer.toString();
	}
	
	private static boolean isOnEdge(int i, int j, char[][] matrix) {
		return i == 0
			|| j == 0
			|| i == matrix.length - 1
			|| j == matrix[0].length - 1;
	}
	
	private static char toSymbol(int wholes) {
		switch (wholes) {
			case 0:
				return 'W';
			case 1:
				return 'A';
			case 2:
				return 'K';
			case 3:
				return 'J';
			case 4: 
				return 'S';
			case 5:
				return 'D';
		}
		return (char) -1;
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		int tc = 1;
		while (true) {
			int n = in.nextInt();
			int m = in.nextInt();
			
			if (n == 0 && m == 0) {
				return;
			}
			
			List<Node> backgrounds = new ArrayList<>();
			char[][] matrix = new char[n][m*4];
			for (int i = 0; i < n; i++) {
				String binaryLine = transform(in.next());
				for (int j = 0; j < binaryLine.length(); j++) {
					char value = binaryLine.charAt(j);
					if (isOnEdge(i, j, matrix) && value == EMPTY) {
						backgrounds.add(new Node(i, j));
					}
					matrix[i][j] = value;
				}
			}
			
			for (Node node : backgrounds) {
				if (matrix[node.getI()][node.getJ()] != BACKGROUND) {
					// fill background
					floodFill(matrix, node.getI(), node.getJ(), BACKGROUND, (char) -1);
				}
			}
			
			// for (int i = 0; i < matrix.length; i++) {
			// 	for (int j = 0; j < matrix[i].length; j++) {
			// 		System.out.print(matrix[i][j]);
			// 	}
			// 	System.out.println();
			// }

			char color = 500;
			char wholeColor = (char) -500;
			
			List<Character> symbols = new ArrayList();
			for (int i = 0; i < matrix.length; i++) {
				for (int j = 0; j < matrix[i].length; j++) {
					if (matrix[i][j] == FILLED) {
						int wholes = floodFill(matrix, i, j, color++, wholeColor--);
						symbols.add(toSymbol(wholes));
					}
				}
			}
			
			Collections.sort(symbols);
			
			System.out.print("Case " + tc + ": ");
			for (Character symbol : symbols) {
				System.out.print(symbol);
			}
			System.out.println();
			tc++;
		}
	}
}