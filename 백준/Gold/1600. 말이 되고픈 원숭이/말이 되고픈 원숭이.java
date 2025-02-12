
import java.io.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int K = Integer.parseInt(br.readLine());
		String[] wh = br.readLine().split(" ");
		int w=Integer.parseInt(wh[0]); int h= Integer.parseInt(wh[1]);
		
		// h가 세로 길이, w가 가로 길이이므로 크기를 아래와 같이 만들기
		String[][] grid = new String[h][w];
		boolean[][][] visited = new boolean[K+1][][];
		
		for(int i=0;i<h;i++) {
			grid[i] = br.readLine().split(" ");
		}
		
		Queue<int[]> q = new ArrayDeque<>();
		int[] dr = {1,-1,0,0, -2,-1,1,2, 2,1,-1,-2}; 
		int[] dc = {0,0,1,-1, 1,2,2,1, -1,-2,-2,-1};
		
		q.offer(new int[]{0,0,0,0});
		visited[0] = new boolean[h][w];
		visited[0][0][0] = true;
		while(!q.isEmpty()) {
			int[] curr = q.poll();
			int r= curr[0]; int c=curr[1];int k = curr[2];int count=curr[3];
			if(r==h-1 && c==w-1) {
				System.out.println(count);
				return;
			}
			// 기본 4방 탐색,knight 탐색을 한 번에 수행
			for(int i=0;i<12;i++) {
				int nr=r+dr[i]; int nc=c+dc[i];
				if(i<4) {
					if(nr>=0&&nr<h && nc>=0&&nc<w && !grid[nr][nc].equals("1") &&!visited[k][nr][nc]) {
						q.offer(new int[] {nr,nc,k,count+1});
						visited[k][nr][nc] = true;
					}
				} else {
					if(k>=K) break;
					// k+1번째 visited가 없다면 만들기
					if(visited[k+1]==null) {
						visited[k+1] = new boolean[h][w];
//						for(int j=0;j<h;j++) visited[k+1][j] = Arrays.copyOf(visited[k][j],w);
					}
					// knight 탐색
					if(nr>=0&&nr<h && nc>=0&&nc<w && !grid[nr][nc].equals("1") && !visited[k+1][nr][nc]) {
						q.offer(new int[] {nr,nc,k+1,count+1});
						visited[k+1][nr][nc] = true;
					}
				}
			}
		}
		System.out.println(-1);
		br.close();
	}

}
