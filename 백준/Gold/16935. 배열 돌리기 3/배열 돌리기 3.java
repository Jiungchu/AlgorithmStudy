import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();

	static int N, M, R;
	static int[][] arr;
	static int[] operations;
	static int[][][] blocks;
	static int[][] blockIndex = {{0,0},{0,1},{1,1},{1,0}};

	public static void main(String[] args) throws IOException {

		init();
		solution();
		arrayShow();
		br.close();
	}

	static void solution() {
		for(int r=0;r<R;r++) {
			operate(operations[r]);
		}
	}
	
	static void operate(int order) {
		if(order==1) {
			for(int i=0;i<arr.length/2;i++) {
				swapRow(i, arr.length-1-i);
			}
		} else if(order==2) {
			for(int i=0;i<arr.length;i++) {
				for(int j=0;j<arr[0].length/2;j++) {
					swapInRow(arr[i], j, arr[0].length-1-j);
				}
			}
			
		} else if(order==3) {
			turnRight();
		} else if(order==4) {
			turnLeft();
		} else if(order==5) {
			makeBlocks();
			for(int i=0;i<4;i++) {
				blockSwap(i, blockIndex[(i+1)%4][0], blockIndex[(i+1)%4][1]);
			}
		} else if(order==6) {
			makeBlocks();
			for(int i=0;i<4;i++) {
				blockSwap((i+1)%4, blockIndex[i][0], blockIndex[i][1]);
			}
		}
	}
	
	static void blockSwap(int blockNum, int n1, int n2) {
		int[][] block = blocks[blockNum];
		for(int i=0;i<arr.length/2;i++) {
			for(int j=0;j<arr[0].length/2;j++) {
				arr[i+arr.length/2*n1][j+arr[0].length/2*n2] = block[i][j];
			}
		}
	}
	
	static void makeBlocks() {
		blocks = new int[4][arr.length/2][arr[0].length/2];
		for(int i=0;i<arr.length/2;i++) {
			for(int j=0;j<arr[0].length/2;j++) {
				blocks[0][i][j] = arr[i][j];
				blocks[1][i][j] = arr[i][j+arr[0].length/2];
				blocks[2][i][j] = arr[i+arr.length/2][j+arr[0].length/2];
				blocks[3][i][j] = arr[i+arr.length/2][j];
			}
		}
	}
	
	static void turnRight() {
		int[][] temp =  new int[arr[0].length][arr.length];
		for(int i=0;i<arr[0].length;i++) {
			for(int j=0;j<arr.length;j++) {
				temp[i][j] = arr[arr.length-1-j][i];
			}
		}
		arr = temp;
	}
	
	static void turnLeft() {
		int[][] temp = new int[arr[0].length][arr.length];
		for(int i=0;i<arr[0].length;i++) {
			for(int j=0;j<arr.length;j++) {
				temp[i][j] = arr[j][arr[0].length-1-i];
			}
		}
		arr = temp;
	}
	
	static void swapRow(int i, int j) {
		int[] temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
	static void swapInRow(int[] row, int i, int j) {
		int temp = row[i];
		row[i] = row[j];
		row[j] = temp;
	}
	
	static void arrayShow() {
		for(int i=0;i<arr.length;i++) {
			for(int j=0;j<arr[i].length;j++) {
				System.out.print(arr[i][j]+" ");
			}
			System.out.println();
		}
	}

	static void init() throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		arr = new int[N][M];
		operations = new int[R];
		
		for(int i=0;i<N;i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0;j<M;j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		st = new StringTokenizer(br.readLine());
		for(int i=0;i<R;i++) operations[i]=Integer.parseInt(st.nextToken());
	}
}
