
import java.util.*;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[][] dp = new int[n][n];
		dp[0][0]=sc.nextInt();
		int maxNum=0;
		if(n==1) maxNum=dp[0][0];
		for(int i=1;i<n;i++) {
			for(int j=0;j<i+1;j++) {
				int currNum = sc.nextInt();
				// 윗줄의 왼쪽 오른쪽 중 더 큰 값을 선택
				int left; int right;
				if(j!=0) left = dp[i-1][j-1];
				else left = 0;
				if(j!=i) right = dp[i-1][j];
				else right=0;
				dp[i][j] = currNum+Math.max(left, right);
				if(i==n-1) {
					if(dp[i][j]>maxNum) maxNum=dp[i][j];
				}
			}
		}
		System.out.println(maxNum);
		sc.close();
	}

}
