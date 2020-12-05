import java.util.Scanner;
import java.util.LinkedList;
import java.util.Queue;

class Node {
	public LinkedList<Integer> adj;

	Node() {
		adj = new LinkedList<Integer>();
	}
}

class Graph {
	int size;
	Node nodes[];

	Graph(int n){
		this.size = n;
		nodes  = new Node[size]; //each nodes[i] non-initialized is null
	}

	public void addLink(int a, int b){
		if(nodes[a]==null)
			nodes[a]=new Node();
		if(nodes[b]==null)
			nodes[b]=new Node();
		nodes[a].adj.add(b);
		nodes[b].adj.add(a);
	}

	public int[] distancesBFS(int base) {
		int[] distance=new int[size];
		boolean[] visited=new boolean[size];
		Queue<Integer> q = new LinkedList<Integer>();

		q.add(base);
		visited[base] = true;
		distance[base] = 0;

		while (q.size() > 0) {
			int u = q.remove();
			for (int w : nodes[u].adj)
			if (!visited[w]) {
				q.add(w);
				visited[w]  = true;
				distance[w] = distance[u] + 1;
			}
		}
		return distance;
	}
}

public class D30 {
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		Graph g = new Graph(in.nextInt());
		int   e = in.nextInt();
		for (int i=0; i<e; i++)
			g.addLink(in.nextInt()-1, in.nextInt()-1);
		g.distancesBFS(0);
	}
}
