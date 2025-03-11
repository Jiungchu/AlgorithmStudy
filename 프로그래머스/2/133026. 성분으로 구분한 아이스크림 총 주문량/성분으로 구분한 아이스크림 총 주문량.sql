SELECT INGREDIENT_TYPE, sum(TOTAL_ORDER) 'TOTAL_ORDER'
FROM FIRST_HALF f
    JOIN ICECREAM_INFO i
    ON(f.flavor = i.flavor)
group by 1
order by 2