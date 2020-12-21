import java.util.*;

public class D35{

	static boolean compareAdded(Integer a, Integer b, Integer c){
		if(a==null || b==null)
			return false;
		if(c==null)
			return true;
		return (a+b < c);
	}

	public static void main(String[] args){
		Scanner in=new Scanner(System.in);
		int V=in.nextInt();
		Integer[][] dist=new Integer[V][V]; //initialize with null (we are using as '+inf')
		for(int v=0; v<V; ++v){
			in.next();
			dist[v][v]=0;
			int E=in.nextInt();
			for(int e=0; e<E; ++e){
				char u=in.next().charAt(0);
				dist[v][u-'A']=1; 	//estava imprimido invertido ao final por ter escrito primeiramente [u-'A'][v] e a Ana Isabel Mendes me ajudou indiretamente no debug
			}
		}
		for(int k=0; k<V; k++)
			for(int i=0; i<V; i++)
				for(int j=0; j<V; j++)
					if(compareAdded(dist[i][k], dist[k][j], dist[i][j]))
						dist[i][j]=dist[i][k]+dist[k][j];
		 
		System.out.print(" ");
		for(char i=0; i<V; i++)
			System.out.print(" "+Character.toString((char)('A'+i)));
		System.out.println();
		for(char i=0; i<V; i++){
			System.out.print(Character.toString((char)('A'+i)));
			for(char j=0; j<V; j++)
				System.out.print(" "+((dist[i][j]==null)?0:1));
			System.out.println();
		}
		
	}
}
