
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	static int N, M;
	static int sr, sc, er, ec; // 시작 지점과 도착 지점의 좌표
	static Queue<int[]> q1, q2;
	static boolean visited[][];
	
	public static void main(String[] args) throws IOException {
		init();
		int result = solution();
		if(result<0) System.out.println("KAKTUS");
		else System.out.println(result);
	}
	
	static int[] dr = {1,-1,0,0}, dc= {0,0,1,-1};
	
	static int solution() throws IOException {
		q1.offer(new int[] {sr,sc,0});
		visited[sr][sc] = true;
		while(!q1.isEmpty()) {
			// 물을 먼저 처리
			int size2= q2.size();
			while(size2-->0) {
				int[] cur = q2.poll();
				int r=  cur[0], c=cur[1];
				for(int d=0;d<4;d++) {
					int nr=r+dr[d], nc=c+dc[d];
					if(nr>=0&&nr<N&&nc>=0&&nc<M && !visited[nr][nc]) {
						visited[nr][nc]=true;
						q2.offer(new int[] {nr,nc});
					}
				}
			}
			// 고슴도치 탐색
			int size1= q1.size();
			while(size1-->0) {
				int[] cur = q1.poll();
				int r=  cur[0], c=cur[1], move=cur[2];
				for(int d=0;d<4;d++) {
					int nr=r+dr[d], nc=c+dc[d];
					if(nr==er && nc==ec) return move+1;
					if(nr>=0&&nr<N&&nc>=0&&nc<M && !visited[nr][nc]) {
						visited[nr][nc]=true;
						q1.offer(new int[] {nr,nc,move+1});
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
		
		// q1은 고슴도치, q2는 물을 저장
		q1= new ArrayDeque<>();
		q2= new ArrayDeque<>();
		
		visited = new boolean[N][M];
		for(int i=0;i<N;i++) {
			String str = br.readLine();
			for(int j=0;j<M;j++) {
				char c = str.charAt(j);
				if(c=='.') {
					continue;
				} else if(c=='S') {
					sr = i; sc = j;
				} else if(c=='D') {
					er = i; ec = j;
					visited[er][ec] = true;
				} else if(c=='X') {
					visited[i][j]=true;
				} else if(c=='*') {
					visited[i][j]=true;
					q2.offer(new int[] {i,j});
				}
			}
		}
		
	}

}

