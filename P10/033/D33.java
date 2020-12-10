import java.util.Scanner;
import java.util.LinkedList;
import java.util.TreeSet;
import java.util.TreeMap;

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
	public LinkedList<Pair<Integer, Float>> adjList;

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

	void addLink(int from, int to, float cost) {
		nodes[from].adjList.add(new Pair<Integer, Float>(to, cost));
		nodes[to].adjList.add(new Pair<Integer, Float>(from, cost));
	}

	int compareFloat(Float now, Float next){
		if(next==null || now<next)
			return -1;
		if(now>next)
			return 1;
		return 0;
	}

//	TreeMap<Integer, Float> dijkstra(int base) {
	Float dijkstra(int base) {
		Float[] distance=new Float[size];
		boolean[] visited=new boolean[size];
//		TreeMap<Integer, Float> results = new TreeMap<>();

		distance[base] = (float)0;
		TreeSet<Pair<Float, Integer>> queue = new TreeSet<>();	//Tree with list of Pair< Cost, Node >
		queue.add(new Pair<Float, Integer>((float)0, base));

		while (!queue.isEmpty()) {

			Pair<Float, Integer> nodeQ = queue.pollFirst();
			int now = nodeQ.y;	//y is the node
			visited[now] = true;

			if(now==1)
				return distance[now];
//			results.put(now, distance[now]);

			for (Pair<Integer, Float> n : nodes[now].adjList) {
				Integer next = n.x;
				Float cost = n.y;
				if(distance[next]==null){
					distance[next] = distance[now] + cost;
					queue.add(new Pair<Float, Integer>(distance[next], next));
					continue;
				}else if (!visited[next] && compareFloat(distance[now]+cost, distance[next])<0) {
					queue.remove(new Pair<Float, Integer>(distance[next], next));
					distance[next] = distance[now] + cost;
					queue.add(new Pair<Float, Integer>(distance[next], next));
				}
			}
		}
		return (float)0;
//		return results;
	}
}


public class D33 {
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);

		int size = in.nextInt();
		Graph g = new Graph(size);
		int e = in.nextInt();
		TreeMap<String, Integer> tree = new TreeMap<>();
		int trad=0;
		tree.put(in.next(), trad++);
		tree.put(in.next(), trad++);
		for (int i=0; i<e; i++){
			Integer posFrom, posTo;
			String from = in.next();
			String to = in.next();
			if((posFrom=tree.get(from))==null){
				posFrom=trad;
				tree.put(from, posFrom);
				trad++;
			}
			if((posTo=tree.get(to))==null){
				posTo=trad;
				tree.put(to, posTo);
				trad++;
			}
			g.addLink(posFrom, posTo, in.nextFloat());
		}

//		TreeMap<Integer, Float> results = g.dijkstra(0);
//		System.out.println(results.get(1));
		System.out.printf("%.1f\n", g.dijkstra(0));
	}
}
