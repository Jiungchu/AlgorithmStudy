
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int[][] ingredients;
	static int n,l,maxScore;
	public static void main(String[] args) throws IOException{
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			init();
			maxScore = 0;
			comb(0, 0,0);
			sb.append("#").append(t).append(" ").append(maxScore).append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}
	
	static void comb(int count,int score, int calories) {
		if(count==n) {
			if(calories<=l) maxScore = Math.max(maxScore, score);
			return;
		}
		comb(count+1,score+ingredients[count][0],calories+ingredients[count][1]);
		comb(count+1,score,calories);
	}
	
	static void init() throws IOException{
		StringTokenizer st = new StringTokenizer(br.readLine());
		n=Integer.parseInt(st.nextToken());
		l=Integer.parseInt(st.nextToken());
		ingredients = new int[n][2];
		for(int i=0;i<n;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<2;j++) {
				ingredients[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}
}
