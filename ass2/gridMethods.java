package ass2;

public class gridMethods {
	public static boolean isValidUp(int i, int w, int h){
		return i >= w;
	}
	public static boolean isValidDown(int i, int w, int h){
		return i<=(h-1)*w;
	}
	public static boolean isValidLeft(int i, int w){
		return (i % w) != 0;
	}
	public static boolean isValidRight(int i, int w){
		return ((i+1) % w) != 0;
	}
	
	public static int getCount(int m, int n, int w, int h){
		return n*w+m;
	}

}
