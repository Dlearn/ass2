package ass2;
import java.util.*;

public class Tuple{ 
	public final LinkedList<Integer> N; 
	public final LinkedList<Integer> B;
	public final double[][] A;
	public final double[] b;
	public final double[] c;
	public final double v;
	public Tuple(LinkedList<Integer> N, LinkedList<Integer> B, double[][] A, double[] b, double[] c, double v) { 
		this.N = N; 
	    this.B = B; 
	    this.A = A;
	    this.b = b;
	    this.c = c;
	    this.v = v;
	}
	public LinkedList<Integer> getN()	{return N;}
	public LinkedList<Integer> getB()	{return B;}
	public double[][] getA()	{return A;}
	public double[] getb()	{return b;}
	public double[] getc()	{return c;}
	public double getv()	{return v;}
} 