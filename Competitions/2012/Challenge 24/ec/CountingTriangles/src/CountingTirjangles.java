import java.awt.Color;
import java.awt.image.BufferedImage;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class CountingTirjangles extends JPanel {

	static BufferedImage image;
	public static void main(String[] args) throws Exception {
		image = ImageIO.read(new URL("file:/home/deximat/Downloads/PROBLEMSET/input/A/9.png"));
		Conncetions cn = new Conncetions(image);
		int brojac = 0;

		for (int x1 = 0; x1 < image.getWidth(); x1++) {
			for (int y1 = 0; y1 < image.getHeight(); y1++) {
				if (!crnja(x1, y1)) {
					continue;
				}
				int i = 1;
				// po horizontali
				while (insideImage(image, x1 + i, y1) && crnja(x1 + i, y1)) {
					brojac += uzmiTrece(cn, x1, y1, x1 + i, y1);
					i++;
				}
				i = 1;
				// po vertikali
				while (insideImage(image, x1, y1 + i) && crnja(x1, y1+i)) {
					brojac += uzmiTrece(cn, x1, y1, x1, y1 + i);
					i++;
				}				
//				i = 0;
//				// po dijagonali glavnoj
//				while (insideImage(image, x1 + i, y1 + i)) {
//					brojac += uzmiTrece(cn, x1, y1, x1 + i, y1 + i);
//					i++;
//				}	
//				i = 0;
//				// po dijagonali sporednoj
//				while (insideImage(image, x1 - i, y1 + i)) {
//					brojac += uzmiTrece(cn, x1, y1, x1 - i, y1 + i);
//					i++;
//				}	

			}
		}

		System.out.println("Ima ovoliko trouglova: " + brojac);

	}

	private static int uzmiTrece(Conncetions cn, int x1, int y1, int x2, int y2) {
		int brojac = 0;
		int x3, y3;
		
		if ( x1 == x2) {
			// vertikalna
			int dist = Math.abs(y1 - y2) + 1;
			if ( dist % 2 == 1) {
				// prva
				x3 = x1 - dist / 2;
				y3 = y1 + dist / 2;
				if (isTriangle(cn, x1, y1, x2, y2, x3, y3)) brojac++;
				// druga
				x3 = x1 + dist / 2;
				y3 = y1 + dist / 2;
				if (isTriangle(cn, x1, y1, x2, y2, x3, y3)) brojac++;
			}
		} else if ( y1 == y2 ) {
			// horizontalna
			// dole levo
			x3 = x1; 
			y3 = y1 + Math.abs(x1-x2);
			if (isTriangle(cn, x1, y1, x2, y2, x3, y3)) brojac++;
			// gore levo
			x3 = x1; 
			y3 = y1 - Math.abs(x1-x2);
			if (isTriangle(cn, x1, y1, x2, y2, x3, y3)) brojac++;
			// dole desno
			x3 = x2; 
			y3 = y1 + Math.abs(x1-x2);
			if (isTriangle(cn, x1, y1, x2, y2, x3, y3)) brojac++;
			// gore desno
			x3 = x2; 
			y3 = y1 - Math.abs(x1-x2);
			if (isTriangle(cn, x1, y1, x2, y2, x3, y3)) brojac++;
			
			int dist = Math.abs(x1 - x2) + 1;
			if ( dist % 2 == 1) {
				// prva
				x3 = x1 + dist / 2;
				y3 = y1 + dist / 2;
				if (isTriangle(cn, x1, y1, x2, y2, x3, y3)) brojac++;
				// druga
				x3 = x1 + dist / 2;
				y3 = y1 - dist / 2;
				if (isTriangle(cn, x1, y1, x2, y2, x3, y3)) brojac++;
			}
		}

		return brojac;
	}
	
	private static boolean insideImage(BufferedImage image, int x, int y) {
		if ( x < 0 || x >= image.getWidth() || y < 0 || y >= image.getHeight()){
			return false;
		}
		return true;
	}

	private static boolean isTriangle(Conncetions cn, int x1, int y1, int x2, int y2, int x3, int y3) {
		 if (crnja(x1,y1) && crnja(x2, y2) && crnja(x3, y3) &&
		     cn.connected(x1, y1, x2, y2) &&	
			 cn.connected(x2, y2, x3, y3) &&
			 cn.connected(x1, y1, x3, y3) )
			 {
			 	return true;
			 };
		return false;
	}
	
	private static boolean crnja(int x, int y) {
		if ( x < 0 || x >= image.getWidth() || y < 0 || y >= image.getHeight()){
			return false;
		}
		return image.getRGB(x, y) == Color.BLACK.getRGB();
	}
}
