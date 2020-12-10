import java.util.Scanner;
import java.util.LinkedList;
import java.util.TreeSet;

class Pair< X extends Comparable<? super X>, Y extends Comparable<? super Y>> implements Comparable<Pair<X, Y>>{
	X x;
	Y y;
	
	Pair(X x, Y y){
		this.x =x;
		this.y =y;
	}

	public int compareTo(Pair<X, Y> that){
		int res;
		if((res=this.x.compareTo(that.x))!=0)
			return res;
		return this.y.compareTo(that.y);
	}
}

class Node {
	public LinkedList<Pair<Integer, Integer>> adjList;

	Node() {
	adjList = new LinkedList<>();
	}
}

class Graph {
	int size;
	Node[] nodes;

	Graph(int size) {
		this.size = size;
		nodes = new Node[size];
		for (int i=0; i<size; i++)
			nodes[i] = new Node();
	}

	void addLink(int from, int to, int cost) {
		nodes[from].adjList.add(new Pair<Integer, Integer>(to, cost));
	}

	int compareInteger(Integer now, Integer next){
		if(next==null || now<next)
			return -1;
		if(now>next)
			return 1;
		return 0;
	}

	void dijkstra(int base) {
		Integer[] distance=new Integer[size];
		boolean[] visited=new boolean[size];

//		for (int i=0; i<size; i++) {
//			distance[i] = Integer.MAX_VALUE;
//			visited[i] = false;
//		}

		distance[base] = 0;
		TreeSet<Pair<Integer, Integer>> queue = new TreeSet<>();	//Tree with list of Pair< Cost, Node >
		queue.add(new Pair<Integer, Integer>(0, base));

		while (!queue.isEmpty()) {

			Pair<Integer, Integer> nodeQ = queue.pollFirst();
			int now = nodeQ.y;	//y is the node
			visited[now] = true;
			System.out.println(now+1 + " [dist=" + distance[now] + "]");

			for (Pair<Integer, Integer> n : nodes[now].adjList) {
				int next = n.x;
				int cost = n.y;
				if(distance[next]==null){
					distance[next] = distance[now] + cost;
					queue.add(new Pair<Integer, Integer>(distance[next], next));
					continue;
				}else if (!visited[next] && compareInteger(distance[now]+cost, distance[next])<0) {
					queue.remove(new Pair<Integer, Integer>(distance[next], next));
					distance[next] = distance[now] + cost;
					queue.add(new Pair<Integer, Integer>(distance[next], next));
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
			g.addLink(in.nextInt()-1, in.nextInt()-1, in.nextInt());

		g.dijkstra(1);
	}
}
