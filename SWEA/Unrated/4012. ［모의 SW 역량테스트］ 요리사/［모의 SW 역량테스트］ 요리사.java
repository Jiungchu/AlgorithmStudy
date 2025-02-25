
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	
	static int N, minDiff;
	static int[][] synergy;
	
	public static void main(String[] args) throws IOException{
		int T = Integer.parseInt(br.readLine());
		for(int t=1;t<=T;t++) {
			init();
			solution(0,0,0);
			sb.append("#").append(t).append(" ").append(minDiff).append("\n");
		}
		System.out.println(sb.toString());
		br.close();
	}
	
	static void solution(int count, int curr, int used) {
		if(count==N/2) {
			int[] comb1 = findComb(used, 1);
			int[] comb2 = findComb(used, 0);
			minDiff = Math.min(minDiff, Math.abs(getSynergy(comb1)-getSynergy(comb2)));
			return;
		}
		for(int i=curr;i<N;i++) {
			// 비트마스킹 사용
			if((used & (1<<i)) == 0) { // i번째 bit가 켜져 있지 않으면
				solution(count+1, i+1, used|(1<<i));
			}
		}
	}
	
	// 1로 표시된 재료, 2로 표시된 재료 찾기
	static int[] findComb(int used, int flag) {
		int[] comb = new int[N/2];
		int index=0;
		for(int i=0;i<N;i++) {
			if((used&(1<<i)) == flag<<i) comb[index++] = i;
			if(index==N/2) break;
		}
		return comb;
	}
	
	// 각 조합별로 점수 더해서 반환
	static int getSynergy(int[] comb) {
		int score = 0;
		for(int i=0;i<comb.length;i++) {
			for(int j=i+1;j<comb.length;j++) {
				score += synergy[comb[i]][comb[j]];
				score += synergy[comb[j]][comb[i]];
			}
		}
		return score;
	}
	
	static void init() throws IOException{
		N = Integer.parseInt(br.readLine());
		minDiff = Integer.MAX_VALUE;
		synergy = new int[N][N];
		for(int i=0;i<N;i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0;j<N;j++) {
				synergy[i][j] = Integer.parseInt(st.nextToken());
			}
		}
	}
	
}
