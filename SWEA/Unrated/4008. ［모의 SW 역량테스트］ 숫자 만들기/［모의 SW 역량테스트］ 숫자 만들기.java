
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	
	static int N, min, max;
	static int[] numbers, operators, used;
	
	public static void main(String[] args) throws IOException{
		int T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			init();
			solution(0,numbers[0]);
			sb.append("#").append(t).append(" ").append(max-min).append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}
	
	static void solution(int index, int currNum) {
		if(index==N-1) {
			min = Math.min(min, currNum);
			max = Math.max(max, currNum);
			return;
		}
		for(int i=0;i<4;i++) {
			if(operators[i]>used[i]) { 
				if(i==3 && numbers[index+1]==0) continue;
				used[i]++;
				solution(index+1, operate(currNum, numbers[index+1],i));
				used[i]--;
			}
		}
	}
	
	static int operate(int num, int nextNum, int oprIndex) {
		if (oprIndex ==0) return num+nextNum;
		else if (oprIndex == 1) return num-nextNum;
		else if (oprIndex == 2) return num*nextNum;
		else if (oprIndex == 3) return num/nextNum;
		return 0;
	}

	
	static void init() throws IOException{
		N = Integer.parseInt(br.readLine());
		min = Integer.MAX_VALUE;
		max = Integer.MIN_VALUE;
		numbers = new int[N];
		operators = new int[4];
		used = new int[4];
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0;i<4;i++) {
			operators[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<N;i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}
	}
	
}
