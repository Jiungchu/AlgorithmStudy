
import java.util.*;

public class Main {
	static void append(int[][] connected, int node, int num) {
		int[] arr = connected[node];
		int[] newArr = new int[arr.length+1];
		for(int i=0;i<arr.length;i++) {
			newArr[i] = arr[i];
		}
		newArr[arr.length] = num;
		connected[node] = newArr;
	}
	
	static void dfs(int[][] connected, int[] parents, int node) {
		for(int nextNode:connected[node]) {
			if(parents[nextNode]==0) {
				parents[nextNode] = node;
				dfs(connected, parents, nextNode);
			}
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		int[][] connected = new int[n+1][];
		int[] parents = new int[n+1];
		
		for(int i=0;i<n-1;i++) {
			int a = sc.nextInt(); int b = sc.nextInt();
			// 양방향 연결
			if(connected[a]==null) connected[a]=new int[] {b};
			else append(connected, a, b);
			if(connected[b]==null) connected[b]=new int[] {a};
			else append(connected, b, a);
		}
		
		dfs(connected, parents, 1);
		
		for(int i=2;i<=n;i++) System.out.println(parents[i]);
		
		sc.close();

	}

}
