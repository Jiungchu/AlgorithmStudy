
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Solution {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();

	static int t, N, ans;
	static Map<Integer, int[]> curr, next;

	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		for (t = 1; t <= T; t++) {
			init();
			solution();
		}
		System.out.println(sb.toString());
	}

	static int[] dx = { 0, 0, -1, 1 }, dy = { 1, -1, 0, 0 };
	static int[] pair = { 1, 0, 3, 2 };

	static void solution() {
		// map 2개를 사용
		while (!next.isEmpty()) {
			curr = next;
			next = new HashMap<>();
			for (int key : curr.keySet()) {
				int[] atom = curr.get(key);
				if (atom != null) {
					int d = atom[0], k = atom[1];
					// 사라질 원자 처리. 3개의 원자가 한 자리에서 만날 수도 있으므로
					if (atom[2] < 0) {
						ans += atom[1];
						continue;
					}
					int x = (key >> 15) + dx[d];
					int y = (key & ((1 << 13) - 1)) + dy[d];
					// 경계에 닿은 경우 영원히 소멸되지 않음
					if (x < 0 || x > 2000 || y < 0 || y > 2000) {
						continue;
					}
					int nextKey = (x << 15) + y;
					// 같은 시점에 같은 위치에 도착한 원자가 있는 경우
					int[] natom = next.get(nextKey);
					int[] catom = curr.get(nextKey);
					if (catom != null && catom[0] == pair[d]) { // 이동 도중에 만날 경우
						ans += atom[1];
						catom[2] = -1;
					} else if (natom != null) {
						natom[1] += k; // 에너지 더하기
						natom[2] = -1; // 다음 시점에 사라지도록 처리
					} else {
						next.put(nextKey, new int[] { d, k, 1 });
					}
				}
			}
		}

		sb.append("#").append(t).append(" ").append(ans).append("\n");
	}

	static void init() throws IOException {
		N = Integer.parseInt(br.readLine());
		next = new HashMap<>();
		ans = 0;
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) + 1000;
			int y = Integer.parseInt(st.nextToken()) + 1000;
			int d = Integer.parseInt(st.nextToken());
			int k = Integer.parseInt(st.nextToken());

			// 위치를 key로 하는 map에 원자 정보 저장
			int location = (x << 15) + y;
			next.put(location, new int[] { d, k, 1 });
		}
	}

}
