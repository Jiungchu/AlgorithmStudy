
import java.io.*;
import java.util.*;

public class Main {
		
		
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] nk = br.readLine().split(" ");
		int n = Integer.parseInt(nk[0]);
		int k = Integer.parseInt(nk[1]);
		
		List<int[]> list = new ArrayList<>();
		for(int i =0;i<n;i++) {
			String[] input = br.readLine().split(" ");
			list.add(new int[] {Integer.parseInt(input[0]),Integer.parseInt(input[1])});
		}
		
		int[] dp = new int[k+1];
		for(int[] item:list) {
			int weight = item[0]; int value = item[1];
			for(int w=k;w>=weight;w--) {
				dp[w] = Math.max(dp[w], dp[w-weight]+value);
			}
		}
		System.out.println(dp[k]);
		br.close();
	}

}
 