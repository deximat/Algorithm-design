import java.awt.Color;
import java.awt.image.BufferedImage;


public class Conncetions {
	
	int[][]   vsum; 
	int[][]   hsum; 
	int[][]  mdsum; 
	int[][] mmdsum;


	public boolean connected(int x, int y, int x1, int y1){
		if ( x < 0 || x >= vsum.length || y < 0 || y >= vsum[0].length || 
			 x1 < 0 || x1 >= vsum.length || y1 < 0 || y1 >= vsum[0].length ){
			return false;
		}
		int wanted = -1;
		int numberOfPixels = -2;
		if ( x == x1 ) {
			// na istoj vertikali
			wanted = Math.abs(y - y1);
			numberOfPixels = Math.abs(vsum[x][y] - vsum[x1][y1]);
		} else if ( y == y1 ) {
			// na istoj horizontali
			wanted = Math.abs(x - x1);
			numberOfPixels = Math.abs(hsum[x][y] - hsum[x1][y1]);
		} else if ( x + y1 == y + x1 ) {
			// glavna dijagonala
			wanted = Math.abs(x - x1);
			numberOfPixels = Math.abs(mdsum[x][y] - mdsum[x1][y1]);
		} else if (x + y == y1 + x1 ) {
			// sporedna dijagonala
			wanted = Math.abs(x - x1);
			numberOfPixels = Math.abs(mmdsum[x][y] - mmdsum[x1][y1]);
		}
		
		return wanted == numberOfPixels;
		
	}
	

	
	public Conncetions(BufferedImage image ) {
		vsum = new int[image.getWidth()][image.getHeight()];  
		hsum = new int[image.getWidth()][image.getHeight()];  
		mdsum = new int[image.getWidth()][image.getHeight()]; 
		mmdsum = new int[image.getWidth()][image.getHeight()];
		 
		// vertical sum
		for(int x = 0; x < image.getWidth(); x++) 
			vsum[x][0] = getValueAt(image, x, 0);
		for(int y = 1; y < image.getHeight(); y++) {
			for(int x = 0; x < image.getWidth(); x++) {
				vsum[x][y] = getValueAt(image, x, y) + vsum[x][y-1];
			}
		}
		// horizontal sum
		for(int y = 0; y < image.getHeight(); y++) 
			hsum[0][y] = getValueAt(image, 0, y);
		for(int y = 0; y < image.getHeight(); y++) {
			for(int x = 1; x < image.getWidth(); x++) {
				hsum[x][y] = getValueAt(image, x, y) + hsum[x-1][y];
			}
		}
		// main diagonal sum
		for(int y = 0; y < image.getHeight(); y++) {
			for(int x = 0; x < image.getWidth(); x++) {
				mdsum[x][y] = getValueAt(image, x, y) + getValueAtMatrix(mdsum, x-1,y-1);
			}
		}
		// minor diagonal sum
		for(int y = 0; y < image.getHeight(); y++) {
			for(int x = image.getWidth() - 1 ; x >= 0 ; x--) {
				mmdsum[x][y] = getValueAt(image, x, y) + getValueAtMatrix(mmdsum, x+1,y-1);
			}
		}
	}
	
	private static int getValueAt(BufferedImage image, int x, int y) {
		if ( x < 0 || x >= image.getWidth() || y < 0 || y >= image.getHeight()){
			return 0;
		}
		return image.getRGB(x, y) == Color.BLACK.getRGB() ? 1 : 0;
	}
	
	private static int getValueAtMatrix(int[][] matrix, int x, int y) {
		if ( x < 0 || x >= matrix.length || y < 0 || y >= matrix[0].length){
			return 0;
		}
		
		return matrix[x][y];
	}


}
