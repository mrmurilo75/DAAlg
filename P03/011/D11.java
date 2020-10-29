import java.util.Scanner;
import java.util.Arrays;

class D11{
	static int addArray(int[] A){
		int res=0;
		for(int i=0; i<A.length; i++)
			res+=A[i];
		return res;
	}
	static int doPartition(int K, int[] D){
		System.out.print("\n\t[ ");
		int i, minSum=0, maxSum=addArray(D);
		while(minSum<maxSum){
			if(partition(K-1, D, 0, (maxSum+minSum)/2))
				maxSum=(maxSum+minSum)/2;
			else
				minSum=(maxSum+minSum)/2+1;
		}
		return maxSum;
	}
	static boolean partition(int K, int[] D, int start, int max){
		System.out.print(start+", ");
		int i, res;
		if(K==0){
			for(i=start, res=0; i<D.length; res+=D[i++]);
			System.out.println("]");
			return ((res<=max)? true : false);
		}
		for(i=start, res=0; i<D.length && res+D[i]<=max; res+=D[i++]);
		if(i==start && i<D.length){
			if(D[i]>max) return false;
			return partition(K-1, D, i+1, max);
		}
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
