
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	
	static int N,M, dp[][];
	static char[][] map;

	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			init();
			solution();
		}
		System.out.println(sb);
	}

	static void solution() {
		// 한 줄의 state를 비트마스킹으로 저장. 그 때의 최대 학생 수를 dp에 저장
		 
		for(int r=0;r<N;r++) {
			makeDP(r, 0, 0, 0);
		}
		int max = 0;
		for(int i=0;i<dp[0].length;i++) {
			max = Math.max(max, dp[N-1][i]);
		}
		sb.append(max).append("\n");
	}

	static void makeDP(int row, int index, int mask, int count) {
		// 현재 mask가 들어갈 수 있는 경우의 최대값을 dp에 저장
		int max = count;
		if(row != 0) {
			for(int prevMask=0;prevMask<dp[0].length;prevMask++) {
				if(dp[row-1][prevMask]==0) continue;
				if((mask & (prevMask<<1))==0 && (mask & (prevMask>>1))==0) {
					max = Math.max(max, count+dp[row-1][prevMask]);
				}
			}
		}
		dp[row][mask] = max;
		
		if(index>=M) return;
		
		// 현재 자리에 앉힌 경우와 앉히지 않은 경우 두 가지 경우 재귀호출
		if(map[row][index]!='x') makeDP(row, index+2, mask | (1<<index), count+1);
		makeDP(row, index+1, mask, count);
	}
	
	static void init() throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		dp = new int[N][1<<M];
		
		
		for(int i=0;i<N;i++) {
			String str = br.readLine();
			for(int j=0;j<M;j++) {
				map[i][j] = str.charAt(j);
			}
		}
	}

}
