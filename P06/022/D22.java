import java.util.Scanner;

class Node{
	boolean isBlack, isNull;
	int value, blackCount, max=Integer.MAX_VALUE, min=Integer.MIN_VALUE;
	Node left, right;

	Node(int v){
		isNull=(v==0);
		isBlack=(v>=0);
		value=Math.abs(v);
	}

	void initializeBST(){
		if(this.isNull)
			return;
		this.max=this.value;
		this.min=this.value;
	}

	boolean followsBSTProperty(){
		if(this.isNull)
			return true;
		if(!this.left.isNull){
			this.left.max=this.value;
			this.left.min=this.min;
		}
		if(!this.right.isNull){
			this.right.max=this.max;
			this.right.min=this.value;
		}
			if(!(right.value>=right.min && right.value<=right.max) || !(left.value<=left.max && left.value>=left.min))
				return false;
		return true;
	}

	boolean followsRedProperty(){
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

	static boolean hasAllProperties(Node root){
		root.blackCount=1;
		//root.initializeBST();
		return (countBlacks(root, 1)>0);
	}
	static int countBlacks(Node node, int parentBlackCount){
		//System.out.print(node.value);
		if(node.isNull)
			return parentBlackCount+1;
		if(!node.followsBSTProperty())
			return 0;
		//System.out.print("\tBST true");
		node.blackCount=parentBlackCount;
		if(node.isBlack)
			node.blackCount++;
		if(!node.followsRedProperty())
			return 0;
		//System.out.print("\tRedP true");
		//System.out.println();
		int leftC, rightC;
		return ( ((leftC=countBlacks(node.left, node.blackCount))==0 || (rightC=countBlacks(node.right, node.blackCount))==0 || leftC!=rightC)? 0 : leftC );
	}

	public static void main(String args[]){
		Scanner in=new Scanner(System.in);
		int n= in.nextInt();
		for(int i=0; i<n; i++){
			Node root=readPreOrder(in);
			if(root.isBlack && hasAllProperties(root))
				System.out.println("SIM");
			else
				System.out.println("NAO");
			//System.out.printf("Tree with %d nodes(min=%d, max=%d)\n", size(root), minimum(root), maximum(root));
		}
	}
}
