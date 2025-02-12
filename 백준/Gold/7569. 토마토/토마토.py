import sys
from collections import deque
input = sys.stdin.readline

# 입력 받기
M, N, H = map(int, input().split())
tomato = []
queue = deque()

# 토마토 상자 초기화 및 익은 토마토 위치 기록
for i in range(H):
    floor = []
    for j in range(N):
        row = list(map(int, input().split()))
        for k in range(M):
            if row[k] == 1:  # 익은 토마토
                queue.append((i, j, k))  # 익은 토마토 위치 큐에 추가
        floor.append(row)
    tomato.append(floor)

# 방향 벡터 (상, 하, 좌, 우, 위, 아래)
dx = [1, -1, 0, 0, 0, 0]
dy = [0, 0, 1, -1, 0, 0]
dz = [0, 0, 0, 0, 1, -1]

# BFS 시작
while queue:
    x, y, z = queue.popleft()
    for i in range(6):
        nx = x + dx[i]
        ny = y + dy[i]
        nz = z + dz[i]
        if 0 <= nx < H and 0 <= ny < N and 0 <= nz < M:
            if tomato[nx][ny][nz] == 0:  # 익지 않은 토마토일 경우
                tomato[nx][ny][nz] = tomato[x][y][z] + 1
                queue.append((nx, ny, nz))

# 결과 계산
max_days = 0
for i in range(H):
    for j in range(N):
        for k in range(M):
            if tomato[i][j][k] == 0:  # 익지 않은 토마토가 남아있을 경우
                print(-1)
                exit()
            max_days = max(max_days, tomato[i][j][k])

# 최종 일수 출력 (초기값 1을 빼줘야 함)
print(max_days - 1)