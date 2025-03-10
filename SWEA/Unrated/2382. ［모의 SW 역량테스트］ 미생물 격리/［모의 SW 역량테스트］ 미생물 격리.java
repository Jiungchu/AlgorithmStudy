
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Solution{
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();

	static int N, M, K, count, t;
	static Map<Integer, int[]> currMap, nextMap;
	static int[] dr = { 0, -1, 1, 0, 0 }, dc = { 0, 0, 0, -1, 1 }; // 입력값에 크기 맞추기

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
		for (int i = 0; i < M; i++) {
			// nextMap을 currMap으로 전환, nextMap은 초기화
			if (i != 0) {
				currMap = nextMap;
			}
			nextMap = new HashMap<>();
			HashMap<Integer, HashSet<int[]>> overlap = new HashMap<>();
			for (int location : currMap.keySet()) {
				int r = (location & (127 << 10))>>10;
				int c = location & 127;
				int k = currMap.get(location)[0], d = currMap.get(location)[1];
				int nr = r + dr[d], nc = c + dc[d];
				// 약품에 닿은 경우
				if (nr == 0 || nr == N - 1 || nc == 0 || nc == N - 1) {
					count -= (k - k / 2);
					k /= 2;
					d = d % 2 == 0 ? d - 1 : d + 1; // 짝수면 1 빼고 홀수면 1 더함. 반대 방향 전환
					if(k==0) continue;
				}
				int nextLocation = makeLocationMask(nr, nc);
				// 같은 칸에 다른 미생물 군집이 존재할 경우
				if (nextMap.containsKey(nextLocation)) {
					int[] other = nextMap.get(nextLocation);
					if(!overlap.containsKey(nextLocation)){
						overlap.put(nextLocation, new HashSet<>());
						overlap.get(nextLocation).add(new int[] {other[0],other[1]});
					}
					overlap.get(nextLocation).add(new int[] {k,d});
					continue;
				}
				nextMap.put(nextLocation, new int[] { k, d });
			}
			// 겹치는 부분 처리
			for(int location : overlap.keySet()) {
				int max=0, k=0, d=0;
				// 모든 겹치는 점 중 가장 큰 점의 방향을 유지
				for(int[] point : overlap.get(location)) {
					if(max<point[0]) {
						d=point[1];
						max=point[0];
					}
					k += point[0]; 
				}
				nextMap.put(location, new int[] {k,d});
			}
			
		}
		sb.append("#").append(t).append(" ").append(count).append("\n");
	}

	static void init() throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		count = 0;
		currMap = new HashMap<>();
		// 각 미생물
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int n1 = Integer.parseInt(st.nextToken());
			int n2 = Integer.parseInt(st.nextToken());
			int n3 = Integer.parseInt(st.nextToken());
			int n4 = Integer.parseInt(st.nextToken());

			// 비트로 행, 열 좌표 표시
			int location = makeLocationMask(n1, n2);
			count += n3; // 숫자 모두 더해두기
			currMap.put(location, new int[] { n3, n4 }); // 위치를 key로 각 군집에 접근
		}
	}

	static int makeLocationMask(int r, int c) {
		int location = 0;
		location |= r; // 행 위치
		location <<= 10;
		location |= c; // 열 위치

		return location;
	}
}
