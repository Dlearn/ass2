/**
 * 
 */
package ass2;

public class hello {
	public static void main(String[] args) {
		Simplex sim = new Simplex();
		
		/*
		 * Test code for a 1 x 2 pixel case: Works!
		 * 
		int m = 2;
		int n = 1;
		int lambda = 4;
		double[][] A = new double[7*m*n][6*m*n];
		for(int i=0;i<7*m*n;i++) //initialize all A to be 0
			for(int j=0;j<6*m*n;j++)
				A[i][j]=0;
		
		for(int i=0;i<6*m*n;i++){ //initialize all diagonal to 1
			A[i][i] = 1;
		}
		A[12][0]=1;
		A[12][1]=-1;
		A[12][5]=-1;
		A[12][10]=1;
		A[13][5]=1;
		A[13][6]=1;
		A[13][7]=-1;
		A[13][10]=-1;
		
		double[] b = new double[7*m*n];
		for(int i=0;i<b.length;i++) b[i]=0;
		b[1]=400;
		b[5]=lambda;
		b[6]=400;
		b[10]=lambda;
			
		double[] c = new double[6*m*n];
		for(int i=0;i<c.length;i++) c[i]=0;
		c[0]=1;
		c[6]=1;
		*/
		
		/* First set used to test simplex algorithm: No longer works.
		 * 
		 * double [][] A = new double[5][5];
		for(int i=0;i<5;i++) //initialize all A to be 0
			for(int j=0;j<5;j++)
				A[i][j]=0;
		A[2][0]=1;
		A[3][0]=1;
		A[4][0]=0;
		A[2][1]=1;
		A[3][1]=0;
		A[4][1]=1;
		
		double[] b = new double[5];
		for(int i=0;i<b.length;i++) b[i]=0;
		b[2]=20;
		b[3]=12;
		b[4]=16;
		
		double[] c = new double[5];
		for(int i=0;i<c.length;i++) c[i]=0;
		c[0]=18;
		c[1]=12.5;*/
		
		/*
		try{		
			double[] d = sim.Simplex_Main(A,b,c);
			for(int i=0;i<d.length;i++) System.out.println(d[i]);
		}
		catch(UnboundedException e)
		{
			System.out.println("Unbounded");
			e.printStackTrace();
		}*/
		
	}
}