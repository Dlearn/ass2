package ass2;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class image2 {
	
	public static void printPixelARGB(int pixel) {
	    //int alpha = (pixel >> 24) & 0xff;
	    int red = (pixel >> 16) & 0xff;
	    int green = (pixel >> 8) & 0xff;
	    int blue = (pixel) & 0xff;
	    //System.out.println("argb: " + alpha + ", " + red + ", " + green + ", " + blue);
	    System.out.println(red + ", " + green + ", " + blue);
	}
	
	public BufferedImage getImage(String s){
		BufferedImage image = null;
		try{
			image = ImageIO.read(this.getClass().getResource(s));
		}
		catch(Exception e){
			System.err.println(e.getMessage());
		}
		return image;
	}
	
	public static void write(String s, BufferedImage image){
		try{
			File f = new File(s);
			ImageIO.write(image, "PNG", f);
		}
		catch(Exception e){
			System.err.println(e.getMessage());
		}
	}
	
	public static void createImage(){
		BufferedImage imout = new BufferedImage(2, 2, BufferedImage.TYPE_INT_RGB);
		int w = imout.getWidth();
		int h = imout.getHeight();
		System.out.println("width, height: " + w + ", " + h);
		Color blue = Color.blue;
		Color yellow = Color.yellow;
		
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {		        
		        if(i==1 && j==0) imout.setRGB(j, i, yellow.getRGB());
		        else imout.setRGB(j, i, blue.getRGB());
		    }
		}
		
		String s = "BBYB.png";
		write(s, imout);
	}
	
	public static void main(String[] foo) {
		Simplex sim = new Simplex();
		int lambda=10;

		image2 obj = new image2();
		String s = "BBYB.png";
		BufferedImage image = obj.getImage(s);
		int w = image.getWidth();
		int h = image.getHeight();
		//System.out.println("width, height: " + w + ", " + h);
		double[][] A = new double[7*w*h][6*w*h];
		for(int i=0;i<7*w*h;i++) //initialize all A to be 0
			for(int j=0;j<6*w*h;j++)
				A[i][j]=0;
		for(int i=0;i<6*w*h;i++){ //initialize all diagonal to 1
			A[i][i] = 1;
		}
		
		double[] b = new double[7*w*h]; //initialize all b to be 0
		for(int i=0;i<b.length;i++) b[i]=0;
		
		double[] c = new double[6*w*h]; //initialize all c to be 0
		for(int i=0;i<c.length;i++) c[i]=0;
		
		double[][] fds = new double[h][w];
		double[][] fdt = new double[h][w];
		
		
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				//System.out.println("x,y: " + j + ", " + i);
		        int count = gridMethods.getCount(j, i, w, h);
		        
		        int pixel = image.getRGB(j, i);
		        //printPixelARGB(pixel);
		        int red = (pixel >> 16) & 0xff;
		        int green = (pixel >> 8) & 0xff;
		        int blue = (pixel) & 0xff;
			    /*fds[j][i] = simplexMethods.distanceFrom((double)red, (double)green, (double)blue, 
			    		0.0, 0.0, 255.0);*/
		        b[6*count] = simplexMethods.distanceFrom((double)red, (double)green, (double)blue, 
			    		0.0, 0.0, 255.0);
			    
			    /*fdt[j][i] = simplexMethods.distanceFrom((double)red, (double)green, (double)blue, 
			    		255.0, 255.0, 0.0);*/
		        b[6*count+1] = simplexMethods.distanceFrom((double)red, (double)green, (double)blue, 
			    		255.0, 255.0, 0.0);
			    if (gridMethods.isValidUp(count, w, h)){
			    	System.out.println(count + " is more than or equal to" + w);
			    	b[6*count+2] = lambda;
			    }
			    if (gridMethods.isValidDown(count, w, h)){
			    	b[6*count+3] = lambda;
			    }
			    if (gridMethods.isValidLeft(count, w)){
			    	b[6*count+4] = lambda;
			    }
			    if (gridMethods.isValidRight(count, w)){
			    	b[6*count+5] = lambda;
			    }
			    //A[j][i] = 
		    }
		}
		/*System.out.println("FDS:");
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				System.out.print(fds[j][i]+" ");
			}
			System.out.println("");
		}
		
		System.out.println("FDT:");
		for (int i = 0; i < h; i++) {
			for (int j = 0; j < w; j++) {
				System.out.print(fdt[j][i]+" ");
			}
			System.out.println("");
		}*/
		System.out.println("b vector:");
		for (int i = 0; i < b.length; i++) {
			System.out.println(b[i]);
		}
		
		
		
		
		//createImage();
	}
}
