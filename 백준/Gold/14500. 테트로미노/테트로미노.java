import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] nm = br.readLine().split(" ");
		int n = Integer.parseInt(nm[0]);
		int m = Integer.parseInt(nm[1]);

		// 데이터 입력받기
		int[][] grid = new int[n][m];
		for (int i = 0; i < n; i++) {
			String[] temp = br.readLine().split(" ");
			for (int j = 0; j < m; j++) {
				grid[i][j] = Integer.parseInt(temp[j]);
			}
		}

		// dx, dy 정의
		int[][] dx = { { 0, 0, 0 }, { 1, 2, 3 }, { 0, 1, 1 }, { 1, 2, 2 }, { 1, 2, 2 }, { 0, 0, 1 }, { 0, 0, -1 }, //~6
				{ 0, 1, 2 }, { 0, 1, 2 }, { 1, 1, 1 }, { -1, -1, -1 }, { 1, 1, 2 }, { 1, 1, 2 }, { 0, 1, 1 },  // ~13
				{ 0, -1, -1 }, { 0, 0, 1 }, { 0, 0, -1 }, { 1, 2, 1 }, { 1, 2, 1 } };
		int[][] dy = { { 1, 2, 3 }, { 0, 0, 0 }, { 1, 0, 1 }, { 0, 0, 1 }, { 0, 0, -1 }, { 1, 2, 2 }, { 1, 2, 2 },
				{ 1, 1, 1 }, { -1, -1, -1 }, { 0, 1, 2 }, { 0, 1, 2 }, { 0, 1, 1 }, { 0, -1, -1 }, { 1, 1, 2 },
				{ 1, 1, 2 }, { 1, 2, 1 }, { 1, 2, 1 }, { 0, 0, 1 }, { 0, 0, -1 } };

		int maxSum = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				for (int k = 0; k < dx.length; k++) {
					boolean out=false;
					int sum = grid[i][j];
					for(int l=0;l<3;l++) {
						int x = i+dx[k][l]; int y=j+dy[k][l];
						if(x<0 || x>=n || y<0 || y>=m) {
							out=true; break;
						}
						sum += grid[x][y];
					}
					if(out) continue; // 다음 모양으로 넘어감
					if(sum>maxSum) {
						// k 0~1: 막대, 2: 사각형, 3~10: ㄴ, 11~14: 꺾, 15~ 가운데
						maxSum=sum;
					}
				}
			}
		}
		System.out.println(maxSum);
		br.close();

	}

}
