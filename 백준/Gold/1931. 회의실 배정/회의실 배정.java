import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	// 회의가 끝나는 시간을 key로 하고, 회의 시작 시간이 담긴 우선순위 큐를 사용
	static Map<Integer, PriorityQueue<Integer>> map = new HashMap<>();
	static int N, ans;

	public static void main(String[] args) throws IOException {
		init();
		solution();
		System.out.println(ans);
		br.close();
	}

	static void solution() {
		List<Integer> sortedKeys = new ArrayList<>(map.keySet());
		Collections.sort(sortedKeys);
		int prevEnd = 0;
		for (int endTime : sortedKeys) {
			// 현재 종료 시간 중 가장 늦게 시작하는 회의
			int startTime = map.get(endTime).poll();
			// 시작과 끝이 같은 회의가 있는 경우
			while (startTime == endTime && !map.get(endTime).isEmpty()) {
				startTime = map.get(endTime).poll();
				ans++;
			}
			if (startTime < prevEnd)
				continue; // 마지막 회의가 끝난 시간보다 늦게 시작한 경우
			ans++;
			prevEnd = endTime;
		}
	}

	static void init() throws IOException {
		N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			// 내림차순으로 정렬되도록 우선순위 큐 사용
			if (!map.containsKey(end))
				map.put(end, new PriorityQueue<>((a, b) -> -Integer.compare(a, b)));
			map.get(end).add(start);
		}
	}

}
