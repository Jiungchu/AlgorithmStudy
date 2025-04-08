
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	static int N, M, r, c;
	static char[][] map;
	
	public static void main(String[] args) throws IOException {
		init();
		System.out.println(solution());
	}
	
	static int[] dr = {1,-1,0,0}, dc= {0,0,1,-1};
	static int solution() {
		boolean[][][] visited = new boolean[N][M][1<<6];
		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {r,c,0,0});
		visited[r][c][0] = true;

		while(!q.isEmpty()) {
			int[] cur = q.poll();
			int r = cur[0], c=cur[1], mask=cur[2], move=cur[3];
			for(int d=0;d<4;d++) {
				int nr = r+dr[d], nc=c+dc[d];
				if(nr>=0&&nr<N && nc>=0&&nc<M && map[nr][nc] != '#' && !visited[nr][nc][mask]) {
					char chr = map[nr][nc];
					if(chr=='1') {
						return move+1; // 1을 만나면 바로 탈출
					}
					if(chr=='.' || chr=='0') { // .이면 바로 이동
						visited[nr][nc][mask] = true;
						q.offer(new int[] {nr,nc,mask,move+1});
					} else if(chr>='a' && chr<='f') {
						int nextMask = mask | (1 << (chr-'a')); // 위치에 해당하는 비트 켜주기
						visited[nr][nc][nextMask] = true;
						q.offer(new int[] {nr,nc,nextMask,move+1});
					} else if(chr>='A' && chr<='F') {
						if((mask & (1 << (chr-'A')))>0) { // 해당하는 비트가 켜져있을 때만 이동
							visited[nr][nc][mask] = true;
							q.offer(new int[] {nr,nc,mask,move+1});
						}
					}
				}
			}
		}
		return -1;
	}
	
	static void init() throws IOException{
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new char[N][M];
		for(int i=0;i<N;i++) {
			String str = br.readLine();
			for(int j=0;j<M;j++) {
				char chr = str.charAt(j);
				map[i][j]=chr;
				if(chr=='0') {
					r=i; 
					c=j;
				}
			}
		}
		
	}

}
