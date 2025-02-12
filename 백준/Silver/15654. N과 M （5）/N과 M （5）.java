
import java.util.*;

public class Main {
	static StringBuilder sb = new StringBuilder();
	static Set<Integer> set = new HashSet<>();

	static void next(int[] visited, String currString, List<Integer> list, int n, int m, int count) {
		if (count == m) {
			sb.append(currString + "\n");
			return;
		}
		for (int i = 0; i < n; i++) {
			if(visited[i]==1) continue;
			int[] next_visited = visited.clone();
			String nextString = currString + " " + list.get(i);
			next_visited[i]=1;
			next(next_visited, nextString, list, n, m, count + 1);
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int m = sc.nextInt();
		for (int i = 0; i < n; i++) {
			set.add(sc.nextInt());
		}
		List<Integer> list = new ArrayList<>(set);
		Collections.sort(list);
		// 위치 정보를 어떻게 유지할 것?
		// 배열을 만드려면 수열 한 번마다 배열을 계속해서 깊은복사 해야 함
		for (int i = 0; i < n; i++) {
			int[] visited=new int[n];
			visited[i]=1;
			next(visited, list.get(i) + "", list, n, m, 1);
		}

		System.out.println(sb.toString());
		sc.close();

	}

}
