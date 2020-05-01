import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;


public class ImageToPixel extends Component{
	File f;
	int counter=0,count=0;
	int w,h;
	RGBTOLAB obj=new RGBTOLAB();
	int[][] ab;
	int sum_R=0;
	int sum_G=0;
	int sum_B=0;

	public void printPixelARGB(int pixel) {
		int[] temp_ab=new int[2];
		
		int red = (pixel >> 16) & 0xff;
		int green = (pixel >> 8) & 0xff;
		int blue = (pixel) & 0xff;
		obj.rgb2lab(red, green, blue,temp_ab);
		ab[counter++]=temp_ab;
	}

	private void marchThroughImage(BufferedImage image) {
		int w = image.getWidth();
		int h = image.getHeight();
		System.out.println("width, height: " + w + ", " + h);
		ab=new int[w*h][2];
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				int pixel = image.getRGB(j,i);
				printPixelARGB(pixel);
				int t=counter-1;
				
			}
		}
	}

	private void printPixelRGB(int pixel){
		int red = (pixel >> 16) & 0xff;
		int green = (pixel >> 8) & 0xff;
		int blue = (pixel) & 0xff;
		sum_R=sum_R+red;
		sum_G=sum_G+green;
		sum_B=sum_B+blue;
		
	}
	public void MeanRGB(BufferedImage img,float[] array)
	{
		int w = img.getWidth();
		int h = img.getHeight();
		int mean_R,mean_G,mean_B;
		float index_no;
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				int pixel = img.getRGB(j,i);
				printPixelRGB(pixel);
			}
		}
		
		mean_R=(int) ((float) sum_R/(w*h));
		mean_G=(int) ((float) sum_G/(w*h));
		mean_B=(int) ((float) sum_B/(w*h));
		sum_R=0;
		sum_G=0;
		sum_B=0;
		System.out.println("R:"+mean_R+" G:"+mean_G+" B:"+mean_B);
		index_no=(float) mean_R/mean_G/mean_B;
		System.out.println("Index:"+index_no);
		array[0]=mean_R;
		array[1]=mean_G;
		array[2]=mean_B;
		array[3]=index_no;
	
	}
	public ImageToPixel(File f) {
		try {
			// get the BufferedImage, using the ImageIO class
			BufferedImage image = 
					ImageIO.read(f);
			marchThroughImage(image);
			    
		} catch (IOException e) {
			System.err.println(e.getMessage());
		}
	}
	
}


