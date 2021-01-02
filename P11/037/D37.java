import java.util.*;

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

class Node implements Comparable<Node>{
	public LinkedList<Node> adjList;
	Pair<Integer, Integer> pos;
	int index;
	double distance=Double.POSITIVE_INFINITY;
	Node parent;

	Node(Integer x, Integer y, int index){
		this.index =index;
		adjList =new LinkedList<>();
		pos =new Pair<>(x, y);
	}

	double getDistance(Node that){
		int x1=this.pos.x, x2=that.pos.x, y1=this.pos.y, y2=that.pos.y;
		return  Math.sqrt( (double) ((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2)) );
	}

	public int compareTo(Node that){
		int res=Double.compare(this.distance, that.distance);
		if(res==0)
			return Integer.compare(this.index, that.index);
		return res;
	}

	public String toString(){
		return "node["+index+"]";
	}
}

class Graph {
	int size;
	Node[] nodes;
	TreeSet<Node> unused;

	Graph(int size, Scanner in) {
		this.size = size;
		nodes = new Node[size];
		unused =new TreeSet<>();
		for (int i=0; i<size; i++){
			nodes[i] = new Node(in.nextInt(), in.nextInt(), i);
			unused.add(nodes[i]);
		}
//		System.out.println(Arrays.toString(nodes));
//		for(Node a : unused)
//			System.out.print(a+", ");
//		System.out.println();
	}

	void printPrim(Integer inicial){
		nodes[inicial].distance=0.0;
		unused.remove(nodes[inicial]);
		unused.add(nodes[inicial]);
		while(!unused.isEmpty()){
			Node now=unused.pollFirst();
			for(int i=0; i<size; i++){
				if(i==now.index) continue;
				double dist;
				Node next=nodes[i];
				if(unused.contains(next) && (dist=now.getDistance(next))<next.distance){
//					System.out.println("now="+now.index+"\tnext="+next.index+"\tdist="+dist);
					unused.remove(next);	//Indirect help from ANA MENDEZ on debug
					next.parent =now;
					next.distance =dist;
					unused.add(next);
				}
//				for(Node a : unused)
//					System.out.print(a+", ");
//				System.out.println();
			}
		}
		double sum=0;
		for(int i=0; i<size; i++){
//			System.out.println(i+"\t"+nodes[i].distance+"\t"+nodes[i].parent);
			sum+=nodes[i].distance;
		}
		System.out.println(sum);
	}
}

public class D37{
	static Scanner in;
	public static void main(String[] args){
		in=new Scanner(System.in);
		int size=in.nextInt();
		Graph g=new Graph(size, in);
		g.printPrim(0);
	}
}
