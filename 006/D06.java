import java.util.Scanner;

class DMath{
	double squaredDist(double x1, double y1, double x2, double y2){
		return ((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2));
	}
	double min(double a, double b){
		if(a<=b)
			return a;
		else
			return b;
	}
	double abs(double x){
		if(x>=0)
			return x;
		else
			return -x;
	}
}

class Square{
	double x, y, l;
	public Square(double x,double  y,double  l){
		this.x=x;
		this.y=y;
		this.l=l;
	}

	double area(){
		return this.l*this.l;
	}

	boolean isInCircle(Circle cl){
		return (cl.isIn(x+l,y+l) && cl.isIn(x,y+l) && cl.isIn(x+l,y) && cl.isIn(x,y));
	}

	boolean isThisInSquare(Square sq){
		return (sq.isIn(x+l,y+l) && sq.isIn(x,y+l) && sq.isIn(x+l,y) && sq.isIn(x,y));
	}

	void print(){
		System.out.println("x= "+x+"\ty= "+y+"\tl= "+l);
	}

	boolean isIn(double x, double y){
		return (x>=this.x && x<=this.x+l && y>=this.y && y<=this.y+l);
	}

	boolean isOut(Circle cl){
		return (!cl.isIn(x+l, y+l) && !cl.isIn(x, y+l) && !cl.isIn(x+l, y) && !cl.isIn(x, y));
	}

	boolean boolIntersectSquare(Square sq){
		return (sq.isIn(x+l, y+l) || sq.isIn(x, y+l) || sq.isIn(x+l, y) || sq.isIn(x, y) || sq.x==x || sq.y==y || sq.x+sq.l==x+l || sq.y+sq.l==y+l);
	}

}

class Circle{
	double x, y, r;
	Square sq;
	public Circle(double x,double  y,double  r){
		this.x=x;
		this.y=y;
		this.r=r;
		this.sq=new Square(x-r, y-r, 2*r);
	}
	double area(){
		return 3.14159*r*r;
	}

	boolean isIn(double x,double  y){
		if(((x-this.x)*(x-this.x)+(y-this.y)*(y-this.y))<=r*r)
			return true;
		else
			return false;
	}

	boolean isSquareOut(Square sq){
		if(sq.isIn(x,y))
			return false;
		// System.out.println("testing isSquareOut");
		if(sq.boolIntersectSquare(this.sq)){
			// System.out.println("sq.intersectSq(this.sq)=TRUE");
			return (!isIn(sq.x+sq.l, sq.y+sq.l) && !isIn(sq.x, sq.y+sq.l) && !isIn(sq.x+sq.l, sq.y) && !isIn(sq.x, sq.y));
		}
		return false;
	}

	boolean isInSquare(Square sq){
		return this.sq.isThisInSquare(sq);
	}

	void print(){
		System.out.println("x= "+x+"\ty= "+y+"\tr= "+r);
	}
}

public class D06{

	public static double getArea(Square sq, Circle cl){
		if(cl.isInSquare(sq))
			return cl.area();
		return intersect(sq, cl);
	}

	public static double intersect(Square sq, Circle cl){
		if(cl.isSquareOut(sq))
				return 0;
		if(sq.isInCircle(cl))
			return sq.area();
		if(sq.area()<=0.025){
			if(cl.isIn(sq.x+sq.l/2, sq.y+sq.l/2))
				return sq.area();
			else
				return 0;
			}
		return (intersect(new Square(sq.x, sq.y, sq.l/2), cl)+intersect(new Square(sq.x+sq.l/2, sq.y, sq.l/2), cl)+intersect(new Square(sq.x, sq.y+sq.l/2, sq.l/2), cl)+intersect(new Square(sq.x+sq.l/2, sq.y+sq.l/2, sq.l/2), cl));
	}
	public static void main(String[] args){
		Scanner in=new Scanner(System.in);
		int N=in.nextInt();
		for(int n=0; n<N; n++){
			Square sq=new Square(in.nextDouble(), in.nextDouble(), in.nextDouble());
			Circle cl=new Circle(in.nextDouble(), in.nextDouble(), in.nextDouble());
			System.out.println(getArea(sq, cl));
		}
	}
}
