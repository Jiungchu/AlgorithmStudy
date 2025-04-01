
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	
	static int N, M, C, map[][], t, counts[][];
	
	public static void main(String[] args) throws Exception {
		int T = Integer.parseInt(br.readLine());
		for (t = 1; t <= T; t++) {
			init();
			calculate(0,0);
			solution();
		}
		System.out.println(sb.toString());
		br.close();
	}
	
	static void solution() {
//		 상위 2개만 뽑기
		for(int i=0;i<N;i++) Arrays.sort(counts[i]);
		Arrays.sort(counts, (a,b)->b[N-M]-a[N-M]);
		int max = counts[0][N-M]+counts[1][N-M];
		sb.append("#").append(t).append(" ").append(max).append("\n");
	}

	static void calculate(int rIndex, int cIndex) {
		if(cIndex==N-M+1) {
			rIndex++; cIndex=0;
		}
		if(rIndex==N) return;
		work(rIndex, cIndex,0, 0, 0, 0);
		calculate(rIndex, cIndex+1);
	}

	static void work(int r, int c, int m, int mask, int sum, int count) {
		if(sum>counts[r][c]) {
			counts[r][c] = sum;
		}
		if(m>=M) return;
		if((mask&(1<<m))==0) { //i번째 수를 사용하고 있지 않으면
			int num = map[r][c+m];
			if(count+num<=C) work(r,c,m+1,mask|(1<<m),sum+num*num,count+num);
		}
		work(r,c,m+1,mask,sum,count);
	}
	
	static void init() throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		counts = new int[N][N-M+1];  // r, c에서 출발했을 때의 최적 해를 저장
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}

}
