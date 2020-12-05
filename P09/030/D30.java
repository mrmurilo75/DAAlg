import java.util.Scanner;
import java.util.LinkedList;

class Node {
	public LinkedList<Integer> adj;
	public boolean visited;
	public int distance;

	Node() {
		adj = new LinkedList<Integer>();
	}
}

class Graph {
	int n;
	Node nodes[];

	Graph(int n){
		this.n = n;
		nodes  = new Node[n];
		for (int i=0; i<n; i++)
			nodes[i] = new Node();
	}

	public void addLink(int a, int b){
		nodes[a].adj.add(b);
		nodes[b].adj.add(a);
	}

	public void bfs(int v) {
		LinkedList<Integer> q = new LinkedList<Integer>();
		for (int i=0; i<n; i++)
			nodes[i].visited = false;

		q.add(v);
		nodes[v].visited = true;
		nodes[v].distance = 0;

		while (q.size() > 0) {
			int u = q.removeFirst();
			System.out.println((u+1)+" [dist="+nodes[u].distance+"]");
			for (int w : nodes[u].adj)
			if (!nodes[w].visited) {
				q.add(w);
				nodes[w].visited  = true;
				nodes[w].distance = nodes[u].distance + 1;
			}
		}
	}
}

public class D30 {
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		Graph g = new Graph(in.nextInt());
		int   e = in.nextInt();
		for (int i=0; i<e; i++)
			g.addLink(in.nextInt()-1, in.nextInt()-1);
		g.bfs(2);
	}
}
