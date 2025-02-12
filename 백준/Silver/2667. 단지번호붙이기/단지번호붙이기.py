import sys
input = sys.stdin.readline
N = int(input())
grid = [input().strip() for _ in range(N)]
visited = [[False]*N for _ in range(N)]
counts = []
def dfs(loc,num):
    counts[num] += 1
    dx = [1,-1,0,0]
    dy = [0,0,1,-1]
    x,y = loc[0],loc[1]
    visited[x][y]= True
    for i in range(4):
        nx = x+dx[i]
        ny = y+dy[i]
        if 0<=nx<N and 0<=ny<N and not visited[nx][ny] and grid[nx][ny]=='1':
            visited[nx][ny] = True
            dfs([nx,ny],num)
for i in range(N):
    for j in range(N):
        if grid[i][j]=='1' and not visited[i][j]:
            counts.append(0)
            dfs([i,j],len(counts)-1)
print(len(counts))
counts.sort()
for num in counts:
    print(num)