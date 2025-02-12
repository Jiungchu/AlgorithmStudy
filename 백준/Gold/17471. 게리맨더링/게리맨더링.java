
import java.io.*;
import java.util.*;

public class Main {
	static int[] populations;
	static int[][] edges;
	static int[] groups;
	static int minDiff=99999;
	static int n;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		populations = new int[n + 1];
		groups = new int[n + 1];
		edges = new int[n + 1][];
		// 입력
		String[] popInput = br.readLine().split(" ");
		for (int i = 0; i < n; i++) {
			int num = Integer.parseInt(popInput[i]);
			populations[i + 1] = num;
		}
		for (int i = 0; i < n; i++) {
			String[] edge = br.readLine().split(" ");
			int k = Integer.parseInt(edge[0]);
			edges[i + 1] = new int[k];
			for (int j = 0; j < k; j++) {
				edges[i + 1][j] = Integer.parseInt(edge[j + 1]);
			}
		}
		//
		dfs(1);
		if(minDiff != 99999) System.out.println(minDiff);
		else System.out.println(-1);
		br.close();
	}

	static void dfs(int num) {
		// 모든 점 할당이 끝나면
		if (num == n + 1) {
			// 각 그룹이 모두 연결되었는지 확인
			int index1 = 0;
			int index2 = 0; // 그룹 1, 그룹 2의 노드 인덱스를 하나씩 탐색
			for (int i = 1; i < n + 1; i++) {
				if (index1 != 0 && index2 != 0)
					break;
				if (groups[i] == 1)
					index1 = i;
				else
					index2 = i;
			}
			if(index1==0 || index2==0) return; // 둘 중 하나가 선택되지 않은 경우
			boolean[] visited = new boolean[n + 1];
			boolean valid = (bfs(index1, visited, 1) + bfs(index2, visited, 2)) == n;
			if (valid) {
				int population1=0;
				int population2=0;
				for (int i = 1; i < n + 1; i++) {
					int population =populations[i];
					if(groups[i]==1) population1+= population;
					else population2 += population;
				}
				int diff = Math.abs(population1-population2);
				if(diff<minDiff) {
					minDiff=diff;
				}
			}
			return;
		}
		// 모든 경우의 수를 고려
		groups[num] = 1;
		dfs(num + 1);
		groups[num] = 2;
		dfs(num + 1);
	}

	static int bfs(int start, boolean[] visited, int group) {

		Queue<Integer> q = new ArrayDeque<>();
		int count = 1; // 시작점을 포함한 count
		q.offer(start);
		visited[start] = true;
		while (!q.isEmpty()) {
			int curr = q.poll();
			for (int next : edges[curr]) {
				if (!visited[next] && groups[next] == group) {
					visited[next] = true;
					q.offer(next);
					count++;
				}
			}
		}
		return count;
	}

}
