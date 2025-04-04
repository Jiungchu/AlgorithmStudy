
import java.util.Scanner;

public class Main {
	static long n, divNum = 1000000;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextLong();
		System.out.println(solution(new long[][] {{1,1},{1,0}}, n-1));
		sc.close();
	}
	static long solution(long[][] matrix, long exp) {
		long[][] result = {{1,0},{0,1}};
		while(exp>0) {
			// matrix를 제곱해가는 것은, 늘어난 항의 개수를 처리
			// exp가 작아지면 항의 개수는 2배로 늘어남 
			// -> 따라서 홀수일 때 처리하는 것도 현재 base가 아니라 matrix를 곱해주는 것
			if(exp%2==1) result = dot(result,matrix);
			matrix = dot(matrix,matrix);
			exp /= 2;
		}
		return result[0][0];
	};
	
	// 행렬곱
	static long[][] dot(long[][] A, long[][] B){
		long[][] result = new long[A.length][A.length];
		for(int i=0;i<A.length;i++) {
			for(int j=0;j<A.length;j++) {
				for(int k=0;k<A.length;k++) {
					result[i][j] = (result[i][j] + (A[i][k]*B[k][j])%divNum)%divNum;
				}
			}
		}
		return result;
	}
}