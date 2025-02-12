
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] rgbCost = new int[n][3];
        
        for (int i = 0; i < n; i++) {
            rgbCost[i][0] = sc.nextInt(); // R
            rgbCost[i][1] = sc.nextInt(); // G
            rgbCost[i][2] = sc.nextInt(); // B
        }

        // DP 테이블 선언
        int[][] dp = new int[n][3];

        // 초기값 설정
        dp[0][0] = rgbCost[0][0];
        dp[0][1] = rgbCost[0][1];
        dp[0][2] = rgbCost[0][2];

        // 점화식을 이용하여 DP 테이블 채우기
        for (int i = 1; i < n; i++) {
            dp[i][0] = rgbCost[i][0] + Math.min(dp[i - 1][1], dp[i - 1][2]);
            dp[i][1] = rgbCost[i][1] + Math.min(dp[i - 1][0], dp[i - 1][2]);
            dp[i][2] = rgbCost[i][2] + Math.min(dp[i - 1][0], dp[i - 1][1]);
        }

        // 최솟값 출력 (마지막 집을 칠할 때 최소 비용)
        int result = Math.min(dp[n - 1][0], Math.min(dp[n - 1][1], dp[n - 1][2]));
        System.out.println(result);

        sc.close();
    }
}
