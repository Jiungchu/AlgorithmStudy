
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static char[][] map;
	static boolean[][] visited;
	static int[][] results, simulations;
	static int R, C, ans;
	static boolean possible;

	public static void main(String[] args) throws IOException {
		for (int t = 1; t <= 4; t++) {
			init();
			solution(0,1);

			if (possible)
				sb.append(1);
			else
				sb.append(0);
			sb.append(" ");
		}
		System.out.println(sb.toString());
		br.close();
	}

	static void solution(int team1, int team2) {
		if (team2 == 6) {
			if (team1 < 4)
				solution(team1 + 1, team1 + 2);
			else {
				for (int i = 0; i < 6; i++) {
					for (int j = 0; j < 3; j++) {
						if (results[i][j] != simulations[i][j])
							return;
					}
				}
				possible = true; 
			}
			return;
		}
		// team1 승리
		// team1의 승 수, team2의 패 수가 주어진 결과보다 적을 때만 재귀호출
		if (simulations[team1][0] < results[team1][0] && simulations[team2][2] < results[team2][2]) {
			simulations[team1][0]++;
			simulations[team2][2]++;
			solution(team1, team2 + 1);
			simulations[team1][0]--;
			simulations[team2][2]--;
		}
		// 무승부
		if (simulations[team1][1] < results[team1][1] && simulations[team2][1] < results[team2][1]) {
			simulations[team1][1]++;
			simulations[team2][1]++;
			solution(team1, team2 + 1);
			simulations[team1][1]--;
			simulations[team2][1]--;
		}
		// team2 승리
		if (simulations[team1][2] < results[team1][2] && simulations[team2][0] < results[team2][0]) {
			simulations[team1][2]++;
			simulations[team2][0]++;
			solution(team1, team2 + 1);
			simulations[team1][2]--;
			simulations[team2][0]--;
		}

	}

	static void init() throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		results = new int[6][3];
		simulations = new int[6][3];
		possible = false;
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 3; j++) {
				results[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}
}
