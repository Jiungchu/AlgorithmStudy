
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();

	static int L,C;
	static Set<Character> chars;
	static int[] isVowel, isConst;
	
		
	public static void main(String[] args) throws IOException {
		init();
		solution(0,0,0,0,0,new StringBuilder());
		System.out.println(sb.toString());
	}
	
	// charCount는 총 문자의 수, count1은 모음의 수, count2는 자음의 수
	// 비트마스킹으로 사용한 문자를 관리
	static void solution(int charCount, int count1, int count2, int index, int used, StringBuilder curr) {
		if(charCount==L) {
			if(count1>=1 && count2>=2) {
				sb.append(curr).append("\n");
			}
			return;
		}
		int currLen = curr.length();
		for(char c: chars) {
			int i=c-'a';
			if(i<index) continue; // 현재 선택한 문자 이후의 문자들만 고려
			// 사용하지 않은 문자
			if((used & (1<<i))==0) {
				solution(charCount+1, count1+isVowel[i], count2+isConst[i],
						i+1,used|1<<i,curr.append(c));
				// 백트래킹을 위한 초기화
				curr.setLength(currLen);
			}
		}
	}
	
	
	static void init() throws IOException{
		StringTokenizer st = new StringTokenizer(br.readLine());
		L = Integer.parseInt(st.nextToken());
		C= Integer.parseInt(st.nextToken());
		chars = new TreeSet<>();
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<C;i++) {
			chars.add(st.nextToken().charAt(0));
		}
		
		// 모음과 자음을 바로 뽑을 수 있는 배열
		isVowel = new int[26];
		isConst = new int[26];
		Arrays.fill(isConst, 1);
		for(int i=0;i<26;i++) {
			char c = (char)('a'+i);
			if( c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
				isVowel[i]=1;
				isConst[i]=0;
			}
		}
	}

}
