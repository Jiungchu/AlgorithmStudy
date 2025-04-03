
import java.util.*;
import java.io.*;

public class Solution {
    static int[][] map;
    static int[] dr = { 1, -1, 0, 0 };
    static int[] dc = { 0, 0, 1, -1 };
    static int N, K;
    static int maxLength = 0;
    static boolean[][] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            maxLength = 0;

            map = new int[N][N];
            visited = new boolean[N][N];
            int maxHeight = 0;
            List<int[]> maxList = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    int height = Integer.parseInt(st.nextToken());
                    if (height > maxHeight) {
                        maxList.clear();
                        maxHeight = height;
                    }
                    if (height == maxHeight)
                        maxList.add(new int[] { i, j });
                    map[i][j] = height;
                }
            }

            for (int[] curr : maxList) {
            	// 백트래킹을 위해 방문 이후 visited를 false로 설정
                visited[curr[0]][curr[1]] = true;
                dfs(curr[0], curr[1], maxHeight, 1, false);
                visited[curr[0]][curr[1]] = false;
            }
            sb.append("#").append(t+1).append(" ").append(maxLength).append("\n");
        }
        System.out.println(sb.toString());
        br.close();
    }

    static void dfs(int r, int c, int height, int length, boolean breaked) {
        if (length > maxLength)
            maxLength = length;
        
        for (int i = 0; i < 4; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if (nr >= 0 && nr < N && nc >= 0 && nc < N && !visited[nr][nc]) {
                if (map[nr][nc] < height) {
                    visited[nr][nc] = true;
                    dfs(nr, nc, map[nr][nc], length + 1, breaked);
                    visited[nr][nc] = false;
                } else if (!breaked) {
                    for (int k = 1; k <= K; k++) {
                        if (map[nr][nc] - k < height) {
                            visited[nr][nc] = true;
                            dfs(nr, nc, map[nr][nc] - k, length + 1, true);
                            visited[nr][nc] = false;
                            break;
                        }
                    }
                }
            }
        }
    }
}
