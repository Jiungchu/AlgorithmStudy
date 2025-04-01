
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	static int N,M,K;
	static int[] parents, counts; 
	static List<int[]> groups;
	
	public static void main(String[] args) throws IOException{
		init();
		solution();
	}
	
	static void solution() {
		int dp[] = new int[K];
		for(int i=0;i<groups.size();i++) {
			int[] group = groups.get(i);
			int size = group[0], count = group[1];
			for(int s = K-1;s>=size;s--) {
				if(dp[s]<dp[s-size]+count) dp[s]=dp[s-size]+count;
			}
		}
		System.out.println(dp[K-1]);
	}
	
	static void init() throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		parents = new int[N+1];
		counts = new int[N+1];
		groups = new ArrayList<>();
		st = new StringTokenizer(br.readLine());
		for(int i=1;i<=N;i++) {
			counts[i] = Integer.parseInt(st.nextToken());
			// root에는 해당 그룹의 총 사람 수를 음수로 저장, root가 아니면 root 노드 번호를 저장
			parents[i] = -1; 
		}
		
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			union(a,b);
		}
		
		for(int i=1;i<=N;i++) {
			if(parents[i]<0) groups.add(new int[] {-parents[i], counts[i]});
		}
	}
	
	static int find(int x) {
		if(parents[x]<0) return x; 
		return parents[x] = find(parents[x]);
	} 
	
	static void union(int a, int b) {
		int pa = find(a);
		int pb = find(b);
		if(pa==pb) return;
		
		// 사탕 수, 그룹 크기 더해주기
		parents[pa] += parents[pb];
		counts[pa] += counts[pb];
		parents[pb] = pa;
	}

}
