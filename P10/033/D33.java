import java.util.Scanner;
import java.util.LinkedList;
import java.util.TreeSet;

class Edge {
	int to;
	int weight;

	Edge(int t, int w) {
	to = t;
	weight = w;
	}
}

class Node {
	public LinkedList<Edge> adj;
	public boolean visited;
	public int distance;

	Node() {
	adj = new LinkedList<>();
	}
}

class NodeQ implements Comparable<NodeQ> {
	public int cost;
	public int node;

	NodeQ(int c, int n) {
	cost = c;
	node = n;
	}

	@Override
	public int compareTo(NodeQ nq) {
		if (cost < nq.cost) return -1;
		if (cost > nq.cost) return +1;
	if (node < nq.node) return -1;
	if (node > nq.node) return +1;
		return 0;
	}
}

class Graph {
	int n;
	Node[] nodes;

	Graph(int n) {
	this.n = n;
	nodes = new Node[n+1];
	for (int i=1; i<=n; i++)
		nodes[i] = new Node();
	}

	void addLink(int a, int b, int c) {
	nodes[a].adj.add(new Edge(b, c));
	}

	void dijkstra(int s) {

	for (int i=1; i<=n; i++) {
		nodes[i].distance = Integer.MAX_VALUE;
		nodes[i].visited  = false;
	}

	nodes[s].distance = 0;
	TreeSet<NodeQ> q = new TreeSet<>();
	q.add(new NodeQ(0, s));

	while (!q.isEmpty()) {

		NodeQ nq = q.pollFirst();
		int  u = nq.node;
		nodes[u].visited = true;
		System.out.println(u + " [dist=" + nodes[u].distance + "]");

		for (Edge e : nodes[u].adj) {
		int v = e.to;
		int cost = e.weight;
		if (!nodes[v].visited && nodes[u].distance + cost < nodes[v].distance) {
			q.remove(new NodeQ(nodes[v].distance, v));
			nodes[v].distance = nodes[u].distance + cost;
			q.add(new NodeQ(nodes[v].distance, v));
		}
		}
	}
	}
}


public class D33 {
	public static void main(String args[]) {
	Scanner in = new Scanner(System.in);

	Graph g = new Graph(in.nextInt());
	int   e = in.nextInt();
	for (int i=0; i<e; i++)
		g.addLink(in.nextInt(), in.nextInt(), in.nextInt());

	g.dijkstra(1);
	}
}
