import java.util.Scanner;
import java.util.Arrays;

class D11{
	static int doPartition(int K, int[] D){
		int i, now, minSum=D[0], min=partition(K-1, D, 1, minSum);
		for(i=1, minSum+=D[i]; (now=partition(K-1, D, i+1, minSum))<min; minSum+=D[++i], min=now)
			System.out.print("\n\t[ ");
		return min;
	}
	static int partition(int K, int[] D, int start, int max){
		System.out.print(start+", ");
		int i, res;
		if(K<=0){
			for(i=start, res=0; i<D.length; res+=D[i++]);
			System.out.println("]");
			return ((res>max)? res : max);
		}
		for(i=start, res=0; i<D.length && res+D[i]<=max; res+=D[i++]);
		if(i==start)
			return partition(K-1, D, i+1, D[i]);
		return partition(K-1, D, i, max);
	}

	public static void main(String[] args){
		Scanner in=new Scanner(System.in);
		int N=in.nextInt();
		int[] D=new int[N];
		for(int i=0; i<N; i++)
			D[i]=in.nextInt();
		int P=in.nextInt();
		int[] K=new int[P];
		//System.out.println("\t"+Arrays.toString(D));
		for(int i=0; i<P; i++)
			K[i]=doPartition(in.nextInt(), D);
		//System.out.println("\t"+Arrays.toString(K));
		for(int i=0; i<P; i++)
			System.out.println(K[i]);

	}
}
