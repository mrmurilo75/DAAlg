import java.util.Scanner;

class Square{
	int x, y, l;
	Square(x, y, l){
		this.x=x;
		this.y=y;
		this.l=l;
	}
	boolean checkBorder(Circle cl){

	}


public class D06{
	public static int getArea(Square sq, Circle cl){

	}
	public static void main(String[] args){
		Scanner in=new Scanner(System.in);
		int N=in.nextInt();
		for(int n=0; n<N; n++){
			Square sq=new Square(in.nextInt(), in.nextInt(), in.nextInt());
			Circle cl=new Circle(in.nextInt(), in.nextInt(), in.nextInt());
			System.out.println(getArea(sq, cl));
		}
	}
}
