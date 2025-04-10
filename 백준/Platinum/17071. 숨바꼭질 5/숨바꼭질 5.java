
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	static int N, K ,ans;
	static int[] locations;
	static boolean[][] visited;
	
	static void init() throws IOException {
		StringTokenizer st =new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		visited = new boolean[2][500001]; // time이 짝수일 때, 홀수일 때 방문한 위치
		locations = new int[500001]; // 동생의 위치
		ans = Integer.MAX_VALUE;
		Arrays.fill(locations, -1);
		int time = 0;
		int k = K;
		while(k<=500000) {
			locations[k] = time;
			k += ++time;
		}
	}
	
	static void solution() {
		// 큐 두 개 사용
		Queue<Integer> currQ = new ArrayDeque<>();
		Queue<Integer> nextQ = new ArrayDeque<>();
		currQ.add(N);
		int time=0;
		while(K<=500000) {
			if(time != 0) {
				currQ = nextQ;
				nextQ = new ArrayDeque<>();
			}
			while(!currQ.isEmpty()) {
				int curr = currQ.poll();
//				System.out.println(curr+" "+K);
				// 동생이 해당 점을 밟을 예정인 경우
				if(locations[curr]!=-1 && locations[curr]>=time) { 
					int checkVal = locations[curr]-time; 
					// 짝수인 경우에만 만날 수 있음
					if(checkVal % 2 ==0 )ans = Math.min(ans, locations[curr]);
				}
				// 짝수, 홀수 다른 경우만 보는 걸로 하
				if(curr>0 && !visited[time%2][curr-1]) {
					nextQ.add(curr-1);
					visited[time%2][curr-1] = true;
				}
				if(curr<500000 && !visited[time%2][curr+1]) {
					nextQ.add(curr+1);
					visited[time%2][curr+1] = true;
				}
				if(curr<=500000/2 && !visited[time%2][curr*2]) {
					nextQ.add(curr*2);
					visited[time%2][curr*2] = true;
				}
			}
			K += ++time;
		}
		if(ans==Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(ans);
	}
	
	public static void main(String[] args) throws IOException{
		init();
		solution();
	}

}
