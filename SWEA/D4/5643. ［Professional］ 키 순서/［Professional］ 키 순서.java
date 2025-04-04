
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	
	static int t, N, M, count, ans;
	static List<List<Integer>> bigger, smaller; 
	
	public static void main(String[] args) throws IOException {
		int T= Integer.parseInt(br.readLine());
		for(t=1; t<=T;t++) {
			init();
			solution();
		}
		System.out.println(sb.toString());
	}
	
	static void solution() {
		for(int i=1;i<=N;i++) {
			count = 0; 
			boolean[] visited = new boolean[N+1];
			dfs(i, bigger, visited);
			dfs(i, smaller, visited);
			if(count==N-1) {
				ans++;
			}
		}
		
		sb.append("#").append(t).append(" ").append(ans).append("\n");
	}
	
	static void dfs(int n, List<List<Integer>> list, boolean[] visited) {
		visited[n] = true;
		for(int next : list.get(n)) {
			if(!visited[next]) {
				dfs(next, list, visited);
				count++;
			}
		}
	}
	
	static void init() throws IOException{
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		ans=0;
		
		bigger = new ArrayList<>();
		smaller = new ArrayList<>();
		for(int i=0;i<N+1;i++) {
			bigger.add(new ArrayList<>());
			smaller.add(new ArrayList<>());
		}
		
		for(int i=0;i<M;i++) {
			StringTokenizer st= new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			bigger.get(a).add(b);
			smaller.get(b).add(a);
		}
	}

}
