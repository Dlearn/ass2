package ass2;

import java.util.LinkedList;

public class simplexMethods {
	
	public static Tuple initializeSimplex(double[][] A, double[] b, double[] c, int m, int n)
			throws UnboundedException{
		LinkedList<Integer> N = new LinkedList<Integer>();
		LinkedList<Integer> B = new LinkedList<Integer>();
		
		int count = 0;
		for(int i=0;i<6*m*n;i++){
			N.add(count);
			count++;
		}
		for(int i=0;i<7*m*n;i++){
			B.add(count);
			count++;
		}
		
		/*System.out.println("The N:");
		for(int i=0; i<N.size();i++){
			System.out.println(N.get(i));
		}
		System.out.println("The B:");
		for(int i=0; i<B.size();i++){
			System.out.println(B.get(i));
		}*/
		
		double v = 0;
		Tuple t = new Tuple(N,B,A,b,c,v);
		return t;
	}
	
	public static Tuple pivot(Tuple t, int l, int e){ //Tuple contains all values N B A b c v
		LinkedList<Integer> N = t.getN();	//Initialize N from the tuple
		LinkedList<Integer> B = t.getB();	//Initialize B from the tuple
		double[][] A = t.getA();//Initialize A from the tuple
		double[] b = t.getb();	//Initialize b from the tuple
		double[] c = t.getc();	//Initialize c from the tuple
		double v = t.getv();	//Initialize v from the tuple
		
		double[] bhat = new double[b.length];
		double[] chat = new double[c.length];
		for(int i=0; i<b.length;i++){
			bhat[i] = 0;
		}
		for(int i=0; i<c.length;i++){
			chat[i] = 0;
		}
		
		System.out.println("Starting A:");
		for(int i=0; i<A.length;i++){
			for(int j=0; j<A[0].length;j++){
				System.out.print(A[i][j]+" ");
			}
			System.out.print("\n");
		}
		/*System.out.println("Start b:");
		for(int i=0; i<b.length;i++){
			System.out.println(b[i]);
		}
		System.out.println("Start c:");
		for(int i=0; i<c.length;i++){
			System.out.println(c[i]);
		}*/
		
		/*System.out.println("Ending N:");
		for(int i=0; i<N.size();i++){
			System.out.println(N.get(i));
		}
		System.out.println("Ending B:");
		for(int i=0; i<B.size();i++){
			System.out.println(B.get(i));
		}*/
		
		int dim = N.size()+B.size();
		double[][] Ahat = new double[dim][dim]; //initialize Ahat dim * dim matrix
		for(int i=0;i<dim;i++){ //initialize all Ahat to be 0
			for(int j=0;j<dim;j++){ Ahat[i][j] = 0; }
		}
		
		/*
		 * Compute the coefficient of the equation for the new basic variable xe
		 */
		
		bhat[e] = b[l]/A[l][e]; //Line 3 code
		
		for(int i=0;i<dim;i++){
			if(N.contains(i) && i != e){
				//System.out.println("Iterating for j = "+i);
				Ahat[e][i] = A[l][i]/A[l][e];
			}
		}
		Ahat[e][l] = 1/A[l][e];
		
		/*
		 * Compute the coefficient of the equation for the remaining constants
		 */
		
		for(int i=0;i<dim;i++){
			if(B.contains(i) && i != l){
				System.out.println("2Iterating for i = "+i);
				bhat[i] = b[i] - A[i][e]*bhat[e];
				for(int j=0;j<dim;j++){
					if(N.contains(j) && j != e)
						Ahat[i][j] = A[i][j]-A[i][e]*Ahat[e][j];
					Ahat[i][l]=-A[i][e]*Ahat[e][l];
				}
			}
		}
		
		/*
		 * Compute the objective function
		 */
		
		v = v + c[e]*bhat[e]; //Line 14
		for(int j=0;j<dim;j++){
			if(N.contains(j) && j != e){
				chat[j] = c[j] - c[e]*Ahat[e][j];
			}
		}
		chat[l] = -c[e]*Ahat[e][l];
		
		/*
		 * Compute the new sets of basic and non-basic variables
		 */
		
		N.removeFirstOccurrence(e);
		N.add(l);
		B.removeFirstOccurrence(l);
		B.add(e);
		
		/*
		 * Print check
		 */
		
		/*System.out.println("Ending A:");
		for(int i=0; i<Ahat.length;i++){
			for(int j=0; j<Ahat[0].length;j++){
				System.out.print(Ahat[i][j]+" ");
			}
			System.out.print("\n");
		}
		
		System.out.println("Ending N:");
		for(int i=0; i<N.size();i++){
			System.out.println(N.get(i));
		}
		System.out.println("Ending B:");
		for(int i=0; i<B.size();i++){
			System.out.println(B.get(i));
		}
		/*System.out.println("Ending b:");
		for(int i=0; i<b.length;i++){
			System.out.println(bhat[i]);
		}
		System.out.println("Ending c:");
		for(int i=0; i<c.length;i++){
			System.out.println(chat[i]);
		}*/
		
		System.out.println("Final value v: "+v);
		
		Tuple t2 = new Tuple(N,B,Ahat,bhat,chat,v);
		return t2;
	}
	
