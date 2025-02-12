import sys
import heapq

def solve_abs_heap():
    input = sys.stdin.read
    data = input().split()
    N = int(data[0])
    operations = map(int, data[1:])
    
    heap = []
    results = []
    
    for x in operations:
        if x != 0:
            heapq.heappush(heap, (abs(x), x))
        else:
            if heap:
                results.append(heapq.heappop(heap)[1])
            else:
                results.append(0)
    
    sys.stdout.write("\n".join(map(str, results)) + "\n")

solve_abs_heap()