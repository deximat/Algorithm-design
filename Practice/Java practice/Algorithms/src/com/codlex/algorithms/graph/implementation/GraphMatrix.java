package com.codlex.algorithms.graph.implementation;

import java.util.Iterator;
import java.util.NoSuchElementException;

import com.codlex.algorithms.graph.Graph;

public class GraphMatrix implements Graph {
	
	private int edgesCount;
	private final int verticesCount;
	private final boolean[][] adjecencyMatrix;
	
	public GraphMatrix(int numberOfVertices) {
		this.verticesCount = numberOfVertices;
		this.adjecencyMatrix = new boolean[this.verticesCount][this.verticesCount];
	}
	
	@Override
	public int getVerticesCount() {
		return this.verticesCount;
	}

	@Override
	public int getEdgesCount() {
		return this.edgesCount;
	}

	@Override
	public void addEdge(int vertex1, int vertex2) {
		if (!this.adjecencyMatrix[vertex1][vertex2]) {
			this.edgesCount++;
		}
		
		this.adjecencyMatrix[vertex1][vertex2] = true;
		this.adjecencyMatrix[vertex2][vertex1] = true;
	}

	@Override
	public Iterable<Integer> adjecentTo(int vertex) {
		return new VertexIterator(this.adjecencyMatrix[vertex]);
	}
	
	private class VertexIterator implements Iterable<Integer>, Iterator<Integer> {
		
		private boolean[] adjecency;
		private int current = 0;
		
		public VertexIterator(boolean[] adjecency) {
			this.adjecency = adjecency;
		}
		
		@Override
		public boolean hasNext() {
			
			while (this.current < this.adjecency.length) {
				if (this.adjecency[this.current]) {
					return true;
				}
				this.current++;
			}
			
			return false;
		}

		@Override
		public Integer next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			
			int result = this.current;
			
			this.current++;
			
			return result;
		}

		@Override
		public Iterator<Integer> iterator() {
			return this;
		}
	}

}
