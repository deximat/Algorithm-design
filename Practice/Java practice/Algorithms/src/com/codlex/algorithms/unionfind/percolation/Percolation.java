

import java.util.LinkedList;
import java.util.List;

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	
	private final boolean[][] open;
	
	// upper, down, right, left
	private final int[] neighbourI = {-1, 1, 0,  0};
	private final int[] neighbourJ = { 0, 0, 1, -1};
	
	private final int source = 0;
	private final int sink;
	
	private final WeightedQuickUnionUF percolateFind;
	
	/** Not connected to sink. */
	private final WeightedQuickUnionUF fullFind;
	
	public Percolation(int n) {
		if (n <= 0) {
			throw new IllegalArgumentException();
		}
		
		// source is 0
		this.open = new boolean[n][n];
		this.sink = n*n + 1;
		
		this.fullFind = new WeightedQuickUnionUF(n*n + 1);
		this.percolateFind = new WeightedQuickUnionUF(n*n + 2);
		
	}
	
	public void open(int i, int j) {
		checkBounds(i, j);
		this.open[i - 1][j - 1] = true;
		int fieldId = calculateId(i, j);
		for (int neighbourId : calculateNeighbours(i, j)) {
			
			this.percolateFind.union(fieldId, neighbourId);
			
			if (neighbourId != this.sink) {
				this.fullFind.union(fieldId, neighbourId);
			}
		}
	}
	
	public boolean isOpen(int i, int j)  {
		checkBounds(i, j);
		return this.open[i - 1][j - 1];
	}
	
	public boolean isFull(int i, int j) {
		checkBounds(i, j);
		return this.fullFind.connected(this.source, calculateId(i, j));
	}
	
	public boolean percolates() {
		return this.percolateFind.connected(this.source, this.sink);
	}

	private int calculateId(int i, int j) {
		if (i < 1) {
			return this.source;
		}
		
		if (i > this.open.length) {
			return this.sink;
		}
		
		return (i - 1) * this.open.length + j; 
	}
	
	private List<Integer> calculateNeighbours(int i, int j) {
		List<Integer> neighbours = new LinkedList<Integer>();
		
		for (int shiftId = 0; shiftId < this.neighbourI.length; shiftId++) {
			int newI = i + this.neighbourI[shiftId];
			int newJ = j + this.neighbourJ[shiftId];
			
			if (canConnect(newI, newJ)) {
				neighbours.add(calculateId(newI, newJ));
			}
		}
		
		return neighbours;
	}
	
	private boolean canConnect(int neighbourI, int neighbourJ) {
		if (neighbourI < 1 || neighbourI > this.open.length) {
			return true;
		}
		return neighbourJ >= 1 && neighbourJ <= this.open.length 
				&& isOpen(neighbourI, neighbourJ);
	}
	
	private void checkBounds(int i, int j) {
		if (i < 1 || i > this.open.length 
				|| j < 1 || j > this.open.length) {
			System.out.println("i:" + i + "j:" + j);
			throw new IndexOutOfBoundsException();
		}
	}
	
	public static void main(String[] args) {
		Percolation percolation = new Percolation(3);
		while (true) {
			String command = StdIn.readString();
			switch (command) {
			case "open":
				int openI = StdIn.readInt();
				int openJ = StdIn.readInt();
				percolation.open(openI, openJ);
				System.out.println("Opened");
				break;
			case "isFull":
				int fullI = StdIn.readInt();
				int fullJ = StdIn.readInt();
				System.out.println("Is full: " + percolation.isFull(fullI, fullJ));
				break;
			case "isOpen":
				int i = StdIn.readInt();
				int j = StdIn.readInt();
				System.out.println("Is open: " + percolation.isOpen(i, j));
				break;
			case "percolates":
				System.out.println("Percolates: " + percolation.percolates());
				break;
			}
		}
	}
}
