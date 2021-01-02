import java.util.*;

class Pair< X extends Comparable<? super X>, Y extends Comparable<? super Y>> implements Comparable<Pair<X, Y>>{
	X x;
	Y y;

	Pair(X x, Y y){
		this.x =x;
		this.y =y;
	}
	
	X getX(){
		return x;
	}
	
	Y getY(){
		return y;
	}

	public int compareTo(Pair<X, Y> that){
		int res;
		if((res=this.x.compareTo(that.x))!=0)
			return res;
		return this.y.compareTo(that.y);
	}
}

class Node implements Comparable<Node>{
	public TreeSet< Pair<Node, Integer> > adjList;
	int index, distance, pathDistance;
	Node parent;

	Node(int index){
		this.index =index;
		adjList =new TreeSet<>();
		distance=Integer.MAX_VALUE;
	}

	void addLink(Node connectTo, Integer weight){
		adjList.add(new Pair<>(connectTo, weight));
	}

	public int compareTo(Node that){
		if(this.distance==that.distance)
			return (this.index==that.index)? 0 : -1;
		if(this.distance<that.distance)
			return -1;
		return 1;
	}

	public String toString(){
		return "node["+(index+1)+"]";
	}
}

class Graph {
	int size, oldSize;
	Node[] nodes;
	TreeSet<Node> unused;

	Graph(int oldSize, int addSize, int connects, Scanner in) {
		this.size = oldSize+addSize;
		this.oldSize = oldSize;
		nodes = new Node[size];
		unused =new TreeSet<>();

		for(int i=0; i<size; i++){
			nodes[i] = new Node(i);
			if(i<oldSize && i>0){
				nodes[i].parent=nodes[i-1];
				nodes[i].distance=0;
			}
			unused.add(nodes[i]);
		}

		for(int i=0, a, b, c; i<connects; i++){
			nodes[(a=in.nextInt()-1)].addLink(nodes[(b=in.nextInt()-1)], (c=in.nextInt()));
			nodes[b].addLink(nodes[a], c);
		}
	}

	void printPrim(Integer inicial){
		unused.remove(nodes[inicial]);
		nodes[inicial].distance=0;
		unused.add(nodes[inicial]);
		while(!unused.isEmpty()){
			Node now=unused.pollFirst();
			for(Pair<Node, Integer> pp : now.adjList){
				Node next=pp.x;
				Integer pathDist=pp.y;
				if(unused.contains(next) && pathDist<next.distance){
					unused.remove(next);
					next.parent =now;
					next.distance =pathDist;
					unused.add(next);
				}
			}
		}
		int sum=0;
		int[] paths = new int[size-oldSize];
		for(int i=oldSize; i<size; i++){
//			System.out.println((i+1)+"\t"+nodes[i].distance+"\t"+nodes[i].parent);
			sum+=nodes[i].distance;
			paths[i-oldSize]=nodes[i].distance;

		}
		System.out.println(sum);
		Arrays.sort(paths);
		System.out.print(paths[0]);
		for(int i=1; i<paths.length; i++)
			System.out.print(" "+paths[i]);
		System.out.println();
	}
}

public class D38{
	static Scanner in;
	public static void main(String[] args){
		in=new Scanner(System.in);
		Graph g=new Graph(in.nextInt(), in.nextInt(), in.nextInt(), in);
		g.printPrim(0);
	}
}
