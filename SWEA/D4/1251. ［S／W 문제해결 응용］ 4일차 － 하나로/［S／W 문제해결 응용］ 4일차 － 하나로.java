
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();

	static int N, nodes[][], parents[], t;
	static long[][] edges;
	static double E;
	
	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(br.readLine());
		for (t = 1; t <= T; t++) {
			init();
			solution();
		}
		System.out.println(sb.toString());
		br.close();
	}

	static void solution() {
		// overflow가 발생할 수 있으므로 long 사용
		double sum=0; 
		// 우선순위 큐 사용
		PriorityQueue<long[]> pq = new PriorityQueue<>((a,b)->Long.compare(a[1], b[1]));
		boolean[] visited = new boolean[N];
		pq.add(new long[] {0,0});
		while(!pq.isEmpty()) {
			long[] curr = pq.poll();
			int num = (int) curr[0];
			long weight = curr[1];
			if(visited[num]) continue;
			
			sum += weight*E;
			visited[num]=true;
			for(int i=0;i<N;i++) {
				if(!visited[i]) {
					pq.add(new long[] {i,edges[num][i]});
				}
			}
			
		}
		
		// 값 손실을 최소화하기 위해 Math.round 사용
		sb.append("#").append(t).append(" ").append(Math.round(sum)).append("\n");
	}

	static void init() throws IOException {
		N = Integer.parseInt(br.readLine());
		nodes = new int[N][2];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			int a = Integer.parseInt(st.nextToken());
			nodes[i][0] = a; 
		}
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			int a = Integer.parseInt(st.nextToken());
			nodes[i][1] = a; 
		}
		E = Double.parseDouble(br.readLine());

		// 인접 행렬로 간선 표현
		edges =new long[N][N];
		for(int i=0;i<N-1;i++) {
			for(int j=i+1;j<N;j++) {
				long weight = (long)Math.pow(nodes[i][0]-nodes[j][0],2)+(long)Math.pow(nodes[i][1]-nodes[j][1],2);
				edges[i][j]=weight;
				edges[j][i]=weight;
			}
		}

	}

}
