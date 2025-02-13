
import java.util.*;
import java.io.*;

public class Solution {
	static int[][] map;
	static int[] dr = {1,-1,0,0};
	static int[] dc = {0,0,1,-1};
	static int N, K;
	static int maxLength = 0;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st; 
		int T = Integer.parseInt(br.readLine());
		for(int t=0;t<T;t++) {
			st = new StringTokenizer(br.readLine());
			N=Integer.parseInt(st.nextToken());
			K=Integer.parseInt(st.nextToken());
			maxLength = 0;
			
			map = new int[N][N];
			int maxHeight = 0;
			List<int[]> maxList=new ArrayList<>();
			for(int i=0;i<N;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0;j<N;j++) {
					int height = Integer.parseInt(st.nextToken()); 
					if(height>maxHeight) {
						maxList = new ArrayList<>();
						maxHeight = height;
					}
					if(height==maxHeight) maxList.add(new int[] {i,j});
					map[i][j] = height;
				}
			}
			
			for(int[] curr : maxList) {
				int r = curr[0];
				int c = curr[1];
				boolean[][] visited = new boolean[N][N];
                visited[r][c]=true;
				dfs(r,c,maxHeight,1,false,visited);
			}
			System.out.println("#"+(t+1)+" "+maxLength);
		}
		br.close();
	}
	
	static void dfs(int r, int c, int height, int length, boolean breaked, boolean[][] visited) {
		if(length>maxLength) maxLength=length;
//		if(N==7) System.out.println("#"+length+" "+r+" "+c+" "+height+" "+breaked);
		boolean[][] newVisited = new boolean[N][N];
		for(int j=0;j<N;j++) newVisited[j] = Arrays.copyOf(visited[j], N);
		for(int i=0;i<4;i++) {
			int nr=r+dr[i];
			int nc=c+dc[i];
			if(nr>=0&&nr<N && nc>=0&&nc<N && !visited[nr][nc]) {
				if(map[nr][nc] < height) {
					boolean[][] newVisited2 = new boolean[N][N];
					for(int j=0;j<N;j++) newVisited2[j] = Arrays.copyOf(newVisited[j], N);
					newVisited2[nr][nc] = true;
					dfs(nr, nc, map[nr][nc], length+1, breaked, newVisited2);
				} else if (!breaked) { // [nr,nc] 지점이 [r,c]보다 작은 경우는 break할 필요 x
					for(int k=1;k<=K;k++) {
//						if(N==7) System.out.println(nr+" "+nc+" "+map[nr][nc] + " "+k+" "+K);
						if(map[nr][nc]-k<height) {
							boolean[][] newVisited2 = new boolean[N][N];
							for(int j=0;j<N;j++) newVisited2[j] = Arrays.copyOf(newVisited[j], N);
							newVisited2[nr][nc]=true;
							dfs(nr,nc,map[nr][nc]-k,length+1,true,newVisited2);
							break;
						}  
						// 땅을 팔 수 있는 가장 조금만 파기. 땅을 팠을 때의 재귀호출은 한 점에서 최대 1회
						
					}
					
					
				}
			}
		}
		
	}
}
