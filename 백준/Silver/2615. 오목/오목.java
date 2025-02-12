
import java.io.*;

public class Main {
	static String[][] grid = new String[19][];
	static int[] dx = { 1, 1, 0, 1 };
	static int[] dy = { 0, 1, 1, -1 };

	public static void main(String[] args) throws FileNotFoundException, IOException {
//		System.setIn(new FileInputStream("Test5.txt"));
		// ---------여기에 코드를 작성하세요.---------------//
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		for (int i = 0; i < 19; i++) {
			grid[i] = br.readLine().split(" ");
		}

		// 1번 돌에 대해 먼저 탐색
		String[] teams = { "1", "2" };
		boolean finish = false;
		for (String team : teams) { // 한 팀씩 확인
			if (finish)
				break;
			for (int i = 0; i < 19; i++) {
				if (finish)
					break;
				for (int j = 0; j < 19; j++) {
					if (grid[i][j].equals(team)) {
						boolean[] result = check(i, j, team);
						if (result[0]) {
							if(!result[1]) System.out.println(team + "\n" + (i + 1) + " " + (j + 1));
							else System.out.println(team + "\n" + (i + 5) + " " + (j - 3)); // 우측 대각선의 경우 방향이 다르므로 맨 왼쪽을 호출
							finish = true;
							break;
						}

					}
				}
			}
		}
		if (!finish)
			System.out.println(0);
		br.close();
	}

	public static boolean[] check(int x, int y, String team) {
		boolean ans = false;
		boolean flag = false;
		for (int i = 0; i < 4; i++) {
			int count = 1;
			while (true) {
				if (count == 1 && ((i==0 && x > 0)|| (i==1 && x>0 && y>0) || (i==2 && y>0) || (i==3 && x>0 &&y<18))) {
					int px = x - dx[i];
					int py = y - dy[i];
					if (grid[px][py].equals(team))
						break; // 방향 반대로 한 칸 이전이 같으면 이미 탐색한 지점
				}
				int nx = x + dx[i] * count;
				int ny = y + dy[i] * count;
				// dx, dy에 증가만 체크하므로 상한만 확인
				if ( nx < 19 && ny>=0 && ny < 19 && grid[nx][ny].equals(team)) {
					count++;
				} else
					break; // 다음 점이 연속된 지점이 아니면 break
			}
			if (count == 5) {
				ans=true;
				if(i==3) flag = true;
				return new boolean[] {ans,flag};
			}
		}
		return new boolean[] {false, false};
	}
}
