
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	
	static int N, A, ans;
	static PriorityQueue<Integer> minheap, maxheap;

	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			init();
			sb.append("#").append(t).append(" ").append(ans).append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}
	
	static void solution(int n1, int n2) {
		minheap.add(Math.max(n1, n2)); // 큰 애들
		maxheap.add(Math.min(n1, n2)); // 작은 애들
		// maheap에는 항상 minheap보다 작은 애들이 저장되어야 함
		if(maxheap.peek() > minheap.peek()) {
			maxheap.add(minheap.poll());
			minheap.add(maxheap.poll());
		}
		ans = (ans+minheap.peek())%20171109;
	}
	

	static void init() throws IOException{
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		A = Integer.parseInt(st.nextToken());
		minheap = new PriorityQueue<>();
		maxheap = new PriorityQueue<>((a,b)->-Integer.compare(a, b));
		minheap.add(A); // 초기값은 minheap에 넣기
		
		ans=0;
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			int num1 = Integer.parseInt(st.nextToken());
			int num2 = Integer.parseInt(st.nextToken());
			solution(num1, num2);
		}
	}

}
