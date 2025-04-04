
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main{
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	static int D, ans;
	static int[][] edges;
	static long[][] path;
	
	public static void main(String[] args) throws IOException {
		init();
//		arrayShow(solution(D));
		System.out.println(solution(D)[0][0]);
	}
	
	static long[][] solution(int d) {
		if(d==1) return path;
		long[][] next = solution(d/2);
		next = matrixProduct(next, next);
		
		if(d%2 == 1) return matrixProduct(next, path);
		else return next;
	}
	
	static void dp() {
		int[][] dp = new int[8][D+1];
		dp[0][0] = 1; // 시간 0에 0에 있을 수 있는 경우의 수는 1
		for(int i=0;i<D;i++) {
			for(int v=0;v<8;v++) {
				int n = dp[v][i];
				for(int next : edges[v]) {
					dp[next][i+1] += n;
				}
			}
		}
	}
	
	static long[][] matrixProduct(long[][] m1, long[][] m2){
		long[][] m = new long[8][8];
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
		D = Integer.parseInt(br.readLine());
		ans=0;
		
		// 주어진 그림에 따라 간선 설정
		edges = new int[8][];
		edges[0] = new int[] {1,2}; // 정보과학관
		edges[1] = new int[] {0,2,3}; // 전산관
		edges[2] = new int[] {0,1,3,5}; // 미래관
		edges[3] = new int[] {1,2,4,5}; // 신양관
		edges[4] = new int[] {3,5,6}; // 진리관
		edges[5] = new int[] {2,3,4,7}; // 한경직기념관
		edges[6] = new int[] {4,7}; // 학생회관
		edges[7] = new int[] {5,6}; // 형남공학관
		
		path = new long[8][8];
		for (int i = 0; i < edges.length; i++) {
			for (int j : edges[i]) {
				path[i][j] = 1;
			}
		}
	}
	
	static void arrayShow(long[][] arr) {
		System.out.println("##############");
		for(int i=0;i<arr.length;i++) {
			System.out.println(Arrays.toString(arr[i]));
		}
	}
}
