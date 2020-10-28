import java.util.Scanner;
import java.util.TreeSet;

class D10{
	public static void main(String[] args){
		Scanner in=new Scanner(System.in);
		int N=in.nextInt();
		int[] S=new int[N];
		for(int i=0; i<N; i++)
			S[i]=in.nextInt();
		int Q=in.nextInt();
		int[] P=new int[Q];
		for(int i=0; i<Q; i++)
			P[i]=in.nextInt();
		TreeSet<Integer> tree=new TreeSet<Integer>();
		for(int i=0; i<N-1; i++)
			for(int j=i+1; j<N; j++)
				tree.add(S[i]+S[j]);
		for(int i=0; i<Q; i++){
			Integer top, floor;
			if((top=tree.ceiling(P[i]))!=null && top!=P[i] && (floor=tree.floor(P[i]))!=null){
				if((P[i]-floor)==(top-P[i]))
					System.out.println(floor+" "+top);
				else
					System.out.println((P[i]-floor<top-P[i])? floor : top);
			} else
				System.out.println( (top==null)? tree.floor(P[i]) : top );
		}
	}
}

