
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		int total = (1<<10)-1;
		for(int t=1;t<=T;t++) {
			int n = Integer.parseInt(br.readLine());
			int visited = 0;
			int count=0;
			while(visited!=total) {
				char[] arr = String.valueOf(n*++count).toCharArray();
				for(char c : arr) {
					int num = c-'0';
					visited = visited | (1<<num);
				}
			}
			sb.append("#").append(t).append(" ").append(n*count).append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}

}
