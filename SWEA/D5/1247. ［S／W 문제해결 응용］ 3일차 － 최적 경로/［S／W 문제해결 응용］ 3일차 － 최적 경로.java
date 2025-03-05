
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();

	static int  N, minLength, t;
	static int[][] locations, distance;

	public static void main(String[] args) throws IOException {
		int T = Integer.parseInt(br.readLine());
		for (t = 1; t <= T; t++) {
			init();
//			for(int i=2;i<N+2;i++) {
//				System.out.println(Arrays.toString(distance[i]));
//			}
			solution(0, 0, 0, 0);
			sb.append("#").append(t).append(" ").append(minLength).append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}

	static void solution(int id, int length, int mask, int count) {
		if(count==N) {
			// 모든 고객의 집을 다 방문한 경우 바로 집으로 향하기
			length += distance[1][id]; 
			minLength = Math.min(length, minLength);
			return;
		}
		if(length>minLength) return;
		for(int i=2;i<N+2;i++) {
			if((mask & (1<<i)) == 0) { // i지점을 방문하지 않은 경우
				solution(i, length+distance[id][i], mask|(1<<i), count+1);
			}
		}
	}
	
	static int getDistance(int[] p1, int[] p2) {
		return Math.abs(p1[0]-p2[0])+Math.abs(p1[1]-p2[1]);
	}
	
	static void init() throws IOException {
		N = Integer.parseInt(br.readLine());
		minLength = Integer.MAX_VALUE;
		locations = new int[N+2][];
		distance = new int[N+2][N+2];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0;i<N+2;i++) {
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			locations[i] = new int[] {x,y};
		}
		
		int[] office = locations[0];
		int[] home = locations[1];
		
		for(int i=0;i<N;i++) {
			int[] customer = locations[i+2]; // 회사, 집을 제외
			
			// 회사, 집까지의 거리 구하기
			distance[0][i+2] = getDistance(office, customer);
			distance[i+2][0] = getDistance(office, customer);
			distance[1][i+2] = getDistance(home, customer);
			distance[i+2][1] = getDistance(home, customer);
			
			// 고객들 간의 거리 구하기
			for(int j=0;j<N;j++) {
				int[] customer2 = locations[j+2];
				distance[i+2][j+2] = getDistance(customer, customer2);
				distance[j+2][i+2] = getDistance(customer, customer2);
			}
		}
	}
}
