
import java.io.*;
import java.util.*;

public class Solution {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int t=1;t<=10;t++) {
			// n이 주어지지 않으므로 첫 한 줄을 읽어서 n 구하기
			String testNum = br.readLine();
			String[] first = br.readLine().split(" ");
			int n = first.length;
			String[][] grid = new String[n][n];
			grid[0] = first;
			int[] destination = new int[2];
			// 2번째 줄부터 계속 읽기
			for(int i=1;i<n;i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j=0;j<n;j++) {
					String token = st.nextToken(); 
					grid[i][j] = token;
					if(token.equals("2")) {
						destination[0]=i;
						destination[1]=j;
					}
				}
			}

			// 좌, 우로 움직이는 방향
			int[] dir = {1,-1};
			int r = destination[0]; int c=destination[1];
			// 아래에서 위로 거슬러서 0행까지 도착하는 경로
			boolean[][] visited = new boolean[n][n];
			visited[r][c]=true;
			while(r!=0) {
				visited[r][c]=true;
				boolean up = true; 
				for(int i=0;i<2;i++) {
					int nc=c+dir[i];
					if(nc>=0&&nc<n && !visited[r][nc] && !grid[r][nc].equals("0")) {
						up=false;
						c=nc;  
						break;
					}
				}
				// 위 조건에 걸리지 않았다면 위로 올라가는 경우
				if(up) {
					r--;
				}
			}
			System.out.println("#"+t+" "+c);
			
		}
		
		br.close();
	}

}
