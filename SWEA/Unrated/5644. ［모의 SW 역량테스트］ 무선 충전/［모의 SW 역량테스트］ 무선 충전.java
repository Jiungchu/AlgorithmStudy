
import java.util.*;
import java.io.*;

public class Solution {
	static int[][] direction = { { 0, 0 }, { 0, -1 }, { 1, 0 }, { 0, 1 }, { -1, 0 } };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int m = Integer.parseInt(st.nextToken());
			int a = Integer.parseInt(st.nextToken());
			int[][] moves = new int[2][m];
			int[][] bcs = new int[a][4];
			for (int i = 0; i < 2; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < m; j++) {
					moves[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			for (int i = 0; i < a; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < 4; j++) {
					bcs[i][j] = Integer.parseInt(st.nextToken());
				}
			}

			// 최댓값 구하기
			int sum = 0;
			int[][] p = { { 1, 1 }, { 10, 10 } };
			for (int i = 0; i <= m; i++) { // 각 MOVE에 대해
				boolean[] connect1 = new boolean[bcs.length];
				boolean[] connect2 = new boolean[bcs.length];
				for (int j = 0; j < 2; j++) { // A,B에 대해
					if (i != 0) { // 이동. t=0인 시점을 고려하기 위해 i를 0~m까지 설정
						p[j][0] += direction[moves[j][i - 1]][0];
						p[j][1] += direction[moves[j][i - 1]][1];
					}
					for (int k = 0; k < bcs.length; k++) { // 각 BC들에 대해
						int[] bc = bcs[k];
						// bc의 0, 1번째 원소는 좌표, 2번째 원소는 범위, 3번째 원소는 power를 의미
						if (bc[2] >= Math.abs(bc[0] - p[j][0]) + Math.abs(bc[1] - p[j][1])) {
							if (j == 0)
								connect1[k] = true;
							if (j == 1)
								connect2[k] = true;
						}
					}
				}
				int currMax = 0;
				for (int j = 0; j < bcs.length; j++) {
					for (int k = 0; k < bcs.length; k++) {
                        if(bcs.length==1 && (connect1[j]||connect2[k])) {
							currMax = connect1[j]?bcs[j][3]:bcs[k][3];
						}
						if ((!connect1[j] && !connect2[k]) || j == k)
							continue;
						int power1 = connect1[j] ? bcs[j][3] : 0;
						int power2 = connect2[k] ? bcs[k][3] : 0;
						int curr = power1+power2;
						currMax = Math.max(currMax, curr);
					}
				}
				sum += currMax;
			}
			System.out.println("#" + (t + 1) + " " + sum);
		}

		br.close();

	}

}
