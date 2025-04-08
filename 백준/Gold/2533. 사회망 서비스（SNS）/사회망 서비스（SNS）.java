
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	static int N, dp[][]; 
	static boolean[] visited;
	static List<List<Integer>> edges;
	
	public static void main(String[] args) throws IOException {	
		init();
		solution();
	}

	static void solution() {
		// 어디를 루트로 잡아도 관계 x
		dfs(1);
		System.out.println(Math.min(dp[1][0], dp[1][1]));
	}
	
	static void dfs(int node) {
		visited[node] = true;
		dp[node][0] = 0;
		dp[node][1] = 1; // 1이 얼리어답터를 의미. 따라서 dp에 저장된 count를 ++
		for(int child : edges.get(node)) {
			if(!visited[child]) {
				dfs(child);
				dp[node][0] += dp[child][1]; // 해당 노드가 얼리어답터가 아니면 자식은 반드시 얼리어답터
				dp[node][1] += Math.min(dp[child][0], dp[child][1]); // 얼리어답터이면 관련 x
			}
		}
	}
	
	static void init() throws IOException{
		N = Integer.parseInt(br.readLine());
		dp = new int[N+1][2];
		visited = new boolean[N+1];
		
		edges = new ArrayList<>();
		for(int i=0;i<=N;i++) {
			edges.add(new ArrayList<>());
		}
		for(int i=0;i<N-1;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			edges.get(a).add(b);
			edges.get(b).add(a);
		}
	}
}
