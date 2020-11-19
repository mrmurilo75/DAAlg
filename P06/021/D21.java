import java.util.TreeMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.AbstractMap;
import java.util.AbstractMap.SimpleEntry;

class D21{
	public static void main(String[] args){
		FastScanner in=new FastScanner(System.in);
		int A=in.nextInt();
		int R=in.nextInt();
		TreeMap<Integer,Integer> tree=new TreeMap<>();
		for(int i=0; i<A+R; i++){
			Map.Entry<Integer,Integer> kv;
			Integer amount, pow;
			String now=in.next();
			if(now.equals("MIN")){
				kv=new AbstractMap.SimpleEntry<Integer,Integer>(tree.pollFirstEntry());
				System.out.println(pow=kv.getKey());
				if((amount=kv.getValue())>1)
					tree.put(pow, amount-1);
			} else if(now.equals("MAX")){
				kv=new AbstractMap.SimpleEntry<Integer,Integer>(tree.pollLastEntry());
				System.out.println(pow=kv.getKey());
				if((amount=kv.getValue())>1)
					tree.put(pow, amount-1);
			} else if(now.equals("BAK")){
				pow=in.nextInt();
				if((amount=tree.get(pow))==null){
					amount=Integer.valueOf(0);
					//System.out.println(pow);
				}
				tree.put(pow, amount+1);
			}

		}
	}
}
