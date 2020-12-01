import java.util.Scanner;

public class D27{
	static int V;
	static boolean adj[][];
	static boolean visited[];
	static boolean color[];

	static boolean dfs(int v, boolean red){
		if(visited[v]){
			if(color[v]==red)
				return true;
			else
				return false;
		}
		//System.out.print(v+1 + " ");
		visited[v] = true;
		color[v]=red;
		boolean res=true;
		for (int i=0; i<V; i++)
			if (adj[v][i] && !(res=res && dfs(i, !red)) )
				return false;
		return res;
	}

	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		int N=in.nextInt();
		for(int n=0; n<N; n++){
			V=in.nextInt();
			int E=in.nextInt();
			adj = new boolean[V][V];
			visited = new boolean[V];
			color = new boolean[V];
			for (int e=0; e<E; e++) {
				int a = in.nextInt();
				int b = in.nextInt();
				adj[a-1][b-1] = adj[b-1][a-1] = true;
			}
			if(dfs(0, true))
				System.out.println("sim");
			else
				System.out.println("nao");
		}
	}
}
