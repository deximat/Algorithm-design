

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
	private class Coordinate {
		private int i;
		private int j;
		
		Coordinate(int i, int j) {
			this.i = i;
			this.j = j;
		}
	}
	
	private class RandomCoordinate {
		private Coordinate[] elements;
		private int currentIndex;
		
		private RandomCoordinate(int iSize, int jSize) {
			this.elements = new Coordinate[iSize * jSize];
			int currentPosition = 0;
			for (int i = 1; i <= iSize; i++) {
				for (int j = 1; j <= jSize; j++) {
					this.elements[currentPosition++] = new Coordinate(i,j);
				}
			}
			StdRandom.shuffle(this.elements);
		}
		
		public Coordinate next() {
			return this.elements[this.currentIndex++];
		}
	}

	private static final double CONFIDENCE = 1.96;
	
	private final double mean;
	private final double stdDev;
	private final double confidenceHi;

	private double confidenceLo;
	
	
	
	public PercolationStats(int n, int t) {
		if (n <= 0 || t <= 0) {
			throw new IllegalArgumentException();
		}
		
		double[] results = new double[t];
		for (int test = 0; test < t; test++) {
			results[test] = doTheTest(n);
		}
		// math stuff...
		this.mean = calculateMean(results, t);
		this.stdDev = calculateStdDev(results, t);

		this.confidenceLo = calculateConfidence(t, true);
		this.confidenceHi = calculateConfidence(t, false);
	}
	
	
	private double calculateConfidence(int t, boolean isLow) {
		double error = (this.stdDev * CONFIDENCE) / Math.sqrt(t);
		if (isLow) {
			return this.mean - error;
		} else {
			return this.mean + error;
		}
	}


	private double calculateStdDev(double[] results, int t) {
		return StdStats.stddev(results);
	}


	private double calculateMean(double[] results, int t) {
		return StdStats.mean(results);
	}


	private double doTheTest(int n) {
	   int openFields = 0;
	   Percolation percolation = new Percolation(n);
	   RandomCoordinate randomCoordinate = new RandomCoordinate(n, n);
	   while (!percolation.percolates()) {
		   Coordinate coordinate = randomCoordinate.next();
		   percolation.open(coordinate.i, coordinate.j);
		   openFields++;
	   }
	   
	   return (double) openFields / (n*n);
	}


	public double mean() {
		return this.mean;
	}
   
	public double stddev() {
		return this.stdDev;
	}
   
	public double confidenceLo() {
		return this.confidenceLo;
	}
   
	public double confidenceHi() {
		return this.confidenceHi;
	}
	
	public static void main(String[] args) {
		
		PercolationStats percolationStats = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
		StdOut.printf("mean\t= %f\n", percolationStats.mean());
		StdOut.printf("stddev\t= %f\n", percolationStats.stddev());
		StdOut.printf("95%% confidence interval\t= %f, %f\n", percolationStats.confidenceLo(), percolationStats.confidenceHi());

	}
}
