import java.util.Scanner;
import java.util.Arrays;

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
			if(this.end==that.end)
				return 0;
			if(this.end>that.end)
				return -1;
			return 1;
		}
		return (this.start<that.start)? -1 : 1;
	}
	public String toString(){
		return "("+start+", "+end+")"; 
	}
}

class D13{
	public static void main(String[] args){
		Scanner in=new Scanner(System.in);
		int M =in.nextInt();
		int N=in.nextInt();
		Segment[] S=new Segment[N];
		for(int i=0; i<N; i++){
			int L, R;
			if((L=in.nextInt())>=M){
				in.nextInt();
				continue;
			}
			if((R=in.nextInt())>=M){
				S[i]=new Segment(L, M);
				continue;
			}
			S[i]=new Segment(L, R);
		}
		Arrays.sort(S);
//		System.out.println(Arrays.toString(S));
		int count=1;
		Segment now=S[0];
		for(int i=1; i<N && now.end<M; i++){
			if(S[i].start>=now.end){
				if(S[i].start==now.end)
					now=S[i];
				else
					now=S[--i];
				count++;
			}
		}
		System.out.println(count);
		
	}
}

