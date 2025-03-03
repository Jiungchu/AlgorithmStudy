
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();

	static int N,K;
	static int[][] items;
	static int[] maxValues;
	
	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			init();
			solution();
			sb.append("#").append(t).append(" ").append(maxValues[K]).append("\n");
		}
		System.out.println(sb.toString());
	}
	
	static void solution() {
		for(int i=0;i<N;i++) {
			int size=items[i][0], value=items[i][1];
			// 뒤에서부터 value까지 아이템별로 순회
			for(int k=K;k>=size;k--) {
				maxValues[k] = Math.max(maxValues[k], maxValues[k-size]+value);
			}
		}
	}
	
	static void init() throws IOException{
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		items = new int[N][2];
		maxValues = new int[K+1];
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			items[i][0]=Integer.parseInt(st.nextToken());
			items[i][1]=Integer.parseInt(st.nextToken());
		}
	}
}
