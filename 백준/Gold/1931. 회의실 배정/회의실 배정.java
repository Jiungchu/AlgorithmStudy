
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	// 회의가 끝나는 시간을 기준으로 묶기
	static int N;
	static Map<Integer, Integer> zeroCount, shortCons;
	static List<Integer> endtimes;

	public static void main(String[] args) throws IOException {
		init();
		solution();
	}

	static void init() throws IOException {
		N = Integer.parseInt(br.readLine());
		zeroCount = new HashMap<>();
		shortCons = new HashMap<>();
		endtimes = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());

			// 끝나는 시간을 기준으로 그룹핑
			if (!shortCons.containsKey(end)) {
				zeroCount.put(end, 0);
				shortCons.put(end, Integer.MIN_VALUE);
				endtimes.add(end);
			}
			// 끝나는 시간이 같은 회의 중 가장 짧은 회의만 저장
			// 시작 시간과 끝나는 시간이 같지 않은 이상 끝나는 시간이 같은 회의는 하나만 있음
			if (start == end)
				zeroCount.put(end, zeroCount.get(end) + 1);
			else if (shortCons.get(end) < start) {
				shortCons.put(end, start);
			}
		}
	}

	static void solution() {
		int count = 0;
		int lastTime = 0;

		// 끝나는 시간이 빠른 회의부터 순서대로 확인
		Collections.sort(endtimes);
		for (int time : endtimes) {

			int startTime = shortCons.get(time);
			if (startTime != Integer.MIN_VALUE && startTime >= lastTime) {
				count++;
				lastTime = time;
			}

			// 시작 시간과 끝나는 시간이 같은 회의는 바로 count
			int zero = zeroCount.get(time);
			count += zero;
			if(zero!=0) lastTime = time;

		}
		System.out.println(count);
	}
}
