
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	
	static int t, N, K, items[][];
	
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
    	int[] dp = new int[K+1];
    	for(int i=0;i<N;i++) {
    		int[] item = items[i];
    		int v = item[0], c=item[1];
    		for(int w = K;w>=v;w--) {
    			dp[w] = Math.max(dp[w], dp[w-v]+c);
    		}
    	}
    	sb.append("#").append(t).append(" ").append(dp[K]).append("\n");
    }

    static void init() throws IOException{
    	StringTokenizer st = new StringTokenizer(br.readLine());
    	N = Integer.parseInt(st.nextToken());
    	K = Integer.parseInt(st.nextToken());
    	
    	items = new int[N][2];
    	for(int i=0;i<N;i++) {
    		st = new StringTokenizer(br.readLine());
    		items[i][0] = Integer.parseInt(st.nextToken());
    		items[i][1] = Integer.parseInt(st.nextToken());
    	}
    }
}
