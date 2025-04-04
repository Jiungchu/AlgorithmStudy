import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	static int D, N, M;
	static long[][] path;
	
	public static void main(String[] args) throws IOException {
		init();
		System.out.println(solution(D)[0][0]);
	}
	
	static long[][] solution(int d) {
		if(d==1) return path;
		long[][] next = solution(d/2);
		next = matrixProduct(next, next);
		
		if(d%2 == 1) return matrixProduct(next, path);
		else return next;
	}
	
	static long[][] matrixProduct(long[][] m1, long[][] m2){
		long[][] m = new long[N][N];
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m.length; j++) {
				long sum=0;
				for (int k = 0;  k< m.length; k++) {
					sum += (m1[i][k]*m2[k][j]) % 1000000007;
				}
				m[i][j] = sum % 1000000007;
			}
		}
		return m;
	}
	
	static void init() throws IOException{
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		path = new long[N][N];
		for(int i=0;i<M;i++) {
			st = new StringTokenizer(br.readLine());
			int v1 = Integer.parseInt(st.nextToken())-1;
			int v2 = Integer.parseInt(st.nextToken())-1;
			// 양방향으로 설정
			path[v1][v2] = 1;
			path[v2][v1] = 1;
		}

		D = Integer.parseInt(br.readLine());
	}
	
	static void arrayShow(long[][] arr) {
		System.out.println("##############");
		for(int i=0;i<arr.length;i++) {
			System.out.println(Arrays.toString(arr[i]));
		}
	}
}
