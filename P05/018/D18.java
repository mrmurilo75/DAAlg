import java.util.Scanner;
import java.util.Arrays;

class D18{
	static int[] petty;
	static Integer[] coins;
	static Integer[] count;
	static Integer[] use;
	static Integer[] used;
	static int minCount(int[] possib, int total){
		int min=0;
		for(int i=1; i<coins.length; i++)
			if(possib[i]<possib[min])
				min=i;
		use[total]=coins[min];
		return possib[min];
	}
	static int getCoins(int total){
		if(total<0)
			return 10001;
		if(total==0)
			return 0;
		if(count[total]==null){
			int[] possib=new int[coins.length];
			for(int i=0; i<coins.length; i++)
				possib[i]=getCoins(total-coins[i]);

			count[total]=1+minCount(possib, total);
		}
		return count[total];
	}
	static void fillPetty(int total){
		petty=new int[total];
		for(int i=0; i<total; i++)
			petty[i]=i;
	}
	static void fillUsed(int total){
//		fillPetty(count.length);
//		System.out.println("\t"+Arrays.toString(petty));
//		System.out.println("count=\t"+Arrays.toString(count));
//		System.out.println("use=\t"+Arrays.toString(use));
		used=new Integer[count[total]];
		for(int i=0; i<used.length; i++){
			used[i]=use[total];
			total-=use[total];
		}
		Arrays.sort(used);
	}
	static void printChange(int Q){
		System.out.print(Q+": ["+used.length+"]");
		for(int i=0; i<used.length; i++)
			System.out.print(" "+used[i]);
		System.out.println();
	}
	public static void main(String[] args){
		Scanner in=new Scanner(System.in);
		int N=in.nextInt();
		coins=new Integer[N];
		for(int i=0; i<N; i++)
			coins[i]=in.nextInt();
		int P=in.nextInt();
		while((--P)>=0){
			int Q=in.nextInt();
			count=new Integer[Q+1];
			use=new Integer[Q+1];
			getCoins(Q);
			fillUsed(Q);
			printChange(Q);
		}
	}
}
