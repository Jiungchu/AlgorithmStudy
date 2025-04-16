
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

	static class Edge implements Comparable<Edge> {
		int num;
		int a;
		int b;
		int length;
		int time;

		public Edge(int num, int a, int b, int length) {
			this.num = num;
			this.a = a;
			this.b = b;
			this.length = length;
		}

		@Override
		public int compareTo(Edge o) {
			return this.time - o.time;
		}

	}

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	static int N, M;
	static List<List<List<Edge>>> edges;

	public static void main(String[] args) throws IOException {
		init();
		solution();
	}

	static void init() throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		edges = new ArrayList<>();
		for (int i = 0; i <= N; i++) {
			edges.add(new ArrayList<>());
			edges.get(i).add(new ArrayList<>());
			edges.get(i).add(new ArrayList<>());
		}
		int num = 0;
		// 노드의 왼족, 오른쪽에 연결된 경우를 나눠서 저장
		// 같은 노드에서 출발해서 같은 노드에서 끝날 수 있음
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			int length = Integer.parseInt(st.nextToken());
			// 양방향 연결
			Edge e = new Edge(num++, a, b, length);
			edges.get(b).get(0).add(e);
			edges.get(a).get(1).add(e);
		}
	}

	static void solution() {
		// 모든 시작점에 대해 한 번씩 진행
		double min = Double.MAX_VALUE;
		for (int i = 1; i <= N; i++) {
			double totalTime = 0;
			boolean[] burned = new boolean[M]; // 각 간선이 추가되는 시간 저장
			boolean[] isEnd = new boolean[M]; // 간선이 업데이트될 경우 끝났는지 관리
			boolean[] visited = new boolean[N + 1]; // 노드 방문 여부 저장

			PriorityQueue<Edge> pq = new PriorityQueue<>();
			for (int j = 0; j < 2; j++) {
				for (Edge e : edges.get(i).get(j)) {
					e.time = e.length; // 간선이 다 타는 시간을 저장
					burned[e.num] = true;
					pq.add(e);
				}
			}
			visited[i] = true;

			int curTime = 0;
			boolean finish = true;
			while (!pq.isEmpty()) {
				Edge e = pq.poll();
				if (isEnd[e.num])
					continue;
				int a = e.a, b = e.b;
				curTime = e.time;
				isEnd[e.num] = true;
				// 시간 업데이트. 아직 타고있는 간선을 종료 처리했으므로 Math.max로 계산
				totalTime = Math.max(totalTime, curTime);
				if(totalTime>min) {
					finish = false; break;
				}
				
				int next = -1;
				if (visited[a])
					next = b;
				else
					next = a;
				if(visited[next]) continue; // 같은 지점에서 시작하고 끝나는 경우
				visited[next] = true;
				
				for (int j = 0; j < 2; j++) {
					for (Edge ne : edges.get(next).get(j)) {
						if (!isEnd[ne.num]) {
							// 아직 타고있는 간선이 연결되어 있을 때, 남은 시간의 절반만 추가로 소요됨
							if (burned[ne.num]) {
								double rem = ne.time - curTime;
								totalTime = Math.max(curTime + rem / 2, totalTime);
								isEnd[ne.num] = true;
							} else {
								ne.time = curTime + ne.length;
								burned[ne.num] = true;
								pq.add(ne);
							}
						}

					}
				}
			}
			if(finish )min = Math.min(min, totalTime);
		}
		System.out.println(String.format("%.1f", min));
	}

}
