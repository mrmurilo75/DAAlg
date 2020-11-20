import java.util.Scanner;

class Node{
	boolean isBlack;
	boolean isNull;
	int value, blackCount;
	Node left, right;

	Node(int v){
		isNull=(v==0);
		isBlack=(v>=0);
		value=Math.abs(v);
	}

	boolean followRedProperty(){
		if(isBlack)
			return true;
		if(this.left.isBlack && this.right.isBlack)
			return true;
		return false;
	}

}

public class D22{

	static Node readPreOrder(Scanner in){
		int v=in.nextInt();
		Node aux=new Node(v);
		if(v!=0){
			aux.left=readPreOrder(in);
			aux.right=readPreOrder(in);
		}
		return aux;
	}

	static int maximum(Node t){
		if(t.isNull)
			return Integer.MIN_VALUE;
		int minLeft=maximum(t.left);
		int minRight=maximum(t.right);
		return Math.max(t.value, Math.max(minLeft, minRight));
	}

	static int size(Node t){
		if(t.isNull)
			return 0;
		return 1 + size(t.left) + size(t.right);
	}

	static boolean hasBlackProperty(Node root){
		root.blackCount=1;
		return (countBlacks(root)>0);
	}
	static int countBlacks(Node node){
		int left, right;
		if(node.left.isBlack)
			node.left.blackCount=node.blackCount+1;
		else
			node.left.blackCount=node.blackCount;
		if(node.right.isBlack)
			node.right.blackCount=node.blackCount+1;
		else
			node.right.blackCount=node.blackCount;
		if(node.left.isNull)
			left=node.left.blackCount;
		else
			left=countBlacks(node.left);
		if(node.right.isNull)
			right=node.right.blackCount;
		else
			right=countBlacks(node.right);
		return ( (left==right)? left : 0 );
	}

	static boolean hasRedProperty(Node root){
		if(root.isNull)
			return true;
		if(root.followRedProperty())
			return (hasRedProperty(root.left) && hasRedProperty(root.right));
		return false;
		
	}

	public static void main(String args[]){
		Scanner in=new Scanner(System.in);
		int n= in.nextInt();
		for(int i=0; i<n; i++){
			Node root=readPreOrder(in);
			if(root.isBlack &&  hasRedProperty(root) && hasBlackProperty(root))
				System.out.println("SIM");
			else
				System.out.println("NAO");
			//System.out.printf("Tree with %d nodes(min=%d, max=%d)\n", size(root), minimum(root), maximum(root));
		}
	}
}
