
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int[] cond=new int[4], cnt=new int[4];
	static int ans=0;
	public static void main(String[] args) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		int S = Integer.parseInt(st.nextToken());
		int P = Integer.parseInt(st.nextToken());
		String str = br.readLine();
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<4;i++) {
			cond[i] = Integer.parseInt(st.nextToken());
		}
		// 가장 앞쪽에 길이가 P인 문자열 COUNT
		for(int i=0;i<P;i++) {
			char c = str.charAt(i);
			increse(c, 1);
		}
		check();
		
		// 앞에 하나씩 빼고 뒤에 하나씩 넣으면서 check
		for(int i=0;i<S-P;i++) {
			char c1 = str.charAt(i);
			char c2 = str.charAt(i+P);
			increse(c1,-1);
			increse(c2,1);
			check();
		}
		System.out.println(ans);
		br.close();
	}
	
	static void increse(char c, int num) {
		if(c=='A') cnt[0]+=num;
		if(c=='C') cnt[1]+=num;
		if(c=='G') cnt[2]+=num;
		if(c=='T') cnt[3]+=num;
	}
	
	static void check() {
		// 조건 하나라도 맞추지 못하면 return
		for(int i=0;i<4;i++) {
			if(cond[i]>cnt[i]) return;
		}
		ans++; 
	}
}
