import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static class Map implements Comparable<Map>{
		int num;
		int minExp;
		int values;
		
		public Map(int num, int minExp, int values) {
			this.num = num;
			this.minExp = minExp;
			this.values = values;
		}

		@Override
		// 입장 경험치가 낮고, 획득 경험치가 큰 map 순으로 정렬
		public int compareTo(Map o) {
			if(this.minExp==o.minExp) {
				return o.values-this.values;
			}
			return this.minExp-o.minExp;
		}
		
	}
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	static int N, T;
	static int[][] distance;
	static Map[] maps;
	
	public static void main(String[] args) throws IOException {
		init();
		solution();
	}
	
	static void init() throws IOException{
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		maps = new Map[N];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			maps[i] = new Map(i, a, b);
		}
		
		distance = new int[N][N];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				distance[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}
	
	static void solution() {
		// t시점에 i번 사냥터에서 사냥한 최대값
		int[][] dp = new int[N][T+1];
		
		for(int t=0;t<=T;t++) {
			for(int i=0;i<N;i++) {
				int exp = dp[i][t];
				Map map = maps[i];
				if(exp<map.minExp) continue;
				for(int j=0;j<N;j++) {
					// i==j면 현재 사냥터에서 사냥, 나머지는 이동이 가능한지 고려
					if(i==j && t<T) {
						dp[i][t+1] = Math.max(exp+map.values, dp[i][t+1]);
					} else {
						int nextTime = t+distance[i][j];
						if(nextTime<=T && maps[j].minExp<=exp) 
						dp[j][nextTime] = Math.max(dp[j][nextTime], exp);
					}
				}
			}
		}
		
		int max = 0;
		for(int i=0;i<N;i++) {
			max=  Math.max(dp[i][T], max);
		}
		System.out.println(max);
	}
	


}

