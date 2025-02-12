
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public abstract class Main {
	static StringBuilder sb1 = new StringBuilder(); // preorder
	static StringBuilder sb2 = new StringBuilder(); // inorder
	static StringBuilder sb3 = new StringBuilder(); // postorder
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		Map<String, String[]> map = new HashMap<>();
		for(int i=0;i<n;i++) {
			String par = sc.next();
			String left = sc.next();
			String right = sc.next(); 
			String[] child = new String[] {left, right};
			map.put(par, child);
		}
		order("A", map);
		System.out.println(sb1+"\n"+sb2+"\n"+sb3);
		sc.close();

	}
	static void order(String par, Map<String, String[]> map) {
		sb1.append(par);
		String[] childs = map.get(par);
		for(int i=0;i<2;i++) {
			String child = childs[i];
			if(!child.equals(".")) order(child, map); // == 말고 항상 equals 쓰기
			if(i==0) sb2.append(par);
		}
		sb3.append(par);
	}
}