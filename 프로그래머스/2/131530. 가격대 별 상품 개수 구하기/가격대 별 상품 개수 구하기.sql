SELECT (PRICE DIV 10000)*10000 'PRICE_GROUP',
COUNT(*)
FROM PRODUCT 
GROUP BY 1
ORDER BY 1