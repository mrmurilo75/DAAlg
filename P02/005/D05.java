public class D05{
	public static void main(String[] args){
		FastScanner in = new FastScanner(System.in);
		int N = in.nextInt();
		int E[] = new int[N]; 
		for(int i=0; i<N; i++)
			E[i] = in.nextInt();
		int F = in.nextInt();
		int A[] = new int[F]; 
		int B[] = new int[F]; 
		for(int i=0; i<F; i++){
			A[i]=in.nextInt();
			B[i]=in.nextInt();
		}
		for(int i=1; i<N; i++)
			E[i]+=E[i-1];
		for(int i=0; i<F; i++)
			if(A[i]==1)
				FastPrint.out.println(E[B[i]-1]);
			else
				FastPrint.out.println(E[B[i]-1]-E[A[i]-2]);
		FastPrint.out.close();
	}
}
