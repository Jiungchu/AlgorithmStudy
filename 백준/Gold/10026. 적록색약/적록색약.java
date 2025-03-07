
import java.io.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();

	static int N;
	static char[][] map;

	public static void main(String[] args) throws Exception {
		init();
		solution();
		System.out.println(sb.toString());
		br.close();
	}

	static void solution() {
		boolean isNormal = true;
		// 두 번의 탐색 진행
		for(int t = 0; t<2;t++) {
			int count=0;
			boolean[][] visited = new boolean[N][N];
			for(int i=0;i<N;i++) {
				for(int j=0;j<N;j++) {
					if(!visited[i][j]) {
						dfs(i,j,isNormal,visited);
						count++;
					}
					
				}
			}
			sb.append(count).append(" ");
			// 적록색약인 경우 고려
			isNormal =false;
		}
	}
	
	static int[] dr= {1,-1,0,0}, dc = {0,0,1,-1};
	
	static void dfs(int r, int c, boolean isNormal, boolean[][] visited) {
		visited[r][c]=true;
		for(int d=0;d<4;d++) {
			int nr=r+dr[d], nc=c+dc[d];
			if(nr>=0&&nr<N && nc>=0&&nc<N && !visited[nr][nc]) {
				if(isNormal) {
					// 정상인의 경우
					if(map[r][c]==map[nr][nc]) {
						dfs(nr,nc,isNormal,visited);
					}
				} else {
					// 적록색약의 경우 R, G는 구분 없이 진행
					if((map[r][c]==map[nr][nc]) || ((map[r][c]=='R'||map[r][c]=='G')&&(map[nr][nc]=='R'||map[nr][nc]=='G'))) {
						dfs(nr,nc,isNormal,visited);
					}
				}
			}
		}
	}
	
	
	static void init() throws IOException{
		N = Integer.parseInt(br.readLine());
		map = new char[N][];
		for(int i=0;i<N;i++) {
			map[i] = br.readLine().toCharArray(); 
		}
	}

}
