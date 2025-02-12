from itertools import combinations

def calculate_chicken_distance(houses, chickens):
    total_distance = 0
    for hr, hc in houses:
        min_distance = float('inf')
        for cr, cc in chickens:
            distance = abs(hr - cr) + abs(hc - cc)
            min_distance = min(min_distance, distance)
        total_distance += min_distance
    return total_distance

def solve_chicken_distance():
    N, M = map(int, input().split())
    city = [list(map(int, input().split())) for _ in range(N)]
    
    houses = []
    chickens = []
    
    for r in range(N):
        for c in range(N):
            if city[r][c] == 1:
                houses.append((r, c))
            elif city[r][c] == 2:
                chickens.append((r, c))
    
    chicken_combinations = list(combinations(chickens, M))
    
    min_city_distance = float('inf')
    for chicken_set in chicken_combinations:
        city_distance = calculate_chicken_distance(houses, chicken_set)
        min_city_distance = min(min_city_distance, city_distance)
    print(min_city_distance)

solve_chicken_distance()