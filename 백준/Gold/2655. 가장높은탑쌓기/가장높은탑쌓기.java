
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	static class Brick implements Comparable<Brick>{
		int num, area, height, weight;

		public Brick(int num, int area, int height, int weight) {
			this.num = num;
			this.area = area;
			this.height = height;
			this.weight = weight;
		}

		@Override
		// 일단 넓이 기준 내림차순으로 정렬
		public int compareTo(Brick o) {
			return o.area - this.area;
		}
	}
	
	static class State {
		int height;
		List<Integer> list;
		
		public State() {
			this.height= 0 ;
			this.list = new ArrayList<>();
		}
		
		public State(int height, List<Integer> list) {
			this.height = height;
			this.list = new ArrayList<>();
			for(int i=0;i<list.size();i++) {
				this.list.add(list.get(i));
			}
		}

		public void add(Brick b) {
			list.add(b.num);
			height += b.height;
		}
		
	}
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	
	static int N, weightList[];
	static Brick[] list;
	
	public static void main(String[] args) throws IOException{
		init();
		solution();
		System.out.println(sb);
	}
	
	static void solution() {
		// dp[i]는 가장 위쪽 벽돌의 무게가 i일 때 height가 가장 높은 state
		State[] dp = new State[10001]; 
		State maxState = null;
		int maxHeight = Integer.MIN_VALUE;
		// 넓이 순으로 정렬했으므로 앞에 쌓인 벽돌들은 무조건 무게는 현재 벽돌보다 더 좁음
		for(int i=0;i<N;i++) {
			Brick cur = list[i];
			dp[cur.weight]=new State(); // 무게가 같은 벽돌은 없음
			dp[cur.weight].add(cur);
			for(int j=N-1;weightList[j]>cur.weight;j--) {
				int weight = weightList[j];
//				if(dp[weight]!=null)
//				System.out.println("weight : "+dp[weight].height+"+"+cur.height+" "+dp[cur.weight].height);
				if(dp[weight] != null && dp[weight].height+cur.height>dp[cur.weight].height) {
					dp[cur.weight] = new State(dp[weight].height, dp[weight].list);
					dp[cur.weight].add(cur);
				}
			}
			if(maxHeight<dp[cur.weight].height) {
				maxState = dp[cur.weight];
				maxHeight = maxState.height;
			}
		}
		sb.append(maxState.list.size()).append("\n");
		for(int i=maxState.list.size()-1;i>=0;i--) {
			sb.append(maxState.list.get(i)).append("\n");
		}
	}
	
	static void init() throws IOException {
		N = Integer.parseInt(br.readLine());
		list = new Brick[N];
		weightList = new int[N];
		for(int i=0;i<N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int area = Integer.parseInt(st.nextToken());
			int height = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			list[i] = new Brick(i+1, area, height, weight);
			weightList[i] = weight;
		}
		Arrays.sort(list); // 넓이 기준으로 정렬
		Arrays.sort(weightList); // 문제풀이 과정에서 무게를 사용
	}
}
