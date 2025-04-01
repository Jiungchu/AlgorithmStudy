
import java.io.*;
import java.util.*;

public class Solution {
    static int N, M, C, map[][], dp[][];
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            init(br);
            fill();
            int max= getMax();
            sb.append("#").append(t).append(" ").append(max).append("\n");
        }
        
        System.out.println(sb.toString());
        br.close();
    }
    
    static void init(BufferedReader br) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        
        map = new int[N][N];
        dp = new int[N][N - M + 1];
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
    
    static void fill() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j <= N - M; j++) {
            	work(i, j, 0, 0, 0);
            }
        }
    }
    
    static void work(int r, int c, int idx, int sum, int count) {
        if (count > C) return;
        dp[r][c] = Math.max(dp[r][c], sum);
        if (idx == M) return;

        work(r, c, idx + 1, sum + map[r][c + idx] * map[r][c + idx], count + map[r][c + idx]);
        work(r, c, idx + 1, sum, count);
    }
    
    static int getMax() {
        int max = 0;
        
		for (int i = 0; i < N; i++)
			Arrays.sort(dp[i]);
		Arrays.sort(dp, (a, b) -> b[N - M] - a[N - M]);
        max = dp[0][N-M]+dp[1][N-M];
        return max;
    }
}
