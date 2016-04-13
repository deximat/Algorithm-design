import java.util.*;

class Graph {

	private final List<List<Integer>> neighbours;
	private final char[] names;
	
	public Graph (char[] names) {
		this.names = names;
		this.neighbours = new ArrayList<List<Integer>>();
		for (int i = 0; i < names.length; i++) {
			this.neighbours.add(new ArrayList<Integer>());
		}
	}
	
	private int find(char name) {
		for (int i = 0; i < names.length; i++) {
			if (name == names[i]) {
				return i;
			}
		}
		return -1; 
	}
	
	public void addEdge(char from, char to) {
		addEdge(find(from), find(to));
	}
	
	public void addEdge(int from, int to) {
		this.neighbours.get(from).add(to);
	}
	
	public boolean isBefore(int fromNode, String nodes) {
		// System.out.println("adding " + names[fromNode] + " so far:  " + nodes);
		boolean[] visited = new boolean[names.length];
		return recursiveDfs(visited, fromNode, nodes);
	}
	
	private boolean recursiveDfs(boolean[] visited, int node, String nodes) {
		for (Integer neighbour : this.neighbours.get(node)) {
			if (!visited[neighbour]) {
				visited[neighbour] = true;
				char name = names[neighbour];
				if (nodes.indexOf(name) != -1) {
					// System.out.println("canceling " + name + " visited");
					return false;
				}
				if (!recursiveDfs(visited, neighbour, nodes)) {
					return false;
				}
			}
		}
		return true;
	}
}

class Main {

	private static char[] readLetters(Scanner in) {
		String lettersString = in.nextLine() + " ";
		char[] letters = new char[lettersString.length() / 2];
		
		for (int i = 0; i < lettersString.length(); i+=2) {
			letters[i/2] = lettersString.charAt(i);
		}
		
		return letters;
	}
	
	private static void findRecursive(String sofar, char[] letters, Graph graph, List<String> solutions) {
		if (sofar.length() == letters.length * 2) {
			solutions.add(sofar.substring(0, sofar.length()-1));
			return;
		}
		
		for (int i = 0; i < letters.length; i++) {
			
			if (!(sofar.indexOf(letters[i]) != -1)
				&& graph.isBefore(i, sofar)) {
					findRecursive(sofar + letters[i] + " ", letters, graph, solutions);
			}
		}
	}
	
	private static int find(char toFind, char[] letters){
		for (int i = 0; i < letters.length; i++) {
			if (letters[i] == toFind) {
				return i;
			}
		}
		return -1;
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		
		int tc = in.nextInt();
		in.nextLine(); // behind number
		for (int t = 0; t < tc; t++) {
			in.nextLine(); // empty line
			
			final char[] letters = readLetters(in);
			
			Graph graph = new Graph(letters);
			
			String[] constraints = in.nextLine().split(" ");
			
			for (String constraint : constraints) {
				String[] splitted = constraint.split("<");
				graph.addEdge(splitted[0].charAt(0), splitted[1].charAt(0));
			}
			
			List<String> solutions = new ArrayList<String>();
			
			findRecursive("", letters, graph, solutions);
			
			Collections.sort(solutions);
			
			if (solutions.isEmpty()) {
				System.out.println("NO");
			} else {
				for (String solution : solutions) {
					System.out.println(solution);
				}
			}
			if (tc - 1 != t) {
				System.out.println();
			}
		}
	}
}