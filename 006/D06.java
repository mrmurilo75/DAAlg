import java.util.Scanner;

class Square{
	double x, y, l;
	public Square(double x,double  y,double  l){
		this.x=x;
		this.y=y;
		this.l=l;
	}
	void adjust(Circle cl){
		while(l<cl.r*2){
			

	double area(){
		return this.l*this.l;
	}
	int checkCorners(Circle cl){
		boolean b[]=new boolean[4];

		b[0]=cl.isInside(x+l,y+l);
		b[1]=cl.isInside(x,y+l);
	       	b[2]=cl.isInside(x+l,y);
		b[3]=cl.isInside(x,y);
		if(b[0] && b[1] && b[2] && b[3] )
			return 1;
		else if(!b[0] && !b[1] && !b[2] && !b[3] )
			return -1;
		else
			return 0;
	}
	void print(){
		System.out.println("x= "+x+"\ty= "+y+"\tl= "+l);
	}
}
class Circle{
	double x, y, r;
	public Circle(double x,double  y,double  r){
		this.x=x;
		this.y=y;
		this.r=r;
	}
	boolean isInside(double x,double  y){
		if(((x-this.x)*(x-this.x)+(y-this.y)*(y-this.y))<=r*r)
			return true;
		else
			return false;
	}
	void print(){
		System.out.println("x= "+x+"\ty= "+y+"\tr= "+r);
	}
}

public class D06{
	public static boolean isOut(Square sq, Circle cl);
		/*
		 * pra ver se o quadrado esta dentro do circulo é so ver se a circunf e a borda do quadrado se intersectam
		 * pra isso vemos a eq da reta (no caso da lateral vertical x=", e horizontal y..)
		 * e vemos tambem a equacao da cricunf ( (x+cx)^2 + (y+cy)^2 = r^2 )
		 * resolvemos como sistema e temos a inetrseccao 
		 */
	public static boolean isSqInCl(Square sq, Circle cl);
		/*
		 * so ver se as 4 bordas do quad estao dentro da area do circ
		 * aka. dist (bx, by) de r < r
		 */
	public static boolean isClInSq(Square sq, Circle cl);
		 /*
		  * primeiro ver se o centro esta dentro do quadrado 
		  * aka. qx <= cx <= qx+l "eq. p cy
		  * entao ver se cx-r >= qx ; cx+r >=qx+l "eq. p y
		  */

	public static double getArea(Square sq, Circle cl, double area){
		System.out.print("looking in square: ");
		sq.print();
		int cIn=sq.checkCorners(cl);
		System.out.println("cIn= "+cIn);
		if(cIn==1){
			System.out.println("returning "+area);
			return (area+=sq.area());
		} else if(cIn==-1){
			System.out.println("returning "+area);
			return area;
		} else if(sq.l>=0.25){
			area+=getArea(new Square(sq.x+sq.l,sq.y+sq.l,sq.l/2), cl, area);
			area+=getArea(new Square(sq.x+sq.l,sq.y,sq.l/2), cl, area);
			area+=getArea(new Square(sq.x,sq.y+sq.l,sq.l/2), cl, area);
			area+=getArea(new Square(sq.x,sq.y,sq.l/2), cl, area);
			System.out.println("returning "+area);
			return area;
		} else{ 
			System.out.println("returning "+area);
			return area;
		}
	}
	public static void main(String[] args){
		Scanner in=new Scanner(System.in);
		int N=in.nextInt();
		for(int n=0; n<N; n++){
			Square sq=new Square(in.nextDouble(), in.nextDouble(), in.nextDouble());
			Circle cl=new Circle(in.nextDouble(), in.nextDouble(), in.nextDouble());
			sq.adjust(cl);
			System.out.println(getArea(sq, cl, 0));
		}
	}
}
