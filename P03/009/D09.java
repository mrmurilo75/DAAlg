import java.util.Scanner;
import java.util.Arrays;

class Letter implements Comparable<Letter>{
	int a;
	int count;
	int pos;
	Letter(int A){
		a=A;
		count=0;
		pos=-1;
	}
	public int compareTo(Letter that){
		if(that.count<this.count)
			return -1;
		if(that.count>this.count)
			return 1;
		if(that.pos>this.pos)
			return -1;
		if(that.pos<this.pos)
			return 1;
		return 0;
	}
	public String toString(){
		return Character.valueOf((char) a)+" "+count;
	}
}


class D09{
	public static void main(String[] args){
		Scanner in=new Scanner(System.in);
		String ADN=in.next();
		Letter[] arr=new Letter[26];
		for(int i=0; i<26; i++)
			arr[i]=new Letter(i+'A');
		for(int i=0; i<ADN.length(); i++){
			if(arr[ADN.charAt(i)-'A'].pos<0)
				arr[ADN.charAt(i)-'A'].pos=i;
			arr[ADN.charAt(i)-'A'].count++;
		}
		Arrays.sort(arr);
		for(int i=0; i<26; i++)
			if(arr[i].count>0)
				System.out.println(arr[i]);
	}
}
