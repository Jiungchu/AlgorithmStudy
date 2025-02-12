import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) throws IOException  {
		Scanner sc = new Scanner(System.in);
		int m = sc.nextInt(); int n= sc.nextInt();
		Queue<int[]> queue = new LinkedList<>();
		int[][] grid = new int[n][m];
		int totCount = 0;
		for(int i=0;i<n;i++) {
			for(int j=0;j<m;j++) {
				int num = sc.nextInt();
				if(num==0) totCount++; // 익지 않은 토마토의 수
				if(num==1) queue.offer(new int[] {i,j}); // 익은 토마토는 바로 큐에 넣기
				grid[i][j] = num;
			}
		}
		int[] dx = {1,-1,0,0}; int[] dy = {0,0,1,-1};
		int time=1;
		while(!queue.isEmpty()) {
			int[] curr = queue.poll();
			int x= curr[0]; int y=curr[1];
			for(int i=0;i<4;i++) {
				int nx=x+dx[i];int ny=y+dy[i];
				if(nx>=0&&nx<n && ny>=0&&ny<m && grid[nx][ny]==0) {
					grid[nx][ny] = grid[x][y]+1; // grid를 visited처럼 사용
					time = grid[nx][ny];
					totCount--;
					queue.offer(new int[] {nx,ny});
				}
			}
		}
		if(totCount!=0) {
			System.out.println(-1);
		} else {
			System.out.println(time-1); // 시간이 1에서 시작하므로
		}
		sc.close();
	}

}
