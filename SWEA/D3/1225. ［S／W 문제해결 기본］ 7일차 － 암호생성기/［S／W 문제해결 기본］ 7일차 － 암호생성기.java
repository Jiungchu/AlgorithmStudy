
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static Queue<Integer> q;
	static int[] arr,quotient;
	public static void main(String[] args) throws IOException {
		for(int t=0;t<10;t++) {
			int testNum = Integer.parseInt(br.readLine());
			init();
			sb.append("#").append(testNum).append(" ");
			solution();
		}
		System.out.println(sb.toString());
		br.close();
	}
	
	static void solution() {
		// 큐를 사용해 맨 앞 숫자를 감소시키고, 맨 뒤에 다시 넣는 과정을 반복
		int diffNum=1;
		while(true) {
			int curr = q.poll();
			curr = Math.max(0, curr-diffNum++);
			q.offer(curr);
			if(diffNum==6) diffNum=1; // 다시 1부터 시작
			if(curr==0) break;
		}
		for(int i=0;i<8;i++) {
			sb.append(q.poll()).append(" ");
		}
		sb.append("\n");
	}
	
	static void init() throws IOException{
		arr = new int[8];
		quotient = new int[8];
		StringTokenizer st = new StringTokenizer(br.readLine());
		// 8사이클을 돌면 모든 자리 수가 15씩 감소하고 제자리로 돌아옴. 
		// 연산 최소화를 위해 각 자리 수를 15로 나눈 나머지를 이용
		int maxQuotient=0;
		for(int i=0;i<8;i++) {
			int num = Integer.parseInt(st.nextToken());
			arr[i] = num%15;
			quotient[i] = num/15;
			maxQuotient = Math.max(maxQuotient, quotient[i]);
		}
		// 몫이 다른 경우가 있을 수 있으므로 처리, 리스트에 넣기
		q = new ArrayDeque<>();
		for(int i=0;i<8;i++) {
			q.offer(arr[i] + (quotient[i]+1-maxQuotient)*15);
		}

	}
}
