
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.TreeSet;

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int G, P;
	static TreeSet<Integer> set;
	
	public static void main(String[] args) throws IOException{
		init();
		solution();
	}
	
	static void solution() throws IOException {
		int count=0;
		for(int i=0;i<P;i++) {
			Integer g = set.floor(Integer.parseInt(br.readLine()));
			if(g==null) break;
			set.remove(g);
			count++;
		}
		System.out.println(count);
	}
	
	static void init() throws IOException {
		G = Integer.parseInt(br.readLine());
		P = Integer.parseInt(br.readLine());
		set = new TreeSet<>();
		for(int i=1;i<=G;i++) {
			set.add(i);
		}
	}

}
