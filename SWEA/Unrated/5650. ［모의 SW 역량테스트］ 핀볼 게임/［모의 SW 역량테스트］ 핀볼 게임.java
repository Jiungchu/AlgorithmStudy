
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Solution {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	
	static int t, ans, N, map[][];
	static Map<Integer, Integer> wormhalls;
	
	public static void main(String[] args) throws IOException {
		int T= Integer.parseInt(br.readLine().trim());
		for(t=1; t<=T;t++) {
			init();
			solution();
		}
		System.out.println(sb.toString());
	}
	
	static void solution() {
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				if(map[i][j]!=0) continue;
				for(int d=0;d<4;d++) {
					int score = go(i,j,d);
					ans = Math.max(ans, score);
				}
			}
		}
		sb.append("#").append(t).append(" ").append(ans).append("\n");
	}
	
	// 시계 방향
	static int[] dr = {0,-1,0,1}, dc = {-1,0,1,0};
	
	static int go(int r, int c, int d) {
		int nr = r+dr[d]; int nc= c+dc[d];
		int score = 0;
		while(true) {
			// 벽에 닿는 경우
			if(nr<0 || nr>=N || nc<0 || nc>=N) {
				d = (d+2)%4;
				score++;
			} else {
				int cur = map[nr][nc];
				if(cur==-1 || (nr==r && nc==c)) {
					break;
				} else if(cur>=6 && cur<=10) {
					int key = (nr<<10)+nc;
					int value = wormhalls.get(key);
					nr = value>>10;
					nc = value & ((1<<10)-1);
				} else if(cur>0 && cur<=5){ // 1~5번 블록에 부딪히는 경우
					score++;
					// 반대 방향으로 전환하는 경우
					if(cur==5  
					|| ((cur==1)&&(d==1 || d==2)) 
					|| ((cur==2)&&(d==2 || d==3))
					|| ((cur==3)&&(d==3 || d==0))
					|| ((cur==4)&&(d==0 || d==1))) d = (d+2)%4;
					else if(cur%2==1) { // 1, 3번 모양
						d = d%2==0?(d+1)%4:(d-1+4)%4;
					} else { // 2, 4번 모양
						d = d%2==1?(d+1)%4:(d-1+4)%4;
					}
				}
			}
			nr += dr[d]; nc += dc[d];
		}
		return score;
		
	}
	
	static void init() throws IOException{
		N = Integer.parseInt(br.readLine().trim());
		map = new int[N][N];
		ans = 0;
		
		wormhalls = new HashMap<>();
		int[] holes = new int[6];
		Arrays.fill(holes, -1);
		for(int i=0;i<N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine().trim());
			for(int j=0;j<N;j++) {
				int num = Integer.parseInt(st.nextToken());
				map[i][j] = num;
				if(num>=6 && num<=10) {
					// 저장된 hole이 있다면 양방향 연결
					if(holes[num-5] != -1) {
						int key1 = holes[num-5];
						int key2 = (i<<10)+j;
						wormhalls.put(key1, key2);
						wormhalls.put(key2, key1);
					} else {
						holes[num-5] = (i<<10)+j;
					}
				}
			}
		}
	}

}
