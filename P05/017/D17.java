import java.util.Scanner;
import java.util.Arrays;
import java.util.TreeSet;

class IntPair implements Comparable<IntPair>{
        int line, row;
        IntPair(int line, int row){
                this.line=line;
                this.row=row;
        }
        public int compareTo(IntPair that){
                if(this.line<that.line)
                        return -1; 
                if(this.line>that.line)
                        return 1;
                if(this.line==that.line){
                        if(this.row<that.row)
                                return -1; 
                        if(this.row>that.row)
                                return 1;
                }
                return 0;
        }
}


class D17{
        static void printPyr(Long[][] pyr, int pSize){
                for(int i=0; i<pSize; i++)
                        System.out.println(Arrays.toString(pyr[i]));
        }

        static long getWays(Long[][] pyr, int pSize, int nowLine, int nowRow, TreeSet<IntPair> D){
                if(D.contains(new IntPair(nowLine, nowRow))){
                                pyr[nowLine][nowRow]=(long)0;
//                                printPyr(pyr,pSize);
                                return 0;
                }
                if(pyr[nowLine][nowRow]==null){
                        long res=0;
                        if(nowLine==pSize-1){
                                pyr[nowLine][nowRow]=(long)1;
                                return 1;
                        }
                        res+=(pyr[nowLine+1][nowRow]=getWays(pyr, pSize, nowLine+1, nowRow, D));
                        res+=(pyr[nowLine+1][nowRow+1]=getWays(pyr, pSize, nowLine+1, nowRow+1, D));
                        pyr[nowLine][nowRow]=res;
//                        printPyr(pyr,pSize);
                        return res;
                }
                return pyr[nowLine][nowRow];
        }
        public static void main(String[] args){
                Scanner in=new Scanner(System.in);
                int N=in.nextInt();
                int d=in.nextInt();
                //IntPair[] D=new IntPair[d];
                TreeSet<IntPair> D=new TreeSet<IntPair>();
                for(int i=0; i<d; i++)
                        D.add(new IntPair(N-in.nextInt(), in.nextInt()-1));
                        //D[i]=new IntPair(in.nextInt(), in.nextInt());
                //Arrays.sort(D);
                Long[][] pyramid=new Long[N][N];
                System.out.println(getWays(pyramid,N,0,0,D));
//                for(int i=0; i<N; i++)
//                        System.out.println(Arrays.toString(pyramid[i]));

        }
}

