import sys
input = sys.stdin.readline
N, M = map(int, input().split())
grid = [list(input().strip()) for _ in range(N)]
grid2 = grid.copy()
from collections import deque
queue = deque([[0,0]])
dx = [1,-1,0,0]
dy = [0,0,1,-1]
while queue:
    curr = queue.popleft()
    x,y = curr[0], curr[1]
    for i in range(4):
        nx = x+dx[i]
        ny = y+dy[i]
        if 0<=nx<N and 0<=ny<M and grid[nx][ny]=='1':
            queue.append([nx,ny])
            grid2[nx][ny]=int(grid2[x][y])+1
print(grid2[N-1][M-1])