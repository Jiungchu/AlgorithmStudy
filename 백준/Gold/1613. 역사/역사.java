import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	static int N, K, S, dist[][];
	
	
	public static void main(String[] args) throws IOException {
		init();
		solution();
	}
	
	static void solution() throws IOException {
		StringBuilder sb = new StringBuilder();
		for(int k=1;k<=N;k++) {
			for(int i=1;i<=N;i++) {
				for(int j=1;j<=N;j++) {
					// 0인 경우 업데이트. k가 i, j와 모두 선후 관계가 있으면 업데이트 가능
					if(dist[i][j]==0 && i!=j && dist[i][k]!=0 && dist[j][k] !=0) {
						// i가 k보다 먼저 일어나고, j는 k보다 나중에 경우
						if(dist[i][k]<dist[j][k]) {
							dist[i][j] = -1;
							dist[j][i] = 1;
						} else if(dist[i][k]<dist[j][k]) { // 반대의 경우
							dist[i][j] = 1;
							dist[j][i] = -1;
						} // 두 개가 똑같으면 비교가 불가능
					} 
				}
			}
		}
		
		S = Integer.parseInt(br.readLine());
		for(int i=0;i<S;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			sb.append(dist[a][b]).append("\n");
		}
		System.out.println(sb);
	}
	
	static void init() throws IOException{
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		// 거리 배열 초기화
		dist = new int[N+1][N+1];

		// 두 사건을 비교해서 먼저 일어났으면 -1, 나중에 일어났으면 1을 반환
		for(int i=0;i<K;i++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			dist[a][b] = -1;
			dist[b][a] = 1; 
		}
	}

}

