
import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    static int d, w, k;
    static int[][] grid;
    static int[] row;
    static boolean success;
    static int min;
    static int t;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for (t = 0; t < T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            d = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            grid = new int[d][w];
            row = new int[d];
            Arrays.fill(row, -1);
            success = false;
            min = k + 1;
            for (int i = 0; i < d; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < w; j++) {
                    grid[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            if (k == 1) {
                System.out.println("#" + (t + 1) + " " + 0);
                continue;
            }
            dfs(0, 0);
            System.out.println("#" + (t + 1) + " " + min);
        }
        br.close();
    }

    static void dfs(int curr, int count) {
        if (count >= min || count > k)
            return;
        if (count == k) {
            min = Math.min(min, count);
            return;
        }
        if (curr == d) {
            if (check()) min = count;
            return;
        }

        dfs(curr + 1, count);
        row[curr] = 0;
        dfs(curr + 1, count + 1);

        row[curr] = 1;
        dfs(curr + 1, count + 1);

        row[curr] = -1;
    }

    static boolean check() {
        int pass = 0;
        for (int j = 0; j < w; j++) {
            int count = 1;
            int prev = row[0] == -1 ? grid[0][j] : row[0];
            for (int i = 1; i < d; i++) {
                int curr = (row[i] == -1) ? grid[i][j] : row[i];

                if (prev == curr) {
                    count++;
                } else {
                    count = 1;
                }
                prev = curr;

                if (k >= d - i + 1 && count == 1)
                    break;
                if (count == k) {
                    pass++;
                    break;
                }
            }
            if (pass != j + 1)
                return false;
        }
        return true;
    }
}
