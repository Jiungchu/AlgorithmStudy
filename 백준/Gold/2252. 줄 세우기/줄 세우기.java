
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();

	static int N, M;
	static Map<Integer, List<Integer>> lower;
	static int[] inDegree;

	public static void main(String[] args) throws Exception {
		init();
		solution();
		System.out.println(sb.toString());
		br.close();
	}

	static void solution() {
		Queue<Integer> q = new ArrayDeque<>();
		// 진입 차수가 0인 점들만 저장
		for(int i=1;i<=N;i++) {
			if(inDegree[i]==0) q.offer(i);
		}
		// bfs
		while(!q.isEmpty()) {
			int curr = q.poll();
			if(lower.containsKey(curr)) {
				for(int next:lower.get(curr)) {
					inDegree[next]--;
					if(inDegree[next]==0) q.offer(next);
				}
			}
			sb.append(curr).append(" ");
		}
	}
	
	
	
	static void init() throws IOException{
		StringTokenizer st =new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		// 위상 정렬로 해결하기 위해, 단방향 간선 저장, 진입 차수 저장
		lower = new HashMap<>();
		inDegree = new int[N+1];
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int first = Integer.parseInt(st.nextToken());
			int end= Integer.parseInt(st.nextToken());
			if(!lower.containsKey(first)) lower.put(first, new ArrayList<>());
			lower.get(first).add(end);
			inDegree[end]++; // 진입 들어오는 쪽 진입 차수 하나 증가 
		}
	}

}
