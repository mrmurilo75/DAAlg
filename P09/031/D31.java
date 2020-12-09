import java.util.Scanner;
import java.util.LinkedList;
import java.util.Queue;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Iterator;
import java.util.Arrays;

class Pair<X, Y>{
	X x;
	Y y;
	Pair(X x, Y y){
		this.x = x;
		this.y = y;
	}
}

class CloudMap{
	int rows, cols;
	String[] map;
	TreeSet<Integer> minDistances;
	Integer[][] count;
	LinkedList<Pair<Integer, Integer>> queue;

	CloudMap(int L, int C){
		rows=L;
		cols=C;
		map=new String[L];
		queue = new LinkedList<>();
	}

	public void putLine(int i, String line){
		map[i]=line;
		for(int j=0; j<cols; j++){
			boolean check;
			if(check=(Character.compare(line.charAt(j), '#')==0))
				queue.add(new Pair<Integer, Integer>(i, j));
		}
	}

	public boolean isInBound(int x, int y){
		return (x<rows && x>=0 && y<cols && y>=0);
	}

	public void printCount(){
		for(int i=0; i<rows; i++){
			for(int j=0; j<cols; j++)
				System.out.print(count[i][j]+"\t");
			System.out.println();
		}
	}

	public void fillDistances(){
		minDistances = new TreeSet<>();
		count = new Integer[rows][cols];
//		Iterator<Pair<Integer, Integer>> ashes = queue.iterator(); // WHY WONT THIS FUCKING WOOORRKKKKKKKKK
		LinkedList<Pair<Integer, Integer>> ashes = new LinkedList<>();
		Pair<Integer, Integer> now;
		while((now=queue.poll())!=null){
			count[now.x][now.y]=0;
			ashes.add(now);
		}
		queue = ashes;
					// UGLY WORKAROUD bc Iterator wouldnt fucking work
//		for(now=ashes.next(); ashes.hasNext(); now=ashes.next())
//			count[now.x][now.y]=0;
		while((now=queue.poll())!=null){
			if(Character.compare(map[now.x].charAt(now.y), 'A')==0)
				minDistances.add(count[now.x][now.y]);
			if(isInBound(now.x, now.y+1) && (count[now.x][now.y+1]==null || (count[now.x][now.y+1]!=null && count[now.x][now.y+1]>count[now.x][now.y]+1))){
				count[now.x][now.y+1]=count[now.x][now.y]+1;
				queue.add(new Pair<Integer, Integer>(now.x, now.y+1));
			}
			if(isInBound(now.x, now.y-1) && (count[now.x][now.y-1]==null || (count[now.x][now.y-1]!=null && count[now.x][now.y-1]>count[now.x][now.y]+1))){
				count[now.x][now.y-1]=count[now.x][now.y]+1;
				queue.add(new Pair<Integer, Integer>(now.x, now.y-1));
			}
			if(isInBound(now.x+1, now.y) && (count[now.x+1][now.y]==null || (count[now.x+1][now.y]!=null && count[now.x+1][now.y]>count[now.x][now.y]+1))){
				count[now.x+1][now.y]=count[now.x][now.y]+1;
				queue.add(new Pair<Integer, Integer>(now.x+1, now.y));
			}
			if(isInBound(now.x-1, now.y) && (count[now.x-1][now.y]==null || (count[now.x-1][now.y]!=null && count[now.x-1][now.y]>count[now.x][now.y]+1))){
				count[now.x-1][now.y]=count[now.x][now.y]+1;
				queue.add(new Pair<Integer, Integer>(now.x-1, now.y));
			}
		}
	}

	public int getMinDistance(){
		if(minDistances==null)
			fillDistances();
		return minDistances.first();
	}

	public int getMaxDistance(){
		if(minDistances==null)
			fillDistances();
		return minDistances.last();
	}
}


public class D31 {
	public static void main(String args[]) {
		Scanner in = new Scanner(System.in);
		int L, C;
		CloudMap mm = new CloudMap(L=in.nextInt(), C=in.nextInt());
		for(int i=0; i<L; i++)
			mm.putLine(i, in.next());
		System.out.println(mm.getMinDistance()+" "+mm.getMaxDistance());

	}
}
