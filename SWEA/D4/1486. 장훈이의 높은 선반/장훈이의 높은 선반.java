
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static int[] heights;
	static int N, B, minHeight;

	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			init();
			solution(0,0);
			sb.append("#").append(t).append(" ").append(minHeight-B).append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}

	static void solution(int index, int height) {
		// 높이가 B보다 높아지면 즉시 비교
		if(height>=B) {
			minHeight = Math.min(minHeight, height);
			return;
		}
		// 선택이 완료되었지만 높이가 B보다 작은 경우
		if(index==N) return;
		
		// index에 해당하는 점원을 포함한 경우
		solution(index+1,height+heights[index]);
		// 포함하지 않은 경우
		solution(index+1,height);
	}

	static void init() throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		minHeight = Integer.MAX_VALUE;
		
		st = new StringTokenizer(br.readLine());
		heights = new int[N];
		for(int i=0;i<N;i++) {
			heights[i] = Integer.parseInt(st.nextToken());
		}
	}
}
