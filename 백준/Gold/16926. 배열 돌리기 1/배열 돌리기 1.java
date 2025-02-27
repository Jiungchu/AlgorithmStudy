
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int N, M, R, numLayers;
    static int[][] arr;
    static int[] dr = {0, 1, 0, -1}, dc = {1, 0, -1, 0}; // 우, 하, 좌, 상 방향

    public static void main(String[] args) throws IOException {
        init();
        solution();
        printArray();
        br.close();
    }

    static void solution() {
        numLayers = Math.min(N, M) / 2; // 전체 레이어 개수

        for (int l = 0; l < numLayers; l++) {
            int layerSize = getLayerSize(l);
            int rotations = R % layerSize; // size만큼 돌면 제자리

            // 원본 배열에서 해당 레이어 값을 1차원 배열로 추출
            int[] temp = extractLayer(l, layerSize);

            // 회전 후 값을 원본 배열에 반영
            applyRotatedLayer(l, temp, rotations, layerSize);
        }
    }

    // 레이어의 크기 구하기
    static int getLayerSize(int l) {
        return 2 * ((N - 2 * l) + (M - 2 * l) - 2); // 테두리 길이 공식
    }

    // 특정 레이어의 숫자를 1차원 배열로 추출
    static int[] extractLayer(int l, int size) {
        int[] temp = new int[size];
        int index = 0, r = l, c = l, d = 0;

        while (index < size) {
            temp[index++] = arr[r][c];

            // 다음 좌표 계산
            int nr = r + dr[d], nc = c + dc[d];
            if (nr < l || nr >= N - l || nc < l || nc >= M - l) {
                d++; // 방향 전환
                nr = r + dr[d];
                nc = c + dc[d];
            }
            r = nr;
            c = nc;
        }
        return temp;
    }

    // 회전된 레이어 값을 배열에 적용
    static void applyRotatedLayer(int l, int[] temp, int start, int size) {
        int index = 0, r = l, c = l, d = 0;

        while (index < size) {
            arr[r][c] = temp[(index + start) % size];

            // 다음 좌표 계산
            int nr = r + dr[d], nc = c + dc[d];
            if (nr < l || nr >= N - l || nc < l || nc >= M - l) {
                d++; // 방향 전환
                nr = r + dr[d];
                nc = c + dc[d];
            }
            r = nr;
            c = nc;
            index++;
        }
    }

    static void printArray() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                sb.append(arr[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

    static void init() throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        arr = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }
}
