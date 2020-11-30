import java.util.*;
import java.io.*;

public class D25{
	static int n;
	static boolean adj[][];
	static boolean visited[];

	static void dfs(int v) {
		//System.out.print(v+1 + " ");
		visited[v] = true;
		for (int i=0; i<n; i++)
			if (adj[v][i] && !visited[i])
				dfs(i);
	}

	public static void main(String args[]) {
		Scanner stdin = new Scanner(System.in);
		n = stdin.nextInt();
		adj = new boolean[n][n];
		visited = new boolean[n];
		int edges = stdin.nextInt();
		for (int i=0; i<edges; i++) {
			int a = stdin.nextInt();
			int b = stdin.nextInt();
			adj[a-1][b-1] = adj[b-1][a-1] = true;
		}

		int counter=0;
		for(int i=0; i<n; i++)
			if(!visited[i]){
				counter++;
				dfs(i);
			}
		System.out.println(counter);
	}
}
