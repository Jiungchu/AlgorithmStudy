import java.util.*;
import java.io.*;
// 우선순위 큐를 사용하지 않아 불필요한 재귀가 너무 많이 일어남 
public class Main {
	static Map<Integer,List<int[]>> edges = new HashMap<>();
	static PriorityQueue<int[]> pq;
	static int[] paths;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int v = Integer.parseInt(st.nextToken());
		int e = Integer.parseInt(st.nextToken());
		int start = Integer.parseInt(br.readLine());
		for(int i=0;i<e;i++) {
			st = new StringTokenizer(br.readLine());
			int node1 = Integer.parseInt(st.nextToken());
			int node2 = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			if(!edges.containsKey(node1)) edges.put(node1, new ArrayList<>());
			edges.get(node1).add(new int[] {node2,weight});
		}
		paths = new int[v+1];
		Arrays.fill(paths, Integer.MAX_VALUE); // 연결 안 된 점에 큰 값 채우기
		paths[start] = 0; // 시작점 본인과의 거리는 0
		pq = new PriorityQueue<>((a,b)->Integer.compare(a[1],b[1]));
		pq.offer(new int[] {start,0});
		// 우선순위 큐이므로, 시작점에서 가장 가까운 순서대로 고려함
		while(!pq.isEmpty()) {
			int[] curr = pq.poll();
			int node = curr[0];
			int length = curr[1];
			// 현재 점으로부터의 간선들 확인
			// 현재 점에서 연결 가능한 간선이 없거나, 계산 과정에서 이미 최단거리가 갱신되었다면 continue
			if(!edges.containsKey(node) || length>paths[node]) continue;
			for(int[] edge:edges.get(node)) {
				int nextNode = edge[0];
				int weight = edge[1];
				// 갱신이 가능한 경우 다시 큐에 넣기
				if(paths[nextNode] > length+weight) {
					paths[nextNode] = length+weight;
					pq.offer(new int[] {nextNode,paths[nextNode]});
				}
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for(int i=1;i<v+1;i++) {
			if(!Integer.valueOf(paths[i]).equals(Integer.MAX_VALUE)) sb.append(paths[i]);
			else sb.append("INF");
			sb.append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}

}
