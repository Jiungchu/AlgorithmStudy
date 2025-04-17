
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	
	static int N, loc[][], dist[][];
	
	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			init();
			if(solution()) sb.append("happy").append("\n");
			else sb.append("sad").append("\n");
		}
		System.out.println(sb);
	}
	
	static void init() throws IOException{
		N = Integer.parseInt(br.readLine());
		loc = new int[N+2][];
		dist = new int[N+2][N+2];
		
		for(int i=0;i<N+2;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			loc[i] = new int[] {x,y}; 
		}
		
		// 한 점에서 모든 점까지의 거리 구하기
		for(int i=0;i<N+2;i++) {
			for(int j=i+1;j<N+2;j++) {
				int distance = getDist(loc[i], loc[j]);
				dist[i][j] = distance;
				dist[j][i] = distance;
			}
		}
	}
	
	static boolean solution() {
		boolean[] visited = new boolean[N+2];
		Queue<Integer> q = new ArrayDeque<>();
		q.offer(0);
		visited[0] = true;
		
		while(!q.isEmpty()) {
			int cur = q.poll();
			
			for(int i=1;i<N+2;i++) {
				if(!visited[i] && dist[cur][i]<=1000) {
					if(i==N+1) return true;
					q.offer(i);
					visited[i] = true;
				}
			}
		}
		
		return false;
	}
	
	static int getDist(int[] p1, int[] p2) {
		return Math.abs(p1[0]-p2[0])+Math.abs(p1[1]-p2[1]);
	}
	
}

