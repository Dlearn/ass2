package ass2;
import java.util.ArrayList;

public class Simplex {
	
	public Simplex (){}
	
	//Initialization
	public SimplexTuple Initialize_Simplex(double _A[][], double _b[], double _c[])
	{
		int m = _A.length;
		int n = _A[0].length;
		SimplexTuple tuple = new SimplexTuple(m,n);
		tuple.A = _A;
		tuple.b = _b;
		tuple.c = _c;
		for (int i = 0; i < n;i++)
		{
			tuple.N.add(i);
		}
		for (int j = n; j < m + n; j++)
		{
			tuple.B.add(j);
		}
		tuple.v = 0;
		return tuple;
	}
	
	
	
	// Pivot operation
	public SimplexTuple Pivot(SimplexTuple Prev, int l, int e)
	{
		int m = Prev.B.size();
		int n = Prev.N.size();

		//SimplexTuple Next = new SimplexTuple(m+n,m+n);
		//Next.getVal();
		double[][] Ahat = new double[m+n][m+n];
		ArrayList<Integer> Nhat = new ArrayList<Integer>();
		ArrayList<Integer> Bhat = new ArrayList<Integer>();
		double[] bhat = new double[m + n];
		double[] chat = new double[m + n];
		double vhat = 0;
		
		Prev.N.remove(new Integer(e));
		Prev.B.remove(new Integer(l));
		
		//Compute the coefficients of the equation for new basic variable x_e
		bhat[e] = Prev.b[l] / Prev.A[l][e];
		
		for (Integer j : Prev.N)
		{
			Ahat[e][j] = Prev.A[l][j] / Prev.A[l][e];
		}
		Ahat[e][l] = 1 / Prev.A[l][e];
		
		
		//Compute the coefficients of the remaining constraints
		for (Integer i : Prev.B)
		{
			bhat[i] = Prev.b[i] - Prev.A[i][e]* bhat[e];
			for (int j = 0; j < Prev.N.size(); j++)
			{
				int temp_j = Prev.N.get(j);
				Ahat[i][temp_j] = Prev.A[i][temp_j] - Prev.A[i][e] * Ahat[e][temp_j];
			}
			Ahat[i][l] = -1 * Prev.A[i][e] * Ahat[e][l];
		}
		
		// Compute the objective function
		vhat = Prev.v + Prev.c[e] * bhat[e];
		
		for (Integer g : Prev.N)
		{
			chat[g] = Prev.c[g] - Prev.c[e] * Ahat[e][g];
		}
		chat[l] = -1 * Prev.c[e] * Ahat[e][l];
		// Compute new sets of basic and nonbasic variables;
		Prev.N.add(l);
		Nhat = Prev.N;
		
		Prev.B.add(e);
		Bhat = Prev.B;
		
		SimplexTuple result = new SimplexTuple (Nhat, Bhat, Ahat, bhat, chat, vhat);
		//result.getVal();
		return result;
	}
	
	
	public boolean isLessThanZero(double c[])
	{
		for (int i = 0; i < c.length; i++)
		{
			if (c[i] > 0) 
				{return false;}
		}
		return true;
	}
	
	public int chooseIndex(double c[], ArrayList<Integer> N)
	{
		for (Integer j : N)
		{
			if (c[j] > 0)
			{
				return j;
			}
		}
		return 0;
	}
	
	
	public double[] Simplex_Main(double _A[][], double _b[], double _c[]) throws UnboundedException
	{
		SimplexTuple tuple = new SimplexTuple(_A, _b, _c);
		int n = _A.length;
		int m = _A[0].length;
		double[] delta = new double[m + n]; 
		double[] result = new double[n]; 
		int count = 0;
		
		while(!isLessThanZero(tuple.c))
		{
			count = count + 1;
			int e = chooseIndex(tuple.c, tuple.N);
			for (Integer x : tuple.B)
			{
				if (tuple.A[x][e] > 0)
				{
					delta[x] = tuple.b[x] / tuple.A[x][e];
				}
				else delta[x] = Double.MAX_VALUE;
			}
//			for (int r = 0; r < delta.length; r++)
//				{System.out.print(delta[r] + " ");}
			int l1 = minValue (delta, tuple.B);
			//int l2 = tuple.B.get(l1);

			if (delta[l1] == Double.MAX_VALUE)
			{
				throw new UnboundedException();
			}
			else tuple = Pivot(tuple, l1, e);
		}
		for (int q = 0; q < n; q++)
		{
			if (tuple.B.contains(q))
			{
				result[q] = tuple.b[q];
			}
			else result[q] = 0;
		}
		System.out.println("Number of iterations: " + count);
		return result;
	}
	

	private static int minValue(double[] arr, ArrayList<Integer> _B) {
		int min_ktr = _B.get(0);
		double min = arr[min_ktr];
		for (Integer k : _B) {
			if (arr[k] < min) {
				min = arr[k];
				min_ktr = k;
			}
		}
		return min_ktr;
	}
}
