import java.util.Scanner;

public class D26{
	static boolean[][][] cels;
	static int L, C;
	static final int visited=1;

	static int getBlobSize(int l, int c){
		if(c>=0 && c<C && l>=0 && l<L && !cels[visited][l][c]){
			cels[visited][l][c]=true;
			if(cels[0][l][c])
				return 1+getBlobSize(l+1, c)+getBlobSize(l+1, c+1)+getBlobSize(l+1, c-1)+getBlobSize(l-1, c)+getBlobSize(l-1, c+1)+getBlobSize(l-1, c-1)+getBlobSize(l, c+1)+getBlobSize(l, c-1);
		}
		return 0;
	}

	public static void main(String[] args){
		Scanner in= new Scanner(System.in);
		int N=in.nextInt();
		for(int n=0; n<N; n++){
			L=in.nextInt();
			C=in.nextInt();
			cels=new boolean[2][L][C];
			for(int l=0; l<L; l++){
				String line=in.next();
				for(int c=0; c<C; c++){
					if(Character.compare(line.charAt(c), '.')==0)
						cels[0][l][c]=false;
					else
						cels[0][l][c]=true;
				}
			}
			int maxBlob=getBlobSize(0,0);
			for(int l=0; l<L; l++)
				for(int now, c=0; c<C; c++){
					if(cels[1][l][c]==true)
						continue;
					else if((now=getBlobSize(l,c))>maxBlob)
						maxBlob=now;
				}
			System.out.println(maxBlob);
		}
	}
}
