import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	static int V, distance[];
	static List<List<int[]>> edges;
	
	public static void main(String[] args) throws IOException {
		init();
		solution();
	}
	
	static void solution() {
		int idx = bfs(1); // 일단 1에서 시작
		int maxIdx = bfs(idx);
		System.out.println(distance[maxIdx]);
	}
	
	static int bfs(int start) {
		int max = 0;
		int maxIdx = -1;
		boolean[] visited = new boolean[V+1];
		
		Queue<Integer> q = new ArrayDeque<>();
		q.offer(start);
		visited[start] = true;
		distance[start] = 0;
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			int dist = distance[cur];
			if(dist>max) {
				max = dist;
				maxIdx = cur;
			}
			
			for(int[] edge : edges.get(cur)) {
				int next = edge[0], weight=edge[1];
				if(!visited[next]) {
					visited[next] = true;
					distance[next] = dist+weight;
					q.offer(next);
				}
			}
		}
//		System.out.println(Arrays.toString(distance));
		return maxIdx;
	}
	
	static void init() throws IOException{
		V = Integer.parseInt(br.readLine());
		distance = new int[V+1]; // 시작점에서 각 점까지의 거리
		
		edges = new ArrayList<>();
		for(int i=0;i<=V;i++) {
			edges.add(new ArrayList<>());
		}
		
		for(int i=1;i<=V;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			while(true) {
				int a = Integer.parseInt(st.nextToken());
				if(a==-1) break;
				int w = Integer.parseInt(st.nextToken());
				edges.get(num).add(new int[] {a,w});
			}
		}
	}

}
