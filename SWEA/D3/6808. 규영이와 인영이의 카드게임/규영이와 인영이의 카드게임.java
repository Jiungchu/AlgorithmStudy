
import java.io.*;
import java.util.*;

public class Solution {
	static int[] myCard, otherCard;
	static boolean[] used = new boolean[19];
	static int win,lose;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			win=0;lose=0;
			myCard = new int[9];
			otherCard = new int[9];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 9; i++) {
				myCard[i] = Integer.parseInt(st.nextToken());
			}
			int ocIndex = 0;
			for (int i = 1; i <= 18; i++) {
				if (ocIndex == 9)
					break;
				boolean insert = true;
				for(int j=0;j<9;j++) {
					if(myCard[j]==i) {
						insert=false;
						break;
					}
				}
				if(insert) otherCard[ocIndex++]=i;
			}
			solution(0, 0, 0);
			System.out.println("#"+t+" "+win+" "+lose);
		}

		br.close();
	}
	
	static void solution(int count, int myScore, int otherScore) {
		if(count==9) {
			if(myScore>otherScore) win++;
			else if(myScore<otherScore) lose++;
			return;
		}
		int myNum = myCard[count];
		for(int i=0;i<9;i++) {
			int otherNum=otherCard[i];
			if(!used[otherNum]) {
				used[otherNum] = true;
				if(myNum>otherNum) solution(count+1, myScore+myNum+otherNum, otherScore);
				else solution(count+1, myScore, otherScore+myNum+otherNum);
				used[otherNum]=false;
			}
		}
	}

}
