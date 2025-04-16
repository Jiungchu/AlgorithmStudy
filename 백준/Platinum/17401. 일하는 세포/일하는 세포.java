
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	static int T, N, D;
	static long edges[][][], ans[][];

	public static void main(String[] args) throws IOException {
		init();
		solution();
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				sb.append(ans[i][j]);
				if (j != N - 1)
					sb.append(" ");
			}
			if (i != N - 1)
				sb.append("\n");
		}
		System.out.println(sb);
	}

	static void init() throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		T = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());

		edges = new long[T][N][N];
		for (int i = 0; i < T; i++) {
			int M = Integer.parseInt(br.readLine());
			for (int j = 0; j < M; j++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				edges[i][a - 1][b - 1] = c;
			}
		}
	}

	static void solution() {
		// 최초에 단위행렬로 설정
		long[][] basis = new long[N][N];
		if(D==0) {
			ans = basis;
			return;
		}
		
		for (int i = 0; i < N; i++) {
			basis[i][i] = 1;
		}

		// 1~T번 통로까지 모두 방문했을 때의 상태를 단위로 설정
		// 나머지를 계산할 rem도 설정
		int rem = D % T;
		long[][] remainder = new long[N][N];
		for (int i = 0; i < T; i++) {
			if (i == rem)
				remainder = basis;
			basis = matrixProduct(basis, edges[i]);
			if (i == D) {
				ans = remainder;
				return;
			}
		}

		ans = matrixPow(basis, D / T);
		ans = matrixProduct(ans, remainder);

	}

	static long[][] matrixPow(long[][] basis, int exp) {
		long[][] ans = new long[N][N];
		for (int i = 0; i < N; i++) {
			ans[i][i] = 1;
		}
		while (exp > 0) {
			if (exp % 2 == 1) {
				ans = matrixProduct(ans, basis);
			}
			basis = matrixProduct(basis, basis);
			exp /= 2;
		}
		return ans;
	}

	static long[][] matrixProduct(long[][] a, long[][] b) {
		long[][] newMatrix = new long[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < N; k++) {
					newMatrix[i][j] += (a[i][k] * b[k][j]) % 1000000007;
				}
				newMatrix[i][j] = newMatrix[i][j] % 1000000007;
			}
		}
		return newMatrix;
	}

}
