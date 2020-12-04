import java.util.Scanner;
import java.util.Stack;
import java.util.LinkedList;

public class D29{

	static Boolean[][] graph=new Boolean[26][26];
	static Boolean[] visited=new Boolean[26];
	static Stack<Integer> order=new Stack<>();

	static int pos(Character a){
		return (int)a.charValue()-(int)'A';
	}

	static void getGraph(String old, String now){
		Character a, b;
		for(int i=0; i<old.length(); i++){
			if((a=old.charAt(i)).equals(b=now.charAt(i)))
				continue;
			graph[pos(a)][pos(b)]=true;
			visited[pos(a)]=false;
			visited[pos(b)]=false;
			break;
		}
	}

	static void dfsOrder(int i){
		visited[i]=true;
		for(int j=0; j<26; j++)
			if(visited[j]!=null && !visited[j] && graph[i][j]!=null)
				dfsOrder(j);
		order.push(i);
	}

	static void printOrder(){
		while(!order.isEmpty())
			System.out.print(Character.valueOf((char)((int)'A'+order.pop().intValue())));
		System.out.println();
	}

	static void getOrder(){
		for(int i=0; i<26; i++)
			if(visited[i]!=null && !visited[i])
				dfsOrder(i);
		printOrder();
	}

	public static void main(String[] arg){
		Scanner in=new Scanner(System.in);
		int N=in.nextInt();
		String old, now=in.next();
		for(int n=0; n<N-1; n++){
			old=now;
			now=in.next();
			getGraph(old, now);
		}
		getOrder();
	}
}
