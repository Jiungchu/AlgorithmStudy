
import java.util.*;

public class Main {

    static final int LEFT = 0;
    static final int UP = 1;
    static final int RIGHT = 2;
    static final int DOWN = 3;

    static final int MAP_SIZE = 6;
    static final int PRIME = 52967;

    static int[][] MAP = new int[MAP_SIZE + 2][MAP_SIZE + 2];
    static int[] hashMap = new int[PRIME];
    static CAR[] car = new CAR[11];
    static int ccnt = 0;

    static class CAR {
        int r, c, dir, length;

        CAR(int r, int c, int dir, int length) {
            this.r = r;
            this.c = c;
            this.dir = dir;
            this.length = length;
        }
    }

    static void input() {
        // 주변을 벽으로 만든다.
        for (int r = 0; r <= MAP_SIZE + 1; r++)
            for (int c = 0; c <= MAP_SIZE + 1; c++)
                MAP[r][c] = -1;

        // MAP 입력
        Scanner sc = new Scanner(System.in);
        for (int r = 1; r <= MAP_SIZE; r++) {
            for (int c = 1; c <= MAP_SIZE; c++) {
                MAP[r][c] = sc.nextInt();
            }
        }

        for (int r = 1; r <= MAP_SIZE; r++) {
            for (int c = 1; c <= MAP_SIZE; c++) {
                if (MAP[r][c] != 0) {
                    int carNum = MAP[r][c];

                    // hash를 위해 1로 변경
                    MAP[r][c] = 1;

                    if (car[carNum] != null) continue; // 이미 입력 받은 차라면 continue

                    ccnt++;
                    if (MAP[r][c + 1] == carNum) { // 가로로 놓인 차
                        car[carNum] = new CAR(r, c, LEFT, MAP[r][c + 2] == carNum ? 3 : 2);
                    } else { // 세로로 놓인 차
                        car[carNum] = new CAR(r, c, UP, MAP[r + 2][c] == carNum ? 3 : 2);
                    }
                }
            }
        }
    }

    static void output() {
        int[][] tmpMAP = new int[MAP_SIZE + 2][MAP_SIZE + 2];
        for (int i = 1; i <= 10; i++) {
            int length = car[i].length;

            for (int k = 0; k < length; k++) {
                int r, c;

                if (car[i].dir == LEFT) {
                    r = car[i].r;
                    c = car[i].c + k;
                    tmpMAP[r][c] = i;
                } else {
                    r = car[i].r + k;
                    c = car[i].c;
                    tmpMAP[r][c] = i;
                }
            }
        }

        for (int r = 1; r <= MAP_SIZE; r++) {
            for (int c = 1; c <= MAP_SIZE; c++) {
                System.out.print(tmpMAP[r][c] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    static long getHash(int[][] MAP) {
        long hash = 0;
        long count = 0;

        for (int r = 1; r <= MAP_SIZE; r++) {
            for (int c = 1; c <= MAP_SIZE; c++) {
                hash |= ((long) MAP[r][c]) << count++;
            }
        }

        return hash;
    }

    static int[] dr = { 0, -1, 0, 1 };
    static int[] dc = { -1, 0, 1, 0 };

    static boolean isMoveRight(CAR car, int[][] MAP) {
        if (MAP[car.r + dr[RIGHT]][car.c + car.length - 1 + dc[RIGHT]] != 0) return false;

        int length = car.length;
        for (int i = length - 1; i >= 0; i--) {
            int nr = car.r + dr[RIGHT];
            int nc = car.c + i + dc[RIGHT];
            MAP[car.r][car.c + i] = 0;
            MAP[nr][nc] = 1;
        }

        return true;
    }

    static boolean isMoveLeft(CAR car, int[][] MAP) {
        if (MAP[car.r + dr[LEFT]][car.c + dc[LEFT]] != 0) return false;

        int length = car.length;
        for (int i = 0; i < length; i++) {
            int nr = car.r + dr[LEFT];
            int nc = car.c + i + dc[LEFT];
            MAP[car.r][car.c + i] = 0;
            MAP[nr][nc] = 1;
        }

        return true;
    }

    static boolean isMoveUp(CAR car, int[][] MAP) {
        if (MAP[car.r + dr[UP]][car.c + dc[UP]] != 0) return false;

        int length = car.length;
        for (int i = 0; i < length; i++) {
            int nr = car.r + i + dr[UP];
            int nc = car.c + dc[UP];
            MAP[car.r + i][car.c] = 0;
            MAP[nr][nc] = 1;
        }

        return true;
    }

    static boolean isMoveDown(CAR car, int[][] MAP) {
        if (MAP[car.r + car.length - 1 + dr[DOWN]][car.c + dc[DOWN]] != 0) return false;

        int length = car.length;
        for (int i = length - 1; i >= 0; i--) {
            int nr = car.r + i + dr[DOWN];
            int nc = car.c + dc[DOWN];
            MAP[car.r + i][car.c] = 0;
            MAP[nr][nc] = 1;
        }

        return true;
    }

    static int MIN = Integer.MAX_VALUE;

    static void DFS(int L) {
        if (MIN <= L) return;

        long h = getHash(MAP);
        int p = (int) (h % PRIME);

        if (hashMap[p] > L) hashMap[p] = L;
        else return;

        if (car[1].c == 5) {
            if (L < MIN) MIN = L;
            return;
        }

        if (L > 7) return;

        for (int i = 1; i <= ccnt; i++) {
            if (car[i].dir == LEFT) { // 가로 차인 경우
                if (isMoveRight(car[i], MAP)) {
                    car[i].c++;
                    DFS(L + 1);
                    isMoveLeft(car[i], MAP);
                    car[i].c--;
                }

                if (isMoveLeft(car[i], MAP)) {
                    car[i].c--;
                    DFS(L + 1);
                    isMoveRight(car[i], MAP);
                    car[i].c++;
                }
            } else { // 세로 차인 경우
                if (isMoveUp(car[i], MAP)) {
                    car[i].r--;
                    DFS(L + 1);
                    isMoveDown(car[i], MAP);
                    car[i].r++;
                }

                if (isMoveDown(car[i], MAP)) {
                    car[i].r++;
                    DFS(L + 1);
                    isMoveUp(car[i], MAP);
                    car[i].r--;
                }
            }
        }
    }

    public static void main(String[] args) {
        input();

        Arrays.fill(hashMap, Integer.MAX_VALUE);

        DFS(0);

        if (MIN == Integer.MAX_VALUE) {
            System.out.println("-1");
        } else {
            System.out.println(MIN + 2);
        }
    }
}
