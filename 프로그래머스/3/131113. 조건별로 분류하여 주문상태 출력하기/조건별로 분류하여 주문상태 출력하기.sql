SELECT ORDER_ID, PRODUCT_ID, 
date_format(OUT_DATE,'%Y-%m-%d'),
IF(OUT_DATE IS NULL,'출고미정',
  IF(OUT_DATE>'2022-05-01','출고대기','출고완료')) 출고여부
FROM FOOD_ORDER 
ORDER BY 1