import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	public static class Node{
		String name;
		boolean isIn;
		public Node(String name, boolean isIn){
			this.name = name;
			this.isIn = isIn;
		}
	}
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	
	static Map<String, Node> db = new HashMap<>();
	static List<String> list = new ArrayList<>();
	static Node[] nodeList;
	static int N, index;
	
	public static void main(String[] args) throws IOException{
		N = Integer.parseInt(br.readLine());
		init();
		
		for(String key : db.keySet()) {
			Node curr = db.get(key);
			if(curr.isIn) list.add(curr.name);
		}
		Collections.sort(list, Collections.reverseOrder());
		for(int i=0;i<list.size();i++) {
			sb.append(list.get(i)).append("\n");
		}
		System.out.println(sb.toString());
	}
	
//	static int hashing(String str) {
//		int hashVal = 0;
//		int p=53, pPow = 1;
//		int m= (int)1e9+9;
//		for(int i=0;i<str.length();i++) {
//			hashVal += ((str.charAt(i)-'A'+1)*pPow) % m;
//			pPow = (pPow*p) % m;
//		}
//		return hashVal;
//	}
	
	static void init() throws IOException{
		nodeList = new Node[N];
		for(int i=0;i<N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String name = st.nextToken();
			String direction = st.nextToken();
//			int hash = hashing(name);
			if(!db.containsKey(name))
				db.put(name, new Node(name,true));
			else if(direction.startsWith("l")) db.get(name).isIn=false;
			else db.get(name).isIn=true;
		}
	}
}
