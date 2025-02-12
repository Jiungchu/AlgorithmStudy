/////////////////////////////////////////////////////////////////////////////////////////////
// 기본 제공코드는 임의 수정해도 관계 없습니다. 단, 입출력 포맷 주의
// 아래 표준 입출력 예제 필요시 참고하세요.
// 표준 입력 예제
// int a;
// double b;
// char g;
// String var;
// long AB;
// a = sc.nextInt();                           // int 변수 1개 입력받는 예제
// b = sc.nextDouble();                        // double 변수 1개 입력받는 예제
// g = sc.nextByte();                          // char 변수 1개 입력받는 예제
// var = sc.next();                            // 문자열 1개 입력받는 예제
// AB = sc.nextLong();                         // long 변수 1개 입력받는 예제
/////////////////////////////////////////////////////////////////////////////////////////////
// 표준 출력 예제
// int a = 0;                            
// double b = 1.0;               
// char g = 'b';
// String var = "ABCDEFG";
// long AB = 12345678901234567L;
//System.out.println(a);                       // int 변수 1개 출력하는 예제
//System.out.println(b); 		       						 // double 변수 1개 출력하는 예제
//System.out.println(g);		       						 // char 변수 1개 출력하는 예제
//System.out.println(var);		       				   // 문자열 1개 출력하는 예제
//System.out.println(AB);		       				     // long 변수 1개 출력하는 예제
/////////////////////////////////////////////////////////////////////////////////////////////
import java.util.Scanner;
import java.io.*;
import java.util.*;

/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
class Solution
{
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			int n = Integer.parseInt(br.readLine());
			int[] trees = new int[n];
			String[] input = br.readLine().split(" ");
			int maxHeight = 0;
			for (int i = 0; i < n; i++) {
				trees[i] = Integer.parseInt(input[i]);
				if (trees[i] > maxHeight)
					maxHeight = trees[i];
			}
			// 내림차순으로 정렬
			Arrays.sort(trees);
			int day = 0;
			int index = n - 1;
			int count=0;
			for (int i = n-1; i >= 0; i--) {
				if(trees[i]==maxHeight) {
					index--; count++;
				}
				else break;
			}
			boolean flag=true;
			while (count<n) {
				day++;
				int grow;
				if (day % 2 == 1) {
					grow = 1;
				} else {
					grow = 2;
				}
				// 뒤에서부터 넣을 수 있는 곳에 넣기
				for (int i = index; i >= 0; i--) {
					int tree = trees[i];
					if(tree==maxHeight-1) flag=true; // 1 더 작은 나무가 있으면 true
					if(grow==1 && tree==maxHeight-2 && (flag || count==n-1)) { // 1 더 작은 나무가 있을 때
						continue;
					}
					if (tree + grow <= maxHeight) {
						trees[i] += grow; 
						if(trees[i]==maxHeight) {
							count++;
							if(grow==1) {// 단순 탐색 시작 위치
								index--; 
								flag=false;
							}
						}
						break; // 하루에 하나만 grow
					}
				}
//				if(t+1==6) System.out.println("#day: "+day+" "+Arrays.toString(trees)+" "+index+ " "+flag);

			}
			System.out.println("#" + (t + 1) + " " + day);
		}
		br.close();

	}
}