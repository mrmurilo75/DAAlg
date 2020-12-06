import java.util.Scanner;
import java.util.LinkedList;
import java.util.Queue;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Iterator;
import java.util.Arrays;

class Node {
	public LinkedList<Integer> adj;

	Node() {
		adj = new LinkedList<Integer>();
	}
}

class Graph {
	int size;
	Node nodes[];
	TreeMap<Integer, TreeSet<Integer>> excent;

	Graph(int n){
		this.size = n;
		nodes = new Node[size]; //each nodes[i] non-initialized is null
		excent = new TreeMap<>();
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
		//DEFINATLY ERROR IN MAXDIST 
		int[] distance=new int[size];
		boolean[] visited=new boolean[size];
		Queue<Integer> q = new LinkedList<Integer>();
		int maxDist=0;

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
			} else if(distance[w]>maxDist)	//ERROR  testing max dist bf finding acutal max minimal dist
				maxDist=distance[w];
		}
		TreeSet<Integer> equalExcent;
		if((equalExcent=excent.get(maxDist))==null){
			equalExcent=new TreeSet<>();
			equalExcent.add(base);
			excent.put(maxDist, equalExcent);
		} else
			equalExcent.add(base);
		return distance;
	}

	public int getDiameter(){
		if(excent.size()==0)
			for(int i=0; i<size; i++)
				distancesBFS(i);
		return excent.lastKey();
	}

	public int getRadius(){
		if(excent.size()==0)
			for(int i=0; i<size; i++)
				distancesBFS(i);
		return excent.firstKey();
	}

	public void printCentrals(){
		Iterator<Integer> centrals = excent.firstEntry().getValue().iterator();
		System.out.print(centrals.next()+1);
		while(centrals.hasNext())
			System.out.print(" "+(centrals.next()+1));
	}

	public void printPeripherals(){
		Iterator<Integer> periph = excent.lastEntry().getValue().iterator();
		System.out.print(periph.next()+1);
		while(periph.hasNext())
			System.out.print(" "+(periph.next()+1));
	}
}

public class D30 {
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		Graph g = new Graph(in.nextInt());
		int   e = in.nextInt();
		for (int i=0; i<e; i++)
			g.addLink(in.nextInt()-1, in.nextInt()-1);
		for(int i=0; i<g.size; i++)
			System.out.println(Arrays.toString(g.distancesBFS(i)));
		System.out.println(g.getDiameter());
		System.out.println(g.getRadius());
		g.printCentrals();
		System.out.println();
		g.printPeripherals();
		System.out.println();

	}
}
