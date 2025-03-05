def solution(start, target, size):
    if size==1:
        return 0
    size = size//2
    r,c = target[0], target[1]
    curr_r,curr_c = start[0], start[1]
    # 사분면 판별
    order = 0
    if r >= curr_r+size:
        order += 2
        curr_r += size
    if c >= curr_c+size:
        order += 1
        curr_c += size
    return order*size*size + solution([curr_r,curr_c], [r,c], size)
N, r, c = map(int,input().split())
print(solution([0,0], [r,c], 2**N))