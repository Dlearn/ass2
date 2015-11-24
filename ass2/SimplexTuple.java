package ass2;
import java.util.ArrayList;

public class SimplexTuple {

	public ArrayList<Integer> N; 
	public ArrayList<Integer> B;
	public double[][] A;
	public double[] b;
	public double[] c;
	public double v;
	
	// Constructor
	public SimplexTuple(int m, int n)
	{	
		this.N = new ArrayList<Integer>();
		this.B = new ArrayList<Integer>();
		this.A = new double[m][m + n];
		this.b = new double[m + n];
		this.c = new double[m + n];
		this.v = 0;
	}
	
	public SimplexTuple(ArrayList<Integer> _N, ArrayList<Integer> _B, 
					double _A[][], double _b[], double _c[], double _v)
	{
		this.N = _N;
		this.B = _B;
		this.A = _A;
		this.b = _b;
		this.c = _c;
		this.v = _v;
	}
	
	//Initialization
	public SimplexTuple (double _A[][], double _b[], double _c[])
	{
		int m = _A.length;
		int n = _A[0].length;
		int i, j = 0;
		this.A = new double[m+n][m+n];
		
		for (int k = n; k < n+m; k++)
			{
			for (int l = 0; l < n; l++)
				{
					//System.out.println(_A[k-n][l]);	
					this.A[k][l] = _A[k-n][l];
					//System.out.println(this.A[k][l]);
				}
			}
		
		this.b = new double[m+n];
			for (i = 0; i < n; i++)
				this.b[i] = 0;
			for (j = n; j < m + n; j++)
				this.b[j] = _b[j-n];
			
		this.c = new double[m+n];
		for (i = 0; i < n; i++)
			this.c[i] = _c[i];
		for (j = n; j < m + n; j++)
			this.c[j] = 0;
		
		
		this.N = new ArrayList<Integer>();
		this.B = new ArrayList<Integer>();
		for (i = 0; i < n;i++)
		{
			this.N.add(i);
		}
		for (j = n; j < m + n; j++)
		{
			this.B.add(j);
		}
		this.v = 0;
	}
	
	public void getVal()
	{
		System.out.print("Non-basic variables: ");
		for (Integer n : this.N)
		    {System.out.print(n + " ");}
		System.out.print("\nBasic variables: ");
		for (Integer n : this.B)
			{ System.out.print( n + " ");}
		System.out.println("\nMatrix: ");
		for (int x = 0; x < A.length; x++)
		{
			for (int y = 0; y < A[0].length; y++)
			{
				System.out.print(A[x][y] + " ");
			}
			System.out.println();
		}
		System.out.println("Constraints (b values) size =  " + b.length );
		for (int i = 0; i < this.B.size() + this.N.size(); i ++)
			{System.out.print(this.b[i] + " ");}
		
		System.out.print("\nObjective function: (v value + c values): "
				+ "\n" + this.v + " + ");
		for (int j = 0; j < A[0].length; j++)
			{System.out.print(this.c[j] + " ");}
		System.out.println("\n\n");

	}
	
}
