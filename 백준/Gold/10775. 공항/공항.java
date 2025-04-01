
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.TreeSet;

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int G, P, parents[];
	
	public static void main(String[] args) throws IOException{
		init();
		solution();
	}
	
	static void solution() throws IOException {
		int count=0;
		for(int i=0;i<P;i++) {
			int num = find(Integer.parseInt(br.readLine()));
			if(num==0) break;
			// 현재 점 사용 처리. 아래 점과 연결
			union(num, num-1);
			count++;
		}
		System.out.println(count);
	}
	
	static void init() throws IOException {
		G = Integer.parseInt(br.readLine());
		P = Integer.parseInt(br.readLine());
		parents = new int[G+1];
		for(int i=1;i<=G;i++) {
			parents[i] = i;
		}
	}
	
	// 유니온 파인드를 통해 g_i가 들어왔을 때, 대응되는 가장 큰 점을 바로 return
	static int find(int x) {
		if(x==parents[x]) return x;
		return parents[x] = find(parents[x]);
	}
	
	static void union(int a, int b) {
		parents[find(a)] = parents[find(b)];
	}

}
