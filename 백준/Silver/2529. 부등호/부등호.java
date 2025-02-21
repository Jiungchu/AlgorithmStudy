
import java.io.*;

public class Main {
	static String[] symbol;
	static boolean[] used;
	static int k;
	static String min,max;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		k = Integer.parseInt(br.readLine());
		used = new boolean[10];
		symbol = br.readLine().split(" ");
		for(int i=0;i<=9;i++) {
			used[i]=true;
			solution(1, i, new StringBuilder().append(i));
			used[i]=false;
		}
		System.out.println(max);
		System.out.println(min);
		br.close();
	}
	
	static void solution(int count, int prev, StringBuilder curr) {
		// 모든 수를 다 뽑은 경우 min, max와 비교
		if(count==k+1) {
			long num=Long.parseLong(curr.toString());
			if(max==null || num>Long.parseLong(max)) max=curr.toString();
			if(min==null || num<Long.parseLong(max)) min=curr.toString();
			return;
		}
		String currSymbol = symbol[count-1];
		for(int i=0;i<=9;i++) {
			if(!used[i]) {
				if(currSymbol.equals(">") && prev>i || currSymbol.equals("<") && prev<i) {
					used[i]=true;
					int length = curr.length();
					solution(count+1, i, curr.append(i));
					curr.setLength(length); // 백트래킹
					used[i]=false;
				}
			}
		}
		
	}

}
