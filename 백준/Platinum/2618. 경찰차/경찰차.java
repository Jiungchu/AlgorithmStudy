import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	
	static int N, W, items[][], distance[][], dp[][], path[][];
	
	public static void main(String[] args) throws IOException {
		init();
		solution();
	}
	
	static void init() throws IOException {
		N = Integer.parseInt(br.readLine());
		W = Integer.parseInt(br.readLine());
		items = new int[W][];
		
		for(int i=0;i<W;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			items[i] = new int[] {r,c};
		}
		
		// 각 점 사이의 거리 미리 구해놓기
		distance = new int[W+1][W+1];
		for(int i=1;i<=W;i++) {
			distance[0][i] = items[i-1][0]+items[i-1][1]-2;
			for(int j=i+1;j<=W;j++) {
				int dist = Math.abs(items[i-1][0]-items[j-1][0])+Math.abs(items[i-1][1]-items[j-1][1]);
				distance[i][j] = dist;
			}
		}
	}
	
	static void solution() {
		// 경찰차 1, 2의 위치(사건 번호)가 각각 i, j일 때, 남은 최소 이동 거리를 dp[i][j]로 설정
		// 따라서 i, j의 의미는 각 경찰차가 마지막으로 해결한 사건
		dp = new int[W+1][W+1];
		path = new int[W+1][W+1];
		for(int i=W-1;i>=0;i--) {
			for(int j=W-1;j>=0;j--) {
				if(i==j && i!=0) continue;
				// 이동해야 할 다음 item
				int item = Math.max(i, j)+1;
				// dp의 값과 각 경찰차가 이동해야 하는 거리를 더해서 비교
				int distI = distance[i][item];
				int distJ = j==0? 2*(N-1)-distance[j][item]:distance[j][item];
				if(dp[item][j]+distI < dp[i][item]+distJ) {
					dp[i][j] = dp[item][j]+distI;
					path[i][j] = 1;
				} else {
					dp[i][j] = dp[i][item]+distJ;
					path[i][j] = 2;
				}
			}
		}
		sb.append(dp[0][0]).append("\n");
//		debug();
		// 최적 값을 추적하는 코드
		int i=0, j=0;
		int item = 0;
		while(item++<W) {
			if(path[i][j]==1) {
				i=item;
				sb.append(1).append("\n");
			} else {
				j=item;
				sb.append(2).append("\n");
			}
		}
		System.out.println(sb);
	}
	
	static void debug() {
		System.out.println("====================");
		for(int i=0;i<=W;i++) {
			System.out.println(Arrays.toString(distance[i]));
		}
		System.out.println("====================");
		for(int i=0;i<=W;i++) {
			System.out.println(Arrays.toString(path[i]));
		}
		System.out.println("====================");
		for(int i=0;i<=W;i++) {
			System.out.println(Arrays.toString(dp[i]));
		}
	}
}
