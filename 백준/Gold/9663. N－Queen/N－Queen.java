
import java.io.*; 

public class Main {
	static int[] col, diag1, diag2;
	static int n, ans;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		// 행 정보는 index로 관리
		col = new int[n]; diag1=new int[2*n]; diag2=new int[2*n];
		dfs(0);
		System.out.println(ans);
		br.close();

	}

	static void dfs(int count) {
		if (count == n) {
			ans++;
			return;
		}
		// count라는 row에 대해 i는 column의 정보를 저장
		for (int i = 0; i < n; i++) {
			if (col[i]==0 && diag1[count+i]==0 && diag2[i-count+n-1]==0) { // 0인 지점에만 놓을 수 있음
				put(count, i, +1);
				dfs(count + 1);
				put(count, i, -1);
			}
		}
	}

	static void put(int r, int c, int flag) {
		 col[c] += flag;
		 diag1[r+c] += flag; // 오른쪽 위로 향하는 대각선. 왼쪽, 위부터 count
		 diag2[c-r+n-1] += flag; // 오른쪽 아래로 향하는 대각선. 왼쪽, 아래부터 count
	}

}
