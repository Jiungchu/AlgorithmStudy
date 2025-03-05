
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	
	static int N, maxHeight, count1, count2;
	static int[] trees;

	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			init();
			count();
			int ans = solution();
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}
	
	static int solution() {
		if(count2==0) return getDate(); // count2==0이면 바로 값 출력
		int prevDate = Integer.MAX_VALUE;
		int currDate;
		// count를 변화했을 때 값이 더 커지는 경우
		while((currDate = getDate())<prevDate) {
			count1 += 2;
			count2 --;
			prevDate = currDate;
		}
		return prevDate;
	}
	
	static int getDate() {
		if(count1>count2) return count1*2-1;
		else return count2*2; // count2가 크거나 같으면 count2를 기준으로 
	}
	
	static void count() {
		for(int i=0;i<N;i++) {
			int diff = maxHeight-trees[i];
			count1 += diff%2;
			count2 += diff/2;
		}
	}

	static void init() throws IOException{
		N = Integer.parseInt(br.readLine());
		trees = new int[N];
		count1=count2=0;
		maxHeight=0;
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			int num = Integer.parseInt(st.nextToken());
			trees[i]=num;
			maxHeight = Math.max(maxHeight, num);
		}
	}

}
