SELECT C.CAR_ID, C.CAR_TYPE,
ROUND(daily_fee * 30 *(1-ifnull(discount_rate,0)/100)) FEE
FROM    CAR_RENTAL_COMPANY_CAR C
JOIN (
    SELECT CAR_TYPE, DISCOUNT_RATE
    FROM CAR_RENTAL_COMPANY_DISCOUNT_PLAN 
    WHERE DURATION_TYPE = '30일 이상'
    ) D # 30일 이상의 할인율만 필요함
    ON C.CAR_TYPE = D.CAR_TYPE
WHERE C.CAR_TYPE in ('세단','SUV')
# 대여할 수 없는 차는 제외
AND NOT EXISTS (
    SELECT 1
    FROM CAR_RENTAL_COMPANY_RENTAL_HISTORY H
    WHERE C.CAR_ID = H.CAR_ID
    AND START_DATE <= '2022-11-30' AND END_DATE >= '2022-11-01'
)
AND daily_fee * 30 *(1-ifnull(discount_rate,0)/100) 
BETWEEN 500000 AND 2000000
ORDER BY 3 DESC,2, 1 DESC