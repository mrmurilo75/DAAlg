import java.util.*;

public class D34onFW{

	static boolean compareAdded(Integer a, Integer b, Integer c){
		if(a==null || b==null)
			return false;
		if(c==null)
			return true;
		return (a+b < c);
	}

	public static void main(String[] args){
		Scanner in=new Scanner(System.in);
		int C=in.nextInt();
		while(C-->0)
			FloydWarshall(in);
	}


	public static void FloydWarshall(Scanner in){
		int N=in.nextInt(), M=in.nextInt();
		Integer[][] dist=new Integer[N][N]; //initialize with null (we are using as '+inf')
		for(int n=0; n<N; ++n)
			dist[n][n]=0;
		for(int m=0; m<M; ++m)
			dist[in.nextInt()][in.nextInt()]=in.nextInt();
		for(int k=0; k<N; k++)
			for(int i=0; i<N; i++)
				for(int j=0; j<N; j++){
					if(compareAdded(dist[i][k], dist[k][j], dist[i][j]))
						dist[i][j]=dist[i][k]+dist[k][j];
					if(dist[j][j]<0){
						System.out.println("possivel");
						return;
					}
				}
		System.out.println("impossivel");
	}
}
