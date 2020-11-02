import java.util.Scanner;
import java.util.TreeSet;

class Segment implements Comparable<Segment>{
	int start, end;
	Segment(int start, int end){
		this.start=start;
		this.end=end;
	}
	int size(){
		return end-start;
	}
	boolean isIn(Segment that){
		if(that.start>=this.start && that.end<=this.end)
			return true;
		return false;
	}
	public int compareTo(Segment that){
		if(this.start==that.start){
			if(this.size()==that.size())
				return 0;
			if(this.size()<that.size())
				return -1;
			return 1;
		}
		if(this.start<that.start)
			return -1;
		return 1;
	}
}

class D13{
	public static void main(String[] args){
		Scanner in=new Scanner(System.in);
		int M =in.nextInt();
		int N=in.nextInt();
		TreeSet<Segment> S=new TreeSet<Segment>();
		for(int i=0; i<N; i++){
			int L, R;
			if((L=in.nextInt())>=M){
				in.nextInt();
				continue;
			}
			if((R=in.nextInt())>=M){
				S.add(new Segment(L, M));
				continue;
			}
			S.add(new Segment(L, R));
		}


		System.out.println(S.size());
	}
}

