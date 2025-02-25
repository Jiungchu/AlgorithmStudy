
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	static int N, maxScore;
	static int[] numbers, order = {0,0,0,1,0,0,0,0,0};
	static int[][] results;

	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		results = new int[N][9];
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 9; j++) {
				results[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		solution(0, 1<<1);
		System.out.println(maxScore);
	}
	
	static void solution(int count, int mask) {
		if(count==3) count++; // 1번 선수가 이미 4번 타자이므로
		if(count==9) {
			maxScore = Math.max(maxScore, getScore());
			return;
		}
		for(int i=1;i<=9;i++) {
			if((mask & 1<<i) == 0) {
				order[count]=i;
				solution(count+1, mask|1<<i);
			}
		}
	}
	
	static int getScore() {
		int player = 0, score=0;
		for(int i=0;i<N;i++) {
			int out = 0, mask = 0;
			while (out < 3) {
				int result = results[i][order[player]-1];
				if (result == 0)
					out++;
				else {
					mask |= 1;
					mask <<= result;
				}
				if (mask >= (1 << 4)) { // 홈에 들어온 선수가 있다면
					// 4번 이상의 비트에 들어있는 1의 수
					score += scoring(mask);
					mask = mask & Integer.parseInt("1111", 2);
				}
				player = (++player)%9;
			}
		}
		return score;
	}

	static int scoring(int mask) {
		int score = 0;
		for (int i = 4; i <= 7; i++) {
			score += (mask & (1 << i)) >> i;
		}
		return score;
	}

}
