import java.util.Scanner;

class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		int[][] arr = new int[N][N];
		int[][] preSum = new int [N+1][N+1];
		for(int i=0;i<N;i++) {
			for(int j=0;j<N;j++) {
				arr[i][j] = sc.nextInt();
			}
		}
		for(int i=1;i<N+1;i++) {
			for(int j=1;j<N+1;j++) {
				// preSum의 [i,j]는 arr의 [i-1,j-1]에 해당. 좌표 입력이 그렇게 들어옴
				// [i-1,j], [i,j-1]은 항상 [i,j]보다 먼저 계산되어 있음. 두 개를 더하고 겹치는 부분 빼기
				preSum[i][j] = arr[i-1][j-1] + preSum[i-1][j] + preSum[i][j-1] - preSum[i-1][j-1];
			}
		}
		for(int i=0;i<M;i++) {
			int x1=sc.nextInt();
			int y1=sc.nextInt();
			int x2=sc.nextInt();
			int y2=sc.nextInt();
			int sum=preSum[x2][y2] - preSum[x2][y1-1] - preSum[x1-1][y2] + preSum[x1-1][y1-1];
			System.out.println(sum);
		}
		sc.close();

	}

}
