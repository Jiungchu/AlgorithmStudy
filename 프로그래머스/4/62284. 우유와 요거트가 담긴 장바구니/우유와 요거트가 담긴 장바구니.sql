SELECT DISTINCT CART_ID
FROM CART_PRODUCTS T1
WHERE NAME='Milk'
AND EXISTS (
    SELECT 1
    FROM CART_PRODUCTS T2
    WHERE T1.CART_ID = T2.CART_ID
    AND T2.NAME='Yogurt'
)
ORDER BY 1