	public static double[] simplex(double[][] A1, double[] b1, double[] c1, int m, int n)
			throws UnboundedException{
		Tuple t = simplexMethods.initializeSimplex(A1,b1,c1,m,n);
		
		LinkedList<Integer> N = t.getN();	//Initialize N from the tuple
		LinkedList<Integer> B = t.getB();	//Initialize B from the tuple
		double[][] A = t.getA();//Initialize A from the tuple
		double[] b = t.getb();	//Initialize b from the tuple
		double[] c = t.getc();	//Initialize c from the tuple
		//double v = t.getv();	//Initialize v from the tuple
		
		System.out.println("Starting A:");
		for(int i=0; i<A.length;i++){
			for(int j=0; j<A[0].length;j++){
				System.out.print((int)A[i][j]+" ");
			}
			System.out.print("\n");
		}
		
		double[] delta = new double[B.size()]; //Line 2
		for(int i=0;i<delta.length;i++){ delta[i]=Double.MAX_VALUE; } //Initialize delta to large
		boolean iterate = false;
		int e = -1; //Initialize the variable e, which will become the variable we iterate on
		for(int i=0;i<N.size();i++){ //Line 4
			iterate = iterate || c[N.get(i)] > 0;
			if (c[N.get(i)] > 0) {e = i;}
			System.out.println(i+": "+iterate+" "+e);
		}
		while(iterate){ //Line 3
			for(int k=0;k<B.size();k++){ //Line 5
				int i = B.get(k);
				System.out.println("Accessing A: "+ i +" "+ e);
				//System.out.println("Accessing delta: "+i);
				//System.out.println("Accessing b: "+i);
				if (A[i][e] > 0) {
					System.out.println("Equation number "+i+" contains the variable "+e);
					delta[i] = b[i]/A[i][e]; 
					System.out.println(delta[i]+" = "+b[i]+" / "+A[i][e]);
				} //Line 7
				else { //Else, set it to positive infinity
					System.out.println("Equation number "+i+" is invalid with: "+A[i][e]);
					delta[i] = Double.POSITIVE_INFINITY; 
					} 
			}
			/*System.out.println("delta array");
			for(int i=0;i<delta.length;i++){
				System.out.println(delta[i]);
			}*/
			int l = simplexMethods.findMinInArray(delta);
			if (delta[l]==Double.POSITIVE_INFINITY) { throw new UnboundedException(); }//Line 10/11
			else {
				//System.out.println("The leaving variable: "+l);
				t = simplexMethods.pivot(t,l,e); //Line 12
				A = t.getA();	//ReInitialize A from the tuple
				b = t.getb();	//ReInitialize b from the tuple
				c = t.getc();	//ReInitialize c from the tuple
				for(int i=0;i<delta.length;i++){ delta[i]=Double.MAX_VALUE; } //Initialize delta to large
			}
			iterate=false;
			for(int i=0;i<N.size();i++){
				iterate = iterate || c[N.get(i)] > 0;
				if (c[N.get(i)] > 0) {e = N.get(i);}
				//System.out.println("Testing variable: "+N.get(i)+" Iterate?: "+iterate+" Iterate on: "+e);
			}
			//System.out.println("Final iterate?: "+iterate+" "+e);
		}
		double[] d = new double[b.length];
		for(int i=0;i<b.length;i++){
			if (B.contains(i)) d[i]=b[i];
			else d[i]=0;
		}
		return d;
	}
	
	public static double distanceFrom(double r1, double g1, double b1, double r2, double g2, double b2){
		double d = Math.sqrt((r1-r2)*(r1-r2) + (g1-g2)*(g1-g2) + (b1-b2)*(b1-b2));
		return d;
	}
	
	public static int findMinInArray(double[] arr){
		double d = Double.MAX_VALUE;
		int ans = 0;
		for(int i=0;i<arr.length;i++){
			if (arr[i] < d && arr[i]!=0) { 
				d=arr[i];
				ans=i;
			}
		}
		return ans;
	}
}
