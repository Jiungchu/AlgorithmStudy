
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Solution{

	static class Germ implements Comparable<Germ> {
		int r, c;
		int size;
		int d;
		int point;

		public Germ(int r, int c, int size, int d) {
			this.r = r;
			this.c = c;
			this.size = size;
			this.d = d;
			this.point = 0;
		}

		@Override
		public int compareTo(Germ o) {
			// 큰 군집이 먼저 나오도록 설정
			return o.size - this.size;

		}

		@Override
		public String toString() {
			return "Germ [r=" + r + ", c=" + c + ", size=" + size + ", d=" + d +", point="+point+"]";
		}

	}

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();

	static int t, N, M, K, total;
	static Germ[][] used;
	static List<Germ> list;

	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		for (t = 1; t <= T; t++) {
			init();
			solution();
		}
		System.out.println(sb.toString());
	}

	static void init() throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		total = 0;

		used = new Germ[N][N];
		list = new ArrayList<>();
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int size = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			Germ g = new Germ(r, c, size, d);
			used[r][c] = g;
			list.add(g);
			total += size; // 시작 시점에서 총 미생물 수를 더하기
		}
		Collections.sort(list);
	}

	static int[] dr = { 0, -1, 1, 0, 0 }, dc = { 0, 0, 0, -1, 1 };

	static void solution() {
		int time = 0;
		List<Germ> curList = new ArrayList<>();
		while (time++ < M) {
			curList = list;
			list = new ArrayList<>();
			for(int i=0;i<curList.size();i++) {
				Germ g = curList.get(i);
				if(used[g.r][g.c].equals(g))
					used[g.r][g.c] = null;

				// 이동 처리
				int nr = g.r + dr[g.d];
				int nc = g.c + dc[g.d];
				// 벽에 닿을 경우 반대 방향으로 전환, 미생물 수 절반으로 감소
				if (nr == 0 || nr == N - 1 || nc == 0 || nc == N - 1) {
					g.d = g.d % 2 == 1 ? g.d + 1 : g.d - 1;
					int half = g.size / 2;
					total -= g.size - half;
					g.size = half;
				}
				
				// 이미 점유한 미생물이 있을 경우 사이즈만 키우기
				if (used[nr][nc] != null && used[nr][nc].point == g.point + 1) {
					used[nr][nc].size += g.size;
				} else {
					used[nr][nc] = g;
					g.r = nr;
					g.c = nc;
					g.point++;
					list.add(g);
				}
			}
			Collections.sort(list);
		}

		sb.append("#").append(t).append(" ").append(total).append("\n");
	}

}
